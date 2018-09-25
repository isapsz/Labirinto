import java.io.*;
import coordenada.*;
import fila.*;
import pilha.*;
import labirinto.*;

public class Projeto
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			BufferedReader leitura = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Digite o nome do arquivo:  ");
			String arquivo = leitura.readLine();
			BufferedReader lerArquivo = new BufferedReader(new FileReader(arquivo));
			int linhas = Integer.parseInt(lerArquivo.readLine());
			int colunas = Integer.parseInt(lerArquivo.readLine());
			Labirinto lab = new Labirinto(linhas, colunas);
			boolean achouSaida = false;
			Coordenada atual = null;

			for(int y = 0; y < linhas; y++)
			{
				String linha = lerArquivo.readLine();
				String[] caracteres = linha.split("");

                for(int x = 0; x < colunas; x++)
				{
					lab.setMatriz(caracteres[x].charAt(0),x, y);

					if(caracteres[x].equals("E"))
						atual = new Coordenada(x,y);
				}
			}
			lerArquivo.close();
			System.out.println(lab);

			if(atual == null)
				throw new Exception("Caracter de entrada não encontrado!");



			 Pilha<Coordenada> caminho = new Pilha<Coordenada>(linhas*colunas);
			 Pilha<Fila<Coordenada>>  possibilidades = new Pilha<Fila<Coordenada>>(linhas*colunas);

			while(!achouSaida)
			{
				Fila<Coordenada> fila = new Fila<Coordenada>(3);
				if(atual.getX() < colunas)
					if(lab.getMatriz(atual.getX() + 1, atual.getY()) == ' ' || lab.getMatriz(atual.getX() + 1, atual.getY()) == 'S' )	//direita
						fila.guarde(new Coordenada(atual.getX() + 1, atual.getY()));


				if(atual.getX() > 0)
					if(lab.getMatriz(atual.getX() - 1, atual.getY()) == ' ' || lab.getMatriz(atual.getX() - 1, atual.getY()) == 'S')	//esquerda
						fila.guarde(new Coordenada(atual.getX() - 1,atual.getY()));

				if(atual.getY() > 0)
					if(lab.getMatriz(atual.getX(), atual.getY()+1) == ' ' || lab.getMatriz(atual.getX(), atual.getY()+1)== 'S')	//desce
						fila.guarde(new Coordenada(atual.getX(), atual.getY()+1));


				if(atual.getY() < linhas)
					if(lab.getMatriz(atual.getX(), atual.getY()-1) == ' ' || lab.getMatriz(atual.getX(), atual.getY()-1) == 'S')	// sobe
				    	fila.guarde(new Coordenada(atual.getX(), atual.getY() - 1));

				while(fila.isVazia())
				{
					if(!fila.isVazia())
					{
						atual = fila.getUmItem();
						fila.jogueForaUmItem();
						break;
					}
					fila = possibilidades.getUmItem();
					possibilidades.jogueForaUmItem();
					atual = caminho.getUmItem();
					caminho.jogueForaUmItem();
					lab.setMatriz(' ', atual.getX(), atual.getY());
				}
				if(!fila.isVazia())
				{
					atual = fila.getUmItem();
					fila.jogueForaUmItem();
					possibilidades.guarde(fila);
				}

				caminho.guarde(atual);
				if(lab.getMatriz(atual.getX(), atual.getY()) == 'S')
					achouSaida = true;
				else
				   lab.setMatriz('*', atual.getX(), atual.getY());
			}
			System.out.println(lab);
		}
		catch(Exception erro)
		{
			System.err.println(erro);
		}
	}
}