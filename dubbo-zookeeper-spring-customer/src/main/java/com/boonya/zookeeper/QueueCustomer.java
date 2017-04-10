package com.boonya.zookeeper;

import org.apache.zookeeper.KeeperException;

public class QueueCustomer {
	
	public static void main(String[] args) throws KeeperException, InterruptedException {
		
        String address="192.168.234.128:2181";
		
    	String name="/root";
    	
    	Queue queue=new Queue(address, name);

		int count = queue.print(name);// print
		
		if(count>0){
			queue.remove(name);//remove
		}
    	
    	queue.consume();
	}

}
