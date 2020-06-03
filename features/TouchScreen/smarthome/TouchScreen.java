package smarthome;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import business.AbstractSystem;
import business.Channel;
import business.Command;
import business.ECode;
import business.ISystem;
import business.UIBag;

/**
 * TODO description
 */
public class TouchScreen extends AbstractSystem {
	
	
	private JTextArea console;
	private boolean outputMode = false;
	private char c;

	public TouchScreen(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.TOUCH_SCREEN;
	}
	
	public void init() {
//		createConsole();
//		
//		List<Command> allCommands = parentSystem.getParentSystem().render();
//		println("Available Commands:");
//		for (Command c : allCommands) {
//			println(c.toString());
//		}
//
//		println("Enter the command you want to run:");
	}
	
	@Override
	public void respond(Command command) {
		if (command.getCode().getECode() == ECode.TOUCH) {
			UIBag.getInstance().addMessage(getChannel() + " responding to " + command.getCode());
			output(getChannel() + " responding to " + command.getCode());
		}
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
			//output("\nUI Input: " + input);
			respond(new Command("TOUCH=Input@TOUCH_SCREEN"));
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