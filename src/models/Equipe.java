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

    public ArrayList<Personagem> getMembros() {
        return new ArrayList<>(membros);
    }

    public void computarPontosExperiencia(int experienciaGanha) {
        for (Personagem personagem : membros) {
            personagem.ganharExperiencia(experienciaGanha);
        }
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
