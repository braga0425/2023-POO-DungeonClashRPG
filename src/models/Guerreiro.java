package models;

public class Guerreiro extends Classe {
    public Guerreiro() {
        super(14, 10, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.3, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 4, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.1 + personagem.getClasse().getForca() * 0.3));
            }
        });
        adicionarHabilidade(new Habilidade("Golpe de Espada", new PesosDeAtributos(0.7, 0.3, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 5, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.3 + personagem.getClasse().getForca() * 0.7));
            }
        });
        adicionarHabilidade(new Habilidade("Espada Flamejante", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(0.2, 0.0, 1.0), 7, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.5 + personagem.getClasse().getForca() * 0.3 + personagem.getClasse().getInteligencia() * 0.4));
            }

            @Override
            public int getPontosMagia(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getInteligencia() + personagem.getClasse().getForca() * 0.2));
            }
        });
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        setAgilidade(getAgilidade() + 1);
        setInteligencia(getInteligencia() + 1);
        setForca(getForca() + 4);
    }

    @Override
    public String getNome() {
        return "Guerreiro";
    }
}
