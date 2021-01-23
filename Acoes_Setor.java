import java.util.Random;
import java.util.Scanner;

public class Acoes_Setor
{
    Scanner teclado = new Scanner(System.in);
    Random random1 = new Random();
    int opcao,opcaoInimigo,i,j;
    Random random2 = new Random();
    Inimigos[][][] inimigos = new Inimigos[6][6][6];
    boolean continuar;
    Jogador p1 = new Jogador_Simples(2,6);
    Jogador p2 = new Jogador_Suporte(1,7);

    int[][] defesasIni = new int[6][6];
    int quantidade,aleatorio ;

    int valorJogada , valorJogador,valorInimigo,setorBusca,pontoJogadores,temp;
    int somaaqui = 0; 

    public Acoes_Setor()
    {

    }
    //Verifica se o jogador possue defesa ou seja se esta vivo
    public int statusJogador(int identificador)
    {
        if (identificador == 1 && p1.getDef() <= 0)
        {
            return 1;

        }
        if (identificador == 2 && p2.getDef() <= 0)
        {
            return 2;

        }

        return 0;
    }
    //Verifica se ha inimigos vivos no setor
    public int statusInimigosNoSetor(int linha,int coluna)
    {
        for (i = 0 ; i < inimigos.length ; i++){
            if (inimigos[linha][coluna][i] != null)
            {
                if  (inimigos[linha][coluna][i].getDef() > 0)
                    return 1;
            }
        }

        return 0;
    }
    //Metodo para efetuar as buscas nos setores
    public void buscaSetor (int identificador,int quantidade,int linha ,int coluna)

