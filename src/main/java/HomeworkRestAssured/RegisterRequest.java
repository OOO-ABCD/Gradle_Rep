package HomeworkRestAssured;

public class RegisterRequest {
    public String name;
    public Data data;

    public RegisterRequest(String name, Data data) {
        this.name = name;
        this.data = data;
    }

    public static class Data {
        public int year;
        public double price;
        public String cpuModel;
        public String hardDiskSize;

        public Data(int year, double price, String cpuModel, String hardDiskSize) {
            this.year = year;
            this.price = price;
            this.cpuModel = cpuModel;
            this.hardDiskSize = hardDiskSize;
        }
    }
}


