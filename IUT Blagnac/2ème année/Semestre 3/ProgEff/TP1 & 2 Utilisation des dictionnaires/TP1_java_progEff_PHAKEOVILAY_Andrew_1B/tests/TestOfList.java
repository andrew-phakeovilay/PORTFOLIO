package tp1.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import tp1.MapUtils;

public class TestOfList {

	@Test
	public void testCore() {
		List<String> input = Arrays.asList("lol:mdr", "bye:ciao");
		Map<String, String> dict = MapUtils.ofPairList(input);
		Map<String, String> expected = new HashMap<>();
		expected.put("lol", "mdr");
		expected.put("bye", "ciao");
		assertEquals(expected, dict);
	}
	
	@Test
	public void testSameValues() {
		List<String> input = Arrays.asList("bonjour:hello", "salut:hello", "coucou:hello");
		Map<String, String> dict = MapUtils.ofPairList(input);
		Map<String, String> expected = new HashMap<>();
		expected.put("bonjour", "hello");
		expected.put("salut", "hello");
		expected.put("coucou", "hello");
		assertEquals(expected, dict);
	}

}
