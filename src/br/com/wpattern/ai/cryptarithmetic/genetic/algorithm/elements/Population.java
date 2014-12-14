package br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.CrossoverTypeEnum;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.FitnessTypeEnum;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.SelectionTypeEnum;

/**
 * Guarda a populaÃ§Ã£o com todo um grupo de indivÃ­duos. TambÃ©m Ã© responsÃ¡vel
 * por realizar operaÃ§Ãµes de seleÃ§Ã£o, crossover e mutaÃ§Ã£o.
 * 
 * @author Branquinho
 */
public class Population {

	/**
	 * Este atributo pode ser usado para logar informaÃ§Ãµes sobre a execuÃ§Ã£o do
	 * algoritmo genÃ©tico.
	 */
	//private static final Logger logger = Logger.getLoggerInstance();

	private static final Random radom = new Random(System.currentTimeMillis());

	// TODO 1: A populaÃ§Ã£o Ã© formada por uma lista/coleÃ§Ã£o de indivÃ­duos.

	// TODO 2: Ã‰ necessÃ¡rio guardar as palavras do problema e o tamanho da
	// populaÃ§Ã£o.
	private String word1;
	private String word2;
	private String wordResult;

	private int quantPopulation;

	// TODO 3: Ã‰ necessÃ¡rio calcular a cadeia de caracteres Ãºnicos do problema.
	private String uniqueCaracteres;

	// TODO 4: Guardar a populaÃ§Ã£o em uma lista de indivÃ­duos.
	private List<Individual> individuals;

	// ///////////////////////////////////////////////////////
	// CONSTRUTORES //
	// ///////////////////////////////////////////////////////

	/**
	 * Construtor que recebe as palavras do problema de criptoaritmÃ©tica.
	 * 
	 * @param word1
	 *            Primeira palavra.
	 * @param word2
	 *            Segunda palavra.
	 * @param wordResult
	 *            Resultado da operaÃ§Ã£o de soma das duas palavras.
	 */
	public Population(String word1, String word2, String wordResult,
			String uniqueCaracteres) {
		// TODO 5: Calcular a cadeia de caracteres Ãºnicos de acordo com as
		// palavras.
		// Por exemplo, "word1", "word2" e "wordResult" sÃ£o iguais,
		// respectivamente,
		// a SEND, MORE e MONEY, entÃ£o a cadeia de caracteres Ãºnicos Ã© SENDMORY.
		this.word1 = word1;
		this.word2 = word2;
		this.wordResult = wordResult;
		this.uniqueCaracteres = uniqueCaracteres;
	}

	// ///////////////////////////////////////////////////////
	// METODOS PUBLICOS //
	// ///////////////////////////////////////////////////////

