module ru.vsuet.hey_project_with_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ru.vsuet.hey_project_with_javafx to javafx.fxml;
    opens ru.vsuet.hey_project_with_javafx.domain to javafx.fxml;
    opens ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu to javafx.fxml;
    opens ru.vsuet.hey_project_with_javafx.controllers.CourseMenu to javafx.fxml;
    opens ru.vsuet.hey_project_with_javafx.controllers.StudentMenu to javafx.fxml;
    opens ru.vsuet.hey_project_with_javafx.controllers.GroupMenu to javafx.fxml;

    exports ru.vsuet.hey_project_with_javafx;
    exports ru.vsuet.hey_project_with_javafx.domain;
    exports ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;
    exports ru.vsuet.hey_project_with_javafx.controllers.CourseMenu;
    exports ru.vsuet.hey_project_with_javafx.controllers.StudentMenu;
    exports ru.vsuet.hey_project_with_javafx.controllers.GroupMenu;
}