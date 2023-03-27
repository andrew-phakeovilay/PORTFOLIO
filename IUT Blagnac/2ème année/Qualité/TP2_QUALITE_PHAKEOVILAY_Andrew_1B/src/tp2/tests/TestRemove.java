package tp2.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import tp2.StrLen;

public class TestRemove {

	@Test
	public void testCore() {
		List<String> li = Arrays.asList("a", "b", "12345", "c", "d");
		li = new LinkedList<String>(li); // allow to remove elements
		StrLen.removeLong(li, 3);
		List<String> expected = Arrays.asList("a", "b", "c", "d");
		assertEquals(expected, li);
	}

	@Test
	public void testLimit() {
		List<String> li = new LinkedList<String>(Arrays.asList("aaa", "bbb",
				"1234", "ccc", "ddd"));
		StrLen.removeLong(li, 3);
		List<String> expected = Arrays.asList("aaa", "bbb", "ccc", "ddd");
		assertEquals(expected, li);
	}

	@Test
	public void testFirst() {
		List<String> li = new LinkedList<String>(Arrays.asList("12345", "a",
				"b", "c", "d"));
		StrLen.removeLong(li, 3);
		List<String> expected = Arrays.asList("a", "b", "c", "d");
		assertEquals(expected, li);
	}

	@Test
	public void testLast() {
		List<String> li = new LinkedList<String>(Arrays.asList("a", "b", "c",
				"d", "12345"));
		StrLen.removeLong(li, 3);
		List<String> expected = Arrays.asList("a", "b", "c", "d");
		assertEquals(expected, li);
	}

	@Test
	public void testAll() {
		List<String> li = new LinkedList<String>(Arrays.asList("aaa", "bbb",
				"ccc", "ddd"));
		StrLen.removeLong(li, 2);
		List<String> expected = Arrays.asList();
		assertEquals(expected, li);
	}

}
