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
public class SystemBus {

    Queues que;
    String request=null;
    SystemBus(Queues queline) {
        que = queline;
    }

    public void checkProcessors(String instruction) {
        
        que.setBuStatus("response");
        String[] ins = instruction.split(" ", 0);
        //System.out.println(ins[0] + "instruction");
        if(que.uniprocessor==false&&que.dualprocessor==false){
         System.out.println("SystemBus received ins from processor: " + instruction);
        if (ins[0].contains("R1")) {
            que.setBustoP2Res(instruction);
            que.setBustoP3Res(instruction);
        }
        if (ins[0].contains("R2")) {
            que.setBustoP1Res(instruction);
            que.setBustoP3Res(instruction);
        }
        if (ins[0].contains("R3")) {
            que.setBustoP1Res(instruction);
            que.setBustoP2Res(instruction);
        }
        if (ins[0].contains("W1")) {
            que.setBustoP2Res(instruction);
            que.setBustoP3Res(instruction);
        }
        if (ins[0].contains("W2")) {
            que.setBustoP1Res(instruction);
            que.setBustoP3Res(instruction);
        }
        if (ins[0].contains("W3")) {
            que.setBustoP1Res(instruction);
            que.setBustoP2Res(instruction);
        }
        }else if(que.uniprocessor==true){
            System.out.println("Uniprocessor instruction");
        System.out.println(ins[0] + "instruction");
        que.setBustoMemory(instruction);
        }
        else if(que.dualprocessor==true){
            System.out.println("Dual instruction");
        if(que.p1p2){
         if (ins[0].contains("R1")) {
            que.setBustoP2Res(instruction);
        }
        if (ins[0].contains("R2")) {
            que.setBustoP1Res(instruction);  
        } 
        if (ins[0].contains("W1")) {
            que.setBustoP2Res(instruction);
        }
        if (ins[0].contains("W2")) {
            que.setBustoP1Res(instruction);
        }
        }
        else  if(que.p1p3){
         if (ins[0].contains("R1")) {
            que.setBustoP3Res(instruction);
        }
        if (ins[0].contains("R3")) {
            que.setBustoP1Res(instruction);  
        } 
        if (ins[0].contains("W1")) {
            que.setBustoP3Res(instruction);
        }
        if (ins[0].contains("W3")) {
            que.setBustoP1Res(instruction);
        }
        }
          else  if(que.p2p3){
         if (ins[0].contains("R2")) {
            que.setBustoP3Res(instruction);
        }
        if (ins[0].contains("R3")) {
            que.setBustoP2Res(instruction);  
        } 
        if (ins[0].contains("W2")) {
            que.setBustoP3Res(instruction);
        }
        if (ins[0].contains("W3")) {
            que.setBustoP2Res(instruction);
        }
        }
        }
    }

    public void checkProcessorsResponse(String instru) {
        System.out.println("Data received from processor" + instru+ "ACK"+que.ackCount);
        String[] ins = instru.split(" ", 0);
        que.ackCount++;
        if("response".equals(que.getBuStatus())&& "processed".equals(request)){
        que.ackCount=0;
        que.setBuStatus("request");
        request=null;
        System.out.println("Now status changed to Request");
        }
        else if (ins.length == 4 && !ins[3].equals("I")) {
            instru = ins[1] + " " + ins[2] + " " + ins[3];
            if (ins[0].contains("R1")) {
                instru = "R1" + " " + instru;
                que.setBustoP1Req(instru);
            } else if (ins[0].contains("R2")) {
                instru = "R2" + " " + instru;
                que.setBustoP2Req(instru);
            } else if (ins[0].contains("R3")) {
                instru = "R3" + " " + instru;
                que.setBustoP3Req(instru);
            }
            request="processed";
          if(que.ackCount==2){
            que.setBuStatus("request");
            request=null;
            }
          else if(que.ackCount==1&&que.dualprocessor==true){
           que.setBuStatus("request");
            request=null;
          }
        } else if (ins.length == 5 && ins[3].contains("DB")) {
            instru = ins[1] + " " + ins[2] + " " + ins[4];
            String upd2mem = "S" + " " + ins[1] + " " + ins[2] + " " + ins[4];
            if (ins[0].contains("R1")) {
                instru = "R1" + " " + instru;
                que.setBustoP1Req(instru);
            } else if (ins[0].contains("R2")) {
                instru = "R2" + " " + instru;
                que.setBustoP2Req(instru);
            } else if (ins[0].contains("R3")) {
                instru = "R3" + " " + instru;
                que.setBustoP3Req(instru);
            }
            //que.ackCount = 0;
            que.setBustoMemory(upd2mem);
            request="processed";
            if(que.ackCount==2){
            que.setBuStatus("request");
            request=null;
            }
            else if(que.ackCount==1&&que.dualprocessor==true){
           que.setBuStatus("request");
            request=null;
          }
        } else if (ins[0].contains("BusW") && ins[0].contains("Ex")) {
            instru = ins[1] + " " + ins[2] + " " + ins[3];

            if (que.ackCount == 2) {
                if (ins[0].contains("W1")) {
                    instru = "S1" + " " + instru;
                    que.setBustoP1Req(instru);
                } else if (ins[0].contains("W2")) {
                    instru = "S2" + " " + instru;
                    que.setBustoP2Req(instru);
                } else if (ins[0].contains("W3")) {
                    instru = "S3" + " " + instru;
                    que.setBustoP3Req(instru);
                }
                request=null;
                
                if(que.ackCount==2)
               que.setBuStatus("request");
                que.ackCount = 0;
            }else if(que.ackCount==1&&que.dualprocessor==true){
           que.setBuStatus("request");
            request=null;
          }
        } else if (que.ackCount == 2||(que.ackCount==1&&que.dualprocessor==true)) {
            System.out.println("Checking in Memory; ack received" + que.ackCount);
            instru = ins[0] + " " + ins[1] + " " + ins[2];
            que.setBustoMemory(instru);
            que.ackCount = 0;
        }
        else{
       // System.out.println("**********@@@@@@########))))!!!!!"+que.ackCount);
        }
    }

    public void checkMemoryResponse() {
        String instru = que.getMemorytoBus().get(0);
        System.out.println("In Systembus, Data read from Memory" + instru.substring(0,25));
        String[] ins = instru.split(" ", 0);
        instru = ins[1] + " " + ins[2] + " " + ins[3];
        if (ins[0].contains("R1")) {
            instru = "R1" + " " + instru;
            //System.out.println("*****" + instru);
            que.setBustoP1Req(instru);
        } else if (ins[0].contains("R2")) {
            instru = "R2" + " " + instru;
           // System.out.println("*****" + instru);
            que.setBustoP2Req(instru);
        } else if (ins[0].contains("R3")) {
            instru = "R3" + " " + instru;
            //System.out.println("*****" + instru);
            que.setBustoP3Req(instru);
        }
        else if (ins[0].contains("W1")) {
            instru = "S1" + " " + instru;
            //System.out.println("*****" + instru);
            que.setBustoP1Req(instru);
        } else if (ins[0].contains("W2")) {
            instru = "S2" + " " + instru;
            //System.out.println("*****" + instru);
            que.setBustoP2Req(instru);
        } else if (ins[0].contains("W3")) {
            instru = "S3" + " " + instru;
           // System.out.println("*****" + instru);
            que.setBustoP3Req(instru);
        }
        que.setBuStatus("request");
        que.getMemorytoBus().remove(0);
        //System.out.println("Now status changed to Request");
    }

}
