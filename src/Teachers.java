import java.util.ArrayList;

public class Teachers {
    private ArrayList<Teacher> teachers = new ArrayList<>();

    public void addTeacher(String fio, int yearOfBirth) {
        Teacher newTeacher = new Teacher(fio, yearOfBirth);
        if (teachers.isEmpty()) {
            newTeacher.setId(1);
        } else {
            newTeacher.setId(teachers.get(teachers.size() - 1).getId() + 1);
        }
        teachers.add(newTeacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public void updateTeacher(Teacher updatedTeacher, String updatedFio, String updatedYearOfBirth) {
        if (!updatedFio.equals("")) {
            updatedTeacher.setFio(updatedFio);
        }
        if (!updatedYearOfBirth.equals("")) {
            updatedTeacher.setYearOfBirth(Integer.parseInt(updatedYearOfBirth));
        }
    }

    public void showTeachers() {
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + "\t" + teacher.getFio());
        }
    }

    public void showTeachersWithCourses() {
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + "\t" + teacher.getFio());
            for (Course course : teacher.getCourses()) {
                System.out.println("\t" + course.getId() + "\t" + course.getTitle());
            }
        }
    }

    public Teacher findTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {this.teachers = teachers;}

    public ArrayList<Teacher> getTeachers() {return this.teachers;}
}
