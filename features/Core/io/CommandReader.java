package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import business.*;

/**
 * TODO description
 */
public class CommandReader {
	
	private static CommandReader singleCommandReader = null;
	private Map<Channel, List<Command>> map;
	
	private CommandReader() {
		this.map = new HashMap<Channel, List<Command>>();
		readCommands();
	}
	
	public Map<Channel, List<Command>> getMap() {
		return map;
	}
	
    public static CommandReader getInstance() 
    { 
        if (singleCommandReader == null) 
        	singleCommandReader = new CommandReader(); 
  
        return singleCommandReader; 
    } 
	
	private void readCommands() {
		try {
	      Scanner reader = new Scanner(new File("./settings/commands.txt"));
	      Channel currentChannel = null;
	      while (reader.hasNextLine()) {
	        String line = reader.nextLine();
	        if (line.length() == 0 || line.charAt(0) == '/')
	        	continue;
	        if (line.charAt(0) == '[') {
	        	currentChannel = Channel.parseChannel(line.substring(1, line.length()-1));
	        	if (map.get(currentChannel) == null)
	        		map.put(currentChannel, new ArrayList<Command>());
	        } else {
	        	List<Command> mapData = map.get(currentChannel);
	        	mapData.add(new Command(line + "@" + currentChannel));
	        }
	      }
	      reader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}

}