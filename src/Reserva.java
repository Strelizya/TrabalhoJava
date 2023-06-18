import java.util.Random;

public class Reserva {
    private String hospede;
    protected int qntDePessoa;
    private int nmrQuarto;
    private int tempo;
    private TipoDeQuarto tipoDeQuarto;

    public Reserva(String hospede, int qntDePessoa, int nmrQuarto, int tempo, TipoDeQuarto tipoDeQuarto) {
    this.hospede = hospede;
    this.qntDePessoa = qntDePessoa;
    this.nmrQuarto = nmrQuarto;
    this.tempo = tempo;
    this.tipoDeQuarto = tipoDeQuarto;
    }

    //Setters
    public void setNmrQuarto(int nmrQuarto) {this.nmrQuarto = nmrQuarto;}
    public void setTipoDeQuarto(TipoDeQuarto tipoDeQuarto) {this.tipoDeQuarto = tipoDeQuarto;}
    public void setHospede(String hospede) {this.hospede = hospede;}
    public void setTempo(int tempo) {this.tempo = tempo;}
    public void setqntDePessoa(int qntDePessoa){this.qntDePessoa = qntDePessoa;}

    //Getters
    public String getHospede() {return hospede;}
    public TipoDeQuarto getTipoDeQuarto() {return tipoDeQuarto;}
    public int getNmrQuarto() {return nmrQuarto;}
    public int getTempo(){return tempo;}
    public int getqntDePessoa(){ return qntDePessoa;}

    //Fazendo para Aleatorizar o Número do Quarto dentro de certo parâmetros
    public int numeroQuarto(TipoDeQuarto tipoQuarto) {
        Random random = new Random();
        int min, max;

        if (tipoQuarto == TipoDeQuarto.SOLTEIRO) {
            min = 101;
            max = 126;
        } else if (tipoQuarto == TipoDeQuarto.DUPLO) {
            min = 217;
            max = 247;
        } else if (tipoQuarto == TipoDeQuarto.CASAL) {
            min = 348;
            max = 363;
        } else if (tipoQuarto == TipoDeQuarto.MASTER) {
            min = 464;
            max = 469;
        } else {
            throw new IllegalArgumentException("Tipo de quarto Inválido.");
        }

        return random.nextInt(max - min + 1) + min;
    }
}


