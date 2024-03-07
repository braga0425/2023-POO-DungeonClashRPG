package models;

public abstract class Monstro extends Classe {
    public Monstro(){
        super(18, 8, 6);
        this.adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.8, 0.4, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 5, false, false));
        this.adicionarHabilidade(new Habilidade("Chutar", new PesosDeAtributos(1.0, 0.5, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 8, false, false));
        this.adicionarHabilidade(new Habilidade("Grito atordoante", new PesosDeAtributos(0.4, 0.2, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 6, true, true));
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        this.setAgilidade(this.getAgilidade()+1);
        this.setInteligencia(this.getInteligencia());
        this.setForca(this.getForca()+4);
    }
}
