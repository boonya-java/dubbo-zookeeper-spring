package com.boonya.zookeeper;

/**
 * Zookeeper集群测试
 * 
 * @package com.boonya.zookeeper.ZookeeperCluster
 * @date   2017年5月11日  上午10:09:54
 * @author pengjunlin
 * @comment   
 * @update
 */
public class ZookeeperCluster {
	
	 /** zookeeper地址 */  
    private static final String CONNECTION_ADDR = "192.168.200.122:2181,192.168.200.122:2182,192.168.200.122:2183";  
    private static final String CONNECTION_NODE_ONE = "192.168.200.122:2181";  
    private static final String CONNECTION_NODE_TWO = "192.168.200.122:2182";  
    private static final String CONNECTION_NODE_THREE = "192.168.200.122:2183";  
    
    /** session超时时间 */  
    private static final int SESSION_TIMEOUT = 2000;// ms  
    
    /** zk父路径设置 */  
    private static final String PARENT_PATH = "/cluster";  
    
    /** zk子路径设置 */  
    private static final String CHILDREN_PATH = "/cluster/children";  
	
	public static void main(String[] args) throws InterruptedException {
		  // 建立watcher  
        ZooKeeperWatcher zkWatch = new ZooKeeperWatcher();  
        // 创建连接  
        zkWatch.createConnection(CONNECTION_ADDR, SESSION_TIMEOUT);  
        // System.out.println(zkWatch.zk.toString());  
  
        Thread.sleep(1000);  
  
        // 清理之前的节点信息  
        zkWatch.deleteAllTestPath();  
  
        // 1.创建节点信息，并watch这个节点 ========  
        zkWatch.createPath(PARENT_PATH, System.currentTimeMillis() + "");  
        // 2.对父节点的孩子节点，进行watch(不然父节点的子节点变化，将收不到watch)  
        zkWatch.getChildren(PARENT_PATH, true);  
        Thread.sleep(1000);  
        // 3.创建子节点  
        zkWatch.createPath(CHILDREN_PATH, System.currentTimeMillis() + "");  
        zkWatch.writeData(CHILDREN_PATH, "CLUSTER-7893942389043098402-BOONYA");
  
        Thread.sleep(50000);  
        
        /**************CLUSTER TEST NODE DATA*********SATRT*******/
        ZooKeeperWatcher node1=new ZooKeeperWatcher();
        node1.createConnection(CONNECTION_NODE_ONE, SESSION_TIMEOUT); 
        System.out.println("cluster node1"+CHILDREN_PATH+" path value:"+node1.readData(CHILDREN_PATH, true));
        node1.releaseConnection();
        
        ZooKeeperWatcher node2=new ZooKeeperWatcher();
        node2.createConnection(CONNECTION_NODE_TWO, SESSION_TIMEOUT); 
        System.out.println("cluster node2"+CHILDREN_PATH+" path value:"+node2.readData(CHILDREN_PATH, true));
        node2.releaseConnection();
        
        ZooKeeperWatcher node3=new ZooKeeperWatcher();
        node3.createConnection(CONNECTION_NODE_THREE, SESSION_TIMEOUT); 
        System.out.println("cluster node3"+CHILDREN_PATH+" path value:"+node3.readData(CHILDREN_PATH, true));
        node3.releaseConnection();
        
        /**************CLUSTER TEST NODE DATA*********END*******/
        
        
        // 4.删除节点信息  
        zkWatch.deleteAllTestPath();  
        Thread.sleep(1000);  
        zkWatch.releaseConnection();  
	}

}
