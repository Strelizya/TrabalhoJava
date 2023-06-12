// Enumeração: Seu projeto deve apresentar e utilizar ao menos uma enumeração. Essa enumeração deve possuir ao menos 2 atributos.

public enum TipoDeQuarto {
    SOLTEIRO(25, 1, 300,"Quarto com uma cama."),
    DUPLO(20, 2,450, "Quarto com duas camas separadas."),
    CASAL(15, 2,500, "Quarto com cama de casal."),
    MASTER(1, 5,1000, "Quarto duplo com uma cama de casal e duas de solteiro.");

    private int qtdDeQuarto;
    private int cpcdDePessoa;
    private int valor;
    private String especificacao;


    //Verifica se o quarto é valido
    public static boolean quartoNaoExiste(String quarto) {
        for (TipoDeQuarto tipoDeQuarto : TipoDeQuarto.values()) {
            if (tipoDeQuarto.name().equalsIgnoreCase(quarto)) {
                return true;
            }
        }
        return false;
    }

    //Getter e setters
    public int getCpcdDePessoa() {return cpcdDePessoa;}
    public int getQtdDeQuarto() {return qtdDeQuarto;}
    public int getValor() {return valor;}
    public String getEspecificacao() {return especificacao;}

    public void setCpcdDePessoa(int cpcdDePessoa) {this.cpcdDePessoa = cpcdDePessoa;}
    public void setEspecificacao(String especificacao) {this.especificacao = especificacao;}
    public void setQtdDeQuarto(int qtdDeQuarto) {this.qtdDeQuarto = qtdDeQuarto;}

    //Construtor
    private TipoDeQuarto(int qtdDeQuarto, int cpcdDePessoa,int valor, String especificacao){
        this.qtdDeQuarto = qtdDeQuarto;
        this.cpcdDePessoa = cpcdDePessoa;
        this.valor = valor;
        this.especificacao = especificacao;
    }
}
