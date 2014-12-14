package br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums;

/**
 * Tipo de crossover existentes para o Algoritmo Genético.
 * 
 * @author Branquinho
 */
public enum CrossoverTypeEnum {


	// Para realiza o crossover PMX inicialmente é necessário realizar o sorteio de
	// uma regi�o do cromosso. Essa regi�o corresponde a parte do cromosso que neste
	// problema possui 10 elementos, iniciando no �ndice 0 até o �ndice 9.
	// Após selecionar as regiões é preciso criar dois novos indiv�duos que iram conter
	// os novos valores do cromosso.
	// Posteriormente realizamos o cruzamento dos valores dos cromossos para as regiões
	// selecionadas.
	// Ao final é necessário resolver qualquer problema de repeti��o que possa existir
	// nos indiv�duos.
	//
	// Exemplo de crossover PMX para dois indiv�duos.
	// INDIV�DUO 1
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |7|5|1|4|3|6|8|2|0|9|
	// INDIV�DUO 2
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |3|4|8|7|5|2|6|1|9|9|
	//
	// Regi�o selecionada aleatoriamente entre as posi�ões 2 e 4.
	//
	// Indiv�duos gerados a partir da troca dos valores da regi�o selecionada.
	// NOVO INDIV�DUO 1
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |7|5|8|7|5|6|8|2|0|9|
	// NOVO INDIV�DUO 2
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |3|4|1|4|3|2|6|1|9|9|
	//
	// Removendo repeticoes dos digitos em cada individuo.
	// NOVO INDIV�DUO 1
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |3|4|8|7|5|6|1|2|0|9|
	// NOVO INDIV�DUO 2
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |7|5|1|4|3|2|6|8|9|9|
	//PMX("PMX"),

	// Para realiza o crossover C�clico inicialmente é necessário realizar o sorteio de
	// um ponto no cromosso. Este ponto corresponde a um local do cromosso que neste
	// problema possui 10 elementos, iniciando no �ndice 0 até o �ndice 9.
	// Após selecionar um ponto é preciso criar dois novos indiv�duos que iram conter
	// os novos valores do cromosso.
	// Posteriormente realizamos o cruzamento dos valores dos cromossos de modo c�clico.
	//
	// Exemplo de crossover c�clico para dois indiv�duos.
	// INDIV�DUO 1
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |7|5|1|4|3|6|8|2|0|9|
	// INDIV�DUO 2
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |3|4|8|7|5|2|6|1|9|9|
	//
	// Ponto selecionado aleatoriamente: 3.
	//
	// Indiv�duos gerados a partir da troca dos valores da regi�o selecionada de modo
	// c�clico.
	// NOVO INDIV�DUO 1
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |3|4|1|7|5|6|8|2|0|9|
	// NOVO INDIV�DUO 2
	//      Posi�ões   => |0|1|2|3|4|5|6|7|8|9|
	//      Cromossomo => |7|5|8|4|3|2|6|1|9|9|
	CYCLIC("Cíclico");

	/**
	 * Nome mostrado para cada tipo de sele��o.
	 */
	private final String name;

	/////////////////////////////////////////////////////////
	// CONSTRUTORES                                        //
	/////////////////////////////////////////////////////////

	private CrossoverTypeEnum(String name) {
		this.name = name;
	}

	/////////////////////////////////////////////////////////
	// MÉTODOS PÚBLICOS                                    //
	/////////////////////////////////////////////////////////

	@Override
	public String toString() {
		return this.name;
	}

}
