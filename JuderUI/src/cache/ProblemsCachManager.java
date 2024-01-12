package cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProblemsCachManager {
	private boolean update = false;
	private BaseCache cache;
	private static ProblemsCachManager instance;
	private static Object lock = new Object();

	private ProblemsCachManager() {
		// ������������ļ�������ʼBaseCache����;
		Properties prop = new Properties();
		InputStream in = ProblemsCachManager.class
				.getResourceAsStream("cache.properties");
		try {
			prop.load(in);
			String validTime = prop.getProperty("problemsValidTime").trim();
			cache = new BaseCache("Problems", Integer.parseInt(validTime));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ProblemsCachManager getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new ProblemsCachManager();
				}
			}
		}
		return instance;
	}

	public void putObject(String key, Object value) { // key��classId_examId
		cache.put(key, value);
	}

	public void removeObject(String key) {
		cache.remove(key);
	}

	public void removeAllObject() {
		cache.removeAll();
	}

	public Object getObject(String key) {
		try {
			// ��Cache�л��
			return cache.get(key);
		} catch (Exception e) {
			// Cache��û�����DB���ȡ
			// ���ݿ��ж�ȡ����
			// �ѻ�ȡ�Ķ����ٴδ���Cache��
			this.putObject(key, null);
			update = true;
			return null;
		} finally {
			if (!update) {
				// ���Cache�е����ݸ��³����쳣������ֹ�÷���
				cache.cancelUpdate(key); // ȡ����id�ĸ���
			}
		}
	}

}
