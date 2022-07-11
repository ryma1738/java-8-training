package myTesting.functionalInterfaces;

import myTesting.functionalInterfaces.data.Student;
import myTesting.functionalInterfaces.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;

public class StreamsCollecting {

    //Join method only works on strings
    public static String joining1() { //basic join method for strings
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getName)
                .collect(joining());
    }

    public static String joining2() {
    return StudentDataBase.getAllStudents().stream()
            .map(Student::getName)
            .collect(joining("-"));
    }

    public static String joining3() {
    return StudentDataBase.getAllStudents().stream()
            .map(Student::getName)
            .collect(joining("-", "(", ")"));
    }

    public static long counting(int index) { //returns the total number of elements
        if(index<1) return StudentDataBase.getAllStudents().stream().collect(Collectors.counting());
        else return StudentDataBase.getAllStudents().stream()
                .filter(s -> s.getGpa() > 3.2).collect(Collectors.counting());
    }

    public static List<String> mapping() {
        return StudentDataBase.getAllStudents().stream()
                .collect(Collectors.mapping(Student::getName, toList()));
        //Same as just using .map(Student::getName).collect(toList)
    }

    public static Optional<Student> minBy() {
        return StudentDataBase.getAllStudents().stream()
                .collect(Collectors.minBy(Comparator.comparing(Student::getGpa)));
    }
    public static Optional<Student> maxBy() {
        return StudentDataBase.getAllStudents().stream()
                .collect(Collectors.maxBy(Comparator.comparing(Student::getGpa)));
    }

    public static int sum() {
        return StudentDataBase.getAllStudents().stream()
                .collect(summingInt(Student::getNotebooks));
    }

    public static Double averageInt() {
        return StudentDataBase.getAllStudents().stream()
                .collect(averagingInt(Student::getNotebooks));
    }

    public static Map<String, List<Student>> groupingByGender() {
        return StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getGender));
    }

    public static Map<String, String> customGroupingBy() {
        return StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(student -> student.getGpa() >=3.6 ? "OutStanding" : "Average",
                        Collectors.mapping(Student::getName, joining(", "))));
    }

    public static Map<String, String> twoLevelGrouping() {
        return StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getGender,
                        Collectors.mapping(Student::getName, joining(", "))));
                //can combine any form of collection with the grouping
    }

    public static Map<String, Student> groupingOptional() {
        return StudentDataBase.getAllStudents().stream()
                .collect((groupingBy(Student::getGender, collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Student::getGpa)), Optional::get))));
        //using the collectingAndThen method you can turn an optional into a value in a stream
    }

    public static Map<Boolean, String> partitioning() {
        Predicate<Student> notebooks = s -> s.getNotebooks() > 5;
        return StudentDataBase.getAllStudents().stream()
                .collect(partitioningBy(notebooks, Collectors.mapping(Student::getName, joining(", "))));
    }

    public static void main(String[] args) {


        System.out.println("Joining Method 1: " + joining1());
        System.out.println("Joining Method 2: " + joining2());
        System.out.println("Joining Method 3: " + joining3());
        System.out.println("Counting all students: " + counting(0));
        System.out.println("Counting all students with GPA > 3.2: " + counting(1));
        System.out.println("List of all names via mapping(): " + mapping());
        if(minBy().isPresent()) System.out.println("Dumbest Student via minBy: " + minBy().get());
        if(maxBy().isPresent()) System.out.println("Smartest Student via maxBy: " + maxBy().get());
        System.out.println("Total Notebooks via summingInt: " + sum());
        System.out.println("Average number of notebooks: " + averageInt());
        System.out.println("Students grouped by gender: " + groupingByGender());
        System.out.println("Students by GPA Two Level: " + customGroupingBy());
        System.out.println("Students by Gender two level: " + twoLevelGrouping());
        System.out.println("Grouping with optionals maxBy: " + groupingOptional());
        System.out.println("Partitioning by # of notebooks: " + partitioning());
    }
}
