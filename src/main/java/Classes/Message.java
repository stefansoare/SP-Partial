package Classes;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private Date date;
    @JsonProperty("message")
    private String content;
    private String house;

    public String getContent() {
        return content;
    }
    public String getHouse() {
        return house;
    }
    public Date getDate() {
        return date;
    }
}