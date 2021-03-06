package business;

import java.util.List;
import business.Channel;

/**
 * TODO description
 */
public interface ISystem {
	
	public void respond(Command command) throws Exception;
	public List<Command> render();
	
	public List<ISystem> getSubsystems();
	public ISystem getParentSystem();
	public Channel getChannel();
	
	public void addSubsystem(ISystem subsystem);
	
	public void kill();

}