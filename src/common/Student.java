package common;

public class Student {
    private String firstName;
    private String lastName;
    private String SID;
    private int siteNum;
    private Course course;
    private int[] hwGrades;
    private int[] examGrades;
    private int[] projGrades;

    public Student(String fn, String ln, String sid, int stn, Course course) {
        firstName = fn;
        lastName = ln;
        SID = sid;
        siteNum = stn;
        this.course = course;
        hwGrades = new int[course.getHomework()];
        examGrades = new int[course.getExams()];
        projGrades = new int[course.getProjects()];
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

    public int[] getHwGrades() {
        return hwGrades;
    }

    public void setHwGrades(int[] hwGrades) {
        int length;
        if (this.hwGrades.length <= hwGrades.length)
            length = this.hwGrades.length;
        else
            length = hwGrades.length;
        for (int i = 0; i < length; i++)
            this.hwGrades[i] = hwGrades[i];
    }

    public int[] getExamGrades() {
        return examGrades;
    }

    public void setExamGrades(int[] examGrades) {
        int length;
        if (this.examGrades.length <= examGrades.length)
            length = this.examGrades.length;
        else
            length = examGrades.length;
        for (int i = 0; i < length; i++)
            this.examGrades[i] = examGrades[i];
    }

    public int[] getProjGrades() {
        return projGrades;
    }

    public void setProjGrades(int[] projGrades) {
        int length;
        if (this.projGrades.length <= projGrades.length)
            length = this.projGrades.length;
        else
            length = projGrades.length;
        for (int i = 0; i < length; i++)
            this.projGrades[i] = projGrades[i];
    }

    public int getAverageHomeWork() {
        int sum = 0;
        for (int i = 0; i < hwGrades.length; i++)
            sum += hwGrades[i];
        if (sum == 0)
            return sum;
        return sum / hwGrades.length;
    }

    public int getAveragePorjet() {
        int sum = 0;
        for (int i = 0; i < projGrades.length; i++)
            sum += projGrades[i];
        if (sum == 0)
            return sum;
        return sum / projGrades.length;
    }

    public int getAverageExam() {
        int sum = 0;
        for (int i = 0; i < examGrades.length; i++)
            sum += examGrades[i];
        if (sum == 0)
            return sum;
        return sum / examGrades.length;
    }

    public int getTotalScore(int hwWeight, int projWeight, int examWeight) {
        return getAverageHomeWork() * hwWeight / 10 + getAveragePorjet() * projWeight / 20
                + getAverageExam() * examWeight / 100;
    }

    public char getGrade(int hwWeight, int projWeight, int examWeight) {
        char grade;
        int total = getTotalScore(hwWeight, projWeight, examWeight);
        if (total >= 90)
            grade = 'A';
        else if (total >= 80)
            grade = 'B';
        else if (total >= 70)
            grade = 'C';
        else
            grade = 'E';
        return grade;
    }

    @Override
    public String toString() {
        String toReturn = firstName + " " + lastName + " " + SID + " " + course.getClassName() + " " + siteNum + " ";
        for (int i = 0; i < hwGrades.length; i++)
            toReturn += hwGrades[i] + " ";
        for (int j = 0; j < projGrades.length; j++)
            toReturn += projGrades[j] + " ";
        for (int k = 0; k < examGrades.length; k++)
            toReturn += examGrades[k] + " ";
        return toReturn;
    }
}
