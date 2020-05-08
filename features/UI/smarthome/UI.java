package smarthome;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import business.*;

/**
 * TODO description
 */
public class UI extends AbstractSystem {
	
	private JTextArea console;
	private boolean started = false;
	private boolean authMode = true;
	
	private Map<String, Boolean> authDevices;
	
	public UI(ISystem parentSystem) {
		super(parentSystem);
		authDevices = new HashMap<String, Boolean>();
	}
	
	public Channel getChannel() {
		return Channel.UI;
	}
	
	public void init() {
		createConsole();
		
		List<Command> allCommands = parentSystem.render();
		console.append("Available Commands:\n");
		for (Command c : allCommands) {
			console.append(c.toString() + "\n");
		}
		startAuthentication();
	}
	
	private void startAuthentication() {
		console.append("Press ENTER to authenticate.\n");
	}
	
	private void authenticate(String input) {
		checkAuthentication();
	}
	
	private void checkAuthentication() {
		boolean result = true;
		for (Map.Entry<String, Boolean> entry : authDevices.entrySet()) {
			if (entry.getValue() != true) {
				result = false;
				break;
			}
		}
		if (result == true) {
			authMode = false;
		}
	}
	
	private void handleInput(String input) {
		if (!started) {
			return;
		}
		console.append("->Running: " + (new Command(input)).toString() + "\n");
		publisher.publish(input);
		console.append("Enter the command you want to run:\n");
		console.setCaretPosition(console.getDocument().getLength());
	}
	

	
	private void createConsole() {
		final JFrame frame = new JFrame(); 
        frame.setSize(600, 600);
        frame.setLocation(20, 50);

		console = new JTextArea(1, 1);
	    console.setBackground(Color.BLACK);
	    console.setForeground(Color.LIGHT_GRAY);
	    console.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
	    
        console.addKeyListener(new KeyListener() {
            @Override 
            public void keyPressed(KeyEvent e) {  
                synchronized (frame) {
					try {
						if (authMode) {
							if (e.getKeyChar() == '\n') {
								String[] parts = console.getText().split("\n");
								String input = parts[parts.length-1].trim().replace("\n", "");
								if (input.contentEquals("QUIT")) {
									System.exit(0);
								}
								authenticate(input);
							}
						} else {
							if (e.getKeyChar() == '\n') {
								if (!started) {
									console.append("Enter the command you want to run: \n");
									started = true;
								} else {
									String[] parts = console.getText().split("\n");
									String input = parts[parts.length-1].trim().replace("\n", "");
									if (input.contentEquals("QUIT")) {
										System.exit(0);
									}
									handleInput(input);
								}
							}
						}
						
					} catch (Exception e1) {
						console.append(e1.getMessage());
					}
                }  
            }  
            @Override 
            public void keyReleased(KeyEvent e) {  
            }  
            @Override 
            public void keyTyped(KeyEvent e) {  
            }  
        });
        
        JScrollPane scrollPane = new JScrollPane(console);
	    frame.add(scrollPane);
        frame.setVisible(true);
	}

}