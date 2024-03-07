package models;

import java.util.ArrayList;
import java.util.Random;

public class Equipe {
    private ArrayList<Personagem> membros;

    public Equipe() {
        this.membros = new ArrayList<>();
    }

    public void adicionarPersonagem(Personagem personagem) {
        membros.add(personagem);
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
        Random rand = new Random();
        int index = rand.nextInt(membros.size());
        return membros.get(index);
    }

    public void atualizarTempoEspera() {
        for (Personagem personagem : membros) {
            int tempoEspera = personagem.getTempoEspera();
            if (tempoEspera > 0) {
                personagem.setTempoEspera(tempoEspera - 1);
            }
        }
    }
}
