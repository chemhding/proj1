
public class Student {
    private String firstName;
    private String lastName;
    private String SID;
    private int siteNum;
    private Course course;
    private double[] hwGrades;
    private double[] examGrades;
    private double[] projGrades;

    public Student(String fn, String ln, String sid, int stn, Course course) {
        firstName = fn;
        lastName = ln;
        SID = sid;
        siteNum = stn;
        this.course = course;
        hwGrades = new double[course.getHomework()];
        examGrades = new double[course.getExams()];
        projGrades = new double[course.getProjects()];
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String sID) {
        SID = sID;
    }

    public int getSiteNum() {
        return siteNum;
    }

    public void setSiteNum(int siteNum) {
        this.siteNum = siteNum;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double[] getHwGrades() {
        return hwGrades;
    }

    public void setHwGrades(double[] hwGrades) {
        int length;
        if (this.hwGrades.length <= hwGrades.length)
            length = this.hwGrades.length;
        else
            length = hwGrades.length;
        for (int i = 0; i < length; i++)
            this.hwGrades[i] = hwGrades[i];
    }

    public double[] getExamGrades() {
        return examGrades;
    }

    public void setExamGrades(double[] examGrades) {
        int length;
        if (this.examGrades.length <= examGrades.length)
            length = this.examGrades.length;
        else
            length = examGrades.length;
        for (int i = 0; i < length; i++)
            this.examGrades[i] = examGrades[i];
    }

    public double[] getProjGrades() {
        return projGrades;
    }

    public void setProjGrades(double[] projGrades) {
        int length;
        if (this.projGrades.length <= projGrades.length)
            length = this.projGrades.length;
        else
            length = projGrades.length;
        for (int i = 0; i < length; i++)
            this.projGrades[i] = projGrades[i];
    }
}