    {
        System.out.println("\n");

        setorBusca = random1.nextInt(7);

        if (setorBusca >= 0 && setorBusca <= 3)
        {
            System.out.println("Nada foi encontrado no setor\n");
        }

        if (setorBusca == 4)
        {
            System.out.println("Jogador "+identificador +", voce encontrou uma poçao de cura! Recuperou 1 ponto de defesa\n");
            if (identificador == 1)
            {

                pontoJogadores = p1.getDef() + 1;
                p1.setDef(pontoJogadores);
                System.out.println("Novo valor da Defesa "+p1.getDef()+"\n");

            }else{
                pontoJogadores = p2.getDef() + 1;
                p2.setDef(pontoJogadores);
                System.out.println("Novo valor da Defesa "+p2.getDef()+"\n");

            }
        }

        if (setorBusca == 5)
        {
            System.out.println("Jogador "+identificador +", voce encontrou uma super poçao de cura! Recuperou 2 pontos de defesa");
            if (identificador == 1)
            {

                pontoJogadores = p1.getDef() + 2;
                p1.setDef(pontoJogadores);      
                System.out.println("Novo valor da Defesa "+p1.getDef()+"\n");

            }else{
                pontoJogadores = p2.getDef() + 2;
                p2.setDef(pontoJogadores);      
                System.out.println("Novo valor da Defesa "+p2.getDef()+"\n");

            }

        }

        if (setorBusca == 6)
        {
            if (retornaDefesaInimigos(inimigos,linha,coluna) > 0){
                System.out.println("QUEDA DE ENERGIA NO SETOR!! Todos os inimigos perderam 1 ponto de defesa\n");

                for(int k = 0; k < inimigos.length; k++)
                {
                    if (inimigos[linha][coluna][k] != null)
                    {
                        valorInimigo = inimigos[linha][coluna][k].getDef();
                        if (valorInimigo <= 1)
                            valorInimigo = 0;
                        else
                            valorInimigo = valorInimigo - 1;

                        inimigos[linha][coluna][k].setDef(valorInimigo);

                    }

                } printaInimigos( inimigos, quantidade, identificador,linha,  coluna);

            }else{
                System.out.println("Nada foi encontrado no setor\n");
            }
        }

    }
    //Metodo para verificar o tipo do setor
    public String busca(String matriz[][],int linha,int coluna){
        for ( i = 0; i < 6;i++){
            for( j = 0; j< 6 ;j++){
                if(matriz[linha][coluna] == "V")
                {
                    return "V";
                }

                if(matriz[linha][coluna] == "Normal")
                {
                    return "N";
                }
                if(matriz[linha][coluna] == "Privado")
                {
                    return "P";
                }
                if(matriz[linha][coluna] == "Oculto")
                {
                    return "O";
                }
            }

        }

        return " ";
    }
    //Metodo que retorna a soma da defesa dos inimigos
    public int retornaDefesaInimigos(Inimigos inimigos[][][],int linha,int coluna){
        somaaqui = 0;
        for ( i = 0; i < inimigos.length ;i++){
            if (inimigos[linha][coluna][i] != null)
            {
                somaaqui = somaaqui + inimigos[linha][coluna][i].getDef();

            }               

        }

        return somaaqui;
    }
    //Metodo para verificar se determinado innimigo ainda possue defesa(esta vivo)
    public int verficarDefesaInimigos(Inimigos inimigos[][][],int posicao, int linha , int coluna){

        for(i = 0 ; i < inimigos.length ; i++){

            if (inimigos[linha][coluna][posicao] != null){
                temp = inimigos[linha][coluna][posicao].getDef();
            }
        }

        return temp;

    }
    //Metodo que printa os inimigos gerados em determinado setor
    public void printaInimigos(Inimigos inimigos[][][],int quantidade,int identificador,int linha, int coluna){

        System.out.println("\n");

        System.out.println("Inimigos gerados no setor:    |   Localizacao do setor: Linha: "+ linha+" Coluna: "+coluna);                 

        for (int k = 0 ; k < inimigos.length  ; k++)
        {
            if (inimigos[linha][coluna][k] != null)
                System.out.println((k)+" - (*_*)  ATAQUE = "+ inimigos[linha][coluna][k].getAtk()+ "|  DEFESA = "+inimigos[linha][coluna][k].getDef());                 
        }

    }
    //Metodo responsavel por gerar inimigos em cada setor
    public void geraInimigos(int linha , int coluna,int identificador)
    {
        int somaDefesas = 0 ;
        //Define de forma aleatoria quantos inimigos vao ser criados no setor x
        quantidade= random1.nextInt(6);
        while(quantidade == 0)
        {
            quantidade = random1.nextInt(6);
        }
        //Adiciona os inimigos gerados a matriz inimigos
        for ( i = 0 ; i < inimigos.length; i++)
        {
            for (j = 0 ; j < inimigos.length ; j++ )
            {
                for (int k= 0 ; k < quantidade ; k++ )
                {
                    Inimigos ini1 = new Inimigos(0,0);
                    inimigos[linha][coluna][k] = ini1;              
                }
            }                       
        }   

        for ( i = 0 ; i < inimigos.length; i++)
        {

            for (j = 0 ; j < inimigos.length ; j++ )
            {
                for (int k= 0 ; k < quantidade ; k++ )
                {
                    //Gera de forma aleatoria o valor de ATK/DEF dos inimigos 
                    int gera_Atk_Def = random1.nextInt(4);
                    while(gera_Atk_Def == 0)
                    {
                        gera_Atk_Def = random1.nextInt(4);

                    } 
                    somaDefesas = somaDefesas + gera_Atk_Def;     //Soma a defesa total de todos os inimigos gerados
                    //Atribui aos inimigos os valores de ATK e DEF atraves dos metodos set    
                    inimigos[linha][coluna][k].setAtk(gera_Atk_Def) ;   
                    inimigos[linha][coluna][k].setDef(gera_Atk_Def) ;

                }
            }                       
        }   

        for (i = 0 ; i < 6 ; i ++)
        {
            for(j = 0 ; j < 6; j ++){
                defesasIni[linha][coluna] = somaDefesas;                   //A matriz defesasIni guarda o valor da defesa dos inimmigos somadas
            }
        }

    } 

