/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesiprotocol;

import java.util.List;

/**
 *
 * @author Lakshman
 */
public class P2RequestControler {

    Queues que;
    String input;
    List<String> templist;

    public P2RequestControler(Queues que) {

        this.que = que;
    }

    public void p2top2req() {
        //System.out.println(que.getP2ReqQueue() + "P2 que");
        templist = que.getP2ReqQueue();
        input = templist.get(0);
        que.setP2reqtoP2c(input);
        que.getP2ReqQueue().remove(0);
    }

    public void bustoP2Res() {
        System.out.println("In P2 Respone Queue, received instuction from other processor is" + que.getBustoP2Res().get(0));
        input = que.getBustoP2Res().get(0);
        que.setP2reqtoP2c(input);
        que.getBustoP2Res().remove(0);
    }

    public void bustoP2Req() {
        //System.out.println("Data read for instruction");
        input = que.getBustoP2Req().get(0);
        System.out.println("Now In P2 request Q after reading data for instruction");
        String ins[] = input.split(" ", 0);
        if (ins[0].contains("R2")) {
            que.setOutput(input);
            input = "Load" + " " + ins[1] + " " + ins[2] + " " + ins[3];
            que.setP2reqtoP2c(input);
        } else if (ins[0].contains("S2")) {
            que.setP2reqtoP2c(input);
        }
        que.getBustoP2Req().remove(0);
    }

}
