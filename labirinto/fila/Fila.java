package fila;
import java.lang.reflect.*;

/**
A classe fila � respons�vel por guardar um vetor de objetos que armazena inicio, fim e quantidade de
objetos guardados.
Nela encontramos m�todos para guardar e retornar valores.

@authors Gustavo Ferreira Gitzel 18194 and Isabela Paulino de Souza 18189.
@since 2018
*/
public class Fila<X> implements Cloneable
{
	/**
	Vetor de objetos que representar� a fila.
	*/
	protected Object[] vetor;

	/**
		Expressa a quantidade de itens guardados na fila.
	*/
	protected int qtd = 0;

	/**
		Expressa a posi��o de inicio da fila.
	*/
	protected int inicio = 0;

	/**
	    Expressa a posi��o de fim da fila.
	*/
	protected int fim = 0;

    /**
		Constroi uma nova inst�ncia da classe Fila.
		Para tanto, deve ser fornecido um inteiro que ser� utilizado
		como a capacidade de valores que podem ser armazenados na inst�ncia rec�m criada.
		@param capacidade � o valor do limite da fila.
		@throws Exception se o valor � negativo.
    */
	public Fila(int capacidade) throws Exception
	{
		if(capacidade < 0)
		   throw new Exception("Capacidade inv�lida");

		this.vetor = new Object[capacidade];
	}
    /**
	    Clona um Objeto.
	    Produz e resulta uma c�pia do Objeto presente na fila.
	    @return a c�pia do Objeto que est� na fila.
    */
	protected X meuCloneDeX(X x)
	{
		X ret = null;
		try
		{
			Class<?> classe = x.getClass();
			Class<?>[] tiposDeParametrosFormais = null;
			Method metodo = classe.getMethod("clone", tiposDeParametrosFormais);
			Object[] tiposDeParametrosReais = null;
			ret = (X)metodo.invoke(x,tiposDeParametrosReais);
		}
		catch(NoSuchMethodException erro)
		{}
		catch(IllegalAccessException erro)
		{}
		catch(InvocationTargetException erro)
		{}

		return ret;
	}

     /**
	    Inclui um novo objeto na fila.
	    @param s o Objeto que ser� guardado.
	    @throws Exception se n�o for fornecido um objeto ou se a fila estiver cheia.
    */
	public void guarde(X s) throws Exception
	{
		if(s==null)
		   throw new Exception("Informa��o ausente");

		if(this.isCheia())
		   throw new Exception("Fila cheia");

		if(s instanceof Cloneable)
		{
			if(fim == this.vetor.length - 1)
			{
			   this.vetor[this.fim] = meuCloneDeX(s);
			   fim = 0;
			}
			else
				this.vetor[this.fim++] = meuCloneDeX(s);
		}
		else
		{
			if(fim == this.vetor.length-1)
			{
			   this.vetor[this.fim] = s;
			   fim = 0;
			}
			else
				this.vetor[this.fim++] = s;
		}
		this.qtd++;
	}
    /**
	    Obt�m o primeiro item da fila.
	    @return o primeiro item da fila.
    */
	public X getUmItem() throws Exception
	{
		if(this.isVazia())
		   throw new Exception("Nada a recuperar");

		if(this.vetor[this.inicio] instanceof Cloneable)
			return meuCloneDeX((X)this.vetor[this.inicio]);

		return (X)this.vetor[this.inicio];
	}

 	/**
		Remove o primeiro item da fila.
		Exclui do vetor o primeiro objeto da fila.
		@throws Exception se a fila estiver vazia.
    */
	public void jogueForaUmItem() throws Exception
	{
		if(this.isVazia())
		   throw new Exception("Pilha vazia");

		this.vetor[this.inicio] = null;

		if(this.inicio == this.vetor.length-1)
		   inicio = 0;
		else
			inicio++;

		qtd--;
	}
    /**
		Verifica se a fila est� cheia.
		@return true se a quantidade de itens armazenados for igual ao
		tamanho do vetor e false caso contr�rio.
    */
	public boolean isCheia()
	{
		return this.qtd == this.vetor.length;
	}

    /**
		Verifica se a fila est� vazia.
		@return true se a quantidade de itens armazenados for igual a 0
		e false caso contr�rio.
    */
	public boolean isVazia()
	{
		return this.qtd == 0;
	}

    /**
		Gera uma representa��o textual do primeiro item da fila.
		@return uma String contendo o primeiro item da fila e se estiver vazia
		uma mensagem avisando o usu�rio.
    */
	public String toString()
	{
		if(this.qtd==0)
		   return "Vazia";

		return this.qtd+" elementos, sendo o primeiro "+this.vetor[inicio];
	}
    /**
	    Verifica a igualdade entre duas filas.
	    Verifica se o Object fornecido como par�metro representa uma
	    fila igual �quela representada pela inst�ncia � qual este
	    m�todo for aplicado, resultando true em caso afirmativo,
	    ou false, caso contr�rio.
	    @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
	            for aplicado.
	    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
	            m�todo representarem filas iguais, ou false, caso contr�rio.
    */
	public boolean equals (Object obj)
	{
		if(this==obj)
		   return true;

		if(obj == null)
		   return false;

		if(this.getClass()!=obj.getClass())
		   return false;

		Fila<X> fila = (Fila<X>)obj;

		if(this.qtd!=fila.qtd)
		   return false;

		for(int i = 0,
				posThis=this.inicio,
				posFila=fila.inicio;

			i < this.qtd;

			i++,
			posThis=(posThis<this.vetor.length-1?posThis+1:0),
			posFila=(posFila<fila.vetor.length-1?posFila+1:0))

		   if(!this.vetor[posThis].equals(fila.vetor[posFila]))
			  return false;

		return true;
	}

    /**
		Calcula o c�digo de hash de uma fila.
		Calcula e resulta o hashCode da fila representada pela inst�ncia
		� qual o m�todo for aplicado.
		@return o hashCode da fila chamante do m�todo.
    */
	public int hashCode()
	{
		int ret = 1;

		ret = ret * 2 + new Integer(this.qtd).hashCode();

		for(int i=0, pos=inicio; i<this.qtd; i++, pos=(pos<vetor.length-1?pos+1:0))
			ret = ret*2 + this.vetor[pos].hashCode();

		return ret;
	}

	/**
	    Constroi uma c�pia da inst�ncia da classe Fila dada.
	    Para tanto, deve ser fornecida uma instancia da classe Fila para ser
	    utilizada como modelo para a constru��o da nova inst�ncia criada.
	    @param modelo a inst�ncia da classe Fila a ser usada como modelo.
	    @throws Exception se o modelo for null.
    */
	public Fila (Fila<X> modelo) throws Exception
	{
		if(modelo == null)
			throw new Exception("Modelo ausente");

		this.qtd = modelo.qtd;

		this.inicio = modelo.inicio;

		this.fim = modelo.fim;

		this.vetor = new Object[modelo.vetor.length];

		for(int i=0; i<modelo.vetor.length-1; i++)
			this.vetor[i] = modelo.vetor[i];
	}

	/**
	    Clona uma Fila.
	    Produz e resulta uma c�pia da Fila representada pela inst�ncia
	    � qual o m�todo for aplicado.
	    @return a c�pia da Fila representada pela inst�ncia � qual
	            o m�todo for aplicado.
    */
	public Object clone()
	{
		Fila<X> ret = null;
		try
		{
			ret = new Fila<X>(this);
		}
		catch(Exception erro)
		{}

		return ret;
	}

}