    public void efetua_Batalha(String tipoSetor[][],String visitado[][],int linha, int coluna,int identificador,int linha_A, int coluna_A, int linP2, int colP2)
    {

        int controle ;

        if(identificador == 1){
            System.out.println("Tipo do Setor: "+tipoSetor[linha][coluna] +"   |   Jogador "+identificador + " Atk = "+p1.getAtk() + " Def = "+p1.getDef());
        }else{
            System.out.println("Tipo do Setor: "+tipoSetor[linha][coluna] +"   |   Jogador "+identificador + " Atk = "+p2.getAtk() + " Def = "+p2.getDef());
        }

        if (busca(visitado,linha,coluna) != "V")    //Se o setor nao tiver sido visitado gera se os inimigos para este setor
        {
            geraInimigos(linha,coluna,identificador);
        }

        printaInimigos(inimigos,quantidade,identificador,linha,coluna);
        //Caso  haja inimigos vivos no setor a batalha eh iniciada
        if (retornaDefesaInimigos(inimigos,linha,coluna) > 0){

            System.out.println();                 

            System.out.println("Vamos Comecar a Batalha:");
            //A variavel controle recebe o valor da defesa do inimigos somadas
            controle = retornaDefesaInimigos(inimigos,linha,coluna) ;

            if (identificador == 1 ||  p2.getDef() > 0)
            //Chamada metodo vezJogador
                vezJogador( linha, coluna , identificador,  controle,linha_A,coluna_A,linP2,colP2,tipoSetor );
            if(identificador == 2 || p2.getDef() <= 0){

                for (int i = 0; i < 2; i++)
                {  
                    if ( identificador == 2 && linha == linha_A && coluna == coluna_A )
                    {
                        vezInimigos( linha, coluna , 2,  controle , linha_A, linP2, coluna_A,colP2);

                        break;

                    }else {

                        if (i == 0)
                        {
                            vezInimigos( linha, coluna , 1,  controle , linha_A, linP2, coluna_A,colP2);
                        }
                        if (i == 1 && p2.getDef() > 0){
                            vezInimigos( linha, coluna , 2,  controle , linha_A, linP2, coluna_A,colP2);

                        }
                    }

                } 
            }

        }else{
            System.out.println("Todos os Inimigos do Setor Foram Mortos\n");
            //Caso os inimigos estejam mortos controle recebe -1000
            controle = -1000 ;
            //Chamada metodo vezJogador
            vezJogador( linha, coluna , identificador,  controle,linha_A,coluna_A,linP2,colP2,tipoSetor );

        }

    }

