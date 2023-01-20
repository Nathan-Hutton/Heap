/**
 * MaxHeap.java
 * 
 * Implementation of the HeapInterface as a maximum heap (max heap)
 * 
 * @author Nathan Hutton
 *
 * @param <T>
 */
public class MaxHeap <T extends Comparable<? super T>> implements Heap<T> {
	private T[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	private int index = 1;

	public MaxHeap() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public MaxHeap(int capacity) {
		// we create an array of size +1 to accomodate
		// for starting at position 1
		elements = (T[]) new Comparable[capacity + 1];
	}

	public void add(T item) {
		int itemIndex = index;
		ensureCapacity();
		elements[index] = item;
		index++;
		
		if(itemIndex == 1)
		{
			return;
		}
		
		while(getParent(itemIndex).compareTo(item) < 0)
		{
			elements[itemIndex] = elements[itemIndex/2];
			elements[itemIndex/2] = item;
			itemIndex /= 2;
			if(getParent(itemIndex) == null)
			{
				break;
			}
		}
	}
	private T getParent(int item)
	{
		return elements[item/2];
	}
	private T getLeftChild(int item) 
	{
		if(index - 1 < item * 2)
		{
			return null;
		}
		return elements[item * 2];
	}
	private T getRightChild(int item)
	{
		if(index - 1 < item * 2 + 1)
		{
			return null;
		}
		return elements[item * 2 + 1];
	}

	public T remove() {
		int itemIndex = 1;
		if(isEmpty())
		{
			return null;
		}
		T item = elements[1];
		elements[1] = elements[index - 1];
		elements[index - 1] = null;
		index--;
		int largerChild;
		//place the last element where the root used to be
		//decremenet index by 1
		//see how many children the top has
		//see which child is bigger and swap it with the node you're comparing
		while(itemIndex < index)
		{
			T duplicate = elements[itemIndex];
			if(getLeftChild(itemIndex) != null && getRightChild(itemIndex) != null)
			{
				largerChild = 0;
				if(getLeftChild(itemIndex).compareTo(getRightChild(itemIndex)) > 0)
				{
					largerChild = itemIndex * 2;
				}
				else
				{
					largerChild = itemIndex * 2 + 1;
				}
				
				elements[itemIndex] = elements[largerChild];
				elements[largerChild] = duplicate;
			}
			else if(getLeftChild(itemIndex) != null)
			{
				if(elements[itemIndex].compareTo(getLeftChild(itemIndex)) < 0)
				{
					elements[itemIndex] = elements[itemIndex * 2];
					elements[itemIndex * 2] = duplicate;
				}
			}
			else if(getRightChild(itemIndex) != null)
			{
				if(elements[itemIndex].compareTo(getRightChild(itemIndex)) < 0)
				{
					elements[itemIndex] = elements[itemIndex * 2 + 1];
					elements[itemIndex * 2 + 1] = duplicate;
				}
			}
			itemIndex++;
		}
		return item;
	}

	public T front() {
		return elements[1];
	}

	public boolean isEmpty() {
		if(index == 1)
		{
			return true;
		}
		return false;
	}

	public int getSize() {
		return index - 1;
	}

	private void ensureCapacity() {
		// The following is a starting point
		if(index > elements.length - 1)
		{
		
			elements = java.util.Arrays.copyOf(elements, 2 * elements.length);
		}
	}
}
