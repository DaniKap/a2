package bgu.spl.a2.sim.actions;

import java.util.List;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;
import bgu.spl.a2.sim.privateStates.DepartmentPrivateState;

public class OpenANewCourse extends Action<Boolean>{
	
	private String courseName;
	
	private String department;
	
	private int availableSpots;
	
	private List<String> prequisites;
	
	
	OpenANewCourse(String courseName, int availableSpots, List<String> prequisites, String department){
		this.courseName = courseName;
		this.department = department;
		this.availableSpots = availableSpots;
		this.prequisites = prequisites;
		
	}

	@Override
	protected void start() {
		CoursePrivateState courseState = new CoursePrivateState();
		courseState.setAvailableSpots(availableSpots);
		courseState.setPrequisites(prequisites);
		
		then(new LinkedList<Action<Boolean>>(), ()->
		{
			complete(true);
		});
		sendMessage(null, courseName, courseState);
	}

}
