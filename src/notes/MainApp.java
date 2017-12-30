package notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import notes.model.Note;
import notes.model.User;
import notes.view.AuthorizationOverviewController;
import notes.view.NoteEditDialogController;
import notes.view.NoteListOverviewController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainApp extends Application {

    private Stage primaryStage;
    //will replace in DB
    private Map<String, User> usersMap = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("NotesEditorApp");

        showAuthorizationOverview();
    }

    public MainApp() {
        //Hardcode for testing
        usersMap.put("One", new User("One"));
        usersMap.put("Two", new User("Two"));
        usersMap.put("Three", new User("Three"));
        usersMap.put("Four", new User("Four"));
        usersMap.put("Five", new User("Five"));
    }

    public Map<String, User> getUsersMap() {
        return usersMap;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showAuthorizationOverview() {
        try {
                    //Можно вынести в отдельный метод
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("view/AuthorizationOverview.fxml"));
                    AnchorPane authorizationOverview = loader.load();

                    AuthorizationOverviewController controller = loader.getController();
                    controller.setMainApp(this);


                    Scene scene = new Scene(authorizationOverview);
                    primaryStage.setScene(scene);
                    primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Можно вынести в класс где используется, т.к. этот метод больше подходит под логику AuthorizationOverviewController
    public void showNoteListOverview(User user) {
        try {
                    //Можно вынести в отдельный метод
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("view/NoteListOverview.fxml"));

                    AnchorPane noteListOverview = loader.load();

                    NoteListOverviewController controller = loader.getController();
                    controller.setMainApp(this);

            controller.setUser(user);

                    Scene scene = new Scene(noteListOverview);
                    primaryStage.setScene(scene);
                    primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showNoteEditDialog(Note note) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/NoteEditDialog.fxml"));

            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit note");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NoteEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setNote(note);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}