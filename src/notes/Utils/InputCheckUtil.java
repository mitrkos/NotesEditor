package notes.Utils;

public class InputCheckUtil {

    //ApacheCommons - StringUtils.isEmpty() - когда подключишь Maven
    public static boolean isInputValid(String string) {
        return string != null && string.length() > 0;
    }
}
