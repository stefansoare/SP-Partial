package Services;

import Classes.Message;

public class HarkonnenMessageDecoder implements MessageDecoderStrategy {
    @Override
    public String decodeMessage(Message message) {
        String encodedContent = message.getContent();

        if (encodedContent == null) {
            return "Harkonnen: [Content is null]";
        }
        StringBuilder decodedContent = new StringBuilder();
        for (int i = 0; i < encodedContent.length(); i++) {
            char currentChar = encodedContent.charAt(i);
            char decodedChar = (char) (currentChar - 2);
            decodedContent.append(decodedChar);
        }
        return "Harkonnen: " + decodedContent.toString();
    }
}