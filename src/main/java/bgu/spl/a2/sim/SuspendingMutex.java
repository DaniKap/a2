package bgu.spl.a2.sim;
import bgu.spl.a2.Promise;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * this class is related to {@link Computer}
 * it indicates if a computer is free or not
 * 
 * Note: this class can be implemented without any synchronization. 
 * However, using synchronization will be accepted as long as the implementation is blocking free.
 *
 */
public class SuspendingMutex {

	private Computer computer;

	private AtomicBoolean lock;

	private Queue<Promise<Computer>> promiseQueue;


	/**
	 * Constructor
	 * @param computer
	 */
	public SuspendingMutex(Computer computer){
		this.computer = computer;
		this.lock = new AtomicBoolean(false);
		promiseQueue = new ConcurrentLinkedQueue<Promise<Computer>>();

	}
	/**
	 * Computer acquisition procedure
	 * Note that this procedure is non-blocking and should return immediatly
	 * 
	 * @return a promise for the requested computer
	 */
	public Promise<Computer> down(){
		if(lock.compareAndSet(false, true))
		{
			return null;
		}
		return new Promise<Computer>();

	}
	/**
	 * Computer return procedure
	 * releases a computer which becomes available in the warehouse upon completion
	 */
	public void up(){
		lock.set(false);
	}
}
