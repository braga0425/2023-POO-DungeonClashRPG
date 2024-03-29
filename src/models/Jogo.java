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
            batalhar();

            //verificar o resultado da batalha
            if (equipeInimigos.getMembros().isEmpty()) {
                if (faseAtual < 1) {
                    faseAtual++;
                    cont = 1;
                } else {
                    System.out.println("Parabéns! Os heróis venceram todas as fases!\n");
                    break;
                }
            } else if (equipeHerois.getMembros().isEmpty()) {
                System.out.println("Os heróis foram derrotados na fase " + (faseAtual - 1 )+ "!\n");
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
            Personagem heroi = new Personagem(nome, 1, 0, 0, 0, classePersonagem);

            int PV = heroi.calcularPVMax();
            int PM = heroi.calcularPMMax();
            heroi.setPV(PV);
            heroi.setPM(PM);
            equipeHerois.adicionarPersonagem(heroi);
        }

    }

    private void carregarEquipesInimigos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/utils/jogo.txt"))) {
            String linha;
            boolean lerInimigos = true; //começar a ler os inimigos
            while ((linha = br.readLine()) != null) {

                if (!equipeHerois.getMembros().isEmpty()) {
                    if (linha.startsWith("fase")) {
                        System.out.println("\n" + linha.substring(5));
                        iniciarBatalha(); // Iniciar a batalha se não estivermos lendo inimigos pela primeira vez
                        lerInimigos = true; // Parar de ler os inimigos após encontrar a indicação de fase

                        faseAtual++;
                        continue;
                        }
                    } else {
                        break;
                    }

                    if (linha.isEmpty()) {
                        //pula linhas vazias
                        continue;
                    }

                    if (lerInimigos) {
                        //processar a linha como um inimigo somente se estamos lendo inimigos
                        String[] partes = linha.split(" ");
                        if (partes.length < 2) {
                            System.out.println("Linha inválida: \n" + linha);
                            continue;
                        }

                        String nome = partes[0];
                        String classe = partes[1];
                        int nivel = Integer.parseInt(partes[2]); //define o nível inicial como 1 para todos os inimigos
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
                        Personagem inimigo = new Personagem(nome, nivel, 0, 0, 0, classePersonagem);

                        int PV = inimigo.calcularPVMax();
                        int PM = inimigo.calcularPMMax();

                        inimigo.setPV(PV);
                        inimigo.setPM(PM);
                        equipeInimigos.adicionarPersonagem(inimigo);
                    }
                }

                //iniciar a última batalha após ler todos os inimigos
                if (lerInimigos) {
                    iniciarBatalha();
                }
            } catch(IOException e){
                System.err.println("Erro ao ler o arquivo jogo.txt: " + e.getMessage());
            }
        }

        private void iniciarBatalha () {
            if (!equipeHerois.getMembros().isEmpty()) {
                exibirInformacoesEquipes();
                System.out.println("\nComeça a batalha na fase " + faseAtual + "!");
                batalhar();
            }
        }

        private void exibirInformacoesEquipes () {
            if (!equipeHerois.getMembros().isEmpty()) {
                System.out.println("\nEquipe dos Heróis:\n");
                exibirInformacoesEquipe(equipeHerois);
                System.out.println("\nEquipe dos Inimigos:\n");
                exibirInformacoesEquipe(equipeInimigos);
                pegarPontosExperiencia();
            }
        }

        private void exibirInformacoesEquipe (Equipe equipe){
            ArrayList<Personagem> membros = equipe.getMembros();

            for (Personagem personagem : membros) {
                System.out.println("ID: " + personagem.getID() + " | Nome: " + personagem.getNome() + " | Classe: " +
                        personagem.getClasse().getNome() + " | PV: " + personagem.getPV() +
                        " | PM: " + personagem.getPM() + " | Nível: " + personagem.getNivel() + " | Tempo de espera: " + personagem.getTempoEspera());
            }
        }

        private void batalhar () {
            Equipe equipePrimeiroAtaque;
            Equipe equipeSegundoAtaque;
            String nomeEquipePrimeiroAtaque;
            String nomeEquipeSegundoAtaque;
            Random rand = new Random();
            if (rand.nextBoolean()) {
                equipePrimeiroAtaque = equipeHerois;
                equipeSegundoAtaque = equipeInimigos;
                nomeEquipePrimeiroAtaque = "Heróis";
                nomeEquipeSegundoAtaque = "Inimigos";
            } else {
                equipePrimeiroAtaque = equipeInimigos;
                equipeSegundoAtaque = equipeHerois;
                nomeEquipePrimeiroAtaque = "Inimigos";
                nomeEquipeSegundoAtaque = "Heróis";
            }

            while (!equipeHerois.getMembros().isEmpty() && !equipeInimigos.getMembros().isEmpty()) {
                System.out.println("\nTurno " + cont + ":");

                if (!equipeSegundoAtaque.getMembros().isEmpty()) {
                    System.out.println("\nEquipe dos " + nomeEquipePrimeiroAtaque + " ataca:");
                    ataqueAleatorio(equipePrimeiroAtaque, equipeSegundoAtaque);
                }

                //segunda equipe ataca
                if (!equipePrimeiroAtaque.getMembros().isEmpty() && !equipeSegundoAtaque.getMembros().isEmpty()) {
                    System.out.println("\nEquipe dos " + nomeEquipeSegundoAtaque + " ataca:");
                    ataqueAleatorio(equipeSegundoAtaque, equipePrimeiroAtaque);
                }

                proximoTurno();
                cont++;
            }
        }

    private Personagem sortearAlvo(Equipe equipe) {
        Random rand = new Random();
        int size = equipe.getMembros().size();
        if (size > 0) {
            int index = rand.nextInt(equipe.getMembros().size());
            return equipe.getMembros().get(index);
        } else {
            return null;
        }
    }

    private void ataqueAleatorio(Equipe equipeAtacante, Equipe equipeAlvo) {
        if (equipeAlvo.getMembros().isEmpty()) {
            return; //verifica se há membros na equipe alvo antes de realizar um ataque
        }
        for (Personagem atacante : equipeAtacante.getMembros()) {
            //verifica se o atacante está em tempo de espera
            if (atacante.getTempoEspera() > 0) {
                System.out.println("\n" + atacante.getNome() + " está em tempo de descanso. Não pode atacar neste turno.");
                continue;
            }

            for (Personagem atacante1 : equipeAtacante.getMembros()) {
                Personagem alvo = sortearAlvo(equipeAlvo);
                if (alvo != null) {
                    //exibir lista de habilidades e realizar ataque
                    realizarAtaque(atacante1, alvo);
                }
            }
        }
    }

    private void realizarAtaque(Personagem atacante, Personagem alvo) {
        Equipe equipeAtacante;
        Equipe equipeAlvo;

        //determinar as equipes do atacante e do alvo
        if (equipeHerois.getMembros().contains(atacante)) {
            equipeAtacante = equipeHerois;
            equipeAlvo = equipeInimigos;
        } else {
            equipeAtacante = equipeInimigos;
            equipeAlvo = equipeHerois;
        }

        //verificar se o atacante está em tempo de descanso
        if (atacante.getTempoEspera() > 0) {
            System.out.println(atacante.getNome() + " está em tempo de descanso. Não pode atacar neste turno.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int escolhaHabilidade;

        while (true) {
            System.out.println("\n" + atacante.getNome() + " está atacando " + alvo.getNome() + "!");
            System.out.println("\nSelecione a habilidade para " + atacante.getNome() + ":\n");
            for (int i = 0; i < atacante.getClasse().getHabilidades().size(); i++) {
                Habilidade habilidade = atacante.getClasse().getHabilidades().get(i);
                System.out.println((i + 1) + " - " + habilidade.getNome() +
                        " (PM: " + habilidade.getCustoPM(atacante) +
                        ", Dano: " + habilidade.calcularDano(atacante) +
                        ", Tempo de Espera: " + habilidade.getTempo() +
                        ", Afeta: " + (habilidade.isAfetaTodos() ? "Todos" : "Um alvo") + ")");
            }

            try {
                escolhaHabilidade = Integer.parseInt(scanner.nextLine());
                if (escolhaHabilidade <= 0 || escolhaHabilidade > atacante.getClasse().getHabilidades().size()) {
                    throw new NumberFormatException();
                }
                Habilidade habilidadeEscolhida = atacante.getClasse().getHabilidades().get(escolhaHabilidade - 1);
                if (atacante.getPM() >= habilidadeEscolhida.getCustoPM(atacante)) {
                    atacante.setPM(atacante.getPM() - habilidadeEscolhida.getCustoPM(atacante));
                    atacante.setTempoEspera(habilidadeEscolhida.getTempo());
                    int dano = habilidadeEscolhida.calcularDano(atacante);
                    if (!habilidadeEscolhida.isAfetaTodos() && !equipeAtacante.equals(equipeInimigos)) {
                        System.out.println("O ataque de " + atacante.getNome() + " causou " + dano + " de dano em " + alvo.getNome());
                    }
                    if (habilidadeEscolhida.isAfetaAmigos() && equipeAtacante.equals(equipeHerois)) {
                        System.out.println("\nSelecione o aliado para curar:\n");
                        for (int i = 0; i < equipeAtacante.getMembros().size(); i++) {
                            Personagem aliado = equipeAtacante.getMembros().get(i);
                            System.out.println((i + 1) + " - " + aliado.getNome() +
                                    " (PV: " + aliado.getPV() +
                                    ", PM: " + aliado.getPM() +
                                    ", Nível: " + aliado.getNivel() +
                                    ")");
                        }

                        int escolhaAliado = 0;
                        boolean habilidadeEscolhidaComSucesso = false;
                        do {
                            try {
                                escolhaAliado = Integer.parseInt(scanner.nextLine());
                                if (escolhaAliado <= 0 || escolhaAliado > equipeAtacante.getMembros().size()) {
                                    throw new NumberFormatException();
                                }
                                habilidadeEscolhidaComSucesso = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Escolha inválida. Digite um número entre 1 e " + equipeAtacante.getMembros().size() + ".\n");
                            }
                        } while (!habilidadeEscolhidaComSucesso);


                        //aplicar a cura ao aliado escolhido
                        Personagem aliadoCura = equipeAtacante.getMembros().get(escolhaAliado - 1);
                        int cura = habilidadeEscolhida.curaAliado(atacante);
                        aliadoCura.setPV(aliadoCura.getPV() + cura);
                        //exibir mensagem de cura
                        System.out.println("\n" + atacante.getNome() + " curou " + aliadoCura.getNome() + " em " + cura + " pontos de vida.\n");
                    } else if (habilidadeEscolhida.isAfetaTodos() && equipeAtacante.equals(equipeInimigos)) {
                        //aplicar a habilidade em todos os membros da equipe alvo
                        for (Personagem todosAlvo : equipeAlvo.getMembros()) {
                            todosAlvo.setPV(todosAlvo.getPV() - dano);
                            System.out.println("\n" + todosAlvo.getNome() + " sofreu " + dano + " pontos de dano!");
                            if (todosAlvo.getPV() <= 0) {
                                System.out.println("\n" + todosAlvo.getNome() + " foi derrotado!\n");
                                equipeAlvo.removerPersonagem(todosAlvo);
                            }
                        }
                    } else {
                        //aplicar o dano no alvo selecionado
                        alvo.setPV(alvo.getPV() - dano);
                        exibirInformacoesEquipes();
                        if (alvo.getPV() <= 0) {
                            System.out.println("\n" + alvo.getNome() + " foi derrotado!\n");
                            if (equipeAtacante.equals(equipeHerois)) {
                                distribuirPontosExperiencia(true); // Herói derrotou inimigo
                            } else {
                                distribuirPontosExperiencia(false); // Inimigo derrotou herói
                            }
                            equipeAlvo.removerPersonagem(alvo);
                        }
                    }
                    break;
                } else {
                    System.out.println("PM insuficiente para usar essa habilidade. Escolha outra opção.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Escolha inválida. Digite um número entre 1 e " + atacante.getClasse().getHabilidades().size() + ".\n");
            }
        }
    }

    private void proximoTurno() {
        equipeHerois.atualizarTempoEspera();
        equipeInimigos.atualizarTempoEspera();
    }

    private void distribuirPontosExperiencia(boolean heroiDerrotouInimigo) {
        int experienciaTotal = pegarPontosExperiencia();

        if (heroiDerrotouInimigo) {
            ArrayList<Personagem> heroisSobreviventes = equipeHerois.getMembros(); // Obtém os heróis sobreviventes
            equipeHerois.computarPontosExperiencia(experienciaTotal); // Distribui a experiência entre os heróis sobreviventes
            for (Personagem heroi : heroisSobreviventes) {
                System.out.println(heroi.getNome() + " ganhou " + experienciaTotal + " pontos de experiência!");
                heroi.setTempoEspera(0);
            }
        } else {
            ArrayList<Personagem> inimigosSobreviventes = equipeInimigos.getMembros(); // Obtém os inimigos sobreviventes
            equipeInimigos.computarPontosExperiencia(experienciaTotal); // Distribui a experiência entre os inimigos sobreviventes
            for (Personagem inimigo : inimigosSobreviventes) {
                System.out.println(inimigo.getNome() + " ganhou " + experienciaTotal + " pontos de experiência!");
                inimigo.setTempoEspera(0);
            }
        }
    }

    private int pegarPontosExperiencia() {
        int pontosExperiencia = 0;
        for (Personagem inimigoDerrotado : equipeInimigos.getMembros()) {
            pontosExperiencia = inimigoDerrotado.getNivel() * 5;
        }
        return pontosExperiencia;
    }

    private boolean faseConcluida() {
        return faseAtual > 2;
    }
}
