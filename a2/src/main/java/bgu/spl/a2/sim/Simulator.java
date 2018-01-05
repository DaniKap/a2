/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgu.spl.a2.sim;
import bgu.spl.a2.Action;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Queue;
import bgu.spl.a2.ActorThreadPool;
import bgu.spl.a2.PrivateState;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;


/**
 * A class describing the simulator for part 2 of the assignment
 */
public class Simulator {

	
	public static ActorThreadPool actorThreadPool;

	public static void start() {
	}

	public static void attachActorThreadPool(ActorThreadPool myActorThreadPool){
		actorThreadPool = myActorThreadPool;
	}
	
	/**
	* shut down the simulation
	* returns list of private states
	*/
	public static HashMap<String,PrivateState> end(){
		return null;
	}
	
	
	public static int main(String [] args){
		// In case we didn't receive a json file
		if (args.length <= 1)
			return 1;

		Gson gson = new Gson();

		try {
			FileReader reader = new FileReader(args[1]);
			JsonReader jsonReader = gson.newJsonReader(reader);
			JsonFormat runFormat = gson.fromJson(jsonReader, JsonFormat.class);
			String afterExecution = gson.toJson(runFormat);
			System.out.println(afterExecution);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private class JsonFormat {

		private int threads;

		@SerializedName("Computers")
		private Computer[] computers;

		@SerializedName("Phase 1")
		private Queue<Action> phase1;

		@SerializedName("Phase 2")
		private Queue<Action> phase2;

		@SerializedName("Phase 3")
		private Queue<Action> phase3;

		public JsonFormat() {}
	}
}
