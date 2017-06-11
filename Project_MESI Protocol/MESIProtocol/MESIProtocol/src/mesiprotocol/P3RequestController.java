/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesiprotocol;

import java.util.List;

/**
 *
 * @author srishailamdasari1
 */
public class P3RequestController {

    Queues que;
    String input;
    List<String> templist;

    P3RequestController(Queues que) {
        this.que = que;
    }

    public void p3top3req() {
       // System.out.println(que.getP3ReqQueue() + "P3 que");
        templist = que.getP3ReqQueue();
        input = templist.get(0);
        que.setP3reqtoP3c(input);
        que.getP3ReqQueue().remove(0);
    }

    public void bustoP3Res() {
        System.out.println("In P3 response,Instruction from other processor is:" + que.getBustoP3Res().get(0));
        input = que.getBustoP3Res().get(0);
        que.setP3reqtoP3c(input);
        que.getBustoP3Res().remove(0);
    }

    public void bustoP3Req() {
        //System.out.println("Data read for instruction");
        input = que.getBustoP3Req().get(0);
        System.out.println("Now Placing in P3 request Q after  checking in other processors for instruction " + input.substring(0,23));
        String ins[] = input.split(" ", 0);
        if (ins[0].contains("R3")) {
            que.setOutput(input);
            input = "Load" + " " + ins[1] + " " + ins[2] + " " + ins[3];
            que.setP3reqtoP3c(input);
        } else if (ins[0].contains("S3")) {
            que.setP3reqtoP3c(input);
        }
        que.getBustoP3Req().remove(0);
    }

}
