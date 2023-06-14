package controller;

import model.HospitalModel;
import view.CadastrarSenhaView;
import view.PainelAtendimentoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarSenhaController {
    private HospitalModel model;
    private CadastrarSenhaView view;
    private PainelAtendimentoView painelAtendimentoView;

    public CadastrarSenhaController(HospitalModel model, CadastrarSenhaView view, PainelAtendimentoView painelAtendimentoView) {
        this.model = model;
        this.view = view;
        this.painelAtendimentoView = painelAtendimentoView;

        view.addCadastrarButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = view.getNome();
                String especialidade = view.getEspecialidade();
                int idade = view.getIdade();

                boolean preferencial = idade > 60;

                String senha = model.adicionarSenha(nome, especialidade, preferencial);

                if (senha != null) {
                    // Obtém o nome e a especialidade da senha
                    String senhaCompleta = "Nome: " + nome + "\nEspecialidade: " + especialidade + "\nSenha: " + senha;
                    JOptionPane.showMessageDialog(view.getFrame(), "Senha cadastrada com sucesso!\n" + senhaCompleta,
                            "Cadastro de Senha", JOptionPane.INFORMATION_MESSAGE);
                    view.close();
                } else {
                    JOptionPane.showMessageDialog(view.getFrame(), "Erro ao cadastrar a senha. Por favor, tente novamente.",
                            "Cadastro de Senha", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    
    
}
