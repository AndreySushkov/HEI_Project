package ru.vsuet.hey_project_with_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.repo.*;
import ru.vsuet.hey_project_with_javafx.service.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        DataBaseConnector connector = new DataBaseConnector();

        IRepository<Teacher> teacherRepository = new TeacherRepository(connector);
        IService<Teacher> teacherService = new TeacherService(teacherRepository);

        IRepository<Course> courseRepository = new CourseRepository(connector);
        IService<Course> courseService = new CourseService(courseRepository);

        IRepository<Student> studentRepository = new StudentRepository(connector);
        IService<Student> studentService = new StudentService(studentRepository);

        IRepository<Group> groupRepository = new GroupRepository(connector);
        IService<Group> groupService = new GroupService(groupRepository);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(loader.load());

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.transferParameters(teacherService, courseService, studentService, groupService);

        stage.setTitle("HEI Project");
        stage.setScene(scene);
        stage.show();
    }
}
