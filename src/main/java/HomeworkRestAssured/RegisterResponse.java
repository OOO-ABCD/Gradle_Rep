package HomeworkRestAssured;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {
    public String id;
    public String name;
    public String createdAt;
    public Data data;

    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Data {
        public int year;
        public double price;

        @JsonProperty ("CPU model")
        public String cpuModel;

        @JsonProperty ("Hard disk size")
        public String hardDiskSize;
    }
}
