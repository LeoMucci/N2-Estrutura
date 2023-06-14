package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class TelaInicialView {
    private JFrame frame;
    private JPanel panel;
    private JButton atendimentoButton;
    private JButton medicoButton;

    public TelaInicialView() {
        frame = new JFrame("Hospital Endurance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Bem-vindo ao Hospital Endurance");
        panel.add(label, constraints);

        constraints.gridy = 1;

        atendimentoButton = new JButton("Painel de Atendimento");
        panel.add(atendimentoButton, constraints);
        


        constraints.gridy = 2;
        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setVisible(true);


        
    }

    public void addAtendimentoButtonListener(ActionListener listener) {
        atendimentoButton.addActionListener(listener);
    }

    public void addmedicooButtonListener(ActionListener listener) {
        medicoButton.addActionListener(listener);
    }
}

