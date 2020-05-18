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

	
	public UI(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.UI;
	}
	
	public void init() {
		for (ISystem s : subsystems) {
			if (s instanceof TouchScreen) {
				((TouchScreen) s).init();
			}
		}
	}
	
}