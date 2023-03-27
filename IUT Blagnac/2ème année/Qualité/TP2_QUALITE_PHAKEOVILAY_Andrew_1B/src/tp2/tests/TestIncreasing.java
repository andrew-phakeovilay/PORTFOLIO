package tp2.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import tp2.StrLen;

public class TestIncreasing {

	@Test
	public void testCore() {
		List<String> input = Arrays.asList("AA", "B", "CCC", "DDDDD", "EEEEEE", "FFFF");
		List<String> res = StrLen.increasingLength(input);
		List<String> expected = Arrays.asList("B", "AA", "CCC", "FFFF", "DDDDD", "EEEEEE");
		assertEquals(expected, res);
	}

	/**
	 * Tests that the original list was NOT modified.
	 */
	@Test
	public void testInputUnchanged() {
		List<String> reference = Arrays.asList("z", "ccc", "aa");
		List<String> input = new LinkedList<String>(reference); // working on a copy
		@SuppressWarnings("unused")
		List<String> res = StrLen.increasingLength(input);
		assertEquals("input list is unchanged", reference, input);
	}

	@Test
	public void testMultiple() {
		List<String> input = Arrays.asList("yz", "z", "xyz", "xyz", "yz", "z");
		List<String> res = StrLen.increasingLength(input);
		List<String> expected = Arrays.asList("z", "z", "yz", "yz", "xyz", "xyz");
		assertEquals(expected, res);
	}

	@Test
	public void testStable() {
		List<String> input = Arrays.asList("aaa", "ccc", "bbb");
		List<String> res = StrLen.increasingLength(input);
		List<String> expected = Arrays.asList("aaa", "ccc", "bbb");
		assertEquals(expected, res);
	}
}
