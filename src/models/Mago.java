package models;

class Mago extends Classe {
    public Mago(int forca, int agilidade, int inteligencia) {
        super(forca, agilidade, inteligencia);
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.1, 0.1, 0), new PesosDeAtributos(0, 0, 0), 2, false, false));
        adicionarHabilidade(new Habilidade("Enfraquecer", new PesosDeAtributos(0.3, 0.2, 0.5), new PesosDeAtributos(0, 0, 0), 5, false, false));
        adicionarHabilidade(new Habilidade("Cura Amigo", new PesosDeAtributos(0.5, 0.2, 0.8), new PesosDeAtributos(0, 0, 0), 4, false, true));
    }
}
