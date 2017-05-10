package com.boonya.zookeeper;

import java.io.IOException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
/**
 * 原始同步初始化Zookeeper客户端连接和监听
 * 
 * @package com.boonya.zookeeper.SyncPrimitive
 * @date   2017年5月10日  上午9:26:09
 * @author pengjunlin
 * @comment   
 * @update
 */
public class SyncPrimitive implements Watcher {

    static ZooKeeper zk = null;
    
    static Integer mutex;

    String root;

    /**
     * 初始化Zookeeper连接
     * @param address
     */
    SyncPrimitive(String address) {
        if(zk == null){
            try {
                System.out.println("Starting ZK:");
                zk = new ZooKeeper(address, 3000, this);
                mutex = new Integer(-1);
                System.out.println("Finished starting ZK: " + zk);
            } catch (IOException e) {
                System.out.println(e.toString());
                zk = null;
            }
        }
        //else mutex = new Integer(-1);
    }

    /**
     * 监听观察者同步处理
     */
    synchronized public void process(WatchedEvent event) {
        synchronized (mutex) {
            System.out.println("Process: " + event.getType());
            mutex.notify();
        }
    }
}
