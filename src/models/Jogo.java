package models;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private Equipe equipeHerois;
    private Equipe equipeInimigos;

    public Jogo() {
        equipeHerois = new Equipe();
        equipeInimigos = new Equipe();
    }

    // Método para iniciar o jogo
    public void iniciarJogo() {
        carregarEquipes(); // Carrega os personagens nas equipes
        batalhar(); // Inicia a batalha
    }

    // Método para carregar os personagens nas equipes
    private void carregarEquipes() {
        // Lógica para carregar os personagens nas equipes a partir do arquivo jogo.txt
        // Este método pode ler o arquivo jogo.txt e criar os personagens e equipes correspondentes
    }

    // Método para iniciar a batalha
    private void batalhar() {
        // Lógica para gerenciar a batalha entre as equipes
        // Este método pode controlar os turnos, ataques, atualização de status, etc.
    }

    // Método principal que pede entrada e exibe mensagens na tela
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
    }
}

