package business;

/**
 * TODO description
 */
public interface ISubscriber {
	
	public void start();
	public void stop();
	public void interrupt();
	public void kill();

}