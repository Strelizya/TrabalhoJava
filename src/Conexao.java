import java.sql.*;

public class Conexao {

    private Connection conexao;
    private PreparedStatement ps;

    //Getter para Conexão
    public Connection getConexao(){
        return conexao;
    }

    //Conexão com o Servidor do MySQL
    public void sqlConexao(){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/hotel";
            conexao = DriverManager.getConnection(url, "root", "root");
            
            criarTabelaTiposQuartos();

        }catch(Exception e){
            e.printStackTrace();
        }
    } 

    //Criando Tabela tiposquartos dentro do SQL
    public void criarTabelaTiposQuartos(){
        try {
            String tabelaTipoQuarto = "create table if not exists tiposquartos (hospede varchar(50), qntdepessoa int, nmrquarto int, tempo int, valor int, tipodequarto varchar(30), primary key(hospede));";
            ps = conexao.prepareStatement(tabelaTipoQuarto);
            ps.execute();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
