package tp2.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tp2.StrLen;

public class TestFrequent {

	@Test
	public void testCore() {
		List<String> li = Arrays.asList("aaa", "b", "ccc", "dd", "eee", "ffff");
		int res = StrLen.mostFrequent(li);
		assertEquals(3, res);
	}
	
	@Test
	public void testSameLength() {
		List<String> li = Arrays.asList("123456", "654321");
		int res = StrLen.mostFrequent(li);
		assertEquals(6, res);
	}
	
	@Test
	public void testTieFirstWin() {
		List<String> li = Arrays.asList("aaa", "bb", "ccc", "dd", "eee", "ff");
		int res = StrLen.mostFrequent(li);
		assertEquals(3, res);
	}
	
	@Test
	public void testTieSecondWin() {
		List<String> li = Arrays.asList("a", "bb", "c", "dd", "e", "ff");
		int res = StrLen.mostFrequent(li);
		assertEquals(2, res);
	}
	
	@Test
	public void testSingle() {
		List<String> li = Arrays.asList("123456789");
		int res = StrLen.mostFrequent(li);
		assertEquals(9, res);
	}

}
