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

/**
 * TODO description
 */
public class RSA extends AbstractSystem {
	

	public RSA(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.RSA;
	}
	
	
}