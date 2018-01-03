package bgu.spl.a2.sim.actions;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.DepartmentPrivateState;
import bgu.spl.a2.sim.privateStates.StudentPrivateState;

public class AddStudentToDepartment extends Action<Boolean> {

	private String studentName;
	
	private String department;
	
	private long signature;
	
	private DepartmentPrivateState departmentState;
	
	public AddStudentToDepartment(String student, String department, long signature, DepartmentPrivateState departmentState) {
		this.studentName = student;
		this.department = department;
		this.signature = signature;
		this.departmentState = departmentState;
	}
	
	public void start() {
		then(new LinkedList<Action<Boolean>>(), ()->
		{
			departmentState.addStudent(studentName)
			this.actorState.addRecord(this.name);
			complete(true);
		});
	}
}
