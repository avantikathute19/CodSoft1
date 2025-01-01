import java.util.*;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private int enrolledStudents;


    public Course(String courseCode, String title, String description, int capacity, List<String> schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    
    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    
    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    
    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    
    public boolean registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.enrollStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            return true;
        }
        return false;
    }
}

class CourseManagementSystem {
    private List<Course> courses;
    private List<Student> students;

    
    public CourseManagementSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }


    public void addCourse(Course course) {
        courses.add(course);
    }

    
    public void addStudent(Student student) {
        students.add(student);
    }

   
    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println("Code: " + course.getCourseCode() + ", Title: " + course.getTitle() +
                    ", Description: " + course.getDescription() + ", Available Slots: " + course.getAvailableSlots());
            System.out.println("Schedule: " + String.join(", ", course.getSchedule()));
        }
    }

  
    public Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

   
    public Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }

    
    public void registerStudent(String studentID, String courseCode) {
        Student student = findStudent(studentID);
        Course course = findCourse(courseCode);

        if (student == null) {
            System.out.println("Student not found.");
        } else if (course == null) {
            System.out.println("Course not found.");
        } else if (student.registerCourse(course)) {
            System.out.println("Student " + student.getName() + " successfully registered for course: " + course.getTitle());
        } else {
            System.out.println("Registration failed. Course may be full or already registered.");
        }
    }

    
    public void dropStudent(String studentID, String courseCode) {
        Student student = findStudent(studentID);
        Course course = findCourse(courseCode);

        if (student == null) {
            System.out.println("Student not found.");
        } else if (course == null) {
            System.out.println("Course not found.");
        } else if (student.dropCourse(course)) {
            System.out.println("Student " + student.getName() + " successfully dropped the course: " + course.getTitle());
        } else {
            System.out.println("Drop failed. Course may not be registered.");
        }
    }
}

public class CourseDatabaseSystem {
    public static void main(String[] args) {
        CourseManagementSystem system = new CourseManagementSystem();

        
        system.addCourse(new Course("CS101", "Introduction to Programming", "Learn the basics of programming.", 30,
                Arrays.asList("Mon 9AM-11AM", "Wed 9AM-11AM")));
        system.addCourse(new Course("CS102", "Data Structures", "Understand data structures and algorithms.", 25,
                Arrays.asList("Tue 10AM-12PM", "Thu 10AM-12PM")));
        system.addCourse(new Course("CS103", "Database Systems", "Learn database design and SQL.", 20,
                Arrays.asList("Mon 2PM-4PM", "Wed 2PM-4PM")));

      
        system.addStudent(new Student("S001", "Alice"));
        system.addStudent(new Student("S002", "Bob"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Management System");
            System.out.println("1. Display Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    system.registerStudent(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    system.dropStudent(studentID, courseCode);
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}