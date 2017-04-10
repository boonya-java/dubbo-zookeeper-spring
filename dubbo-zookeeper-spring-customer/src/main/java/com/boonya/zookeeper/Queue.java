package com.boonya.zookeeper;

import java.nio.ByteBuffer;
import java.util.List;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
/**
 * Producer-Consumer queue
 */
public class Queue extends SyncPrimitive {

	/**
	 * Constructor of producer-consumer queue
	 *
	 * @param address
	 * @param name
	 */
	Queue(String address, String name) {
		super(address);
		this.root = name;
		// Create ZK node name
		if (zk != null) {
			try {
				Stat s = zk.exists(root, true);
				if (s == null) {
					zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE,
							CreateMode.PERSISTENT);
				}
			} catch (KeeperException e) {
				System.out
						.println("Keeper exception when instantiating queue: "
								+ e.toString());
			} catch (InterruptedException e) {
				System.out.println("Interrupted exception");
			}
		}
	}

	/**
	 * Add element to the queue.
	 *
	 * @param i
	 * @return
	 */

	boolean produce(int i) throws KeeperException, InterruptedException {
		System.out.println("Produce..........");
		ByteBuffer b = ByteBuffer.allocate(4);
		byte[] value;

		// Add child with value i
		b.putInt(i);
		value = b.array();
		zk.create(root + "/element", value, Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT_SEQUENTIAL);
		System.out.println("Produce..........completed!");

		return true;
	}

	/**
	 * Remove first element from the queue.
	 *
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	int consume() throws KeeperException, InterruptedException {
		int retvalue = -1;
		Stat stat = null;

		// Get the first element available
		while (true) {
			synchronized (mutex) {
				List<String> list = zk.getChildren(root, true);
				if (list.size() == 0) {
					System.out.println("Going to wait");
					mutex.wait();
				} else {
					for (int i = 0,j=list.size(); i < j; i++) {
						// current node
						String node=root + "/"+list.get(i);
						System.out.println("consuming..............node="+node); 
						
						byte[] b = zk.getData(node, true, stat);
						zk.delete(node, 0);
						ByteBuffer buffer = ByteBuffer.wrap(b);
						retvalue = buffer.getInt();
						System.out.println("consumed..............retvalue="+retvalue); 
					}
					return retvalue;
				}
			}
		}
		
	}
	
	/**
	 * Print Info
	 * 
	 * @MethodName: print 
	 * @Description: 
	 * @param name
	 * @throws KeeperException
	 * @throws InterruptedException
	 * @throws
	 */
	public int  print(String name) throws KeeperException, InterruptedException{
		int result=0;
		List<String> children=zk.getChildren(name, true);
    	if(children!=null &&children.size()>0){
    		for (int i = 0; i < children.size(); i++) {
    			System.out.println("Print....children....node.........");
				System.out.println("Path="+children.get(i));
				System.out.println("Value="+zk.getData(name+"/"+children.get(i), true, null));
				System.out.println();
		    }
    		result=children.size();
    	}
    	return result;
	}
	
	/**
	 * Remove Info
	 * 
	 * @MethodName: remove 
	 * @Description: 
	 * @param name
	 * @throws KeeperException
	 * @throws InterruptedException
	 * @throws
	 */
	public void remove(String name) throws KeeperException, InterruptedException{
		List<String> children=zk.getChildren(name, true);
    	if(children!=null &&children.size()>0){
    		for (int i = 0; i < children.size(); i++) {
				System.out.println("Remove.............."+children.get(i));
				zk.delete(name+"/"+children.get(i), -1);
				System.out.println("Removed:"+name+"/"+children.get(i)); 
		    }
    	}
	}

}
