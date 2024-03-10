package models;

import java.util.Random;

public class Monstro extends Classe {
    public Monstro() {
        super(18, 8, 6);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.8, 0.4, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 5, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.4 + personagem.getClasse().getForca() * 0.8));
            }
        });
        adicionarHabilidade(new Habilidade("Chutar", new PesosDeAtributos(1.0, 0.5, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 8, false, false) {
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.5 + personagem.getClasse().getForca() * 1.0));
            }
        });
        adicionarHabilidade(new Habilidade("Grito Atordoante", new PesosDeAtributos(0.4, 0.2, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 6, true, true) {
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.2 + personagem.getClasse().getForca() * 0.4));
            }
        });
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        setAgilidade(getAgilidade() + 1);
        setInteligencia(getInteligencia() + 1);
        setForca(getForca() + 4);
    }

    @Override
    public String getNome() {
        return "Monstro";
    }
}