package models;
import java.util.ArrayList;

public abstract class Classe {
    private int forca;
    private int agilidade;
    private int inteligencia;
    private ArrayList<Habilidade> habilidades;

    public Classe(int forca, int agilidade, int inteligencia) {
        this.forca = forca;
        this.agilidade = agilidade;
        this.inteligencia = inteligencia;
        this.habilidades = new ArrayList<>();
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public ArrayList<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public void adicionarHabilidade(Habilidade habilidade) {
        habilidades.add(habilidade);
    }

    public abstract void habilidadeEspecial(Personagem alvo);

    protected void ganharExperiencia(int experienciaGanha) {
    }
}
