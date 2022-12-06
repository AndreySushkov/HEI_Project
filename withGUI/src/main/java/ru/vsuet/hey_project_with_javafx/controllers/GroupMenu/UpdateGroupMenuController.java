package ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class UpdateGroupMenuController {
    IService<Group> groupService;

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

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Group> groupService, Group group) {
        this.groupService = groupService;

        this.group = group;

        start();
    }

    private void start() {
        titleField.setText(group.getTitle());
    }
}
