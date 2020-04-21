package smarthome;

import java.util.List;
import java.util.ArrayList;

/**
 * TODO description
 */
public abstract class AbstractSystem implements ISystem {
	
	protected List<ISystem> subsystems;
	protected ISystem parentSystem;
	
	public AbstractSystem() {
		this(null);
	}
	
	public AbstractSystem(ISystem parentSystem) {
		this.parentSystem = parentSystem;
		this.subsystems = new ArrayList<ISystem>();
	}
	
	public abstract void respond(String code);
	public abstract List<String> render();
	
	public List<ISystem> getSubsystems() {
		return subsystems;
	}
	
	public ISystem getParentSystem() {
		return parentSystem;
	}
	
	public String getType() {
		return "NORMAL";
	}

}