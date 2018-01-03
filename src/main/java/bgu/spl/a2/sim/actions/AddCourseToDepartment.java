package bgu.spl.a2.sim.actions;

import java.util.List;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;
import bgu.spl.a2.sim.privateStates.DepartmentPrivateState;

public class AddCourseToDepartment extends Action<Boolean>{
	
	private DepartmentPrivateState departmentState;
	
	private String departmentName;
	
	private String courseName;
	
	private int availableSpotsInCourse;
	
	private List<String> prequisites;
	
	public AddCourseToDepartment (DepartmentPrivateState departmentState, String departmentName, String courseName, int availableSpotsInCourse, List<String> prequisites) {
		this.departmentState = departmentState;
		this.departmentName = departmentName;
		this.courseName = courseName;
		this.availableSpotsInCourse = availableSpotsInCourse;
		this.prequisites = prequisites;
	}
	/**
	 * first this action first calls an action to create a course
	 * to the "then" function it adds a callback to add the course to our department, but it must happen only 
	 * then(nothing->complete(true)
	 */
	@Override
	protected void start() {
		
		Action<Boolean> createCourseAction = new CreateCourseAction();
		LinkedList<Action<Boolean>> actionsBeforeCallback= new LinkedList<Action<Boolean>>();
		actionsBeforeCallback.add(createCourseAction);
		
		then(actionsBeforeCallback, ()->
		{
			departmentState.addCourse(courseName)
			this.actorState.addRecord(this.name);
			complete(true);
		});
		sendMessage(createCourseAction, courseName, courseState);
	}

}
