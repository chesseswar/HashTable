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
        Object output = null;
        int hash = key.hashCode()%table.length;
        if (table[hash] != null){
            output = table[hash].value;
            System.out.println("LINEAR PROBING: " + new Node(key, value));
            while (table[hash] != null) {
                if ((table[hash].key).toString().equals(key.toString())) {
                    table[hash] = new Node(key, value);
                    return null;
                }
                hash = indexIncrement(hash);
            }
            table[hash] = new Node(key, value);
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
        while (table[hash] != null && !table[hash].key.toString().equals(key.toString())){
            hash++;
        }
        return table[hash] == null ? null : table[hash].value;
    }

    public void remove(Object key){

    }

    public String toString(){
        String output = "[";
        for (int i = 0; i < table.length-1; i++){
            if (table[i] != null){
                output += table[i].toString() + ", ";
            } else {
                output += "NULL, ";
            }

        }
        if (table[table.length-1] != null){
            output += table[table.length-1].toString() + "]";
        } else {
            output += "NULL]";
        }
        return output;
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
            String output = "<" + key + ", " + value + ">";
            return output;
        }
    }

}