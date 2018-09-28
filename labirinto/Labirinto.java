package labirinto;
import fila.*;
import pilha.*;
import coordenada.*;
/**
A classe fila é responsável por guardar uma matriz representando um labirinto, além
de ter o controle das filas, pilhas e coordenadas usadas.
Nela encontramos métodos para resolver o labirinto, retornar e alterar valores da matriz

@authors Gustavo Ferreira Gitzel 18194 and Isabela Paulino de Souza 18189.
@since 2018
*/
public class Labirinto implements Cloneable
{
	/**
		Variável que diz se a saída foi encontrada.
	*/
	protected boolean achouSaida = false;

	/**
		Coordenada que armazena a posicao atual do labirinto.
	*/
	protected Coordenada atual = null;

	/**
		Pilha de coordenada que representa o caminho do labirinto.
	*/
	protected Pilha<Coordenada> caminho;

	/**
		Pilha de coordenada que representa o caminho inverso do labirinto.
	*/
	protected Pilha<Coordenada> caminhoInverso;

	/**
		Pilha de filas de coordenadas que armazenam as possibilidades de resolução
		do labirinto
	*/
	protected Pilha<Fila<Coordenada>>  possibilidades;

	/**
		Fila que armazena as posicoes cabíveis e adjacentes à atual.
	*/
	protected Fila<Coordenada> fila;

	/**
		Matriz de char que representa o labirinto.
	*/
	protected char[][] matriz;

	/**
		Expressa a quantidade de linhas do labirinto.
	*/
	protected int qtdLinha;

	/**
		Expressa a quantidade de colunas do labirinto.
	*/
	protected int qtdColuna;

	/**
    	Método que resolve o labirinto.
	*/
	public void resolverLab() throws Exception
	{
		if(!this.achouEntrada())
	        throw new Exception("Caracter de entrada não encontrado!");


		while(!achouSaida)
		{
			this.avancar();
			this.retroceder();
			this.empilhar();
			this.verificaSaida();
		}
	}

	/**
		Método que percorre a matriz até encontrar a entrada representada
		pelo caracter E.
		@return true se a entrada foi encontrada caso contrário, false
	*/
	public boolean achouEntrada() throws Exception
	{
		for(int y = 0; y < this.qtdLinha; y++)
			for(int x = 0; x < this.qtdColuna; x++)
				if(x == 0 || x == this.qtdColuna - 1 || y == 0 || y == this.qtdLinha -1)
					if(this.verifica(x, y, 'E'))
					{
						this.atual = new Coordenada(x,y);
						return true;
					}
		return false;
	}

	/**
		Verifica se as posições adjacentes à atual são vazias ou apresentam o
		caracter de saída (S).
	*/
	protected void avancar() throws Exception
	{
		this.fila = new Fila<Coordenada>(3);
		int x = this.atual.getX();
		int y = this.atual.getY();

		if(x < this.qtdColuna - 1)
			if(this.verifica(x +1, y, ' ') ||this.verifica(x +1, y, 'S'))
				fila.guarde(new Coordenada(x + 1, y));

		if(x > 0)
			if(this.verifica(x - 1, y, ' ') || this.verifica(x -1, y, 'S'))
				fila.guarde(new Coordenada(x - 1,y));

		if(y >= 0)
			if(this.verifica(x, y + 1, ' ') ||this.verifica(x, y+1, 'S'))
				fila.guarde(new Coordenada(x, y+1));


		if(y < this.qtdLinha - 1 && y > 0)
			if(this.verifica(x, y - 1, ' ') || this.verifica(x, y-1, 'S'))
				fila.guarde(new Coordenada(x, y - 1));
	}

	/**
		Quando o labirinto está em modo de retrocesso. Enquanto a fila estiver
		vazia, retira-se das possibilidades e caminho um item o qual pode representar
		outro caminho a ser seguido até a saída.
	*/
	protected void retroceder() throws Exception
	{
		while(this.fila.isVazia())
		{
			if(this.caminho.isVazia())
				throw new Exception("Saída não encontrada");

			this.fila = this.possibilidades.getUmItem();
			this.possibilidades.jogueForaUmItem();
			this.atual = this.caminho.getUmItem();
			this.caminho.jogueForaUmItem();

			this.setPosicaoMatriz(' ', atual.getX(), atual.getY());
		}
	}

	/**
		Guarda em possibilidades a fila, e atual em caminho. Além de retirar
		um item da fila. Logo, o verifica-se o encontro da saída, se não
		adiciona o caracter * à matriz.
	*/
	protected void empilhar() throws Exception
	{
		this.atual = fila.getUmItem();
		this.fila.jogueForaUmItem();
		this.possibilidades.guarde(fila);
		this.caminho.guarde(atual);

	}

    /**
	    Verifica se a posição da matriz contém o caracter de saída do labirinto,
	    se sim a boolean achouSaida recebe true caso contrário a posição é preenchida com
	    o caracter *.
	*/
    protected void verificaSaida() throws Exception
    {
		if(this.getPosicaoMatriz(atual.getX(), atual.getY()) == 'S')
			achouSaida = true;
		else
			this.setPosicaoMatriz('*', atual.getX(), atual.getY());
	}
	/**
		Retorna uma string contendo as coordenadas do caminho de resolução
		do labirinto.
		@return String com o valor de todas as coordenadas presentes no caminho.
	*/
	public String exibeCaminho()throws Exception
	{
		String ret = "";
		while(!caminho.isVazia())
		{
			caminhoInverso.guarde(caminho.getUmItem());
			caminho.jogueForaUmItem();
		}
		while(!caminhoInverso.isVazia())
		{
			ret += caminhoInverso.getUmItem() + "\r\n";
			caminhoInverso.jogueForaUmItem();
		}
		return ret;
	}

