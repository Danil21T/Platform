package Test;

import IO.ReaderRecords;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ReaderRecordsTest {

    @Test
    public void readRecords() {
        ReaderRecords r = new ReaderRecords("src/Record.txt");
        TreeMap<Integer, ArrayList<String>> m = r.ReadRecords();
        boolean empty = true;
        for (Map.Entry<Integer, ArrayList<String>> entry : m.entrySet()) {
            ArrayList<String> value = entry.getValue();
            for(String element : value) {
                empty = false;
                break;
            }
        }
        Assert.assertFalse(empty);

    }

    @Test
    public void readRecords_Non() {
        ReaderRecords r = new ReaderRecords("src/Test/Empty.txt");
        Map<Integer, ArrayList<String>> m = r.ReadRecords();
        boolean empty = true;
        for (Map.Entry<Integer, ArrayList<String>> entry : m.entrySet()) {
            ArrayList<String> value = entry.getValue();
            for(String element : value) {
                empty = false;
            }
        }
        Assert.assertTrue(empty);


    }
}