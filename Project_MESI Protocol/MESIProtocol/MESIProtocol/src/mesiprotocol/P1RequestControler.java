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
public class P1RequestControler {

    Queues que;
    String input;
    List<String> templist;

    public P1RequestControler(Queues que) {

        this.que = que;
    }

    public void p1top1req() {
        System.out.println("instruction in p1 request Q is "+que.getP1ReqQueue());
// templist=que.getP1ReqQueue();
        input = que.getP1ReqQueue().get(0);
        que.setP1reqtoP1c(input);
       // System.out.println("**after reading instuction has been removed from Q so now size of the Q is: " + que.getQueue().size());
        que.getP1ReqQueue().remove(0);

    }

    public void bustoP1Res() {
        System.out.println("In P1 Respone Queue, received instuction from other processor is: " + que.getBustoP1Res().get(0));
        input = que.getBustoP1Res().get(0);
        que.setP1reqtoP1c(input);
        que.getBustoP1Res().remove(0);
    }

    public void bustoP1Req() {
        //System.out.println("Data read for instruction");
        input = que.getBustoP1Req().get(0);
        System.out.println("Now In P1 request Q after reading data for instruction" + input.substring(0, 22));

        String ins[] = input.split(" ", 0);
        if (ins[0].contains("R1")) {
            que.setOutput(input);
            input = "Load" + " " + ins[1] + " " + ins[2] + " " + ins[3];
            que.setP1reqtoP1c(input);

        } else if (ins[0].contains("S1")) {
            que.setP1reqtoP1c(input);
        }
        que.getBustoP1Req().remove(0);
    }

}
