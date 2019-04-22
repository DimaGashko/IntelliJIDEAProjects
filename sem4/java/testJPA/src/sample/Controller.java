package sample;

import dao.StudentDAO;
import dao.SubjDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tables.Student;
import tables.Subject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class Controller {
    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, Integer> ageColumn;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private ListView<Subject> subjList;

    private StudentDAO studentDAO;
    private SubjDAO subjDAO;

    @FXML
    public void initialize() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();
        studentDAO = new StudentDAO(em);
        subjDAO = new SubjDAO(em);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.setItems(FXCollections.observableList(studentDAO.findAll()));
        subjList.setItems(FXCollections.observableList(subjDAO.findAll()));
    }

    public void addStudent() {
        String fio = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        Student student = new Student();
        student.setFio(fio);
        student.setAge(age);

        studentDAO.add(student);
        table.setItems(FXCollections.observableList(studentDAO.findAll()));
    }

    public void deleteStudent() {
        Student student = table.getSelectionModel().getSelectedItem();
        if (student != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удалить студента");
            alert.setContentText(String.format("Удаляем студента (%s) ?", student.toString()));
            alert.setHeaderText(null);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (ButtonType.OK.equals(buttonType.get())) {
                studentDAO.delete(student);
                table.setItems(FXCollections.observableList(studentDAO.findAll()));
            }
        }
    }
}
