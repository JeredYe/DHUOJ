/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;


import java.util.Date;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class BaseCache extends GeneralCacheAdministrator {
	// ����ʱ��(��λΪ��);
	private int refreshPeriod;
	// �ؼ���ǰ׺�ַ�;
	private String keyPrefix;
	private static final long serialVersionUID = -4397192926052141162L;

	public BaseCache(String keyPrefix, int refreshPeriod) {
		super();
		this.keyPrefix = keyPrefix;
		this.refreshPeriod = refreshPeriod;
	}

	public BaseCache() {
	};

	// ��ӱ�����Ķ���;
	public void put(String key, Object value) {
		this.putInCache(this.keyPrefix + "_" + key, value);
	}

	// ɾ��������Ķ���;
	public void remove(String key) {
		this.flushEntry(this.keyPrefix + "_" + key);
	}

	// ɾ�����б�����Ķ���;
	public void removeAll(Date date) {
		this.flushAll(date);
	}

	public void removeAll() {
		this.flushAll();
	}

	// ��ȡ������Ķ���;
	public Object get(String key) throws Exception {
		try {
			return this.getFromCache(this.keyPrefix + "_" + key,
					this.refreshPeriod);
		} catch (NeedsRefreshException e) {
			this.cancelUpdate(this.keyPrefix + "_" + key);
			throw e;
		}
	}

}
