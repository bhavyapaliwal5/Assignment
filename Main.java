// Andrewid - Bpaliwal
// MSSM Summer Orientation Program
// Data Structures Assignment

import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {

        // Read input hardcoded files
        BufferedReader fi = new BufferedReader(new FileReader("9.in"));
        //Prepare output file
        PrintWriter fo = new PrintWriter(new BufferedWriter(new FileWriter("9.out")));

        TreeSet<Integer> indexSet = new TreeSet<>();
        int n = Integer.parseInt(fi.readLine());
        Checktime[] states = new Checktime[2*n];

        //Prepare array for all the lifeguard slots
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(fi.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            states[2*i] = new Checktime(start, i);
            states[2*i+1] = new Checktime(end, i);
        }
        Arrays.sort(states);


        int coverTime = 0;
        int[] leftTime = new int[n];
        int last = 0;

        //Get overlapping time for all slots from array
        for(Checktime out: states) {
            if(indexSet.size() == 1) {
                leftTime[indexSet.first()] += out.time - last;
            }
            if(!indexSet.isEmpty()) {
                coverTime += out.time - last;
            }
            if(indexSet.contains(out.index)) {
                indexSet.remove(out.index);
            }
            else {
                indexSet.add(out.index);
            }
            last = out.time;
        }

        // Get maximum overlapped
        int total = 0;
        for(int out: leftTime) {
            total = Math.max(total, coverTime - out);
        }
        fo.println(total);
        fo.close();
    }

    static class Checktime implements Comparable<Checktime> {
        public int time, index;
        public Checktime(int time, int index) {
            //time structure
            this.time=time;
            this.index=index;
        }
        public int compareTo(Checktime s) {
            return  time - s.time ;
        }
    } }

