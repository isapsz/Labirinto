package coordenada;
/**
A classe coordenada é responsável por guardar uma coordenada,
sendo x a posição das abcissas e y das ordenadas.
Nela encontramos métodos para guardar valores, retornar e validar.

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
	Constroi uma nova instância da classe Coordenada.
	Para tanto, deve ser fornecido dois inteiros que serão utilizados
	como coordenadas da instância recém criada.
	@param x o número das abcissas e y o número das ordenadas.
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
	    Verifica se o valor fornecido como parâmetro é maior que 0,
	    lançando exceções, caso seja negativo.
	    @param x o valor a ser validado.
	    @throws Exception se não for fornecido um valor válido.
    */
    protected void validarX(int x) throws Exception
    {
		if(x < 0)
			throw new Exception("Coordenada x inválida!");
	}

	/**
		Valida o valor de y.
		Verifica se o valor fornecido como parâmetro é maior que 0,
		lançando exceções, caso seja negativo.
		@param y o valor a ser validado.
		@throws Exception se não for fornecido um valor válido.
    */
	protected void validarY(int y) throws Exception
	{
	    if(y < 0)
		 throw new Exception("Coordenada y inválida!");
	}

	/**
	    Obtém o valor de x.
	    @return o valor de x.
    */
	public int getX()
	{
		return this.x;
	}

	/**
	    Obtém o valor de y.
	    @return o valor de y.
    */
	public int getY()
	{
		return this.y;
	}

	/**
		Altera o valor de x para o número desejado.
		@param o valor de x a ser atualizado
		@throws Exception se o número for negativo
	*/
	public void setX(int x)	throws Exception
	{
		this.validarX(x);

		this.x = x;
	}

	/**
		Altera o valor de y para o número desejado.
		@param o valor de y a ser atualizado
		@throws Exception se o número for negativo
	*/
	public void setY(int y)	throws Exception
	{
		this.validarY(y);
		this.y = y;
	}

	/**
	    Gera uma representação textual de todo conteúdo da coordenada.
	    Produz uma string com os valores da coordenada
	    @return um String contendo a coordenada.
    */
	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}

	/**
	    Verifica a igualdade entre duas coordenadas.
	    Verifica se o Object fornecido como parâmetro representa uma
	    coordenada igual àquela representada pela instância à qual este
	    método for aplicado, resultando true em caso afirmativo,
	    ou false, caso contrário.
	    @param  obj o objeto a ser comparado com a instância à qual esse método
	            for aplicado.
	    @return true, caso o Object fornecido ao método e a instância chamante do
	            método representarem coordenadas iguais, ou false, caso contrário.
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
	    Calcula o código de hash de uma coordenada.
	    Calcula e resulta o hashCode da coordenada representada pela instância
	    à qual o método for aplicado.
	    @return o hashCode da coordenada chamante do método.
    */
	public int hashCode()
	{
		int ret = 2;

		ret = ret * 3 + ((Integer)this.x).hashCode();
		ret = ret * 3 + ((Integer)this.y).hashCode();

		return ret;
	}

	/**
	    Constroi uma cópia da instância da classe Coordenada dada.
	    Para tanto, deve ser fornecida uma instancia da classe Coordenada para ser
	    utilizada como modelo para a construção da nova instância criada.
	    @param modelo a instância da classe Coordenada a ser usada como modelo.
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
	    Produz e resulta uma cópia da Coordenada representada pela instância
	    à qual o método for aplicado.
	    @return a cópia da Coordenada representada pela instância à qual
	            o método for aplicado.
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