/**
 * Created by 216430 on 11/2/2017.
 */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input.txt"));
        HashTable test = new HashTable(13);
        while (in.hasNext()){
            int key = in.nextInt();
            String value = in.next();
            test.put(key, value);
            System.out.println(test);
        }
    }
}