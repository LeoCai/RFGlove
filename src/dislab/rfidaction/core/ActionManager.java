package dislab.rfidaction.core;

import java.util.List;

import dislab.rfidaction.core.actionchecker.AbstractActionChecker;
import dislab.rfidaction.core.actionchecker.ActionChecker;
import dislab.rfidaction.core.actionchecker.MenuActionChecker;
import dislab.rfidaction.core.actionchecker.MoveActionChecker;
import dislab.rfidaction.core.actionchecker.PianoActionChecker;
import dislab.rfidaction.core.actionchecker.ClarinetActionChecker;
import dislab.rfidaction.core.actionchecker.DrumActionChecker;//drum
import dislab.rfidaction.core.actionchecker.RockGameChecker;
import dislab.rfidaction.core.actionchecker.RotateActionChecker;
import dislab.rfidaction.core.actionchecker.ScaleActionChecker;
import dislab.rfidaction.core.actionchecker.WheelActionChecker;
import dislab.rfidaction.utils.TagsInit;

/**
 * 集中管理所有action
 * 
 */
public class ActionManager {

	private AbstractActionChecker drumActionChecker;
	private AbstractActionChecker clarinetActionChecker;
	private AbstractActionChecker pinaoActionChecker;
	private AbstractActionChecker scaleActionChecker;
	private AbstractActionChecker rotateActionChecker;
	private AbstractActionChecker moveActionChecker;
	private AbstractActionChecker rockGameChecker;
	private AbstractActionChecker wheelActionChecker;

	private AbstractActionChecker instrumentChangeChecker;
	private boolean instrumentChangeCheckerFlag = true;

	private AbstractActionChecker menuActionChecker;

	private ActionChecker actionChecker;

	private TagsInit tagsInit;
	private ChartDrawer chartDrawer;
	/**
	 * 当前的模式
	 */
	private CommandType currentMode;

	public ActionManager(List<String> tags) {

		drumActionChecker = new DrumActionChecker(tags);
		clarinetActionChecker = new ClarinetActionChecker(tags);
		pinaoActionChecker = new PianoActionChecker(tags);
		scaleActionChecker = new ScaleActionChecker(tags);
		menuActionChecker = new MenuActionChecker(tags, this);
		rotateActionChecker = new RotateActionChecker(tags);
		moveActionChecker = new MoveActionChecker(tags);
		instrumentChangeChecker = new instrumentChangeChecker(tags, this);
		rockGameChecker = new RockGameChecker(tags);
		wheelActionChecker = new WheelActionChecker(tags);
		actionChecker = rockGameChecker;
		currentMode = CommandType.ROCKGAME;

		this.tagsInit = TagsInit.getInstance();
		this.chartDrawer = ChartDrawer.getInstance();

		clarinetActionChecker.setInitRssi(TagsInit.initRssiClarinet);
		pinaoActionChecker.setInitRssi(TagsInit.initRssiPIANO);
		drumActionChecker.setInitRssi(TagsInit.initRssiDrum);
		scaleActionChecker.setInitRssi(TagsInit.initRssiSCALE);
		menuActionChecker.setInitRssi(TagsInit.initRssiMenu);
		rotateActionChecker.setInitRssi(TagsInit.initRssiRotate);
		moveActionChecker.setInitRssi(TagsInit.initRssiMove);
		instrumentChangeChecker.setInitRssi(TagsInit.initRssiInstrument);
		rockGameChecker.setInitRssi(TagsInit.initRssiRockGame);
		wheelActionChecker.setInitRssi(TagsInit.initRssiWheel);

		tagsInit.setTagAverageRssi(TagsInit.initRssiClarinet);//focus on

		// TODO Auto-generated constructor stub
	}

	/**
	 * 监测rfid的动作
	 * 
	 * @param targetTagRssi
	 */
	public void checkAction(double[] targetTagRssi) {
		// TODO Auto-generated method stub
		if (actionChecker != null) {
			String tagUpDown = actionChecker.checkAction(targetTagRssi);
			chartDrawer.showStateInfo(tagUpDown);
		}
		else{
			selectMode(CommandType.PIANO);//选择默认模式
		}
//		menuActionChecker.checkAction(targetTagRssi); 检查菜单
		//
		if (instrumentChangeCheckerFlag) {
			instrumentChangeChecker.checkAction(targetTagRssi);
		}
	}

	/**
	 * 选择模式
	 * 
	 * @param commandType
	 */
	public void selectMode(CommandType commandType) {
		switch (commandType) {
		case PIANO:
			actionChecker = pinaoActionChecker;
			tagsInit.setTagAverageRssi(TagsInit.initRssiPIANO);
			instrumentChangeCheckerFlag = true;
			break;
		case ClARINET:
			actionChecker = clarinetActionChecker;
			tagsInit.setTagAverageRssi(TagsInit.initRssiClarinet);
			instrumentChangeCheckerFlag = true;
			break;
		case DRUM:
			actionChecker = drumActionChecker;
			tagsInit.setTagAverageRssi(TagsInit.initRssiDrum);
			instrumentChangeCheckerFlag = true;
			break;
		case SCALE:
			tagsInit.setTagAverageRssi(TagsInit.initRssiSCALE);
			actionChecker = scaleActionChecker;
			break;
		case ROTATE:
			tagsInit.setTagAverageRssi(TagsInit.initRssiRotate);
			actionChecker = rotateActionChecker;
			break;
		case MOVE:
			tagsInit.setTagAverageRssi(TagsInit.initRssiMove);
			actionChecker = moveActionChecker;
			break;
		case WHEEL:
			tagsInit.setTagAverageRssi(TagsInit.initRssiWheel);
			actionChecker = wheelActionChecker;
			break;
		case ROCKGAME:
			tagsInit.setTagAverageRssi(TagsInit.initRssiRockGame);
			actionChecker = rockGameChecker;
			break;
		default:
			break;
		}
		currentMode = commandType;
		// ChartDrawer.getInstance().setIniting(true);
		System.out.println("selectMode:" + commandType);
	}

	/**
	 * 退出当前的动作监测器
	 */
	public void exitActionChecker() {
		// TODO Auto-generated method stub
		if (actionChecker != null) {
			actionChecker.exit();
			actionChecker = null;
			tagsInit.setTagAverageRssi(TagsInit.initRssiMenu);
			// ChartDrawer.getInstance().setIniting(true);
			instrumentChangeCheckerFlag = false;
		}

	}

	public CommandType getCurrentMode() {
		return currentMode;
	}

}
