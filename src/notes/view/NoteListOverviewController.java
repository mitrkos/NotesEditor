package notes.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import notes.MainApp;
import notes.model.Note;
import notes.model.User;

import java.io.IOException;

import static notes.Utils.PrepareJavaFXUtils.initLoader;

public class NoteListOverviewController {

    private User user;
    private MainApp mainApp;

    @FXML
    private Label username;
    @FXML
    private TableView<Note> noteTable;
    @FXML
    private TableColumn<Note, String> noteColumn;
    @FXML
    private Label showedNodeTitle;
    @FXML
    private TextArea showedNodeBody;

    @FXML
    private void initialize() {
        noteColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        noteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserNote(newValue)
        );
    }

    @FXML
    private void handledLogOut() {
        mainApp.showAuthorizationOverview();
    }

    @FXML
    private void handledEdit() {
        Note selectedNote = noteTable.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            boolean isOkClicked = showNoteEditDialog(selectedNote);
            if (isOkClicked) {
                showUserNote(selectedNote);
            }
        } else {
            showAlert();
        }
    }

    @FXML
    private void handledNew() {
        Note tempNote = new Note();
        boolean isOkClicked = showNoteEditDialog(tempNote);
        if (isOkClicked) {
            user.getNotesData().add(tempNote);
        }
    }

    @FXML
    private void handledDelete() {
        int selectedIndex = noteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            noteTable.getItems().remove(selectedIndex);
            //тут удаляю запись, и как-то еще надо удалить listener? не получается разобраться
        } else {
            showAlert();
        }
    }

    public void setUser(User user) {
        this.user = user;
        username.setText(this.user.getLogin());
        noteTable.setItems(user.getNotesData());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void showUserNote(Note note) {
        if (note != null) {
            showedNodeTitle.setText(note.getTitle());
            showedNodeBody.setText(note.getBody());
        } else {
            showedNodeTitle.setText("");
            showedNodeBody.setText("");
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Note selected");
        alert.setContentText("Please select a note in the table");

        alert.showAndWait();
    }

    public boolean showNoteEditDialog(Note note) {
        try {
            FXMLLoader loader = initLoader("Resources/NoteEditDialog.fxml");
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit note");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NoteEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setNote(note);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
