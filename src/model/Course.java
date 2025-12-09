package model;
import service.Report;

public class Course implements Report {
    private String name;
    private String code;
    private int workload;

    public Course(String name, String code, int workload) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be empty.");
        }
        if (workload <= 0) {
            throw new IllegalArgumentException("Workload must be greater than zero.");
        }

        this.name = name;
        this.code = code;
        this.workload = workload;
    }

    public String detailCourse() {
        return "Course: " + name +
                "\nCode: " + code +
                "\nWorkload: " + workload + "h";
    }

    @Override
    public String toString() {
        return detailCourse();
    }

    @Override
    public String generateReport() {
        return detailCourse();
    }
}
