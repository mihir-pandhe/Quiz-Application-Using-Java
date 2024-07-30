package src.main.java.models;

public class TestTaker {
    private String testTakerID;
    private String name;

    public TestTaker(String testTakerID, String name) {
        this.testTakerID = testTakerID;
        this.name = name;
    }

    public String getTestTakerID() {
        return testTakerID;
    }

    public String getName() {
        return name;
    }
}
