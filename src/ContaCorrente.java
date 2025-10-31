import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class ContaCorrente extends Banco{
    private String numero;
    private double saldo;
    private boolean statusEspecial;
    private double limite;
    private ArrayList<Movimentacao> arrayMov;

    public ContaCorrente(String agencia, String numero, boolean statusEspecial, double limite)
    {
        super(agencia);
        this.numero = numero;
        this.saldo = 0.0;
        this.statusEspecial = statusEspecial;
        this.limite = limite;
        this.arrayMov = new ArrayList<Movimentacao>();
    }

    public ContaCorrente(String agencia, String numero)
    {
        super(agencia);
        this.numero = numero;
        this.arrayMov = new ArrayList<Movimentacao>();
    }

    public void Saque(double valor)
    {
        String sql2 =
                "UPDATE CONTACORRENTE SET SALDO = SALDO - ? WHERE NUMEROCONTA = ? AND (SALDO - ? >= 0)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql2)) {
            stmt.setString(1, String.valueOf(valor));
            stmt.setString(2, numero);
            stmt.setString(3, String.valueOf(valor));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.arrayMov.add(new Movimentacao("Saque 22-04-2025 17:55", valor, 'D', this.numero, super.agencia));

        String sql = "INSERT INTO Movimentacao (numeroagencia, numeroconta, tipotransacao, valor) VALUES (?, ?, 'D', ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, super.agencia);
            stmt.setString(2, this.numero);
            stmt.setString(3, String.valueOf(valor));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Deposita(double valor)
    {
        this.arrayMov.add(new Movimentacao("Depósito 22-04-2025 17:30", valor, 'C', this.numero, super.agencia));

        String sql = "INSERT INTO Movimentacao (numeroagencia, numeroconta, valor, tipotransacao) VALUES (?, ?, ?, 'C')";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agencia);
            stmt.setString(2, numero);
            stmt.setString(3, String.valueOf(valor));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql2 = "UPDATE CONTACORRENTE set SALDO = SALDO + ? WHERE NUMEROCONTA = ? AND NUMEROAGENCIA = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql2)) {
            stmt.setString(1, String.valueOf(valor));
            stmt.setString(2, numero);
            stmt.setString(3, agencia);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double Saldo()
    {
        return this.getSaldo();
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
