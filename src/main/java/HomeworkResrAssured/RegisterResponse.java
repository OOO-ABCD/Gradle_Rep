package HomeworkResrAssured;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse {
    public String id;
    public String name;
    public String createdAt;
    public Data data;

    public static class Data {
        public int year;
        public double price;

       @JsonProperty ("CPU model")
        public String cpuModel;

       @JsonProperty ("Hard disk size")
        public String hardDiskSize;
    }
}
