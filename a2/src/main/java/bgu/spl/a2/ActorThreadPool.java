package bgu.spl.a2;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * represents an actor thread pool - to understand what this class does please
 * refer to your assignment.
 *
 * Note for implementors: you may add methods and synchronize any of the
 * existing methods in this class *BUT* you must be able to explain why the
 * synchronization is needed. In addition, the methods you add can only be
 * private, protected or package protected - in other words, no new public
 * methods
 */
public class ActorThreadPool {

	private Thread[] threads;
	
	private ConcurrentHashMap<String, PrivateState> actorTable;
	
	private ConcurrentHashMap<String, ConcurrentLinkedQueue<Action<?>>> actorQueues;
	
	private ConcurrentHashMap<String, AtomicBoolean> actorLocks;

	private AtomicInteger activeQueues;
	
	/**
	 * creates a {@link ActorThreadPool} which has nthreads. Note, threads
	 * should not get started until calling to the {@link #start()} method.
	 *
	 * Implementors note: you may not add other constructors to this class nor
	 * you allowed to add any other parameter to this constructor - changing
	 * this may cause automatic tests to fail..
	 *
	 * @param nthreads
	 *            the number of threads that should be started by this thread
	 *            pool
	 */
	public ActorThreadPool(int nthreads) {
		// Initialize all the hashMaps
		Thread[] threads = new Thread[nthreads];
		actorTable = new ConcurrentHashMap<String, PrivateState>();
		actorQueues = new ConcurrentHashMap<String, ConcurrentLinkedQueue<Action<?>>>();
		actorLocks = new ConcurrentHashMap<String, AtomicBoolean>();
		activeQueues.set(0);
		
		// Initialize the threads with run function
		for (int i = 0 ; i < nthreads ; i++)
		{
			threads[i] = new Thread(()->
				{
					AtomicBoolean isLive = new AtomicBoolean(true);
					while(isLive.get())
					{
						try
						{
							// If there are no available queues put the thread on wait
							while (activeQueues.compareAndSet(0, 0))
							{
								this.wait();
							}
							for (ConcurrentHashMap.Entry<String, AtomicBoolean> entry : actorLocks.entrySet())
							{
								String id = entry.getKey();
								// lock the actor
								if (entry.getValue().compareAndSet(false, true))
								{
									activeQueues.decrementAndGet();
									Action<?> action = actorQueues.get(id).poll();
									if (action != null)
									{
										action.handle(this, id, actorTable.get(id));
									}
									actorLocks.get(id).set(false);
								}
							}
						}
						catch (ConcurrentModificationException e)
						{
							continue;
						}
						catch (InterruptedException e)
						{
							isLive.compareAndSet(true, false);
						}
					}
				});
		}
	}

	/**
	 * getter for actors
	 * @return actors
	 *
	 */
	public Map<String, PrivateState> getActors(){

		return actorTable;
	}
		
	/**
	 * getter for actor's private state
	 * @param actorId actor's id
	 * @return actor's private state
	 *
	 */
	public PrivateState getPrivateState(String actorId){
		
		return actorTable.get(actorId);
	}


	/**
	 * submits an action into an actor to be executed by a thread belongs to
	 * this thread pool
	 *
	 * @param action
	 *            the action to execute
	 * @param actorId
	 *            corresponding actor's id
	 * @param actorState
	 *            actor's private state (actor's information)
	 */
	public void submit(Action<?> action, String actorId, PrivateState actorState){
		// if actor is absent
		actorLocks.putIfAbsent(actorId, new AtomicBoolean(false));
		actorTable.putIfAbsent(actorId, actorState);
		actorQueues.putIfAbsent(actorId, new ConcurrentLinkedQueue<Action<?>>());
		
		if (action == null)
		{
			return;
		}
		
		// If actor is free
		while (actorLocks.get(actorId).compareAndSet(false, true))
		{
			activeQueues.incrementAndGet();
			this.notifyAll();
			actorQueues.get(actorId).add(action);
			actorLocks.get(actorId).set(false);
		}
	}

	/**
	 * closes the thread pool - this method interrupts all the threads and waits
	 * for them to stop - it is returns *only* when there are no live threads in
	 * the queue.
	 *
	 * after calling this method - one should not use the queue anymore.
	 *
	 * @throws InterruptedException
	 *             if the thread that shut down the threads is interrupted
	 */
	public void shutdown() throws InterruptedException {
		
		for (int i = 0 ; i < threads.length ; i++)
		{
			threads[i].interrupt();
			threads[i].join();
		}
	}

	/**
	 * start the threads belongs to this thread pool
	 */
	public void start() {
		for (int i = 0 ; i < threads.length ; i++)
		{
			threads[i].start();
		}
	}
	 
}
