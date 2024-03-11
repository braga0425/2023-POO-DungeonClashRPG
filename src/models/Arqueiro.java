package models;

public class Arqueiro extends Classe {
    public Arqueiro() {
        super(10, 14, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.5, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 3, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.1 + personagem.getClasse().getForca() * 0.5));
            }
        });
        adicionarHabilidade(new Habilidade("Atirar Flecha", new PesosDeAtributos(0.3, 0.5, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 4, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.3 + personagem.getClasse().getForca() * 0.5));
            }
        });
        adicionarHabilidade(new Habilidade("Flecha Encantada", new PesosDeAtributos(0.3, 0.5, 0.4), new PesosDeAtributos(0.0, 0.2, 1.0), 7, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * 0.5 + personagem.getClasse().getForca() * 0.3 + personagem.getClasse().getInteligencia() * 0.4));
            }

            @Override
            public int getPontosMagia(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getInteligencia() + personagem.getClasse().getAgilidade() * 0.2));
            }
        });
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        setAgilidade(getAgilidade() + 3);
        setInteligencia(getInteligencia() + 2);
        setForca(getForca() + 1);
    }

    @Override
    public String getNome() {
        return "Arqueiro";
    }

}
