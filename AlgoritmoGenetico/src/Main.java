import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Define a solução -> maior que 5
		Scanner in = new Scanner(System.in);
		System.out.print("Defina a solucao: ");
		String solucao = in.nextLine();
		while(solucao.length() < 5) {
			System.out.println("Tamanho invalido, insira uma String com tamanho maior que 5:");
			solucao = in.nextLine();
		}
		
        AG.setSolucao(solucao);    
        AG.setCaracteres("!,.:;?áÁãÃâÂõÕôÔóÓéêÉÊíQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890 ");
        AG.setTaxaDeCrossover(0.6);
        AG.setTaxaDeMutacao(0.3);
        boolean eltismo = true;
        
        int pop = in.nextInt();
        while(pop < 0) {
        	System.out.println("Valor de populacao invalido, precisa ser acima de 0");
        	pop = in.nextInt();
        }
        int tamPop = pop;
        //numero máximo de gerações
        int geracoes = in.nextInt();
        while(pop < 0) {
        	System.out.println("Valor de geracoes invalido, precisa ser acima de 0");
        	geracoes = in.nextInt();
        }
        int numMaxGeracoes = geracoes;

        //define o número de genes do indivíduo baseado na solução
        int numGenes = AG.getSolucao().length();

        //cria a primeira população aleatérioa
        Populacao populacao = new Populacao(numGenes, tamPop);

        boolean temSolucao = false;
        int geracao = 0;

        System.out.println("Fitness da solução: " + AG.getSolucao().length());
        
        //loop até o critério de parada
        while (!temSolucao && geracao < numMaxGeracoes) {
            geracao++;

            //cria nova populacao
            populacao = AG.novaGeracao(populacao, eltismo);

            System.out.println("Geração " + geracao + " | Fitness: " + populacao.getIndivduo(0).getFitnessInt() + " | Melhor: " + populacao.getIndivduo(0).getGenes());
            
            //verifica se tem a solucao
            temSolucao = populacao.temSolocao(AG.getSolucao());
        }

        if (geracao == numMaxGeracoes) {
            System.out.println("Número Maximo de Gerações | " + populacao.getIndivduo(0).getGenes() + " " + populacao.getIndivduo(0).getFitnessInt());
        }

        if (temSolucao) {
            System.out.println("Encontrado resultado na geração " + geracao + " | " + populacao.getIndivduo(0).getGenes() + " (Aptidão: " + populacao.getIndivduo(0).getFitnessInt() + ")");
        }

	}

}
