import java.util.ArrayList;
import java.util.Objects;

public class Students {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(String fio, int yearOfBirth, int yearOfStudy) {
        Student newStudent = new Student(fio, yearOfBirth, yearOfStudy);
        if (students.isEmpty()) {
            newStudent.setId(1);
        } else {
            newStudent.setId(students.get(students.size() - 1).getId() + 1);
        }
        students.add(newStudent);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void updateStudent(Student updatedStudent, String updatedFio, String updatedYearOfBirth, String updatedYearOfStudy) {
        if (!updatedFio.equals("")) {
            updatedStudent.setFio(updatedFio);
        }
        if (!updatedYearOfBirth.equals("")) {
            updatedStudent.setYearOfBirth(Integer.parseInt(updatedYearOfBirth));
        }
        if (!updatedYearOfStudy.equals("")) {
            updatedStudent.setYearOfStudy(Integer.parseInt(updatedYearOfStudy));
        }
    }

    public void showStudents() {
        for (Student student : students) {
            System.out.println(student.getId() + "\t" + student.getFio());
        }
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void setStudents(ArrayList<Student> students) {this.students = students;}

    public ArrayList<Student> getStudents() {return this.students;}
}
