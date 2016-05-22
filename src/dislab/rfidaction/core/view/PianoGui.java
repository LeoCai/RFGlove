package dislab.rfidaction.core.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 钢琴界面
 *
 */
public class PianoGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5204718033784360062L;
	private JLabel[] lb = new JLabel[7];
	private ImageIcon [] imgp = new ImageIcon[7];
	private ImageIcon [] imgr = new ImageIcon[7];
	private File wav0;
	private File wav1;
	private File wav2;
	private File wav3;
	private File wav4;
	private File wav5;
	private File wav6;
	private Clip[] c = new Clip[7];
	private AudioInputStream ais0;
	private AudioInputStream ais1;
	private AudioInputStream ais2;
	private AudioInputStream ais3;
	private AudioInputStream ais4;
	private AudioInputStream ais5;
	private AudioInputStream ais6;

	// private AudioStream as;
	public PianoGui() {
		super();
		// set layout
		GridLayout gridlayout = new GridLayout(1, 7);

		// set panel
		JPanel p = new JPanel();
		p.setLayout(gridlayout);

		// load image
		for(int i=1;i<8;i++){
			imgr[i-1] = new ImageIcon("./img/0_0"+i+".jpg"); 
			imgp[i-1] = new ImageIcon("./img/1_0"+i+".jpg"); 
		}

		// load wav
		wav0 = new File("./wav/piano/1.au");
		wav1 = new File("./wav/piano/2.au");
		wav2 = new File("./wav/piano/3.au");
		wav3 = new File("./wav/piano/4.au");
		wav4 = new File("./wav/piano/5.au");
		wav5 = new File("./wav/piano/6.au");
		wav6 = new File("./wav/piano/7.au");

		try {
			initaudio();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// set label
		lb[0] = new JLabel(imgr[0]);
		lb[1] = new JLabel(imgr[1]);
		lb[2] = new JLabel(imgr[2]);
		lb[3] = new JLabel(imgr[3]);
		lb[4] = new JLabel(imgr[4]);
		lb[5] = new JLabel(imgr[5]);
		lb[6] = new JLabel(imgr[6]);

		for (int i = 0; i < 7; i++) {
			lb[i].setOpaque(true);
		}

		// set frame location
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) (screen.getWidth() / 2 - 350),
				(int)screen.getHeight()/2 - 320);

		// add to frame
		this.add(p, BorderLayout.CENTER);
		this.setTitle("PIANO");
//		this.setResizable(false);
//		this.setUndecorated(true);
		this.setSize(630,500);
		setLocation(0, 0);
		for (int i = 0; i < 7; i++) {
			p.add(lb[i], null);
		}

	}

	private void initaudio() throws Exception {
		ais0 = AudioSystem.getAudioInputStream(wav0);
		ais1 = AudioSystem.getAudioInputStream(wav1);
		ais2 = AudioSystem.getAudioInputStream(wav2);
		ais3 = AudioSystem.getAudioInputStream(wav3);
		ais4 = AudioSystem.getAudioInputStream(wav4);
		ais5 = AudioSystem.getAudioInputStream(wav5);
		ais6 = AudioSystem.getAudioInputStream(wav6);

		c[0] = AudioSystem.getClip();
		c[1] = AudioSystem.getClip();
		c[2] = AudioSystem.getClip();
		c[3] = AudioSystem.getClip();
		c[4] = AudioSystem.getClip();
		c[5] = AudioSystem.getClip();
		c[6] = AudioSystem.getClip();

		c[0].open(ais0);
		c[1].open(ais1);
		c[2].open(ais2);
		c[3].open(ais3);
		c[4].open(ais4);
		c[5].open(ais5);
		c[6].open(ais6);
	}

	public void pressed(int i) {
		System.out.println("pressed");
		lb[i].setIcon(imgp[i]);
		//c[i].setFramePosition(0);
		c[i].start();

	}

	public void released(int i) {
		System.out.println("released");
		lb[i].setIcon(imgr[i]);
		//c[i].stop();
		c[i].setFramePosition(0);
		c[i].stop();
	}
}
