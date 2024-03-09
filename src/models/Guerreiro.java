package models;

public abstract class Guerreiro extends Classe {
    public Guerreiro() {
        super(14, 10, 12);
        this.adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 4, false, false));
        this.adicionarHabilidade(new Habilidade("Golpe de Espada", new PesosDeAtributos(0.7, 0.3, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 5, false, false));
        this.adicionarHabilidade(new Habilidade("Espada Flamejante", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(0.2, 0.0, 1.0), 7, false, false));
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        this.setAgilidade(this.getAgilidade() + 1);
        this.setInteligencia(this.getInteligencia() + 1);
        this.setForca(this.getForca() + 4);
    }

}
