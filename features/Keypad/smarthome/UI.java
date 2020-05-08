package smarthome;

/**
 * TODO description
 */
public class UI {

	private void authenticate(String input) {
		if (input.equals("12345")) {
			authMode = false;
		} else {
			console.append("Incorrect password. Try again:\n");
			
		}
	}
	
}