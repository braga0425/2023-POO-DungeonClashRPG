package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Equipe equipeHerois;
    private Equipe equipeInimigos;
    String nomeClasse;

    public Jogo() {
        equipeHerois = new Equipe();
        equipeInimigos = new Equipe();
    }

    public void iniciarJogo() {
        carregarEquipesHerois();
        carregarEquipesInimigos();
        exibirInformacoesEquipes();

        System.out.println("Começa a batalha!\n");
        batalhar();
    }

    private Arqueiro criarArqueiro() {
        return new Arqueiro() {
            @Override
            public void habilidadeEspecial(Personagem alvo) {

            }

        };
    }

    private Guerreiro criarGuerreiro() {
        return new Guerreiro() {
            @Override
            public void habilidadeEspecial(Personagem alvo) {

            }

        };
    }

    private Mago criarMago() {
        return new Mago() {
            @Override
            public void habilidadeEspecial(Personagem alvo) {

            }

        };
    }

    private Monstro criarMonstro() {
        return new Monstro() {
            @Override
            public void habilidadeEspecial(Personagem alvo) {

            }

        };
    }

    private void carregarEquipesHerois() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Digite o nome do herói " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            System.out.println("Escolha a classe do herói " + (i + 1) + " (Guerreiro, Arqueiro, Mago ou Monstro): ");
            String classe = scanner.nextLine();
            Classe classePersonagem;
            switch (classe.toLowerCase()) {
                case "guerreiro":
                    classePersonagem = criarGuerreiro();
                    break;
                case "arqueiro":
                    classePersonagem = criarArqueiro();
                    break;
                case "mago":
                    classePersonagem = criarMago();
                    break;
                case "monstro":
                    classePersonagem = criarMonstro();
                    break;
                default:
                    System.out.println("Classe inválida. Será atribuída a classe padrão Guerreiro.");
                    classePersonagem = criarGuerreiro();
            }
            Personagem heroi = new Personagem(nome, 1, 0, 100, 100, classePersonagem);
            equipeHerois.adicionarPersonagem(heroi);
        }
        scanner.close();
    }

    private void carregarEquipesInimigos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/utils/jogo.txt"))) {
            String linha;
            String faseDescricao = null;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("fase")) {
                    System.out.println("\n" + linha);
                    faseDescricao = br.readLine();
                    System.out.println(faseDescricao);
                    continue;
                }

                String[] partes = linha.split(" ");
                if (partes.length < 3) {
                    System.out.println("Linha inválida: " + linha);
                    continue;
                }

                String nome = partes[0];
                String classe = partes[1];
                int nivel = Integer.parseInt(partes[2]);
                int PV = 100;
                int PM = 100;
                Classe classePersonagem;
                switch (classe.toLowerCase()) {
                    case "guerreiro":
                        classePersonagem = criarGuerreiro();
                        break;
                    case "arqueiro":
                        classePersonagem = criarArqueiro();
                        break;
                    case "mago":
                        classePersonagem = criarMago();
                        break;
                    case "monstro":
                        classePersonagem = criarMonstro();
                        break;
                    default:
                        System.out.println("Classe inválida. Será atribuída a classe padrão Guerreiro para o inimigo " + nome);
                        classePersonagem = criarGuerreiro();
                }
                Personagem inimigo = new Personagem(nome, nivel, 0, PV, PM, classePersonagem);
                equipeInimigos.adicionarPersonagem(inimigo);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo jogo.txt: " + e.getMessage());
        }
    }


    private void exibirInformacoesEquipes() {
        System.out.println("Equipe dos Heróis:");
        exibirInformacoesEquipe(equipeHerois);
        System.out.println("\nEquipe dos Inimigos:");
        exibirInformacoesEquipe(equipeInimigos);
    }

    private void exibirInformacoesEquipe(Equipe equipe) {
        ArrayList<Personagem> membros = equipe.getMembros();

        for (Personagem personagem : membros) {
            System.out.println("ID: " + personagem.getID() + " | Nome: " + personagem.getNome() + " | Classe: " +
                    personagem.getClasse().getNome() + " | PV: " + personagem.getPV() +
                    " | PM: " + personagem.getPM() + " | Nível: " + personagem.getNivel() +
                    " | Tempo de Espera: " + personagem.getTempoEspera());
        }
    }

    private void batalhar() {
        while (!equipeHerois.getMembros().isEmpty() && !equipeInimigos.getMembros().isEmpty()) {
            Personagem primeiroAtacante = sortearPrimeiroAtacante();
            System.out.println(primeiroAtacante.getNome() + " é o próximo a atacar!\n");
            atacar(primeiroAtacante);
            proximoTurno();
        }
        if (equipeHerois.getMembros().isEmpty()) {
            System.out.println("Os heróis foram derrotados!");
        } else {
            System.out.println("Os heróis venceram a batalha!");
            distribuirPontosExperiencia();
        }
    }

    private Personagem sortearPrimeiroAtacante() {
        Random rand = new Random();
        int index = rand.nextInt(equipeHerois.getMembros().size() + equipeInimigos.getMembros().size());
        if (index < equipeHerois.getMembros().size()) {
            return equipeHerois.getMembros().get(index);
        } else {
            return equipeInimigos.getMembros().get(index - equipeHerois.getMembros().size());
        }
    }

    private void atacar(Personagem atacante) {
        Equipe equipeAlvo = equipeInimigos;
        if (atacante.getClasse() instanceof Guerreiro) {
            equipeAlvo = equipeHerois;
        }

        ArrayList<Personagem> membrosAlvo = equipeAlvo.getMembros();
        Random rand = new Random();
        int indexAlvo = rand.nextInt(membrosAlvo.size());
        Personagem alvo = membrosAlvo.get(indexAlvo);

        int dano = atacante.getClasse().atacar(alvo);

        alvo.setPV(alvo.getPV() - dano);

        if (alvo.getPV() <= 0) {
            System.out.println(alvo.getNome() + " foi derrotado!");
            equipeAlvo.removerPersonagem(alvo);
        }
    }

    private void proximoTurno() {
        equipeHerois.atualizarTempoEspera();
        equipeInimigos.atualizarTempoEspera();
    }

    private void distribuirPontosExperiencia() {
        int pontosExperiencia = 0;
        for (Personagem inimigoDerrotado : equipeInimigos.getMembros()) {
            pontosExperiencia += inimigoDerrotado.getNivel() * 10;
        }

        ArrayList<Personagem> heroisSobreviventes = equipeHerois.getMembros();
        int pontosPorHeroi = pontosExperiencia / heroisSobreviventes.size();
        for (Personagem heroi : heroisSobreviventes) {
            heroi.setExperiencia(heroi.getExperiencia() + pontosPorHeroi);
            System.out.println(heroi.getNome() + " ganhou " + pontosPorHeroi + " pontos de experiência!");
        }
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
    }
}
