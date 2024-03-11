package models;

import java.util.ArrayList;

public class Equipe {
    private ArrayList<Personagem> membros;

    public Equipe() {
        this.membros = new ArrayList<>();
    }

    public void adicionarPersonagem(Personagem personagem) {
        membros.add(personagem);
        personagem.setTempoEspera(0);
    }

    public void removerPersonagem(Personagem personagem) {
        membros.remove(personagem);
    }

    public Personagem buscarPersonagemPorID(int ID) {
        if (ID <= 0) {
            return null;
        }
        for (Personagem personagem : membros) {
            if (personagem.getID() == ID) {
                return personagem;
            }
        }
        return null;
    }

    public ArrayList<Personagem> getMembros() {
        return new ArrayList<>(membros);
    }

    public void computarPontosExperiencia(int experienciaGanha) {
        for (Personagem personagem : membros) {
            personagem.ganharExperiencia(experienciaGanha);
        }
    }

    public Personagem proximoAtacante() {
        Personagem proximo = null;
        int menorTempoEspera = Integer.MAX_VALUE;
        for (Personagem personagem : membros) {
            int tempoEspera = personagem.getTempoEspera();
            if (tempoEspera == 0 && personagem.getPV() > 0) {
                return personagem;
            } else if (tempoEspera < menorTempoEspera) {
                menorTempoEspera = tempoEspera;
                proximo = personagem;
            }
        }
        return proximo;
    }

    public void atualizarTempoEspera() {
        for (Personagem personagem : membros) {
            int tempoEspera = personagem.getTempoEspera();
            if (tempoEspera > 0) {
                personagem.setTempoEspera(tempoEspera - 1);
            }
        }
    }

    public boolean estaVazia() {
        return membros.isEmpty();
    }

}
