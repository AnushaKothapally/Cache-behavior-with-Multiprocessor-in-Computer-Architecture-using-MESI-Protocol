/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesiprotocol;

/**
 *
 * @author srishailamdasari1
 */
public class L3Data {

    Queues queueline;
    String data=null;
    String cachesize[][] = new String[128][2];
    String datacache[][] = new String[128][2];

    L3Data() {
    }

    L3Data(Queues que) {
        this.queueline = que;
    }

    public void P3L1DataController() {
        String st = queueline.getP3CtoP3DQueue().get(0);
        if(st.contains("Load"))
        System.out.println("In P3L1D, Current instruction is:  " + st.substring(0,24));
        else
            System.out.println("In P3L1D, Current instruction is:  " + st);
        String instruction[] = st.split(" ", 0);
        String tag = null;
        data=null;
        int offset;
        int location;
        tag = instruction[1].substring(0, 5);
        offset = Integer.parseInt(instruction[1].substring(12, 17));
        location = Integer.parseInt(instruction[1].substring(5, 12), 2);
        // System.out.println("P2L1D"+instruction[0]);
        if (instruction[0].contains("R")) {
            if (tag.equalsIgnoreCase(cachesize[location][0])) {
                data = datacache[location][0];
                 System.out.println("data1"+data+"tag"+tag+"cache"+cachesize[location][0]+"data"+datacache[location][0]);

            } else if (tag.equalsIgnoreCase(cachesize[location][1])) {
                data = datacache[location][1];
                 System.out.println("data2"+data);
            }
            
            if (data != null) {
                data = st + " " + data;

                queueline.setP3DtoP3CQueue(data);
                System.out.println("P3L1D" + queueline.getP3DtoP3CQueue()+"data"+data);
            }
            queueline.getP3CtoP3DQueue().remove(0);
        } //       else if(instruction[0].contains("W")){
        //           String value=instruction[3];
        //           if(tag.equals(cachesize[location][0])){
        //               datacache[location][0]=value;
        //               }
        //               else if(tag.equals(cachesize[location][1])){
        //               datacache[location][1]=value;
        //               }
        //            queueline.getP3CtoP3DQueue().remove(0);
        //       }
        else if (instruction[0].contains("Load") || instruction[0].contains("S3")) {
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
            queueline.getP3CtoP3DQueue().remove(0);
            System.out.println("Data Loaded successfully P3 cache");
        }
    }
}
