package models;

public class PesosDeAtributos {
    private int pesoForca;
    private int pesoAgilidade;
    private int pesoInteligencia;

    public PesosDeAtributos(int pesoForca, int pesoAgilidade, int pesoInteligencia) {
        this.pesoForca = pesoForca;
        this.pesoAgilidade = pesoAgilidade;
        this.pesoInteligencia = pesoInteligencia;
    }

    public int getPesoForca() {
        return pesoForca;
    }

    public void setPesoForca(int pesoForca) {
        this.pesoForca = pesoForca;
    }

    public int getPesoAgilidade() {
        return pesoAgilidade;
    }

    public void setPesoAgilidade(int pesoAgilidade) {
        this.pesoAgilidade = pesoAgilidade;
    }

    public int getPesoInteligencia() {
        return pesoInteligencia;
    }

    public void setPesoInteligencia(int pesoInteligencia) {
        this.pesoInteligencia = pesoInteligencia;
    }
}
