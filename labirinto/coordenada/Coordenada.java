package coordenada;
/**
A classe coordenada � respons�vel por guardar uma coordenada,
sendo x a posi��o das abcissas e y das ordenadas.
Nela encontramos m�todos para guardar valores, retornar e validar.

@authors Gustavo Ferreira Gitzel 18194 and Isabela Paulino de Souza 18189.
@since 2018
*/
public class Coordenada implements Cloneable
{
	/**
	Expressa o valor da coordenada x
	*/
    protected int x;

    /**
    Expressa o valor da coordenada y
    */
    protected int y;

    /**
	Constroi uma nova inst�ncia da classe Coordenada.
	Para tanto, deve ser fornecido dois inteiros que ser�o utilizados
	como coordenadas da inst�ncia rec�m criada.
	@param x o n�mero das abcissas e y o n�mero das ordenadas.
    @throws Exception se um dos valores for negativo.
    */

    public Coordenada(int x, int y) throws Exception
    {
		this.validarX(x);
		this.validarY(y);

		this.x = x;
		this.y = y;
	}

	/**
	    Valida o valor de x.
	    Verifica se o valor fornecido como par�metro � maior que 0,
	    lan�ando exce��es, caso seja negativo.
	    @param x o valor a ser validado.
	    @throws Exception se n�o for fornecido um valor v�lido.
    */
    protected void validarX(int x) throws Exception
    {
		if(x < 0)
			throw new Exception("Coordenada x inv�lida!");
	}

	/**
		Valida o valor de y.
		Verifica se o valor fornecido como par�metro � maior que 0,
		lan�ando exce��es, caso seja negativo.
		@param y o valor a ser validado.
		@throws Exception se n�o for fornecido um valor v�lido.
    */
	protected void validarY(int y) throws Exception
	{
	    if(y < 0)
		 throw new Exception("Coordenada y inv�lida!");
	}

	/**
	    Obt�m o valor de x.
	    @return o valor de x.
    */
	public int getX()
	{
		return this.x;
	}

	/**
	    Obt�m o valor de y.
	    @return o valor de y.
    */
	public int getY()
	{
		return this.y;
	}

	/**
		Altera o valor de x para o n�mero desejado.
		@param o valor de x a ser atualizado
		@throws Exception se o n�mero for negativo
	*/
	public void setX(int x)	throws Exception
	{
		this.validarX(x);

		this.x = x;
	}

	/**
		Altera o valor de y para o n�mero desejado.
		@param o valor de y a ser atualizado
		@throws Exception se o n�mero for negativo
	*/
	public void setY(int y)	throws Exception
	{
		this.validarY(y);
		this.y = y;
	}

	/**
	    Gera uma representa��o textual de todo conte�do da coordenada.
	    Produz uma string com os valores da coordenada
	    @return um String contendo a coordenada.
    */
	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}

	/**
	    Verifica a igualdade entre duas coordenadas.
	    Verifica se o Object fornecido como par�metro representa uma
	    coordenada igual �quela representada pela inst�ncia � qual este
	    m�todo for aplicado, resultando true em caso afirmativo,
	    ou false, caso contr�rio.
	    @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
	            for aplicado.
	    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
	            m�todo representarem coordenadas iguais, ou false, caso contr�rio.
    */
	public boolean equals(Object obj)
	{
		if(obj == null)
		return false;
		if(obj == this)
		return true;

		if(obj.getClass() != this.getClass())
		return false;

		Coordenada cd = (Coordenada)obj;
		if(this.x != cd.x)
		return false;
		if(this.y != cd.y)
		return false;

		return true;
	}

	/**
	    Calcula o c�digo de hash de uma coordenada.
	    Calcula e resulta o hashCode da coordenada representada pela inst�ncia
	    � qual o m�todo for aplicado.
	    @return o hashCode da coordenada chamante do m�todo.
    */
	public int hashCode()
	{
		int ret = 2;

		ret = ret * 3 + ((Integer)this.x).hashCode();
		ret = ret * 3 + ((Integer)this.y).hashCode();

		return ret;
	}

	/**
	    Constroi uma c�pia da inst�ncia da classe Coordenada dada.
	    Para tanto, deve ser fornecida uma instancia da classe Coordenada para ser
	    utilizada como modelo para a constru��o da nova inst�ncia criada.
	    @param modelo a inst�ncia da classe Coordenada a ser usada como modelo.
	    @throws Exception se o modelo for null.
    */
	public Coordenada(Coordenada model) throws Exception
	{
		if(model == null)
		 throw new Exception("Modelo ausente!");
	   this.x = model.x;
	   this.y = model.y;
    }

	/**
	    Clona uma Coordenada.
	    Produz e resulta uma c�pia da Coordenada representada pela inst�ncia
	    � qual o m�todo for aplicado.
	    @return a c�pia da Coordenada representada pela inst�ncia � qual
	            o m�todo for aplicado.
    */
    public Object clone()
    {
		Coordenada ret= null;
		try
		{
			ret = new Coordenada(this);
		}
		catch(Exception erro)
		{}
		return ret;
	}
}