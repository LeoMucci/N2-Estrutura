package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.HospitalModel;

public class CadastrarSenhaView {
    private JFrame frame;
    private JTextField nomeTextField;
    private JComboBox<String> especialidadeComboBox;
    private JTextField idadeTextField;
    private JButton cadastrarButton;
    private HospitalModel model;

    public CadastrarSenhaView(HospitalModel model) {
        this.model = model;

        frame = new JFrame("Nova Senha");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 230);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(10, 10, 80, 30);
        panel.add(nomeLabel);

        nomeTextField = new JTextField();
        nomeTextField.setBounds(100, 10, 200, 30);
        panel.add(nomeTextField);

        JLabel especialidadeLabel = new JLabel("Especialidade");
        especialidadeLabel.setBounds(10, 50, 80, 30);
        panel.add(especialidadeLabel);

        especialidadeComboBox = new JComboBox<>(new String[] {"Cardiologia", "Ortopedia", "Dermatologia", "Oftalmologia"});
        especialidadeComboBox.setBounds(100, 50, 200, 30);
        panel.add(especialidadeComboBox);

        JLabel idadeLabel = new JLabel("Idade");
        idadeLabel.setBounds(10, 90, 80, 30);
        panel.add(idadeLabel);

        idadeTextField = new JTextField();
        idadeTextField.setBounds(100, 90, 200, 30);
        panel.add(idadeTextField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(120, 150, 120, 30);
        panel.add(cadastrarButton);

        frame.getContentPane().add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }
    public void hide() {
        frame.setVisible(false);
    }

    public void addCadastrarButtonListener(ActionListener listener) {
        cadastrarButton.addActionListener(listener);
    }

    public String getNome() {
        return nomeTextField.getText();
    }

    public String getEspecialidade() {
        return (String) especialidadeComboBox.getSelectedItem();
    }

    public int getIdade() {
        String idadeText = idadeTextField.getText();
        int idade = 0;

        try {
            idade = Integer.parseInt(idadeText);
        } catch (NumberFormatException e) {
            // Caso a idade não seja um número válido, retorne 0 ou lance uma exceção adequada
        }

        return idade;
    }

    public boolean isPreferencial() {
        int idade = getIdade();
        return idade > 60;
    }

    public JFrame getFrame() {
        return frame;
    }
}
