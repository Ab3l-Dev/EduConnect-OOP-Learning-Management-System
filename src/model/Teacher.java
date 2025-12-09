package model;
import service.Report;

public class Teacher extends User implements Report {

    private String specialty;
    private String registry;

    public Teacher(String name, String login, String password, String specialty, String registry) {
        super(name, login, password);

        if (specialty == null || specialty.isEmpty()) {
            throw new IllegalArgumentException("Specialty cannot be empty.");
        }
        if (registry == null || registry.isEmpty()) {
            throw new IllegalArgumentException("Registry cannot be empty.");
        }

        this.specialty = specialty;
        this.registry = registry;
    }

    @Override
    public String generateReport() {
        return "\nTeacher Report:"
                + "\nName: " + getName()
                + "\nSpecialty: " + specialty
                + "\nRegistry: " + registry;
    }
}
