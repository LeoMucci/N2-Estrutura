package model;

import java.util.LinkedList;
import java.util.Queue;

public class HospitalModel {
    private Queue<Senha> filaSenhas;
    private int senhaPreferencial;
    private int senhaNormal;
    private int contadorPacientesNormais;

    public HospitalModel() {
        filaSenhas = new LinkedList<>();
        senhaPreferencial = 1;
        senhaNormal = 1;
        contadorPacientesNormais = 0;
    }
    

    public String adicionarSenha(String nome, String especialidade, boolean preferencial) {
        String senha = "";

        if (preferencial) {
            senha = "P" + String.format("%02d", senhaPreferencial);
            senhaPreferencial++;
        } else {
            senha = "N" + String.format("%02d", senhaNormal);
            senhaNormal++;
            contadorPacientesNormais++;
        }

        Senha novaSenha = new Senha(nome, especialidade, senha);
        filaSenhas.offer(novaSenha);
        return senha;
    }

    public String chamarProximaSenha() {
        if (filaSenhas.isEmpty()) {
            return null; // Retorna null se a fila de senhas estiver vazia
        }

        Senha senha = filaSenhas.poll();

        if (senha.getTipo().startsWith("N") && filaSenhas.size() % 3 == 0) {
            Senha senhaPreferencial = filaSenhas.poll();
            if (senhaPreferencial != null) {
                filaSenhas.offer(senhaPreferencial);
            }
        }

        return senha.getSenha();
    }


    public String obterProximaSenhaChamada() {
        if (!filaSenhas.isEmpty()) {
            Senha senha = filaSenhas.peek();
            return senha.getSenha();
        }
        return null;
    }
    
    public int getNumeroSenhasNormaisChamadas() {
        return contadorPacientesNormais;
    }
    
    public String obterNomeDaSenha(String senha) {
        for (Senha s : filaSenhas) {
            if (s.getSenha().equals(senha)) {
                return s.getNome();
            }
        }
        return null;
    }
    
    public String obterEspecialidadeDaSenha(String senha) {
        for (Senha s : filaSenhas) {
            if (s.getSenha().equals(senha)) {
                return s.getEspecialidade();
            }
        }
        return null;
    }
    
    public int getPosicaoNaFila(String senhaAtual) {
        int posicao = 1;

        for (Senha senha : filaSenhas) {
            if (senha.getSenha().equals(senhaAtual)) {
                return posicao;
            }
            posicao++;
        }

        return -1; // Retorna -1 se a senha atual não estiver na fila
    }


    // Classe interna para representar a senha com informações adicionais
    public class Senha {
        private String nome;
        private String especialidade;
        private String senha;

        public Senha(String nome, String especialidade, String senha) {
            this.nome = nome;
            this.especialidade = especialidade;
            this.senha = senha;
        }

        public String getNome() {
            return nome;
        }

        public String getEspecialidade() {
            return especialidade;
        }

        public String getSenha() {
            return senha;
        }

        public String getTipo() {
            return senha.substring(0, 1);
        }
    }
    
    public boolean existeSenha(String senha) {
        for (Senha s : filaSenhas) {
            if (s.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public int getTamanhoFila() {
        return filaSenhas.size();
    }

    public boolean filaVazia() {
        return filaSenhas.isEmpty();
    }


	public String[] obterInformacoesSenha(String senha) {
		String nome = obterNomeDaSenha(senha);
	    String especialidade = obterEspecialidadeDaSenha(senha);
	    String tipoAtendimento = senha.startsWith("P") ? "Preferencial" : "Normal";

	    return new String[]{senha, nome, especialidade, tipoAtendimento};
	}

    
}
