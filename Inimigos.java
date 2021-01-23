
public class Inimigos
{

    private int atk;
    private int def;
    //Construtor padrao
    public Inimigos()
    {        
    }
    //Construtor completo
    public Inimigos(int atk, int def)
    {
        this.setAtk(0);
        this.setDef(0);        
    }
    //Metodos get e set
    public int getAtk ()
    {
        return atk;
    }

    public void setAtk(int atk)
    {
        this.atk = atk;       
    }

    public int getDef ()
    {
        return def;
    }

    public void setDef(int def)
    {
        this.def = def;       
    }
}
