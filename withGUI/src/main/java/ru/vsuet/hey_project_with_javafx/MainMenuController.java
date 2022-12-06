package ru.vsuet.hey_project_with_javafx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.vsuet.hey_project_with_javafx.controllers.CourseMenu.CourseMenuController;
import ru.vsuet.hey_project_with_javafx.controllers.GroupMenu.GroupMenuController;
import ru.vsuet.hey_project_with_javafx.controllers.StudentMenu.StudentMenuController;
import ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu.TeacherMenuController;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class MainMenuController {
    private IService<Teacher> teacherService;
    private IService<Course> courseService;
    private IService<Student> studentService;
    private IService<Group> groupService;

    @FXML
    private Button coursesButton;

    @FXML
    private Button groupsButton;

    @FXML
    private Button studentsButton;

    @FXML
    private Button teachersButton;

    @FXML
    void initialize() {
        teachersButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("controllers/TeacherMenu/TeacherMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            TeacherMenuController teacherMenuController = loader.getController();
            teacherMenuController.transferParameters(teacherService, courseService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        coursesButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("controllers/CourseMenu/CourseMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            CourseMenuController courseMenuController = loader.getController();
            courseMenuController.transferParameters(courseService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        studentsButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("controllers/StudentMenu/StudentMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            StudentMenuController studentMenuController = loader.getController();
            studentMenuController.transferParameters(studentService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        groupsButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("controllers/GroupMenu/GroupMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GroupMenuController groupMenuController = loader.getController();
            groupMenuController.transferParameters(groupService, studentService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    public void transferParameters(IService<Teacher> teacherService, IService<Course> courseService, IService<Student> studentService, IService<Group> groupService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.groupService = groupService;
    }
}
