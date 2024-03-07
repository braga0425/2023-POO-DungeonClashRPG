package models;

class Monstro extends Classe {
    public Monstro(int forca, int agilidade, int inteligencia) {
        super(forca, agilidade, inteligencia);
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.8, 0.4, 0), new PesosDeAtributos(0, 0, 0), 5, false, false));
        adicionarHabilidade(new Habilidade("Chutar", new PesosDeAtributos(1.0, 0.5, 0), new PesosDeAtributos(0, 0, 0), 8, false, false));
        adicionarHabilidade(new Habilidade("Grito atordoante", new PesosDeAtributos(0.4, 0.2, 0), new PesosDeAtributos(0, 0, 0), 6, true, false));
    }
}
