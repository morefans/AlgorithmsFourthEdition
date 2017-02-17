package chapter_3.session_1;

public abstract class ST<Key, Value> {
	public abstract int size();
	public abstract boolean isEmpty();
	public abstract void put(Key key, Value value);
	public abstract Value get(Key key);
	public abstract void delete(Key key);
	public abstract boolean contains(Key key);
	public abstract Iterable<Key> keys();
}
