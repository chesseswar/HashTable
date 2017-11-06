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

        Node output = null;
        boolean collision = false;


        table[key.hashCode()%table.length] = new Node(key, value);

        return output;
    }

    public boolean contains(Object key){
        return get(key) != null;
    }

    public Object get(Object key){
        return table[key.hashCode()%table.length].value;
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
            String output = "key: " + key + "\tvalue: " + value;
            return output;
        }
    }

}