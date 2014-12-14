package br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.elements;

/**
 * Este elemento é o indivíduo, ou seja, corresponde a solução
 * do problema.
 * 
 * @author Branquinho
 */
public class Individual {

	// TODO 1: O indivíduo possui o cromosso que representa um vetor
	//         de inteiros de tamanho 10.
	private int[] cromossomo = new int[10];

	// TODO 2: Também está no indivíduo a sua aptidão.
	private int aptidao;

	/////////////////////////////////////////////////////////
	// CONSTRUTORES                                        //
	/////////////////////////////////////////////////////////

	/**
	 * Construtor sem parâmetros.
	 */
	public Individual() {
	}

	/////////////////////////////////////////////////////////
	// MÉTODOS PÚBLICOS                                    //
	/////////////////////////////////////////////////////////

	@Override
	public String toString() {
		// TODO 3: Colocar os valores de como será impresso o indivíduo
		//         na tela.
		String crom = String.format("Aptidão: %s\n[", this.aptidao);
		for(int i=0;i<this.cromossomo.length;i++){
			crom += this.cromossomo[i]+", ";
		}

		return crom.trim()+"]";
	}

	public int[] getCromossomo() {
		return this.cromossomo;
	}

	public void setCromossomo(int[] cromossomo) {
		this.cromossomo = cromossomo;
	}

	public int getAptidao() {
		return this.aptidao;
	}

	public void setAptidao(int aptidao) {
		this.aptidao = aptidao;
	}

}
