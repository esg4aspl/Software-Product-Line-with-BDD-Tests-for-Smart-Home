package smarthome;

import business.OutputBag;

/**
 * TODO description
 */
public class Home {
	public void authenticate(String device) {
		original(device);
		
		if (device.equals("n 8475yt8374c3b8 34bc384ygcn 3u4ry")) {
			OutputBag.getInstance().addOutput("Authenticated with retina scanner!");
		}
	}
}