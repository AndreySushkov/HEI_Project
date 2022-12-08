package ru.vsuet.hey_project_with_javafx.controllers.CourseMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.io.IOException;

public class AddCourseMenuController {
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

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CourseMenu.fxml"));
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

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Course> courseService) {
        this.courseService = courseService;
    }

}
