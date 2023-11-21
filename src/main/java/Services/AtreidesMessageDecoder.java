package Services;

import Classes.Message;
public class AtreidesMessageDecoder implements MessageDecoderStrategy {
    @Override
    public String decodeMessage(Message message) {
        String encodedContent = message.getContent();
        if (encodedContent == null) {
            return "Atreides: [Unable to decode - Content is null]";
        }
        StringBuilder decodedContent = new StringBuilder();
        for (int i = 0; i < encodedContent.length(); i++) {
            char currentChar = encodedContent.charAt(i);
            char decodedChar = (char) (currentChar + 3);
            decodedContent.append(decodedChar);
        }
        return "Atreides: " + decodedContent.toString();
    }
}