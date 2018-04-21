package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileScanner {
    private Scanner scan;
    private String pathCourse;
    private String pathStudent;
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Student> students = new ArrayList<Student>();

    public FileScanner(String pathCourse, String pathStudent) {
        try {
            this.pathCourse = pathCourse;
            this.pathStudent = pathStudent;
            scan = new Scanner(new File(pathCourse));
            scanCourse();
            scan = new Scanner(new File(pathStudent));
            scanStudents();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Course> scanCourse() {
        while (scan.hasNextLine()) {
            if (scan.hasNext()) {
                String className = scan.next();
                int homework = scan.nextInt();
                int projects = scan.nextInt();
                int exams = scan.nextInt();
                int sites = scan.nextInt();
                int[] students = new int[sites];
                for (int i = 0; i < sites; i++)
                    students[i] = scan.nextInt();
                Course course = new Course(className, homework, projects, exams, sites);
                course.setStudents(students);
                // System.out.println(course);
                courses.add(course);
                // System.out.println(courses.size());
            }
            scan.nextLine();
        }
        return courses;
    }

    public ArrayList<Student> scanStudents() {
        while (scan.hasNextLine()) {
            if (scan.hasNext()) {
                String firstName = scan.next();
                String lastName = scan.next();
                String SID = scan.next();
                String className = scan.next();
                Course course = find(className);
                if (course != null) {
                    int siteNum = scan.nextInt();
                    if (siteNum <= course.getSites()) {
                        int[] hwGrades = new int[course.getHomework()];
                        int[] examGrades = new int[course.getExams()];
                        int[] projGrades = new int[course.getProjects()];
                        for (int i = 0; i < course.getHomework(); i++)
                            hwGrades[i] = scan.nextInt();
                        for (int j = 0; j < course.getProjects(); j++)
                            projGrades[j] = scan.nextInt();
                        for (int k = 0; k < course.getExams(); k++)
                            examGrades[k] = scan.nextInt();

                        Student student = new Student(firstName, lastName, SID, siteNum, course);
                        student.setHwGrades(hwGrades);
                        student.setProjGrades(projGrades);
                        student.setExamGrades(examGrades);
                        students.add(student);
                    }
                }
            }
            scan.nextLine();
        }
        return students;

    }

    public Course find(String className) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getClassName().equalsIgnoreCase(className))
                return courses.get(i);
        }
        return null;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getPathCourse() {
        return pathCourse;
    }

    public String getPathStudent() {
        return pathStudent;
    }

    public BSTree<Course> sortCourses() {
        BSTree<Course> coursesTree = new BSTree<Course>(new Comparator<Course>() {
            @Override
            public int compare(Course course1, Course course2) {
                return course1.getClassName().compareToIgnoreCase(course2.getClassName());
            }
        });
        for (int i = 0; i < courses.size(); i++)
            coursesTree.iterativeAdd(courses.get(i));
        return coursesTree;
    }

    public BSTree<Student> sortStudents(SortType sorttype) {
        Comparator<Student> comp;
        if (sorttype == SortType.FirstName) {
            comp = new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    if (student1.getFirstName().compareToIgnoreCase(student2.getFirstName()) == 0) {
                        if (student1.getLastName().compareToIgnoreCase(student2.getLastName()) == 0)
                            return student1.getSiteNum() - (student2.getSiteNum());
                        else
                            return student1.getLastName().compareToIgnoreCase(student2.getLastName());
                    } else
                        return student1.getFirstName().compareToIgnoreCase(student2.getFirstName());
                }
            };
        } else if (sorttype == SortType.LastName) {
            comp = new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    if (student1.getLastName().compareToIgnoreCase(student2.getLastName()) == 0) {
                        if (student1.getFirstName().compareToIgnoreCase(student2.getFirstName()) == 0)
                            return student1.getSiteNum() - student2.getSiteNum();
                        else
                            return student1.getFirstName().compareToIgnoreCase(student2.getFirstName());
                    } else
                        return student1.getLastName().compareToIgnoreCase(student2.getLastName());
                }
            };
        } else {
            comp = new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    return student1.getSID().compareTo(student2.getSID());
                }
            };
        }
        BSTree<Student> studentsTree = new BSTree<Student>(comp);
        for (int i = 0; i < students.size(); i++)
            studentsTree.iterativeAdd(students.get(i));
        return studentsTree;
    }

    public ArrayList<Student> searchStudents(String key, SortType sorttype) {
        ArrayList<Student> results = new ArrayList<Student>();
        BSTree<Student> studentsTree = sortStudents(sorttype);
        // System.out.println(studentsTree.iterativeSize());
        Iterator<Student> iter = studentsTree.getIterator(BSTree.Traversal.Inorder);
        // while (iter.hasNext())
        // System.out.println(iter.next());
        System.out.println("----------FileScanner 170-----------");
        if (sorttype == SortType.FirstName) {
            System.out.println("test before searchstudents while loop");
            while (iter.hasNext()) {
                Student student = iter.next();
                // System.out.println("filescannerclass171" + student);
                if (key.compareToIgnoreCase(student.getFirstName()) == 0)
                    results.add(student);
                if (key.compareToIgnoreCase(student.getFirstName()) < 0)
                    return results;
            }
        } else if (sorttype == SortType.LastName) {
            while (iter.hasNext()) {
                Student student = iter.next();
                if (key.compareToIgnoreCase(student.getLastName()) == 0)
                    results.add(student);
                if (key.compareToIgnoreCase(student.getLastName()) < 0)
                    return results;
            }
        } else {
            while (iter.hasNext()) {
                Student student = iter.next();
                if (key.compareToIgnoreCase(student.getSID()) == 0)
                    results.add(student);
                if (key.compareToIgnoreCase(student.getSID()) < 0)
                    return results;
            }
        }
        return results;
    }

    // public static void main(String[] args) {
    // FileScanner fs = new FileScanner("src/resources/class_roster.txt",
    // "src/resources/fakedata.txt");
    // System.out.println(fs.courses.size() + " " + fs.students.size());
    // System.out.println(fs.courses.get(2));
    // System.out.println(fs.students.get(456));
    // System.out.println(fs.students.get(3));
    // ArrayList<Student> searchResults = fs.searchStudents("Akira",
    // SortType.FirstName);
    // System.out.println(searchResults.size());
    // for (int i = 0; i < 239; i++)
    // System.out.println(searchResults.get(i));
    // }

    public enum SortType {
        FirstName, LastName, SID;
    }
}
