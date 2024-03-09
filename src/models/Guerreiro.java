package models;

import java.util.Random;

public abstract class Guerreiro extends Classe {
    public Guerreiro() {
        super(14, 10, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 4, false, false));
        adicionarHabilidade(new Habilidade("Golpe de Espada", new PesosDeAtributos(0.7, 0.3, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 5, false, false));
        adicionarHabilidade(new Habilidade("Espada Flamejante", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(0.2, 0.0, 1.0), 7, false, false));
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        setAgilidade(getAgilidade() + 1);
        setInteligencia(getInteligencia() + 1);
        setForca(getForca() + 4);
    }

    public void atacarEquipeInimiga(Equipe equipeInimiga) {
        Personagem alvo = escolherAlvo(equipeInimiga);
        if (alvo != null) {
            int dano = calcularDano();
            alvo.setPV(alvo.getPV() - dano);
            if (alvo.getPV() <= 0) {
                System.out.println(alvo.getNome() + " foi derrotado!");
                equipeInimiga.removerPersonagem(alvo);
            }
        }
    }

    private Personagem escolherAlvo(Equipe equipeInimiga) {
        Random rand = new Random();
        int index = rand.nextInt(equipeInimiga.getMembros().size());
        return equipeInimiga.getMembros().get(index);
    }

    private int calcularDano() {
        return getForca() * 2;
    }
}
