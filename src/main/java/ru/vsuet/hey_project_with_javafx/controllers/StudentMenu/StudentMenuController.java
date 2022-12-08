package ru.vsuet.hey_project_with_javafx.controllers.StudentMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.util.Optional;

public class StudentMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

    private IService<Student> studentService;

    private ObservableList<Student> students;

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
            String path = "StudentMenu/AddStudentMenu.fxml";
            AddStudentMenuController addStudentMenuController = (AddStudentMenuController) sceneLoader.loadScene(path);
            addStudentMenuController.transferParameters(studentService);

            addStudentButton.getScene().getWindow().hide();
        });

        updateStudentButton.setOnAction(actionEvent -> {
            Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();

            String path = "StudentMenu/UpdateStudentMenu.fxml";
            UpdateStudentMenuController updateStudentMenuController = (UpdateStudentMenuController) sceneLoader.loadScene(path);
            updateStudentMenuController.transferParameters(studentService, selectedStudent);

            updateStudentButton.getScene().getWindow().hide();
        });

        removeStudentButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление студента");
            alert.setHeaderText("Вы точно хотите удалить студента?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
                studentService.removeById(selectedStudent.getId());
                students.remove(selectedStudent);
                updateStudentTable();
            }
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

