package br.com.wpattern.ai.cryptarithmetic.genetic.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.elements.Individual;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.elements.Population;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.CrossoverTypeEnum;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.FitnessTypeEnum;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.SelectionTypeEnum;
import br.com.wpattern.ai.cryptarithmetic.utils.Logger;

/**
 * Realiza a execu��o do algoritmo gen�tico para o problema de
 * criptoaritm�tica de acordo com determinaddos par�metros.
 * 
 * @author Branquinho
 */
public class GeneticAlgorithm {

	/**
	 * Este atributo pode ser usado para logar informa��es sobre
	 * a execu��o do algoritmo gen�tico.
	 */
	private final Logger logger = Logger.getLoggerInstance();

	/////////////////////////////////////////////////////////
	// CONSTRUTORES                                        //
	/////////////////////////////////////////////////////////

	/**
	 * Construtor sem par�metros.
	 */
	public GeneticAlgorithm() {
	}

	/////////////////////////////////////////////////////////
	// M�TODOS PÚBLICOS                                    //
	/////////////////////////////////////////////////////////

	/**
	 * Executa o algoritmo gen�tico considerando os par�metros da
	 * popula��o e do algoritmo gen�tico.
	 * 
	 * @param word1
	 * 			Primeira palavra do problema.
	 * @param word2
	 * 			Segunda palavra do problema.
	 * @param wordResult
	 * 			Palavra resultante da soma entre as duas primeiras palavras.
	 * @param populationSize
	 * 			Tamanho da popula��o.
	 * @param numberGenerations
	 * 			N�mero m�ximo de gera��es.
	 * @param percentageCrossover
	 * 			Percentual de indiv�duos selecionados para o crossover.
	 * 			Este percentual � maior que 0 e menor ou igual a 100.
	 * @param probabilityMutation
	 * 			Probabilidade de ocorrer a muta��o em um indiv�duo.
	 *          Esta probabilidade � maior ou igual 0 e menor ou igual a 100.
	 * @param fitnessType
	 * 			Tipo da aptid�oo.
	 * @param selectionType
	 * 			Tipo da sele��o.
	 * @param crossoverType
	 * 			Tipo do crossover.
	 * 
	 * @return
	 * 			Melhor indiv�duo encontrado corresponde a melhor solu��o do problema.
	 */
	public Individual executeGeneticAlgorithm(String word1, String word2,
			String wordResult, int populationSize, int numberGenerations,
			int percentageCrossover, int probabilityMutation,
			FitnessTypeEnum fitnessType, SelectionTypeEnum selectionType,
			CrossoverTypeEnum crossoverType) {

		this.logger.log("Iniciando o Algoritmo Genético para os seguintes parâmetros.");
		this.logger.log("Palavra 1: " + word1);
		this.logger.log("Palavra 2: " + word2);
		this.logger.log("Palavra Resultado: " + wordResult);
		this.logger.log("Tamanho da População: " + populationSize);
		this.logger.log("Número de Gerações " + numberGenerations);
		this.logger.log("Percentual de Crossover: " + percentageCrossover);
		this.logger.log("Probabilidade de Mutação: " + probabilityMutation);
		this.logger.log("Tipo de Aptidão: " + fitnessType);
		this.logger.log("Tipo de Seleção: " + selectionType);
		this.logger.log("Tipo de Crossover: " + crossoverType);

		int gercoes = 1;

		// TODO 1: Calcular a cadeia de caracteres �nicos a partir de "word1",
		// "word2" e
		// "wordResult".
		String caracteresUnicos = GeneticAlgorithm.calculateUniqueCharacters(word1, word2,
				wordResult);

		this.logger.log("Caracteres �nicos: "+caracteresUnicos);

		// TODO 3: Criar a popula��o do algoritmo gen�tico.
		Population population = new Population(word1, word2, wordResult, caracteresUnicos);

		// TODO 4: 	Gerar a populacao inicial de modo aleat�rio. Esta popula��o
		// 			deve conter "populationSize" indiv�duos.
		population.createRandomPopulation(populationSize);

		Individual individual = new Individual();

		while (true) {

			// TODO 5:  Realizar a avalia��o dos invidiv�duos criados
			// 			aleatoriamente.
			population.evaluatePopulation(fitnessType);

			// TODO 6: 	Verificar se o melhor indiv�duo � a solu��o (aptidao
			// igual a 0) do problema
			// ou se foi alcancado o n�mero m�ximo de gera��es.
			individual = population.getBestSolution();
			if (individual.getAptidao() == 0 || gercoes == numberGenerations) {
				break;
			}

			// TODO 7: 	Realizar a sele��o dos indiv�duos. A quantidade de indiv�duos selecionados
			//          para o crossover � igual a quantidade percentual de indiv�duos de acordo
			//	        com o tamanho da popula��o.
			//          Se "percentageCrossover" � igual a 60% e "populationSize" � igual a 50,
			//          ent�oo ser�oo selecionados 30 indiv�duos.
			//          O crossover � feito em pares, sendo assim, se a quantidade de indiv�duos
			//          for �mpar o �ltimo indiv�duo pode ser desconsiderado do crossover. Contudo,
			//          o algoritmo de sele��o pode escolher trazer sempre uma quantidade par de
			//          indiv�duos, tomando sempre a descis�oo de arredondar sempre a quantidade
			//          para um valor par.

			List<Individual> selectInd = population.selectIndividuals(percentageCrossover, selectionType);

			List<Individual> crossInd = new ArrayList<Individual>();

			// TODO 8: 	Realizamos o crossover dos indiv�duos selecionados.
			Iterator<Individual> iInd = selectInd.iterator();
			while(iInd.hasNext()){
				crossInd.addAll(population.executeCrossover(iInd.next(),iInd.next(), crossoverType));
			}

			// TODO 9: 	� executado o processo de muta��o em cada indiv�duo. Cada
			// 			indiv�duo possui uma probabilidade "probabilityMutation"
			//			de ocorrer uma muta��o.
			for(Individual i:crossInd){
				i = population.executeMutation(probabilityMutation, i);
			}

			// TODO 10: S�oo calculadas as aptid�es dos novos indiv�duos.
			// TODO 11: Os novos indiv�duos s�oo adicionados na popula��o.
			population.getIndividuals().addAll(crossInd);
			population.evaluatePopulation(fitnessType);

			// TODO 12: Os indiv�duos da popula��o sao ordenados de modo
			// 			crescente de acordo com a aptid�oo.

			// TODO 13: S�oo removidos da popula��o os indiv�duos que possuem as piores aptid�es.
			// 			Ap�s essa remo��o devem restar o mesmo tamanho da popula��o inicial.
			population.executeNaturalSelection();

			// TODO 14: � repetido todo o processo novamente a partir do passo 4.
			gercoes++;
		}
		// TODO 15: O melhor indiv�duo e retornado como sendo a solu��o.
		return individual;
	}

