import java.sql.*;
import java.util.*;

//Herança vindo do Reserva.java
public class Hotel extends Reserva{ 
        
    //Criando o Scanner para Input do Usuário
    Scanner sc = new Scanner(System.in);

    int nmrQuarto;
    int tempo;
    
    //Criando um HashSet para o número dos quartos, para não se repetirem.
    Set<Integer> numerosQuarto = new HashSet<>();
    ArrayList<Reserva> reservas;

    //Construtor
    public Hotel(String hospede, int qntDePessoa, int nmrQuarto, int tempo, TipoDeQuarto tipoDeQuarto) {
        super(hospede, qntDePessoa, nmrQuarto, tempo, tipoDeQuarto);
        this.reservas = new ArrayList<>();
    }

    // ------------------- //
    // CADASTRANDO RESERVA //
    // ------------------- //

    public void CadastrarReserva(){
        
        //Conexão com o SQL
        Conexao conexao = new Conexao();
                conexao.sqlConexao();
        Connection connection = conexao.getConexao();

        //Variavel para o While
        boolean cadastro = true;

        while (cadastro) { 

            System.out.println("\n-=- Reservando um Quarto -=- \n");

            System.out.print("Informe o nome do Hóspede: ");
            String hospede = sc.next();

            //Função para não ter nomes repetidos.
            for(Reserva reserva : reservas){
                if(hospede.equalsIgnoreCase(reserva.getHospede())){
                    System.out.println("Esse Hóspede ja tem reserva!");
                    return;
                }
            }

            //Função para mostrar todos os Quartos Disponiveis.
            System.out.println("\n-*- Quartos Disponíveis -*- \n");
            for (TipoDeQuarto tipodeQuarto : TipoDeQuarto.values()) {
                System.out.println(tipodeQuarto.name());
            }

            // Informação sobre os quartos.
            System.out.print("\nO Hóspede deseja saber informações específicas sobre algum quarto? (sim / não): ");
            String infoQuarto = sc.next();

            //Função para se o hóspede quiser informação sobre os quartos.
            if (infoQuarto.equalsIgnoreCase("SIM")) {
                do{
                    System.out.print("Qual quarto o Hóspede deseja saber mais?: ");
                    infoQuarto = sc.next();

                    //Busca e mostra as informações do quarto.
                    for (TipoDeQuarto tipodeQuarto : TipoDeQuarto.values()) {
                        if (infoQuarto.equalsIgnoreCase(tipodeQuarto.name())) {
                            System.out.println("\n-----------------------------------------------------------");
                            System.out.println("Tipo do quarto: " + tipodeQuarto.name());
                            System.out.println("Capacidade de pessoas: " + tipodeQuarto.getCpcdDePessoa());
                            System.out.println("Valor diario: " + tipodeQuarto.getValor());
                            System.out.println("Especificações: " + tipodeQuarto.getEspecificacao());
                            System.out.println("Quantidade disponível de quartos: "+ tipodeQuarto.getQtdDeQuarto());
                            System.out.println("-----------------------------------------------------------");
                        }
                    }

                    System.out.print("\nO Hóspede deseja ver mais algum quarto? (sim / não): ");
                    infoQuarto = sc.next();

                    if (!infoQuarto.equalsIgnoreCase("Sim")){
                        break;
                    }

                }while (true);
            }

            System.out.print("Qual quarto o Hóspede deseja reservar: ");
            String quarto = sc.next().toUpperCase();
            TipoDeQuarto tipoDeQuarto = TipoDeQuarto.valueOf(quarto);

            System.out.print("Quantos Hóspedes vão ficar no quarto?: ");
            qntDePessoa = sc.nextInt();

            System.out.print("Por quantos dias o(s) Hóspede(s) ira reservar o quarto?: ");
            tempo = sc.nextInt();

            //Cria um if para ver se ainda existem quartos disponíveis.
            if (tipoDeQuarto.getQtdDeQuarto() > 0){
                //Puxa o gerador de numeros de quarto.
                do {
                    nmrQuarto = numeroQuarto(tipoDeQuarto);
                } while (numerosQuarto.contains(nmrQuarto));
                 numerosQuarto.add(nmrQuarto);

                System.out.print("O Hóspede deseja confirmar a reserva (sim / não): ");
                String confirmar = sc.next();

                //If para se a resposta for sim, confirmar a reserva do quarto.
                if (confirmar.equalsIgnoreCase("SIM")){
                    Reserva reserva = new Reserva(hospede, qntDePessoa, nmrQuarto, tempo, tipoDeQuarto);
                    reservas.add(reserva);

                    //Diminui a quantidade de quartos disponíveis do quarto selecionado.
                    tipoDeQuarto.qtdDeQuarto--;

                    int valor  = tempo * tipoDeQuarto.getValor();
                    // Informa o Hóspede que acabou de realizar a reserva.
                    System.out.println("\nHóspede cadastrado: " + hospede);
                    System.out.println("No Quarto: " + tipoDeQuarto.name());
                    System.out.println("Número do quarto: " + nmrQuarto);
                    System.out.println("Quantidade de Hóspedes: " + qntDePessoa);
                    System.out.println("Por: " + tempo + " dias");
                    System.out.println("Valor final: " + valor);

                    try {
                        //Cadastra o Hóspede e as informações anteriores no SQL
                        String cadastrar = "insert into tiposquartos (hospede, qntdepessoa, nmrquarto, tempo, valor, tipodequarto) values (?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = connection.prepareStatement(cadastrar);

                        //Coloca os dados nos locais certos da tabela tiposquartos.
                        ps.setString(1, hospede);
                        ps.setInt(2, qntDePessoa);
                        ps.setInt(3, nmrQuarto);
                        ps.setInt(4, tempo);
                        ps.setInt(5, valor);
                        ps.setString(6, tipoDeQuarto.name());

                        //Termina de Colocar e Fecha.
                        ps.executeUpdate();
                        ps.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    
                }else {
                    System.out.println("Reserva cancelada!!");
                }
            }else{
               System.out.println("Não a mais quartos disponiveis desse tipo!");
            }

            // Verificar se deseja cadastrar mais reservas.
            System.out.print("\nDeseja cadastrar mais reservas? (sim/ não):");
            String resposta = sc.next().toUpperCase();

            // Se a resposta for sim ele continua no programa.
                //Se for não ou alguma outra coisa ele volta para o menu.
            if (resposta.equalsIgnoreCase("SIM")) {
                cadastro = true;
            }else {
                cadastro = false;
            }
        }
    }

    // ------------------- //
    // LISTANDO RESERVAS   //
    // ------------------- //

    public void listar() {

        //Faz a Conexão com o SQL.
        Conexao conexao = new Conexao();
        conexao.sqlConexao();
        Connection connection = conexao.getConexao();

        int opcao;

        do{
            //Imprime as Opções
            System.out.println("\n-=- Lista de reservas: -=-\n");
    
            System.out.println("Opções:");
            System.out.println("1. Listar nome dos Hóspedes, números dos quartos");
            System.out.println("2. Mostrar todos os dados");
            System.out.println("3. Sair das listas");
            System.out.print("\nDigite o número da função que deseja realizar: ");

            opcao = sc.nextInt();
    
            try{
                Statement st = connection.createStatement();
                ResultSet sqlComando = null;

                //Cria o Switch Case para as opções selecionadas.
                switch (opcao) {
                    
                    //Opção para listar Nome dos Hóspedes e Números dos Quartos
                    case 1:
                        System.out.println("\nLista de nomes dos Hóspedes e quartos:");

                        //Pegando o hóspede, numero do quarto da tabela tiposquartos do SQL.
                        sqlComando = st.executeQuery("select hospede, nmrquarto from tiposquartos;");

                    while (sqlComando.next()) {
                        String hospede = sqlComando.getString("hospede");
                        System.out.println("Nome: " + hospede);
                    }
                    break;
    
                //Mostrando todos os dados que estão na tabela tiposquartos no SQL.
                case 2:
                    System.out.println("\nLista de todas as reservas");
                    
                    //Listando todas as Reservas usando o comando SELECT do SQL.
                    sqlComando = st.executeQuery("select * from tiposquartos;");

                    //Printando e buscando todos os dados.
                    while (sqlComando.next()){
                        String hospede = sqlComando.getString("hospede");
                        int qntDePessoa = sqlComando.getInt("qntdepessoa");
                        int nmrQuarto = sqlComando.getInt("nmrquarto");
                        int tempo = sqlComando.getInt("tempo");
                        int valor = sqlComando.getInt("valor");
                        String tipoDeQuarto = sqlComando.getString("tipodequarto");

                        System.out.println("-----------------------------------------------------");
                        System.out.println("Nome: " + hospede);
                        System.out.println("Quantidade de pessoas: " + qntDePessoa);
                        System.out.println("Número do Quarto: " + nmrQuarto);
                        System.out.println("Tempo: " + tempo);
                        System.out.println("Valor: " + valor);
                        System.out.println("Tipo de Quarto: " + tipoDeQuarto);
                    }
                    break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
          //Se a opção for igual a 3, fecha essa parte do código e volta ao menu.
        } while(opcao != 3);
    }

    // -------------------- //
    // ATUALIZANDO RESERVA  //
    // -------------------- //

    public void atualizar() {
        //Conectando ao SQL
        Conexao conexao = new Conexao();
        conexao.sqlConexao();
        Connection connection = conexao.getConexao();

        try {
            Statement consultaSt = connection.createStatement();
            Statement atualizacaoSt = connection.createStatement();
            ResultSet consultaRs = null;

            System.out.println("\n-=- Atualizando reserva -=-");
            System.out.print("\nQual Hóspede deseja alterar?: ");
            String hospedeAtualiza = sc.next();

            boolean encontrouReserva = false;

            // Consulta o banco de dados para encontrar a reserva correspondente ao hóspede
            String consultaBD = "SELECT * FROM tiposquartos WHERE hospede = '" + hospedeAtualiza + "'";
            consultaRs = consultaSt.executeQuery(consultaBD);

            //Vai procurar os dados e fazer as funções.
            while (consultaRs.next()) {
                encontrouReserva = true;

                int nmrQuarto = consultaRs.getInt("nmrquarto");
                int tempo = consultaRs.getInt("tempo");

                //Vai procurar e imprimir todos os dados do Hóspede.
                System.out.println("Informações do Hóspede: " + hospedeAtualiza);
                System.out.println("Hóspede: " + hospedeAtualiza);
                System.out.println("Quarto: " + nmrQuarto);
                System.out.println("Tempo: " + tempo);

                //Vai imprimir e perguntar o que deseja alterar, entre Nome e Tempo
                System.out.println("\nOpções para alterações (Nome, Tempo)");
                System.out.print("O que deseja alterar?: ");
                String alteracao = sc.next();

                //Se for Nome, ele irá alterar aqui
                if (alteracao.equalsIgnoreCase("NOME")) {
                    System.out.print("Informe o novo nome do Hóspede: ");
                    String novoNome = sc.next();

                    //Pede o novo nome e com o comando UPDATE, altera dentro da tabela tiposquartos no SQL o nome do Hóspede. 
                    String atualizandoBD = "UPDATE tiposquartos SET hospede = '" + novoNome + "' WHERE hospede = '" + hospedeAtualiza + "'";
                    atualizacaoSt.executeUpdate(atualizandoBD);

                    System.out.println("Nome atualizado com sucesso!");
                
                //Se for Tempo, ele irá alterar aqui
                } else if (alteracao.equalsIgnoreCase("TEMPO")) {
                    System.out.print("Informe o novo tempo da reserva: ");
                    int novoTempo = sc.nextInt();

                    //Pede o novo e tempo com o comando UPDATE, altera dentro da tabela tiposquartos no SQL o tempo de estadia do Hóspede. 
                    String atualizandoBD = "UPDATE tiposquartos SET tempo = '" + novoTempo + "' WHERE hospede = '" + hospedeAtualiza + "'";
                    atualizacaoSt.executeUpdate(atualizandoBD);

                    System.out.println("Tempo atualizado com sucesso!");

                //Se o usuário digitar algo que não seja Nome ou Tempo virá aqui.
                } else {
                    System.out.println("OPÇÃO INVÁLIDA!");
                }
            }

            //Se não encontrar nenhuma Reserva com o nome dado.
            if (!encontrouReserva) {
                System.out.println("Reserva não encontrada para o Hóspede: " + hospedeAtualiza);
            }

            // Fechar ResultSet e Statements
                consultaRs.close();
                consultaSt.close();
                atualizacaoSt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ------------------- //
    //  APAGANDO RESERVA   //
    // ------------------- //

    public void apagar() {

    //Conectando com o SQL
    Conexao conexao = new Conexao();
    conexao.sqlConexao();
    Connection connection = conexao.getConexao();

    //Cria a Boolean continuar e enquanto ela for verdade, continua o programa.
    boolean continuar = true;
    while (continuar) {
        System.out.println("\n-=- Apagando uma reserva -=-");

        
        System.out.print("Informe o nome do Hóspede que deseja apagar: ");
        String nome = sc.next();

        //Pede o nome do Hóspede e procura por ele.
        try {
            Statement st = connection.createStatement();
            ResultSet sqlComando = null;

            //Seleciona o Hóspede pelo nome dado anteriormente.
            String buscarReserva = "SELECT * FROM tiposquartos WHERE hospede = '" + nome + "'";
            sqlComando = st.executeQuery(buscarReserva);

            //Se achar ele faz esse comando:
            if (sqlComando.next()) {
                //Imprime todas as informações do Hóspede selecionado.
                System.out.println("Informações do Hóspede:");
                System.out.println("Hóspede: " + sqlComando.getString("hospede"));
                System.out.println("Quarto: " + sqlComando.getInt("nmrquarto"));
                System.out.println("Tempo: " + sqlComando.getInt("tempo"));

                System.out.print("Deseja apagar a reserva desse hóspede? (sim / não): ");
                String apagar = sc.next();

                //Pergunta se tem certeza sobre deletar, e se a resposta for Sim, ela Deleta o
                //  Hóspede do banco de dados usando o comando DELETE do SQL.
                if (apagar.equalsIgnoreCase("SIM")) {
                    String apagarReserva = "DELETE FROM tiposquartos WHERE hospede = '" + nome + "'";
                    st.executeUpdate(apagarReserva);

                    // Remover a reserva da lista 'reservas'
                    for (Reserva reserva : reservas) {
                        if (reserva.getHospede().equalsIgnoreCase(nome)) {
                            reservas.remove(reserva);
                            break;
                        }
                    }

                    System.out.println("Reserva apagada com sucesso.");
                //Se a resposta for qualquer coisa que não seja um sim, cancela.
                } else {
                    System.out.println("Nenhuma reserva foi apagada.");
                }
            //Se não achar nenhum Hóspede com o nome colocado cancela.
            } else {
                System.out.println("Nenhum Hóspede encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Pergunta se deseja apagar mais alguma reserva
        System.out.print("Deseja apagar mais alguma reserva? (sim/não): ");
        String resposta = sc.next();

        //Se a resposta for sim ele deixa a Boolean como True, portanto continuando o programa
        if (resposta.equalsIgnoreCase("SIM")) {
            continuar = true;
        //Se for não transforma em False, e retorna para o menu.
        } else {
            continuar = false;
        }
    }
}

}

