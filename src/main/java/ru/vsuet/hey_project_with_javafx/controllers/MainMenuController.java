package ru.vsuet.hey_project_with_javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.vsuet.hey_project_with_javafx.controllers.CourseMenu.CourseMenuController;
import ru.vsuet.hey_project_with_javafx.controllers.GroupMenu.GroupMenuController;
import ru.vsuet.hey_project_with_javafx.controllers.StudentMenu.StudentMenuController;
import ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu.TeacherMenuController;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class MainMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

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
            String path = "TeacherMenu/TeacherMenu.fxml";
            TeacherMenuController teacherMenuController = (TeacherMenuController) sceneLoader.loadScene(path);
            teacherMenuController.transferParameters(teacherService, courseService);
        });

        coursesButton.setOnAction(actionEvent -> {
            String path = "CourseMenu/CourseMenu.fxml";
            CourseMenuController courseMenuController = (CourseMenuController) sceneLoader.loadScene(path);
            courseMenuController.transferParameters(courseService);
        });

        studentsButton.setOnAction(actionEvent -> {
            String path = "StudentMenu/StudentMenu.fxml";
            StudentMenuController studentMenuController = (StudentMenuController) sceneLoader.loadScene(path);
            studentMenuController.transferParameters(studentService);
        });

        groupsButton.setOnAction(actionEvent -> {
            String path = "GroupMenu/GroupMenu.fxml";
            GroupMenuController groupMenuController = (GroupMenuController) sceneLoader.loadScene(path);
            groupMenuController.transferParameters(groupService, studentService);
        });
    }

    public void transferParameters(IService<Teacher> teacherService, IService<Course> courseService, IService<Student> studentService, IService<Group> groupService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.groupService = groupService;
    }
}
