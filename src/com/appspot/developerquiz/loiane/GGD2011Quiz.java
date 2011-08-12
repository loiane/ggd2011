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
	 * Os linguistas descobriram que as preposi��es em Googlon s�o as palavras
	 * de 3 letras que terminam numa letra tipo bar, mas onde n�o ocorre a letra
	 * m. Portanto, � f�cil ver que existem 80 preposi��es no Texto A. E no
	 * Texto B, quantas preposi��es existem?
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
	 * Um outro fato interessante descoberto pelos linguistas � que, no Googlon,
	 * os verbos sempre s�o palavras de 8 ou mais letras que terminam numa letra
	 * tipo foo. Assim, lendo o Texto A, � poss�vel identificar 29 verbos no
	 * texto, dos quais 25 est�o em primeira pessoa.
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
	 * Um outro fato interessante descoberto pelos linguistas � que, no Googlon,
	 * os verbos sempre s�o palavras de 8 ou mais letras que terminam numa letra
	 * tipo foo. Al�m disso, se um verbo come�a com uma letra tipo bar, o verbo
	 * est� em primeira pessoa. Assim, lendo o Texto A, � poss�vel identificar
	 * 29 verbos no texto, dos quais 25 est�o em primeira pessoa.
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
	 * Um professor universit�rio utilizar� os textos A e B para ensinar o
	 * Googlon aos alunos. Para ajudar os alunos a compreender o texto, esse
	 * professor precisa criar uma lista de vocabul�rio para cada texto, isto �,
	 * uma lista ordenada (e sem repeti��es) das palavras que aparecem em cada
	 * um dos textos. Essas listas devem estar ordenadas e n�o podem conter
	 * repeti��es de palavras. No Googlon, assim como no nosso alfabeto, as
	 * palavras s�o ordenadas lexicograficamente, mas o problema � que no
	 * Googlon, a ordem das letras no alfabeto � diferente da nossa. O alfabeto
	 * Googlon, em ordem, �: zbcqlwhvrjmtnksgxfdp. Assim, ao fazer essas listas,
	 * o professor deve respeitar a ordem alfab�tica Googlon.
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
	 * Mas como os Googlons escrevem n�meros? Bem, no Googlon, as palavras
	 * tamb�m s�o n�meros dados em base 20, onde cada letra � um d�gito, e os
	 * d�gitos s�o ordenados do menos significativo para o mais significativo (o
	 * inverso do nosso sistema). Ou seja, a primeira posi��o � a unidade, a
	 * segunda posi��o vale 20, a terceira vale 400, e assim por diante. Os
	 * valores das letras s�o dados pela ordem em que elas aparecem no alfabeto
	 * Googlon (que � diferente da nossa ordem, como vimos acima). Ou seja, a
	 * primeira letra do alfabeto Googlon representa o d�gito 0, a segunda
	 * representa o d�gito 1, e assim por diante.
	 * 
	 * Por exemplo, a palavra ppj tem o valor num�rico de 3999.
	 * 
	 * OBS: os n�meros nesse problema podem ser grandes, ent�o se voc� est�
	 * usando um tipo de dados de tamanho limitado, tenha cuidado com poss�veis
	 * overflows.
	 * 
	 * Os Googlons consideram um n�mero bonito se ele satizfaz essas duas
	 * propriedades: � maior ou igual a 823262 � divis�vel por 5
	 * 
	 * Ao consideramos o Texto A como uma lista de n�meros (isto �,
	 * interpretando cada palavra como um n�mero usando a conven��o explicada
	 * acima), notamos que existem 64 n�meros bonitos distintos. E no Texto B,
	 * quantos n�meros bonitos distintos existem?
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
