package myTesting.functionalInterfaces;

import jdk.xml.internal.XMLSecurityManager;
import myTesting.functionalInterfaces.data.Student;
import myTesting.functionalInterfaces.data.StudentDataBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamNums {

    public static int numsReduce(List<Integer> nums) {
        return nums.stream().reduce(0, (a,b) -> a+b);
        // or .reduce(0, Integer::sum);
    }

    public static Optional<Integer> numsReduceOptional(List<Integer> nums) { //with no identity null values are possible
        return nums.stream().reduce((a,b) -> a+b );
    }

    private static int numOfNotebooks() { //returns the total number of notebooks the students have
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getNotebooks)
                .reduce(0, (a,b) -> a+b);
            // or .reduce(0, Integer::sum);
    }

    public static Optional<Integer> findMaxValueOpt(List<Integer> nums) {
        return nums.stream().reduce((x,y)-> x>y ? x : y);
    }

    public static Optional<Integer> findMinValueOpt(List<Integer> nums) {
        return nums.stream().reduce((x,y)-> x<y ? x : y);
    }

    public static Optional<Integer> limit(List<Integer> nums) { // only uses the first N'th values
        return nums.stream().limit(2) // 6, 7
                .reduce(Integer::sum);
    }

    public static Optional<Integer> skipped(List<Integer> nums) { // skips the first N'th values
        return nums.stream().skip(2) // 8, 9, 10
                .reduce(Integer::sum);
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(4,5,6,7,8,9,10);
        List<Integer> emptyList = new ArrayList<>();
        Optional<Integer> maxValueOpt = findMaxValueOpt(intList);
        Optional<Integer> minValueOpt = findMinValueOpt(intList);
        Optional<Integer> limitedValue = limit(intList);
        Optional<Integer> skipValues = skipped(intList);


        System.out.println("Total Numbers via Reduce: " + numsReduce(intList));
        Optional<Integer> numsOpt = numsReduceOptional(intList);
        System.out.println("Optional Test: " + numsOpt.isPresent());
        if (numsOpt.isPresent()) System.out.println("Optional Get: " + numsOpt.get());
        if (maxValueOpt.isPresent()) System.out.println("Max Value: " +maxValueOpt.get());
        else System.out.println("No Max Value");
        if (minValueOpt.isPresent()) System.out.println("Min Value: " +minValueOpt.get());
        else System.out.println("No Min Value");
        if(limitedValue.isPresent()) System.out.println("Limited value: " + limitedValue.get());
        if(skipValues.isPresent()) System.out.println("Skiped Values: " + skipValues.get());


        System.out.println("All notebooks: " + numOfNotebooks());
    }
}
