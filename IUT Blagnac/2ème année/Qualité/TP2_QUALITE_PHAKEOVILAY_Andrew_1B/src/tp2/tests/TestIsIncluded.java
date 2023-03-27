package tp2.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tp2.StrLen;

public class TestIsIncluded {

	@Test
	public void testCore() {
		List<String> li = Arrays.asList("aaa", "b", "eee", "ffff");
		List<String> lic = Arrays.asList("aaa", "b", "cccccccc", "dd", "eee", "ffff");
		boolean res = StrLen.isIncluded(li, lic);
		assertTrue(res);	
	}
	
	@Test
	public void testSame() {
		List<String> li = Arrays.asList("aaa", "b", "cccccccc", "dd", "eee", "ffff");
		List<String> lic = Arrays.asList("aaa", "b", "cccccccc", "dd", "eee", "ffff");
		boolean res = StrLen.isIncluded(li, lic);
		assertTrue(res);
	}
	
	@Test
	public void testEmpty() {
		List<String> li = Arrays.asList();
		List<String> lic = Arrays.asList();
		boolean res = StrLen.isIncluded(li, lic);
		assertTrue(res);
	}
	
	@Test
	public void testNotFirst() {
		List<String> li = Arrays.asList("ZZZ", "aaa", "b", "eee", "ffff");
		List<String> lic = Arrays.asList("aaa", "b", "cccccccc", "dd", "eee", "ffff");
		boolean res = StrLen.isIncluded(li, lic);
		assertFalse(res);
	}
	
	@Test
	public void testNotLast() {
		List<String> li = Arrays.asList("aaa", "b", "eee", "ffff", "ZZZ");
		List<String> lic = Arrays.asList("aaa", "b", "cccccccc", "dd", "eee", "ffff");
		boolean res = StrLen.isIncluded(li, lic);
		assertFalse(res);
	}
	
	@Test
	public void testNotMiddle() {
		List<String> li = Arrays.asList("aaa", "b", "ZZZ", "eee", "ffff");
		List<String> lic = Arrays.asList("aaa", "b", "cccccccc", "dd", "eee", "ffff");
		boolean res = StrLen.isIncluded(li, lic);
		assertFalse(res);
	}
}
