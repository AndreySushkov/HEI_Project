package ru.vsuet.hey_project_with_javafx.controllers.CourseMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class AddCourseMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

    private IService<Course> courseService;

    @FXML
    private Button applyButton;

    @FXML
    private TextField numberHoursField;

    @FXML
    private TextField titleField;

    @FXML
    void initialize() {
        applyButton.setOnAction(actionEvent -> {
            String title = titleField.getText();
            Integer numberHours = Integer.parseInt(numberHoursField.getText());

            Course newCourse = new Course(0L, title, numberHours);
            courseService.save(newCourse);

            String path = "CourseMenu/CourseMenu.fxml";
            CourseMenuController courseMenuController = (CourseMenuController) sceneLoader.loadScene(path);
            courseMenuController.transferParameters(courseService);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Course> courseService) {
        this.courseService = courseService;
    }

}
