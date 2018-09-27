package pilha;
import java.lang.reflect.*;

/**
A classe pilha é responsável por guardar um vetor de objetos que armazena quantidade de
objetos guardados.
Nela encontramos métodos para guardar e retornar valores.

@authors Gustavo Ferreira Gitzel 18194 and Isabela Paulino de Souza 18189.
@since 2018
*/
public class Pilha<X> implements Cloneable
{
	/**
		Vetor de objetos que representará a pilha
	*/
    protected Object[] vetor;

    /**
        Expressa a quantidade de itens guardados na pilha
	*/
    protected int qtd   = 0;

	/**
		Constroi uma nova instância da classe Pilha.
		Para tanto, deve ser fornecido um inteiro que será utilizado
		como a capacidade de valores que podem ser armazenados na instância recém criada.
		@param t é o valor do limite da Pilha.
		@throws Exception se o valor é negativo.
    */
	public Pilha(int t) throws Exception
	{
		if(t<0)
		  throw new Exception ("Capacidade inválida");

	    this.vetor = new Object[t];
	}

	/**
	    Inclui um novo objeto na Pilha.
	    @param s o Objeto que será guardado.
	    @throws Exception se não for fornecido um objeto ou se a Pilha estiver cheia.
    */
    public void guarde (X s) throws Exception
    {
		if(s == null)
		  throw new Exception("Seu valor está vazio");
		if(this.isCheia())
		  throw new Exception("Capacidade de armazenamento atingida!!");

        if(s instanceof Cloneable)
            this.vetor[this.qtd] = meuCloneDeX(s);
        else
            this.vetor[this.qtd]  = s;
        this.qtd++;
    }

    /**
   	    Obtém o ultimo item da Pilha.
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
		Verifica se a pilha está cheia.
		@return true se a quantidade de itens armazenados for igual ao
		tamanho do vetor e false caso contrário.
    */
    public boolean isCheia()
    {
		return this.qtd == this.vetor.length;
	}

    /**
		Verifica se a pilha está vazia.
		@return true se a quantidade de itens armazenados for igual a 0
		e false caso contrário.
    */
	public boolean isVazia()
	{
		return this.qtd == 0;
	}

	 /**
		 Gera uma representação textual do ultimo item da pilha.
		 @return uma String contendo o ultimo item da pilha e se estiver vazia
		 uma mensagem avisando o usuário.
    */
	public String toString()
	{
		if(this.isVazia())
		    return "Vazia";

	    return this.qtd + " elementos, sendo o último " + this.vetor[this.qtd-1];
	}

	/**
		Verifica a igualdade entre duas pilhas.
		Verifica se o Object fornecido como parâmetro representa uma
		pilha igual àquela representada pela instância à qual este
		método for aplicado, resultando true em caso afirmativo,
		ou false, caso contrário.
		@param  obj o objeto a ser comparado com a instância à qual esse método
				for aplicado.
		@return true, caso o Object fornecido ao método e a instância chamante do
				método representarem pilhas iguais, ou false, caso contrário.
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
		Calcula o código de hash de uma pilha.
		Calcula e resulta o hashCode da pilha representada pela instância
		à qual o método for aplicado.
		@return o hashCode da pilha chamante do método.
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
		Constroi uma cópia da instância da classe Pilha dada.
		Para tanto, deve ser fornecida uma instancia da classe Pilha para ser
		utilizada como modelo para a construção da nova instância criada.
		@param modelo a instância da classe Pilha a ser usada como modelo.
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
		Produz e resulta uma cópia da Pilha representada pela instância
		à qual o método for aplicado.
		@return a cópia da Pilha representada pela instância à qual
				o método for aplicado.
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
		Produz e resulta uma cópia do Objeto presente na pilha.
		@return a cópia do Objeto que está na pilha.
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