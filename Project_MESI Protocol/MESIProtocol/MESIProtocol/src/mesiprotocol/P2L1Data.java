/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesiprotocol;

/**
 *
 * @author Lakshman
 */
public class P2L1Data {

    Queues queueline;
    String data;
    String cachesize[][] = new String[128][2];
    String datacache[][] = new String[128][2];

    P2L1Data() {
    }

    P2L1Data(Queues que) {
        this.queueline = que;
    }

    public void P2L1DataController() {
        cachesize[42][0] = "10001";
        datacache[42][0] = "Anusha";
        String st = queueline.getP2CtoP2DQueue().get(0);
        if(st.contains("Load"))
        System.out.println(" To Write the data from memory to P2L1D, Current instruction is:  " + st.substring(0,24));
        else
            System.out.println("In P2L1D, Current instruction is:  " + st);
        String instruction[] = st.split(" ", 0);
        String tag = null;
        int offset;
        int location;
        tag = instruction[1].substring(0, 5);
        offset = Integer.parseInt(instruction[1].substring(12, 17));
        location = Integer.parseInt(instruction[1].substring(5, 12), 2);
        // System.out.println("P2L1D"+instruction[0]);
        if (instruction[0].contains("R")) {
            if (tag.equalsIgnoreCase(cachesize[location][0])) {
                data = datacache[location][0];

            } else if (tag.equalsIgnoreCase(cachesize[location][1])) {
                data = datacache[location][1];
            }
            if (data != null) {
                data = st + " " + data;

                queueline.setP2DtoP2CQueue(data);
                System.out.println("P2L1D" + queueline.getP2DtoP2CQueue());
            }
            queueline.getP2CtoP2DQueue().remove(0);
        } else if (instruction[0].contains("W")) {
            String value;
            if (instruction.length > 3) {
                value = instruction[3];
            } else {
                value = instruction[2];
            }
            if (tag.equals(cachesize[location][0])) {
                datacache[location][0] = value;
            } else if (tag.equals(cachesize[location][1])) {
                datacache[location][1] = value;
            }
            queueline.getP2CtoP2DQueue().remove(0);
        } else if (instruction[0].contains("Load") || instruction[0].contains("S2")) {
            String value = null;
            int place;
            if (instruction.length == 5) {
               if(instruction[0].contains("Load"))
                value = instruction[3];
                else
                   value = instruction[2];
                place = Integer.parseInt(instruction[4]);
            } else {
                value = instruction[2];
                place = Integer.parseInt(instruction[3]);
            }
            datacache[location][place] = value;
            cachesize[location][place] = tag;
            queueline.getP2CtoP2DQueue().remove(0);
            System.out.println("Data Loaded successfully P2 cache ");
        }
    }

}
