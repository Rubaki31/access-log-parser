package ru.learn.students;
import java.util.ArrayList;
import java.util.List;


public class Student {
    private final String name;
    private final List<Integer> grades;

    public Student(String name, int... initialGrades) {
        if (name != null) this.name = name;
        else throw new IllegalArgumentException("name must not be empty");
        this.grades = new ArrayList<>();
        if (initialGrades != null) {
            addGrades(initialGrades);
        }
    }

    public void addGrades(int... newGrades) {
        for (int grade : newGrades) {
            if (grade < 2 || grade > 5) {
                throw new IllegalArgumentException("Grade should be from 2 to 5");
            }
            grades.add(grade);
        }
    }

    public List<Integer> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grades=" + grades +
                '}';
    }
}
