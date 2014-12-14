package br.com.wpattern.ai.cryptarithmetic.utils;

/**
 * Usado para observar mensagens contendo informações sobre a execução
 * do algoritmo genético.
 * 
 * @author Branquinho
 */
public interface IListener {

	/**
	 * Evento de receber uma nova mensagem.
	 * 
	 * @param message
	 * 			Mensagem recebida.
	 */
	public void onMessage(String message);

}
