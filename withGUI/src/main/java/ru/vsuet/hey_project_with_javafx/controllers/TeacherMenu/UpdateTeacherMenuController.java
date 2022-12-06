package ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class UpdateTeacherMenuController {
    private IService<Teacher> teacherService;

    private Teacher teacher;

    @FXML
    private DatePicker dateBirthField;

    @FXML
    private TextField fioField;

    @FXML
    private Button applyButton;

    @FXML
    void initialize() {
        applyButton.setOnAction(actionEvent -> {
            String fio = fioField.getText();
            LocalDate date = dateBirthField.getValue();
            int yearBirth = date.getYear();
            int monthBirth = date.getMonthValue();
            int dayBirth = date.getDayOfMonth();

            Teacher newTeacher = new Teacher(teacher.getId(), fio, yearBirth, monthBirth, dayBirth);
            teacherService.update(newTeacher);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Teacher> teacherService, Teacher teacher) {
        this.teacherService = teacherService;

        this.teacher = teacher;

        start();
    }

    public void start() {
        fioField.setText(teacher.getFio());
        LocalDate date = LocalDate.of(teacher.getYearBirth(), teacher.getMonthBirth(), teacher.getDayBirth());
        dateBirthField.setValue(date);
    }
}