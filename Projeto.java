import java.io.*;
import java.lang.*;
import fila.*;
import pilha.*;
import coordenada.*;
import labirinto.*;

public class Projeto
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			ProcessBuilder pb;
			BufferedReader leitura = new BufferedReader(new InputStreamReader(System.in));
			String resposta;
			do
			{
				System.out.print("Digite o nome do arquivo texto:  ");
				String arquivo = leitura.readLine();
				BufferedReader lerArquivo = new BufferedReader(new FileReader(arquivo));
				PrintStream resultado = new PrintStream(arquivo.substring(0, arquivo.length() - 4) + ".res.txt");
				int linhas = Integer.parseInt(lerArquivo.readLine());
				int colunas = Integer.parseInt(lerArquivo.readLine());

			   Labirinto lab = new Labirinto(linhas, colunas);

				for(int y = 0; y < linhas; y++)
				{
					String linha = lerArquivo.readLine();
					String[] caracteres = linha.split("");

					for(int x = 0; x < colunas; x++)
						lab.setPosicaoMatriz(caracteres[x].charAt(0),x, y);
			   }
				lerArquivo.close();

				resultado.println("Labirinto original:" + "\r\n" + lab);
				lab.resolverLab();
				resultado.println("Labirinto resolvido:"+ "\r\n" +lab);
				resultado.println("Caminho realizado: "+ "\r\n"+lab.exibeCaminho());

				pb = new ProcessBuilder("Notepad.exe", arquivo.substring(0, arquivo.length() - 4) + ".res.txt");
				pb.start();
				System.out.println("Deseja tentar novamente? (S / N) ");
				resposta = leitura.readLine().toUpperCase();
				resultado.close();
			}
			while(resposta.equals("S"));
		}
		catch(Exception erro)
		{
			System.err.println(erro);
		}
	}
}