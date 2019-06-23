package template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import template.Employee;

public class EmployeeRecords {
	private ArrayList<Employee> employees;

	/***
	 * constructs a new records object representing a single day of card swipes.
	 * it is pre-populated with a number of consecutive employee ids given by
	 * numids.
	 * 
	 * @param numids
	 *            the number of consecutive employee ids to pre-populate the
	 *            object with
	 */
	public EmployeeRecords(int numids) {
		employees = new ArrayList<Employee>();
		createEmployees(numids);
	}

	/***
	 * This method creates a list of 300 employees with ids from 0 to 300. These
	 * are the ids that will be in the actual swipes in the data file.
	 * 
	 * You may modify this method and/or the employee constructor if you wish.
	 * 
	 * @param numids
	 *            the number of consecutive ids to populate your employee list
	 *            with
	 */
	private void createEmployees(int numids) {
		for (int id = 0; id < numids; id++) {
			employees.add(new Employee(id));
		}
	}

	/***
	 * get an Employee object from the list by id number.
	 * 
	 * @param id
	 *            the id of the employee to return
	 * @return Employee the employee corresponding to the input id, or null if
	 *         no such employee
	 */
	public Employee getEmployeeById(int id) {
		for (Employee e : employees) {
			if (e.getId() == id)
				return e;
		}

		return null;
	}

	/***
	 * register a new swipe with an id and a time
	 * 
	 * @param id
	 *            the id of the card that was swiped
	 * @param swipe_time
	 *            the time (in seconds since midnight) when the swipe occured
	 */
	public void registerSwipe(int id, long swipe_time) {
		Employee employee = getEmployeeById(id); // get the employee for this
													// swipe

		System.out.println("registering swipe for " + id + " at: " + swipe_time);
		employee.addTimesSwiped(swipe_time);
	}

	/***
	 * return the total time spent inside the building for employee id
	 * 
	 * @param id
	 *            the id of the employee you want to get the time for
	 * @return int the total seconds spent inside the building so far for
	 *         employee id
	 */
	public long getTimeInBuildingFor(int id) {
		long sum = 0;
		ArrayList<Long> times = new ArrayList<Long>();
		Employee employee = getEmployeeById(id);
		times = employee.timesSwiped;
		for (int i = 0; i < times.size(); i += 2) {
			sum += (times.get(i + 1) - times.get(i));
		}
		
		return sum;

	}

	/***
	 * return the time employee id first swiped in
	 * 
	 * @param id
	 *            the id of the employee you want to get the time for
	 * @return int the time in seconds after midnight employee id first swiped
	 *         in
	 */
	public long getTimeInFor(int id) {
		Employee employee = getEmployeeById(id);
		return employee.timesSwiped.get(0);
	}

	/***
	 * return the employee with the most time in the building so far
	 * 
	 * @return Employee the employee with the most time in the building so far
	 */
	public Employee getEmployeeWithMostTimeIn() {
		long maxTime = 0;
		int employeeLocationWithMaxTime;
		for(int i = 0; i < employees.size(); i++){
				if(
			}
			
		}
	}

	/***
	 * return the employee with the least time in the building so far
	 * 
	 * @return Employee the employee with the least time in the building so far
	 */
	public Employee getEmployeeWithLeastTimeIn() {
		return null;
	}

	/***
	 * return a list of employees in the building at a particular time
	 * 
	 * @param time
	 *            the time (seconds after midnight) you want an employee list
	 *            for
	 * @return the list of employees in the building at that time
	 */
	public ArrayList<Employee> getEmployeesInBuildingAt(long time) {
		return null;
	}

	/***
	 * return a 2-element array with a start and end time for the time interval
	 * during which the most employees were in the building
	 * 
	 * @return int[] a 2-element array with a start and end time
	 */
	public int[] timeIntervalWithMostEmployees() {
		return null;
	}

	public void displayAllEmployees() {
		for (Employee e : this.employees) {
			System.out.println(e);
		}
	}

	public static double toHours(double seconds) {
		return seconds / 60.0 / 60.0;
	}

	public static double toSeconds(double hours) {
		return hours * 60.0 * 60.0;
	}

	/***
	 * Load swipe data from a file.
	 * 
	 * pre-condition: File first line is the # of records, second line is column
	 * names and remaining lines are comma-separated values. First column is
	 * employee id, second is time (in seconds since midnight). We assume data
	 * is in increasing time-sequential order.
	 * 
	 * @param filepath
	 */
	public void loadDataFromFile(String filepath) {
		Scanner scanner;
		try {
			scanner = new Scanner(new FileReader(filepath));
			scanner.useDelimiter("\n");

			String line;
			int numRecords = scanner.nextInt(); // first line gives the number
												// of records
			scanner.next();

			while (scanner.hasNext()) {
				line = scanner.next();
				String[] args = line.split(",");

				try {
					int id = Integer.parseInt(args[0].trim());
					long time = Long.parseLong(args[1].trim());

					this.registerSwipe(id, time);

				} catch (Exception e) {
					System.out.println("something went wrong:" + e.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found " + filepath);
		}
	}
}