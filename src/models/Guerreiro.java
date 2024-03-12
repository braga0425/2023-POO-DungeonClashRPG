package models;

public class Guerreiro extends Classe {
    public Guerreiro() {
        super(14, 10, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        //habilidade "Socar"
        PesosDeAtributos pesosSocar = new PesosDeAtributos();
        pesosSocar.setPesoForca(0.3);
        pesosSocar.setPesoAgilidade(0.1);
        adicionarHabilidade(new Habilidade("Socar", pesosSocar, new PesosDeAtributos(), 4, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosSocar.getPesoAgilidade() + personagem.getClasse().getForca() * pesosSocar.getPesoForca()));
            }
        });

        //habilidade "Golpe de Espada"
        PesosDeAtributos pesosGolpeEspada = new PesosDeAtributos();
        pesosGolpeEspada.setPesoForca(0.7);
        pesosGolpeEspada.setPesoAgilidade(0.3);
        adicionarHabilidade(new Habilidade("Golpe de Espada", pesosGolpeEspada, new PesosDeAtributos(), 5, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosGolpeEspada.getPesoAgilidade() + personagem.getClasse().getForca() * pesosGolpeEspada.getPesoForca()));
            }
        });

        //habilidade "Espada Flamejante"
        PesosDeAtributos pesosEspadaFlamejante = new PesosDeAtributos();
        pesosEspadaFlamejante.setPesoForca(0.3);
        pesosEspadaFlamejante.setPesoAgilidade(0.5);
        pesosEspadaFlamejante.setPesoInteligencia(0.4);

        PesosDeAtributos custoEspadaFlamejante = new PesosDeAtributos();
        custoEspadaFlamejante.setPesoForca(0.2);
        custoEspadaFlamejante.setPesoInteligencia(1.0);

        adicionarHabilidade(new Habilidade("Espada Flamejante", pesosEspadaFlamejante, custoEspadaFlamejante, 7, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosEspadaFlamejante.getPesoAgilidade() + personagem.getClasse().getForca() * pesosEspadaFlamejante.getPesoForca() + personagem.getClasse().getInteligencia() * pesosEspadaFlamejante.getPesoInteligencia()));
            }

            @Override
            public int getCustoPM(Personagem personagem) {
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
