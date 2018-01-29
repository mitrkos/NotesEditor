package notes.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import notes.MainApp;
import notes.model.User;

import java.io.IOException;

import static notes.Utils.InputCheckUtil.isInputValid;
import static notes.Utils.PrepareJavaFXUtils.initLoader;
import static notes.Utils.PrepareJavaFXUtils.showScene;

public class AuthorizationOverviewController {
    @FXML
    private TextField loginInputField;
    @FXML
    private TextField newUserField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void showInputError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login is wrong");
        alert.setHeaderText("Please input correct login or create new user");

        alert.showAndWait();
    }

    public void showNoteListOverview(User user) {
        try {
            FXMLLoader loader = initLoader("Resources/NoteListOverview.fxml");
            AnchorPane noteListOverview = loader.load();

            NoteListOverviewController controller = loader.getController();
            controller.setMainApp(mainApp);

            controller.setUser(user);

            showScene(noteListOverview, mainApp.getPrimaryStage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {

    }

    @FXML
    public void handledGO() {
        String inputLogin = loginInputField.getText();
        if (isInputValid(inputLogin) && mainApp.getUsersMap().containsKey(inputLogin)) {
            showNoteListOverview(mainApp.getUsersMap().get(inputLogin));
        } else {
            showInputError();
        }
    }

    @FXML
    public void handledNewUser() {
        String inputLogin = newUserField.getText();
        if (isInputValid(inputLogin) && !mainApp.getUsersMap().containsKey(inputLogin)) {
            User newUser = new User(inputLogin);
            mainApp.getUsersMap().put(inputLogin, newUser);
            showNoteListOverview(newUser);
        } else {
            showInputError();
        }
    }
}
