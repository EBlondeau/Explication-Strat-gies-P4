package puissance4.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;

public class TranspositionTable {
    private static final int MAX_ENTRIES = 2000;
    public LinkedHashMap<String, List<Integer>> table;

    public TranspositionTable() {
        this.table = new LinkedHashMap<String, List<Integer>>(MAX_ENTRIES, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, List<Integer>> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }

    public void put(String key, List<Integer> val) {
        this.table.put(key, val);
    };

    public List<Integer> getVal(String key) {
        if (this.table.containsKey(key)) {
            return this.table.get(key);
        } else {
            return null;
        }

    }

    public int size() {
        return this.table.size();
    }

    public int getDepth(String key) {
        return getVal(key).get(0);
    }

    public int getValue(String key) {
        return getVal(key).get(1);

    }

    public int getFlag(String key) {
        return getVal(key).get(2); // 0 = exact , -1 = Lower , 1 = Upper
    }

    public String FindFirstEntryWithArrayMethod() {
        if (this.size() != 0) {
            return String.valueOf(this.table.entrySet().toArray()[0]);
        } else {
            return null;
        }
    }

    public void SaveHashMapToInternalStorage(String SavedData, LinkedHashMap<String, List<Integer>> linkedHashMapList) {
        try {

            FileOutputStream fos = new FileOutputStream(SavedData);
            ObjectOutputStream s = new ObjectOutputStream(fos);
            s.writeObject(linkedHashMapList);
            s.close();
            System.out.println("bravo");

        } catch (Exception e) {
            System.out.println("en fait ça marche aps");
        }
    }

    public LinkedHashMap<String, List<Integer>> LoadHashMapFromInternalStorage(String SavedData) {
        LinkedHashMap<String, List<Integer>> linkedHashMapLIST = new LinkedHashMap<String, List<Integer>>();
        try {
            File f = new File(SavedData);
            FileInputStream fileInputStream = new FileInputStream(f);
            System.out.println(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            linkedHashMapLIST = (LinkedHashMap<String, List<Integer>>) objectInputStream.readObject();
            System.out.println("FFFFFF" + linkedHashMapLIST.size());
            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            System.out.println("ça veut pas lire zzzz");
        }
        return linkedHashMapLIST;
    }
}
