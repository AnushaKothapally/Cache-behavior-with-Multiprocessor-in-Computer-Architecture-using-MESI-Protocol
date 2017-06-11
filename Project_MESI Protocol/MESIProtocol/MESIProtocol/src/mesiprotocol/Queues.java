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
 * @author Lakshman
 */
public class Queues {

    /**
     * @return the L1DtoL1CQueue
     */
    public List<String> queue=new ArrayList();
    public List<String> P1ReqQueue=new ArrayList();
    public List<String> P2ReqQueue=new ArrayList();
    public List<String> P3ReqQueue=new ArrayList();
    public List<String> P1ResQueue=new ArrayList();
    public List<String> P2ResQueue=new ArrayList();
    public List<String> P3ResQueue=new ArrayList();
    public List<String> Output=new ArrayList();
    public List<String> P1reqtoP1c=new ArrayList();
    public List<String> P2reqtoP2c=new ArrayList();
    public List<String> P1CtoP1DQueue=new ArrayList();
    public List<String> P2CtoP2DQueue=new ArrayList();
    public List<String> P3CtoP3DQueue=new ArrayList();
    public List<String> P1DtoP1CQueue=new ArrayList();
    public List<String> P2DtoP2CQueue=new ArrayList();
    public List<String> P3DtoP3CQueue=new ArrayList();
    public List<String> P1toreqQueue=new ArrayList();
    public List<String> P2toreqQueue=new ArrayList();
    public List<String> BustoMemory=new ArrayList();
    public List<String> MemorytoBus=new ArrayList();
    public List<String> P1RestoBusQueue=new ArrayList();/*start of Queues to communicate with bus*/
    public List<String> P1ReqtoBusQueue=new ArrayList();
    public List<String> P2ReqtoBusQueue=new ArrayList();
    public List<String> P2RestoBusQueue=new ArrayList();
    public List<String> BustoP1Req=new ArrayList();
    public List<String> BustoP1Res=new ArrayList();
    public List<String> BustoP2Req=new ArrayList();
    public List<String> BustoP2Res=new ArrayList();  /*End Queues to communicate with bus*/
    public List<String> P3reqtoP3c=new ArrayList();
    public List<String> P3ReqtoBusQueue=new ArrayList();
    public List<String> BustoP3Req=new ArrayList();
    public List<String> P3RestoBusQueue=new ArrayList();
    public List<String> BustoP3Res=new ArrayList();
    public String buStatus="request";
    public int ackCount=0;
    public String prev=null;
    public int cycle=0;
    public boolean uniprocessor=false;
    public boolean dualprocessor=false;
    public boolean p1p2=false;
    public boolean p2p3=false;
    public boolean p1p3=false;
    /**
     * @return the CPUQueue
     */
    public List<String> getQueue() {
        return queue;
    }

    /**
     * @param CPUQueue the CPUQueue to set
     */
    public void setQueue(List<String> que) {
        queue.addAll(que);
    }

    /**
     * @return the P1ReqQueue
     */
    public List<String> getP1ReqQueue() {
        return P1ReqQueue;
    }

    /**
     * @param P1ReqQueue the P1ReqQueue to set
     */
    public void setP1ReqQueue(String input) {
        P1ReqQueue.add(input);
    }

    /**
     * @return the P2ReqQueue
     */
    public List<String> getP2ReqQueue() {
        return P2ReqQueue;
    }

    /**
     * @param P2ReqQueue the P2ReqQueue to set
     */
    public void setP2ReqQueue(String input) {
        this.P2ReqQueue.add(input);
    }

    /**
     * @return the P3ReqQueue
     */
    public List<String> getP3ReqQueue() {
        return P3ReqQueue;
    }

    /**
     * @param P3ReqQueue the P3ReqQueue to set
     */
    public void setP3ReqQueue(String input) {
        this.P3ReqQueue.add(input);
    }

    /**
     * @return the P1ResQueue
     */
    public List<String> getP1ResQueue() {
        return P1ResQueue;
    }

    /**
     * @param P1ResQueue the P1ResQueue to set
     */
    public void setP1ResQueue(String input) {
        this.P1ResQueue.add(input);
    }

    /**
     * @return the P2ResQueue
     */
    public List<String> getP2ResQueue() {
        return P2ResQueue;
    }

    /**
     * @param P2ResQueue the P2ResQueue to set
     */
    public void setP2ResQueue(String input) {
        this.P2ResQueue.add(input);
    }

    /**
     * @return the P3ResQueue
     */
    public List<String> getP3ResQueue() {
        return P3ResQueue;
    }

    /**
     * @param P3ResQueue the P3ResQueue to set
     */
    public void setP3ResQueue(String input) {
        this.P3ResQueue.add(input);
    }

