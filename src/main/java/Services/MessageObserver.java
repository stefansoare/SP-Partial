package Services;

import Classes.Message;

public interface MessageObserver {
    void notifyNewMessage(Message message);
}