package Services;

import Classes.Message;

public interface MessageDecoderStrategy {
    String decodeMessage(Message message);
}