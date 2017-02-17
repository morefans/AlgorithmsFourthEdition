package chapter_1.session_3;

import java.util.Iterator;
import java.util.Random;

/**
 * Ï°Ìâ1.3.34£¬Ëæ»ú±³°ü
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class RandomBag<Item> implements Iterable<Item>{

	private Item[] array;
	private int size;
	@SuppressWarnings("unchecked")
	public RandomBag(){
		array = (Item[]) new Object[1];
	}

	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
	public void add(Item item){
		if (size == array.length)
			resize(array.length*2);
		array[size] = item;
		size++;
	}
	public void print(){
		for (int i = 0;i < size;i++)
			System.out.print(array[i] + "\t");
		System.out.println();
	}
	public static void test(){
		RandomBag<String> bag = new RandomBag<String>();
		for (int i = 0;i < 10;i++){
			bag.add(String.valueOf(i) + "s");
		}
		for (String s : bag){
			System.out.print(s + "\t");
		}
		System.out.println();
		bag.print();
	}
	public static void main(String[] args){
		RandomBag.test();
	}
	@SuppressWarnings("unchecked")
	private void resize(int capacity){
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
			temp[i] = array[i];
		array = temp;
	}

	@Override
	public Iterator<Item> iterator() {
		return new BagIterator();
	}

	private class BagIterator implements Iterator<Item>{
		private Item[] items;
		private int index = 0;
		
		@SuppressWarnings("unchecked")
		public BagIterator(){
			items = (Item[]) new Object[size];
			for (int i = 0;i < size;i++){
				items[i] = array[i];
			}
			shuffle();
		}
		public void shuffle(){
			int r;
			for (int i = 0;i < size;i++){
				r = new Random(System.currentTimeMillis()).nextInt(size-i);
				Item item = items[r];
				items[r] = items[size-1-i];
				items[size-1-i] = item;
			}
			return ;
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Item next() {
			return items[index++];
		}

	}


}
