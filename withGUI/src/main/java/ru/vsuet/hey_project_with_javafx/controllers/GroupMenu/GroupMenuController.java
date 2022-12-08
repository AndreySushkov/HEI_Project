package ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;

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
import ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu.AddTeacherMenuController;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.io.IOException;

public class GroupMenuController {
    private IService<Group> groupService;
    private IService<Student> studentService;

    private ObservableList<Group> groups;
    private ObservableList<Student> students;

    @FXML
    private Button addGroupButton;

    @FXML
    private TableColumn<Group, Long> groupIdTableColumn;

    @FXML
    private TableView<Group> groupTable;

    @FXML
    private TableColumn<Group, String> groupTitleTableColumn;

    @FXML
    private Button removeGroupButton;

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
    private Button updateGroupButton;

    @FXML
    private Button updateStudentsButton;

    @FXML
    void initialize() {
        groupIdTableColumn.setCellValueFactory(new PropertyValueFactory<Group, Long>("id"));
        groupTitleTableColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("title"));

        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("id"));
        studentFioTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("fio"));
        studentDayBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("dayBirth"));
        studentMonthBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("monthBirth"));
        studentYearBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("yearBirth"));

        groupTable.setOnMouseClicked(mouseEvent -> {
            Group selectedGroup = groupTable.getSelectionModel().getSelectedItem();
            Long groupId = selectedGroup.getId();

            students = FXCollections.observableArrayList();
            for (Student student : studentService.getAll()) {
                if (groupId == student.getGroup_id()) {
                    students.add(student);
                }
            }

            studentTable.setItems(students);
        });

        addGroupButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddGroupMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            AddGroupMenuController addGroupMenuController = loader.getController();
            addGroupMenuController.transferParameters(groupService, studentService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            addGroupButton.getScene().getWindow().hide();
        });

        removeGroupButton.setOnAction(actionEvent -> {
            Group selectedGroup = groupTable.getSelectionModel().getSelectedItem();
            Long groupId = selectedGroup.getId();

            for(Student student : studentService.getAll()) {
                if (groupId == student.getGroup_id()) {
                    Student newStudent = new Student(student.getId(), student.getFio(), student.getYearBirth(), student.getMonthBirth(), student.getDayBirth(), student.getYearStudy(), student.getNumberRecordBook(), null);
                    studentService.update(newStudent);
                }
            }

            groups.remove(selectedGroup);
            groupService.removeById(selectedGroup.getId());

            updateGroupTable();
        });

        updateGroupButton.setOnAction(actionEvent -> {
            Group selectedGroup = groupTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UpdateGroupMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            UpdateGroupMenuController updateGroupMenuController = loader.getController();
            updateGroupMenuController.transferParameters(studentService, groupService, selectedGroup);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            updateGroupButton.getScene().getWindow().hide();
        });

        updateStudentsButton.setOnAction(actionEvent -> {
            Group selectedGroup = groupTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UpdateGroupStudentsMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            UpdateGroupStudentsMenuController updateGroupStudentsMenuController = loader.getController();
            updateGroupStudentsMenuController.transferParameters(groupService, studentService, selectedGroup);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            updateStudentsButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Group> groupService, IService<Student> studentService) {
        this.groupService = groupService;
        this.studentService = studentService;

        start();
    }

    private void start() {
        groups = FXCollections.observableArrayList(groupService.getAll());

        updateGroupTable();
    }

    private void updateGroupTable() {
        groupTable.setItems(groups);
    }

}
