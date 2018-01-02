package bgu.spl.a2.sim.actions;

import java.util.List;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;

public class OpenANewCourse extends Action<Boolean>{
	
	OpenANewCourse(String courseName, int availableSpots, List<String> prequisites){
		CoursePrivateState courseState = new CoursePrivateState();
		
		courseState.setAvailableSpots(availableSpots);
		courseState.setPrequisites(prequisites)
		
		this.sendMessage(this, courseName, courseState);
		
	}

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		
	}

}
