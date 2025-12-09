package model;

public class Evaluation {
    private double grade;
    private String description;

    public Evaluation(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Evaluation description cannot be empty.");
        }
        this.description = description;
        this.grade = -1;
    }

    public void assignGrade(double value) {
        if (value < 0 || value > 10) {
            throw new IllegalArgumentException("Grade must be between 0 and 10.");
        }
        this.grade = value;
    }

    public double getGrade() {
        return grade;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description + " - Grade: " + (grade == -1 ? "Not assigned" : grade);
    }
}
