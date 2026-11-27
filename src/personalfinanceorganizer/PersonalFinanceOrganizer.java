package personalfinanceorganizer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PersonalFinanceOrganizer {
    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);
        RepositorioTransacao rp = new RepositorioTransacao();
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
            opcao = lerInteiro(r);
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
                    System.out.print("Informe o valor(ex.: 12,50): ");
                    double valor = lerDouble(r);
                    System.out.print("Informe a data do pagamento ou parcela: ");
                    String data = r.nextLine();
                    System.out.println("-------------------------------------");
                    Transacao t = new Transacao(categoria, descricao, valor, data);
                    try {
                        st.verificaCadastro(t);
                        System.out.println("-------------------------------------");
                        System.out.println("Item cadastrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("-------------------------------------");
                        System.out.println(e.getMessage());
                    }
                    
                break;
                //MOSTRAR TRANSAÇÃO
                case 2:
                    boolean menuLista = true;
                    int opcaoLista;
                    while (menuLista) {
                        System.out.println("-------------------------------------");
                        System.out.println("           MINHAS TRANSAÇÕES         ");
                        System.out.println("-------------------------------------");
                        System.out.println("     1 - Listar Todas         ");
                        System.out.println("     2 - Apenas Receitas         ");
                        System.out.println("     3 - Apenas Despesas ");
                        System.out.println("     4 - Ordenar por maior valor         ");
                        System.out.println("     5 - Ordenar por menor valor         ");
                        System.out.println("     6 - Voltar ");
                        System.out.println("-------------------------------------");
                        opcaoLista = lerInteiro(r);
                        switch (opcaoLista) {
                            //LISTAR TODAS
                            case 1:
                                List<Transacao> todas = st.listarTodas();
                                st.listarFormatado(todas);
                            break;
                            //APENAS RECEITAS
                            case 2:
                                List<Transacao> receitas = st.listarReceitas();
                                st.listarFormatado(receitas);
                            break;
                            //APENAS DESPESAS
                            case 3:
                                List<Transacao> despesas = st.listarDespesas();
                                st.listarFormatado(despesas);
                            break;
                            //ORDENAR POR MAIOR VALOR
                            case 4:
                                List<Transacao> maiorValor = st.listarMaiorValor();
                                st.listarFormatado(maiorValor);
                            break;
                            //ORDERNAR POR MENOR VALOR
                            case 5:
                                List<Transacao> menorValor = st.listarMenorValor();
                                st.listarFormatado(menorValor);
                            break;
                            //VOLTAR
                            case 6:
                                System.out.println("Voltando ao menu...");
                                menuLista = false;
                            break;
                            default:
                                System.out.println("Você digitou errado, tente novamente.");
                            break;
                        }
                    }
                break;
                //CONSULTAR ID
                case 3:
                    System.out.print("Digite o ID do item específico que deseja ver: ");
                    int id = lerInteiro(r);
                    st.consultarFormatado(id);
                break;
                //REMOVER TRANSAÇÃO
                case 4:
                    List<Transacao> todas = st.listarTodas();
                    st.listarFormatado(todas);
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("Qual item você deseja remover? [Digite o ID do item em específico]");
                    int idr = lerInteiro(r);
                    try {
                        st.removerFormatado(idr);
                    }catch (TransacaoNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                    }
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
    public static int lerInteiro(Scanner r) {
        while (true) {
            try {
                int valor = r.nextInt();
                r.nextLine();
                return valor;
            }catch ( InputMismatchException e) {
                r.nextLine();
                System.out.println("Entrada inválida. Digite apenas números: ");
            }

        }
    }
    public static double lerDouble(Scanner r) {
        while (true) {
            try {
                double valor = r.nextDouble();
                r.nextLine();
                return valor;
            }catch ( InputMismatchException e) {
                r.nextLine();
                System.out.println("Entrada inválida. Digite apenas números: ");
            }
        }
    }
}
