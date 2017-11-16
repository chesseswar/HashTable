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
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));
        //BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (String scanner : files){
            writer.print("File: " + scanner + "\n");
            writer.print("Collision Aversion: " + "Linear Probing\n");
            Scanner in = new Scanner(new File(scanner));
            double[] loadFactors = {.1, .5, .8, .9, 1};
            ArrayList<String[]> input = new ArrayList<>();
            while(in.hasNext()){
                input.add(in.nextLine().split(" "));
            }
            writer.print("Table Size Used: " + input.size() + "\n\n");
            for (float d = .01f; d <= 1f; d+= .01f){
                HashTable table = new HashTable(nextPrime((int)(((double)input.size())/d)));
                long start = System.currentTimeMillis();
                for (String[] data : input){
                    table.put(Integer.parseInt(data[0]), data[1]);
                }
                long end = System.currentTimeMillis();
                //writer.print("Time Taken: " + (end-start) + "\n");
                writer.printf("%.10f\t%d \n", (double)((double)table.size())/((double)table.table.length), table.collisions);
                //System.out.println("Collision Aversion: Linear Probing");
                //System.out.println("Time taken: " + (end - start));
                //System.out.println("Collisions: " + table.collisions);
                //System.out.println();
            }
            writer.print("\n");
        }
        writer.close();
    }

    public static int nextPrime(int input){
        while (!isPrime(input)){
            input++;
        }
        return input;
    }

    public static boolean isPrime(int input){
        if (input == 1 || input == 2){
            return true;
        }
        for (int i = 3; i * i <= input; i++){
            if (input % i == 0){
                return false;
            }
        }
        return true;
    }
}