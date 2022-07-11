package myTesting.functionalInterfaces;

import myTesting.functionalInterfaces.data.Student;
import myTesting.functionalInterfaces.data.StudentDataBase;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams {

    static Predicate<Student> sp1 = student -> student.getGpa() >=3.3; //Universal filter

    public static Map<String, List<String>> studentMap() {
        return StudentDataBase.getAllStudents().stream()
                .filter(sp1)
                //.peek(student -> System.out.println("Peeking in at: " + student.getName())) //This allows you to see what a stream is doing / bug fixing
                .collect(Collectors.toMap(Student::getName, Student::getActivities));// Using Method Refrencing. Same as saying student.getName()
    }

    public static List<String> namesList() {
        return StudentDataBase.getAllStudents().stream()
                //Student is the input -> convert to just the name
                .map(Student::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static List<String> flatMap() {
       return StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)// use this to "reduce" a steam of lists into a stream of items
                .distinct()// gets rid of dups
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<String> studentsByNameDec() {
        return StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getName).reversed())
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    public static Optional<Student> highestGpa() {
        return StudentDataBase.getAllStudents().stream()
                .reduce((s1, s2) -> s1.getGpa() > s2.getGpa() ? s1 : s2);
    }

    public static List<Boolean> anyAllNoneMatch() {
        return Arrays.asList(StudentDataBase.getAllStudents().stream().anyMatch(sp1),
                StudentDataBase.getAllStudents().stream().allMatch(sp1),
                StudentDataBase.getAllStudents().stream().noneMatch(sp1));
    }

    public static Optional<Student> findFirstStudent() {
        return StudentDataBase.getAllStudents().stream().filter(sp1).findFirst();
    }
    public static Optional<Student> findAnyStudent() {
        return StudentDataBase.getAllStudents().stream().filter(sp1).findAny();
    }

    public static void main(String[] args) {


        System.out.println("List of students with Gpa higher than 3.3: " +studentMap());
        System.out.println("Mapped list of names: " + namesList());
        System.out.println("Flat Map Example: " + flatMap());
        System.out.println("Students sorted by name: " + studentsByNameDec());
        Optional<Student> highestGpaStudent = highestGpa();
        if (highestGpaStudent.isPresent()) System.out.println("Student with the highest GPA: " + highestGpaStudent.get());
        System.out.println("Check if any, all, or none match the sp1 filter: " + anyAllNoneMatch());
        Optional<Student> findFirst = findFirstStudent();
        Optional<Student> findAny = findAnyStudent();
        if(findFirst.isPresent()) System.out.println("First student to match sp1: " + findFirst.get());
        if(findAny.isPresent()) System.out.println("Any student to match sp1: " + findAny.get());

    }
}
