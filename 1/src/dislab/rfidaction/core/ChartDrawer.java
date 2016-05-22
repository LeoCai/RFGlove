package dislab.rfidaction.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * rssi动态图表
 *
 */
public class ChartDrawer extends JFrame {

	private static final int SINGLE_SCALE = 20;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2765473396023984243L;
	List<double[]> chartData = new ArrayList<double[]>();
	private int offsetX;
	private int swidth = 800;
	private int sheight = 600;
	private double refData = 20000;

	private int yScaleNum = 20;
	private int yscale = (int) (refData / yScaleNum);

	private JLabel lableAction = new JLabel();

	Color color[] = new Color[] { Color.RED, Color.GREEN, Color.BLACK,
			Color.BLUE, Color.CYAN, Color.GRAY, Color.MAGENTA, Color.ORANGE,
			Color.YELLOW, Color.PINK };

	int paddingX = 50;
	int paddingY = 50;
	private int[] preX = new int[10];
	private double[] preY = new double[10];

	boolean pause = false;
	protected boolean initing;
	private JButton btn_init;
	private JLabel stateLable = new JLabel("state");

	private static ChartDrawer chartDrawer = new ChartDrawer();

	public static ChartDrawer getInstance() {
		return chartDrawer;
	}

	private ChartDrawer() {
		// setSize(swidth, sheight);
		setTitle("chart");
		Panel panelBtn = new Panel(new FlowLayout());
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(panelBtn);
		setVisible(true);
		JButton btn_add = new JButton("add");
		btn_init = new JButton("init");
		final JButton btn_pause = new JButton("pause");
		final JButton btn_clear = new JButton("clear");
		// new JFrame().add(this.getContentPane());

		btn_init.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!initing)
					setIniting(true);
			}
		});
		btn_add.addActionListener(new ActionListener() {

			private int clickCount;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addData(new double[] { 100, 300 + 5 * clickCount++, 300, 9 });
			}
		});
		btn_pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!pause) {
					pause = true;
					btn_pause.setText("continue");
				} else {
					pause = false;
					btn_pause.setText("pause");
				}

			}
		});
		btn_clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clearChart();
				repaint();
			}
		});
		panelBtn.add(btn_init);
		panelBtn.add(btn_add);
		panelBtn.add(btn_pause);
		panelBtn.add(btn_clear);
		panelBtn.add(lableAction);
		panelBtn.add(stateLable);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.swidth = (int) screen.getWidth() / 2;
		this.sheight = (int) screen.getHeight() / 2;
		this.setSize(swidth, sheight);
		this.setLocation(0, sheight);

	}

	protected void clearChart() {
		// TODO Auto-generated method stub
		this.chartData.clear();
		offsetX = 0;
	}

	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		swidth = getWidth();
		sheight = getHeight();
		g.clearRect(0, 0, swidth, sheight);
		int i = 0;

		for (i = 0; i < 100; i++) {
			int ys = yscale * i;
			g.drawString("" + ys, swidth - paddingX, getY(ys) + 10);
		}

		if (chartData.size() == 0)
			return;

		double[] showLine = chartData.get(0);
		int showW = 60;
		int showMargin = 10;
		int offsX = swidth - (showW + showMargin) * (showLine.length + 1);
		int offsY = 100;
		for (int k = 0; k < showLine.length; k++) {
			g.setColor(color[k]);
			g.drawString("line" + k, k * (showW + showMargin) + offsX, offsY);
			g.drawLine(k * (showW + showMargin) + offsX, offsY, k
					* (showW + showMargin) + offsX + showW, offsY);
		}

		for (i = 0; i < chartData.size(); i++) {
			double[] data = chartData.get(i);
			for (int j = 0; j < data.length; j++) {
				int x = i * SINGLE_SCALE + paddingX + offsetX;
				int y = getY(data[j]);
				g.setColor(color[j]);
				g.fillOval(x, y, 5, 5);
				if (preX[j] != 0) {
					g.drawLine(x - SINGLE_SCALE, getY(preY[j]), x, y);
				}
				preX[j] = x;
				preY[j] = data[j];
				// g.drawLine(arg0, arg1, arg2, arg3);
			}
		}
		if ((i * 20 + 50 + offsetX) > swidth - paddingX) {
			offsetX -= 80;
		}

	}

	private int getY(double data) {
		// TODO Auto-generated method stub
		return sheight - (int) (data / (refData + 100) * (sheight - paddingY))
				- paddingY;
	}

	public void addData(double[] chartArray) {
		// TODO Auto-generated method stub

		// for (int i = 0; i < chartArray.length; i++) {
		// refData = chartArray[i] > refData ? chartArray[i] : refData;
		// }
		if (!pause) {
			chartData.add(chartArray);
			repaint();
		}

	}

	public void setLable(String lable) {
		lableAction.setText(lable);
	}

	public void clear() {
		chartData.clear();
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	/**
	 * 判断是否在初始化状态
	 * @return
	 */
	public boolean isIniting() {
		return initing;
	}

	public void setIniting(boolean initing) {
		this.initing = initing;
		if (initing) {
			btn_init.setText("initing");
		} else {
			btn_init.setText("start init");
		}
	}

//	public static void main(String args[]) {
//		new ChartDrawer();
//
//	}

	public void showStateInfo(String tagUpDown) {
		// TODO Auto-generated method stub
		if (tagUpDown != null)
			this.stateLable.setText(tagUpDown);

	}

}
