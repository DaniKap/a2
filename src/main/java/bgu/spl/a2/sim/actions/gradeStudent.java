package bgu.spl.a2.sim.actions;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;
import bgu.spl.a2.sim.privateStates.StudentPrivateState;

public class gradeStudent extends Action<Integer> {

	private String student;
	
	private String course;
	
	private int grade;
	
	private CoursePrivateState courseState;
	
	private StudentPrivateState studentState;
	
	public gradeStudent(String student, String course, int grade, StudentPrivateState studentState, CoursePrivateState courseState) {
		this.student = student;
		this.course = course;
		this.courseState = courseState;
		this.studentState = studentState;
	}
	
	@Override
	protected void start() {
		Action<Boolean> action1 = new ParticipateInCourse()
		sendMessage

	}

}
