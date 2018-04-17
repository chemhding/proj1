
public class Course {
    private String classNum;
    private int homework;
    private int projects;
    private int exams;
    private int sites;
    private int[] students;

    public Course(String classNum) {
        this.classNum = classNum;
        homework = 10;
        projects = 1;
        exams = 2;
        sites = 3;
        students = new int[] { 10, 5, 5 };
    }

    public Course(String c, int h, int p, int e, int s) {
        classNum = c;
        homework = h;
        projects = p;
        exams = e;
        sites = s;
        students = new int[sites];
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public int getHomework() {
        return homework;
    }

    public void setHomework(int homework) {
        this.homework = homework;
    }

    public int getProjects() {
        return projects;
    }

    public void setProjects(int projects) {
        this.projects = projects;
    }

    public int getExams() {
        return exams;
    }

    public void setExams(int exams) {
        this.exams = exams;
    }

    public int getSites() {
        return sites;
    }

    public void setSites(int sites) {
        this.sites = sites;
    }

    public int[] getStudents() {
        return students;
    }

    public void setStudents(int[] students) {
        int length;
        if (this.students.length <= students.length)
            length = this.students.length;
        else
            length = students.length;
        for (int i = 0; i < length; i++)
            this.students[i] = students[i];
    }

}
