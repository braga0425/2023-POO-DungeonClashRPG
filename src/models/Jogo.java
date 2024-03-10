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
                System.out.println("Começa a batalha na fase " + faseAtual + "!\n");
                batalhar();

                // Verificar o resultado da batalha
                if (equipeInimigos.getMembros().isEmpty()) {
                    if (faseAtual < 2) {
                        faseAtual++;
                        cont = 1;
                    } else {
                        System.out.println("Parabéns! Os heróis venceram todas as fases!");
                        break;
                    }
                } else {
                    System.out.println("Os heróis foram derrotados na fase " + faseAtual + "!");
                    break;
                }
            }
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

        private void carregarEquipesInimigos() {
            try (BufferedReader br = new BufferedReader(new FileReader("src/utils/jogo.txt"))) {
                String linha;
                boolean lerInimigos = true; // Começar a ler os inimigos

                while ((linha = br.readLine()) != null) {
                    if (linha.startsWith("fase")) {
                        if (!lerInimigos) {
                            iniciarBatalha(); // Iniciar a batalha se não estivermos lendo inimigos pela primeira vez
                        }
                        System.out.println(linha.substring(5) + "\n");
                        faseAtual++;
                        lerInimigos = false; // Parar de ler os inimigos após encontrar a indicação de fase
                        continue;
                    }


    private void carregarEquipesInimigos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/utils/jogo.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("fase")) {
                    if (faseAtual > 1) {
                        continue;
                    }
                    System.out.println(linha.substring(5) + "\n");
                    faseAtual++;
                }

                if (linha.isEmpty()) {
                    // Pula linhas vazias
                    continue;
                }


                if (faseAtual == 1) {
                    String[] partes = linha.split(" ");
                    if (partes.length < 3) {
                        System.out.println("Linha inválida: " + linha);
                        continue;
                    }
                    
                    if (lerInimigos) {
                        // Processar a linha como um inimigo somente se estamos lendo inimigos
                        String[] partes = linha.split(" ");
                        if (partes.length < 2) {
                            System.out.println("Linha inválida: " + linha);
                            continue;
                        }

                        String nome = partes[0];
                        String classe = partes[1];
                        int nivel = 1; // Define o nível inicial como 1 para todos os inimigos
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
                                System.out.println("Classe inválida. Será atribuída a classe padrão Guerreiro para o inimigo " + nome);
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
            faseAtual = faseAtual - 1;
            System.out.println("Começa a batalha na fase " + faseAtual + "!\n");
            batalhar();
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
                System.out.println("Turno " + cont + ":\n");

                if (!equipeInimigos.getMembros().isEmpty()) {
                    System.out.println("Equipe dos Heróis ataca:\n");
                    ataqueAleatorio(equipeHerois, equipeInimigos);
                }

                if (!equipeHerois.getMembros().isEmpty() && !equipeInimigos.getMembros().isEmpty()) {
                    System.out.println("Equipe dos Inimigos ataca:\n");
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
                    System.out.println("Os heróis venceram a batalha!");
                    distribuirPontosExperiencia();
                    faseAtual++;
                    cont = 1;
                }
            } else {
                System.out.println("Os heróis foram derrotados!");
            }

            if (faseAtual < 2) {
                equipeInimigos = new Equipe(); // Reinicia a equipe de inimigos
                carregarEquipesInimigos();
            }
        }

        private Personagem sortearAlvo(Equipe equipe) {
            Random rand = new Random();
            int size = equipe.getMembros().size();
            if(size >0){
                int index = rand.nextInt(equipe.getMembros().size());
                return equipe.getMembros().get(index);
            } else {
                throw new IllegalArgumentException("A equipe está vazia, não é possível sortear um alvo.");
            }
        }
        private void ataqueAleatorio(Equipe equipeAtacante, Equipe equipeAlvo) {
            if (equipeAlvo.getMembros().isEmpty()) {
                return; // Verifica se há membros na equipe alvo antes de realizar um ataque
            }
            for (Personagem atacante : equipeAtacante.getMembros()) {
                Personagem alvo = sortearAlvo(equipeAlvo);
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

            System.out.println("Selecione a habilidade para " + atacante.getNome() + ":");
            for (int i = 0; i < atacante.getClasse().getHabilidades().size(); i++){
                System.out.println((i + 1) + " - " + atacante.getClasse().getHabilidades().get(i).getNome());
            }


            int escolhaHabilidade = 0;

            do {
                try {
                    escolhaHabilidade = Integer.parseInt(scanner.nextLine());
                    if (escolhaHabilidade <= 0 || escolhaHabilidade > atacante.getClasse().getHabilidades().size()) {
                        throw new NumberFormatException();
                    }
                    Personagem inimigo = new Personagem(nome, nivel, 0, PV, PM, classePersonagem);
                    equipeInimigos.adicionarPersonagem(inimigo);
                    cont++;
                }
            } while (escolhaHabilidade <= 0 || escolhaHabilidade > atacante.getClasse().getHabilidades().size());

            escolhaHabilidade --;

            Habilidade habilidadeEscolhida = atacante.getClasse().getHabilidades().get(escolhaHabilidade);

            if (atacante.getClasse() instanceof Mago && habilidadeEscolhida.isAfetaAmigos()) {
                // Mostra os membros da equipe para que o jogador escolha um aliado para curar
                System.out.println("Escolha o aliado para curar:");
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
                    System.out.println(atacante.getNome() + " curou " + aliadoCura.getNome() + " em " + cura + " pontos de vida.");
                } else {
                    System.out.println("Escolha de aliado inválida.");
                }
                } else if (atacante.getClasse() instanceof Monstro && habilidadeEscolhida.isAfetaTodos()){
                    ArrayList<Personagem> membrosEquipeAlvo = equipeAlvo.getMembros();
                    for (Personagem todosAlvo : membrosEquipeAlvo) {
                        int dano = habilidadeEscolhida.calcularDano(atacante);
                        todosAlvo.setPV(todosAlvo.getPV() - dano);
                        System.out.println(todosAlvo.getNome() + " sofreu " + dano + " pontos de dano!");
                        if (alvo.getPV() <= 0) {
                            System.out.println(todosAlvo.getNome() + " foi derrotado!");
                            equipeAlvo.removerPersonagem(alvo);
                        }
                    }
                exibirInformacoesEquipes();
            }else {
                int dano = habilidadeEscolhida.calcularDano(atacante);

                alvoAleatorio.setPV(alvoAleatorio.getPV() - dano);

                exibirInformacoesEquipes();
                if (alvoAleatorio.getPV() <= 0) {
                    System.out.println(alvoAleatorio.getNome() + " foi derrotado!");
                    equipeAlvo.removerPersonagem(alvoAleatorio);
                }
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
        while (!equipeHerois.getMembros().isEmpty() && !todosInimigosDerrotados()) {
            Personagem primeiroAtacante = sortearPrimeiroAtacante();
            System.out.println(primeiroAtacante.getNome() + " é o próximo a atacar!\n");
            atacar(primeiroAtacante);
            proximoTurno();
        }

        if (todosInimigosDerrotados()) {
            if (faseConcluida()) {
                carregarEquipesInimigos();
                exibirInformacoesEquipes();
                System.out.println("Começa a próxima batalha!\n");
            } else {
                System.out.println("Os heróis venceram a batalha!");
                distribuirPontosExperiencia();
            }
        } else {
            System.out.println("Os heróis foram derrotados!");
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
        Scanner scanner = new Scanner(System.in);
        Equipe equipeAlvo = equipeInimigos;
        if (atacante.getClasse() instanceof Guerreiro) {
            equipeAlvo = equipeHerois;
        }

        ArrayList<Personagem> membrosAlvo = equipeAlvo.getMembros();
        Random rand = new Random();
        int indexAlvo = rand.nextInt(membrosAlvo.size());
        Personagem alvo = membrosAlvo.get(indexAlvo);

        System.out.println("Selecione a habilidade para " + atacante.getNome() + ":");
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
                System.out.println("Escolha inválida. Digite um número entre 1 e " + atacante.getClasse().getHabilidades().size() + ".");
            }
        } while (escolhaHabilidade <= 0 || escolhaHabilidade > atacante.getClasse().getHabilidades().size());

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

    private boolean todosInimigosDerrotados() {
        return equipeInimigos.getMembros().isEmpty();
    }

    private boolean faseConcluida() {
        return faseAtual > 2;
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
    }
}
