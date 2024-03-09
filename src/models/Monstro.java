package models;

import java.util.Random;

public abstract class Monstro extends Classe {
    public Monstro() {
        super(18, 8, 6);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.8, 0.4, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 5, false, false));
        adicionarHabilidade(new Habilidade("Chutar", new PesosDeAtributos(1.0, 0.5, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 8, false, false));
        adicionarHabilidade(new Habilidade("Grito Atordoante", new PesosDeAtributos(0.4, 0.2, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 6, true, true));
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        setAgilidade(getAgilidade() + 1);
        setInteligencia(getInteligencia() + 1);
        setForca(getForca() + 4);
    }

    public void atacarEquipeHerois(Equipe equipeHerois) {
        Personagem alvo = escolherAlvo(equipeHerois);
        if (alvo != null) {
            int dano = calcularDano();
            alvo.setPV(alvo.getPV() - dano);
            if (alvo.getPV() <= 0) {
                System.out.println(alvo.getNome() + " foi derrotado!");
                equipeHerois.removerPersonagem(alvo);
            }
        }
    }

    private Personagem escolherAlvo(Equipe equipeHerois) {
        Random rand = new Random();
        int index = rand.nextInt(equipeHerois.getMembros().size());
        return equipeHerois.getMembros().get(index);
    }

    private int calcularDano() {
        return getForca() * 2;
    }
}
