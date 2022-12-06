package ru.vsuet.hey_project_with_javafx.controllers.CourseMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class UpdateCourseMenuController {
    private IService<Course> courseService;

    private Course course;

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

            Course newCourse = new Course(course.getId(), title, numberHours);
            courseService.update(newCourse);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Course> courseService, Course course) {
        this.courseService = courseService;

        this.course = course;

        start();
    }

    private void start() {
        titleField.setText(course.getTitle());
        numberHoursField.setText(Integer.toString(course.getNumberHours()));
    }
}