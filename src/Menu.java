import java.util.Scanner;

    /*
        Observações: 

        * Conector mysql *    
            Coloque o mysql connector no "referenced libraris"
            Fina no java projects
            
        * Crie no mysql *
            create database hotel;
            use hotel;
      
        * Mude o user e o password *
     */

public class Menu {
    public static void main(String[] args) {
        
        Hotel hotel = new Hotel(null, 0, 0, 0, null);

        //Criando a Conexao com base no Conexao.java e linkando com o SQL
        Conexao conexao = new Conexao();
        conexao.sqlConexao();

        //Criando o Scanner para Input do Usuário
        Scanner sc = new Scanner(System.in);
        int opcao;

        //Imprime no Terminal o Menu
        do {
            System.out.println("\n-=- Bem-vindo(a) ao Hotel Transilvânia!! -=-\n");
            System.out.println("1. Cadastrar Reserva");
            System.out.println("2. Quartos Reservados");
            System.out.println("3. Alterar Reserva");
            System.out.println("4. Excluir Reserva");
            System.out.println("5. Finalizar Programa");
            System.out.print("\nDigite o número da função que deseja realizar: ");

            opcao = sc.nextInt();
            
            //Switch para as opções do menu
            switch (opcao) {

                case 1: 
                    hotel.CadastrarReserva();
                    break;

                case 2:
                    hotel.listar();
                    break;

                case 3:
                    hotel.atualizar();
                    break;

                case 4:
                    hotel.apagar();
                    break;
            }
        }while (opcao != 5);
    }
}