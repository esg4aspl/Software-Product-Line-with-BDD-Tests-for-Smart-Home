package smarthome;

import business.*;

/**
 * TODO description
 */
public class ManualIllumination extends AbstractSystem {
	
	public ManualIllumination(ISystem parentSystem) {
		super(parentSystem);
		commands.add(new Command("TURN_ON" + Code.DIVIDER + "Kitchen" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_ON" + Code.DIVIDER + "Living Room" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_ON" + Code.DIVIDER + "Bathroom" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_ON" + Code.DIVIDER + "Bedroom" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_ON" + Code.DIVIDER + "All" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_OFF" + Code.DIVIDER + "Kitchen" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_OFF" + Code.DIVIDER + "Living Room" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_OFF" + Code.DIVIDER + "Bathroom" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_OFF" + Code.DIVIDER + "Bedroom" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
		commands.add(new Command("TURN_OFF" + Code.DIVIDER + "All" + Command.DIVIDER + "LIGHT_MANAGEMENT"));
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_ILLUMINATION;
	}
	
}