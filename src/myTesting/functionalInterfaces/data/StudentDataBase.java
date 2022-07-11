package myTesting.functionalInterfaces.data;

import java.util.Arrays;
import java.util.List;

public class StudentDataBase {
    public static List<Student> getAllStudents(){
        Student student1 = new Student("Ryan", 3.6, Arrays.asList("Gaming", "Videography", "Computer Science"), 0, 3);
        Student student2 = new Student("Adam", 3.2, Arrays.asList("Gaming", "Band", "Choir"), 0, 5);
        Student student3 = new Student("Cat", 2.9, Arrays.asList("Dance", "Art Club"), 1, 6);
        Student student4 = new Student("Eric", 3.6, Arrays.asList("Gaming", "Computer Science"), 0, 2);
        Student student5 = new Student("Tyler", 3.8, Arrays.asList("Dance", "Wrestling", "Drama"), 2, 4);
        Student student6 = new Student("Alice", 3.3, Arrays.asList("Gaming", "Dance", "Art Club"), 1, 8);
        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5, student6);
        return students;
    }
}
