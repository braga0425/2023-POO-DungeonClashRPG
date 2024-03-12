package models;

public class Arqueiro extends Classe {
    public Arqueiro() {
        super(10, 14, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        // Habilidade "Socar"
        PesosDeAtributos pesosSocar = new PesosDeAtributos();
        pesosSocar.setPesoForca(0.5);
        pesosSocar.setPesoAgilidade(0.1);
        pesosSocar.setPesoInteligencia(0.0);
        adicionarHabilidade(new Habilidade("Socar", pesosSocar, new PesosDeAtributos(), 3, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosSocar.getPesoAgilidade() + personagem.getClasse().getForca() * pesosSocar.getPesoForca()));
            }
        });

        // Habilidade "Atirar Flecha"
        PesosDeAtributos pesosAtirarFlecha = new PesosDeAtributos();
        pesosAtirarFlecha.setPesoForca(0.3);
        pesosAtirarFlecha.setPesoAgilidade(0.5);
        adicionarHabilidade(new Habilidade("Atirar Flecha", pesosAtirarFlecha, new PesosDeAtributos(), 4, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosAtirarFlecha.getPesoAgilidade() + personagem.getClasse().getForca() * pesosAtirarFlecha.getPesoForca()));
            }
        });

        // Habilidade "Flecha Encantada"
        PesosDeAtributos pesosFlechaEncantada = new PesosDeAtributos();
        pesosFlechaEncantada.setPesoForca(0.3);
        pesosFlechaEncantada.setPesoAgilidade(0.5);
        pesosFlechaEncantada.setPesoInteligencia(0.4);

        PesosDeAtributos custoFlechaEncantada = new PesosDeAtributos();
        custoFlechaEncantada.setPesoAgilidade(0.2);
        custoFlechaEncantada.setPesoInteligencia(1.0);

        adicionarHabilidade(new Habilidade("Flecha Encantada", pesosFlechaEncantada, custoFlechaEncantada, 7, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosFlechaEncantada.getPesoAgilidade() + personagem.getClasse().getForca() * pesosFlechaEncantada.getPesoForca() + personagem.getClasse().getInteligencia() * pesosFlechaEncantada.getPesoInteligencia()));
            }

            @Override
            public int getCustoPM(Personagem personagem) {
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
