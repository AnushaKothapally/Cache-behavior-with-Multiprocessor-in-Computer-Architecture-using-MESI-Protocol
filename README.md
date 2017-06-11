# Cache-behavior-with-Multiprocessor-in-Computer-Architecture-using-MESI-Protocol
The implementation of my project "To include multiprocessor functionality on Project 1" 
This project Consists of 3 Processors interacting with each other through Bus and each processor consists of corresponding L1 cache and shared a memory.
This will work for Uni Processor, multiple processors with 2 nodes and 3 nodes also.
we have classes for each of the componenent and also separate class for scheduling.

The Classes are:
1)L1Data.java
2)L3Data.java
3)MainMemory.java
4)MESIProtocol.java(Consists of Main method)
5)P1RequestControler.java
6)P2L1Data.java
7)P2RequestControler.java
8)P3RequestController.java
9)processr1L1controler.java
10)processr2L1controler.java
11)processr3L1controler.java
12)Queues.java
13)Scheduling.java
14)SystemBus.java


Below are The Test cases of my project and corresponding output trace:
1)Single read per instruction with different address
2)Single read per instruction with same address.
3)Multiple read per instruction for different adresss.
4)Multiple read per instruction for same adresss.
5)single read per instruction followed by single write to the different address for different processor.
6)single read per instruction followed by single write to the same address for different processor.
7)single read per instruction followed by single write to the same address for same processor.
8)Multiple writes with the different address in the same instuction to the different processor.
9)Multiple writes with the same address in the same instuction to the different processor. 
10)combination of read and write commands with different address to the different processors.
11) functionality of Uniprocessor for read and write
	- single read, single write
	- multiple read,multiple write
	- read and write
12) Test cases from 1 to 11 for 2 core processors.
