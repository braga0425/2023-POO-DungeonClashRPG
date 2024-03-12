package models;

public class Monstro extends Classe {
    public Monstro() {
        super(9, 4, 3);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        //habilidade "Socar"
        PesosDeAtributos pesosSocar = new PesosDeAtributos();
        pesosSocar.setPesoForca(0.8);
        pesosSocar.setPesoAgilidade(0.4);
        adicionarHabilidade(new Habilidade("Socar", pesosSocar, new PesosDeAtributos(), 5, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosSocar.getPesoAgilidade() + personagem.getClasse().getForca() * pesosSocar.getPesoForca()));
            }
        });

        //habilidade "Chutar"
        PesosDeAtributos pesosChutar = new PesosDeAtributos();
        pesosChutar.setPesoForca(1.0);
        pesosChutar.setPesoAgilidade(0.5);
        adicionarHabilidade(new Habilidade("Chutar", pesosChutar, new PesosDeAtributos(), 8, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosChutar.getPesoAgilidade() + personagem.getClasse().getForca() * pesosChutar.getPesoForca()));
            }
        });

        //habilidade "Grito Atordoante"
        PesosDeAtributos pesosGritoAtordoante = new PesosDeAtributos();
        pesosGritoAtordoante.setPesoForca(0.4);
        pesosGritoAtordoante.setPesoAgilidade(0.2);
        adicionarHabilidade(new Habilidade("Grito Atordoante", pesosGritoAtordoante, new PesosDeAtributos(), 6, true, true) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosGritoAtordoante.getPesoAgilidade() + personagem.getClasse().getForca() * pesosGritoAtordoante.getPesoForca()));
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
        return "Monstro";
    }
}
