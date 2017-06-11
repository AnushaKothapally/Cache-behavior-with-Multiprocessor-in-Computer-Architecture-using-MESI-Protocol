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
public class Scheduling {

    Queues queueline;
    MESIProtocol mp = new MESIProtocol();

    public Scheduling(Queues queue) {
        //System.out.println("Queue pls" + queue);
        this.queueline = queue;

    }

    public void run() {
        P1RequestControler p1req = new P1RequestControler(queueline);
        processr1L1controler p1c = new processr1L1controler(queueline);
        L1Data p1d = new L1Data(queueline);
        P2RequestControler p2req = new P2RequestControler(queueline);
        processr2L1controler p2c = new processr2L1controler(queueline);
        P2L1Data p2d = new P2L1Data(queueline);
        P3RequestController p3req = new P3RequestController(queueline);
        processr3L1controller p3c = new processr3L1controller(queueline);
        L3Data p3d = new L3Data(queueline);
        SystemBus sb = new SystemBus(queueline);
        MainMemory mem = new MainMemory(queueline);
        int i = 1;
        while (true) {
            System.out.println("Cycle" + i);
            System.out.println("----------------------");
            //  System.out.println(queueline.getQueue().size());
            if (queueline.getQueue() != null && queueline.getQueue().size() != 0 && queueline.getQueue().get(0) != null && !queueline.getQueue().isEmpty()) {

                mp.movequeue(queueline);
                // break;
            }
            //process 1
            if (queueline.getP1ReqQueue() != null && queueline.getP1ReqQueue().size() != 0 && queueline.getP1ReqQueue().get(0) != null && !queueline.getP1ReqQueue().isEmpty()) {
               // System.out.println("print" + queueline.getP1ReqQueue() + "" + queueline);
                p1req.p1top1req();
            }
            if (queueline.getP1reqtoP1c() != null && queueline.getP1reqtoP1c().size() != 0 && queueline.getP1reqtoP1c().get(0) != null && !queueline.getP1reqtoP1c().isEmpty()) {
                p1c.readfromP1reqq();
            }
            if (queueline.getP1DtoP1CQueue() != null && queueline.getP1DtoP1CQueue().size() != 0 && queueline.getP1DtoP1CQueue().get(0) != null && !queueline.getP1DtoP1CQueue().isEmpty()) {
                p1c.notempty();
            }
            if (queueline.getP1CtoP1DQueue() != null && queueline.getP1CtoP1DQueue().size() != 0 && queueline.getP1CtoP1DQueue().get(0) != null && !queueline.getP1CtoP1DQueue().isEmpty()) {
                p1d.L1DataController();
            }

            //process 2
            if (queueline.getP2ReqQueue() != null && queueline.getP2ReqQueue().size() != 0 && queueline.getP2ReqQueue().get(0) != null && !queueline.getP2ReqQueue().isEmpty()) {
                //System.out.println("print" + queueline.getP2ReqQueue() + "" + queueline);
                p2req.p2top2req();
            }
            if (queueline.getP2reqtoP2c() != null && queueline.getP2reqtoP2c().size() != 0 && queueline.getP2reqtoP2c().get(0) != null && !queueline.getP2reqtoP2c().isEmpty()) {
                p2c.readfromP2reqq();
            }
            if (queueline.getP2DtoP2CQueue() != null && queueline.getP2DtoP2CQueue().size() != 0 && queueline.getP2DtoP2CQueue().get(0) != null && !queueline.getP2DtoP2CQueue().isEmpty()) {
                p2c.notempty();
            }
            if (queueline.getP2CtoP2DQueue() != null && queueline.getP2CtoP2DQueue().size() != 0 && queueline.getP2CtoP2DQueue().get(0) != null && !queueline.getP2CtoP2DQueue().isEmpty()) {
                p2d.P2L1DataController();
            }
            //processor 3
            if (queueline.getP3ReqQueue() != null && queueline.getP3ReqQueue().size() != 0 && queueline.getP3ReqQueue().get(0) != null && !queueline.getP3ReqQueue().isEmpty()) {
                // System.out.println("print"+queueline.getP2ReqQueue()+""+queueline);
                p3req.p3top3req();
            }
            if (queueline.getP3reqtoP3c() != null && queueline.getP3reqtoP3c().size() != 0 && queueline.getP3reqtoP3c().get(0) != null && !queueline.getP3reqtoP3c().isEmpty()) {
                p3c.readfromP3reqq();
            }
            if (queueline.getP3CtoP3DQueue() != null && queueline.getP3CtoP3DQueue().size() != 0 && queueline.getP3CtoP3DQueue().get(0) != null && !queueline.getP3CtoP3DQueue().isEmpty()) {
                p3d.P3L1DataController();
            }
            if (queueline.getP3DtoP3CQueue() != null && queueline.getP3DtoP3CQueue().size() != 0 && queueline.getP3DtoP3CQueue().get(0) != null && !queueline.getP3DtoP3CQueue().isEmpty()) {
                p3c.notempty();
            }

            if (queueline.getOutput() != null && queueline.getOutput().size() != 0 && queueline.getOutput().get(0) != null && !queueline.getOutput().isEmpty()) {
                mp.output(queueline);
            }
            if (queueline.getBuStatus().equals("request") && queueline.getP1ReqtoBusQueue() != null && queueline.getP1ReqtoBusQueue().size() != 0 && queueline.getP1ReqtoBusQueue().get(0) != null && !queueline.getP1ReqtoBusQueue().isEmpty()) {
                sb.checkProcessors(queueline.getP1ReqtoBusQueue().get(0));
                queueline.getP1ReqtoBusQueue().remove(0);
            }
            // System.out.println("*****"+queueline.getP2ReqtoBusQueue());
            if (queueline.getBuStatus().equals("request") && queueline.getP2ReqtoBusQueue() != null && queueline.getP2ReqtoBusQueue().size() != 0 && queueline.getP2ReqtoBusQueue().get(0) != null && !queueline.getP2ReqtoBusQueue().isEmpty()) {
                sb.checkProcessors(queueline.getP2ReqtoBusQueue().get(0));
                queueline.getP2ReqtoBusQueue().remove(0);
            }
            if (queueline.getBuStatus().equals("request") && queueline.getP3ReqtoBusQueue() != null && queueline.getP3ReqtoBusQueue().size() != 0 && queueline.getP3ReqtoBusQueue().get(0) != null && !queueline.getP3ReqtoBusQueue().isEmpty()) {
                sb.checkProcessors(queueline.getP3ReqtoBusQueue().get(0));
                queueline.getP3ReqtoBusQueue().remove(0);
            }
            if (queueline.getBuStatus().equals("response") && queueline.getP1RestoBusQueue() != null && queueline.getP1RestoBusQueue().size() != 0 && queueline.getP1RestoBusQueue().get(0) != null && !queueline.getP1RestoBusQueue().isEmpty()) {
                sb.checkProcessorsResponse(queueline.getP1RestoBusQueue().get(0));
                queueline.getP1RestoBusQueue().remove(0);
            }
            if (queueline.getBuStatus().equals("response") && queueline.getP2RestoBusQueue() != null && queueline.getP2RestoBusQueue().size() != 0 && queueline.getP2RestoBusQueue().get(0) != null && !queueline.getP2RestoBusQueue().isEmpty()) {
                sb.checkProcessorsResponse(queueline.getP2RestoBusQueue().get(0));
                queueline.getP2RestoBusQueue().remove(0);
            }
            if (queueline.getBuStatus().equals("response") && queueline.getP3RestoBusQueue() != null && queueline.getP3RestoBusQueue().size() != 0 && queueline.getP3RestoBusQueue().get(0) != null && !queueline.getP3RestoBusQueue().isEmpty()) {
                sb.checkProcessorsResponse(queueline.getP3RestoBusQueue().get(0));
                queueline.getP3RestoBusQueue().remove(0);
            }
            if (queueline.getBustoP1Req() != null && queueline.getBustoP1Req().size() != 0 && queueline.getBustoP1Req().get(0) != null && !queueline.getBustoP1Req().isEmpty()) {
                p1req.bustoP1Req();
            }
            if (queueline.getBustoP1Res() != null && queueline.getBustoP1Res().size() != 0 && queueline.getBustoP1Res().get(0) != null && !queueline.getBustoP1Res().isEmpty()) {
                p1req.bustoP1Res();
            }
            if (queueline.getBustoP2Req() != null && queueline.getBustoP2Req().size() != 0 && queueline.getBustoP2Req().get(0) != null && !queueline.getBustoP2Req().isEmpty()) {
                p2req.bustoP2Req();
            }
            //  System.out.println("Am i?????"+queueline.getBustoP2Res());
            if (queueline.getBustoP2Res() != null && queueline.getBustoP2Res().size() != 0 && queueline.getBustoP2Res().get(0) != null && !queueline.getBustoP2Res().isEmpty()) {
                //  System.out.println("Am i?????");
                p2req.bustoP2Res();
            }
            if (queueline.getBustoP3Req() != null && queueline.getBustoP3Req().size() != 0 && queueline.getBustoP3Req().get(0) != null && !queueline.getBustoP3Req().isEmpty()) {
                p3req.bustoP3Req();
            }
            if (queueline.getBustoP3Res() != null && queueline.getBustoP3Res().size() != 0 && queueline.getBustoP3Res().get(0) != null && !queueline.getBustoP3Res().isEmpty()) {
                p3req.bustoP3Res();
            }

            if (queueline.getBustoMemory() != null && queueline.getBustoMemory().size() != 0 && queueline.getBustoMemory().get(0) != null && !queueline.getBustoMemory().isEmpty()) {
                //  System.out.println("Am i?????");
                mem.memory();
            }
            if (queueline.getMemorytoBus() != null && queueline.getMemorytoBus().size() != 0 && queueline.getMemorytoBus().get(0) != null && !queueline.getMemorytoBus().isEmpty()) {
                //  System.out.println("Am i?????");
                sb.checkMemoryResponse();
            }
//        if(queueline.getP1ReqQueue()!=null&&queueline.getP2ReqQueue()!=null&&queueline.getP3ReqQueue()!=null){
//        System.out.println(queueline.getP1ReqQueue());
//        System.out.println(queueline.getP2ReqQueue());
//        System.out.println(queueline.getP3ReqQueue());
//        }
            i++;
        }
    }

}
