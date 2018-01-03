package bgu.spl.a2.sim.actions;

import java.util.LinkedList;

import java.util.List;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;
import bgu.spl.a2.sim.privateStates.StudentPrivateState;

public class AddCourseToStudent extends Action<Integer> {

	private String student;
	
	private String course;
	
	private int grade;
	
	private CoursePrivateState courseState;
	
	private StudentPrivateState studentState;
	
	public AddCourseToStudent (String student, String course, int grade, StudentPrivateState studentState, CoursePrivateState courseState) {
		this.student = student;
		this.course = course;
		this.courseState = courseState;
		this.studentState = studentState;
		this.grade = grade;
	}
	
	@Override
	protected void start() {
		
		sendMessage(t)
	}

}
