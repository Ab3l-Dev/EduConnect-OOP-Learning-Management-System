package model;
import service.Report;

import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Report {
    private String registration;
    private String course;
    private List<Evaluation> evaluations;

    public Student(String name, String login, String password, String registration, String course) {
        super(name, login, password);

        if (registration == null || registration.isEmpty()) {
            throw new IllegalArgumentException("Registration cannot be empty.");
        }
        if (course == null || course.isEmpty()) {
            throw new IllegalArgumentException("Course cannot be empty.");
        }
        this.registration = registration;
        this.course = course;
        this.evaluations = new ArrayList<>();
    }

    public void addEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    @Override
    public String generateReport() {
        String rep = "\nStudent Report:"
                + "\nName: " + getName()
                + "\nRegistration: " + registration
                + "\nCourse: " + course
                + "\nTotal Evaluations: " + evaluations.size()
                + "\nGrades:";

        for (Evaluation ev : evaluations) {
            rep += "\n - " + ev.getDescription() + ": "
                    + (ev.getGrade() == -1 ? "Not assigned" : ev.getGrade());
        }
        return rep;
    }
}
