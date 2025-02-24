import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentFiltering {
    static class Student {
        private String name;
        private int rollNo;
        private double marks;
        
        public Student(String name, int rollNo, double marks) {
            this.name = name;
            this.rollNo = rollNo;
            this.marks = marks;
        }
        
        public String getName() { return name; }
        public int getRollNo() { return rollNo; }
        public double getMarks() { return marks; }
        public double getPercentage() { return marks; } // Assuming marks are already in percentage
        
        @Override
        public String toString() {
            return "Student{name='" + name + "', rollNo=" + rollNo + ", marks=" + marks + "%}";
        }
    }
    
    public static void main(String[] args) {
        // Create a list of students
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", 101, 78.5));
        students.add(new Student("Emma", 102, 92.3));
        students.add(new Student("Michael", 103, 65.7));
        students.add(new Student("Sophia", 104, 88.9));
        students.add(new Student("William", 105, 72.1));
        students.add(new Student("Olivia", 106, 95.4));
        students.add(new Student("James", 107, 81.6));
        students.add(new Student("Ava", 108, 68.9));
        
        System.out.println("Students scoring above 75% (sorted by marks):");
        
        // Using streams and lambda expressions to:
        // 1. Filter students with marks above 75%
        // 2. Sort them by marks in descending order
        // 3. Extract their names and display
        students.stream()
               .filter(student -> student.getPercentage() > 75.0)
               .sorted(Comparator.comparing(Student::getMarks).reversed())
               .forEach(student -> System.out.println(student.getName() + " - " + student.getMarks() + "%"));
    }
}
