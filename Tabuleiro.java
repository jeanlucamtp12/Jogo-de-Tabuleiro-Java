import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Tabuleiro  
{

    int i ,j,ciclo=1, linha_Fonte,coluna_Fonte,pega_Linha,pega_Coluna,linha_A=3, coluna_A=3 ; 
    int  linP2,colP2;

    Random random = new Random();
    String[][] tabuleiro  = new String[7][7];
    String[][] visitado = new String[6][6];
    Scanner teclado = new Scanner(System.in);
    Acoes_Setor acao = new Acoes_Setor();

    String veri_P;

    StringBuilder adiciona = new StringBuilder();                   //StringBuilder - classe que possibilita a concatenação de Strings
    String[][] paredes = new String[6][6];
    String[][] tipoSetor = new String[6][6];

    //Construtor padrão
    public Tabuleiro()
    {
    }

    //Método para verificar se na posição do tabuleiro especificada contém (os jogadores, fonte de infecção...)
    public String busca(String matriz[][],int linha,int coluna){
        for (i = 0; i < 6;i++){
            for(j = 0; j< 6 ;j++){
                if(matriz[linha][coluna] == "V")
                {
                    return "V";
                }
                if(matriz[linha][coluna] == "T")
                {
                    return "T";
                }

                if(matriz[linha][coluna] == "@")
                {
                    return "@";
                }
                if(matriz[linha][coluna] == "P")
                {
                    return "P";
                }
                if(matriz[linha][coluna] == "S")
                {
                    return "S";
                }

            }

        }

        return " ";
    }
    //Esse método coloca o jogador na posição do tabuleiro escolhida pelo usuário
    public void coloca_Jogador(String matriz[][],int linha,int coluna,int identificador)
    {
        if (busca(matriz,linha,coluna) == "T"){
            matriz[linha][coluna] = "T";
            return;
        }

        if (identificador == 1)
        {

            tabuleiro[linha][coluna] = "P";
        }
        if (identificador == 2)
        {          
            tabuleiro[linha][coluna] = "S";
        }
    }

    public String cria_Setor(int linha,int coluna,int rodada,int identificador)
    {		
        if (identificador == 1){          
            tabuleiro[linha_A][coluna_A] = " ";        
        }
        if (identificador == 2)
        {
            tabuleiro[linP2][colP2] = " ";

        }
        if (identificador == 1 && linha == linP2 && coluna == colP2)
        {
            tabuleiro[linha][coluna] = "T";
        }           
        if (identificador == 2 && linha == linha_A && coluna == coluna_A)
        {
            tabuleiro[linha][coluna] = "T";
        }

        if (identificador == 1 && linha_A == linP2 && coluna_A == colP2)
        {
            tabuleiro[linP2][colP2] = "S";

        }

        if (identificador == 2 && linha_A == linP2 && coluna_A == colP2)
        {
            tabuleiro[linha_A][coluna_A] = "P";

        }
        //Rodada 1
        if (rodada == 1 )
        { 
            //Assim que o jogador 1 fizer seu primeiro movimento, o tabuleiro será preenchido com " "
            if (identificador == 1){
                for(i=0;i<=6;i++)
                {
                    for(j = 0;j<=6;j++){
                        if (tabuleiro[linha][coluna] != "S" || tabuleiro[linha][coluna] != "P")
                            tabuleiro[i][j] = " ";                            

                    }
                }

                //Números aleatórios são gerados para definir a posição da fonte de infecção
                linha_Fonte = random.nextInt(6);
                coluna_Fonte = random.nextInt(6);
                //"While" certifica que as posições centrais nao sejam escolhidas
                while (linha_Fonte == 2 && coluna_Fonte == 3 || linha_Fonte == 3 || coluna_Fonte == 3 || linha_Fonte == 4 && coluna_Fonte == 3 || linha_Fonte == 3 && coluna_Fonte == 2||  linha_Fonte == 3 && coluna_Fonte == 4 || linha_Fonte == 0 || coluna_Fonte == 0){
                    linha_Fonte = random.nextInt(6);
                    coluna_Fonte = random.nextInt(6);            
                }
                //O tabuleiro na posição escolhida recebe a fonte de infecção representada por @
                tabuleiro[linha_Fonte][coluna_Fonte] = "@";  
            }
            //Chamada do método que coloca o jogador no tabuleiro e logo depois o método que cria as paredes do setor
            coloca_Jogador(tabuleiro,linha,coluna,identificador);
            criar_Paredes(linha,coluna,rodada,identificador); 

            if(identificador == 2 && busca(visitado,linha,coluna) == "V")
            {
                System.out.println("Direcoes Bloqueadas: "+paredes[linha][coluna] +"   |  (B=Baixo C=Cima E=Esquerda D=Direita)");
            }
            //Chamada do método imprimir
            imprimir();
            //Logo após a impressão do tabuleiro, é chamado o método efetuar_Batalha
            acao.efetua_Batalha(tipoSetor, visitado, linha,  coluna, identificador,linha_A, coluna_A,  linP2, colP2);

            //A matriz visitado recebe V para indicar que este setor foi visitado, assim como a posição central
            visitado[linha][coluna] = "V";
            visitado[3][3] = "V";         

            //indetificador == 1 significa Jogador 1. Com isso armazena a linha e coluna em que ele se encontra e caso contrário armazena as informações para Jogador 2
            if(identificador == 1){
                linha_A = linha;
                coluna_A = coluna;
            }else{
                linP2 = linha;
                colP2 = coluna;
            } 

        }else {
            //Caso a rodada seja diferente de 1 e o setor escolhido nao tenha sido visitado
            if(busca(visitado,linha,coluna) != "V");
            {
                //Chama o método busca para verificar se ali se encontra a fonte de infecção
                if (busca(tabuleiro,linha,coluna) == "@")
                {
                    paredes[linha][coluna] = "Venceu";        //caso esteja ali, printa se vencedor e retorna a informação na main
                    System.out.println("Parabens, jogador "+ identificador+"! Voce acaba de achar a fonte de infeccao e ganhou o jogo");
                    return paredes[linha][coluna];
                }

                if (busca(visitado,linha,coluna) != "V")
                { 
                    //Coloca o jogador no tabuleiro, cria as paredes e imprime o tabuleiro
                    coloca_Jogador(tabuleiro,linha,coluna,identificador);
                    criar_Paredes(linha,coluna,rodada,identificador); 
                    imprimir();
                    //Chamada do método que realiza a batalha no setor
                    acao.efetua_Batalha(tipoSetor, visitado, linha,  coluna, identificador,linha_A, coluna_A,  linP2, colP2);
                    if(identificador == 1){
                        linha_A = linha;
                        coluna_A = coluna;
                    }else{
                        linP2 = linha;
                        colP2 = coluna;
                    } 

                }
            }
            // Caso o setor já tenha sido visitado
            if (busca(visitado,linha,coluna) == "V"){     
                //Neste caso não se cria paredes, visto que o setor ja havia sido visitado
                coloca_Jogador(tabuleiro,linha,coluna,identificador); 
                System.out.println("Direcoes Bloqueadas: "+paredes[linha][coluna] +"   |  (B=Baixo C=Cima E=Esquerda D=Direita)");

                imprimir();
                acao.efetua_Batalha(tipoSetor, visitado, linha,  coluna, identificador,linha_A, coluna_A,  linP2, colP2);

                if(identificador == 1){
                    linha_A = linha;
                    coluna_A = coluna;
                }else{
                    linP2 = linha;
                    colP2 = coluna;
                }
            }
            //A matriz que armazena as visitas contabiliza que o setor acabou de ser visitado.
            visitado[linha][coluna] = "V";      

        }
        //Retorna a relação de paredes bloqueadas para a main
        return paredes[linha][coluna];
    }

    //Método para definir as direções (paredes) bloqueadas
    public void criar_Paredes(int linha, int coluna, int rodada,int identificador)
    {
        //Primeiro verifica se o setor ja foi visitado, visto que setores visitados não podem gerar novas portas (V == Visitado)
        if (busca(visitado,linha,coluna) != "V"){
            //Caso o setor nao tenha sido visitado, gera um número aleatório para cada direção e um número aleatório para definir o tipo do setor
            int r_Baixo = random.nextInt(10);
            int r_Esquerda = random.nextInt(10);
            int r_Cima = random.nextInt(10);
            int r_Direita = random.nextInt(10);
            int defineSetor = random.nextInt(4);

            if (defineSetor == 0 || defineSetor == 1)    //Se a variável defineSetor for igual a 0 ou 1, o setor será Normal
            {
                tipoSetor[linha][coluna] = "Normal";
            }else if (defineSetor == 2)
            {
                tipoSetor[linha][coluna] = "Oculto";     //igual a 2 será Oculto
            }else{
                tipoSetor[linha][coluna] = "Privado";    //igual a 3 Privado... (O tipo do setor é armazenado na matriz tipoSetor)
            }

            //Caso o jogador tenha digitado para ir para baixo, a direção para cima não pode ser bloqueada, já que ele veio de lá
            //identificador == 1 (Jogador P1) e identificador == 2 (Jogador P2)
            //linha_A = posição atual do jogador 1 e linP2 = linha atual do jogador 2
            if((linha == linha_A + 1 && identificador == 1) || (linha == linP2 + 1 && identificador == 2) ){

                if(r_Baixo < 3){             //Se a variável que define o bloqueio para baixo for menor que 3, a variável adiciona recebe B (B == baixo) 
                    adiciona.append("B");
                }
                if(r_Esquerda < 3){          //Se faz o mesmo para Esquerda(E) e Direita(D)
                    adiciona.append("E");
                }

                if(r_Direita < 3){
                    adiciona.append("D");
                }
            }
            //Se faz o mesmo processo, porém agora caso o jogador escolha ir para cima
            if((linha == linha_A - 1 && identificador == 1) || (linha == linP2 - 1 && identificador == 2)){

                if(r_Esquerda < 3){
                    adiciona.append("E");
                }
                if(r_Cima < 3){
                    adiciona.append("C");
                }
                if(r_Direita < 3){
                    adiciona.append("D");
                }
            }
            //Se faz o mesmo processo, porém agora caso o jogador escolha ir para Direita
            if((coluna == coluna_A + 1 && identificador == 1) || (coluna == colP2 + 1 && identificador == 2)){
                if(r_Baixo < 3){
                    adiciona.append("B");
                }

                if(r_Cima < 3){
                    adiciona.append("C");
                }
                if(r_Direita < 3){
                    adiciona.append("D");
                }
            }
            //Se faz o mesmo processo, porém agora caso o jogador escolha ir para Esquerda
            if((coluna == coluna_A - 1 && identificador == 1) || (coluna == colP2 - 1 && identificador == 2)){
                if(r_Baixo < 3){
                    adiciona.append("B");
                }
                if(r_Esquerda < 3){
                    adiciona.append("E");
                }
                if(r_Cima < 3){
                    adiciona.append("C");
                }

            }
            //Por fim a matriz paredes recebe quais as direções foram bloqueadas (exemplo: BC -> indica que as direções Baixo e Cima foram bloqueadas)
            paredes[linha][coluna] = adiciona.toString();       
            veri_P = paredes[linha][coluna];     

            System.out.println("Direcoes Bloqueadas: "+paredes[linha][coluna] +"   |  (B=Baixo C=Cima E=Esquerda D=Direita)");
            //Apaga o que havia armazenado na variável adiciona
            adiciona.setLength(0);  
        }
    }

    //Método que imprime o tabuleiro 
    public void imprimir()
    {

        for (i = 0 ; i <= 6; i++){    
            for (j = 0 ; j <= 6 ; j++) {

                if (i == 0  || j == 6 )     //Imprime as linhas do tabuleiro com a devida numeração
                { 
                    if (j != 0 && j != 6)
                        System.out.printf("|__"+j+"__|");
                    else
                        System.out.printf("|__*__|");

                }else if (j == 0 || i == 6 )    //Imprime as colunas do tabuleiro com a devida numeração
                {
                    if (i != 0 && i != 6)
                        System.out.printf("|__"+i+"__|");
                    else
                        System.out.printf("|__*__|");

                }else if (i == 3 && j == 3)      //Printa X no centro do tabuleiro
                {
                    System.out.print("|__X__|");
                } else {
                    if (tabuleiro[i][j] == "@")  //Printa " " nos lugares onde nao houver jogadores 
                    {   
                        System.out.print("|__ __|");                   
                    }
                    if (tabuleiro[i][j] != "T" || tabuleiro[i][j] != "P" || tabuleiro[i][j] != "S")  //Printa " " nos lugares onde nao houver jogadores 
                    {
                        if(tabuleiro[i][j] != "@")
                            System.out.print ("|__"+tabuleiro[i][j]+"__|");
                    }
                }

            }
            System.out.println();

        }

    }
}
