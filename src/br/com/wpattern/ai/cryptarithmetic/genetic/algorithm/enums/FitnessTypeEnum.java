package br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums;

/**
 * Tipo de processo de aptidão existentes para o Algoritmo Genético.
 * 
 * @author Branquinho
 */
public enum FitnessTypeEnum {

	/**
	 * Diferença entre a soma desejada e a soma real no valor total.
	 */
	MODULE("Modulo"),

	/**
	 * Soma das diferenças entre a  soma desejada e a soma real dígito a dígito.
	 */
	//SUM_DIGIT("Soma Dígito"),

	/**
	 * Produto das diferenças entre a  soma desejada e a soma real dígito a dígito.
	 */
	//MULTIPLY_DIGIT("Multiplica Dígito")
	;

	/**
	 * Nome mostrado para cada tipo de aptidão.
	 */
	private String name;

	/////////////////////////////////////////////////////////
	// CONSTRUTORES                                        //
	/////////////////////////////////////////////////////////

	private FitnessTypeEnum(String name) {
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
