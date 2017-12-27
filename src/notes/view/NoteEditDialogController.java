package notes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import notes.model.Note;

import static notes.Utils.InputCheckUtil.isInputValid;

public class NoteEditDialogController {

    private Stage dialogStage;
    private Note note;
    private boolean isOkClicked = false;

    @FXML
    private TextField editedTitle;
    @FXML
    private TextField editedBody;

    public Stage getDialogStage() {
        return dialogStage;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Note getNote() {
        return note;
    }
    public void setNote(Note note) {
        this.note = note;
        editedTitle.setText(note.getTitle());
        editedBody.setText(note.getBody());
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }
    public void setOkClicked(boolean okClicked) {
        isOkClicked = okClicked;
    }


    @FXML
    private void initialize() {}
    @FXML
    private void handledOK() {
        String newTitle = editedTitle.getText();
        String newBody = editedBody.getText();
        if (isInputValid(newTitle) && isInputValid(newBody)) {
            note.setTitle(newTitle);
            note.setBody(newBody);

            isOkClicked = true;
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");

            alert.showAndWait();
        }
    }
    @FXML
    private void handledCancel() {
        dialogStage.close();
    }
}
