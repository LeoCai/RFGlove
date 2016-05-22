package dislab.rfidaction.command;

import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;
import dislab.rfidaction.core.view.DrumGui;

/**
 * 鼓命令
 *
 */
public class DrumCommand extends Command {
	static DrumGui drumGui = new DrumGui();

	public DrumCommand(CommandInfo commandInfo) {
		// TODO Auto-generated constructor stub
//		System.out.println("DrumGui reached");
		if (!drumGui.isVisible()) {
			drumGui.setVisible(true);
		}
		setCommandType(CommandType.DRUM);
		setName("Drum");
		this.commandinfo = commandInfo;
	}

	public static final int command1 = 0;
	public static final int command2 = 1;
	public static final int command3 = 3;

	@Override
	public void excute() {
		// TODO Auto-generated method stub
//		System.out.println("Action:" + getName() + "," + commandinfo);

		if (commandinfo.getEventType() == CommandInfo.PRESS) {
			drumGui.pressed(commandinfo.getValue());

		} else if (commandinfo.getEventType() == CommandInfo.RELEASE) {
			drumGui.released(commandinfo.getValue());
		}

	}

	public static void quitGui() {
		// TODO Auto-generated method stub
		if (drumGui.isVisible()) {
			drumGui.setVisible(false);
		}
	}

}
