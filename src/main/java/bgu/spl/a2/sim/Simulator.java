/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgu.spl.a2.sim;
import java.util.HashMap;
import bgu.spl.a2.ActorThreadPool;
import bgu.spl.a2.PrivateState;
import com.google.gson.*;


/**
 * A class describing the simulator for part 2 of the assignment
 */
public class Simulator {

	
	public static ActorThreadPool actorThreadPool;

	private static int numberOfThreads(JSON);

	private static Queue<Action<?>> phase(JSON, int phase);


	public static void start()
	{
		Gson gson = new Gson();
	}

	/**
	* Begin the simulation Should not be called before attachActorThreadPool()
	*/
    public static void start(){

    }
	
	/**
	* attach an ActorThreadPool to the Simulator, this ActorThreadPool will be used to run the simulation
	* 
	* @param myActorThreadPool - the ActorThreadPool which will be used by the simulator
	*/
	public static void attachActorThreadPool(ActorThreadPool myActorThreadPool){
		this.actorThreadPool = myActorThreadPool;
	}
	
	/**
	* shut down the simulation
	* returns list of private states
	*/
	public static HashMap<String,PrivateState> end(){
		//TODO: replace method body with real implementation
		throw new UnsupportedOperationException("Not Implemented Yet.");
	}
	
	
	public static int main(String [] args){
		// In case we dint receive a json file
		if (args <= 1)
			return 1;

		JSON

	}
}
