import java.util.*;

public class Hotel{
//Super
    Scanner sc = new Scanner(System.in);
    Set<Integer> numerosQuarto = new HashSet<>();
    List<Reserva> reservas = new ArrayList<>();


    //Cadastrar pessoa em um quarto
   public void CadastrarReserva(){
       boolean cadastro = true;
       int nmrQuarto;

       while (cadastro) {

           System.out.println("\n-*- Reservando um quarto -*- \n");

           System.out.print("Informe o nome do Hospede: ");
           String hospede = sc.next();

           System.out.println("\n-*- Quartos disponiveis -*- \n");
           for (TipoDeQuarto tipodeQuarto : TipoDeQuarto.values()) {
               System.out.println(tipodeQuarto.name());
           }

           // Informação sobre os quartos
           System.out.print("\nO hospede deseja saber informações específicas sobre algum quarto? (sim / não): ");
           String infoQuarto = sc.next();

           if (infoQuarto.equalsIgnoreCase("SIM")) {
               do{
                    System.out.print("Qual quarto o hospede deseja saber mais?: ");
                    infoQuarto = sc.next();

                    for (TipoDeQuarto tipodeQuarto : TipoDeQuarto.values()) {
                        if (infoQuarto.equalsIgnoreCase(tipodeQuarto.name())) {
                            System.out.println("\n-----------------------------------------------------------");
                            System.out.println("Tipo do quarto: " + tipodeQuarto.name());
                            System.out.println("Capacidade de pessoas: " + tipodeQuarto.getCpcdDePessoa());
                            System.out.println("Valor diario: " + tipodeQuarto.getValor());
                            System.out.println("Especificações: " + tipodeQuarto.getEspecificacao());
                            System.out.println("Quantidade disponivel de quartos: "+ tipodeQuarto.getQtdDeQuarto());
                            System.out.println("-----------------------------------------------------------");
                        }
                    }

                    System.out.print("\nO hóspede deseja ver mais algum quarto? (sim / não): ");
                    infoQuarto = sc.next();

                    if (!infoQuarto.equalsIgnoreCase("Sim")){
                        break;
                    }

               }while (true);
           }

           System.out.print("Qual quarto o hospode deseja reservar: ");
           String quarto = sc.next().toUpperCase();
           TipoDeQuarto tipoDeQuarto = TipoDeQuarto.valueOf(quarto);

           if (tipoDeQuarto.getQtdDeQuarto() >0){
                // Chamando o gerador de numeros de quarto
                do {
                    nmrQuarto = numeroQuarto(tipoDeQuarto);
                } while (numerosQuarto.contains(nmrQuarto));
                 numerosQuarto.add(nmrQuarto);

                System.out.print("O hospede deseja confirma a reserva (sim / não): ");
                String confirmar = sc.next();

                if (confirmar.equalsIgnoreCase("SIM")){
                    Reserva reserva = new Reserva(hospede, nmrQuarto, tipoDeQuarto);
                    reservas.add(reserva);

                    tipoDeQuarto.setQtdDeQuarto(tipoDeQuarto.getQtdDeQuarto() -1);

                    // Informa o hospede que acabou de realizar a reserva
                    System.out.println("\nHospede cadastrado: " + hospede);
                    System.out.println("No Quarto: " + tipoDeQuarto.name());
                    System.out.println("Número do quarto: " + nmrQuarto);
                }else {
                    System.out.println("Reserva cancelada!!");
                }
           }else{
               System.out.println("Não a mais quartos disponiveis desse tipo!");
           }

           // Verificar se deseja cadastrar mais reservas
           System.out.print("\nDeseja cadastrar mais reservas? (sim/ não):");
           String resposta = sc.next().toUpperCase();

           // Se a resposta for sim ele continua no programa
                //Se for não ou alguma outra coisa ele volta para o menu
           if (resposta.equalsIgnoreCase("SIM")) {
               cadastro = true;
           }else {
               cadastro = false;
           }
       }
   }
































    private int numeroQuarto(TipoDeQuarto tipoQuarto) {
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
            throw new IllegalArgumentException("Tipo de quarto inválido.");
        }

        return random.nextInt(max - min + 1) + min;
    }
}

