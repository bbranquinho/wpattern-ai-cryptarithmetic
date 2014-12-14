package br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums;

/**
 * Tipo de processo de sele��o existentes para o Algoritmo Gen�tico.
 * 
 * @author Branquinho
 */
public enum SelectionTypeEnum {

	/**
	 * M�todo de sele��o aleat�ria.
	 */
	RANDOM("Aleatorio"),

	/**
	 * M�todo da roleta.
	 */
	//ROULETTE("Roleta"),

	/**
	 * M�todo de torneio 3.
	 */
	//TOURNAMENT_3("Torneio 3")
	;

	/**
	 * Nome mostrado para cada tipo de sele��o.
	 */
	private String name;

	/////////////////////////////////////////////////////////
	// CONSTRUTORES                                        //
	/////////////////////////////////////////////////////////

	private SelectionTypeEnum(String name) {
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
