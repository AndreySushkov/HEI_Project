package ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.util.Optional;

public class GroupMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

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
            String path = "GroupMenu/AddGroupMenu.fxml";
            AddGroupMenuController addGroupMenuController = (AddGroupMenuController) sceneLoader.loadScene(path);
            addGroupMenuController.transferParameters(groupService, studentService);

            addGroupButton.getScene().getWindow().hide();
        });

        removeGroupButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление группы");
            alert.setHeaderText("Вы точно хотите удалить группу?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
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
            }
        });

        updateGroupButton.setOnAction(actionEvent -> {
            Group selectedGroup = groupTable.getSelectionModel().getSelectedItem();

            String path = "GroupMenu/UpdateGroupMenu.fxml";
            UpdateGroupMenuController updateGroupMenuController = (UpdateGroupMenuController) sceneLoader.loadScene(path);
            updateGroupMenuController.transferParameters(studentService, groupService, selectedGroup);

            updateGroupButton.getScene().getWindow().hide();
        });

        updateStudentsButton.setOnAction(actionEvent -> {
            Group selectedGroup = groupTable.getSelectionModel().getSelectedItem();

            String path = "GroupMenu/UpdateGroupStudentsMenu.fxml";
            UpdateGroupStudentsMenuController updateGroupStudentsMenuController = (UpdateGroupStudentsMenuController) sceneLoader.loadScene(path);
            updateGroupStudentsMenuController.transferParameters(groupService, studentService, selectedGroup);

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
