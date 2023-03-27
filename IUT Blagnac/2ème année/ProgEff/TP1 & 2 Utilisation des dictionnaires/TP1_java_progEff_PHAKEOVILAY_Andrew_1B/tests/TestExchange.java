package tp1.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tp1.MapUtils;

public class TestExchange {

	@Test
	public void testStringInteger() {
		Map<String, Integer> dict = new HashMap<>();
		dict.put("un", 1);
		dict.put("deux", 2);
		dict.put("trois", 3);
		MapUtils.exchange(dict, "un", "deux");
		Map<String, Integer> expected = new HashMap<>();
		expected.put("un", 2);
		expected.put("deux", 1);
		expected.put("trois", 3);
		assertEquals(expected, dict);
	}

	@Test
	public void testIntegerString() {
		Map<Integer, String> dict = new HashMap<>();
		dict.put(1, "un");
		dict.put(2, "deux");
		dict.put(3, "trois");
		MapUtils.exchange(dict, 2, 3);
		Map<Integer, String> expected = new HashMap<>();
		expected.put(1, "un");
		expected.put(3, "deux");
		expected.put(2, "trois");
		assertEquals(expected, dict);
	}
}
