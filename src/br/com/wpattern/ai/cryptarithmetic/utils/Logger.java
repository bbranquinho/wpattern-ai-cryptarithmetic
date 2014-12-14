package br.com.wpattern.ai.cryptarithmetic.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por logar informações. Este log é normalmente associado
 * com notificações apresentadas na interface gráfica.
 * 
 * @author Branquinho
 */
public class Logger {

	/**
	 * Lista de listeners de mensagens.
	 */
	private static final List<IListener> listeners = new ArrayList<IListener>();

	/**
	 * Instância Singleton do <b>Logger</b>.
	 */
	private static Logger loggerInstance;

	/////////////////////////////////////////////////////////
	// CONSTRUTORES                                        //
	/////////////////////////////////////////////////////////

	/**
	 * Construtor.
	 */
	protected Logger() {
	}

	/////////////////////////////////////////////////////////
	// METODOS PUBLICOS                                    //
	/////////////////////////////////////////////////////////

	public void log(String message) {
		// Realiza a notificacao da mensagem recebida para todos os listeners.
		for (IListener listener : listeners) {
			listener.onMessage(message);
		}
	}

	/**
	 * Recupera uma nova instância do <b>Logger</b>.
	 * 
	 * @return Logger.
	 */
	public static Logger getLoggerInstance() {
		if (loggerInstance == null) {
			loggerInstance = new Logger();
		}

		return loggerInstance;
	}

	/**
	 * Adiciona um novo listener para receber notificações de mensagens
	 * recebidas pelo <b>Logger</b>.
	 * 
	 * @param listener
	 * 			Listener de mensagens.
	 */
	public static void addListener(IListener listener) {
		listeners.add(listener);
	}

}
