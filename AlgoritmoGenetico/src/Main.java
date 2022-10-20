import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Define a solu��o -> maior que 5
		Scanner in = new Scanner(System.in);
		System.out.print("Defina a solucao: ");
		String solucao = in.nextLine();
		while(solucao.length() < 5) {
			System.out.println("Tamanho invalido, insira uma String com tamanho maior que 5:");
			solucao = in.nextLine();
		}
		
        AG.setSolucao(solucao);    
        AG.setCaracteres("!,.:;?�����������������QWERTYUIOPASDFGHJKL�ZXCVBNMqwertyuiopasdfghjkl�zxcvbnm1234567890 ");
        AG.setTaxaDeCrossover(0.6);
        AG.setTaxaDeMutacao(0.3);
        boolean eltismo = true;
        
        int pop = in.nextInt();
        while(pop < 0) {
        	System.out.println("Valor de populacao invalido, precisa ser acima de 0");
        	pop = in.nextInt();
        }
        int tamPop = pop;
        //numero m�ximo de gera��es
        int geracoes = in.nextInt();
        while(pop < 0) {
        	System.out.println("Valor de geracoes invalido, precisa ser acima de 0");
        	geracoes = in.nextInt();
        }
        int numMaxGeracoes = geracoes;

        //define o n�mero de genes do indiv�duo baseado na solu��o
        int numGenes = AG.getSolucao().length();

        //cria a primeira popula��o aleat�rioa
        Populacao populacao = new Populacao(numGenes, tamPop);

        boolean temSolucao = false;
        int geracao = 0;

        System.out.println("Fitness da solu��o: " + AG.getSolucao().length());
        
        //loop at� o crit�rio de parada
        while (!temSolucao && geracao < numMaxGeracoes) {
            geracao++;

            //cria nova populacao
            populacao = AG.novaGeracao(populacao, eltismo);

            System.out.println("Gera��o " + geracao + " | Fitness: " + populacao.getIndivduo(0).getFitnessInt() + " | Melhor: " + populacao.getIndivduo(0).getGenes());
            
            //verifica se tem a solucao
            temSolucao = populacao.temSolocao(AG.getSolucao());
        }

        if (geracao == numMaxGeracoes) {
            System.out.println("N�mero Maximo de Gera��es | " + populacao.getIndivduo(0).getGenes() + " " + populacao.getIndivduo(0).getFitnessInt());
        }

        if (temSolucao) {
            System.out.println("Encontrado resultado na gera��o " + geracao + " | " + populacao.getIndivduo(0).getGenes() + " (Aptid�o: " + populacao.getIndivduo(0).getFitnessInt() + ")");
        }

	}

}
