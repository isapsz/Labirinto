package labirinto;

public class Labirinto implements Cloneable
{
	 protected char[][] matriz;
	 protected int qtdLinha;
	 protected int qtdColuna;

     public Labirinto(int linha, int coluna) throws Exception
     {
		 this.qtdLinha = linha;
		 this.qtdColuna = coluna;
		 this.verificaLinha(linha);
		 this.verificaColuna(coluna);
		 this.matriz = new char[coluna][linha];
	 }
	 protected void verificaLinha(int linha) throws Exception
	 {
		 if(linha < 0 || linha > qtdLinha)
		  throw new Exception("Valor de linha inválido");
	 }
	 protected void verificaColuna(int coluna)throws Exception
	 {
		 if(coluna < 0 || coluna > qtdColuna)
		  throw new Exception("Valor de coluna inválido");
	 }
	 public char getMatriz(int x, int y) throws Exception
	 {
		 this.verificaLinha(y);
		 this.verificaColuna(x);

		 return	this.matriz[x][y];
	 }

	 public String toString()
	 {
		String mat = "";

		for(int y = 0; y < this.qtdLinha; y++)
		{
			for(int x = 0; x < this.qtdColuna; x++)
			{
				mat += this.matriz[x][y];
			}
			mat += ("\n");
		}

		return mat;
	 }

	 public void setMatriz(char ch, int x, int y) throws Exception
	 {
		this.verificaLinha(y);
		this.verificaColuna(x);

		this.matriz[x][y] = ch;
	 }
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
     public Labirinto(Labirinto modelo)
     {
		 this.qtdLinha = modelo.qtdLinha;
		 this.qtdColuna = modelo.qtdColuna;

		 this.matriz = new char[modelo.qtdColuna][modelo.qtdLinha];

		 for(int y = 0; y < this.qtdLinha; y++)
		 	for(int x = 0; x < this.qtdColuna; x++)
		 		this.matriz[x][y] = modelo.matriz[x][y];
	 }



}