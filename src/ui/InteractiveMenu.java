package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InteractiveMenu {

    private Scanner scanner = new Scanner(System.in);

    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<ClassGroup> classGroups = new ArrayList<>();
    private List<Administrator> administrators = new ArrayList<>();

    public void start() {

        seedAdmin();

        boolean running = true;

        while (running) {

            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1 - Register Student");
            System.out.println("2 - Register Teacher");
            System.out.println("3 - Register Course (Onsite / Online)");
            System.out.println("4 - Create Class Group");
            System.out.println("5 - Add Student to Class Group");
            System.out.println("6 - Register Evaluation");
            System.out.println("7 - Assign Grade");
            System.out.println("8 - Reports");
            System.out.println("9 - Automated Tests");
            System.out.println("0 - Exit");

            int option = readInt("Choose an option: ");

            try {
                switch (option) {
                    case 1 -> registerStudent();
                    case 2 -> registerTeacher();
                    case 3 -> registerCourse();
                    case 4 -> createClassGroup();
                    case 5 -> addStudentToClassGroup();
                    case 6 -> registerEvaluation();
                    case 7 -> assignGrade();
                    case 8 -> generateReports();
                    case 9 -> runTests();
                    case 0 -> {
                        running = false;
                        System.out.println("Shutting down system...");
                    }
                    default -> System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void registerStudent() {
        System.out.println("\n=== REGISTER STUDENT ===");

        String name = readText("Name: ");
        String login = readText("Login: ");
        String password = readText("Password: ");
        String registration = readText("Registration: ");
        String course = readText("Course: ");

        Student s = new Student(name, login, password, registration, course);
        students.add(s);

        System.out.println("Student successfully registered!");
    }

    private void registerTeacher() {
        System.out.println("\n=== REGISTER TEACHER ===");

        String name = readText("Name: ");
        String login = readText("Login: ");
        String password = readText("Password: ");
        String specialty = readText("Specialty: ");
        String registry = readText("Registry: ");

        Teacher t = new Teacher(name, login, password, specialty, registry);
        teachers.add(t);

        System.out.println("Teacher successfully registered!");
    }

    private void registerCourse() {
        System.out.println("\n=== REGISTER COURSE ===");
        System.out.println("1 - Onsite");
        System.out.println("2 - Online");

        int type = readInt("Type: ");

        String name = readText("Name: ");
        String code = readText("Code: ");
        int workload = readInt("Workload: ");

        if (type == 1) {
            String classroom = readText("Classroom: ");
            courses.add(new OnsiteCourse(name, code, workload, classroom));
            System.out.println("Onsite course registered!");
        } else if (type == 2) {
            String platform = readText("Platform: ");
            courses.add(new OnlineCourse(name, code, workload, platform));
            System.out.println("Online course registered!");
        } else {
            System.out.println("Invalid type!");
        }
    }

    private void createClassGroup() {
        if (teachers.isEmpty() || courses.isEmpty()) {
            System.out.println("You must register teachers and courses first.");
            return;
        }

        System.out.println("\n=== CREATE CLASS GROUP ===");

        String code = readText("Class group code: ");

        listTeachers();
        int idxTeacher = readInt("Choose a teacher: ");
        Teacher teacher = teachers.get(idxTeacher);

        listCourses();
        int idxCourse = readInt("Choose a course: ");
        Course course = courses.get(idxCourse);

        ClassGroup cg = new ClassGroup(code, teacher, course);
        classGroups.add(cg);

        System.out.println("Class group created!");
    }

    private void addStudentToClassGroup() {
        if (classGroups.isEmpty() || students.isEmpty()) {
            System.out.println("You must register students and class groups first.");
            return;
        }

        System.out.println("\n=== ADD STUDENT TO CLASS GROUP ===");

        listStudents();
        int idxStudent = readInt("Student: ");
        Student student = students.get(idxStudent);

        listClassGroups();
        int idxGroup = readInt("Class Group: ");
        ClassGroup cg = classGroups.get(idxGroup);

        cg.addStudent(student);
        System.out.println("Student added to class group!");
    }

    private void registerEvaluation() {
        listStudents();
        int idxStudent = readInt("Choose a student: ");
        Student s = students.get(idxStudent);

        String description = readText("Evaluation description: ");
        Evaluation ev = new Evaluation(description);

        s.addEvaluation(ev);
        System.out.println("Evaluation registered!");
    }

    private void assignGrade() {
        listStudents();
        int idxStudent = readInt("Student: ");
        Student s = students.get(idxStudent);

        List<Evaluation> evs = s.getEvaluations();

        if (evs.isEmpty()) {
            System.out.println("This student has no evaluations!");
            return;
        }

        System.out.println("\nEvaluations:");
        for (int i = 0; i < evs.size(); i++) {
            System.out.println(i + " - " + evs.get(i));
        }

        int idxEv = readInt("Choose an evaluation: ");
        double grade = readDouble("Grade (0 to 10): ");

        evs.get(idxEv).assignGrade(grade);

        System.out.println("Grade assigned!");
    }

    private void generateReports() {
        System.out.println("\n=== REPORTS ===");

        System.out.println("\n--- Students ---");
        students.forEach(s -> System.out.println(s.generateReport()));

        System.out.println("\n--- Teachers ---");
        teachers.forEach(t -> System.out.println(t.generateReport()));

        System.out.println("\n--- Courses ---");
        courses.forEach(c -> System.out.println(c.generateReport()));

        System.out.println("\n--- Class Groups ---");
        classGroups.forEach(g -> System.out.println(g.summary()));
    }

    private void runTests() {
        System.out.println("\n=== AUTOMATED TESTS ===");

        try {
            Course c = new OnsiteCourse("Java", "J100", 80, "Room 10");
            courses.add(c);

            Teacher t = new Teacher("Ana", "ana", "123", "Programming", "TE-001");
            teachers.add(t);

            Student s = new Student("Pedro", "pedro", "456", "REG01", "IT");
            students.add(s);

            ClassGroup cg = new ClassGroup("CG-JAV-01", t, c);
            cg.addStudent(s);
            classGroups.add(cg);

            System.out.println("Success test executed.");
        } catch (Exception e) {
            System.out.println("Unexpected failure: " + e.getMessage());
        }

        try {
            Course error = new OnsiteCourse("Error", "E0", 0, "Room X");
        } catch (Exception e) {
            System.out.println("Failure correctly captured: " + e.getMessage());
        }
    }

    private void seedAdmin() {
        administrators.add(new Administrator("Admin", "admin", "admin", "SUPER"));
    }

    private void listStudents() {
        System.out.println("\n--- Students ---");
        for (int i = 0; i < students.size(); i++)
            System.out.println(i + " - " + students.get(i).getName());
    }

    private void listTeachers() {
        System.out.println("\n--- Teachers ---");
        for (int i = 0; i < teachers.size(); i++)
            System.out.println(i + " - " + teachers.get(i).getName());
    }

    private void listCourses() {
        System.out.println("\n--- Courses ---");
        for (int i = 0; i < courses.size(); i++)
            System.out.println(i + " - " + courses.get(i));
    }

    private void listClassGroups() {
        System.out.println("\n--- Class Groups ---");
        for (int i = 0; i < classGroups.size(); i++)
            System.out.println(i + " - " + classGroups.get(i).summary());
    }

    private String readText(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int readInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    private double readDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(scanner.nextLine());
    }
}
