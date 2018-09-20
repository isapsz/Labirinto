import java.io.*;
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
			char[][] matriz = new char[colunas][linhas];

			boolean achouSaida = false;

			Coordenada atual = null;

			for(int y = 0; y < linhas; y++)
			{
				String linha = lerArquivo.readLine();
				String[] caracteres = linha.split("");

                for(int x = 0; x < colunas; x++)
				{
					matriz[x][y] = caracteres[x].charAt(0);

					if(caracteres[x].equals("E"))
						atual = new Coordenada(x,y);
				}
				System.out.println(linha);
			}

			if(atual == null)
				throw new Exception("Caracter de entrada não encontrado!");


             Fila<Coordenada> fila = new Fila<Coordenada>(3);
			 Pilha<Coordenada> caminho = new Pilha<Coordenada>(linhas*colunas);
			 Pilha<Fila<Coordenada>>  possibilidades = new Pilha<Fila<Coordenada>>(linhas*colunas);
			while(!achouSaida)
			{
				if(matriz[atual.getX() + 1][atual.getY()] == 'S' || matriz[atual.getX() + 1][atual.getY()] == ' ')	//direita
					fila.guarde(new Coordenada(atual.getX() + 1, atual.getY()));

				//if(matriz[atual.getX() - 1][atual.getY()] == ' ' || matriz[atual.getX() - 1][atual.getY()] == 'S')	//esquerda
					//fila.guarde(new Coordenada(atual.getX() - 1,atual.getY()));


				if(matriz[atual.getX()][atual.getY() + 1] == ' ' || matriz[atual.getX()][atual.getY() + 1] == 'S')	//sobe
					fila.guarde(new Coordenada(atual.getX(), atual.getY()+1));


				if(matriz[atual.getX()][atual.getY() - 1] == ' ' || matriz[atual.getX()][atual.getY() - 1] == 'S')	// desce
				    fila.guarde(new Coordenada(atual.getX(), atual.getY() - 1));

				//atual = fila.getUmItem();
				//fila.jogueForaUmItem();


				if(matriz[atual.getX()][atual.getY()] == 'S')
					achouSaida = true;
				else
					matriz[atual.getX()][atual.getY()] = '*';


				//caminho.guarde(atual);
				//possibilidades.guarde(fila);
			}

			for(int y = 0; y < linhas; y++)
			{
				for(int x = 0; x < colunas; x++)
				{
					System.out.print(matriz[x][y]);
				}
				System.out.print("\n");
			}

		}
		catch(Exception erro)
		{
			System.err.println(erro.getMessage());
		}
	}
}