package tp1.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tp1.MapUtils;

public class TestMerge {

	@Test
	public void test_disjoint() {
		Map<String, Integer> d1 = new HashMap<>();
		d1.put("A", 1);
		d1.put("B", 2);

		Map<String, Integer> d2 = new HashMap<>();
		d2.put("C", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("A", 1);
		expected.put("B", 2);
		expected.put("C", 2);
		
		assertEquals(expected, MapUtils.merge(d1, d2, null));
	}
	
	@Test
	public void test_max() {
		Map<String, Integer> d1 = new HashMap<>();
		d1.put("A", 1);
		d1.put("B", 2);

		Map<String, Integer> d2 = new HashMap<>();
		d2.put("A", 3);
		d2.put("C", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("A", 3);
		expected.put("B", 2);
		expected.put("C", 2);
		
		assertEquals(expected, MapUtils.merge(d1, d2, Integer::max));
	}
	
	@Test
	public void test_add() {
		Map<String, Integer> d1 = new HashMap<>();
		d1.put("A", 1);
		d1.put("B", 2);

		Map<String, Integer> d2 = new HashMap<>();
		d2.put("A", 3);
		d2.put("C", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("A", 4);
		expected.put("B", 2);
		expected.put("C", 2);
		
		assertEquals(expected, MapUtils.merge(d1, d2, Integer::sum));
	}
	
	@Test
	public void test_conflict() {
		Map<String, Integer> d1 = new HashMap<>();
		d1.put("A", 1);
		d1.put("B", 2);

		Map<String, Integer> d2 = new HashMap<>();
		d2.put("A", 3);
		d2.put("C", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("A", 4);
		expected.put("B", 2);
		expected.put("C", 2);
		
		try {
			MapUtils.merge(d1, d2, null);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("Expecting IllegalArgumentException...");
	}
	
	@Test
	public void test_no_side_effect() {
		Map<String, Integer> d1 = new HashMap<>();
		d1.put("A", 1);
		d1.put("B", 2);
		Map<String, Integer> oldD1 = new HashMap<>();
		oldD1.put("A", 1);
		oldD1.put("B", 2);
		
		Map<String, Integer> d2 = new HashMap<>();
		d2.put("A", 3);
		d2.put("C", 2);
		Map<String, Integer> oldD2 = new HashMap<>();
		oldD2.put("A", 3);
		oldD2.put("C", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("A", 3);
		expected.put("B", 2);
		expected.put("C", 2);
		
		MapUtils.merge(d1, d2, Integer::sum);
		
		assertEquals(oldD1, d1);
		assertEquals(oldD2, d2);
		
	}


}
