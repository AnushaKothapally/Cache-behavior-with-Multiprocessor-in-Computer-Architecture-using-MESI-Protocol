/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesiprotocol;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

/**
 *
 * @author srishailamdasari1
 */
public class MESIProtocol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        System.out.println("Hello MESI");
        Scanner inputFile = new Scanner(new File("sample.txt"));
        Queues queueline = new Queues();
        List<String> tempCPUq = new ArrayList<String>();
        while (inputFile.hasNextLine()) {
            String st = inputFile.nextLine();
            if(st.contains("UniProcessor")){
                queueline.uniprocessor=true;
              }
            else if(st.contains("Dual")){
                queueline.dualprocessor=true;
                if(st.contains("P1P2")||(st.contains("P2P1"))){
                 queueline.p1p2=true;
                }
                else if(st.contains("P2P3")||(st.contains("P3P2"))){
                 queueline.p2p3=true;
                }
                if(st.contains("P1P3")||(st.contains("P3P1"))){
                 queueline.p1p3=true;
                }
            }
            else{
            tempCPUq.add(st);
                    }
        }
        queueline.setQueue(tempCPUq);
        System.out.println("All" + queueline.getQueue());
        Timer time = new Timer();
        Scheduling st = new Scheduling(queueline);
        MESIProtocol mp = new MESIProtocol();
        st.run();
    }

    public void movequeue(Queues que) {
        String instru = que.getQueue().get(0);

        String[] instructions = instru.split(",", 0);
        List tempList = new ArrayList();
        System.out.println(" instruction length: " + instructions.length);
        if (instructions.length == 1) {// Single instruction per cycle
            String[] array = instru.split(" ", 0);
            if (que.getPrev() != null && que.getPrev().equals(array[1])) {// previous instruction to same adres
                que.cycle++;
                if (que.cycle == 2) {
                    que.cycle = 0;
                    que.setPrev(null);
                }
                System.out.println("Queue is in waiting");

                return;
            }
         //   System.out.println("Moving instruction: " + instru);
            tempList.add(instru);
            String prev = array[1];
            que.setPrev(prev);
            if (instru.charAt(1) == '1') {
                // System.out.println(que.P1ReqQueue);
                que.setP1ReqQueue(instru);
                que.getQueue().remove(0);
                System.out.println(" added to P1" + que.getP1ReqQueue());
            } else if (instru.charAt(1) == '2') {
                que.setP2ReqQueue(instru);
                que.getQueue().remove(0);
                System.out.println("added to P2" + que.getP2ReqQueue());
            }
            if (instru.charAt(1) == '3') {
                que.setP3ReqQueue(instru);
                que.getQueue().remove(0);
                System.out.println("added to P3" + que.getP3ReqQueue());
            }
        } else {
            int i = 0;
            while (i < instructions.length) { //Simultaneous instructions
                System.out.println(" instruction length 2 executing" + instructions[i]);
                String instruction = instructions[i];
                List tempList1 = new ArrayList();
                tempList1.add(instruction);
                if (instruction.charAt(1) == '1') {
                    que.setP1ReqQueue(instruction);
                    // tempList.remove(0);
                    System.out.println(" added to P1" + que.getP1ReqQueue());
                } else if (instruction.charAt(1) == '2') {
                    que.setP2ReqQueue(instruction);

                    System.out.println("added to P2" + que.getP2ReqQueue());
                }
                if (instruction.charAt(1) == '3') {
                    que.setP3ReqQueue(instruction);
                    // que.getQueue().remove(0);
                    // tempList.remove(0);
                    System.out.println("added to P3 Queue" + que.getP3ReqQueue());
                }
                i++;

            }
            que.getQueue().remove(0);

        }

    }

    public void output(Queues queueline) {
        String st = queueline.getOutput().get(0);
        String[] data = st.split(" ");
        int no_of_bytes = 0;
        //System.out.println("Data in CPU ****"+st);
        if (data.length == 4) {
            String off = data[1].substring(12, 17);
            int offset = Integer.parseInt(off, 2);
            if (data[0] != null && data[0].contains("R")) {
                no_of_bytes = Integer.parseInt(data[2]);
                String key = null;
                if (data.length == 4) {
                    key = data[3];
                }
                System.out.println("Data read for instruction: " + data[0] + " " + data[1] + " " + data[2] + " is");
                for (int i = offset; i < key.length();) {
                    //  System.out.print(i+""+key.length());
                    for (int j = i; j < i + no_of_bytes;) {
                        // System.out.print(j+""+key.length());
                        if (j < key.length()) {
                            System.out.print(key.charAt(j));
                        }
                        j++;
                    }
                    i = i + no_of_bytes;
                    System.out.println("\n");
                }
                queueline.getOutput().remove(0);
            }
        }
    }
}
