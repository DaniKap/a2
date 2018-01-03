package bgu.spl.a2.sim.actions;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;
import bgu.spl.a2.sim.privateStates.StudentPrivateState;

public class RemoveStudentFromCourse extends Action<Boolean> {

	private StudentPrivateState studentState;
	
	private String student;
	
	private String course;
	
	private CoursePrivateState courseState;
	
	public Unregister(StudentPrivateState studentState, String student, String course, CoursePrivateState courseState) {
		this.student = student;
		this.course = course;
		this.studentState = studentState;
		this.courseState = courseState;
	}
	
	public void start() {
		if (courseState.containsStudent(student))
		{
			courseState.removeStudent(student);
			Action<Boolean> action1 = new Action removeCourseFromStudent(studentState, student, course, courseState);
			List<Action<<?>> actions = new LinkedList<Action<?>>();
			
			sendMessage(action1, student, studentState);
			then(actions, () ->
			{
				if (action1.getResult().get())
				{
					complete(true);
					this.actorState.addRecord(this.name);
				}
				else
				{
					complete(false);
				}
			});
		}
	}
}
