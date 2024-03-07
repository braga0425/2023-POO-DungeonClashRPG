package models;

public class PesosDeAtributos {
    private double pesoForca;
    private double pesoAgilidade;
    private double pesoInteligencia;

    public PesosDeAtributos(double pesoForca, double pesoAgilidade, double pesoInteligencia) {
        this.pesoForca = pesoForca;
        this.pesoAgilidade = pesoAgilidade;
        this.pesoInteligencia = pesoInteligencia;
    }

    public double getPesoForca() {
        return pesoForca;
    }

    public void setPesoForca(double pesoForca) {
        this.pesoForca = pesoForca;
    }

    public double getPesoAgilidade() {
        return pesoAgilidade;
    }

    public void setPesoAgilidade(double pesoAgilidade) {
        this.pesoAgilidade = pesoAgilidade;
    }

    public double getPesoInteligencia() {
        return pesoInteligencia;
    }

    public void setPesoInteligencia(double pesoInteligencia) {
        this.pesoInteligencia = pesoInteligencia;
    }
}
