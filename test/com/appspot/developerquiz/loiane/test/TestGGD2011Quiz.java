package com.appspot.developerquiz.loiane.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import junitx.framework.ListAssert;

import org.junit.Test;

import com.appspot.developerquiz.loiane.GGD2011Quiz;

/**
 * Test Case
 *
 * @author Loiane Groner
 * http://loiane.com
 * http://loianegroner.com
 */
public class TestGGD2011Quiz {
	
	private GGD2011Quiz ggd2001Quiz = new GGD2011Quiz();
	
	private final String fileName = "files/textA.txt";

	@Test
    public void testCountPrepositions() throws IOException{
 
        assertEquals(80, ggd2001Quiz.countPrepositions(fileName));
    }
	
	@Test
    public void testCountVerbs() throws IOException{
 
        assertEquals(29, ggd2001Quiz.countVerbs(fileName));
    }
	
	@Test
    public void testCountVerbsFirstPerson() throws IOException{
 
        assertEquals(25, ggd2001Quiz.countVerbsFirstPerson(fileName));
    }
	
	@Test
    public void testCountNumbers() throws IOException{
 
        assertEquals(64, ggd2001Quiz.countNumbers(fileName));
    }
	
	@Test
    public void testCreateVocabulary() throws IOException, ParseException{
		
		List<String> l1 = Arrays.asList(ggd2001Quiz.readFile("files/vocabularyA.txt"));
		List<String> l2 = ggd2001Quiz.createVocabulary(fileName);
		
		ListAssert.assertEquals(l1, l2);
    }
}
