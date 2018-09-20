 import java.lang.reflect.*;
 /*
...
String str = "COTUCA"
char  chr = str.charAt(2);
...

o código acima é bem simples, suponhamos agora que queiramos SOFRER...
o que fazer? como tornar DEMONIACO código acima?

...

String str = "COTUCA";
Class<?> classe = str.getClass();
Integer parametroReal = 2; //porque quero usar 2 como parametro do CharAt
Class<?>[]  tiposDosParametrosFormais = new Class<?>[1]; //1 pq charAt tem 1 parâmetro
tiposDosParametrosFormais[0] = parametroReal.getClass();
Method metodo = classe.getMethod("charAt", tiposDosParametrosFormais);
Object[] parametrosReais = new Object[1]; //porque charAt só tem 1 parametro
parametrosReais[0] = parametroReal;
char chr = ((Character)metodo.invoke(parametrosReais)).charValue();
*/
public class Pilha<X> implements Cloneable //a classe recebe uma classe que são possíveis de serem comparadas "eXtends" <X eXtends Comparable<X>>
{
    private Object[] vetor;
    private int qtd   = 0;

    //versão preventiva
	public Pilha(int t) throws Exception
	{
		if(t<0)
		  throw new Exception ("Capacidade inválida");

	    this.vetor = new Object[t];
	}


	/*versão remediadora
	public Pilha(int t) throws Exception
	{
		try
		{
		   this.vetor = new X [t];
		}
		catch(NegativeArraySizeException erro)
		{
		   throw new Exception ("Capacidade inválida");
		}
    }
    */

    public void guarde (X s) throws Exception
    {
		if(s == null)
		  throw new Exception("Seu valor está vazio");
		if(this.isCheia())
		  throw new Exception("Capacidade de armazenamento atingida!!");

        if(s instanceof Cloneable)
            this.vetor[this.qtd] = meuCloneDeX(s); //vai dar pau, tem que contornar
        else
            this.vetor[this.qtd]  = s;
        this.qtd++;
    }

    public X getUmItem () throws Exception
    {
		if (this.isVazia())
		    throw new Exception("Nada a recuperar");
         if(this.vetor[this.qtd-1] instanceof Cloneable)
            return meuCloneDeX((X)this.vetor[this.qtd-1]);

         return (X)this.vetor[this.qtd-1];
    }

    public void jogueForaUmItem ()throws Exception
    {
		if(this.isVazia())
		   throw new Exception("Nada p/jogar fora");

        this.qtd--;
        this.vetor[this.qtd] = null;
    }

    public boolean isCheia()
    {
		return this.qtd == this.vetor.length;
	}

	public boolean isVazia()
	{
		return this.qtd == 0;
	}

	public String toString()
	{
		if(this.isVazia())
		    return "Vazia";

	    return this.qtd + " elementos, sendo o último " + this.vetor[this.qtd-1];
	}

	public boolean equals(Object obj)
	{
		//cortar todo o resto
		if(this == obj)
		   return true ;       // verifica se é a mesma instância

		if(obj == null)
		  return false;

        if(this.getClass() != obj.getClass())
           return false;

        Pilha<X> pil = (Pilha<X>)obj; //pega a instância batizada de obj, conveci o java de que é uma pilha e deu o nome dela de pil

        if(this.qtd!=pil.qtd)
               return false;

        for(int i = 0; i < this.qtd; i++)
          if(!this.vetor[i].equals(pil.vetor[i]))
              return false;

        return true;
	}

	public int hashCode()
	{
        int ret = 1; //pode ser instânciado com qualquer valor, menos 0

        ret *= 7 + new Integer(this.qtd).hashCode();  //número primo qualquer

		for(int i = 0; i < this.qtd; i++)
		 // if(this.vetor[i] != null) porém temos a garantia de que não temos nenhum nulo
		     ret *= 7 + this.vetor[i].hashCode(); // número primo qualquer

		return  ret;
	}
	public Pilha(Pilha model) throws Exception
	{
		if(model == null)
		   throw new Exception("Modelo ausente");

	    this.qtd = model.qtd;
	    this.vetor = new Object [model.vetor.length];
	    for(int i = 0; i<=model.qtd; i++)
	        this.vetor[i] = model.vetor[i];
	        //this.vetor[i] = new X(model.vetor[1]); //não precisa clonar, pois os horarios não são alterados pela classe pilha pois ela é uma classe guardadora
	}
	public Object clone()
	{
		Pilha ret = null;
		try
		{
			ret = new Pilha(this);
		}
		catch(Exception erro)
		{}
		return ret;
	}
	private X meuCloneDeX(X x) throws Exception
	{
		// return X.clone();
		X ret = null;
		try
		{
			Class<?> classe = x.getClass();
			Class<?>[]  tiposDosParametrosFormais = null; // pois o método de clone não tem parametros
			Method metodo = classe.getMethod("clone", tiposDosParametrosFormais);
			Object[] parametrosReais = null; // pois o método de clone não tem parametros
		    ret = (X)metodo.invoke(parametrosReais);
        }
       catch(NoSuchMethodException erro)
       {}
       catch(IllegalAccessException erro)
       {}
       catch(InvocationTargetException erro)
       {}
       // ou catch(Exception erro){}
       return ret;
	}
}