/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesiprotocol;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author srishailamdasari1
 */
public class MainMemory {

    Queues queueline;

    MainMemory() {
    }

    MainMemory(Queues que) {
        this.queueline = que;
    }
    String datacache[] = new String[4096];
    String data;

    public void memory() {
        datacache[2218] = "GoogleFacebook";
        datacache[2222] = "ABCDEFGHIJKLMNOP";
        datacache[2731] = "QWERTQWERTY";
        datacache[3968] = "THisis2944speaki";
        datacache[2712] = "Iamreading";
        datacache[2214] = "UNIVERSITYNEBRASKA";
        datacache[2048] = "ANUSHAONE";
        datacache[2176] = "AnushaTwo";
        datacache[2304] = "AnushaThree";
        String st = queueline.getBustoMemory() != null ? queueline.getBustoMemory().get(0) : null;
        String instruction[] = st != null ? st.split(" ", 0) : null;
        //System.out.println("Current instruction in memory is"+st);
        int offset = Integer.parseInt(instruction[1].substring(12, 17));
        int location = Integer.parseInt(instruction[1].substring(0, 12), 2);
        //System.out.println("Location is: " + location);
        if (instruction[0].contains("R")|| instruction[0].contains(("W"))) {
            data = datacache[location];
             //System.out.println("Data read is"+queueline.getMemorytoBus().get(0));
            if (data != null) {
                data = st + " " + data;
                queueline.setMemorytoBus(data);
                
                String MM=queueline.getMemorytoBus().get(0);
                System.out.println("Data read is"+MM.substring(0,25));
            }
            queueline.getBustoMemory().remove(0);

        } else if (instruction[0].contains("S")) {
            data = instruction[3];
            datacache[location] = data;
            System.out.println("Data is stored into memory for instruction: at location " + instruction[1]);
            queueline.getBustoMemory().remove(0);

        }
    }

}