	/**
		Constroi uma nova instância da classe Labirinto.
		Para tanto, deve ser fornecido dois inteiros que serão utilizados
		como a quantidade de linhas e colunas da matriz.
		@param dois valores inteiros que indicam a quantidade de linhas e colunas da matriz.
    */
	public Labirinto(int linha, int coluna) throws Exception
	{
		this.qtdLinha = linha;
		this.qtdColuna = coluna;
		this.verificaPosicao(coluna, linha);
		this.matriz = new char[coluna][linha];
		this.caminho = new Pilha<Coordenada>(linha*coluna);
		this.caminhoInverso = new Pilha<Coordenada>(linha*coluna);
		this.possibilidades = new Pilha<Fila<Coordenada>>(linha*coluna);
	}

	/**
	    Valida o a posição de uma matriz.
	    Verifica se os valores fornecidos como parâmetro são válidos.
	    @param linha e coluna a serem validados.
	    @throws Exception se a linha ou a coluna for menor que 0 ou se a quantidades de seus
	    respectivos limites forem ultrapassados.
    */
	protected void verificaPosicao(int coluna, int linha) throws Exception
	{
		if(linha < 0 || linha > qtdLinha)
			throw new Exception("Valor de linha inv�lido");

		if(coluna < 0 || coluna > qtdColuna)
			throw new Exception("Valor de coluna inv�lido");
	}

	/**
		Obtém o caracter de uma posição da matriz passada como parâmetro.
		@param x e y que são as coordenadas da matriz.
		@return	o char de uma posicao indicada pelo parâmetro.
    */
	public char getPosicaoMatriz(int x, int y) throws Exception
	{
		this.verificaPosicao(x, y);
		return	this.matriz[x][y];
	}

	/**
		Gera uma representação textual da matriz
		@return uma String contendo a matriz.
    */
	public String toString()
	{
		String mat = "";
		for(int y = 0; y < this.qtdLinha; y++)
		{
			for(int x = 0; x < this.qtdColuna; x++)
				mat += this.matriz[x][y];
			mat += ("\r\n");
		}
		return mat;
	}

	/**
		Altera o valor de uma posição da matriz.
		@param ch, o char a ser inserido na matriz , x e y a posição em que o char será inserido.
	*/
	public void setPosicaoMatriz(char ch, int x, int y) throws Exception
	{
		this.verificaPosicao(x,y);
		this.matriz[x][y] = ch;
	}

	/**
		Verifica a igualdade entre dois labirintos.
		Verifica se o Object fornecido como parâmetro representa um
		labirintos igual àquela representada pela instância à qual este
		método for aplicado, resultando true em caso afirmativo,
		ou false, caso contrário.
		@param  obj o objeto a ser comparado com a instância à qual esse método
		for aplicado.
		@return true, caso o Object fornecido ao método e a instância chamante
		do método representarem labirintos iguais, ou false, caso contrário.
    */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;

		if(obj == this)
			return true;

		Labirinto lb = (Labirinto)obj;

		if(this.qtdLinha != lb.qtdLinha)
			return false;

		if(this.qtdColuna != lb.qtdColuna)
			return false;

		if(!this.toString().equals(lb.toString()))
			return false;

		return true;
	}

	/**
		Calcula o código de hash de um labirinto.
		Calcula e resulta o hashCode do labirinto representada pela instância
		à qual o método for aplicado.
		@return o hashCode do labirinto chamante do método.
    */
	public int hashCode()
	{
		int ret = 2;

		ret = ret * 3 + ((Integer)qtdLinha).hashCode();
		ret = ret * 3 + ((Integer)qtdColuna).hashCode();

		for(int y = 0; y < qtdLinha; y++)
			for(int x = 0; x < qtdColuna; x++)
				ret *= 3 + ((Character)matriz[x][y]).hashCode();

		return ret;
	}

	/**
		Clona um labirinto.
		Produz e resulta uma cópia do labirinto representada pela instância
		à qual o método for aplicado.
		@return a cópia do labirinto representada pela instância à qual
				o método for aplicado.
    */
	public Object clone()
	{
		Object ret = null;
		try
		{
			ret = new Labirinto(this);
		}
		catch(Exception erro)
		{}
		return ret;
	}

	/**
		Constroi uma cópia da instância da classe labirinto dada.
		Para tanto, deve ser fornecida uma instancia da classe labirinto para ser
		utilizada como modelo para a construção da nova instância criada.
		@param modelo a instância da classe labirinto a ser usada como modelo.
		@throws Exception se o modelo for null.
    */
	public Labirinto(Labirinto modelo)
	{
		this.qtdLinha = modelo.qtdLinha;
		this.qtdColuna = modelo.qtdColuna;

		this.matriz = new char[modelo.qtdColuna][modelo.qtdLinha];

		for(int y = 0; y < this.qtdLinha; y++)
			for(int x = 0; x < this.qtdColuna; x++)
				this.matriz[x][y] = modelo.matriz[x][y];
	}

	/**
		Verifica se o char de uma determinada posição é igual ao char passado
		por parâmetro.
		@param x e y, coordenadas da matriz, e o chr é o caracter a ser comparado.
		@return	true se os dois caracters forem iguais, caso contrário false
	*/
	public boolean verifica(int x, int y, char chr) throws Exception
	{
		this.verificaPosicao(x, y);

		if(this.matriz[x][y] == chr)
			return true;

		return false;
	}

}