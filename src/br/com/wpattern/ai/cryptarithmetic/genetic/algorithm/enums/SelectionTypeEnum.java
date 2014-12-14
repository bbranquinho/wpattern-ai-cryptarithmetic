package br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums;

/**
 * Tipo de processo de seleção existentes para o Algoritmo Genético.
 * 
 * @author Branquinho
 */
public enum SelectionTypeEnum {

	/**
	 * Método de seleção aleatória.
	 */
	RANDOM("Aleatorio"),

	/**
	 * Método da roleta.
	 */
	//ROULETTE("Roleta"),

	/**
	 * Método de torneio 3.
	 */
	//TOURNAMENT_3("Torneio 3")
	;

	/**
	 * Nome mostrado para cada tipo de seleção.
	 */
	private String name;

	/////////////////////////////////////////////////////////
	// CONSTRUTORES                                        //
	/////////////////////////////////////////////////////////

	private SelectionTypeEnum(String name) {
		this.name = name;
	}

	/////////////////////////////////////////////////////////
	// MÃ‰TODOS PÃšBLICOS                                    //
	/////////////////////////////////////////////////////////

	@Override
	public String toString() {
		return this.name;
	}

}
