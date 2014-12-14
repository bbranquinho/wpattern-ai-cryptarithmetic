package br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums;

/**
 * Tipo de processo de aptid�o existentes para o Algoritmo Gen�tico.
 * 
 * @author Branquinho
 */
public enum FitnessTypeEnum {

	/**
	 * Diferen�a entre a soma desejada e a soma real no valor total.
	 */
	MODULE("Modulo"),

	/**
	 * Soma das diferen�as entre a  soma desejada e a soma real d�gito a d�gito.
	 */
	//SUM_DIGIT("Soma D�gito"),

	/**
	 * Produto das diferen�as entre a  soma desejada e a soma real d�gito a d�gito.
	 */
	//MULTIPLY_DIGIT("Multiplica D�gito")
	;

	/**
	 * Nome mostrado para cada tipo de aptid�o.
	 */
	private String name;

	/////////////////////////////////////////////////////////
	// CONSTRUTORES                                        //
	/////////////////////////////////////////////////////////

	private FitnessTypeEnum(String name) {
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
