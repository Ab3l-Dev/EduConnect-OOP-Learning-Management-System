package model;

public class OnlineCourse extends Course {

    private String platform;

    public OnlineCourse(String name, String code, int workload, String platform) {
        super(name, code, workload);
        if (platform == null || platform.isEmpty()) {
            throw new IllegalArgumentException("Platform cannot be empty.");
        }

        this.platform = platform;
    }

    @Override
    public String detailCourse() {
        return super.detailCourse() +
                "\nType: Online" +
                "\nPlatform: " + platform;
    }
}