    public void vezJogador(int linha, int coluna ,int identificador, int controle,int linha_A, int coluna_A, int linP2,int colP2,String tipoSetor[][] )
    {

        int i ,habilidade;
        i = 0;

        while (controle > 0 || controle == -1000)
        { 
            if(identificador == 1 && busca(tipoSetor,linha,coluna) == "P" && retornaDefesaInimigos(inimigos,linha,coluna) <= 0)
            {
                System.out.println("Muito bem, jogador! Os inimigos foram mortos neste setor e nao ha mais o que fazer aqui. Voce esta em um Setor Privado e nao pode procurar. Passaremos a vez ao seu companheiro P2\n");
                break;
            }

            if(i == 2)
                break;

            while(i <= 1){
                System.out.println("\n");
                if(identificador == 1 && busca(tipoSetor,linha,coluna) == "P" && retornaDefesaInimigos(inimigos,linha,coluna) <= 0)
                {
                    break;
                }
                System.out.println("Jogador "+identificador+" qual acao vc deseja realizar: \n1 - Atacar Inimigo\n2 - Procurar no Setor");
                if(identificador == 2)
                    System.out.println("3 - Recuperar Defesa");

                if (controle == -1000)                
                    System.out.println("\nObs: Lembre-se jogador, a ppcao Atacar Inimigos nao pode ser escolhida nessa rodada. Voce ja matou todos deste setor");

                opcao = teclado.nextInt();  
                //Se a defesa dos inimigos for <= 0 nao ha inimigos para atacar
                while (opcao == 1 && retornaDefesaInimigos(inimigos,linha,coluna) <= 0 )
                {
                    System.out.println("\nNao ha inimigos para matar. Selecione outra opcao");
                    opcao = teclado.nextInt();

                }

                if(identificador == 1){
                    //Se o setor for privado buscas serao bloqueadas para o jogador 1
                    while ((opcao == 2 && busca(tipoSetor,linha,coluna) == "P") || opcao != 1 && opcao != 2)
                    {
                        if (busca(tipoSetor,linha,coluna) == "P" && opcao == 2)
                            System.out.println("\nVoce esta em um Setor Privado. Nao eh possivel fazer busca. Escolha outra Acao: ");
                        else
                            System.out.println("Jogador "+identificador+" voce nao pode fazer esta escolha. Digite novamente: ");
                        opcao = teclado.nextInt();
                    }

                }else{
                    //Se o setor for privado buscas serao bloqueadas para o jogador 2 (ele tem uma opcao a mais que P1)
                    while ((opcao == 2 && busca(tipoSetor,linha,coluna) == "P") || opcao != 1 && opcao != 2 && opcao != 3 )                     
                    {
                        System.out.println("Jogador "+identificador+" voce nao pode fazer esta escolha. Digite novamente: ");
                        opcao = teclado.nextInt();
                    }

                }

                if (opcao == 1)
                {

                    continuar = true;

                    do {  
                        try{
                            //Caso o jogador digite as opçoes validas, o programa saira do do while onde esta os tratamentos de exceçoes

                            System.out.println("Digite Qual dos Inimigos Deseja Atacar. Informe o Numero do Inimigo");
                            opcaoInimigo = teclado.nextInt();     

                            if ( inimigos[linha][coluna][opcaoInimigo] != null)     
                                break;
                            else 					//Força a excecao de que o inimigo nao consta na matriz de inimigos
                                throw new Exception("\nErro! Neste setor esse inimigo nao foi gerado" );     

                        }
                        catch(Exception e)
                        {
                            System.err.print(e);
                            System.out.println("\nErro! " + teclado.nextLine());
                        }

                    }while(continuar);	
                    //Caso o inimigo nao tenha sido gerado, ou a posicao escolhida fuja do intervalo de 0 a 4(posicoes existentes)
                    while (opcaoInimigo < 0 || opcaoInimigo > 4 ||  inimigos[linha][coluna][opcaoInimigo].getDef() <= 0 )
                    {               
                        System.out.println("Esse inimigo esta morto ou nao existe. Escolha outro inimigo para atacar");
                        opcaoInimigo = teclado.nextInt();
                        //Caso uma opcao valida seja selecionada e o inimigo exista e esteja vivo -> valor Jogada recebe a defesa do inimigo escolhido para ser atacado
                        if ((opcaoInimigo >= 0 && opcaoInimigo <= 4) &&  inimigos[linha][coluna][opcaoInimigo] != null){
                            if (inimigos[linha][coluna][opcaoInimigo].getDef() <= 0 )
                                valorJogada = inimigos[linha][coluna][opcaoInimigo].getDef();
                        }else{
                            opcaoInimigo = -1;

                        }
                    }

                    //Se o setor for oculto um numero aleatorio definira se o inimigo eh atacado ou nao, se for 0 o inimigo nao eh acertado
                    if (busca(tipoSetor,linha,coluna) == "O")
                    {
                        aleatorio = random1.nextInt(3);

                        if (aleatorio == 0 )
                        {
                            System.out.println("Que pena! Voce esta em um Setor Oculto e isso ofuscou sua visao. O inimigo escapou do seu ataque");
                            printaInimigos(inimigos,quantidade,identificador,linha,coluna);
                            i++;
                            continue ;

                        } 

                    }       
                    //Se o identifcador for 1 o valorJogador recebe o valor do ataque do jogador 1, caso contrario do jogador 2
                    if (identificador == 1){
                        valorJogador = p1.getAtk();
                    }else {
                        valorJogador = p2.getAtk();                     

                    }
                    //valor Inimigo recebe o valor da defesa do inimigo escolhido para ser atacado
                    valorInimigo = verficarDefesaInimigos(inimigos,opcaoInimigo,linha,coluna);

                    //Realiza a subtracao do ataque do jogador pela defesa do inimigo
                    if (valorJogador > valorInimigo)
                        valorJogada = valorJogador - valorInimigo;
                    else
                        valorJogada = valorInimigo - valorJogador; 

                    if((valorInimigo <= 1) && (valorJogador > 1))
                        valorJogada = 0;
                    //Atraves do metodo set o inimigo recebe o novo valor da defesa apos o ataque recebido
                    inimigos[linha][coluna][opcaoInimigo].setDef(valorJogada); 
                    //Printa se os inimigos apos os ataques 
                    printaInimigos(inimigos,quantidade,identificador,linha,coluna);

                    controle = retornaDefesaInimigos(inimigos,linha,coluna) ;

                }
                //Se a opcao for 2 realiza a busca no setor
                if (opcao == 2)
                {

                    buscaSetor(identificador,quantidade,linha,coluna);
                }
                //se a opcao for 3 o jogador 2 pode curar sua defesa ou do P1
                if(opcao == 3 && identificador == 2)
                {
                    System.out.println("Habilidade especial! Digite 1 para aumentar a defesa de P1 ou qualquer outro numero para aumentar a sua propria defesa");
                    habilidade = teclado.nextInt();

                    if(habilidade == 1){
                        //Caso o jogador 2 queira curar P1, verifica se P2 e P1 estao no mesmo setor
                        while((linha != linha_A && coluna != coluna_A) || (linha != linha_A && coluna == coluna_A) || (linha == linha_A && coluna != coluna_A) )
                        {
                            System.out.println("Desculpe, mas voce e o jogador 1 precisam estar no mesmo Setor. Digite qualquer numero diferente de 1 para recuperar sua propria defesa");
                            habilidade = teclado.nextInt();
                            if (habilidade != 1)
                            {
                                break;
                            }
                        }
                    }
                    if ( habilidade == 1)
                    {
                        //Realiza a adicao do novo valor da defesa atraves dos metodos get e set
                        valorJogada = p1.getDef() + 2;
                        p1.setDef(valorJogada);
                        System.out.println("Defesa de P1 aumentada. Novo valor de Defesa = "+p1.getDef()+" Valor Atak = "+p1.getAtk());

                    }else {
                        valorJogada = p2.getDef() + 2;
                        p2.setDef(valorJogada);
                        System.out.println("Defesa de P2 aumentada. Novo valor de Defesa = "+p2.getDef()+" Valor Atak = "+p2.getAtk());

                    }

                }
                i++;

            }
        }
    }

