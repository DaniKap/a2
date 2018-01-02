package bgu.spl.a2;
/**
 * Describes a monitor that supports the concept of versioning - its idea is
 * simple, the monitor has a version number which you can receive via the method
 * {@link #getVersion()} once you have a version number, you can call
 * {@link #await(int)} with this version number in order to wait until this
 * version number changes.
 *
 * you can also increment the version number by one using the {@link #inc()}
 * method.
 *
 * Note for implementors: you may add methods and synchronize any of the
 * existing methods in this class *BUT* you must be able to explain why the
 * synchronization is needed. In addition, the methods you add can only be
 * private, protected or package protected - in other words, no new public
 * methods
 */

/**
 * @author kaplanda
 * 
 * DONE.
 *
 */
public class VersionMonitor {

	private int version_ = 0;
	/**
	 * This method needs to be synchronized so no other thread can change the version before it returns 
	 * @return version_
	 */
    public synchronized int getVersion() {
        return version_;
    }
    
    /**
     * This method needs to be synchronized so increasement won't happen twice before notifying
     * possibly livelocking in await()
     */
    public synchronized void inc() {
        version_ += 1;
        notifyAll();
    }

    /**
     * This method should'nt be synchronized because we want other threads to access the object and change it
     * and then notify this thread.
     * @param version
     * @throws InterruptedException
     */
    public void await(int version) throws InterruptedException {
        while(version_ != version)
        {
        	wait();
        }
    }
}
