package puissance4.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
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

/**
 * Implementation of the transposition table
 * Consist of a LinkedHashMap where the keys are the stateKey given by the
 * getStateKey function and the value is an array of int with the first value
 * being the depth, the second the value of the node and the third the flag
 */
public class TranspositionTable implements Serializable {
    private static final int MAX_ENTRIES = 200000;
    public LinkedHashMap<String, List<Integer>> table;

    public TranspositionTable() {
        this.table = new LinkedHashMap<String, List<Integer>>(MAX_ENTRIES, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, List<Integer>> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }

    /**
     * Add a entry to the table
     * 
     * @param key key of the entry
     * @param val list of int with depth value and flag
     */
    public void put(String key, List<Integer> val) {
        this.table.put(key, val);
    };

    /**
     * Return the value corresponding to a key or null if not in table
     * 
     * @param key key to look for
     * @return the value or null
     */
    public List<Integer> getVal(String key) {
        if (this.table.containsKey(key)) {
            return this.table.get(key);
        } else {
            return null;
        }

    }

    /**
     * 
     * @return the number of element in the table
     */
    public int size() {
        return this.table.size();
    }

    /**
     * 
     * @param key key of the entry
     * @return depth of a node
     */
    public int getDepth(String key) {
        return getVal(key).get(0);
    }

    /**
     * 
     * @param key key of the entry
     * @return value of a node
     */
    public int getValue(String key) {
        return getVal(key).get(1);

    }

    /**
     * 
     * @param key key of the entry
     * @return flag of a node
     */
    public int getFlag(String key) {
        return getVal(key).get(2); // 0 = exact , -1 = Lower , 1 = Upper
    }

    /**
     * 
     * @return the first element of the linked hash map
     */
    public String getFirstEntry() {
        if (this.size() != 0) {
            return String.valueOf(this.table.entrySet().toArray()[0]);
        } else {
            return null;
        }
    }

    /**
     * Save the LinkedHashMap to internal storage
     * 
     * @param SavedData         name of the file
     * @param linkedHashMapList LinkedHash to save
     */
    public void SaveHashMap(String SavedData, LinkedHashMap<String, List<Integer>> linkedHashMapList) {
        try {

            FileOutputStream fos = new FileOutputStream(SavedData);
            ObjectOutputStream s = new ObjectOutputStream(fos);
            s.writeObject(linkedHashMapList);
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a LinkedHashMap from a file
     * 
     * @param SavedData name of the file
     * @return the loaded LinkedHashMap
     */
    public LinkedHashMap<String, List<Integer>> LoadHashMap(String SavedData) {
        LinkedHashMap<String, List<Integer>> linkedHashMapLIST = new LinkedHashMap<String, List<Integer>>();
        try {
            File f = new File(SavedData);
            FileInputStream fileInputStream = new FileInputStream(f);
            System.out.println(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            linkedHashMapLIST = (LinkedHashMap<String, List<Integer>>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkedHashMapLIST;
    }
}
