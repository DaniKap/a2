package bgu.spl.a2.sim.actions;

import bgu.spl.a2.Action;
import bgu.spl.a2.PrivateState;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;

public class AddStudentToCourse extends Action<Boolean>{
	
	private String student;
	
	private CoursePrivateState courseState;
	
	public AddStudentToCourse(String student, CoursePrivateState courseState) {
		this.student = student;
		this.courseState = courseState;
	}
	
	public void start() {
		courseState.addRecord(this.name);
		Boolean result = courseState.addStudent(student);
		this.complete(result);
	}

}
