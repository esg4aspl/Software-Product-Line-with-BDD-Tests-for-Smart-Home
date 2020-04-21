package smarthome;

import java.util.List;

/**
 * TODO description
 */
public interface ISystem {
	
	public void respond(String code);
	public List<String> render();
	
	public List<ISystem> getSubsystems();
	public ISystem getParentSystem();
	public String getType();

}