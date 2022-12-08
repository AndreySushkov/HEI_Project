package ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class AddTeacherMenuController {
    private IService<Teacher> teacherService;
    private IService<Course> courseService;

    @FXML
    private Button applyButton;

    @FXML
    private DatePicker dateBirthField;

    @FXML
    private TextField fioField;

    @FXML
    void initialize() {
        applyButton.setOnAction(actionEvent -> {
            String fio = fioField.getText();
            LocalDate dateBirth = dateBirthField.getValue();
            int yearBirth = dateBirth.getYear();
            int monthBirth = dateBirth.getMonthValue();
            int dayBirth = dateBirth.getDayOfMonth();

            Teacher newTeacher = new Teacher(0L, fio, yearBirth, monthBirth, dayBirth);
            teacherService.save(newTeacher);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TeacherMenu.fxml"));
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

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Teacher> teacherService, IService<Course> courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }
}
