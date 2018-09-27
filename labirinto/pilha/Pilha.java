package pilha;
import java.lang.reflect.*;

/**
A classe pilha � respons�vel por guardar um vetor de objetos que armazena quantidade de
objetos guardados.
Nela encontramos m�todos para guardar e retornar valores.

@authors Gustavo Ferreira Gitzel 18194 and Isabela Paulino de Souza 18189.
@since 2018
*/
public class Pilha<X> implements Cloneable
{
	/**
		Vetor de objetos que representar� a pilha
	*/
    protected Object[] vetor;

    /**
        Expressa a quantidade de itens guardados na pilha
	*/
    protected int qtd   = 0;

	/**
		Constroi uma nova inst�ncia da classe Pilha.
		Para tanto, deve ser fornecido um inteiro que ser� utilizado
		como a capacidade de valores que podem ser armazenados na inst�ncia rec�m criada.
		@param t � o valor do limite da Pilha.
		@throws Exception se o valor � negativo.
    */
	public Pilha(int t) throws Exception
	{
		if(t<0)
		  throw new Exception ("Capacidade inv�lida");

	    this.vetor = new Object[t];
	}

	/**
	    Inclui um novo objeto na Pilha.
	    @param s o Objeto que ser� guardado.
	    @throws Exception se n�o for fornecido um objeto ou se a Pilha estiver cheia.
    */
    public void guarde (X s) throws Exception
    {
		if(s == null)
		  throw new Exception("Seu valor est� vazio");
		if(this.isCheia())
		  throw new Exception("Capacidade de armazenamento atingida!!");

        if(s instanceof Cloneable)
            this.vetor[this.qtd] = meuCloneDeX(s);
        else
            this.vetor[this.qtd]  = s;
        this.qtd++;
    }

    /**
   	    Obt�m o ultimo item da Pilha.
   	    @return o ultimo item da Pilha.
    */
    public X getUmItem () throws Exception
    {
		if (this.isVazia())
		    throw new Exception("Nada a recuperar");
         if(this.vetor[this.qtd-1] instanceof Cloneable)
            return meuCloneDeX((X)this.vetor[this.qtd-1]);

         return (X)this.vetor[this.qtd-1];
    }

	/**
		Remove o ultimo item da Pilha.
		Exclui do vetor o ultimo item da Pilha.
		@throws Exception se a pilha estiver vazia.
    */
    public void jogueForaUmItem ()throws Exception
    {
		if(this.isVazia())
		   throw new Exception("Nada p/ jogar fora");

        this.qtd--;
        this.vetor[this.qtd] = null;
    }

 	/**
		Verifica se a pilha est� cheia.
		@return true se a quantidade de itens armazenados for igual ao
		tamanho do vetor e false caso contr�rio.
    */
    public boolean isCheia()
    {
		return this.qtd == this.vetor.length;
	}

    /**
		Verifica se a pilha est� vazia.
		@return true se a quantidade de itens armazenados for igual a 0
		e false caso contr�rio.
    */
	public boolean isVazia()
	{
		return this.qtd == 0;
	}

	 /**
		 Gera uma representa��o textual do ultimo item da pilha.
		 @return uma String contendo o ultimo item da pilha e se estiver vazia
		 uma mensagem avisando o usu�rio.
    */
	public String toString()
	{
		if(this.isVazia())
		    return "Vazia";

	    return this.qtd + " elementos, sendo o �ltimo " + this.vetor[this.qtd-1];
	}

	/**
		Verifica a igualdade entre duas pilhas.
		Verifica se o Object fornecido como par�metro representa uma
		pilha igual �quela representada pela inst�ncia � qual este
		m�todo for aplicado, resultando true em caso afirmativo,
		ou false, caso contr�rio.
		@param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
				for aplicado.
		@return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
				m�todo representarem pilhas iguais, ou false, caso contr�rio.
    */

	public boolean equals(Object obj)
	{
		if(obj == null)
		  return false;

		if(this == obj)
		   return true ;

        if(this.getClass() != obj.getClass())
           return false;

        Pilha<X> pil = (Pilha<X>)obj;

        if(this.qtd!=pil.qtd)
               return false;

        for(int i = 0; i < this.qtd; i++)
          if(!this.vetor[i].equals(pil.vetor[i]))
              return false;

        return true;
	}

    /**
		Calcula o c�digo de hash de uma pilha.
		Calcula e resulta o hashCode da pilha representada pela inst�ncia
		� qual o m�todo for aplicado.
		@return o hashCode da pilha chamante do m�todo.
    */
	public int hashCode()
	{
        int ret = 1;

        ret *= 7 + new Integer(this.qtd).hashCode();

		for(int i = 0; i < this.qtd; i++)
		     ret *= 7 + this.vetor[i].hashCode();

		return  ret;
	}

	/**
		Constroi uma c�pia da inst�ncia da classe Pilha dada.
		Para tanto, deve ser fornecida uma instancia da classe Pilha para ser
		utilizada como modelo para a constru��o da nova inst�ncia criada.
		@param modelo a inst�ncia da classe Pilha a ser usada como modelo.
		@throws Exception se o modelo for null.
    */
	public Pilha(Pilha model) throws Exception
	{
		if(model == null)
		   throw new Exception("Modelo ausente");

	    this.qtd = model.qtd;
	    this.vetor = new Object [model.vetor.length];
	    for(int i = 0; i<=model.qtd; i++)
	        this.vetor[i] = model.vetor[i];
	}


	/**
		Clona uma Pilha.
		Produz e resulta uma c�pia da Pilha representada pela inst�ncia
		� qual o m�todo for aplicado.
		@return a c�pia da Pilha representada pela inst�ncia � qual
				o m�todo for aplicado.
    */
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

	 /**
		Clona um Objeto.
		Produz e resulta uma c�pia do Objeto presente na pilha.
		@return a c�pia do Objeto que est� na pilha.
    */
	protected X meuCloneDeX(X x) throws Exception
	{
		X ret = null;
		try
		{
			Class<?> classe = x.getClass();
			Class<?>[]  tiposDosParametrosFormais = null;
			Method metodo = classe.getMethod("clone", tiposDosParametrosFormais);
			Object[] parametrosReais = null;
		    ret = (X)metodo.invoke(x,parametrosReais);
        }
       catch(NoSuchMethodException erro)
       {}
       catch(IllegalAccessException erro)
       {}
       catch(InvocationTargetException erro)
       {}
       return ret;
	}
}