package com.appspot.developerquiz.loiane;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class GenerateTextBAnswers {

	public static void main(String[] args) {

		GGD2011Quiz ggd2001Quiz = new GGD2011Quiz();
		
		final String fileName = "files/textB.txt";

		try {

			System.out.println(ggd2001Quiz.countPrepositions(fileName));
			System.out.println(ggd2001Quiz.countVerbs(fileName));
			System.out.println(ggd2001Quiz.countVerbsFirstPerson(fileName));
			System.out.println(ggd2001Quiz.countNumbers(fileName));
			generateVocabFile(ggd2001Quiz.createVocabulary(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static void generateVocabFile(List<String> list) throws IOException {

		final String vocabFileName = "files/vocabularyB.txt";
		final String space = " ";

		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
				vocabFileName)));

		for (int i = 0; i < list.size() - 1; i++) {
			writer.write(list.get(i) + space);
		}

		writer.write(list.get(list.size() - 1));
		
		writer.flush();
	}

}
