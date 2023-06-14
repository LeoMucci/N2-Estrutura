package controller;

import model.HospitalModel;
import view.CadastrarSenhaView;
import view.PainelAtendimentoView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelAtendimentoController {
    private HospitalModel model;
    private PainelAtendimentoView view;
    
    public void atualizarTabelaSenhaAtual() {
        String senhaAtual = model.obterProximaSenhaChamada();
        if (senhaAtual != null) {
            String nome = model.obterNomeDaSenha(senhaAtual);
            String especialidade = model.obterEspecialidadeDaSenha(senhaAtual);
            String tipoAtendimento = senhaAtual.startsWith("P") ? "Preferencial" : "Normal";
            view.updateSenhaAtualTable(senhaAtual, nome, especialidade, tipoAtendimento);
        } else {
            view.removerSenhaAtualTable();
        }
    }

    public PainelAtendimentoController(HospitalModel model, PainelAtendimentoView view) {
        this.model = model;
        this.view = view;

        view.addChamarSenhaButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verifica se há senha atualmente em atendimento
                String senhaAtual = view.getSenhaAtual();
                if (senhaAtual != null) {
                    String nomeSenhaAtual = model.obterNomeDaSenha(senhaAtual);
                    String especialidadeSenhaAtual = model.obterEspecialidadeDaSenha(senhaAtual);
                    String tipoAtendimentoSenhaAtual = senhaAtual.startsWith("P") ? "Preferencial" : "Normal";

                    // Adicionar a senha atual na tabela de senhas atuais
                    view.updateSenhaAtualTable(senhaAtual, nomeSenhaAtual, especialidadeSenhaAtual, tipoAtendimentoSenhaAtual);

                    // Mover a senha anterior para o histórico
                    view.updateHistoricoTable(senhaAtual, nomeSenhaAtual, especialidadeSenhaAtual, tipoAtendimentoSenhaAtual);
                }

                String proximaSenha = model.chamarProximaSenha();
                if (proximaSenha != null) {
                    String nome = model.obterNomeDaSenha(proximaSenha);
                    String especialidade = model.obterEspecialidadeDaSenha(proximaSenha);
                    String tipoAtendimento = proximaSenha.startsWith("P") ? "Preferencial" : "Normal";
                    view.updateSenhaAtualTable(proximaSenha, nome, especialidade, tipoAtendimento);
                } else {
                    JOptionPane.showMessageDialog(view.getFrame(), "Não há mais senhas disponíveis.", "Chamar Senha",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        view.addVerificarPosicaoButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String senhaAtual = JOptionPane.showInputDialog(view.getFrame(), "Digite sua senha atual:");
                int posicao = model.getPosicaoNaFila(senhaAtual);
                if (posicao != -1) {
                    JOptionPane.showMessageDialog(view.getFrame(), "Sua posição na fila é: " + posicao, "Verificar Posição na Fila",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view.getFrame(), "A senha atual não está na fila.", "Verificar Posição na Fila",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        view.addCadastrarSenhaButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CadastrarSenhaView cadastrarSenhaView = new CadastrarSenhaView(model);
                CadastrarSenhaController cadastrarSenhaController = new CadastrarSenhaController(model, cadastrarSenhaView, view);

                cadastrarSenhaView.show();
            }
        });
    }
}
