package smarthome;

import business.OutputBag;

/**
 * TODO description
 */
public class Home {
	public void authenticate(String device) {
		original(device);
		
		if (device.equals("gc46tr736t4r6dtux")) {
			OutputBag.getInstance().addOutput("Authenticated with fingerprint reader!");
		}
	}
}