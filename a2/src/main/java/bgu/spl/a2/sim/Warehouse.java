package bgu.spl.a2.sim;


import java.util.HashMap;

/**
 * represents a warehouse that holds a finite amount of computers
 * and their suspended mutexes.
 * releasing and acquiring should be blocking free.
 */
public class Warehouse {

    private Computer[] computers;

    private SuspendingMutex[] locks;

    public Warehouse(Computer[] computers)
    {
        this.computers = computers;
        locks = new SuspendingMutex[computers.length];

        for (int i = 0 ; i < locks.length ; i++)
        {
            locks[i] = new SuspendingMutex(computers[i]);
        }
    }


	
}
