package ro.uvt.info.Partial;
import Classes.Message;
import Services.AtreidesMessageDecoder;
import Services.HarkonnenMessageDecoder;
import Services.MessageDecoderStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@SpringBootApplication
public class SpPartialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpPartialApplication.class, args);
    }
    @Bean
    public CommandLineRunner decodeAndPrintMessages() {
        return args -> {
            List<Message> messages = readMessagesFromFile();
            AtreidesMessageDecoder atreidesDecoder = new AtreidesMessageDecoder();
            HarkonnenMessageDecoder harkonnenDecoder = new HarkonnenMessageDecoder();

            for (Message message : messages) {
                MessageDecoderStrategy decoder;
                switch (message.getHouse().toLowerCase()) {
                    case "atreides":
                        decoder = atreidesDecoder;
                        break;
                    case "harkonnen":
                        decoder = harkonnenDecoder;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown house: " + message.getHouse());
                }
                String decodedMessage = decoder.decodeMessage(message);
                System.out.println("Decoded Message: " + decodedMessage);
            }
        };
    }
    private List<Message> readMessagesFromFile() throws IOException {
        return new ObjectMapper().readValue(
                new URL("file:src/messages.json"),
                new TypeReference<List<Message>>() {
                }
        );
    }
}