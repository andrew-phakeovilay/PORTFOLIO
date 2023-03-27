package tp2.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tp2.StrLen;

public class TestLongest {

	@Test
	public void testCore() {
		List<String> li = Arrays.asList("aaa", "b", "cccccccc", "dd", "eee", "ffff");
		String res = StrLen.longestString(li);
		assertEquals("cccccccc", res);
	}
	
	@Test
	public void testTwiceSameLength() {
		List<String> li = Arrays.asList("a", "123456", "b", "654321", "c");
		String res = StrLen.longestString(li);
		assertEquals("123456", res);
	}
	
	@Test
	public void testFirst() {
		List<String> li = Arrays.asList("aaaaaaa", "bb", "ccc", "dd", "eee", "ff");
		String res = StrLen.longestString(li);
		assertEquals("aaaaaaa", res);
	}
	
	@Test
	public void testLast() {
		List<String> li = Arrays.asList("a", "bb", "c", "dd", "e", "ffffff");
		String res = StrLen.longestString(li);
		assertEquals("ffffff", res);
	}
	
	@Test
	public void testEmpty() {
		List<String> li = Arrays.asList();
		String res = StrLen.longestString(li);
		assertEquals("", res);
	}

}
