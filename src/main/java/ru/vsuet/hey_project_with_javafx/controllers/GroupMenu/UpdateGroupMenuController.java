package ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class UpdateGroupMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

    private IService<Group> groupService;
    private IService<Student> studentService;

    Group group;

    @FXML
    private Button applyButton;

    @FXML
    private TextField titleField;

    @FXML
    void initialize() {
        applyButton.setOnAction(actionEvent -> {
            String title = titleField.getText();

            Group newGroup = new Group(group.getId(), title);
            groupService.update(newGroup);

            String path = "GroupMenu/GroupMenu.fxml";
            GroupMenuController groupMenuController = (GroupMenuController) sceneLoader.loadScene(path);
            groupMenuController.transferParameters(groupService, studentService);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Student> studentService, IService<Group> groupService, Group group) {
        this.groupService = groupService;
        this.studentService = studentService;

        this.group = group;

        start();
    }

    private void start() {
        titleField.setText(group.getTitle());
    }
}
