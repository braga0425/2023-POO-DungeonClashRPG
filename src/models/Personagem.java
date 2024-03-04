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

    // Construtor
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

    // Getters e setters

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

    // Método para ganhar pontos de experiência e subir de nível
    public void ganharExperiencia(int experienciaGanha) {
        this.PE += experienciaGanha;
        // Lógica para verificar se o personagem pode subir de nível
        // e atualizar o nível, atributos, etc.
    }

    // Método para atacar um inimigo
    public void atacarInimigo(Personagem inimigo) {
        // Lógica para calcular o dano e aplicá-lo ao inimigo
    }

    // Método para atacar uma equipe
    public void atacarEquipe(ArrayList<Personagem> equipe) {
        // Lógica para calcular o dano e aplicá-lo a todos os personagens na equipe
    }

    // Método para registrar o dano sofrido
    public void registrarDano(float dano) {
        this.PV -= dano;
        if (this.PV <= 0) {
            this.PV = 0;
            // Lógica para lidar com o personagem atordoado (PV = 0)
        }
    }

    // Método para calcular o PV máximo do personagem
    public float calcularPVMax() {
        return nivel * classe.getForca() + (nivel * classe.getAgilidade() / 2);
    }

    // Método para calcular o PM máximo do personagem
    public float calcularPMMax() {
        return nivel * classe.getInteligencia() + (nivel * classe.getAgilidade() / 3);
    }

    // Método para verificar se o personagem pode subir de nível e atualizar seus atributos
    public void verificarSubidaNivel() {
        int experienciaNecessaria = nivel * 25;
        if (PE >= experienciaNecessaria) {
            subirNivel();
        }
    }

    // Método para subir de nível
    private void subirNivel() {
        nivel++;
        PE = 0; // Zera os pontos de experiência
        // Atualiza os atributos conforme necessário para o novo nível
        // Por exemplo, pode aumentar os pontos de vida, pontos de magia, etc.
        PV = calcularPVMax();
        PM = calcularPMMax();
    }
}
