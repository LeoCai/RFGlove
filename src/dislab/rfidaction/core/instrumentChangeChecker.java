package dislab.rfidaction.core;

import java.util.List;

import dislab.rfidaction.core.actionchecker.AbstractActionChecker;

public class instrumentChangeChecker extends AbstractActionChecker {

	private int threhold = 1000;// 钢琴切换竖笛
	private ActionManager actionManager;
	private int threhold2 = 500;
	private int threholdchange = 4500;
	private int pianoCount = 0;
	private int rockCount = 0;
	private boolean readyToRock = false;
	private int goodRightCount;
	private long startTime;

	public instrumentChangeChecker(List<String> tags, ActionManager actionManager) {
		super(tags);
		System.out.println("changeChecker created");
		this.actionManager = actionManager;
		startTime = System.currentTimeMillis();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String checkAction(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		/*
		 * int[] tagUpDownOff = tagRssiEncoder.preBasedEncode(targetTagRssi,
		 * threhold2);
		 */
//		 int goodCount = 0;
//		 for (int i = 0; i < 5; i++) {
//		 if (targetTagRssi[i] > 40) {
//		 goodCount++;
//		 }
//		 }
//		 if (goodCount > 3) {
//		 pianoCount++;
//		 } else {
//		 pianoCount = 0;
//		 }

		if (targetTagRssi[1] > 50 || targetTagRssi[2] > 50) {
			pianoCount++;
		} else {
			pianoCount = 0;
		}
		double cuSecond = 1.0 * (System.currentTimeMillis() - startTime) / 1000000000;

		// 钢琴 切换 鼓
		if (pianoCount >= 10 && actionManager.getCurrentMode() == CommandType.ROCKGAME ) {
			actionManager.exitActionChecker();
			actionManager.selectMode(CommandType.PIANO);
			pianoCount = 0;
		}

		// 鼓 切换 钢琴
		/*
		 * if(index > 3 && targetTagRssi[4] < 4500 && targetTagRssi[5] < 4500 &&
		 * actionManager.getCurrentMode() == CommandType.DRUM) { index = 0;
		 * actionManager.exitActionChecker();
		 * actionManager.selectMode(CommandType.PIANO); }
		 */

		// //鼓 切换 猜拳
		// if(readyToRock && targetTagRssi[5] > 7000 && targetTagRssi[6] >
		// 5000){
		// readyToRock = false;
		// actionManager.exitActionChecker();
		// actionManager.selectMode(CommandType.ROCKGAME);
		// }
		// if(targetTagRssi[5] < 5000 && targetTagRssi[6] > 5000 &&
		// actionManager.getCurrentMode() == CommandType.PIANO){
		// readyToRock = true;
		// }
		// else
		// readyToRock = false;

		if (targetTagRssi[9] > 15000)
			return null;

		// 原代码 钢琴 切换 竖笛
		// int[] tagUpDown = tagRssiEncoder.encode(targetTagRssi, initRssi,
		// threhold);
		// printTagUpDown(tagUpDown, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		// });
		// int count1 = 0;
		// for (int i = 0; i < tagUpDown.length; i++) {
		// if (tagUpDown[i] == 1)
		// count1 += 1;
		// }
		// System.out.println(count);

		// 钢琴 切换 竖笛
		/*
		 * if (actionManager.getCurrentMode() == CommandType.PIANO &&
		 * targetTagRssi[0] > 10000) { actionManager.exitActionChecker();
		 * actionManager.selectMode(CommandType.ClARINET);
		 * 
		 * }
		 */
		// 竖笛 切换 钢琴
		/*
		 * if (actionManager.getCurrentMode() == CommandType.ClARINET && count
		 * >= 7) { actionManager.exitActionChecker();
		 * actionManager.selectMode(CommandType.PIANO);
		 * 
		 * }
		 */
		return null;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
