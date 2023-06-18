public enum TipoDeQuarto {
    SOLTEIRO(25, 1, 300,"Quarto com uma cama."),
    DUPLO(20, 2,450, "Quarto com duas camas separadas."),
    CASAL(15, 2,500, "Quarto com cama de casal."),
    MASTER(1, 5,1000, "Quarto duplo com uma cama de casal e duas de solteiro.");

    int qtdDeQuarto;
    private int cpcdDePessoa;
    private int valor;
    private String especificacao;
    
    //Função para se o Quarto não Existir entre os ditos no ENUM.
    public static boolean quartoNaoExiste(String quarto) {
        for (TipoDeQuarto tipoDeQuarto : TipoDeQuarto.values()) {
            if (tipoDeQuarto.name().equalsIgnoreCase(quarto)) {
                return true;
            }
        }
        return false;
    }

    //Getters
    public int getCpcdDePessoa() {return cpcdDePessoa;}
    public int getQtdDeQuarto() {return qtdDeQuarto;}
    public int getValor() {return valor;}
    public String getEspecificacao() {return especificacao;}

    
    private TipoDeQuarto(int qtdDeQuarto, int cpcdDePessoa,int valor, String especificacao){
        this.qtdDeQuarto = qtdDeQuarto;
        this.cpcdDePessoa = cpcdDePessoa;
        this.valor = valor;
        this.especificacao = especificacao;
    }
}
