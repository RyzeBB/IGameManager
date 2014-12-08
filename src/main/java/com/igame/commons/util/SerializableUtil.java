package com.igame.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SerializableUtil
 * @Package com.heigam.common.util
 * @Author Allen allen.ime@gmail.com
 * @Date 2014年3月4日 下午7:33:15
 * @Description: 序列化工具类
 * @Version V1.0
 */
public class SerializableUtil {
	private static final Logger logger = LoggerFactory.getLogger(SerializableUtil.class);

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T BytesToObject(byte[] bytes) {
		T obj = null;
		try {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			obj = (T) (objectInputStream.readObject());
			byteArrayInputStream.close();
			objectInputStream.close();
		} catch (Exception e) {
			logger.error(" == SerializableUtil ByteToObject error " + e);
		}
		return obj;
	}

	/**
	 * 序列化对象
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] ObjectToByte(Object obj) {
		byte[] bytes = null;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bytes = baos.toByteArray();
			baos.close();
			oos.close();
		} catch (Exception e) {
			logger.error(" == SerializableUtil ObjectToByte error " + e);
		}
		return (bytes);
	}
}
