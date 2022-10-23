public class Student extends Person{
    private int id;
    private Group group;
    private int yearOfStudy;

    public Student(String fio, int yearOfBirth, int yearOfStudy) {
        super(fio, yearOfBirth);

        this.yearOfStudy = yearOfStudy;
    }

    public void addGroupToStudent(Group group) {this.setGroup(group);}

    public void setId(int id) {this.id = id;}
    public void setGroup(Group group) {this.group = group;}
    public void setYearOfStudy(int yearOfStudy) {this.yearOfStudy = yearOfStudy;}

    public int getId() {return this.id;}
    public Group getGroup() {return this.group;}
    public int getYearOfStudy() {return this.yearOfStudy;}
}
