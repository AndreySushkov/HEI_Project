package ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class AddGroupMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

    private IService<Group> groupService;
    private IService<Student> studentService;

    @FXML
    private Button applyButton;

    @FXML
    private TextField titleField;

    @FXML
    void initialize() {
        applyButton.setOnAction(actionEvent -> {
            String title = titleField.getText();

            Group newGroup = new Group(0L, title);
            groupService.save(newGroup);

            String path = "GroupMenu/GroupMenu.fxml";
            GroupMenuController groupMenuController = (GroupMenuController) sceneLoader.loadScene(path);
            groupMenuController.transferParameters(groupService, studentService);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Group> groupService, IService<Student> studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

}
