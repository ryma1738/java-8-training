package myTesting.functionalInterfaces.data;

import java.util.Arrays;
import java.util.List;

public class Student {
    private final List<String> genders = Arrays.asList("Male", "Female", "Other");
    private String name;
    private double gpa;
    private List<String> activities;
    private int gender;
    private int notebooks;
    private int gradeLevel;



    public Student(String name, double gpa, List<String> activities, int gender, int gradeLevel,  int notebooks) {
        this.name = name;
        this.gpa = gpa;
        this.activities = activities;
        this.gender = gender;
        this.gradeLevel = gradeLevel;
        this.notebooks = notebooks;
    }

    public Student(String name, double gpa, List<String> activities, int gender, int gradeLevel) {
        this.name = name;
        this.gpa = gpa;
        this.activities = activities;
        this.gender = gender;
        this.gradeLevel = gradeLevel;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public double getGpa() {
        return gpa;
    }

    public int getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(int notebooks) {
        this.notebooks = notebooks;
    }

    public String getGender() {
        return genders.get(this.gender);
    }

    public Integer getGenderNum() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Student-{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", activities=" + activities +
                ", gender=" + genders.get(gender) +
                ", notebooks=" + notebooks +
                '}';
    }
}