    /**
     * @return the Output
     */
    public List<String> getOutput() {
        return Output;
    }

    /**
     * @param Output the Output to set
     */
    public void setOutput(String Output) {
        this.Output.add(Output);
    }

    /**
     * @return the P1CtoP1DQueue
     */
    public List<String> getP1CtoP1DQueue() {
        return P1CtoP1DQueue;
    }

    /**
     * @param P1CtoP1DQueue the P1CtoP1DQueue to set
     */
    public void setP1CtoP1DQueue(String input) {
        this.P1CtoP1DQueue.add(input);
    }

    /**
     * @return the P2CtoP2DQueue
     */
    public List<String> getP2CtoP2DQueue() {
        return P2CtoP2DQueue;
    }

    /**
     * @param P2CtoP2DQueue the P2CtoP2DQueue to set
     */
    public void setP2CtoP2DQueue(String input) {
        this.P2CtoP2DQueue.add(input);
    }

    /**
     * @return the P3CtoP3DQueue
     */
    public List<String> getP3CtoP3DQueue() {
        return P3CtoP3DQueue;
    }

    /**
     * @param P3CtoP3DQueue the P3CtoP3DQueue to set
     */
    public void setP3CtoP3DQueue(String input) {
        this.P3CtoP3DQueue.add(input);
    }

    /**
     * @return the P1DtoP1CQueue
     */
    public List<String> getP1DtoP1CQueue() {
        return P1DtoP1CQueue;
    }

    /**
     * @param P1DtoP1CQueue the P1DtoP1CQueue to set
     */
    public void setP1DtoP1CQueue(String input) {
        this.P1DtoP1CQueue.add(input);
    }

    /**
     * @return the P2DtoP2CQueue
     */
    public List<String> getP2DtoP2CQueue() {
        return P2DtoP2CQueue;
    }

    /**
     * @param P2DtoP2CQueue the P2DtoP2CQueue to set
     */
    public void setP2DtoP2CQueue(String input) {
        this.P2DtoP2CQueue.add(input);
    }

    /**
     * @return the P3DtoP3CQueue
     */
    public List<String> getP3DtoP3CQueue() {
        return P3DtoP3CQueue;
    }

    /**
     * @param P3DtoP3CQueue the P3DtoP3CQueue to set
     */
    public void setP3DtoP3CQueue(String input) {
        this.P3DtoP3CQueue.add(input);
    }

    /**
     * @return the P1toreqQueue
     */
    public List<String> getP1toreqQueue() {
        return P1toreqQueue;
    }

    /**
     * @param P1toreqQueue the P1toreqQueue to set
     */
    public void setP1toreqQueue(String input) {
        this.P1toreqQueue.add(input);
    }

    /**
     * @return the P1reqtoP1c
     */
    public List<String> getP1reqtoP1c() {
        return P1reqtoP1c;
    }

    /**
     * @param P1reqtoP1c the P1reqtoP1c to set
     */
    public void setP1reqtoP1c(String input) {
        this.P1reqtoP1c.add(input);
    }

    /**
     * @return the P2reqtoP2c
     */
    public List<String> getP2reqtoP2c() {
        return P2reqtoP2c;
    }

    /**
     * @param P2reqtoP2c the P2reqtoP2c to set
     */
    public void setP2reqtoP2c(String input) {
        this.P2reqtoP2c.add(input);
    }

    /**
     * @return the P2toreqQueue
     */
    public List<String> getP2toreqQueue() {
        return P2toreqQueue;
    }

    /**
     * @param P2toreqQueue the P2toreqQueue to set
     */
    public void setP2toreqQueue(String input) {
        this.P2toreqQueue.add(input);
    }

    /**
     * @return the BustoMemory
     */
    public List<String> getBustoMemory() {
        return BustoMemory;
    }

    /**
     * @param BustoMemory the BustoMemory to set
     */
    public void setBustoMemory(String BustoMem) {
        BustoMemory.add(BustoMem);
    }

    /**
     * @return the MemorytoBus
     */
    public List<String> getMemorytoBus() {
        return MemorytoBus;
    }

    /**
     * @param MemorytoBus the MemorytoBus to set
     */
    public void setMemorytoBus(String MemtoBus) {
        MemorytoBus.add(MemtoBus);
    }

    /**
     * @return the P1RestoBusQueue
     */
    public List<String> getP1RestoBusQueue() {
        return P1RestoBusQueue;
    }

    /**
     * @param P1RestoBusQueue the P1RestoBusQueue to set
     */
    public void setP1RestoBusQueue(String P1RestoBusQ) {
        P1RestoBusQueue.add(P1RestoBusQ);
    }

