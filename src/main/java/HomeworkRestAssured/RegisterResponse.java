package HomeworkRestAssured;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {
    public String id;

    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Data {
           }
}
