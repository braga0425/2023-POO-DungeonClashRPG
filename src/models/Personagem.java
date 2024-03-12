package models;

public class Personagem {
    private static int contadorID = 0;
    private String nome;
    private int nivel;
    private int PE;
    private int PV;
    private int PM;
    private int tempoEspera;
    private Classe classe;
    private int ID;

    public Personagem(String nome, int nivel, int PE, int PV, int PM, Classe classe) {
        this.nome = nome;
        this.nivel = nivel;
        this.PE = PE;
        this.PV = PV;
        this.PM = PM;
        this.tempoEspera = 0;
        this.classe = classe;
        this.ID = ++contadorID;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPE() {
        return PE;
    }

    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public int getPM() {
        return PM;
    }

    public void setPM(int PM){
        this.PM = PM;
    }
    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getID() {
        return ID;
    }

    public void ganharExperiencia(int experienciaGanha) {
        this.PE += experienciaGanha;
        verificarSubidaNivel();
    }

    public int calcularPVMax() {
        return nivel * classe.getForca() + (nivel * classe.getAgilidade() / 2);
    }

    public int calcularPMMax() {
        return nivel * classe.getInteligencia() + (nivel * classe.getAgilidade() / 3);
    }

    private void verificarSubidaNivel() {
        int experienciaNecessaria = nivel * 25;
        if (PE >= experienciaNecessaria) {
            subirNivel();
        }
    }

    private void subirNivel() {
        nivel++;
        PE = 0;
        PV = calcularPVMax();
        PM = calcularPMMax();
    }
}
