package hw6.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hw6.MarvelParser;
import hw6.MarvelParser.MalformedDataException;

public class MarvelParserTest {
	private final int timeOut = 99;
	private HashSet<String> characters;
	private String filename = "testmarvel.tsv";
	private HashMap<String, List<String>> books;
	@Before
	public void setUp() throws Exception {
      characters = new HashSet<>();
      books = new HashMap<>();
	}

	@Test(timeout = timeOut)
	public void testRuntime() throws MalformedDataException {
		MarvelParser.parseData(filename, characters, books);
	}
	@Test(timeout = timeOut, expected = NullPointerException.class)
	public void testNullinput_character() throws MalformedDataException {
		MarvelParser.parseData(filename, null, books);
	}
	
	@Test(timeout = timeOut, expected = NullPointerException.class)
	public void testNullinput_book() throws MalformedDataException {
		MarvelParser.parseData(filename, characters, null);
	}
	@Test(timeout = timeOut, expected = NullPointerException.class)
	public void testNullinput_filename() throws MalformedDataException {
		MarvelParser.parseData(filename, characters, null);
	}
}
