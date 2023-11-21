package Services;
import org.springframework.stereotype.Component;

@Component
public class MessageDecoderFactory {
    private final AtreidesMessageDecoder atreidesMessageDecoder;
    private final HarkonnenMessageDecoder harkonnenMessageDecoder;
    public MessageDecoderFactory(AtreidesMessageDecoder atreidesMessageDecoder, HarkonnenMessageDecoder harkonnenMessageDecoder) {
        this.atreidesMessageDecoder = atreidesMessageDecoder;
        this.harkonnenMessageDecoder = harkonnenMessageDecoder;
    }
    public MessageDecoderStrategy getDecoder(String house) {
        switch (house.toLowerCase()) {
            case "atreides":
                return atreidesMessageDecoder;
            case "harkonnen":
                return harkonnenMessageDecoder;
            default:
                throw new IllegalArgumentException("Casa specificată nu are un decodor asociat");
        }
    }
}