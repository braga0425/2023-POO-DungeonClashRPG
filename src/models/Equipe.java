package models;

import java.util.ArrayList;
import java.util.Random;

public class Equipe {
    private ArrayList<Personagem> membros;

    // Construtor
    public Equipe() {
        this.membros = new ArrayList<>();
    }

    // Método para adicionar um personagem à equipe
    public void adicionarPersonagem(Personagem personagem) {
        membros.add(personagem);
    }

    // Método para buscar um personagem na equipe pelo ID
    public Personagem buscarPersonagemPorID(int ID) {
        for (Personagem personagem : membros) {
            if (personagem.getID() == ID) {
                return personagem;
            }
        }
        return null; // Retorna null se o personagem não for encontrado
    }

    // Método para retornar a equipe (coleção)
    public ArrayList<Personagem> getMembros() {
        return membros;
    }

    // Método para computar pontos de experiência para a equipe
    public void computarPontosExperiencia(int experienciaGanha) {
        for (Personagem personagem : membros) {
            personagem.ganharExperiencia(experienciaGanha);
        }
    }

    // Método para determinar o próximo atacante aleatoriamente
    public Personagem proximoAtacante() {
        Random rand = new Random();
        int index = rand.nextInt(membros.size());
        return membros.get(index);
    }

    // Método para atualizar o tempo de espera da equipe
    public void atualizarTempoEspera() {
        for (Personagem personagem : membros) {
            if (personagem.getTempoEspera() > 0) {
                personagem.setTempoEspera(personagem.getTempoEspera() - 1);
            }
        }
    }
}

