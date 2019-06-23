package template;

import java.util.ArrayList;

public class Employee {
	private int id; // unique employee id
	private boolean isInBuilding;
	ArrayList<Long> timesSwiped = new ArrayList<Long>();
	public Employee(int id) {
		this.id = id;
		this.timesSwiped = new ArrayList<Long>();
	}

	public int getId() {
		return id;
	}
	
	public ArrayList<Long> getTimesSwiped(){
		return timesSwiped;
	}
	
	public void addTimesSwiped(long time){
		this.timesSwiped.add(time); 
	}
	
	public String toString(){
		return("Employee id #: " + this.id + "\nTimes Employee Swiped: " + this.timesSwiped + "\n");
	}
	
	public boolean getIsInBuilding(long time){
		for(int i = 0; i < timesSwiped.size(); i++)
		if(timesSwiped.size() % 2 == 1) return false;
		return true;
	}

}
