package ru.vsuet.hey_project_with_javafx.controllers.StudentMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.time.LocalDate;

public class AddStudentMenuController {
    private IService<Student> studentService;

    @FXML
    private Button applyButton;

    @FXML
    private DatePicker dateBirthField;

    @FXML
    private TextField fioField;

    @FXML
    private TextField numberRecordBookField;

    @FXML
    private TextField yearStudyField;

    @FXML
    void initialize() {
        applyButton.setOnAction(actionEvent -> {
            String fio = fioField.getText();
            LocalDate date = dateBirthField.getValue();
            Integer dayBirth = date.getDayOfMonth();
            Integer monthBirth = date.getMonthValue();
            Integer yearBirth = date.getYear();
            Integer yearStudy = Integer.parseInt(yearStudyField.getText());
            Integer numberRecordBook = Integer.parseInt(numberRecordBookField.getText());

            Student newStudent = new Student(0L, fio, yearBirth, monthBirth, dayBirth, yearStudy, numberRecordBook);
            studentService.save(newStudent);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Student> studentService) {
        this.studentService = studentService;
    }

}