    public int vezInimigos(int linha, int coluna ,int identificador, int controle ,int linha_A,int linP2,int coluna_A,int colP2){

        System.out.println("\nTurno dos inimigos atacarem o jogador "+identificador);
        for(int k = 0; k < 5; k++){
            aleatorio = random1.nextInt(7);             //Numero aleatorio para decidir de o inimigo vai conseguir atacar, se o numero for impar ele perde o ataque
            if (aleatorio % 2 == 0)
            {
                if (identificador == 1)
                {
                    if(p2.getDef() <= 0 && inimigos[linha_A][coluna_A][k] != null && inimigos[linha_A][coluna_A][k].getDef() > 0)                      
                        valorInimigo = inimigos[linha][coluna][k].getAtk();        
                    if(p2.getDef() > 0 && inimigos[linha_A][coluna_A][k] != null && inimigos[linha_A][coluna_A][k].getDef() > 0)                      
                        valorInimigo = inimigos[linha_A][coluna_A][k].getAtk();                                

                }else {

                    if(inimigos[linha][coluna][k] != null && inimigos[linha][coluna][k].getDef() > 0)                     
                        valorInimigo = inimigos[linha][coluna][k].getAtk();                 
                }         			               
            }else{
                if(identificador == 2 && inimigos[linha][coluna][k] != null  && inimigos[linha][coluna][k].getDef() > 0){     
                    System.out.println("O inimigo "+k+" perdeu a jogada");
                }else if (p2.getDef() > 0 && identificador == 1 && inimigos[linha_A][coluna_A][k] != null  && inimigos[linha_A][coluna_A][k].getDef() > 0) {
                    System.out.println("O inimigo "+k+" perdeu a jogada");				
                }else if (p2.getDef() <= 0 && identificador == 1 && inimigos[linha][coluna][k] != null  && inimigos[linha][coluna][k].getDef() > 0) {
                    System.out.println("O inimigo "+k+" perdeu a jogada");				
                }
                i++;
                continue;
            }
            //Se P1 e P2 estiverem no mesmo setor, ambos receberam ataques dos inimigos
            if (identificador == 2 && linha == linha_A && coluna == coluna_A)
            {

                if(inimigos[linha][coluna][k] != null  && inimigos[linha][coluna][k].getDef() > 0){     
                    System.out.println("O inimigo "+k+" atacou o jogador 1.AAAAAAAAAAAAAAAAAAA Valor do Ataque: "+inimigos[linha][coluna][k].getAtk());
                    valorJogada = p1.getDef() - valorInimigo;
                    p1.setDef(valorJogada);
                }

                if(inimigos[linha][coluna][k] != null && inimigos[linha][coluna][k].getDef() > 0){      
                    System.out.println("O Inimigo "+k+" atacou o jogador 2.AAAAAAAAAAAAAAAAaa Valor do Ataque: "+inimigos[linha][coluna][k].getAtk());
                    valorJogada = p2.getDef() - valorInimigo;
                    p2.setDef(valorJogada);
                }

            }else{
                //Realiza o ataque dos inimigo ao jogador P1, utiliza os metodos get e set para amarzenar a nova defesa de P1 apos os ataques
                if(identificador == 1)
                {
                    if(p2.getDef() > 0 && inimigos[linha_A][coluna_A][k] != null  && inimigos[linha_A][coluna_A][k].getDef() > 0){     
                        System.out.println("O Inimigo "+k+" atacou o jogador "+ identificador+". Valor do Ataque: "+inimigos[linha_A][coluna_A][k].getAtk());
                        valorJogada = p1.getDef() - valorInimigo;
                        p1.setDef(valorJogada);
                    }else if(p2.getDef() <= 0 && inimigos[linha][coluna][k] != null  && inimigos[linha][coluna][k].getDef() > 0){     
                        System.out.println("O Inimigo "+k+" atacou o jogador "+ identificador+". Valor do Ataque: "+inimigos[linha][coluna][k].getAtk());
                        valorJogada = p1.getDef() - valorInimigo;
                        p1.setDef(valorJogada);
                    }

                }else if (identificador == 2){   //Realiza o mesmo procedimento acima para P2
                    if(inimigos[linha][coluna][k] != null  && inimigos[linha][coluna][k].getDef() > 0){     
                        System.out.println("O Inimigo "+k+" atacou o jogador "+ identificador+". Valor do Ataque: "+inimigos[linha][coluna][k].getAtk());
                        valorJogada = p2.getDef() - valorInimigo;
                        p2.setDef(valorJogada);
                    }
                }

            }
            //Caso o inimigo esteja morto ele nao pode atacar
            if (identificador == 2 && inimigos[linha][coluna][k] != null && inimigos[linha][coluna][k].getDef() < 1)
            {
                System.out.println("O inimigo "+k+" morreu e nao pode atacar");
            }else if (identificador == 1)
            { 
                if (p2.getDef() <=0 && inimigos[linha][coluna][k] != null && inimigos[linha][coluna][k].getDef() <= 0 )
                    System.out.println("O inimigo "+k+" morreu e nao pode atacar");		
                if (p2.getDef() > 0 && inimigos[linha_A][coluna_A][k] != null && inimigos[linha_A][coluna_A][k].getDef() <= 0 )
                    System.out.println("O inimigo "+k+" morreu e nao pode atacar");		   
            }

        }
        System.out.println("\n");

        System.out.println("DADOS DOS JOGADORES ATUALIZADOS APOS ATAQUES INIMIGOS");
        System.out.println("Jogador 1 ATAQUE = "+p1.getAtk()+" DEFESA = "+p1.getDef());
        System.out.println("Jogador 2 ATAQUE = "+p2.getAtk()+" DEFESA = "+p2.getDef());

        return 0;       
    }

}

