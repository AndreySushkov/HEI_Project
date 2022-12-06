package ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class AddTeacherMenuController {
    private IService<Teacher> teacherService;

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

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Teacher> teacherService) {
        this.teacherService = teacherService;
    }
}
