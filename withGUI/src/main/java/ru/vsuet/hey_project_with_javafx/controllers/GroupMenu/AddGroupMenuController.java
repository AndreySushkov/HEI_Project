package ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.io.IOException;

public class AddGroupMenuController {
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

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GroupMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            GroupMenuController groupMenuController = loader.getController();
            groupMenuController.transferParameters(groupService, studentService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Group> groupService, IService<Student> studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

}
