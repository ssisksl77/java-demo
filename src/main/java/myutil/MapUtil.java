package myutil;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yuhnam
 *	파라미터 보낼 때 map 만드는 거 빡쳐서 만듬.
 *
 */
public class MapUtil {
	public static <K,V> Map<K, V> of(K k1, V v1) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		return map;
	}
	
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		return map;
	}
	
	
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		return map;
	}
	
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		map.put(k4, v4);
		return map;
	}
	
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		map.put(k4, v4);
		map.put(k5, v5);
		return map;
	}
	
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5
									, K k6, V v6) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		map.put(k4, v4);
		map.put(k5, v5);
		map.put(k6, v6);
		return map;
	}
	
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5
									, K k6, V v6, K k7, V v7) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		map.put(k4, v4);
		map.put(k5, v5);
		map.put(k6, v6);
		map.put(k7, v7);
		return map;
	}
	
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5
									, K k6, V v6, K k7, V v7, K k8, V v8) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		map.put(k4, v4);
		map.put(k5, v5);
		map.put(k6, v6);
		map.put(k7, v7);
		map.put(k8, v8);
		return map;
	}
	
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5
									, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		map.put(k4, v4);
		map.put(k5, v5);
		map.put(k6, v6);
		map.put(k7, v7);
		map.put(k8, v8);
		map.put(k9, v9);
		return map;
	}
	public static <K,V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5
									, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
		Map<K, V> map = new HashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		map.put(k4, v4);
		map.put(k5, v5);
		map.put(k6, v6);
		map.put(k7, v7);
		map.put(k8, v8);
		map.put(k9, v9);
		map.put(k10, v10);
		return map;
	}
	
	public static class Builder <K, V> {
		private final Map<K,V> map;
		
		public Builder() {
			this.map = new HashMap<>();
		}
		
		public Builder<K,V> put(K key, V value) {
			map.put(key, value);
			return this;
		}
		
		public Map<K,V> build() {
			return map;
		}
	}
}
