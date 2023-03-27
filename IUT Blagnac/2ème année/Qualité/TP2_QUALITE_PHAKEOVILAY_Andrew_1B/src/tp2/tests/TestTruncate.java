package tp2.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tp2.StrLen;

public class TestTruncate {

	@Test
	public void testCore() {
		List<String> li = Arrays.asList("a", "bb", "ccc", "dddd", "eeeee");
		StrLen.truncate(li, 3);
		List<String> expected = Arrays.asList("a", "bb", "ccc", "ddd", "eee");
		assertEquals(expected, li);
	}

}
