import java.util.Observable;

public class Messages extends Observable {

    private String message;

    public Messages() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        // Message has changed
        this.setChanged();
        // Notify
        this.notifyObservers(this.getMessage());
    }
}