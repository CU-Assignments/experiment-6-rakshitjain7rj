import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeSorting {
    static class Employee {
        private String name;
        private int age;
        private double salary;
        
        public Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public double getSalary() { return salary; }
        
        @Override
        public String toString() {
            return "Employee{name='" + name + "', age=" + age + ", salary=" + salary + "}";
        }
    }
    
    public static void main(String[] args) {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 30, 75000));
        employees.add(new Employee("Bob", 45, 85000));
        employees.add(new Employee("Charlie", 25, 65000));
        employees.add(new Employee("Diana", 35, 95000));
        employees.add(new Employee("Eva", 28, 70000));
        
        System.out.println("Original List of Employees:");
        printEmployees(employees);
        
        // Sort by name (alphabetically)
        Collections.sort(employees, (e1, e2) -> e1.getName().compareTo(e2.getName()));
        System.out.println("\nEmployees sorted by name:");
        printEmployees(employees);
        
        // Sort by age (ascending)
        Collections.sort(employees, (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println("\nEmployees sorted by age:");
        printEmployees(employees);
        
        // Sort by salary (descending)
        Collections.sort(employees, (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        System.out.println("\nEmployees sorted by salary (highest first):");
        printEmployees(employees);
    }
    
    private static void printEmployees(List<Employee> employees) {
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
