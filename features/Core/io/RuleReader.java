package io;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import business.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * TODO description
 */
public class RuleReader {
	
	private Map<Channel, List<Rule>> map;
	
	public RuleReader() {
		this.map = new HashMap<Channel, List<Rule>>();
		readRules();
	}
	
	public Map<Channel, List<Rule>> getMap() {
		return map;
	}
	
	private void readRules() {
		try {
	      Scanner reader = new Scanner(new File("./settings/rules.txt"));
	      Channel currentChannel = null;
	      while (reader.hasNextLine()) {
	        String line = reader.nextLine();
	        if (line.length() == 0 || line.charAt(0) == '/')
	        	continue;
	        if (line.charAt(0) == '[') {
	        	currentChannel = Channel.parseChannel(line.substring(1, line.length()-1));
	        	if (map.get(currentChannel) == null)
	        		map.put(currentChannel, new ArrayList<Rule>());
	        } else {
	        	List<Rule> mapData = map.get(currentChannel);
	        	mapData.add(parseRule(line, currentChannel));
	        }
	      }
	      reader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	
	private Rule parseRule(String ruleEnglish, Channel channel) {
		ruleEnglish = ruleEnglish.replace("if", "");
		ruleEnglish = ruleEnglish.trim();
		String parts[] = ruleEnglish.split(" then ");
		String conditions = parts[0];
		conditions = conditions.replace(" and ", "&&");
		conditions = conditions.replace("=", "_EQ_");
		conditions = conditions.replace("!=", "_NE_");
		conditions = conditions.replace("<", "_LT_");
		conditions = conditions.replace(">", "_MT_");
		
		String command = parts[1];
		String[] commandParts = command.split(" at ");
		if (commandParts.length == 1) {
			command += "@" + channel.toString();
		} else {
			command = commandParts[0] + "@" + Channel.parseChannel(commandParts[1]);
		}
		return new Rule(conditions, command);
	}

}