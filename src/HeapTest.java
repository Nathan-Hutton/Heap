import static org.junit.Assert.*;

import org.junit.Test;

public class HeapTest {

	@Test
	public void testAdd() {
		Heap<Integer> heap = new MaxHeap<Integer>();
		
		assertTrue(heap.isEmpty());
		
		heap.add(27);
		heap.add(6);;
		heap.add(4);;
		heap.add(16);
		heap.add(19);
		heap.add(30);
		heap.add(32);
		heap.add(25);
		
		assertFalse(heap.isEmpty());
		assertEquals(8, heap.getSize());
		
		assertEquals(Integer.valueOf(32),heap.front());
	}
	
	@Test
	public void testExtremeAdd() {
		Heap<Integer> heap = new MaxHeap<Integer>();

		assertTrue(heap.isEmpty());

		// this should trigger ensureCapacity()
		for (int i = 1; i <= 1000; i++) {
			heap.add(i);
		}
		
		assertFalse(heap.isEmpty());
		assertEquals(1000, heap.getSize());
		
		assertEquals(Integer.valueOf(1000),heap.front());

	}
	
	@Test
	public void testRemove() {
		Heap<Integer> heap = new MaxHeap<Integer>();
		
		assertTrue(heap.isEmpty());
		
		heap.add(27);
		heap.add(6);;
		heap.add(4);;
		heap.add(16);
		heap.add(19);
		heap.add(30);
		heap.add(32);
		heap.add(25);
		
		assertFalse(heap.isEmpty());
		assertEquals(8, heap.getSize());
		
		assertEquals(Integer.valueOf(32), heap.remove());
		assertEquals(Integer.valueOf(30), heap.remove());
		assertEquals(Integer.valueOf(27), heap.remove());
		assertEquals(Integer.valueOf(25), heap.remove());
		assertEquals(Integer.valueOf(19), heap.remove());
		assertEquals(Integer.valueOf(16), heap.remove());
		assertEquals(Integer.valueOf(6), heap.remove());
		assertEquals(Integer.valueOf(4), heap.remove());

		assertEquals(0, heap.getSize());
		assertTrue(heap.isEmpty());
	}

}
