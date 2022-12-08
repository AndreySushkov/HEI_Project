package ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.util.List;

public class UpdateGroupStudentsMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

    private IService<Group> groupService;
    private IService<Student> studentService;

    private Group group;

    private ObservableList<Student> students;
    private ObservableList<Student> availableStudents;

    @FXML
    private Button addStudentButton;

    @FXML
    private Button applyButton;

    @FXML
    private TableColumn<Student, Integer> availableStudentDayBirthTableColumn;

    @FXML
    private TableColumn<Student, String> availableStudentFioTableColumn1;

    @FXML
    private TableColumn<Student, Long> availableStudentIdTableColumn1;

    @FXML
    private TableColumn<Student, Integer> availableStudentMonthBirthTableColumn;

    @FXML
    private TableView<Student> availableStudentTable;

    @FXML
    private TableColumn<Student, Integer> availableStudentYearBirthTableColumn;

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
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, Integer> studentYearBirthTableColumn;

    @FXML
    void initialize() {
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("id"));
        studentFioTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("fio"));
        studentDayBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("dayBirth"));
        studentMonthBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("monthBirth"));
        studentYearBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("yearBirth"));

        availableStudentIdTableColumn1.setCellValueFactory(new PropertyValueFactory<Student, Long>("id"));
        availableStudentFioTableColumn1.setCellValueFactory(new PropertyValueFactory<Student, String>("fio"));
        availableStudentDayBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("dayBirth"));
        availableStudentMonthBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("monthBirth"));
        availableStudentYearBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("yearBirth"));

        addStudentButton.setOnAction(actionEvent -> {
            Student selectedStudent = availableStudentTable.getSelectionModel().getSelectedItem();
            students.add(selectedStudent);
            availableStudents.remove(selectedStudent);
            updateStudentTable();
            updateAvailableStudentTable();
        });

        removeStudentButton.setOnAction(actionEvent -> {
            Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
            students.remove(selectedStudent);
            availableStudents.add(selectedStudent);
            updateStudentTable();
            updateAvailableStudentTable();
        });

        applyButton.setOnAction(actionEvent -> {
            for (Student student : students) {
                if (student.getGroup_id() == 0) {
                    Student newStudent = new Student(student.getId(), student.getFio(), student.getYearBirth(), student.getMonthBirth(), student.getDayBirth(), student.getYearStudy(), student.getNumberRecordBook(), group.getId());
                    studentService.update(newStudent);
                }
            }
            for (Student student : availableStudents) {
                if (student.getGroup_id() != 0) {
                    Student newStudent = new Student(student.getId(), student.getFio(), student.getYearBirth(), student.getMonthBirth(), student.getDayBirth(), student.getYearStudy(), student.getNumberRecordBook(), null);
                    studentService.update(newStudent);
                }
            }

            String path = "GroupMenu/GroupMenu.fxml";
            GroupMenuController groupMenuController = (GroupMenuController) sceneLoader.loadScene(path);
            groupMenuController.transferParameters(groupService, studentService);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Group> groupService, IService<Student> studentService, Group group) {
        this.studentService = studentService;
        this.groupService = groupService;

        this.group = group;

        start();
    }

    private void start() {
        List<Student> allStudents = studentService.getAll();
        availableStudents = FXCollections.observableArrayList();
        students = FXCollections.observableArrayList();
        for (Student student : allStudents) {
            if (student.getGroup_id() == 0) {
                availableStudents.add(student);
            }
            if (student.getGroup_id() == group.getId()) {
                students.add(student);
            }
        }

        updateAvailableStudentTable();
        updateStudentTable();
    }

    private void updateAvailableStudentTable() {
        availableStudentTable.setItems(availableStudents);
    }

    private void updateStudentTable() {
        studentTable.setItems(students);
    }
}
