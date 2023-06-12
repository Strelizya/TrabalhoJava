public class Reserva {
    private String hospede;
    private int nmrQuarto;
    private TipoDeQuarto tipoDeQuarto;

    public Reserva(String hospede, int nmrQuarto, TipoDeQuarto tipoDeQuarto) {
    }


    public void setNmrQuarto(int nmrQuarto) {
        this.nmrQuarto = nmrQuarto;
    }

    public void setTipoDeQuarto(TipoDeQuarto tipoDeQuarto) {
        this.tipoDeQuarto = tipoDeQuarto;
    }

    public void setHospede(String hospede) {
        this.hospede = hospede;
    }

    public String getHospede() {
        return hospede;
    }

    public TipoDeQuarto getTipoDeQuarto() {
        return tipoDeQuarto;
    }

    public int getNmrQuarto() {
        return nmrQuarto;
    }
}


