import java.util.*;
import java.io.*;

public class HashTable {
    Node[] table;
    int size;
    int collisions;
    long searchCollisions;
    public HashTable(){
        table = new Node[101];
        size = 0;
        collisions = 0;
        searchCollisions = 0;
    }

    public HashTable(int initCap){
        table = new Node[initCap];
        size = 0;
        collisions = 0;
        searchCollisions = 0;
    }
    public int getCollisions(){
        return collisions;
    };
    public int size(){
        return size;
    }

    public double loadFactor(){
        return (double)(size) / (double)table.length;
    }
    public Object put(Object key, Object value, int probingMethod) {
        if (table.length == size){
            return null;
        }
        if (probingMethod == 1) { //LINEAR PROBING
            Object output = null;
            int hash = Math.abs(key.hashCode())%table.length;
            while (table[hash] != null && !table[hash].removed) {
                collisions++;
                if (table[hash].key.equals(key)) {
                    Node oldNode = table[hash];
                    table[hash] = new Node(key, value);
                    return oldNode;
                }
                hash = linearIncrement(hash);
            }

            table[hash] = new Node(key, value);
            size++;
            return output;
        } else if (probingMethod == 2) {
            Object output = null;
            int tempHash = Math.abs(key.hashCode())%table.length;
            int count = 0;
            while (table[tempHash] != null && !table[tempHash].removed) {
                collisions++;
                if (table[tempHash].key.equals(key)) {
                    Node oldNode = table[tempHash];
                    table[tempHash] = new Node(key, value);
                    return oldNode;
                }
                tempHash = quadraticIncrement(tempHash, count);
                count++;
            }

            table[tempHash] = new Node(key, value);
            size++;
            return output;
        } else {
            Random r = new Random();
            Object output = null;
            int tempHash = Math.abs(key.hashCode())%table.length;
            int count = 0;
            while (table[tempHash] != null && !table[tempHash].removed) {
                collisions++;
                if (table[tempHash].key.equals(key)) {
                    Node oldNode = table[tempHash];
                    table[tempHash] = new Node(key, value);
                    return oldNode;
                }
                tempHash = Math.abs(r.nextInt() % table.length);
                count++;
            }

            table[tempHash] = new Node(key, value);
            size++;
            return output;
        }
    }

    public int quadraticIncrement(int index, int count){
        int newIndex = index + (int)(Math.pow(2,count));
        System.out.println((newIndex >= table.length ? newIndex % table.length: newIndex));
        return newIndex >= table.length ? newIndex % table.length : newIndex;
    }

    public int linearIncrement(int index){
        if (index + 1 < table.length){
            return index + 1;
        } else {
            return 0;
        }
    }

    public Object get(Object key){
        int hash = Math.abs(key.hashCode())%table.length;
        int count = 0;
        while (table[hash] != null && !table[hash].key.equals(key) && count < size+1){
            count ++;
            searchCollisions++;
            hash = linearIncrement(hash);
        }
        return table[hash] == null ? null : table[hash].value;
    }

    public void remove(Object key){
        if (!get(key).equals("null")){
            int hash = key.hashCode()%table.length;
            while ((table[hash] != null || table[hash].removed) && !table[hash].key.equals(key)){
                hash = linearIncrement(hash);
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