	/////////////////////////////////////////////////////////
	// M�TODOS PRIVADOS                                    //
	/////////////////////////////////////////////////////////

	private static String calculateUniqueCharacters(String word1, String word2,
			String wordResult) {
		// TODO 2: 	Realizar o c�lculo de uma cadeia de caracteres �nicos
		// 			considerando osvalores de "word1", "word2" e "wordResult".
		//			Por exemplo: word1 = SEND
		// 						 word2 = MORE
		//						 wordResult = MONEY
		//
		// 			A cadeia com caracteres �nicos �: SENDMORY
		char[] w1 = word1.toCharArray();
		char[] w2 = word2.toCharArray();
		char[] wr = wordResult.toCharArray();
		String uni = "";

		boolean tem = true;
		for (int i = 0; i < w1.length; i++) {
			tem = true;
			for (int j = 0; j < uni.length(); j++) {
				if (uni.charAt(j) == w1[i]) {
					tem = false;
					break;
				}
			}
			if (tem) {
				uni = uni + w1[i];
			}
		}

		for (int i = 0; i < w2.length; i++) {
			tem = true;
			for (int j = 0; j < uni.length(); j++) {
				if (uni.charAt(j) == w2[i]) {
					tem = false;
					break;
				}
			}
			if (tem) {
				uni = uni + w2[i];
			}
		}

		for (int i = 0; i < wr.length; i++) {
			tem = true;
			for (int j = 0; j < uni.length(); j++) {
				if (uni.charAt(j) == wr[i]) {
					tem = false;
					break;
				}
			}
			if (tem) {
				uni = uni + wr[i];
			}
		}
		return uni;
	}

}
