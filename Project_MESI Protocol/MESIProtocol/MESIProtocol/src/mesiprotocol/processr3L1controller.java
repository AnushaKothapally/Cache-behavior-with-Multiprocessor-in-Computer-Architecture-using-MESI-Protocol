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
public class processr3L1controller {

    Queues ques;

    public processr3L1controller(Queues que) {
        this.ques = que;
    }
    int i = 0;
    String[] c;
    String k;

    public String[][] L1cache = new String[128][2];
    public boolean[][] vb = new boolean[128][2];
    public boolean[][] db = new boolean[128][2];
    List<String> tempList = new ArrayList<String>();
    List<String> Instr;
    String s;

    public void readfromP3reqq() {
        //L1cache[42][0]="10001";
        // vb[42][0]=true;

        Instr = ques.getP3reqtoP3c();
        int n = Instr.size();
        String MM=Instr.get(0);
        if(MM.contains("Load"))
        System.out.println("in L1Cache of P3 " + MM.substring(0,24));
        else
            System.out.println("in L1Cache of P3 " + MM);
        s = Instr.get(0);//get the ith item of the list anf store it in a string
        c = s.split(" ");
        k = B2Iconvert(c);
        ques.getP3reqtoP3c().remove(0);

    }

//    public void readfromP2resq() {
//
//        Instr = ques.getP2ResQueue();
//        int n = Instr.size();
//        System.out.println("in L1C of P3 from response" + Instr.get(0));
//        s = Instr.get(0);//get the ith item of the list anf store it in a string
//        s = Instr.get(0);
//        c = s.split(" ");
//        k = B2Iconvert(c);
//        ques.getP2ResQueue().remove(0);
//
//    }
    public String B2Iconvert(String[] d) {
        int tag, index, offset;
        //System.out.println("in method to convert  ***");
        String a, b, c, address;
        a = d[1].substring(0, 5);  //Tag
        b = d[1].substring(5, 12); //Index
        c = d[1].substring(12, 16); //OFFset
        tag = Integer.parseInt(a, 2);
        index = Integer.parseInt(b, 2);
        offset = Integer.parseInt(c, 2);
        //System.out.println(tag + "," + index + "," + offset + " tag,index,offset");
        if (d[0].equalsIgnoreCase("R3")) {
            if (d.length > 3) {

            } else if (d.length == 3) {
                k = read(a, b, c, tag, index, offset, d);
                if (k == null) {
                    d[0] = "BUSR3";
                    s = d[0] + " " + d[1] + " " + d[2];
                    ques.setP3ReqtoBusQueue(s);
                    //System.out.println("checking done");
                }

            }
        } else if ((d[0].contains("BUSR"))) {
            k = readForAnotherprocessor(a, b, c, tag, index, offset, d);
            if (k == null) {
                System.out.println("No data found in P3 Cache also");
                s = d[0] + " " + d[1] + " " + d[2] + " " + "I";
                ques.setP3RestoBusQueue(s);
            }
        } else if (d[0].contains("Load") || d[0].contains("S3")) {
            //String data=
            loading(a, b, c, tag, index, offset, d);
        } else if (d[0].contains("W3")) {
            Boolean isdatafound = false;
            if ((a.equals(L1cache[index][0])) && (vb[index][0] == true)) {
                isdatafound = true;
            } else if ((a.equals(L1cache[index][1])) && (vb[index][1] == true)) {
                isdatafound = true;
            }
            if (isdatafound) {
                String input = "BusW3Ex" + " " + d[1] + " " + d[2];
                ques.setP3ReqtoBusQueue(input);
            } else {
                String input = "BusW3Ex" + " " + d[1] + " " + d[2];
                ques.setP3ReqtoBusQueue(input);
            }

        } else if (d[0].contains("BusW") && d[0].contains("Ex")) {
            System.out.println("Invalidate as it is modifying");
            // if (!d[0].contains("I")) {
            invalidate(a, b, c, tag, index, offset, d);
            s = d[0] + " " + d[1] + " " + d[2] + " " + "I";
            ques.setP3RestoBusQueue(s);
            //  }
        }

        return "0";
    }