    /**
     * @return the P1ReqtoBusQueue
     */
    public List<String> getP1ReqtoBusQueue() {
        return P1ReqtoBusQueue;
    }

    /**
     * @param P1ReqtoBusQueue the P1ReqtoBusQueue to set
     */
    public void setP1ReqtoBusQueue(String P1ReqtoBus) {
        P1ReqtoBusQueue.add(P1ReqtoBus);
    }

    /**
     * @return the P2ReqtoBusQueue
     */
    public List<String> getP2ReqtoBusQueue() {
        return P2ReqtoBusQueue;
    }

    /**
     * @param P2ReqtoBusQueue the P2ReqtoBusQueue to set
     */
    public void setP2ReqtoBusQueue(String P2Req2Bus) {
        P2ReqtoBusQueue.add(P2Req2Bus);
    }

    /**
     * @return the P2RestoBusQueue
     */
    public List<String> getP2RestoBusQueue() {
        return P2RestoBusQueue;
    }

    /**
     * @param P2RestoBusQueue the P2RestoBusQueue to set
     */
    public void setP2RestoBusQueue(String P2RestoBus) {
        P2RestoBusQueue.add(P2RestoBus);
    }

    /**
     * @return the BustoP1Req
     */
    public List<String> getBustoP1Req() {
        return BustoP1Req;
    }

    /**
     * @param BustoP1Req the BustoP1Req to set
     */
    public void setBustoP1Req(String BustoP1) {
        BustoP1Req.add(BustoP1);
    }

    /**
     * @return the BustoP1Res
     */
    public List<String> getBustoP1Res() {
        return BustoP1Res;
    }

    /**
     * @param BustoP1Res the BustoP1Res to set
     */
    public void setBustoP1Res(String BustoP1) {
        BustoP1Res.add(BustoP1);
    }

    /**
     * @return the BustoP2Req
     */
    public List<String> getBustoP2Req() {
        return BustoP2Req;
    }

    /**
     * @param BustoP2Req the BustoP2Req to set
     */
    public void setBustoP2Req(String BustoP2) {
        BustoP2Req.add(BustoP2);
    }

    /**
     * @return the BustoP2Res
     */
    public List<String> getBustoP2Res() {
        return BustoP2Res;
    }

    /**
     * @param BustoP2Res the BustoP2Res to set
     */
    public void setBustoP2Res(String BustoP2) {
        BustoP2Res.add(BustoP2);
    }

    /**
     * @return the buStatus
     */
    public String getBuStatus() {
        return buStatus;
    }

    /**
     * @param buStatus the buStatus to set
     */
    public void setBuStatus(String buStatus) {
        this.buStatus = buStatus;
    }

    /**
     * @return the P3reqtoP3c
     */
    public List<String> getP3reqtoP3c() {
        return P3reqtoP3c;
    }

    /**
     * @param P3reqtoP3c the P3reqtoP3c to set
     */
    public void setP3reqtoP3c(String P3toP3c) {
        P3reqtoP3c.add(P3toP3c);
    }

    /**
     * @return the P3ReqtoBusQueue
     */
    public List<String> getP3ReqtoBusQueue() {
        return P3ReqtoBusQueue;
    }

    /**
     * @param P3ReqtoBusQueue the P3ReqtoBusQueue to set
     */
    public void setP3ReqtoBusQueue(String input) {
        P3ReqtoBusQueue.add(input);
    }

    /**
     * @return the BustoP3Req
     */
    public List<String> getBustoP3Req() {
        return BustoP3Req;
    }

    /**
     * @param BustoP3Req the BustoP3Req to set
     */
    public void setBustoP3Req(String input) {
        BustoP3Req.add(input);
    }

    /**
     * @return the P3RestoBusQueue
     */
    public List<String> getP3RestoBusQueue() {
        return P3RestoBusQueue;
    }

    /**
     * @param P3RestoBusQueue the P3RestoBusQueue to set
     */
    public void setP3RestoBusQueue(String input) {
        P3RestoBusQueue.add(input);
    }

    /**
     * @return the BustoP3Res
     */
    public List<String> getBustoP3Res() {
        return BustoP3Res;
    }

    /**
     * @param BustoP3Res the BustoP3Res to set
     */
    public void setBustoP3Res(String input) {
        BustoP3Res.add(input);
    }

    /**
     * @return the previous
     */
    public String getPrev() {
        return prev;
    }

    /**
     * @param prev the previous to set
     */
    public void setPrev(String prev) {
        this.prev = prev;
    }

    /**
     * @return the cycle
     */
    public int getCycle() {
        return cycle;
    }

    /**
     * @param cycle the cycle to set
     */
    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
    /**
     * @return the CPUQueue
     */
    
}
