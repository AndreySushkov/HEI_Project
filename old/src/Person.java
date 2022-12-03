import java.util.Scanner;

public abstract class Person {
    private String fio;
    private int yearOfBirth;

    public Person(String fio, int yearOfBirth) {
        this.fio = fio;
        this.yearOfBirth = yearOfBirth;
    }

    public void setFio(String fio) {this.fio = fio;}
    public void setYearOfBirth(int yearOfBirth) {this.yearOfBirth = yearOfBirth;}

    public String getFio() {return this.fio;}
    public int getYearOfBirth() {return this.yearOfBirth;}
}
