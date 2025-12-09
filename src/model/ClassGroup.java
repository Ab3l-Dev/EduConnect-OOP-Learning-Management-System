package model;

import java.util.ArrayList;
import java.util.List;

public class ClassGroup {
    private String code;
    private Teacher teacher;
    private Course course;
    private List<Student> students;

    public ClassGroup(String code, Teacher teacher, Course course) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Class code cannot be empty.");
        }
        if (teacher == null) {
            throw new IllegalArgumentException("The class must have a valid teacher.");
        }
        if (course == null) {
            throw new IllegalArgumentException("The class must have a valid course.");
        }

        this.code = code;
        this.teacher = teacher;
        this.course = course;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Invalid student.");
        }
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public String summary() {
        return "ClassGroup: " + code +
                "\nTeacher: " + teacher +
                "\nCourse: " + course +
                "\nTotal students: " + students.size();
    }
}
