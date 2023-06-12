//Menu: Deve existir um menu para que o usuário possa acessar as funcionalidades do programa.

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n-=- Bem-vindo(a) ao hotel transilvânia!! -=-");
            System.out.println("-=- Oque o hospede precisa -=- \n");
            System.out.println("1. Cadastrar reserva");
            System.out.println("2. Quartos reservados");
            System.out.println("3. Alterar reserva");
            System.out.println("4. Excluir reserva");
            System.out.println("5. Finalizar Programa");
            System.out.print("\nDigite o número da função que deseja realizar: ");

            opcao = sc.nextInt();

            if (opcao < 0 || opcao > 5){
                System.out.println("\n-=- Opção inválida. Digite novamente -=- \n");
                continue;
            }

            switch (opcao) {

                case 1 -> {
                    hotel.CadastrarReserva();
                }

                case 2 -> {}
                case 3 -> {}
                case 4 -> {}


            }
        }while (opcao != 5);

    }
}