package model;

public class Administrator extends User {

    private String accessLevel;

    public Administrator(String name, String login, String password, String accessLevel) {
        super(name, login, password);
        if (accessLevel == null || accessLevel.isEmpty()) {
            throw new IllegalArgumentException("Access level cannot be empty.");
        }
        this.accessLevel = accessLevel;
    }
}
