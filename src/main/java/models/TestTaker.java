package models;

import java.io.Serializable;

public class TestTaker implements Serializable {
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