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

    private int faseAtual = 1;
    private int cont = 1;

    public Jogo() {
        equipeHerois = new Equipe();
        equipeInimigos = new Equipe();
    }

    public void iniciarJogo() {
        carregarEquipesHerois();

        while (!equipeHerois.getMembros().isEmpty()) {
            carregarEquipesInimigos();
            exibirInformacoesEquipes();
            System.out.println("\nComeça a batalha na fase " + faseAtual + "!");
            batalhar();

            // Verificar o resultado da batalha
            if (equipeInimigos.getMembros().isEmpty()) {
                if (faseAtual < 1) {
                    faseAtual++;
                    cont = 1;
                } else {
                    System.out.println("Parabéns! Os heróis venceram todas as fases!\n");
                    break;
                }
            } else {
                System.out.println("Os heróis foram derrotados na fase " + faseAtual + "!\n");
                break;
            }
        }
    }


    private void carregarEquipesHerois() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("\nDigite o nome do herói " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            System.out.println("\nEscolha a classe do herói " + (i + 1) + " (Guerreiro, Arqueiro, Mago ou Monstro): ");
            String classe = scanner.nextLine();
            Classe classePersonagem;
            switch (classe.toLowerCase()) {
                case "guerreiro":
                    classePersonagem = new Guerreiro();
                    break;
                case "arqueiro":
                    classePersonagem = new Arqueiro();
                    break;
                case "mago":
                    classePersonagem = new Mago();
                    break;
                case "monstro":
                    classePersonagem = new Monstro();
                    break;
                default:
                    System.out.println("Classe inválida. Será atribuída a classe padrão Guerreiro.");
                    classePersonagem = new Guerreiro();
            }
            Personagem heroi = new Personagem(nome, 1, 0, 100, 100, classePersonagem);
            equipeHerois.adicionarPersonagem(heroi);
        }

    }

    private void carregarEquipesInimigos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/utils/jogo.txt"))) {
            String linha;
            boolean lerInimigos = true; // Começar a ler os inimigos
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("fase")) {
                    System.out.println("\n" + linha.substring(5));
                    iniciarBatalha(); // Iniciar a batalha se não estivermos lendo inimigos pela primeira vez
                    lerInimigos = true; // Parar de ler os inimigos após encontrar a indicação de fase

                    faseAtual++;
                    continue;
                }

                if (linha.isEmpty()) {
                    // Pula linhas vazias
                    continue;
                }

                if (lerInimigos) {
                    // Processar a linha como um inimigo somente se estamos lendo inimigos
                    String[] partes = linha.split(" ");
                    if (partes.length < 2) {
                        System.out.println("Linha inválida: \n" + linha);
                        continue;
                    }

                    String nome = partes[0];
                    String classe = partes[1];
                    int nivel = Integer.parseInt(partes[2]); // Define o nível inicial como 1 para todos os inimigos
                    int PV = 100;
                    int PM = 100;
                    Classe classePersonagem;
                    switch (classe.toLowerCase()) {
                        case "guerreiro":
                            classePersonagem = new Guerreiro();
                            break;
                        case "arqueiro":
                            classePersonagem = new Arqueiro();
                            break;
                        case "mago":
                            classePersonagem = new Mago();
                            break;
                        case "monstro":
                            classePersonagem = new Monstro();
                            break;
                        default:
                            System.out.println("Classe inválida. Será atribuída a classe padrão Guerreiro para o inimigo. \n" + nome);
                            classePersonagem = new Guerreiro();
                    }
                    Personagem inimigo = new Personagem(nome, nivel, 0, PV, PM, classePersonagem);
                    equipeInimigos.adicionarPersonagem(inimigo);
                }
            }

            // Iniciar a última batalha após ler todos os inimigos
            if (lerInimigos) {
                iniciarBatalha();
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo jogo.txt: " + e.getMessage());
        }
    }

    private void iniciarBatalha() {
        exibirInformacoesEquipes();
        System.out.println("\nComeça a batalha na fase " + faseAtual + "!");
        batalhar();
    }

    private void exibirInformacoesEquipes() {
        System.out.println("\nEquipe dos Heróis:\n");
        exibirInformacoesEquipe(equipeHerois);
        System.out.println("\nEquipe dos Inimigos:\n");
        exibirInformacoesEquipe(equipeInimigos);
        pegarPontosExperiencia();
    }

    private void exibirInformacoesEquipe(Equipe equipe) {
        ArrayList<Personagem> membros = equipe.getMembros();

        for (Personagem personagem : membros) {
            System.out.println("ID: " + personagem.getID() + " | Nome: " + personagem.getNome() + " | Classe: " +
                    personagem.getClasse().getNome() + " | PV: " + personagem.getPV() +
                    " | PM: " + personagem.getPM() + " | Nível: " + personagem.getNivel());
        }
    }

    private void batalhar() {
        while (!equipeHerois.getMembros().isEmpty() && !equipeInimigos.getMembros().isEmpty()) {
            System.out.println("\nTurno " + cont + ":");

            if (!equipeInimigos.getMembros().isEmpty()) {
                System.out.println("\nEquipe dos Heróis ataca:");
                ataqueAleatorio(equipeHerois, equipeInimigos);
            }

            if (!equipeHerois.getMembros().isEmpty() && !equipeInimigos.getMembros().isEmpty()) {
                System.out.println("\nEquipe dos Inimigos ataca:");
                ataqueAleatorio(equipeInimigos, equipeHerois);
            }

            proximoTurno();
            cont++;
        }

        // Verificar o resultado da batalha
        if (equipeInimigos.getMembros().isEmpty()) {
            if (faseConcluida()) {
                faseAtual++;
                cont = 1;
            } else {
            System.out.println("Os heróis venceram a batalha!\n");
            faseAtual++;
            cont = 1;
            }
        } else {
            System.out.println("Os heróis foram derrotados!\n");
        }

        //if (faseAtual < 2) {
          //  equipeInimigos = new Equipe(); // Reinicia a equipe de inimigos
     //   }
    }

    private Personagem sortearAlvo(Equipe equipe) {
        Random rand = new Random();
        int size = equipe.getMembros().size();
        if (size > 0) {
            int index = rand.nextInt(equipe.getMembros().size());
            return equipe.getMembros().get(index);
        } else {
            //System.out.println("A equipe está vazia, não é possível sortear um alvo.");
            return null;
        }
    }

    private void ataqueAleatorio(Equipe equipeAtacante, Equipe equipeAlvo) {
        if (equipeAlvo.getMembros().isEmpty()) {
            return; // Verifica se há membros na equipe alvo antes de realizar um ataque
        }
        for (Personagem atacante : equipeAtacante.getMembros()) {
            Personagem alvo = sortearAlvo(equipeAlvo);
            if (alvo != null)
                atacar(atacante, alvo);
        }
    }

    private void atacar(Personagem atacante, Personagem alvo) {
        Equipe equipeAtacante;
        Equipe equipeAlvo;

        if (equipeHerois.getMembros().contains(atacante)) {
            equipeAtacante = equipeHerois;
            equipeAlvo = equipeInimigos;
        } else {
            equipeAtacante = equipeInimigos;
            equipeAlvo = equipeHerois;
        }

        Scanner scanner = new Scanner(System.in);
        if (atacante.getClasse() instanceof Monstro) {
            equipeAlvo = equipeHerois;
        }

        ArrayList<Personagem> membrosAlvo = equipeAlvo.getMembros();
        Random rand = new Random();
        int indexAlvo = rand.nextInt(membrosAlvo.size());
        Personagem alvoAleatorio = membrosAlvo.get(indexAlvo);

        System.out.println("\nSelecione a habilidade para " + atacante.getNome() + ":\n");
        for (int i = 0; i < atacante.getClasse().getHabilidades().size(); i++) {
            System.out.println((i + 1) + " - " + atacante.getClasse().getHabilidades().get(i).getNome());
        }


        int escolhaHabilidade = 0;

        do {
            try {
                escolhaHabilidade = Integer.parseInt(scanner.nextLine());
                if (escolhaHabilidade <= 0 || escolhaHabilidade > atacante.getClasse().getHabilidades().size()) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Escolha inválida. Digite um número entre 1 e " + atacante.getClasse().getHabilidades().size() + ".\n");
            }
        } while (escolhaHabilidade <= 0 || escolhaHabilidade > atacante.getClasse().getHabilidades().size());

        escolhaHabilidade--;

        Habilidade habilidadeEscolhida = atacante.getClasse().getHabilidades().get(escolhaHabilidade);

        if (atacante.getClasse() instanceof Mago && habilidadeEscolhida.isAfetaAmigos()) {
            // Mostra os membros da equipe para que o jogador escolha um aliado para curar
            System.out.println("Escolha o aliado para curar:\n");
            for (int i = 0; i < equipeAtacante.getMembros().size(); i++) {
                Personagem aliado = equipeAtacante.getMembros().get(i);
                System.out.println((i + 1) + " - " + aliado.getNome());
            }
            int escolhaAliado = Integer.parseInt(scanner.nextLine()) - 1;

            // Verifica se a escolha do aliado é válida
            if (escolhaAliado >= 0 && escolhaAliado < equipeAtacante.getMembros().size()) {
                Personagem aliadoCura = equipeAtacante.getMembros().get(escolhaAliado);

                // Aplica a cura ao aliado escolhido
                int cura = habilidadeEscolhida.curaAliado(atacante);
                aliadoCura.setPV(aliadoCura.getPV() + cura);

                // Exibe mensagem de cura
                System.out.println(atacante.getNome() + " curou " + aliadoCura.getNome() + " em " + cura + " pontos de vida.\n");
            } else {
                System.out.println("Escolha de aliado inválida.\n");
            }
        } else if (atacante.getClasse() instanceof Monstro && habilidadeEscolhida.isAfetaTodos()) {
            ArrayList<Personagem> membrosEquipeAlvo = equipeAlvo.getMembros();
            for (Personagem todosAlvo : membrosEquipeAlvo) {
                int dano = habilidadeEscolhida.calcularDano(atacante);
                todosAlvo.setPV(todosAlvo.getPV() - dano);
                System.out.println(todosAlvo.getNome() + " sofreu " + dano + " pontos de dano!\n");
                if (alvo.getPV() <= 0) {
                    System.out.println("\n" + todosAlvo.getNome() + " foi derrotado!\n");
                    equipeAlvo.removerPersonagem(alvo);
                }
            }
            exibirInformacoesEquipes();
        } else {
            int dano = habilidadeEscolhida.calcularDano(atacante);

            alvoAleatorio.setPV(alvoAleatorio.getPV() - dano);

            exibirInformacoesEquipes();
            if (alvoAleatorio.getPV() <= 0) {
                System.out.println("\n" + alvoAleatorio.getNome() + " foi derrotado!\n");
                distribuirPontosExperiencia();
                equipeAlvo.removerPersonagem(alvoAleatorio);
            }
        }
    }

    private void proximoTurno() {
        equipeHerois.atualizarTempoEspera();
        equipeInimigos.atualizarTempoEspera();
    }

    private void distribuirPontosExperiencia() {
        int experienciaTotal = pegarPontosExperiencia();
        ArrayList<Personagem> heroisSobreviventes = equipeHerois.getMembros();
        equipeHerois.computarPontosExperiencia(experienciaTotal);
        for (Personagem heroi : heroisSobreviventes) {
            System.out.println(heroi.getNome()+ " ganhou " + experienciaTotal + " pontos de experiência!");
        }
    }

    private int pegarPontosExperiencia(){
        int pontosExperiencia = 0;
        for (Personagem inimigoDerrotado : equipeInimigos.getMembros()) {
            pontosExperiencia = inimigoDerrotado.getNivel() * 5;
        }
        return pontosExperiencia;
    }

    private boolean todosInimigosDerrotados() {
        return equipeInimigos.getMembros().isEmpty();
    }

    private boolean faseConcluida() {
        return faseAtual > 2;
    }
}
