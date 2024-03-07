package models;

class Guerreiro extends Classe {
    public Guerreiro(int forca, int agilidade, int inteligencia) {
        super(forca, agilidade, inteligencia);
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 0), new PesosDeAtributos(0, 0, 0), 4, false, false));
        adicionarHabilidade(new Habilidade("Golpe de espada", new PesosDeAtributos(0.7, 0.3, 0), new PesosDeAtributos(0, 0, 0), 5, false, false));
        adicionarHabilidade(new Habilidade("Espada Flamejante", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(0, 0, 0), 7, false, false));
    }
}
