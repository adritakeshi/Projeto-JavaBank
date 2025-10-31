public class Movimentacao extends ContaCorrente{
    private String descricao;
    private double valor;
    private char tipoDaMov;

    public Movimentacao(String descricao, double valor, char tipoDaMov, String numero, String agencia)
    {
        super(agencia,numero);
        this.descricao = descricao;
        this.valor = valor;
        this.tipoDaMov = tipoDaMov;

        if (this.tipoDaMov == 'D')
            super.setSaldo(super.getSaldo() - valor);
        else
            if (this.tipoDaMov == 'C')
                super.setSaldo(super.getSaldo() + valor);
    }

}
