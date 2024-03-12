package models;

public class PesosDeAtributos {
    private double pesoForca;
    private double pesoAgilidade;
    private double pesoInteligencia;

    public PesosDeAtributos() {
        //construtor vazio
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
