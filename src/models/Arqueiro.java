package models;

public abstract class Arqueiro extends Classe {
    public Arqueiro() {
        super(10, 14, 12);
        this.adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.5, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 3, false, false));
        this.adicionarHabilidade(new Habilidade("Atirar Flecha", new PesosDeAtributos(0.3, 0.5, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 4, false, false));
        this.adicionarHabilidade(new Habilidade("Flecha Encantada", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(0.0, 0.2, 1.0), 7, false, false));
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        this.setAgilidade(this.getAgilidade() + 3);
        this.setInteligencia(this.getInteligencia() + 2);
        this.setForca(this.getForca() + 1);
    }

}