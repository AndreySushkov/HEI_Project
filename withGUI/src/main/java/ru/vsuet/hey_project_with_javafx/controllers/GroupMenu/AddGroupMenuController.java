package ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class AddGroupMenuController {
    private IService<Group> groupService;

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

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Group> groupService) {
        this.groupService = groupService;
    }

}
