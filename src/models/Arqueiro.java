package models;

import java.util.Random;

public abstract class Arqueiro extends Classe {
    public Arqueiro() {
        super(10, 14, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.5, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 3, false, false));
        adicionarHabilidade(new Habilidade("Atirar Flecha", new PesosDeAtributos(0.3, 0.5, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 4, false, false));
        adicionarHabilidade(new Habilidade("Flecha Encantada", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(0.0, 0.2, 1.0), 7, false, false));
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        setAgilidade(getAgilidade() + 3);
        setInteligencia(getInteligencia() + 2);
        setForca(getForca() + 1);
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
        return getAgilidade() * 2;
    }
}
