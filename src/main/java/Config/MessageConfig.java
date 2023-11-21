package Config;

import Services.AtreidesMessageDecoder;
import Services.HarkonnenMessageDecoder;
import Services.MessageDecoderStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    @Bean
    public MessageDecoderStrategy atreidesMessageDecoder() {
        return new AtreidesMessageDecoder();
    }
    @Bean
    public MessageDecoderStrategy harkonnenMessageDecoder() {
        return new HarkonnenMessageDecoder();
    }
}