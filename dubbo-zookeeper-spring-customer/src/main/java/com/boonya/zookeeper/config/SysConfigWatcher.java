package com.boonya.zookeeper.config;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 配置的Watcher监听
 * 
 * @package com.boonya.zookeeper.config.SysConfigWatcher
 * @date   2017年5月9日  下午1:42:45
 * @author pengjunlin
 * @comment   
 * @update
 */
public class SysConfigWatcher {

	private final static Logger logger = LoggerFactory
			.getLogger(SysConfigWatcher.class);

	private ZkClient client;

	private ConfigListener configListener;

	private SysConfig config;

	public SysConfigWatcher(ZkClient client, SysConfig config) {
		this.client = client;
		this.config = config;
		this.initConfigYard();
	}

	private void initConfigYard() {
		configListener = new ConfigListener();
	}

	public void watcher(String key) {
		client.subscribeDataChanges(key, configListener);
		client.subscribeChildChanges(key, configListener);
	}

	/**
	 * 配置监听器
	 * 
	 * @author flyking
	 * 
	 */
	private class ConfigListener implements IZkDataListener,
			IZkChildListener {
		public void handleDataChange(String dataPath, Object data)
				throws Exception {
			logger.info("data {} change,start reload configProperties",
					dataPath);
			config.reload();
		}

		public void handleDataDeleted(String dataPath) throws Exception {
			logger.info("data {} delete,start reload configProperties",
					dataPath);
			config.reload();
		}

		public void handleChildChange(String parentPath,
				List<String> currentChilds) throws Exception {
			logger.info("data {} ChildChange,start reload configProperties",
					parentPath);
			config.reload();
		}

	}

}
