package bgu.spl.a2.sim.actions;

import java.util.LinkedList;
import java.util.List;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;
import bgu.spl.a2.sim.privateStates.StudentPrivateState;

public class ParticipateInCourse extends Action<Boolean> {
	
	private String student;
	
	private String course;
	
	private int grade;
	
	private CoursePrivateState courseState;
	
	private StudentPrivateState studentState;
	
	ParticipateInCourse(String student, String course, int grade, StudentPrivateState studentState, CoursePrivateState courseState) {
		this.student = student;
		this.course = course;
		this.courseState = courseState;
		this.studentState = studentState;
	}
	
	public void start() {
		Action<Boolean> action1 = new AddStudentToCourse(student, courseState);
		Action<Integer> action2 = new gradeStudent(studentState, course, grade);
		List<Action<?>> actions = new LinkedList<Action<?>>();
		actions.add(action1);
		actions.add(action2);
		
		this.sendMessage(action1, course, courseState);
		this.sendMessage(action2, student, studentState);
		then(actions, () -> {
			if (action1.getResult().get())
			{
				complete(true);
			}	
		});
		
		
	}

}
