import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class App extends JFrame{
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JTable table1;
    private JButton addTeacher;
    private JButton removeTeacher;
    private JButton updateTeacher;
    private JScrollPane tableScrollPanel;

    public App() {
        this.setContentPane(mainPanel);
        this.setVisible(true);

        this.setSize(640, 480);

        String[] collumnNames = {
                "id",
                "fio",
                "age"
        };
        String[][] data = {};
        table1 = new JTable(data, collumnNames);
        tableScrollPanel.setViewportView(table1);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
}
