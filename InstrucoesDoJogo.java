public class InstrucoesDoJogo implements Informacoes
{

    public void imprimirInformacoes(){
        System.out.print('\u000C');

        System.out.println("********************************************************************************");
        System.out.println("********************************************************************************");

        System.out.println("                            INSTRUCOES:\n");
        System.out.println("                            PERSONAGENS:\n");
        System.out.println("-Jogadores (Jogador Simples P1 e Jogador Suporte P2)\n-Inimigos(vírus).\n");
        System.out.println("O jogador P1 eh representado por P");
        System.out.println("O jogador P2 eh representado por S\n");
        System.out.println("********************************************************************************");

        System.out.println("                            OBJETIVO:\n");
        System.out.println("O objetivo do jogo eh encontrar a fonte de infecçao (@) de onde saem os inimigos.\n");
        System.out.println("Os jogadores tem ao todo 25 rodadas para encontrar este setor.");
        System.out.println("Caso nao encontrem a fonte os inimigos vencem.\n"); 

        System.out.println("********************************************************************************");
        System.out.println("                            MOVIMENTACAO:\n");
        System.out.println("Os jogadores so podem se mover nas direcoes para cima, baixo, esquerda ou direita.");
        System.out.println("Porem algumas direcoes (paredes) podem ser bloqueadas, impendindo a passagem.");
        System.out.println("\nQuando uma direçao estiver bloqueada sera exibida a seguinte informaçao:");
        System.out.println("\n   B - Indica que a passagem para BAIXO esta bloqueada\n   C - Indica que a passagem para CIMA esta bloqueada ");
        System.out.println("   E - Indica que a passagem para ESQUERDA esta bloqueada\n   D - Indica que a passagem para DIREITA esta bloqueada");
        System.out.println("\nOs jogadores começam no centro do tabuleiro Posicao:[3x3]\n");
        System.out.println("*******************************************************************************");

        System.out.println("                            GERACAO DE INIMIGOS:\n");
        System.out.println("Cuidado! A cada novo setor que o jogador visita, inimigos sao gerados\ne o jogador nao pode sair deste setor ate mata-los, ou ser morto por eles.");
        System.out.println("\nCada setor pode gerar de 1 a 5 inimigos \nObs: Setores ja visitados nao geram novos inimigos.\n");
        System.out.println("********************************************************************************");

        System.out.println("                            CARACTERISTICAS DOS JOGADORES:\n");
        System.out.println("Se tratando de jogadores, cada um tem suas caracteristicas individuais.\n");
        System.out.println("-JOGADOR SIMPLES(P1):  Ataque inicial = 2 e Defesa inicial = 6");
        System.out.println("-JOGADOR SUPORTE(P2):  Ataque inicial = 1 e Defesa inicial = 7\n");
        System.out.println("O JOGADOR SIMPLES pode:\n   1-Efetuar buscas no setor.\n   2-Atacar os inimigos.");
        System.out.println("\nO JOGADOR SUPORTE pode:\n   1-Efetuar buscas no setor.\n   2-Atacar inimigos.\n   3- HABILIDADE ESPECIAL: P2 pode recuperar 2 pts da defesa de P1 ou dele mesmo.\n");
        System.out.println("********************************************************************************");

        System.out.println("                            BUSCAS NOS SETORES:\n");
        System.out.println("As buscas nos setores podem dar alguns bonus aos jogadores.");
        System.out.println("Quando uma busca eh efetuada 4 açoes podem acontecer:\n");
        System.out.println("   1- Nada vai ser encontrado no setor\n   2- O jogador ganha 1 ponto de defesa\n   3- O jogador ganha 2 pontos de defesa\n   4- Todos os ininmigos do setor perdem 1 ponto de defesa.\n");
        System.out.println("Obs: O retorno da busca sera gerado de forma aleatoria.\n");
        System.out.println("********************************************************************************");

        System.out.println("                            INFORMACOES EXTRAS:\n");
        System.out.println("Os jogadores podem realizar duas açoes em sua rodada,\nlogo apos isso os inimigos iniciam seus ataques.\n");
        System.out.println("********************************************************************************");

        System.out.println("                            TIPOS DE SETORES:\n");
        System.out.println("Os setores podem ser:\n\n   1- Privados: O jogador fica impossibilitado de efetuar buscas\n   2- Oculto: Ataques podem nao acertar os inimigos\n   3- Normal: Ataques sempre acertam inimigos e buscas podem ser efetuadas.\n");
        System.out.println("********************************************************************************");

        System.out.println("                            QUANDO O JOGO TERMINA?\n");
        System.out.println("O jogo acaba quando:\n\n   1- Caso algum jogador encontre a fonte de infeccao (@)\n   2- Caso os inimigos matem o Jogador SIMPLES (P1)\n   3- Caso as 25 rodadas acabem (os inimigos vencem).\n");
        System.out.println("********************************************************************************");
        System.out.println("********************************************************************************");

    }
}
