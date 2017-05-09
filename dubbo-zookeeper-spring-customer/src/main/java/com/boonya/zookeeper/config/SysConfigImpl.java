package com.boonya.zookeeper.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 系统配置实现类
 * 
 * @package com.boonya.zookeeper.config.SysConfigImpl
 * @date   2017年5月9日  下午1:42:26
 * @author pengjunlin
 * @comment   
 * @update
 */
public class SysConfigImpl implements SysConfig {

	private final static Logger logger = LoggerFactory
			.getLogger(SysConfigImpl.class);
	/**
	 * 存储配置内容
	 */
	private volatile Map<String, String> yardProperties = new HashMap<String, String>();

	private ZkClient client;

	private SysConfigWatcher sysConfigWatcher;

	public SysConfigImpl(String serverstring) {
		this.client = new ZkClient(serverstring);
		sysConfigWatcher = new SysConfigWatcher(client, this);
		this.init();
	}

	/**
	 * 初始化加载配置到内存
	 */
	public void init() {
		if (!client.exists(root)) {
			client.createPersistent(root);
		}
		if (yardProperties == null) {
			logger.info("start to init yardProperties");
			yardProperties = this.getAll();
			logger.info("init yardProperties over");
		}
	}

	private String contactKey(String key) {
		return root.concat("/").concat(key);
	}

	public void add(String key, String value) {
		String contactKey = this.contactKey(key);
		this.client.createPersistent(contactKey, value);
		sysConfigWatcher.watcher(contactKey);
	}

	public void update(String key, String value) {
		String contactKey = this.contactKey(key);
		this.client.writeData(contactKey, value);
		sysConfigWatcher.watcher(contactKey);
	}

	public void delete(String key) {
		String contactKey = this.contactKey(key);
		this.client.delete(contactKey);
	}

	public String get(String key) {
		if (this.yardProperties.get(key) == null) {
			String contactKey = this.contactKey(key);
			if (!this.client.exists(contactKey)) {
				return null;
			}
			return this.client.readData(contactKey);
		}
		return yardProperties.get(key);
	}

	public Map<String, String> getAll() {
		if (yardProperties != null) {
			return yardProperties;
		}
		List<String> yardList = this.client.getChildren(root);
		Map<String, String> currentYardProperties = new HashMap<String, String>();
		for (String yard : yardList) {
			String value = this.client.readData(yard);
			String key = yard.substring(yard.indexOf("/") + 1);
			currentYardProperties.put(key, value);
		}
		return yardProperties;
	}

	public void reload() {
		List<String> yardList = this.client.getChildren(root);
		Map<String, String> currentYardProperties = new HashMap<String, String>();
		for (String yard : yardList) {
			String value = this.client.readData(this.contactKey(yard));
			currentYardProperties.put(yard, value);
		}
		yardProperties = currentYardProperties;
	}

}
