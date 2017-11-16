/**
 * Created by 216430 on 11/2/2017.
 */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner inputs = new Scanner(new File("input.txt"));
        ArrayList<String> files = new ArrayList<>();
        while (inputs.hasNext()){
            files.add(inputs.next());
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (String scanner : files){
            System.out.println("File: " + scanner);
            Scanner in = new Scanner(new File(scanner));
            double[] loadFactors = {.1, .5, .8, .9, 1};
            ArrayList<String[]> input = new ArrayList<>();
            while(in.hasNext()){
                input.add(in.nextLine().split(" "));
            }
            for (double d : loadFactors){
                HashTable table = new HashTable((int)(((double)input.size())/d));
                long start = System.currentTimeMillis();
                for (String[] data : input){
                    table.put(Integer.parseInt(data[0]), data[1]);
                }
                long end = System.currentTimeMillis();
                writer.write(/*System.out.println(*/d + "\t" + table.collisions + "\n");
                //System.out.println("Collision Aversion: Linear Probing");
                //System.out.println("Time taken: " + (end - start));
                //System.out.println("Collisions: " + table.collisions);
                //System.out.println();
            }
            System.out.println();
        }
        writer.close();
    }
}