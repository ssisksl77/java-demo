package designpattern.composite;

import java.util.Arrays;
import static designpattern.composite.Employee.boss;
import static designpattern.composite.Employee.developer;
import static designpattern.composite.Employee.manager;

public class CompositeDemoMain {
	public static void main(String[] args) {
		Employee dev1 = developer("kim", "dev1 team");
		Employee dev2 = developer("kimj", "dev team");
		Employee man1 = manager("Kahn", "dev1 team", Arrays.asList(new Employee[] {dev1, dev2}));
		Employee dev3 = developer("nam1", "dev2 team");
		Employee dev4 = developer("park", "dev2 team");
		Employee man2 = manager("!@#$!", "dev2 team", Arrays.asList(new Employee[] {dev3, dev4}));
		Employee boss = boss("BOSS", "TOP", Arrays.asList(new Employee[] {man1, man2}));
		
		// boss.printSubEmployeesBFS();
		boss.printSubEmployeesBFSRecursive();
	}
}
