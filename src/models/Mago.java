package models;

public class Mago extends Classe {
    public Mago() {
        super(14, 10, 12);
        adicionarHabilidades();
    }

    private void adicionarHabilidades() {
        // Habilidade "Socar"
        PesosDeAtributos pesosSocar = new PesosDeAtributos();
        pesosSocar.setPesoForca(0.1);
        pesosSocar.setPesoAgilidade(0.1);
        adicionarHabilidade(new Habilidade("Socar", pesosSocar, new PesosDeAtributos(), 2, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosSocar.getPesoAgilidade() + personagem.getClasse().getForca() * pesosSocar.getPesoForca()));
            }
        });

        // Habilidade "Enfraquecer"
        PesosDeAtributos pesosEnfraquecer = new PesosDeAtributos();
        pesosEnfraquecer.setPesoForca(0.3);
        pesosEnfraquecer.setPesoAgilidade(0.2);
        pesosEnfraquecer.setPesoInteligencia(0.5);
        PesosDeAtributos custoEnfraquecer = new PesosDeAtributos();
        custoEnfraquecer.setPesoInteligencia(0.5);
        adicionarHabilidade(new Habilidade("Enfraquecer", pesosEnfraquecer, custoEnfraquecer, 5, false, false) {
            @Override
            public int calcularDano(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosEnfraquecer.getPesoAgilidade() + personagem.getClasse().getForca() * pesosEnfraquecer.getPesoForca() + personagem.getClasse().getInteligencia() * pesosEnfraquecer.getPesoInteligencia()));
            }
        });

        // Habilidade "Cura Amigo"
        PesosDeAtributos pesosCuraAmigo = new PesosDeAtributos();
        pesosCuraAmigo.setPesoForca(0.5);
        pesosCuraAmigo.setPesoAgilidade(0.2);
        pesosCuraAmigo.setPesoInteligencia(0.8);
        PesosDeAtributos custoCuraAmigo = new PesosDeAtributos();
        custoCuraAmigo.setPesoInteligencia(0.7);
        adicionarHabilidade(new Habilidade("Cura Amigo", pesosCuraAmigo, custoCuraAmigo, 4, false, true) {
            @Override
            public int curaAliado(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getAgilidade() * pesosCuraAmigo.getPesoAgilidade() + personagem.getClasse().getForca() * pesosCuraAmigo.getPesoForca() + personagem.getClasse().getInteligencia() * pesosCuraAmigo.getPesoInteligencia()));
            }

            @Override
            public int getPontosMagia(Personagem personagem) {
                return (int) Math.ceil(personagem.getNivel() * (personagem.getClasse().getInteligencia() * custoCuraAmigo.getPesoInteligencia()));
            }
        });
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        setAgilidade(getAgilidade() + 2);
        setInteligencia(getInteligencia() + 3);
        setForca(getForca() + 1);
    }

    @Override
    public String getNome() {
        return "Mago";
    }
}
