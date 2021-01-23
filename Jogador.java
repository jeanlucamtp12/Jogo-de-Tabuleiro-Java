
public abstract class Jogador
{
    protected int atk;
    protected int def;

    //Construtor padrao
    public Jogador()
    {

    }
    //Construtor completo
    public Jogador(int atk, int def)
    {
        this.setAtk(atk);
        this.setDef(def);        
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
