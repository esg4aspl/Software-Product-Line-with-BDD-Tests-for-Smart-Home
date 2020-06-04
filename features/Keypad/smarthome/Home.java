package smarthome;

import business.OutputBag;

/**
 * TODO description
 */
public class Home {
	public void authenticate(String device) {
		original(device);
		
		if (device.equals("123456")) {
			OutputBag.getInstance().addOutput("Authenticated with keypad!");
		}
	}
}