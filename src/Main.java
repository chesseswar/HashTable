/**
 * Created by 216430 on 11/2/2017.
 */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("build.txt"));
        //HMMM TEST GITHUB PLEZ
        ArrayList<Double> loadFactors = new ArrayList<>();
        loadFactors.add(1.0);/*
        for (double i = .1; i <= 1.001; i += .001){
            loadFactors.add(i);
        }*/
        PrintWriter writer = new PrintWriter(new FileWriter("Book1.csv"));

        ArrayList<String[]> input = new ArrayList<>();
        while(in.hasNext()){
            String[] temp = new String[2];
            temp[0] = in.next();
            temp[1] = in.nextLine();
            input.add(temp);
        }

        writer.println("Elements: " + input.size());
        writer.println("Load Factor,Average Probes Per Insertion,Average Insertion Time,Average Search Time (Successful),Average Probes Per Search (Successful),Average Probes to Find Absent Element,Average Search Time (Unsuccessful)");

        for (double d : loadFactors){
            HashTable table = new HashTable(nextPrime((int) (((double) input.size()) / d)));
            long start = System.currentTimeMillis();
            for (String[] data : input){
                table.put(data[0], data[1]);
            }
            long time = System.currentTimeMillis() - start;
            double averageInsertionTime = ((double)(time) / (double)(input.size()));
            double averageProbes = (double)(table.collisions) / (double)(input.size());


            in = new Scanner(new File("successful.txt"));
            ArrayList<String> keys = new ArrayList<>();
            while (in.hasNext()){
                keys.add(in.next());
                in.nextLine();
            }
            start = System.currentTimeMillis();
            for (String find : keys){
                table.get(find);
            }
            double averageFindTimeSucc = (double)(System.currentTimeMillis() - start) / (double)(keys.size());
            double averageProbesSucc = (double) (table.searchCollisions) / (double)(keys.size());
            in = new Scanner(new File("unsuccessful.txt"));
            keys.clear();
            while (in.hasNext()){
                keys.add(in.next());
                in.nextLine();
            }
            start = System.currentTimeMillis();
            for (String find : keys){
                table.get(find);
            }
            double averageFindTimeUnsucc = (double)(System.currentTimeMillis() - start) / (double)(keys.size());
            double averageProbesUnsucc = (double)(table.searchCollisions) / (double)(keys.size());
            writer.println(d + "," + averageProbes + "," + averageInsertionTime + "," + averageFindTimeSucc+ "," + averageProbesSucc + "," + averageFindTimeUnsucc + "," + averageProbesUnsucc);
            System.out.println("Load Factor: " + d);
            System.out.println(("Average Probes Per Insertion: " + averageProbes));
            System.out.println(("Average Insertion Time: " + averageInsertionTime));
            System.out.println(("Average Search Time (Successful): " + averageFindTimeSucc));
            System.out.println(("Average Probes To Find Absent Element: " + averageProbesUnsucc));
            System.out.println(("Average Search Time (Unsuccessful): " + averageFindTimeUnsucc) + "\n");
        }

        /*
        for (String str : searches) {
            in = new Scanner(new File(str));
            writer.printf(str + "\n");
            ArrayList<Integer> keys = new ArrayList<>();
            while (in.hasNext()) {
                keys.add(in.nextInt());
                in.nextLine();
            }

            for (/*float d = .01f; d <= 1f; d+= .01f double d : loadFactors) {
                HashTable table = new HashTable(nextPrime((int) (((double) input.size()) / d)));
                long start = System.currentTimeMillis();
                for (String[] data : input) {
                    table.put(Integer.parseInt(data[0]), data[1]);
                }
                long end = System.currentTimeMillis();
                //writer.print("Time Taken: " + (end-start) + "\n");
                writer.printf("Max Table Size: %d\t\t", table.table.length);
                writer.printf("Load Factor: %.1f\t\tCollisions: %d\t\tAverage Insertion Time: %.5f\t\tCollisions vs. Insertions: %.3f%%\t\t", (double) ((double) table.size()) / ((double) table.table.length), table.collisions, ((double) (end - start)) / table.size(), 100 * ((double) (table.collisions) / (double) (table.size)));
                //writer.printf("Average Time Per Search: %.3f\n", (double)(end-start) / (double)table.size);
                //System.out.println("Collision Aversion: Linear Probing");
                //System.out.println("Time taken: " + (end - start));
                //System.out.println("Collisions: " + table.collisions);
                //System.out.println();
                start = System.currentTimeMillis();
                for (int i = 0; i < keys.size(); i++) {
                    table.get(keys.get(i));
                }
                end = System.currentTimeMillis();
                writer.printf("Average Search Time: %.5f\n", (double)(end-start) / keys.size());
            }
            writer.printf("\n");
        }*/

        writer.close();
    }

    public static int nextPrime(int input1){
        int input = input1;
        while (!isPrime(input)){
            input++;
        }
        return input;
    }

    public static boolean isPrime(int input){
        if (input == 1 || input == 2){
            return true;
        }
        for (int i = 3; i*2 <= input; i++){
            if (input % i == 0){
                return false;
            }
        }
        return true;
    }
}