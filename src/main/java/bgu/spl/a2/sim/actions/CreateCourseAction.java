package bgu.spl.a2.sim.actions;

import java.util.List;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;

public class CreateCourseAction extends Action<Boolean> {

	private CoursePrivateState courseState;
	
	private String courseName;
	
	private int availableSpotsInCourse;
	
	private List<String> prequisites;
	
	public CreateCourseAction (CoursePrivateState courseState, String courseName, int availableSpotsInCourse, List<String> prequisites){
		this.courseState = courseState;
		this.courseName = courseName;
		this.availableSpotsInCourse = availableSpotsInCourse;
		this.prequisites = prequisites;
	}
	
	@Override
	public void start() {
		courseState.setAvailableSpots(availableSpotsInCourse);
		courseState.setPrequisites(prequisites);
		sendMessage(null, courseName, courseState);
		complete(true);
	}

}
