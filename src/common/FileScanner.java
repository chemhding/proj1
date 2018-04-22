package common;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import common.BSTree.Traversal;
//import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;

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
        // System.out.println("----------FileScanner 170-----------");
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

    public ArrayList<Student> SortedStudentsArrayList() {
        ArrayList<Student> results = new ArrayList<Student>();
        BSTree<Student> studentsTree = sortStudents(SortType.FirstName);
        Iterator<Student> iter = studentsTree.getIterator(BSTree.Traversal.Inorder);
        while (iter.hasNext())
            results.add(iter.next());
        return results;
    }

    public ArrayList<Student> rankByClassSiteLastName() {
        ArrayList<Student> results = new ArrayList<Student>();
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName()) == 0) {
                    if (s1.getSiteNum() == s2.getSiteNum()) {
                        if (s1.getLastName().compareTo(s2.getLastName()) == 0) {
                            if (s1.getFirstName().compareTo(s2.getFirstName()) == 0) {
                                return s1.getSID().compareTo(s2.getSID());
                            } else
                                return s1.getFirstName().compareTo(s2.getFirstName());
                        } else
                            return s1.getLastName().compareTo(s2.getLastName());
                    } else
                        return s1.getSiteNum() - s2.getSiteNum();
                } else
                    return s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName());
            }

        };

        BSTree<Student> studentsTree = new BSTree<Student>(comp);
        for (int i = 0; i < students.size(); i++)
            studentsTree.iterativeAdd(students.get(i));
        Iterator<Student> iter = studentsTree.getIterator(Traversal.Inorder);
        while (iter.hasNext())
            results.add(iter.next());
        return results;
    }

    public ArrayList<Student> rankByHwAvg() {
        ArrayList<Student> results = new ArrayList<Student>();
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName()) == 0) {
                    if (s1.getAverageHomeWork() == s2.getAverageHomeWork()) {
                        if (s1.getSiteNum() == s2.getSiteNum()) {
                            if (s1.getLastName().compareTo(s2.getLastName()) == 0) {
                                return s1.getFirstName().compareTo(s2.getFirstName());
                            } else
                                return s1.getLastName().compareTo(s2.getLastName());
                        } else
                            return s1.getSiteNum() - s2.getSiteNum();
                    } else
                        return s2.getAverageHomeWork() - s1.getAverageHomeWork();
                } else
                    return s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName());
            }

        };

        BSTree<Student> studentsTree = new BSTree<Student>(comp);
        for (int i = 0; i < students.size(); i++)
            studentsTree.iterativeAdd(students.get(i));
        Iterator<Student> iter = studentsTree.getIterator(Traversal.Inorder);
        while (iter.hasNext())
            results.add(iter.next());
        return results;
    }

    public ArrayList<Student> rankByProjAvg() {
        ArrayList<Student> results = new ArrayList<Student>();
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName()) == 0) {
                    if (s1.getAveragePorjet() == s2.getAveragePorjet()) {
                        if (s1.getSiteNum() == s2.getSiteNum()) {
                            if (s1.getLastName().compareTo(s2.getLastName()) == 0) {
                                return s1.getFirstName().compareTo(s2.getFirstName());
                            } else
                                return s1.getLastName().compareTo(s2.getLastName());
                        } else
                            return s1.getSiteNum() - s2.getSiteNum();
                    } else
                        return s2.getAveragePorjet() - s1.getAveragePorjet();
                } else
                    return s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName());
            }

        };

        BSTree<Student> studentsTree = new BSTree<Student>(comp);
        for (int i = 0; i < students.size(); i++)
            studentsTree.iterativeAdd(students.get(i));
        Iterator<Student> iter = studentsTree.getIterator(Traversal.Inorder);
        while (iter.hasNext())
            results.add(iter.next());
        return results;
    }

    public ArrayList<Student> rankByExamAvg() {
        ArrayList<Student> results = new ArrayList<Student>();
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName()) == 0) {
                    if (s1.getAverageExam() == s2.getAverageExam()) {
                        if (s1.getSiteNum() == s2.getSiteNum()) {
                            if (s1.getLastName().compareTo(s2.getLastName()) == 0) {
                                return s1.getFirstName().compareTo(s2.getFirstName());
                            } else
                                return s1.getLastName().compareTo(s2.getLastName());
                        } else
                            return s1.getSiteNum() - s2.getSiteNum();
                    } else
                        return s2.getAverageExam() - s1.getAverageExam();
                } else
                    return s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName());
            }

        };

        BSTree<Student> studentsTree = new BSTree<Student>(comp);
        for (int i = 0; i < students.size(); i++)
            studentsTree.iterativeAdd(students.get(i));
        Iterator<Student> iter = studentsTree.getIterator(Traversal.Inorder);
        while (iter.hasNext())
            results.add(iter.next());
        return results;
    }

    // @param i homework weight
    // @param j project weight
    // @param k exam weight
    public ArrayList<Student> rankByTotalScore(int i, int j, int k) {
        ArrayList<Student> results = new ArrayList<Student>();
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName()) == 0) {
                    if (s1.getTotalScore(i, j, k) == s2.getTotalScore(i, j, k)) {
                        if (s1.getSiteNum() == s2.getSiteNum()) {
                            if (s1.getLastName().compareTo(s2.getLastName()) == 0) {
                                return s1.getFirstName().compareTo(s2.getFirstName());
                            } else
                                return s1.getLastName().compareTo(s2.getLastName());
                        } else
                            return s1.getSiteNum() - s2.getSiteNum();
                    } else
                        return s2.getTotalScore(i, j, k) - s1.getTotalScore(i, j, k);
                } else
                    return s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName());
            }

        };

        BSTree<Student> studentsTree = new BSTree<Student>(comp);
        for (int t = 0; t < students.size(); t++)
            studentsTree.iterativeAdd(students.get(t));
        Iterator<Student> iter = studentsTree.getIterator(Traversal.Inorder);
        while (iter.hasNext())
            results.add(iter.next());
        return results;
    }

    // @param i homework weight
    // @param j project weight
    // @param k exam weight
    public ArrayList<Student> rankByGrade(int i, int j, int k) {
        ArrayList<Student> results = new ArrayList<Student>();
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName()) == 0) {
                    if (s1.getGrade(i, j, k) == s2.getGrade(i, j, k)) {
                        if (s1.getSiteNum() == s2.getSiteNum()) {
                            if (s1.getLastName().compareTo(s2.getLastName()) == 0) {
                                return s1.getFirstName().compareTo(s2.getFirstName());
                            } else
                                return s1.getLastName().compareTo(s2.getLastName());
                        } else
                            return s1.getSiteNum() - s2.getSiteNum();
                    } else
                        return s1.getGrade(i, j, k) - s2.getGrade(i, j, k);
                } else
                    return s1.getCourse().getClassName().compareTo(s2.getCourse().getClassName());
            }

        };

        BSTree<Student> studentsTree = new BSTree<Student>(comp);
        for (int t = 0; t < students.size(); t++)
            studentsTree.iterativeAdd(students.get(t));
        Iterator<Student> iter = studentsTree.getIterator(Traversal.Inorder);
        while (iter.hasNext())
            results.add(iter.next());
        return results;
    }

    public ArrayList<String[]> gradesPerClass() {

        ArrayList<Student> students = rankByClassSiteLastName();
        ArrayList<String[]> classGrades = new ArrayList<String[]>();
        String tempClassName = students.get(0).getCourse().getClassName();
        int i = 0, numPerClass = 0;
        int totalHwGrade = 0, totalProjGrade = 0, totalExamGrade = 0, totalScore = 0;
        while (i < students.size()) {
            if (tempClassName.equals(students.get(i).getCourse().getClassName())) {
                totalHwGrade += students.get(i).getAverageHomeWork();
                totalProjGrade += students.get(i).getAveragePorjet();
                totalExamGrade += students.get(i).getAverageExam();
                totalScore += students.get(i).getTotalScore(20, 20, 60);
                numPerClass++;
            } else {
                String[] temp = new String[] { tempClassName, totalHwGrade / numPerClass + "",
                        totalProjGrade / numPerClass + "", totalExamGrade / numPerClass + "",
                        totalScore / numPerClass + "" };
                classGrades.add(temp);
                tempClassName = students.get(i).getCourse().getClassName();
                totalHwGrade = students.get(i).getAverageHomeWork();
                totalProjGrade = students.get(i).getAveragePorjet();
                totalExamGrade = students.get(i).getAverageExam();
                totalScore = students.get(i).getTotalScore(20, 20, 60);
                numPerClass = 1;
            }
            i++;
        }
        String[] temp = new String[] { tempClassName, totalHwGrade / numPerClass + "",
                totalProjGrade / numPerClass + "", totalExamGrade / numPerClass + "", totalScore / numPerClass + "" };
        classGrades.add(temp);

        return classGrades;
    }

    public ArrayList<int[]> classDetailedGrades(String className) {

        ArrayList<Student> students = rankByClassSiteLastName();
        ArrayList<int[]> classGrades = new ArrayList<int[]>();
        int i = 0;
        int foundIndex = 0, stopIndex = 0;

        int totalHwGrade = 0, totalProjGrade = 0, totalExamGrade = 0, totalScore = 0;
        boolean found = false, stop = false;
        System.out.println(students.get(0).getSiteNum());
        System.out.println(students.get(7).getSiteNum());
        while (i < students.size() && !stop) {
            if (className.equalsIgnoreCase(students.get(i).getCourse().getClassName())) {
                if (!found)
                    foundIndex = i;
                found = true;
            } else if (found) {
                if (!className.equalsIgnoreCase(students.get(i).getCourse().getClassName())) {
                    stopIndex = i - 1;
                    stop = true;
                }
            } else {
            }
            i++;
        }
        int j = foundIndex;
        System.out.println(foundIndex + " " + stopIndex);
        int numPerSite = 0;
        int tempSiteNum = students.get(j).getSiteNum();
        while (j <= stopIndex) {
            if (tempSiteNum == students.get(j).getSiteNum()) {
                totalHwGrade += students.get(j).getAverageHomeWork();
                totalProjGrade += students.get(j).getAveragePorjet();
                totalExamGrade += students.get(j).getAverageExam();
                totalScore += students.get(j).getTotalScore(20, 20, 60);
                numPerSite++;
            } else {
                int[] temp = new int[] { tempSiteNum, totalHwGrade / numPerSite, totalProjGrade / numPerSite,
                        totalExamGrade / numPerSite, totalScore / numPerSite };
                classGrades.add(temp);
                tempSiteNum = students.get(j).getSiteNum();
                totalHwGrade = students.get(j).getAverageHomeWork();
                totalProjGrade = students.get(j).getAveragePorjet();
                totalExamGrade = students.get(j).getAverageExam();
                totalScore = students.get(j).getTotalScore(20, 20, 60);
                numPerSite = 1;
            }
            j++;
        }

        int[] temp = new int[] { tempSiteNum, totalHwGrade / numPerSite, totalProjGrade / numPerSite,
                totalExamGrade / numPerSite, totalScore / numPerSite };
        classGrades.add(temp);

        return classGrades;
    }

    public ArrayList<String[]> gradePercentage() {

        ArrayList<Student> students = rankByClassSiteLastName();
        ArrayList<String[]> classGrades = new ArrayList<String[]>();
        String tempClassName = students.get(0).getCourse().getClassName();
        int i = 0, numPerClass = 0;
        int ANum = 0, BNum = 0, CNum = 0, ENum = 0;
        DecimalFormat fmt = new DecimalFormat("0.00");
        while (i < students.size()) {
            if (tempClassName.equals(students.get(i).getCourse().getClassName())) {
                if (students.get(i).getGrade(20, 20, 60) == 'A')
                    ANum++;
                else if (students.get(i).getGrade(20, 20, 60) == 'B')
                    BNum++;
                else if (students.get(i).getGrade(20, 20, 60) == 'C')
                    CNum++;
                else
                    ENum++;
                numPerClass++;
            } else {
                String[] temp = new String[] { tempClassName, fmt.format((double) ANum / numPerClass * 100) + "%",
                        fmt.format((double) BNum / numPerClass * 100) + "%",
                        fmt.format((double) CNum / numPerClass * 100) + "%",
                        fmt.format((double) ENum / numPerClass * 100) + "%" };
                System.out.println(ANum + " " + BNum + " " + CNum + " " + ENum + " " + numPerClass);
                classGrades.add(temp);
                tempClassName = students.get(i).getCourse().getClassName();
                ANum = 0;
                BNum = 0;
                CNum = 0;
                ENum = 0;
                if (students.get(i).getGrade(20, 20, 60) == 'A')
                    ANum = 1;
                else if (students.get(i).getGrade(20, 20, 60) == 'B')
                    BNum = 1;
                else if (students.get(i).getGrade(20, 20, 60) == 'C')
                    CNum = 1;
                else
                    ENum = 1;
                numPerClass = 1;
            }
            i++;
        }
        String[] temp = new String[] { tempClassName, fmt.format((double) ANum / numPerClass * 100) + "%",
                fmt.format((double) BNum / numPerClass * 100) + "%",
                fmt.format((double) CNum / numPerClass * 100) + "%",
                fmt.format((double) ENum / numPerClass * 100) + "%" };
        classGrades.add(temp);

        return classGrades;
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
