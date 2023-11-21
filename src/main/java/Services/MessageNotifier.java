package Services;

import Classes.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageNotifier {
    private final List<MessageObserver> observers = new ArrayList<>();
    public void addObserver(MessageObserver observer) {
        observers.add(observer);
    }
    public void notifyObservers(Message message) {
        observers.forEach(observer -> observer.notifyNewMessage(message));
    }
}