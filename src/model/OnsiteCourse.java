package model;

public class OnsiteCourse extends Course {

    private String classroom;

    public OnsiteCourse(String name, String code, int workload, String classroom) {
        super(name, code, workload);
        if (classroom == null || classroom.isEmpty()) {
            throw new IllegalArgumentException("Classroom cannot be empty.");
        }
        this.classroom = classroom;
    }

    @Override
    public String detailCourse() {
        return super.detailCourse() +
                "\nType: Onsite" +
                "\nClassroom: " + classroom;
    }
}
