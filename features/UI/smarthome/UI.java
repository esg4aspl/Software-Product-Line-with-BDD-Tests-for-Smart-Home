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
	private boolean outputMode = false;
	private char c;
	
	public UI(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.UI;
	}
	
	public void init() {
		createConsole();
		
		List<Command> allCommands = parentSystem.render();
		println("Available Commands:");
		for (Command c : allCommands) {
			println(c.toString());
		}

		println("Enter the command you want to run:");
	}
	
	
	private void handleInput(String input) {
		if (input.contentEquals("QUIT"))
			System.exit(0);
		
		println("->Running: " + (new Command(input)).toString());
		publisher.publish(input);
		println("Enter the command you want to run:");
	}
	
	private String readLine() {
		if (c == '\n') {
			c = ' ';
			String[] parts = console.getText().split("\n");
			String input = parts[parts.length-1].trim();
			System.out.println("\nUI Input: " + input);
			return input;
		} else {	
			return null;
		}
	}
	
	private void createConsole() {
		final JFrame frame = new JFrame(); 
        frame.setSize(600, 600);
        frame.setLocation(20, 50);

		console = new JTextArea(1, 1);
	    
        console.addKeyListener(new KeyListener() {
            @Override 
            public void keyPressed(KeyEvent e) {  
                synchronized (frame) {
                	if (outputMode)
                		return;
                	c = e.getKeyChar();
					try {
						String input = readLine();
						if (input == null)
							return;
						handleInput(input);
					} catch (Exception e1) {
						println(e1.getMessage());
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
	
	private void println(String str) {
		outputMode = true;
		console.append(str);
		console.append("\n");
		console.setCaretPosition(console.getDocument().getLength());
		outputMode = false;
	}

}