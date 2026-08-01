package personalfinanceorganizer;
public class ServicoTransacao {
    //ATRIBUTO 
    private RepositorioTransacao repositorio;
    
    //CONSTRUTOR
    public ServicoTransacao(RepositorioTransacao repositorio) {
        this.repositorio = repositorio;
    }
    
    //MÉTODOS
    public boolean verificaCadastro(Transacao t) {
        if (t.getCategoria().equalsIgnoreCase("Receita") ||
            t.getCategoria().equalsIgnoreCase("Despesa")) {
            repositorio.cadastrar(t);
            return true;
        }
        System.out.println("Você digitou o tipo da categoria errada, so há 2 categorias, Receita ou Despesa.");
        return false;
    }
    
    public double calcularSaldo() {
        Transacao[] transacoes = this.repositorio.listar();
        double saldo= 0;
        for (int i=0; i< this.repositorio.getContador() ; i++) {
            if(transacoes[i].getCategoria().equalsIgnoreCase("Receita")) {
                saldo = saldo + transacoes[i].getValor();
            }else {
                saldo = saldo - transacoes[i].getValor();
            }
        }
        return saldo;
    }
    public void listarFormatado() {
        Transacao[] transacoes = this.repositorio.listar();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                                    MINHAS TRANSAÇÕES                                       ");
        System.out.println("--------------------------------------------------------------------------------------------");
        if (this.repositorio.getContador()== 0) {
            System.out.println("Não possui nenhum item cadastrado no momento.");
        }else {
            for (int i=0;i < this.repositorio.getContador();i++) {
                System.out.println(transacoes[i]);
            }
        }
        }
    public void consultarFormatado(int id) {
        Transacao t = this.repositorio.consultar(id);
        if (t == null) {
            System.out.println("Não há itens cadastrados para buscar");
        }else {
            System.out.println(t);
        }
    }
    public void removerFormatado(int id) {
        Transacao t = this.repositorio.consultar(id);
        boolean removeu = this.repositorio.remover(id);
        if (removeu) {
            System.out.println("O item "+ t.getDescricao() +" foi removido com sucesso.");
        }else {
            System.out.println("O ID escolhido não foi encontrado. Digite apenas um dentre os disponíveis.");
        }
    }
    public void analisarSaude() {
        double saldo = calcularSaldo();
        double totalReceita = 0;
        double totalDespesas= 0;
        Transacao[] transacoes = this.repositorio.listar();
        for (int i=0;i< this.repositorio.getContador();i++) {
            if (transacoes[i].getCategoria().equalsIgnoreCase("Receita")) {
                totalReceita += transacoes[i].getValor();
            }else {
                totalDespesas += transacoes[i].getValor();
            }
        }
        System.out.println("----------------------------");
        System.out.println("     SAÚDE FINANCEIRA       ");
        System.out.println("----------------------------");
        System.out.println("Total Receitas: R$ " + String.format("%.2f", totalReceita));
        System.out.println("Total Despesas: R$ " + String.format("%.2f", totalDespesas));
        System.out.println("Saldo Atual:    R$ " + String.format("%.2f", saldo));
        System.out.println("----------------------------");
        
        if (saldo > 0) {
        System.out.println("""
        =============================================================================
        🟢 PARABÉNS! Seu saldo está positivo.

        Você terminou o período gastando menos do que ganhou.
        Agora seu objetivo é fazer esse dinheiro trabalhar por você.

        O QUE FAZER AGORA?

        ✔ Invista entre 20% e 50% deste saldo.
        ✔ Se já possui uma reserva de emergência, invista 100% do excedente.
        ✔ Evite aumentar seu padrão de vida apenas porque sobrou dinheiro.
        ✔ Analise quais foram seus maiores gastos e veja onde ainda pode economizar.

        OBJETIVO

        Transforme esse resultado em um hábito.
        Pequenas sobras recorrentes geram grandes patrimônios.

        "A riqueza não vem apenas de ganhar mais.
         Ela nasce da diferença entre o que você ganha e o que você gasta."

        =============================================================================
        """);
        } else if (saldo < 0) {
        System.out.println("""
        🔴 ATENÇÃO! Seu saldo está negativo.
        """);

        System.out.println("Suas despesas superam suas receitas em R$ "
        + String.format("%.2f", Math.abs(saldo)));

        System.out.println("""

        Neste período você gastou mais do que recebeu.
        A boa notícia é que isso pode ser corrigido rapidamente com algumas ações.

        O QUE FAZER AGORA?

        ✔ Reduza seus gastos em 10% a 20% no próximo mês.
        ✔ Identifique seus 3 maiores gastos.
        ✔ Corte pelo menos 1 despesa não essencial.
        ✔ Suspenda compras por impulso.
        ✔ Evite novos parcelamentos.
        ✔ Priorize quitar dívidas com juros mais altos.
        ✔ Se necessário, busque negociações de maior parcelamento da dívida, para
          ganhar tempo.

        OBJETIVO

        Primeiro volte ao saldo positivo.
        Depois pense em investir.

        "Livre-se das dívidas antes de buscar multiplicar seu dinheiro."

        =============================================================================
        """);

        } else {
        System.out.println("""
        =============================================================================                   
        🟡 Seu saldo terminou exatamente em zero. Terreno Perigoso!

        Você conseguiu manter o equilíbrio financeiro.
        Isso é melhor do que terminar no negativo, mas ainda não houve sobra para construir patrimônio.

        O QUE FAZER AGORA?

        ✔ Economize entre 5% e 10% da próxima renda.
        ✔ Revise seus 3 maiores gastos.
        ✔ Corte pelo menos 1 gasto não essencial.
        ✔ Planeje suas despesas antes do próximo período.

        OBJETIVO

        Seu foco agora é transformar um saldo zerado em um saldo positivo todos os meses.

        "Quem consegue guardar um pouco hoje,
        conquista muito mais liberdade amanhã."
        =============================================================================                                                                      
        """);
        }
    }
}
