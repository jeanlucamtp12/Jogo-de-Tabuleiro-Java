import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class Principal
{   int i,j;
    Scanner teclado = new Scanner(System.in);
    String veri_P, retorno;
    int []  retLinCol = new int[2];

    //Verifica se a matriz que armazena as informações sobre paredes contém a palavra Venceu ou * que se refere à setores inacessíveis no tabuleiro.
    //Caso a matriz venha a receber a palavra venceu, retorna-se essa informação para indicar o fim do jogo.
    public String busca(String matriz[][],int linha,int coluna){

        for (i = 0; i < 6;i++){
            for(j = 0; j< 6 ;j++){         
                if(matriz[linha][coluna] == "*")
                {
                    return "*";
                }       
                if(matriz[linha][coluna] == "Venceu")
                {
                    return "Venceu";
                }     
            }
        }
        return " ";
    }

    //Solicita ao jogador digitar a posição novamente.
    public int[] digitar_Novamente(int linha, int coluna, int linha_A, int coluna_A, int linP2, int colP2,int identificador,String paredes[][])
    {

        System.out.println("JOGADA INVALIDA!!\nDigite novamente Linha e Coluna:");
        linha = teclado.nextInt();
        coluna = teclado.nextInt(); 

        retLinCol[0] = linha;
        retLinCol[1] = coluna;

        verifica_Jogada(paredes,linha,coluna,identificador,linha_A,coluna_A,linP2,colP2);
        return retLinCol;
    }

    //Verifica se a posição digitada pelo jogador é válida e caso não for, chama o método digitar_Novamente.
    public int[] verifica_Jogada(String paredes[][], int linha, int coluna, int identificador,int linha_A,int coluna_A,int linP2,int colP2){   

        String veri_P;

        if (identificador == 1)
        {

            veri_P = paredes[linha_A][coluna_A];

            if (((linha != linha_A + 1 && linha != linha_A - 1 ) || coluna != coluna_A) && ( (coluna != coluna_A + 1 && coluna != coluna_A - 1 ) || linha != linha_A ) )
            {
                retLinCol = digitar_Novamente( linha, coluna, linha_A, coluna_A, linP2, colP2, identificador,paredes);
            }
        }else {
            if (((linha != linP2 + 1 && linha != linP2 - 1 ) || coluna != colP2) && ( (coluna != colP2 + 1 && coluna != colP2 - 1 ) || linha != linP2 ) )
            {
                retLinCol = digitar_Novamente(linha, coluna, linha_A, coluna_A, linP2, colP2, identificador,paredes);
            }
            veri_P = paredes[linP2][colP2];
        } 

        if ((linha < 1 || linha > 5) && (coluna < 1 || coluna > 5))
        {
            retLinCol = digitar_Novamente( linha, coluna, linha_A, coluna_A, linP2, colP2, identificador,paredes);
        }

        //Caso a posição que o jogador digite para se mover for uma parede bloqueada, chama o método digitar_Novamente;
        if((linha == linha_A + 1 && identificador == 1) || (linha == linP2 + 1 && identificador == 2)){     
            if (veri_P == null)
            {
                veri_P = " ";
            }

            if(veri_P.contains("B") && veri_P != " "){
                retLinCol = digitar_Novamente( linha, coluna, linha_A, coluna_A, linP2, colP2, identificador,paredes);
            }
        }
        if((linha == linha_A - 1 && identificador == 1) || (linha == linP2 - 1 && identificador == 2))
        {

            if (veri_P == null)
            {
                veri_P = " ";
            }
            if(veri_P.contains("C") && veri_P != " "){       
                retLinCol = digitar_Novamente( linha, coluna, linha_A, coluna_A, linP2, colP2, identificador,paredes);
            }
        }
        if((coluna == coluna_A + 1 && identificador == 1) || (coluna == colP2 + 1 && identificador == 2))
        {
            if (veri_P == null)
            {
                veri_P = " ";
            }

            if(veri_P.contains("D") && veri_P != " "){  
                retLinCol = digitar_Novamente( linha, coluna, linha_A, coluna_A, linP2, colP2, identificador,paredes);

            }
        }
        if((coluna == coluna_A - 1 && identificador == 1) || (coluna == colP2 - 1 && identificador == 2))
        {
            if (veri_P == null)
            {
                veri_P = " ";
            }

            if(veri_P.contains("E") && veri_P != " "){
                retLinCol = digitar_Novamente( linha, coluna, linha_A, coluna_A, linP2, colP2, identificador,paredes);

            }  
        }
        return retLinCol;
    }

    public static void main(String args[])
    { 
        System.out.print('\u000C');
        Principal controla = new Principal();
        Scanner teclado = new Scanner(System.in);
        int  linha = -1 ,coluna = -1,i;

        int ret2,ciclo = 1,ret,identificador,linha_A=3,coluna_A=3,linP2=3,colP2=3,opcao,opc =0;
        Random random = new Random();
        String direcao;
        boolean continuar = true;
        int [][] pos = new int[6][6];
        String[] retorno = new String[3];
        String[][] recebeMatriz = new String[6][6];
        Tabuleiro[][] recebeMat = new Tabuleiro[6][6]; 
        int [] retLinCol = new int[2];

        Informacoes instrucoes = new InstrucoesDoJogo();
        Informacoes sobre = new ConceitosJavaUsados();

        Tabuleiro jogadas = new Tabuleiro();  

        do {  
            try{
                //Caso o jogador digite as opçoes validas, o programa saira do do while onde esta os tratamentos de exceçoes
                if(opc == 1 || opc == 2 || opc == 3 || opc == 4){
                    break;
                }
                System.out.println("BEM VINDO AO JOGO ANTIVIRUS POR UM DIA!!!\nDIGITE:\n1 - PARA JOGAR\n2 - PARA LER AS REGRAS\n3 - INFORMAÇOES SOBRE OS CONCEITOS JAVA USADOS NO PROJETO\n4 - PARA SAIR ");

                opc = teclado.nextInt();

            }
            catch(Exception e)
            {
                System.err.print(e);
                System.out.println("\nErro! Voce digitou uma opcao invalida. Digite novamente: " + teclado.nextLine());
            }

        }while(continuar);

        opcao = opc;
        System.out.print('\u000C');
        if (opcao == 2)
            instrucoes.imprimirInformacoes();

        if (opcao == 3)
            sobre.imprimirInformacoes(); 

        //Enquanto o jogador digitar a opcao 2 ou 3, o programa ira continuar mostrando as informacoes de acordo com que ele clicou, ate que ele digite outra opcao.
        while(opcao == 2 || opcao == 3)
        {

            System.out.println("DESEJA LER NOVAMENTE AS INFORMACOES SOBRE O JOGO OU IREMOS AO COMBATE??\nDIGITE:\n1 - PARA JOGAR\n2 - PARA LER AS REGRAS\n3 - INFORMAÇOES SOBRE OS CONCEITOS JAVA USADOS NO PROJETO\n4 - PARA SAIR ");
            opcao = teclado.nextInt();
            if (opcao == 2)
                instrucoes.imprimirInformacoes();

            if (opcao == 3)
                sobre.imprimirInformacoes(); 

        } 
        System.out.print('\u000C');

        //Caso a opcao for igual a 1, inicia-se o jogo.
        continuar = true;
        if ( opcao == 1 ){

            while (ciclo <= 25)
            {
                //Primeira rodada
                if (ciclo == 1)
                {			
                    System.out.println("\n");
                    for(i = 1; i <=2 ; i++)
                    {

                        do {  
                            try{
                                //Caso o jogador digite as opçoes validas, o programa saira do do while onde esta os tratamentos de exceçoes

                                if (linha > 0 && coluna > 0)
                                    break;

                                System.out.println ("PRIMEIRA RODADA");
                                System.out.println("Jogador "+i+", escolha uma direcao para se mover!");
                                System.out.println("Digite a linha e a coluna que deseja se mover: ");
                                linha = teclado.nextInt();
                                coluna = teclado.nextInt();

                            }
                            catch(Exception e)
                            {
                                System.err.print(e);
                                System.out.println("\nErro! Voce digitou uma opcao invalida. Digite novamente: " + teclado.nextLine());
                            }

                        }while(continuar);

                        //Verifica se a jogada e valida
                        retLinCol = controla.verifica_Jogada(recebeMatriz,linha,coluna,i,linha_A,coluna_A,linP2,colP2);
                        //Pega o retorno apos verificar se a jogada e permitida e atribui a linha e coluna.
                        if (retLinCol[0] != 0){
                            linha = retLinCol[0];
                            coluna = retLinCol[1];
                            retLinCol[0] = 0;
                            retLinCol[1] = 0;
                        }

                        if(i == 1)
                        {
                            linha_A = linha;
                            coluna_A = coluna;
                        }else{
                            linP2 = linha;
                            colP2 = coluna;
                        }

                        //A matriz recebeMatriz recebe o retorno do metodo cria_Setor da classe Tabuleiro
                        recebeMatriz[linha][coluna] = jogadas.cria_Setor(linha,coluna,ciclo,i);

                        //Vez do jogador Suporte

                        linha = -1; coluna = -1;
                    }
                }else{ 

                    System.out.println("\n");

                    identificador = 1;

                    //Verifica se o jogador esta morto e caso esteja, printa notificando a derrota.
                    if(jogadas.acao.statusJogador(identificador) == 1)
                    {
                        System.out.println("FIM DE JOGO! JOGADOR 1 ESTA MORTO");
                        System.out.println("INIMIGOS VENCERAM");
                        break;
                    }

                    //Verifica se ainda ha inimigos no setor que o jogador esta.
                    if (jogadas.acao.statusInimigosNoSetor(linha_A,coluna_A) > 0 && identificador == 1){

                        recebeMatriz[linha_A][coluna_A] = jogadas.cria_Setor(linha_A,coluna_A,ciclo,1);

                    }else{ 

                        linha = -1; coluna = -1;
                        continuar = true; 

                        do {  
                            try{
                                //Caso o jogador digite as opçoes validas, o programa saira do do while onde esta os tratamentos de exceçoes

                                if (linha > 0 && coluna > 0)
                                    break;

                                System.out.println("Jogador Simples, escolha uma direcao para se mover! ");
                                System.out.println("Jogador se atente as paredes bloqueadas: "+recebeMatriz[linha_A][coluna_A]);
                                System.out.println("Digite a linha e a coluna que deseja se mover: ");

                                linha = teclado.nextInt();
                                coluna = teclado.nextInt(); 

                            }
                            catch(Exception e)
                            {
                                System.err.print(e);
                                System.out.println("\nErro! Voce Digitou uma Opcao Invalida, Digite Novamente" + teclado.nextLine());
                            }

                        }while(continuar);

                        retLinCol = controla.verifica_Jogada(recebeMatriz,linha,coluna,1,linha_A,coluna_A,linP2,colP2);
                        if (retLinCol[0] != 0){
                            linha = retLinCol[0];
                            coluna = retLinCol[1];
                            retLinCol[0] = 0;
                            retLinCol[1] = 0;
                        }

                        linha_A = linha;
                        coluna_A = coluna;
                        recebeMatriz[linha][coluna] = jogadas.cria_Setor(linha,coluna,ciclo,1);

                        //Caso a palavra Venceu seja retornada pelo metodo busca, o programa se encerrara.
                        if (controla.busca(recebeMatriz,linha,coluna) == "Venceu")
                        {
                            break;
                        }

                    } 

                    //Vez do jogador 2

                    identificador = 2;

                    if (jogadas.acao.statusInimigosNoSetor(linP2,colP2) > 0 && identificador == 2 && jogadas.acao.statusJogador(2) != 2){

                        recebeMatriz[linP2][colP2] = jogadas.cria_Setor(linP2,colP2,ciclo,2);

                    }else{

                        if(jogadas.acao.statusJogador(2) == 2){
                            System.out.println("Jogador 2 esta morto, entao nao pode efetuar jogadas!");
                        }else{  

                            linha = -1; coluna = -1;
                            continuar = true; 

                            do {  
                                try{
                                    //Caso o jogador digite as opçoes validas, o programa saira do do while onde esta os tratamentos de exceçoes

                                    if (linha > 0 && coluna > 0)
                                        break;

                                    System.out.println("Jogador Suporte escolha uma direcao para se mover!");
                                    System.out.println("Jogador se atente as paredes bloqueadas: "+recebeMatriz[linP2][colP2]);
                                    System.out.println("Digite a linha e a coluna que deseja se mover: ");

                                    linha = teclado.nextInt();
                                    coluna = teclado.nextInt();

                                }
                                catch(Exception e)
                                {
                                    System.err.print(e);
                                    System.out.println("\nErro! Voce digitou uma opcao invalida. Digite novamente: " + teclado.nextLine());
                                }

                            }while(continuar);

                            if (retLinCol[0] != 0){
                                linha = retLinCol[0];
                                coluna = retLinCol[1];
                            }

                            linP2 = linha;
                            colP2 = coluna;
                            recebeMatriz[linha][coluna] = jogadas.cria_Setor(linha,coluna,ciclo,2);

                            if (controla.busca(recebeMatriz,linha,coluna) == "Venceu")
                            {
                                break;
                            }

                        }
                    }
                }

                //Quando o jogador concluir as 25 rodadas, o jogo ira terminar.
                if (ciclo == 25)
                {
                    System.out.println("As rodadas acabaram e os jogadores P1 e P2 nao encontraram a fonte!! FIM DE JOGO!");
                    break;
                }
                ciclo++;
                System.out.println("RODADA: "+ ciclo);
                retLinCol[0] = 0;
                retLinCol[1] = 0;

            }
            //Caso o jogador digite o numero 4, o programa se encerrara.
        }else if (opc == 4)
        {
            System.out.println("Programa encerrado.");
            return;
        }
    }
}
