package Controller;

import Classes.Message;
import Services.MessageDecoderFactory;
import Services.MessageDecoderStrategy;
import Services.MessageNotifier;
import Services.MessageObserver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
public class MessageController implements MessageObserver {
    private final MessageDecoderFactory decoderFactory;
    private final MessageNotifier messageNotifier;
    public MessageController(MessageDecoderFactory decoderFactory, MessageNotifier messageNotifier) {
        this.decoderFactory = decoderFactory;
        this.messageNotifier = messageNotifier;
        this.messageNotifier.addObserver(this);
    }
    @GetMapping
    public List<String> decodeMessages() throws IOException {
        List<Message> messages = new ObjectMapper().readValue(
                new URL("file:src/messages.json"),
                new TypeReference<List<Message>>() {
                }
        );
        return messages.stream()
                .map(this::decodeMessage)
                .collect(Collectors.toList());
    }
    @PostMapping("/new")
    public ResponseEntity<String> receiveNewMessage(@RequestBody Message newMessage) {

        messageNotifier.notifyObservers(newMessage);
        return ResponseEntity.ok("Mesaj nou primit cu succes");
    }
    @Override
    public void notifyNewMessage(Message message) {
        String decodedMessage = decodeMessage(message);
        System.out.println("Mesaj nou: " + decodedMessage);
    }
    private String decodeMessage(Message message) {
        MessageDecoderStrategy decoder = decoderFactory.getDecoder(message.getHouse());
        return decoder.decodeMessage(message);
    }
}