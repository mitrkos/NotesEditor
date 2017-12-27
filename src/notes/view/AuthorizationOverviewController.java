package notes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import notes.MainApp;
import notes.model.User;

import static notes.Utils.InputCheckUtil.isInputValid;

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

    @FXML
    private void initialize() {

    }

    @FXML
    public void handledGO() {
        String inputLogin = loginInputField.getText();
        if (isInputValid(inputLogin) && mainApp.getUsersMap().containsKey(inputLogin)) {
            mainApp.showNoteListOverview(mainApp.getUsersMap().get(inputLogin));
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
            mainApp.showNoteListOverview(newUser);
        } else {
            showInputError();
        }
    }

}
