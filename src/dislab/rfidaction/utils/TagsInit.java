package dislab.rfidaction.utils;

import java.util.Arrays;

/**
 * 初始化的rssi
 * 
 * 
 */
public class TagsInit {

	public static double[] initRssiWheel = new double[] { 2596.75, 923.24, 0.0,
			3727.2, 6191.18, 8899.14, 6408.65, 950.2800000000001,
			1623.8799999999999, 1853.9000000000003 };
	public static final double[] initRssiRockGame = new double[] {
			50,50,50,50,50,50,50,50,50,50};
	public static final double[] initRssiInstrument = new double[] { 4802.83,
			2307.2200000000003, 1038.01, 1791.1299999999999,
			2933.4700000000003, 1898.89, 4190.19, 1424.02, 2089.45,
			1581.7599999999998 };
	public static final double[] initRssiMenu = new double[] {
			1794.3700000000001, 577.8, 2366.7699999999995, 4222.460000000001,
			2814.0299999999997, 2457.04, 2411.63, 2660.21, 6717.35,
			7380.540000000001 };
	public static final double[] initRssiPIANO = new double[] {
			3718.6400000000003, 3110.2799999999997, 4003.8, 2413.3499999999995,
			627.08, 2803.65, 4303.96, 1528.0500000000002, 2331.2799999999997,
			648.12 };
	public static final double[] initRssiClarinet = new double[] { 5468.36,
			2323.9900000000002, 2046.0500000000004, 95.39, 1591.75,
			3317.9300000000003, 2441.1600000000008, 2475.0, 6288.6900000000005,
			2171.65 };
	public static final double[] initRssiDrum = new double[] { 3718.6400000000003, 
			3110.2799999999997, 4003.8, 2413.3499999999995,
			627.08, 2803.65, 4303.96, 1528.0500000000002, 2331.2799999999997,
			648.12 };//init drum Rssi
	public static final double[] initRssiSCALE = new double[] {
			1704.9100000000003, 3071.4100000000008, 3006.0600000000004,
			6526.999999999999, 4476.69, 5136.860000000001, 7112.610000000001,
			194.54000000000002, 4125.320000000001, 1491.8899999999996 };
	public static final double[] initRssiRotate = new double[] {
			1477.7099999999998, 2963.58, 3271.44, 5101.930000000001, 3822.16,
			1952.92, 6494.849999999999, 7885.630000000002, 2052.8499999999995,
			11027.73 };
	public static final double[] initRssiMove = new double[] { 3242.53,
			2850.49, 2767.25, 4852.54, 3265.49, 6761.25, 8883.539999999999,
			6370.28, 673.3799999999999, 6966.660000000001 };

	private static TagsInit tagsInit = new TagsInit();

	private double[] tagSumRssis = new double[10];

	private int initNum;

	private int maxInitNum = 10;

	private double[] tagAverageRssi = initRssiMenu;

	private TagsInit() {

	}

	public static TagsInit getInstance() {
		// TODO Auto-generated method stub
		return tagsInit;
	}

	/**
	 * 初始化基准rssi
	 * 
	 * @param targetTagRssi
	 * @return
	 */
	public boolean initing(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		for (int i = 0; i < targetTagRssi.length; i++) {
			tagSumRssis[i] += targetTagRssi[i];
		}
		initNum += 1;
		if (initNum < maxInitNum)
			return true;
		else {
			for (int i = 0; i < tagSumRssis.length; i++) {
				tagAverageRssi[i] = tagSumRssis[i] / initNum;
			}
			System.out.println(Arrays.toString(tagAverageRssi));
			tagSumRssis = new double[10];
			initNum = 0;
			return false;
		}
	}

	public double[] getTagAverageRssi() {
		return tagAverageRssi;
	}

	/**
	 * 设置被初始化的rssi数组
	 * 
	 * @param tagAverageRssi
	 */
	public void setTagAverageRssi(double[] tagAverageRssi) {
		this.tagAverageRssi = tagAverageRssi;
	}

}
