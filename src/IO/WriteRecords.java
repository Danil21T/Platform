package IO;

import model.Player;

import java.io.FileWriter;
import java.util.*;


public class WriteRecords {
    private String way;
    private TreeMap<Integer, ArrayList<String>> spisok ;
    public WriteRecords(Player p, String way){
        this.way = way;
        spisok = new ReaderRecords(way).ReadRecords();
        EnterRecords(p);
    }
    public void EnterRecords(Player player) {

        try {
            addValues(player.getScore(), player.getName());
            int count = 0;
            FileWriter writer = new FileWriter(way);
            for (Map.Entry<Integer, ArrayList<String>> entry : spisok.entrySet()) {
                Integer key = entry.getKey();
                ArrayList<String> value = entry.getValue();
                for(String element : value) {
                    count++;
                    writer.write(element + " " + key + "\n");
                }
                if (count == 10)
                    break;
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addValues(int key, String value) {
        ArrayList tempList = null;
        if (spisok.containsKey(key)) {
            tempList = spisok.get(key);
            if(tempList == null)
                tempList = new ArrayList();
            tempList.add(value);
        } else {
            tempList = new ArrayList();
            tempList.add(value);
        }
        spisok.put(key,tempList);
    }
}
