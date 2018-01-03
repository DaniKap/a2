package bgu.spl.a2.sim.actions;

import java.util.List;

import bgu.spl.a2.Action;
import bgu.spl.a2.sim.privateStates.CoursePrivateState;
import bgu.spl.a2.sim.privateStates.DepartmentPrivateState;

public class CreateDepartmentAction extends Action<Boolean> {

	private DepartmentPrivateState departmentState;
	
	private String departmentName;
	
	public CreateDepartmentAction (String departmentName){
		this.departmentState = new DepartmentPrivateState();
		this.departmentName = departmentName;
	}
	
	@Override
	public void start() {
		sendMessage(null, departmentName, departmentState);
		complete(true);
	}

}
