package myTesting.functionalInterfaces;

import myTesting.functionalInterfaces.data.Student;
import myTesting.functionalInterfaces.data.StudentDataBase;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionExample {

    static Function<String, String> function = name -> name.toUpperCase();

    static Function<String, String> addSomeString = name -> name.toUpperCase().concat("default");

    static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> biFunction = (students, studentPredicate) -> {
        Map<String, Double> studentGpaMap = new HashMap<>();
        students.forEach(student -> {
            if(studentPredicate.test(student)) {
                studentGpaMap.put(student.getName(), student.getGpa());
            }
        });
        return studentGpaMap;
    };

    public static void main(String[] args) {
        List<Student> studentList = StudentDataBase.getAllStudents();
        System.out.println("Result is : " + function.apply("java8"));
        System.out.println("Result of and then is : " + function.andThen(addSomeString).apply("java8"));
        System.out.println("Result of and then is : " + function.compose(addSomeString).apply("java8"));
        System.out.println("Result of BiFunction : " + biFunction.apply(studentList, PredicateAndConsumerExample.p1));
    }
}
