import java.util.*;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many or too few entries.
 *
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> implements Map<K, V> {

	// average number of entries per map before we grow the map
	private static final double ALPHA = 1.0;
	// average number of entries per map before we shrink the map
	private static final double BETA = .25;

	// resizing factor: (new size) = (old size) * (resize factor)
	private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

	private static final int MIN_MAPS = 16;

	// list of maps
	protected List<MyLinearMap<K,V>> maps;
	private int size = 0;

	public MyHashMap() {
		makeMaps(MIN_MAPS);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Initialize maps
	 */
	protected void makeMaps(int size) {
		maps = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			maps.add(new MyLinearMap<K, V>());
		}
	}

	protected MyLinearMap<K, V> chooseMap(Object key) {
		if (key != null) {
			int hashIndex = key.hashCode() % maps.size();
			return maps.get(hashIndex);
		} else {
			int hashIndex = 0;
			return maps.get(hashIndex);
		}
	}

	@Override
	public boolean containsKey(Object key) {
		for (int i = 0; i < maps.size(); i++) {
			MyLinearMap linMap = maps.get(i);
			if (linMap.containsKey(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		for (int i = 0; i < maps.size(); i++) {
			MyLinearMap linMap = maps.get(i);
			if (linMap.containsValue(value)) {
				return true;
			}
		}
		return false;
	}

	protected void rehash(double growthFactor) {
		double temp = maps.size() * growthFactor;
		int newCapacity = (int) temp;

		List<MyLinearMap<K,V>> newMaps = new ArrayList<>();
		for (int j = 0; j < newCapacity; j++) {
			newMaps.add(new MyLinearMap<K, V>());
		}

		Set keys = keySet();
		for (Object key : keys) {
			Object value = get(key);
			int i;
			try {
				i = key.hashCode() % newCapacity;
			} catch (NullPointerException e) {
				i = 0;
			}
			MyLinearMap newMap = newMaps.get(i);
			newMap.put(key, value);
		}
		maps = newMaps;
	}

	@Override
	public V get(Object key) {
		MyLinearMap<K,V> m = chooseMap(key);
		return m.get(key);
	}

	@Override
	public V put(K key, V value) {
		MyLinearMap linMap = chooseMap(key);
		if (!linMap.containsKey(key)) {
			size++;
		}
		V returnV = (V) linMap.put(key, value);
		if (size/maps.size() >= ALPHA) {
			rehash(GROWTH_FACTOR);
		} else if ((size < BETA * maps.size()) && (maps.size()*SHRINK_FACTOR >= MIN_MAPS)) {
			rehash(SHRINK_FACTOR);
		}
		return returnV;
	}

	@Override
	public V remove(Object key) {
		MyLinearMap linMap = chooseMap(key);
		size--;
		V returnV = (V) linMap.remove(key);
		if ((size < BETA * maps.size()) && maps.size()*SHRINK_FACTOR >= MIN_MAPS) {
			rehash(SHRINK_FACTOR);
		}
		return returnV;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	public void clear() {
		for (int i=0; i<maps.size(); i++) {
			maps.get(i).clear();
		}
		size = 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		for (MyLinearMap<K,V> map : maps) {
			set.addAll(map.keySet());
		}
		return set;
	}

	@Override
	public Collection<V> values() {
		Collection<V> ll = new LinkedList<>();
		for (MyLinearMap<K,V> map : maps) {
			ll.addAll(map.values());
		}
		return ll;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K,V>> set = new HashSet<>();
		for (MyLinearMap<K,V> map : maps) {
			set.addAll(map.getEntries());
		}
		return set;
	}
}
