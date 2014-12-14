package br.com.wpattern.ai.cryptarithmetic.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.GeneticAlgorithm;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.elements.Individual;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.CrossoverTypeEnum;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.FitnessTypeEnum;
import br.com.wpattern.ai.cryptarithmetic.genetic.algorithm.enums.SelectionTypeEnum;
import br.com.wpattern.ai.cryptarithmetic.utils.IListener;
import br.com.wpattern.ai.cryptarithmetic.utils.Logger;

public class MainWindow {

	private static final int MAX_NUMBER_LENGTH = 5;
	private static final int MAX_WORD_LENGTH = 13;

	private JFrame frmCryptarithmetic;
	private JTextField txtWord1;
	private JTextField txtWord2;
	private JTextField txtWordResult;
	private JTextField txtPopulation;
	private JTextField txtGenerations;
	private JTextField txtCrossover;
	private JTextField txtMutation;
	private JButton btnFindSolution;
	private JComboBox cmbSelectionType;
	private JComboBox cmbCrossoverType;
	private JComboBox cmbFitnessType;
	private JLabel lblCrossover;
	private OutputPanel edtLogger;
	private OutputPanel edtResults;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmCryptarithmetic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		this.initialize();
		this.customInitialize();
	}

	private void initialize() {
		this.frmCryptarithmetic = new JFrame();
		this.frmCryptarithmetic.setResizable(false);
		this.frmCryptarithmetic.setTitle("Criptoaritmética");
		this.frmCryptarithmetic.setBounds(100, 100, 542, 558);
		this.frmCryptarithmetic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmCryptarithmetic.getContentPane().setLayout(new MigLayout("", "[59.00,grow][115.00][103.00][78.00,grow]", "[][][][][][][][][160.00][140.00,grow]"));

		JLabel lblWord1 = new JLabel("Palavra 1:");
		this.frmCryptarithmetic.getContentPane().add(lblWord1, "cell 0 0,alignx trailing");

		this.txtWord1 = new JTextField();
		this.txtWord1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyPressed) {
				MainWindow.this.processWords(MainWindow.this.txtWord1, keyPressed);
			}
		});
		this.txtWord1.setHorizontalAlignment(SwingConstants.RIGHT);
		this.frmCryptarithmetic.getContentPane().add(this.txtWord1, "cell 1 0,growx");
		this.txtWord1.setColumns(10);

		this.txtWord2 = new JTextField();
		this.txtWord2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyPressed) {
				MainWindow.this.processWords(MainWindow.this.txtWord2, keyPressed);
			}
		});
		this.txtWord2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.frmCryptarithmetic.getContentPane().add(this.txtWord2, "cell 1 1,growx");
		this.txtWord2.setColumns(10);

		this.txtWordResult = new JTextField();
		this.txtWordResult.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyPressed) {
				MainWindow.this.processWords(MainWindow.this.txtWordResult, keyPressed);
			}
		});
		this.txtWordResult.setHorizontalAlignment(SwingConstants.RIGHT);
		this.frmCryptarithmetic.getContentPane().add(this.txtWordResult, "cell 1 3,growx");
		this.txtWordResult.setColumns(10);

		JLabel lblPopulation = new JLabel("População:");
		lblPopulation.setHorizontalAlignment(SwingConstants.RIGHT);
		this.frmCryptarithmetic.getContentPane().add(lblPopulation, "cell 2 0,alignx trailing");

		this.txtPopulation = new JTextField();
		this.txtPopulation.setText("100");
		this.txtPopulation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyPressed) {
				MainWindow.this.processNumbers(MainWindow.this.txtPopulation, keyPressed);
			}
		});
		this.frmCryptarithmetic.getContentPane().add(this.txtPopulation, "cell 3 0,growx");
		this.txtPopulation.setColumns(10);

		JLabel lblWord2 = new JLabel("Palavra 2:");
		this.frmCryptarithmetic.getContentPane().add(lblWord2, "cell 0 1,alignx trailing");

		JLabel lblGenerations = new JLabel("Gerações:");
		this.frmCryptarithmetic.getContentPane().add(lblGenerations, "cell 2 1,alignx trailing");

		this.txtGenerations = new JTextField();
		this.txtGenerations.setText("100");
		this.txtGenerations.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyPressed) {
				MainWindow.this.processNumbers(MainWindow.this.txtGenerations, keyPressed);
			}
		});
		this.frmCryptarithmetic.getContentPane().add(this.txtGenerations, "cell 3 1,growx");
		this.txtGenerations.setColumns(10);

		JLabel label = new JLabel("                   +_________________");
		this.frmCryptarithmetic.getContentPane().add(label, "cell 0 2 2 1,alignx right");

		this.lblCrossover = new JLabel("Crossover (%):");
		this.frmCryptarithmetic.getContentPane().add(this.lblCrossover, "cell 2 2,alignx trailing");

		this.txtCrossover = new JTextField();
		this.txtCrossover.setText("60");
		this.txtCrossover.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyPressed) {
				MainWindow.this.processNumbers(MainWindow.this.txtCrossover, keyPressed);
			}
		});
		this.frmCryptarithmetic.getContentPane().add(this.txtCrossover, "cell 3 2,growx");
		this.txtCrossover.setColumns(10);

		JLabel lblWordResult = new JLabel("Resultado:");
		this.frmCryptarithmetic.getContentPane().add(lblWordResult, "cell 0 3,alignx trailing");

		JLabel lblMutation = new JLabel("Muta\u00E7\u00E3o (%):");
		this.frmCryptarithmetic.getContentPane().add(lblMutation, "cell 2 3,alignx trailing");

		this.txtMutation = new JTextField();
		this.txtMutation.setText("2");
		this.txtMutation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyPressed) {
				MainWindow.this.processNumbers(MainWindow.this.txtMutation, keyPressed);
			}
		});
		this.frmCryptarithmetic.getContentPane().add(this.txtMutation, "cell 3 3,growx");
		this.txtMutation.setColumns(10);

		JLabel lblCrossoverType = new JLabel("Tipo de Crossover:");
		this.frmCryptarithmetic.getContentPane().add(lblCrossoverType, "cell 2 5,alignx trailing");

		JLabel lblSelectionType = new JLabel("Tipo de Seleção:");
		this.frmCryptarithmetic.getContentPane().add(lblSelectionType, "cell 2 4,alignx trailing");

		this.cmbSelectionType = new JComboBox();
		this.cmbSelectionType.setModel(new DefaultComboBoxModel(SelectionTypeEnum.values()));
		this.frmCryptarithmetic.getContentPane().add(this.cmbSelectionType, "cell 3 4,growx");

		this.cmbCrossoverType = new JComboBox();
		this.cmbCrossoverType.setModel(new DefaultComboBoxModel(CrossoverTypeEnum.values()));
		this.frmCryptarithmetic.getContentPane().add(this.cmbCrossoverType, "cell 3 5,growx");

		JLabel lblFitnessType = new JLabel("Tipo de Aptidão:");
		this.frmCryptarithmetic.getContentPane().add(lblFitnessType, "cell 2 6,alignx trailing");

		this.cmbFitnessType = new JComboBox();
		this.cmbFitnessType.setModel(new DefaultComboBoxModel(FitnessTypeEnum.values()));
		this.frmCryptarithmetic.getContentPane().add(this.cmbFitnessType, "cell 3 6,growx");

		this.btnFindSolution = new JButton("Buscar Solução (Algoritmo Genético)");
		this.btnFindSolution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

				Integer populationSize = MainWindow.this.tryParseToInt("Popula��o", MainWindow.this.txtPopulation.getText());
				Integer numberGenerations = MainWindow.this.tryParseToInt("Gera��es", MainWindow.this.txtGenerations.getText());
				Integer percentageCrossover = MainWindow.this.tryParseToInt("Crossover", MainWindow.this.txtCrossover.getText());
				Integer probabilityMutation = MainWindow.this.tryParseToInt("Muta��o", MainWindow.this.txtMutation.getText());

				if ((populationSize == null) || (numberGenerations == null) ||
						(percentageCrossover == null) || (probabilityMutation == null)) {
					return;
				}

				if ((percentageCrossover <= 0) || (percentageCrossover > 100)) {
					JOptionPane.showMessageDialog(MainWindow.this.frmCryptarithmetic, "O valor do \"Crossover\" deve ser maior que 0 e menor ou igual a 100.");
					return;
				}

				if ((probabilityMutation <= 0) || (probabilityMutation > 100)) {
					JOptionPane.showMessageDialog(MainWindow.this.frmCryptarithmetic, "O valor da \"Muta��o\" deve ser maior que 0 e menor ou igual a 100.");
					return;
				}

				Individual solution = geneticAlgorithm.executeGeneticAlgorithm(MainWindow.this.txtWord1.getText(), MainWindow.this.txtWord2.getText(),
						MainWindow.this.txtWordResult.getText(), populationSize, numberGenerations, percentageCrossover,
						probabilityMutation, (FitnessTypeEnum)MainWindow.this.cmbFitnessType.getSelectedItem(),
						(SelectionTypeEnum)MainWindow.this.cmbSelectionType.getSelectedItem(),
						(CrossoverTypeEnum)MainWindow.this.cmbCrossoverType.getSelectedItem());

				MainWindow.this.edtResults.setText(String.format("%s%s\n", MainWindow.this.edtResults.getText(), solution));
			}
		});
		this.frmCryptarithmetic.getContentPane().add(this.btnFindSolution, "cell 0 7 4 1,growx");

		this.edtLogger = new OutputPanel();
		this.frmCryptarithmetic.getContentPane().add(this.edtLogger, "cell 0 8 4 1,grow");

		this.edtResults = new OutputPanel();
		this.frmCryptarithmetic.getContentPane().add(this.edtResults, "cell 0 9 4 1,grow");
	}

	private void customInitialize() {
		Logger.addListener(new IListener() {
			@Override
			public void onMessage(String message) {
				MainWindow.this.edtLogger.setText(String.format("%s%s\n", MainWindow.this.edtLogger.getText(), message));
			}
		});
	}

	private void processWords(JTextField textField, KeyEvent keyPressed) {
		keyPressed.setKeyChar(Character.toUpperCase(keyPressed.getKeyChar()));

		if (textField.getText().length() >= MAX_WORD_LENGTH) {
			keyPressed.consume();
		}
	}

	private void processNumbers(JTextField textField, KeyEvent keyPressed) {
		if (textField.getText().toString().length() >= MAX_NUMBER_LENGTH) {
			keyPressed.consume();
		}

		if (!Character.isDigit(keyPressed.getKeyChar())) {
			keyPressed.consume();
		}
	}

	private Integer tryParseToInt(String field, String number) {
		try{
			return Integer.parseInt(number);
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this.frmCryptarithmetic, String.format("Valor inválido para o campo \"%s\".", field));
			return null;
		}
	}

}
