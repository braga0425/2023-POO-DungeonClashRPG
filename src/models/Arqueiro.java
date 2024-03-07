package models;

public abstract class Arqueiro extends Classe{
    public Arqueiro(int forca, int agilidade, int inteligencia) {
        super(forca, agilidade, inteligencia);
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0, 0.1, 0.3), new PesosDeAtributos(0, 0, 0), 3, false, false));
        adicionarHabilidade(new Habilidade("Atirar flecha", new PesosDeAtributos(0.3, 0.5, 0), new PesosDeAtributos(0, 0, 0), 4, false, false));
        adicionarHabilidade(new Habilidade("Flecha Encantada", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(0, 0, 0), 7, false, false));
    }
}