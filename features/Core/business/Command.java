package business;

/**
 * TODO description
 */
public class Command {
	
	public static final String DIVIDER = "@";
	private Channel channel;
	private Code code;
	
	public Command(String commandString) {
		String[] parts = commandString.split(DIVIDER);
		this.code = new Code(parts[0]);
		this.channel = Channel.parseChannel(parts[1]);
	}
	
	public Command(Channel channel, Code code) {
		this.channel = channel;
		this.code = code;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public Code getCode() {
		return code;
	}

	public String toString() {
		return code.toString() + DIVIDER + channel.toString();
	}
	
}