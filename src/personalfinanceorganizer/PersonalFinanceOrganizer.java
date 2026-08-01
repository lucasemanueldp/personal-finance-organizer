package personalfinanceorganizer;

import java.util.Scanner;

public class PersonalFinanceOrganizer {
    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);
        RepositorioTransacao rp = new RepositorioTransacao(100);
        ServicoTransacao st = new ServicoTransacao(rp);
        boolean menu = true;
        int opcao;
        while (menu) {
            System.out.println("-------------------------------------");
            System.out.println("    ORGANIZADOR FINANCEIRO PESSOAL   ");
            System.out.println("-------------------------------------");
            System.out.println("     1 - Cadastrar Transação         ");
            System.out.println("     2 - Mostrar Transações         ");
            System.out.println("     3 - Consultar Transação por ID ");
            System.out.println("     4 - Remover Transação         ");
            System.out.println("     5 - Consultar Saldo            ");
            System.out.println("     6 - Analisar Saúde Financeira  ");
            System.out.println("     7 - Sair         ");
            System.out.println("-------------------------------------");
            opcao = r.nextInt();
            r.nextLine();
            switch (opcao) {
                //CADASTRAR TRANSAÇÃO
                case 1:
                    System.out.println("------------------------------------");
                    System.out.println("             CADASTRO               ");
                    System.out.println("------------------------------------");
                    System.out.print("Informe a categoria: [Receita/Despesa]");
                    String categoria = r.nextLine();
                    System.out.print("Informe a descrição/nome: ");
                    String descricao = r.nextLine();
                    System.out.print("Informe o valor: ");
                    double valor = r.nextDouble();
                    r.nextLine();
                    System.out.print("Informe a data do pagamento ou parcela: ");
                    String data = r.nextLine();
                    System.out.println("-------------------------------------");
                    Transacao t = new Transacao(categoria, descricao, valor, data);
                    boolean cadastrou = st.verificaCadastro(t);
                    if (cadastrou) {
                        System.out.println("-------------------------------------");
                        System.out.println("Item cadastrado com sucesso!");
                    } 
                    
                break;
                //MOSTRAR TRANSAÇÃO
                case 2:
                    st.listarFormatado();
                break;
                //CONSULTAR ID
                case 3:
                    System.out.print("Digite o ID do item específico que deseja ver: ");
                    int id = r.nextInt();
                    r.nextLine();
                    st.consultarFormatado(id);
                break;
                //REMOVER TRANSAÇÃO
                case 4:
                    st.listarFormatado();
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("Qual item você deseja remover? [Digite o ID do item em específico]");
                    int idr = r.nextInt();
                    r.nextLine();
                    st.removerFormatado(idr);
                break;
                //VER SALDO
                case 5:
                    System.out.println("----------------------------------------");
                    System.out.println("                 SALDO                  ");
                    System.out.println("----------------------------------------");
                    double saldo = st.calcularSaldo();
                    if (saldo == 0) {
                        System.out.println("Seu saldo atual é de R$0,00. Cadastre uma receita.");
                    }else {
                        System.out.println("Seu saldo atual é de R$"+ String.format("%.2f", saldo)+".");
                    }
                break;
                //ANALISAR SAÚDE FINANCEIRA
                case 6:
                    st.analisarSaude();
                break;
                //SAIR
                case 7:
                    System.out.println("Finalizando programa...");
                    menu = false;
                break;
                default:
                    System.out.println("Opção inválida, digite apenas os números do menu. ");
                break;
            }
        }
        
    }
    
}
