package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import model.User;
import view.model.UserDTO;

import java.util.List;

public class AdminView {
    private TableView<UserDTO> userTableView;
    private final ObservableList<UserDTO> usersObservableList;
    private TextField usernameTextField;
    private TextField idTextField;
    private Label usernameLabel;
    private Label idLabel;
    private Button saveButton;
    private Button deleteButton;
    private Button reportButton;

    public AdminView(Stage primaryStage, List<UserDTO> users) {
        primaryStage.setTitle("Admin Panel");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 500);
        primaryStage.setScene(scene);

        usersObservableList = FXCollections.observableArrayList(users);
        initTableView(gridPane);

        initControlOptions(gridPane);

        primaryStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initTableView(GridPane gridPane) {
        userTableView = new TableView<>();

        userTableView.setPlaceholder(new Label("No users to display"));

        TableColumn<UserDTO, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(150);

        TableColumn<UserDTO, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setMinWidth(300);

        userTableView.getColumns().addAll(idColumn, usernameColumn);

        userTableView.setItems(usersObservableList);

        gridPane.add(userTableView, 0, 0, 5, 1);
    }

    private void initControlOptions(GridPane gridPane) {
        idLabel = new Label("User ID");
        gridPane.add(idLabel, 0, 1);

        idTextField = new TextField();
        gridPane.add(idTextField, 1, 1);

        usernameLabel = new Label("Username");
        gridPane.add(usernameLabel, 0, 2);

        usernameTextField = new TextField();
        gridPane.add(usernameTextField, 1, 2);

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        saveButton = new Button("Save");
        deleteButton = new Button("Delete");
        reportButton = new Button("Create Report");

        buttonBox.getChildren().addAll(saveButton, deleteButton,reportButton);

        gridPane.add(buttonBox, 5, 0, 1, 3);
    }

    public void addSaveButtonListener(EventHandler<ActionEvent> saveButtonListener) {
        saveButton.setOnAction(saveButtonListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addReportButtonListener(EventHandler<ActionEvent> reportButtonListener) {
        reportButton.setOnAction(reportButtonListener);
    }

    public void displayAlertMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getUserId() {
        return idTextField.getText();
    }

    public void addUserToObservableList(UserDTO userDTO) {
        this.usersObservableList.add(userDTO);
    }

    public void removeUserFromObservableList(UserDTO userDTO) {
        this.usersObservableList.remove(userDTO);
    }

    public TableView<UserDTO> getUserTableView() {
        return userTableView;
    }

    public void addDisplayAlertMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
    public UserDTO getSelectedUser() {
        return userTableView.getSelectionModel().getSelectedItem();
    }

}

