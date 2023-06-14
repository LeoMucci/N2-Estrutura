package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelAtendimentoView {
    private JFrame frame;
    private JTable senhaAtualTable;
    private JTable historicoTable;
    private JButton cadastrarSenhaButton;
    private JButton verificarPosicaoButton;
    public JButton chamarSenhaButton;
    private DefaultTableModel senhaAtualTableModel;
    private String senhaAtual;

    public PainelAtendimentoView() {
        frame = new JFrame("Painel de Atendimento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        senhaAtualTable = createTable();
        JScrollPane senhaAtualScrollPane = new JScrollPane(senhaAtualTable);
        senhaAtualScrollPane.setPreferredSize(new Dimension(800, 50)); // Ajustar o tamanho da tabela da senha atual
        panel.add(senhaAtualScrollPane, constraints);

        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        historicoTable = createTable();
        JScrollPane historicoScrollPane = new JScrollPane(historicoTable);
        panel.add(historicoScrollPane, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        cadastrarSenhaButton = new JButton("Cadastrar senha");
        verificarPosicaoButton = new JButton("Verificar posição");
        chamarSenhaButton = new JButton("Chamar Senha");
        buttonPanel.add(cadastrarSenhaButton);
        buttonPanel.add(verificarPosicaoButton);
        buttonPanel.add(chamarSenhaButton);
        panel.add(buttonPanel, constraints);

        frame.getContentPane().add(panel);
        frame.pack(); // Ajustar automaticamente o tamanho da janela
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setVisible(true);

        // Inicialize a senhaAtualTableModel
        senhaAtualTableModel = (DefaultTableModel) senhaAtualTable.getModel();
    }

    private JTable createTable() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Senha");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Especialidade");
        tableModel.addColumn("Tipo de Atendimento");

        return new JTable(tableModel);
    }

    public void addChamarSenhaButtonListener(ActionListener listener) {
        chamarSenhaButton.addActionListener(listener);
    }

    public void updateSenhaAtualTable(String senha, String nome, String especialidade, String tipoAtendimento) {
        DefaultTableModel tableModel = (DefaultTableModel) senhaAtualTable.getModel();
        tableModel.setRowCount(0); // Limpa a tabela

        tableModel.addRow(new Object[]{senha, nome, especialidade, tipoAtendimento});

        senhaAtual = senha; // Armazena a senha atual
    }

    public void updateHistoricoTable(String senha, String nome, String especialidade, String tipoAtendimento) {
        DefaultTableModel tableModel = (DefaultTableModel) historicoTable.getModel();
        tableModel.addRow(new Object[]{senha, nome, especialidade, tipoAtendimento});
    }

    public void removerSenhaAtualTable() {
        DefaultTableModel tableModel = (DefaultTableModel) senhaAtualTable.getModel();
        int rowCount = tableModel.getRowCount();
        if (rowCount > 0) {
            tableModel.removeRow(rowCount - 1);
        }
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void addCadastrarSenhaButtonListener(ActionListener listener) {
        cadastrarSenhaButton.addActionListener(listener);
    }

    public void addVerificarPosicaoButtonListener(ActionListener listener) {
        verificarPosicaoButton.addActionListener(listener);
    }
    
    public JTable getTabelaSenhasAtuais() {
        return senhaAtualTable;
    }
    
}
