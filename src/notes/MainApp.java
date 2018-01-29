package notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import notes.model.User;
import notes.view.AuthorizationOverviewController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static notes.Utils.PrepareJavaFXUtils.initLoader;
import static notes.Utils.PrepareJavaFXUtils.showScene;

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
            FXMLLoader loader = initLoader("Resources/AuthorizationOverview.fxml");
            AnchorPane authorizationOverview = loader.load();

            AuthorizationOverviewController controller = loader.getController();
            controller.setMainApp(this);

            showScene(authorizationOverview, primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}