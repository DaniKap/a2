package bgu.spl.a2;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * an abstract class that represents private states of an actor
 * it holds actions that the actor has executed so far 
 * IMPORTANT: You can not add any field to this class.
 * 
 * Check whether i need to synchronize this class of not
 * 
 */
public abstract class PrivateState {
	
	// holds the actions' name what were executed
	private List<String> history;
	private AtomicBoolean lock;
	
	/**
	 * @return
	 */
	public List<String> getLogger(){
		return history;
	}
	
	/**
	 * @param actionName
	 */
	public void addRecord(String actionName){
		if (null != actionName)
		{
			if (lock.compareAndSet(false, true))
			{
				history.add(actionName);
				lock.set(false);
			}
		}
	}
	
	
}
