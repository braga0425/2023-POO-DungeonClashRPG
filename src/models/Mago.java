package models;

public abstract class Mago extends Classe {
    public Mago() {
        super(14, 10, 12);
        this.adicionarHabilidade(new Habilidade("Socar", new PesosDeAtributos(0.1, 0.1, 0.0), new PesosDeAtributos(0.0, 0.0, 0.0), 2, false, false));
        this.adicionarHabilidade(new Habilidade("Enfraquecer", new PesosDeAtributos(0.3, 0.2, 0.5), new PesosDeAtributos(0.0, 0.0, 0.5), 5, false, false));
        this.adicionarHabilidade(new Habilidade("Cura Amigo", new PesosDeAtributos(0.5, 0.2, 0.8), new PesosDeAtributos(0.0, 0.0, 0.7), 4, false, true));
    }

    @Override
    public void ganharExperiencia(int experienciaGanha) {
        super.ganharExperiencia(experienciaGanha);
        this.setAgilidade(this.getAgilidade()+2);
        this.setInteligencia(this.getInteligencia()+3);
        this.setForca(this.getForca()+1);
    }

    @Override
    public void atacar(Personagem alvo) {
        // Lógica para escolher a habilidade e atacar
        Habilidade habilidade = escolherHabilidade(alvo);
        if (habilidade == null) {
            System.out.println("Habilidade indisponível!");
            return;
        }

        if (!habilidade.estaPronta()) {
            System.out.println("Habilidade em tempo de espera!");
            return;
        }

        if (habilidade.calcularCustoMana(this) > this.getPM()) {
            System.out.println("Mana insuficiente!");
            return;
        }

        this.setPM(this.getPM() - habilidade.calcularCustoMana(this));
        habilidade.usarHabilidade(alvo, null);
        habilidade.setTempoEsperaAtual(habilidade.getTempoDescanso());
    }

    /*  adpatar para nosso codigo
    @Override
    public void atacarEquipe(Equipe equipeInimiga) {
        // Lógica para escolher a habilidade e atacar a equipe
        Habilidade habilidade = escolherHabilidade(equipeInimiga);
        if (habilidade == null) {
            System.out.println("Habilidade indisponível!");
            return;
        }

        if (!habilidade.estaPronta()) {
            System.out.println("Habilidade em tempo de espera!");
            return;
        }

        if (habilidade.calcularCustoMana(this) > this.getPM()) {
            System.out.println("Mana insuficiente!");
            return;
        }

        this.setPM(this.getPM() - habilidade.calcularCustoMana(this));
        habilidade.usarHabilidade(null, equipeInimiga);
        habilidade.setTempoEsperaAtual(habilidade.getTempoDescanso());
    }

    private Habilidade escolherHabilidade(Personagem alvo) {
        // Enfraquecer o alvo se ele tiver vida alta
        if (alvo.getHP() > alvo.getHPMax() * 0.7) {
            return this.getHabilidades().get(1);
        }

        // Cura amigo se necessário
        for (Personagem aliado : this.getEquipe().getPersonagens()) {
            if (aliado.getHP() < aliado.getHPMax() * 0.5) {
                return this.getHabilidades().get(2);
            }
        }

        // Caso contrário, atacar o alvo
        return this.getHabilidades().get(0);
    }

    private Habilidade escolherHabilidade(Equipe equipeInimiga) {
        // Enfraquecer o inimigo com maior força
        Personagem alvo = null;
        int maiorForca = 0;
        for (Personagem personagem : equipeInimiga.getPersonagens()) {
            if (personagem.getForca() > maiorForca) {
                maiorForca = personagem.getForca();
                alvo = personagem;
            }
        }

        return this.getHabilidades().get(1);
    }
*/

}
