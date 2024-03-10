package models;

import java.util.Random;

public class Mago extends Classe {
    public Mago() {
        super(14, 10, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.1, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 2, false, false));
        adicionarHabilidade(new Habilidade("Enfraquecer", new PesosDeAtributos(0.3, 0.2, 0.5), new PesosDeAtributos(0.0, 0.0, 0.5), 5, false, false));
        adicionarHabilidade(new Habilidade("Cura Amigo", new PesosDeAtributos(0.5, 0.2, 0.8), new PesosDeAtributos(0.0, 0.0, 0.7), 4, false, true));
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        setAgilidade(getAgilidade() + 2);
        setInteligencia(getInteligencia() + 3);
        setForca(getForca() + 1);
    }

    @Override
    public String getNome() {
        return "Mago";
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

    public void curarAmigo(Equipe equipeAliada) {
        Personagem aliado = escolherAliado(equipeAliada);
        if (aliado != null) {
            int cura = calcularCura();
            aliado.setPV(aliado.getPV() + cura);
        }
    }

    private Personagem escolherAlvo(Equipe equipeInimiga) {
        Random rand = new Random();
        int index = rand.nextInt(equipeInimiga.getMembros().size());
        return equipeInimiga.getMembros().get(index);
    }

    private Personagem escolherAliado(Equipe equipeAliada) {
        Random rand = new Random();
        int index = rand.nextInt(equipeAliada.getMembros().size());
        return equipeAliada.getMembros().get(index);
    }

    private int calcularDano() {
        return getInteligencia() * 2;
    }

    private int calcularCura() {
        return getInteligencia() * 3;
    }
}
