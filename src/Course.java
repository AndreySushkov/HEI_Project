public class Course {
    private int id;
    private String title;
    private int numberOfHours;

    public Course(String title, int numberOfHours) {
        this.title = title;
        this.numberOfHours = numberOfHours;
    }

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setNumberOfHours(int numberOfHours) {this.numberOfHours = numberOfHours;}

    public int getId() {return this.id;}
    public String getTitle() {return this.title;}
    public int getNumberOfHours() {return this.numberOfHours;}
}