	/**
	 * ResponsÃ¡vel por criar uma populaÃ§Ã£o aleatÃ³ria de acordo com o valor de
	 * "populationSize".
	 * 
	 * @param populationSize
	 *            Tamanho da populaÃ§Ã£o.
	 */
	public void createRandomPopulation(int populationSize) {
		// TODO 6: Este mÃ©todo que irÃ¡ criar uma populaÃ§Ã£o com "populationSize"
		// indivÃ­duos.
		// Essa populaÃ§Ã£o eh aleatÃ³ria e deve ser guardada em uma lista de
		// indivÃ­duos. O tamanho da populaÃ§Ã£o deve ser guardado nesta classe.
		this.quantPopulation = populationSize;
		this.individuals = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++) {
			this.individuals.add(this.createIndividual());
		}
	}

	/**
	 * Realiza o cÃ¡lculo/avaliaÃ§Ã£o da aptidÃ£o dos indivÃ­duos da populaÃ§Ã£o.
	 * 
	 * @param fitnessType
	 *            Tipo do cÃ¡lculo da aptidÃ£o dos indivÃ­duos.
	 */
	public void evaluatePopulation(FitnessTypeEnum fitnessType) {
		// TODO 8: Calcular a aptidÃ£o de cada indivÃ­duo da populaÃ§Ã£o.
		if (fitnessType.equals(FitnessTypeEnum.MODULE)) {
			this.calcApModule();
		}
	}

	/**
	 * Recupera o melhor indivÃ­duo da populaÃ§Ã£o.
	 * 
	 * @return Melhor indivÃ­duo.
	 */
	public Individual getBestSolution() {
		// TODO 9: Recuperar o melhor indivÃ­duo presente na populaÃ§Ã£o.
		Individual ind = new Individual();
		ind.setAptidao(Integer.MAX_VALUE);
		for (Individual i : this.individuals) {
			if (ind.getAptidao() >= i.getAptidao()) {
				ind = i;
			}
		}
		return ind;
	}

	/**
	 * Seleciona um grupo de indivÃ­duos para serem usados no crossover.
	 * 
	 * @param percentageCrossover
	 *            Percentual de indivÃ­duos selecionados para o crossover. Este
	 *            percentual Ã© maior que 0 e menor ou igual a 100.
	 * @param selectionType
	 *            Tipo da seleÃ§Ã£o.
	 */
	public List<Individual> selectIndividuals(int percentageCrossover,
			SelectionTypeEnum selectionType) {
		// TODO 10: Realizar a seleÃ§Ã£o dos indivÃ­duos. A quantidade de
		// indivÃ­duos selecionados
		// para o crossover Ã© igual a quantidade percentual de indivÃ­duos de
		// acordo
		// com o tamanho da populaÃ§Ã£o.
		// Se "percentageCrossover" Ã© igual a 60% e "populationSize" Ã© igual a
		// 50,
		// entÃ£o serÃ£o selecionados 30 indivÃ­duos.
		// O crossover Ã© feito em pares, sendo assim, se a quantidade de
		// indivÃ­duos
		// for Ã­mpar o Ãºltimo indivÃ­duo pode ser desconsiderado do crossover.
		// Contudo,
		// o algoritmo de seleÃ§Ã£o pode escolher trazer sempre uma quantidade par
		// de
		// indivÃ­duos, tomando sempre a descisÃ£o de arredondar sempre a
		// quantidade
		// para um valor par.
		List<Individual> listaIndividuos = new ArrayList<Individual>();

		int calcPorcentagem = (int) (this.quantPopulation * (percentageCrossover / 100.0));
		int cont = 0;
		boolean temzero = false;

		if (calcPorcentagem % 2 != 0) {
			calcPorcentagem = calcPorcentagem - 1;
		}
		int[] x = new int[calcPorcentagem];

		boolean tem = false;
		for (int i = 0; i < calcPorcentagem; i++) {

			// System.out.println(resultado);
			cont ++;

			while (true) {
				tem = false;
				int resultado = radom.nextInt((int) this.quantPopulation);
				for (int j = 0; j < cont; j++) {
					if (resultado == 0 && !temzero) {
						temzero = true;
						break;
					} else if (resultado == x[j]) {
						tem = true;
						break;
					}
				}
				if (!tem) {
					x[i] = resultado;
					listaIndividuos.add(this.individuals.get(resultado));
					break;
				}
			}
		}
		return listaIndividuos;
	}

	/**
	 * Executa o crossover em dois indivÃ­duos gerandos novos indivÃ­duos. Para
	 * este caso sempre serÃ£o gerados apenas 2 novos indivÃ­duos.
	 * 
	 * @param individual1
	 *            Primeiro indivÃ­duo usado no crossover.
	 * @param individual2
	 *            Segundo indivÃ­duo usado no crossover.
	 * @param crossoverType
	 *            Tipo do crossover.
	 * 
	 * @return Novos indivÃ­duos resultantes do crossover.
	 */
	public List<Individual> executeCrossover(Individual individual1,
			Individual individual2, CrossoverTypeEnum crossoverType) {
		// TODO 11: Ã‰ preciso que seja executado o crossover nos dois
		// indivÃ­vudos. O crossover
		// deve ser do tipo especificado em "crossoverType". Na descriÃ§Ã£o do
		// enumeration
		// "CrossoverTypeEnum" estÃ¡ a descriÃ§Ã£o de como sÃ£o executados cada um
		// dos
		// dos crossovers (PMX e CÃ­clico).

		List<Individual> individuals = new ArrayList<Individual>();

		switch (crossoverType) {

		case CYCLIC:
			int pos = radom.nextInt(10);
			Individual ind3 = new Individual();
			ind3.setAptidao(individual2.getAptidao());
			ind3.setCromossomo(individual2.getCromossomo().clone());
			Individual ind4 = new Individual();
			ind4.setAptidao(individual1.getAptidao());
			ind4.setCromossomo(individual1.getCromossomo().clone());

			int tmp3 = ind3.getCromossomo()[pos];
			int tmp4 = ind4.getCromossomo()[pos];
			ind3.getCromossomo()[pos] = tmp4;
			ind4.getCromossomo()[pos] = tmp3;

			for (int i = 0; i < 10; i++) {
				if (i != pos) {
					if (ind3.getCromossomo()[i] == tmp4) {
						ind3.getCromossomo()[i] = tmp3;
					}
				}

				if (i != pos) {
					if (ind4.getCromossomo()[i] == tmp3) {
						ind4.getCromossomo()[i] = tmp4;
					}
				}
			}

			individuals.add(ind3);
			individuals.add(ind4);
			break;
		default:
			break;
		}

		return individuals;
	}

	/**
	 * Executa a mutaÃ§Ã£o em um indivÃ­duo caso seja satisfeita a probabilidade
	 * disso ocorre.
	 * 
	 * @param probabilityMutation
	 *            Probabilidade de ocorrer a mutaÃ§Ã£o em um indivÃ­duo.
	 * @param individual
	 *            IndivÃ­duo que podera sofrer a mutaÃ§Ã£o.
	 * 
	 * @return IndivÃ­duo.
	 */
	public Individual executeMutation(int probabilityMutation,
			Individual individual) {
		// TODO 12: Realizar o sorteio de um valor entre 0 e 100.
		// Se este o valor sorteado for menor que "probabilityMutation" entÃ£o Ã©
		// executada a mutaÃ§Ã£o no indivÃ­duo. Caso contrÃ¡rio, apenas retornamos o
		// indivÃ­duo sem alteraÃ§Ãµes.
		int porc = radom.nextInt(100);

		if (porc <= probabilityMutation) {
			// TODO 13: Caso tenha que realizar a mutaÃ§Ã£o no indivÃ­duo sÃ£o
			// sorteadas duas
			// posiÃ§Ãµes entre 0 e 9. Ã‰ realizada a troca dos valores dos genes
			// em
			// cada uma destas posiÃ§Ãµes.

			// TODO 14: Ã‰ retornado o indivÃ­duo resultante deste processo.

			// TODO 15: Exemplo de mutaÃ§Ã£o de um indivÃ­duo.
			// INDIVÃ�DUO
			// PosiÃ§Ãµes => |0|1|2|3|4|5|6|7|8|9|
			// Cromossomo => |7|5|1|4|3|6|8|2|0|9|
			//
			// PosiÃ§Ãµes sorteadas: 1 e 5.
			//
			// Realizamos a troca dos genes.
			// INDIVÃ�DUO APÃ“S A MUTAÃ‡ÃƒO
			// PosiÃ§Ãµes => |0|1|2|3|4|5|6|7|8|9|
			// Cromossomo => |7|6|1|4|3|5|8|2|0|9|
			int pos1 = radom.nextInt(10);
			int pos2;
			int cross[] = individual.getCromossomo().clone();
			while (true) {
				pos2 = radom.nextInt(10);
				if (pos2 != pos1) {
					break;
				}
			}
			int tmp = cross[pos1];
			cross[pos1] = cross[pos2];
			cross[pos2] = tmp;
			individual.setCromossomo(cross);
		}
		return individual;
	}

	/**
	 * 
	 */
	public void executeNaturalSelection() {
		// TODO 16: Todos os indivÃ­duos sÃ£o ordenados de acordo com a aptidÃ£o e
		// os
		// piores sÃ£o eliminados. A populaÃ§Ã£o final deve possuir a mesma
		// quantidade da populaÃ§Ã£o inicial que foi gerada aleatoriamente.

		Collections.sort(this.individuals, new Comparator<Individual>() {
			@Override
			public int compare(Individual o1, Individual o2) {
				return o1.getAptidao() - o2.getAptidao();
			}

		});
		for(int i=this.quantPopulation; i< this.individuals.size();i++){
			this.individuals.remove(i);
		}


	}

	public List<Individual> getIndividuals() {
		return this.individuals;
	}

	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}

	// ///////////////////////////////////////////////////////
	// MÃ‰TODOS PRIVADOS //
	// ///////////////////////////////////////////////////////

	/**
	 * Cria um indivÃ­duo de forma aleatÃ³ria.
	 * 
	 * @return InidivÃ­duo.
	 */
	private Individual createIndividual() {
		// TODO 7: Este mÃ©todo deve criar um indivÃ­duo aleatÃ³ria e o retornar
		// para quem o chamou.
		// Neste indivÃ­duo teremos uma combinaÃ§Ã£o de 10 dÃ­gitos entre 0 e 9 que
		// nÃ£o se
		// repetem.
		int[] x = new int[10];
		boolean temzero = false;
		for (int i = 0; i < 10; i++) {
			while (true) {
				boolean tem = false;
				int res = radom.nextInt(10);
				for (int j = 0; j < 10; j++) {
					if (res == 0 && !temzero) {
						temzero = true;
						break;
					} else if (res == x[j]) {
						tem = true;
						break;
					}
				}
				if (!tem) {
					x[i] = res;
					break;
				}
			}
		}
		Individual individual = new Individual();
		individual.setCromossomo(x);
		individual.setAptidao(0);
		return individual;
	}

	private void calcApModule() {
		for (Individual i : this.individuals) {
			int tamanhoW1 = this.word1.length();
			int tamanhoW2 = this.word1.length();
			int somaW1 = 0;
			int somaW2 = 0;
			int somaR = 0;
			int el = tamanhoW1-1;
			for (int j = 0; j < tamanhoW1; j++) {
				somaW1 += (i.getCromossomo()[this.uniqueCaracteres.indexOf(String.valueOf(this.word1.charAt(j)))]) * (Math.pow(10, el));
				el--;
			}
			el = tamanhoW2-1;
			for (int j = 0; j < tamanhoW2; j++) {
				somaW2 += (i.getCromossomo()[this.uniqueCaracteres.indexOf(String.valueOf(this.word2.charAt(j)))]) * (Math.pow(10, el));
				el--;
			}
			somaR = somaW1 + somaW2;

			int tamanhoWR = this.wordResult.length();
			int somaResult = 0;
			el = tamanhoWR-1;
			for (int j = 0; j < tamanhoWR; j++) {
				somaResult += (i.getCromossomo()[this.uniqueCaracteres.indexOf(String.valueOf(this.wordResult.charAt(j)))]) * (Math.pow(10, el));
				el--;
			}

			i.setAptidao(Math.abs(somaResult - somaR));
		}
	}

}