    public String read(String a, String b, String c, int tag, int index, int offset, String[] add) {

        if ((a.equals(L1cache[index][0])) && (vb[index][0] == true)) {
            System.out.println("tag matched so checking in P3L1d" + L1cache);
            ques.setP3CtoP3DQueue(s);

        } else if ((a.equals(L1cache[index][1])) && (vb[index][1] == true)) {
            System.out.println("tag matched so checking in P3L1d" + L1cache);
            ques.setP3CtoP3DQueue(s);
        } else {
            //System.out.println("No data found in cache");
            return null;
        }

        return "Valid";
    }

    public String readForAnotherprocessor(String a, String b, String c, int tag, int index, int offset, String[] add) {

        if ((a.equals(L1cache[index][0])) && (vb[index][0] == true)) {
            if (db[index][0] == true) {
                String instru = add[0] + " " + add[1] + " " + add[2] + " " + "DB";
                db[index][0] = false;
                ques.setP3CtoP3DQueue(instru);
                System.out.println("Dirty bit is true: " + instru);
            } else {
                ques.setP3CtoP3DQueue(s);
            }
            System.out.println("tag matched so checking in P3L1d" + L1cache);

        } else if ((a.equals(L1cache[index][1])) && (vb[index][1] == true)) {
            System.out.println("tag matched so checking in P3L1d" + L1cache);
            ques.setP3CtoP3DQueue(s);
        } else {
            //System.out.println("No data found in cache");
            return null;
        }

        return "Valid";
    }

    public void loading(String a, String b, String c, int tag, int index, int offset, String[] d) {
        System.out.println("Data loading into L1 cache of P3 ");
        String input = null;
        if (a.equals(L1cache[index][0])) {
           // System.out.println("Saving at previous location");
            input = d[0] + " " + d[1] + " " + d[2] + " " + d[3] + " " + "0";
            L1cache[index][0] = a;
            ques.setP1CtoP1DQueue(input);
        } else if (a.equals(L1cache[index][1])) {
          //  System.out.println("Saving at previous location");
            input = d[0] + " " + d[1] + " " + d[2] + " " + d[3] + " " + "1";
            L1cache[index][1] = a;
            ques.setP1CtoP1DQueue(input);
        } else if (db[index][0] != true && !(vb[index][0] == true && vb[index][1] == false)) {
            //Loading the data into cache on read
            vb[index][0] = true;
            // ques.setP1CtoP1DQueue(s);
            L1cache[index][0] = a;
            db[index][0] = false;
            if (d[0].equals("Load")) {
                input = d[0] + " " + d[1] + " " + d[2] + " " + d[3] + " " + "0";
            } else {
                input = d[0] + " " + d[1] + " " + d[2] + " " + "0";
                db[index][0] = true;
            }
            //System.out.println("Print s" + s);
            ques.setP3CtoP3DQueue(input);
        } else if (db[index][1] != true) {
            //Loading the data into cache on read
            vb[index][1] = true;
            //ques.setP1CtoP1DQueue(s);
            L1cache[index][1] = a;
            db[index][1] = false;
            if (d[0].equals("Load")) {
                input = d[0] + " " + d[1] + " " + d[2] + " " + d[3] + " " + "1";
            } else {
                input = d[0] + " " + d[1] + " " + d[2] + " " + "1";
                db[index][1] = true;
            }
            //System.out.println("Print s" + s);
            ques.setP3CtoP3DQueue(input);
        } else if (db[index][0] == true && vb[index][0] == true) {
            // need to write back the original data
            if (d[0].equals("Load") && a.equals(L1cache[index][0])) {
                //may previous write was not complete before write  
            }
        }

       // System.out.println("s is ---------" + input);

    }

    public void invalidate(String a, String b, String c, int tag, int index, int offset, String[] add) {
        if ((a.equals(L1cache[index][0])) && (vb[index][0] == true) && db[index][0] == false) {
            System.out.println("Data present in P3L1d, invalidating:");
            //ques.setP2CtoP2DQueue(s);
            L1cache[index][0] = null;
            vb[index][0] = false;

        } else if ((a.equals(L1cache[index][1])) && (vb[index][1] == true && db[index][0] == false)) {
            System.out.println("Data present in P3L1d, invalidating:");
            L1cache[index][0] = null;
            vb[index][0] = false;
        }
    }

    public void notempty() {
        String received = ques.getP3DtoP3CQueue().get(0);
        System.out.println("P3D2P3C called: " + received + "busstatus" + ques.getBuStatus());
        if (received.contains("BUS")) {
            ques.setP3RestoBusQueue(received);
        } else {
            ques.setOutput(received);
        }
        ques.getP3DtoP3CQueue().remove(0);
    }
}
