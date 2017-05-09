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
	private volatile Map<String, String> properties = new HashMap<String, String>();

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
		if (properties == null) {
			logger.info("start to init properties");
			properties = this.getAll();
			logger.info("init properties over");
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
		if (this.properties.get(key) == null) {
			String contactKey = this.contactKey(key);
			if (!this.client.exists(contactKey)) {
				return null;
			}
			return this.client.readData(contactKey);
		}
		return properties.get(key);
	}

	public Map<String, String> getAll() {
		if (properties != null) {
			return properties;
		}
		List<String> rootList = this.client.getChildren(root);
		Map<String, String> currentProperties = new HashMap<String, String>();
		for (String node : rootList) {
			String value = this.client.readData(node);
			String key = node.substring(node.indexOf("/") + 1);
			currentProperties.put(key, value);
		}
		return properties;
	}

	public void reload() {
		List<String> rootList = this.client.getChildren(root);
		Map<String, String> currentProperties = new HashMap<String, String>();
		for (String node : rootList) {
			String value = this.client.readData(this.contactKey(node));
			currentProperties.put(node, value);
		}
		properties = currentProperties;
	}

}
