package business;

import java.util.List;
import business.Channel;

/**
 * TODO description
 */
public interface ISystem {
	
	public void respond(Code code);
	public List<Code> render();
	
	public List<ISystem> getSubsystems();
	public ISystem getParentSystem();
	public Channel getChannel();

}