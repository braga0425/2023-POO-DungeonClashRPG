package models;
import java.util.ArrayList;

public class Personagem {
    private static int contadorID = 0;

    private String nome;
    private int nivel;
    private int PE;
    private float PV;
    private float PM;
    private int tempoEspera;
    private Classe classe;
    private int ID;

    public Personagem(String nome, int nivel, int PE, float PV, float PM, Classe classe) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPE() {
        return PE;
    }

    public void setPE(int PE) {
        this.PE = PE;
    }

    public float getPV() {
        return PV;
    }

    public void setPV(float PV) {
        this.PV = PV;
    }

    public float getPM() {
        return PM;
    }

    public void setPM(float PM) {
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

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getID() {
        return ID;
    }

    public void ganharExperiencia(int experienciaGanha) {
        this.PE += experienciaGanha;
        verificarSubidaNivel();
    }

    public void atacarInimigo(Personagem inimigo) {
        float dano = calcularDano();
        inimigo.registrarDano(dano);
    }

    public void atacarEquipe(ArrayList<Personagem> equipe) {
        for (Personagem aliado : equipe) {
            float dano = calcularDano();
            aliado.registrarDano(dano);
        }
    }

    public void registrarDano(float dano) {
        this.PV -= dano;
        if (this.PV <= 0) {
            this.PV = 0;
        }
    }

    private float calcularDano() {
        return this.classe.getForca() * 0.5f;
    }

    public float calcularPVMax() {
        return nivel * classe.getForca() + (nivel * classe.getAgilidade() / 2);
    }

    public float calcularPMMax() {
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
