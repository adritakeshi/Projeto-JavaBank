import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        try {

            Scanner entrada;
            int opcao;
            Banco JavaBank = new Banco("001");

            Conexao.conectar();

            do {

                System.out.println("Sistema do JavaBank");
                System.out.println("Digite a opção desejada:");

                System.out.println("1 - Criar Conta Corrente");
                System.out.println("2 - Saque");
                System.out.println("3 - Extrato");
                System.out.println("4 - Saldo");
                System.out.println("5 - Transferência");
                System.out.println("0 - Sair");
                entrada = new Scanner(System.in);
                opcao = entrada.nextInt();

               /* if (opcao == 1)
                {
                    JavaBank.criarContaCorrente("001","783949-8", true);
                    JavaBank.criarContaCorrente("001","783923-1", false);
                    JavaBank.criarContaCorrente("001","783757-5", false);
                }*/

                if (opcao == 2) {
                    ContaCorrente cc = new ContaCorrente("001","783923-1");
                    cc.Saque(100.00);
                }

                if (opcao == 5) {
                    ContaCorrente cc = new ContaCorrente("001","783923-1");
                    cc.Deposita(1000.00);
                }

            } while (opcao != 0);

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}