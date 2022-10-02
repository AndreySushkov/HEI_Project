import java.util.Scanner;

public class Course {
    private int id;
    private String title;
    private int numberOfHours;

    public Course() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название курса: ");
        this.title = scanner.nextLine();
        System.out.print("Введите количество часов: ");
        this.numberOfHours = scanner.nextInt();
    }

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setNumberOfHours(int numberOfHours) {this.numberOfHours = numberOfHours;}

    public int getId() {return this.id;}
    public String getTitle() {return this.title;}
    public int getNumberOfHours() {return this.numberOfHours;}
}
