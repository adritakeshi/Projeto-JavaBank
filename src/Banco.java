import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Banco{

    public String agencia;

    public ArrayList<ContaCorrente> arrayCC;

    public Banco(String agencia)
    {
        this.agencia = agencia;
        this.arrayCC = new ArrayList<ContaCorrente>();
    }

    public void criarContaCorrente(String agencia, String numero, boolean statusEspecial) {
        ContaCorrente cc = new ContaCorrente(this.agencia, numero, statusEspecial, 0.0);
        this.arrayCC.add(cc);
        String sql = "INSERT INTO ContaCorrente (numeroagencia, numeroconta, saldo, datacadastro, id_cliente) VALUES (?, ?, 0.0, '2025/05/17','36889637412')";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agencia);
            stmt.setString(2, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerContaCorrente(String numero)
    {
        for(int i = 0; i < this.arrayCC.size(); i++)
        {
            ContaCorrente cc = this.arrayCC.get(i);
            if (cc.getNumero() == numero)
            {
                this.arrayCC.remove(i);
                break;
            }
        }
    }
}
