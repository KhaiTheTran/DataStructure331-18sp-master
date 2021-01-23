package hw7.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import hw7.MarvelParser;
import hw7.MarvelParser.MalformedDataException;

public class MarvelParserTest {
	private final int timeOut = 99;
	private String filename = "testmarvel.tsv";
	private Map<String, HashMap<String, Integer>> books;
	@Before
	public void setUp() throws Exception {
      books = new HashMap<>();
	}

	@Test(timeout = timeOut)
	public void testRuntime() throws MalformedDataException {
		MarvelParser.parseData(filename, books);
	}
	@Test(timeout = timeOut, expected = NullPointerException.class)
	public void testNullinput_character() throws MalformedDataException {
		MarvelParser.parseData(filename, null);
	}
}
