package IO;

import java.io.FileReader;
import java.util.*;

public class ReaderRecords {
    private String way;
    private TreeMap<Integer, ArrayList<String> > spisok = new TreeMap<>();
    public ReaderRecords(String way){
        this.way = way;
    }

    public TreeMap<Integer, ArrayList<String>> ReadRecords() {
        try {
            FileReader read = new FileReader(way);
            Scanner scan = new Scanner(read);
            while (scan.hasNextLine()) {
                char[] line = scan.nextLine().toCharArray();
                StringBuffer names = new StringBuffer();
                int raz = 0;
                int scores = 0;
                for (int i = line.length - 1; i > 0; i--) {
                    if (Character.isDigit(line[i])) {
                        if (raz > 0) {
                            scores += (((int) line[i] - 48) * raz);
                            raz = raz * 10;
                        } else {
                            raz = 10;
                            scores = (int) line[i] - 48;
                        }

                    } else {
                        break;
                    }
                    ;
                }
                for (int i = 0; i < line.length; i++) {
                    if (Character.isLetter(line[i])) {
                        names.append(line[i]);
                    } else {
                        break;
                    }
                }
                addValues(scores, names.toString());
                names.setLength(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spisok;
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
