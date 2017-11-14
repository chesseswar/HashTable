import java.util.*;
import java.io.*;

public class HashTable {
    Node[] table;
    int size;
    public HashTable(){
        table = new Node[101];
        size = 0;
    }

    public HashTable(int initCap){
        table = new Node[initCap];
        size = 0;
    }

    public int size(){
        return size;
    }

    public Object put(Object key, Object value) {
        if (table.length == size){
            return null;
        }
        Object output = null;
        int hash = key.hashCode()%table.length;
        int collisions = 0;
        while (table[hash] != null && !table[hash].removed) {
            collisions++;
            if (table[hash].key.equals(key)) {
                Node oldNode = table[hash];
                table[hash] = new Node(key, value);
                return oldNode;
            }
            hash = indexIncrement(hash);
        }

        table[hash] = new Node(key, value);
        size++;
        return output;
    }

    public int indexIncrement(int index){
        if (index + 1 < table.length){
            return index + 1;
        } else {
            return 0;
        }
    }

    public Object get(Object key){
        int hash = key.hashCode()%table.length;
        while (table[hash] != null && !table[hash].key.equals(key)){
            hash = indexIncrement(hash);
        }
        return table[hash] == null ? null : table[hash].value;
    }

    public void remove(Object key){
        if (!get(key).equals("null")){
            int hash = key.hashCode()%table.length;
            while ((table[hash] != null || table[hash].removed) && !table[hash].key.equals(key)){
                hash = indexIncrement(hash);
            }
            table[hash].removed = true;
            size--;
        }
    }

    public String toString(){
        String output = "[";
        for (int i = 0; i < table.length-1; i++){
            if (table[i] == null){
                output += "NULL, ";
            } else if (table[i].removed){
                output += "REMOVED, ";
            } else {
                output += table[i].toString() + ", ";
            }
        }
        if (table[table.length-1] != null){
            output += table[table.length-1].toString() + "]";
        } else {
            output += "NULL]";
        }
        return output + ", " + size;
    }

    public class Node {
        Object key;
        Object value;
        boolean removed;

        public Node(){
            key = null;
            value = null;
            removed = false;
        }

        public Node(Object k, Object v){
            key = k;
            value = v;
            removed = false;
        }

        public String toString(){
            return removed ? "DUMMY" : "<" + key + ", " + value + ">";
        }
    }

}