package notes.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import notes.MainApp;

public class PrepareJavaFXUtils {
    public static FXMLLoader initLoader(String viewFXMLPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(viewFXMLPath));

        return loader;
    }

    public static void showScene(AnchorPane pane, Stage stage) {
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
