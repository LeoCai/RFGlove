package dislab.rfidaction.core.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class RockGameGui extends ActionGui {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1587356806437584220L;

	public static final int STONE = 0;
	public static final int BU = 1;
	public static final int JIANDAO = 2;

	ImageIcon iconBu = new ImageIcon("./img/bu.png");
	ImageIcon iconStone = new ImageIcon("./img/shitou.png");
	ImageIcon iconJiandao = new ImageIcon("./img/jiandao.png");

	private JLabel jLabel;

	public RockGameGui() {
		super();
		setTitle("StoneGameGui");
		setSize(512, 500);
		setLocation(0, 0);
		setLayout(new BorderLayout());
		jLabel = new JLabel(iconBu);
		add(jLabel);
		JButton btnBu = new JButton("btnBu");
		btnBu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setImage(BU);
			}
		});
		JButton btnJiandao = new JButton("btnJiandao");
		btnJiandao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setImage(JIANDAO);
			}
		});
		JButton btnStone = new JButton("iconStone");
		btnStone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setImage(STONE);
			}
		});
		// add(btnBu);
		// add(btnJiandao);
		// add(btnStone);
	}

	public void setImage(int type) {
		switch (type) {
		case STONE:
			jLabel.setIcon(iconStone);

			break;
		case BU:
			jLabel.setIcon(iconBu);
			break;
		case JIANDAO:
			jLabel.setIcon(iconJiandao);

		default:
			break;
		}
	}

	public static void main(String args[]) {
		RockGameGui stoneGameGui = new RockGameGui();
		stoneGameGui.setVisible(true);
	}

}
