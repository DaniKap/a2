package bgu.spl.a2.sim.actions;

import bgu.spl.a2.Action;

public class AddStudent extends Action<Boolean> {

	private String studentName;
	
	private String department;
	
	private long signature;
	
	public AddStudent(String student, String department, long signature) {
		this.studentName = student;
		this.department = department;
		this.signature = signature;
	}
	
	public void start() {
		StudentPrivateState studentState = new StudentPrivateState();
		studentState.addSignature(signature);
		
		
		then(new LinkedList<Action<Boolean>>(), ()->
		{
			complete(true);
		});
		sendMessage(null, studentName, studentState);
	}
}
