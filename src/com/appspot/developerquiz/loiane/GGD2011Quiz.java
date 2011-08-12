package com.appspot.developerquiz.loiane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GGD2011Quiz {

	private final char[] FOO = { 't', 's', 'w', 'l', 'h' };
	private final String M = "m";
	private final String SPACE = " ";
	private final String ALPHABET = "zbcqlwhvrjmtnksgxfdp";
	private final String RULE = "<z<b<c<q<l<w<h<v<r<j<m<t<n<k<s<g<x<f<d<p";


	public String[] readFile(String fileName) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(
				new File(fileName)));

		String text = br.readLine();

		return text.split(SPACE);
	}

	/**
	 * Os linguistas descobriram que as preposições em Googlon são as palavras
	 * de 3 letras que terminam numa letra tipo bar, mas onde não ocorre a letra
	 * m. Portanto, é fácil ver que existem 80 preposições no Texto A. E no
	 * Texto B, quantas preposições existem?
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public int countPrepositions(String fileName) throws IOException {

		String[] words = readFile(fileName);

		int total = 0;

		for (String word : words) {

			if (isPreposition(word)) {
				total++;
			}
		}

		return total;
	}

	private boolean isPreposition(String word) {

		char lastChar;
		boolean isPreposition = false;

		if (word.length() == 3) {

			if (word.contains(M))
				return false;

			lastChar = word.charAt(word.length() - 1);
			isPreposition = true;

			for (char c : FOO) {

				if (c == lastChar) {
					isPreposition = false;
				}
			}

		}

		return isPreposition;
	}

	/**
	 * Um outro fato interessante descoberto pelos linguistas é que, no Googlon,
	 * os verbos sempre são palavras de 8 ou mais letras que terminam numa letra
	 * tipo foo. Assim, lendo o Texto A, é possível identificar 29 verbos no
	 * texto, dos quais 25 estão em primeira pessoa.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public int countVerbs(String fileName) throws IOException {

		String[] words = readFile(fileName);

		int total = 0;

		for (String word : words) {

			if (isVerb(word)) {
				total++;
			}
		}

		return total;
	}

	private boolean isVerb(String word) {

		char lastChar;
		boolean isVerb = false;

		if (word.length() >= 8) {

			lastChar = word.charAt(word.length() - 1);
			isVerb = false;

			for (char c : FOO) {

				if (c == lastChar) {
					isVerb = true;
				}
			}

		}

		return isVerb;
	}

	/**
	 * Um outro fato interessante descoberto pelos linguistas é que, no Googlon,
	 * os verbos sempre são palavras de 8 ou mais letras que terminam numa letra
	 * tipo foo. Além disso, se um verbo começa com uma letra tipo bar, o verbo
	 * está em primeira pessoa. Assim, lendo o Texto A, é possível identificar
	 * 29 verbos no texto, dos quais 25 estão em primeira pessoa.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public int countVerbsFirstPerson(String fileName) throws IOException {

		String[] words = readFile(fileName);

		int total = 0;

		for (String word : words) {

			if (isVerbFirstPerson(word)) {
				total++;
			}
		}

		return total;
	}

	private boolean isVerbFirstPerson(String word) {

		char firstChar;
		boolean isVerbFirstPerson = false;

		if (isVerb(word)) {

			isVerbFirstPerson = true;
			firstChar = word.charAt(0);

			for (char c : FOO) {

				if (c == firstChar) {
					isVerbFirstPerson = false;
				}
			}
		}

		return isVerbFirstPerson;
	}

	/**
	 * Um professor universitário utilizará os textos A e B para ensinar o
	 * Googlon aos alunos. Para ajudar os alunos a compreender o texto, esse
	 * professor precisa criar uma lista de vocabulário para cada texto, isto é,
	 * uma lista ordenada (e sem repetições) das palavras que aparecem em cada
	 * um dos textos. Essas listas devem estar ordenadas e não podem conter
	 * repetições de palavras. No Googlon, assim como no nosso alfabeto, as
	 * palavras são ordenadas lexicograficamente, mas o problema é que no
	 * Googlon, a ordem das letras no alfabeto é diferente da nossa. O alfabeto
	 * Googlon, em ordem, é: zbcqlwhvrjmtnksgxfdp. Assim, ao fazer essas listas,
	 * o professor deve respeitar a ordem alfabética Googlon.
	 * 
	 * http://blogs.oracle.com/CoreJavaTechTips/entry/sorting_strings
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<String> createVocabulary(String fileName) throws IOException,
			ParseException {

		String[] words = readFile(fileName);

		RuleBasedCollator collator = new RuleBasedCollator(RULE);

		Set<String> distinct = new HashSet<String>(Arrays.asList(words));

		List<String> list = new ArrayList<String>();
		list.addAll(distinct);

		Collections.sort(list, collator);

		return list;
	}

	/**
	 * Mas como os Googlons escrevem números? Bem, no Googlon, as palavras
	 * também são números dados em base 20, onde cada letra é um dígito, e os
	 * dígitos são ordenados do menos significativo para o mais significativo (o
	 * inverso do nosso sistema). Ou seja, a primeira posição é a unidade, a
	 * segunda posição vale 20, a terceira vale 400, e assim por diante. Os
	 * valores das letras são dados pela ordem em que elas aparecem no alfabeto
	 * Googlon (que é diferente da nossa ordem, como vimos acima). Ou seja, a
	 * primeira letra do alfabeto Googlon representa o dígito 0, a segunda
	 * representa o dígito 1, e assim por diante.
	 * 
	 * Por exemplo, a palavra ppj tem o valor numérico de 3999.
	 * 
	 * OBS: os números nesse problema podem ser grandes, então se você está
	 * usando um tipo de dados de tamanho limitado, tenha cuidado com possíveis
	 * overflows.
	 * 
	 * Os Googlons consideram um número bonito se ele satizfaz essas duas
	 * propriedades: é maior ou igual a 823262 é divisível por 5
	 * 
	 * Ao consideramos o Texto A como uma lista de números (isto é,
	 * interpretando cada palavra como um número usando a convenção explicada
	 * acima), notamos que existem 64 números bonitos distintos. E no Texto B,
	 * quantos números bonitos distintos existem?
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public int countNumbers(String fileName) throws IOException {

		String[] words = readFile(fileName);

		final BigInteger val = new BigInteger("823262");
		final BigInteger five = new BigInteger("5");
		final BigInteger zero = new BigInteger("0");
		BigInteger num;
		Set<BigInteger> distincts = new HashSet<BigInteger>();

		for (String word : words) {

			num = getNumberBase20(word);

			if (num.compareTo(val) >= 0 && num.mod(five).compareTo(zero) == 0) {
				distincts.add(num);
			}
		}
		
		return distincts.size();
	}

	private BigInteger getNumberBase20(String word) {

		int base;
		char c;
		BigInteger result = new BigInteger("0");
		BigInteger aux = new BigInteger("20");
		BigInteger num = null;

		for (int i = 0; i < word.length(); i++) {

			c = word.charAt(i);
			base = ALPHABET.indexOf(c);
			num = new BigInteger(String.valueOf(base));
			result = result.add(aux.pow(i).multiply(num));
		}

		return result;
	}

}
