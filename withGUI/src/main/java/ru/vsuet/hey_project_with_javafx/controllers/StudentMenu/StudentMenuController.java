package ru.vsuet.hey_project_with_javafx.controllers.StudentMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.io.IOException;

public class StudentMenuController {
    private IService<Student> studentService;

    ObservableList<Student> students;

    @FXML
    private Button addStudentButton;

    @FXML
    private Button removeStudentButton;

    @FXML
    private TableColumn<Student, Integer> studentDayBirthTableColumn;

    @FXML
    private TableColumn<Student, String> studentFioTableColumn;

    @FXML
    private TableColumn<Student, Long> studentIdTableColumn;

    @FXML
    private TableColumn<Student, Integer> studentMonthBirthTableColumn;

    @FXML
    private TableColumn<Student, Integer> studentNumberRecordBookTableColumn;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, Integer> studentYearBirthTableColumn;

    @FXML
    private TableColumn<Student, Integer> studentYearStudyTableColumn;

    @FXML
    private Button updateStudentButton;

    @FXML
    void initialize() {
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("id"));
        studentFioTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("fio"));
        studentDayBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("dayBirth"));
        studentMonthBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("monthBirth"));
        studentYearBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("yearBirth"));
        studentYearStudyTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("yearStudy"));
        studentNumberRecordBookTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("numberRecordBook"));

        addStudentButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddStudentMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            AddStudentMenuController addStudentMenuController = loader.getController();
            addStudentMenuController.transferParameters(studentService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        updateStudentButton.setOnAction(actionEvent -> {
            Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UpdateStudentMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UpdateStudentMenuController updateStudentMenuController = loader.getController();
            updateStudentMenuController.transferParameters(studentService, selectedStudent);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        removeStudentButton.setOnAction(actionEvent -> {
            Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
            studentService.removeById(selectedStudent.getId());
            students.remove(selectedStudent);
            updateStudentTable();
        });
    }

    public void transferParameters(IService<Student> studentService) {
        this.studentService = studentService;

        start();
    }

    private void start() {
        students = FXCollections.observableArrayList(studentService.getAll());

        updateStudentTable();
    }

    public void updateStudentTable() {
        studentTable.setItems(students);
    }
}

