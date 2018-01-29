package notes.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Note {

    private final StringProperty title;
    private final StringProperty body;

    public Note() {
        this(null, null);
    }

    public Note(String title, String body) {
        this.title = new SimpleStringProperty(title);
        this.body = new SimpleStringProperty(body);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getBody() {
        return body.get();
    }

    public StringProperty bodyProperty() {
        return body;
    }

    public void setBody(String body) {
        this.body.set(body);
    }
}
