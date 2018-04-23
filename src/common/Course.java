package common;

public class Course {
    private String className;
    private int homework;
    private int projects;
    private int exams;
    private int sites;
    private int[] students;

    // Construct course by default
    public Course(String className) {
        this.className = className;
        homework = 10;
        projects = 1;
        exams = 2;
        sites = 3;
        students = new int[] { 10, 5, 5 };
    }

    // Construct course by parameters
    /* @param c class name
     * @param h number of homework
     * @param p number of projects
     * @param e number of exams
     * @param s number of sites
     */
    public Course(String c, int h, int p, int e, int s) {
        className = c;
        homework = h;
        projects = p;
        exams = e;
        sites = s;
        students = new int[sites];
    }

    // -------------------Getters and Setters-------------------
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public String getClassName() {
        return className;
    }

    public void setClassName(String classNum) {
        this.className = classNum;
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
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public String toString() {
        String studentsNumbers = "";
        for (int i = 0; i < students.length; i++)
            studentsNumbers += students[i] + " ";
        return getClassName() + " " + getHomework() + " " + getProjects() + " " + getExams() + " " + getSites() + " "
                + studentsNumbers;
    }

}
