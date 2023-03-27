package tp2.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import tp2.StrLen;

public class TestIndexAreIdentical {

	@Test
	public void testCore() {
		List<String> li = Arrays.asList("aaa", "b", "eee", "ffff");
		List<String> lic = Arrays.asList("aaA", "b", "cccccccc", "ffff");
		List<String> actual = StrLen.indexAreIdentical(li, lic);
		List<String> expected = Arrays.asList("F", "T", "F", "T"); 
		assertEquals(expected, actual);;
	}

	@Test
	public void testEmptyLists() {
		List<String> li = new LinkedList<String>(); 
		List<String> lic = new ArrayList<String>(); 
		List<String> actual = StrLen.indexAreIdentical(li, lic);
		List<String> expected = new LinkedList<String>(); 
		assertEquals(expected, actual);;
	}
	
	@Test
	public void testContainingListLonger1() {
		List<String> li = Arrays.asList("aaa", "b", "eee", "ffff");
		List<String> lic = Arrays.asList("aaA", "b", "cccccccc", "ffff", "dd", "eee");
		List<String> actual = StrLen.indexAreIdentical(li, lic);
		List<String> expected = Arrays.asList("F", "T", "F", "T"); 
		assertEquals(expected, actual);;
	}
	
	@Test
	public void testContainingListLonger2() {
		List<String> li = Arrays.asList();
		List<String> lic = Arrays.asList("aaA", "b", "cccccccc", "ffff", "dd", "eee");
		List<String> actual = StrLen.indexAreIdentical(li, lic);
		List<String> expected = new LinkedList<String>(); 
		assertEquals(expected, actual);;
	}

	@Test
	public void testSearchedListLonger() {
		List<String> li = Arrays.asList("aaa", "b", "eee", "ffff", "g", "h", "i", "j", "k");
		List<String> lic = Arrays.asList("aaA", "b", "cccccccc", "ffff");
		List<String> actual = StrLen.indexAreIdentical(li, lic);
		List<String> expected = Arrays.asList("F", "T", "F", "T", "F", "F", "F", "F", "F"); 
		assertEquals(expected, actual);;
	}
}
