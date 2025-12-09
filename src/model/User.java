package model;
import service.Authentication;

public abstract class User implements Authentication {

    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Login cannot be empty.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    public String getName() {
        return name;
    }
}
