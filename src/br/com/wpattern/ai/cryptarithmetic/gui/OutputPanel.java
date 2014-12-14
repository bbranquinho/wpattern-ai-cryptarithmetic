package br.com.wpattern.ai.cryptarithmetic.gui;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

public class OutputPanel extends JPanel {

	private static final long serialVersionUID = 5342970828932615069L;

	private JEditorPane editorPane;

	/**
	 * Create the panel.
	 */
	public OutputPanel() {
		this.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));

		this.editorPane = new JEditorPane();
		this.add(this.editorPane, "cell 0 0,grow");
		this.add(new JScrollPane(this.editorPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}

	public void setText(String text) {
		this.editorPane.setText(text);
	}

	public String getText() {
		return this.editorPane.getText();
	}

}
