package myTesting.functionalInterfaces;

import myTesting.functionalInterfaces.data.Student;
import myTesting.functionalInterfaces.data.StudentDataBase;

import java.util.List;
import java.util.function.*;

public class PredicateAndConsumerExample {

    // Using only Predicate
    static Predicate<Student> p1 = (s) -> s.getGpa() > 3; // people with gpas higher than or equal to 3.0
    static Predicate <Student> p2 = s -> s.getGenderNum() == 1; // only Females

    //Using BiPredicate
    BiPredicate<Double, Integer> bothPredicates = (gpa, gender) -> gpa > 32 && gender == 0;

    BiConsumer<String, List<String>> studentBiConsumer = (name, activities) -> System.out.println(name + " : " + activities);

    Consumer<Student> studentConsumer = student -> {
        // Using only Predicate
//        if(p1.and(p2).test(student)) {
//            studentBiConsumer.accept(student.getName(), student.getActivities());
//        }
        // Using BiPredicate
        if(bothPredicates.test(student.getGpa(), student.getGenderNum())) {
            studentBiConsumer.accept(student.getName(), student.getActivities());
        }
    };

    public void printNameAndActivities(List<Student> students) {
    students.forEach(studentConsumer);
    }

    public static void main(String[] args) {
    List<Student> studentList = StudentDataBase.getAllStudents();
    new PredicateAndConsumerExample().printNameAndActivities(studentList);
    }

}
