package com.boonya.zookeeper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
/**
 * Zk客户端，不使用Watcher
 * 
 * 参考地址:http://blog.csdn.net/zbw18297786698/article/details/53816426
 * @package com.boonya.zookeeper.ZookeeperNoWatcher
 * @date   2017年4月7日  下午1:17:14
 * @author pengjunlin
 * @comment   
 * @update
 */
public class ZookeeperNoWatcher {
	
	 /** zookeeper地址 */  
    private final String CONNECT_ADDR = "192.168.200.122:2181";  
    
    /** session超时时间 */  
    private final int SESSION_OUTTIME = 2000;// ms  
    
    /*** 用于操作Zookeeper */  
    private ZooKeeper zk = null;  
    
    /** zk父路径设置 */  
    private static final String PARENT_PATH = "/testWatch";  
    
    /** zk子路径设置 */  
    private static final String CHILDREN_PATH = "/testWatch/children";  
  
    public ZooKeeper connection() {  
        try {  
            zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher() {  
                @Override  
                public void process(WatchedEvent event) {  
                    // 获取事件的状态  
                    KeeperState keeperState = event.getState();  
                    EventType eventType = event.getType();  
  
                    // 如果是建立连接  
                    if (KeeperState.SyncConnected == keeperState) {  
                        if (EventType.None == eventType) {  
                            System.out.println("zk 建立连接");  
                        }  
                    }  
                }  
            });  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
 
        return zk;
    }  
  
    /** 
     * 创建节点的路径 
     *  
     * @param path 
     *            节点的路径 
     * @param data 
     *            节点的数据 
     * @param createMode 
     *            节点的模式 
     * @return 创建节点的路径 
     */  
    public String createNode(String path, String data, CreateMode createMode) {  
        String result = null;  
        try {  
            result = zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE,  
                    createMode);  
        } catch (KeeperException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     *  
     * @param path 
     *            要获取数据的路径 
     *  
     * @return 路径节点的数据 
     */  
    public String getData(String path) {  
        String result = null;  
        try {  
            byte[] data = zk.getData(path, false, null);  
            result = new String(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 设置节点信息 
     *  
     * @param path 
     * @param data 
     * @return 
     */  
    public String setData(String path, String data) {  
        String result = null;  
        try {  
            zk.setData(path, data.getBytes(), -1);  
            result = new String(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 同步的方式，删除节点信息 
     *  
     * @param path 
     */  
    public void deleteNode(String path) {  
        try {  
            zk.delete(path, -1);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 使用异步的方式，删除节点信息 
     *  
     * @param path 
     * @param object 
     */  
    public void deleteNodeCallback(String path, Object object) {  
        try {  
            zk.delete(path, -1, new VoidCallback() {  
  
                @Override  
                public void processResult(int paramInt, String paramString,  
                        Object paramObject) {  
                    System.out.println("paramString: " + paramString);  
                    System.out.println("paramObject: " + paramObject);  
                }  
            }, object);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 返回父路径下的，孩子节点的名字信息 
     *  
     * @param parentPath 
     * @return 
     */  
    public List<String> getChildsNameInfo(String parentPath) {  
        List<String> childrens = null;  
        try {  
            childrens = zk.getChildren(parentPath, false);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return childrens;  
    }  
  
    /** 
     * 返回父目录下的子节点信息 
     *  
     * @param parentPath 
     * @return 
     */  
    public Map<String, String> getChildsDataInfo(String parentPath) {  
        List<String> childrensNameInfo = null;  
        Map<String, String> childrens = new HashMap<String, String>();  
        try {  
            childrensNameInfo = zk.getChildren(parentPath, false);  
            for (int i = 0; i < childrensNameInfo.size(); i++) {  
                String data = getData(parentPath + childrensNameInfo.get(i));  
                childrens.put(parentPath + childrensNameInfo.get(i), data);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return childrens;  
    }  
  
    public void close() {  
        if (zk != null) {  
            try {  
                zk.close();  
                System.out.println("zk 释放连接");  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    public Stat exists(String path) {  
        Stat stat = null;  
        try {  
            stat = zk.exists(CHILDREN_PATH, false);  
        } catch (KeeperException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return stat;  
    }  
  
    // 用于测试zk  
    public static void main(String[] args) throws Exception {  
  
        // 1.创建zk客户端  
    	ZookeeperNoWatcher zookeeperBase = new ZookeeperNoWatcher();  
        // 连接服务端  
        zookeeperBase.connection();  
  
        // 2.创建父节点信息  
        zookeeperBase.createNode(PARENT_PATH, "testParent",  
                CreateMode.PERSISTENT);  
        // 3.创建子节点信息  
        zookeeperBase.createNode(CHILDREN_PATH, "testChilren",  
                CreateMode.PERSISTENT);  
  
        // 4.获取父节点的数据信息  
        String parentData = zookeeperBase.getData(PARENT_PATH);  
        System.out.println(parentData);  
        // 5.获取子节点的数据信息  
        String data = zookeeperBase.getData(CHILDREN_PATH);  
        System.out.println(data);  
        // 6.设置父节点的数据信息  
        zookeeperBase.setData(PARENT_PATH, "haha");  
        // 7.设置子节点的数据信息  
        zookeeperBase.setData(CHILDREN_PATH, "heihei");  
        // 8.同步删除节点信息  
        zookeeperBase.deleteNode(CHILDREN_PATH);  
        // 9.异步的删除节点的信息（带有回调的方式）  
        zookeeperBase.deleteNodeCallback(PARENT_PATH, "aaa");  
  
        // 关闭连接  
        zookeeperBase.close();  
  
    }  

}
