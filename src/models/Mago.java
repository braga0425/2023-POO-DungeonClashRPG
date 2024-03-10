package models;

import java.util.Random;

public class Mago extends Classe {
    public Mago() {
        super(14, 10, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.1, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 2, false, false){
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.1 + personagem.getClasse().getForca() * 0.1));
            }
        });
        adicionarHabilidade(new Habilidade("Enfraquecer", new PesosDeAtributos(0.3, 0.2, 0.5), new PesosDeAtributos(0.0, 0.0, 0.5), 5, false, false){
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.2 + personagem.getClasse().getForca() * 0.3 + personagem.getClasse().getInteligencia() * 0.5));
            }
        });
        adicionarHabilidade(new Habilidade("Cura Amigo", new PesosDeAtributos(0.5, 0.2, 0.8), new PesosDeAtributos(0.0, 0.0, 0.7), 4, false, true){
            @Override
            public int curaAliado(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.2 + personagem.getClasse().getForca() * 0.5 + personagem.getClasse().getInteligencia() * 0.8));
            }
            @Override
            public int getPontosMagia(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getInteligencia() * 0.7));
            }
        });
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

}
