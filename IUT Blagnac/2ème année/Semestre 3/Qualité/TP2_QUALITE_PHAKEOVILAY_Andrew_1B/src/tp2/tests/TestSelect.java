package tp2.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import tp2.StrLen;

public class TestSelect {

	@Test
	public void testCore() {
		List<String> input = Arrays.asList("1", "12", "123", "1234", "12345",
				"123456");
		List<String> res = StrLen.selectLong(input, 5);
		List<String> expected = Arrays.asList("12345", "123456");
		assertEquals(expected, res);
	}

	@Test
	public void testDisorder() {
		List<String> input = Arrays.asList("1", "123456", "1234", "123",
				"12345", "12");
		List<String> res = StrLen.selectLong(input, 5);
		List<String> expected = Arrays.asList("123456", "12345");
		assertEquals(expected, res);
	}

	@Test
	public void testInputIsUnchanged() {
		List<String> input = Arrays.asList("1", "12", "123", "1234", "12345",
				"123456");
		input = new LinkedList<String>(input); // allow input to be modified
		List<String> copy = new LinkedList<String>(input);
		@SuppressWarnings("unused")
		List<String> res = StrLen.selectLong(input, 5);
		assertEquals("input list is unchanged", copy, input);
	}

	@Test
	public void testEmptySelection() {
		List<String> input = Arrays.asList("1", "12", "123", "1234", "12345",
				"123456");
		List<String> res = StrLen.selectLong(input, 7);
		List<String> expected = Arrays.asList();
		assertEquals(expected, res);
	}

}
