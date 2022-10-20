import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		AG.setCaracteres("!,.:;?·¡„√‚¬ı’Ù‘Û”ÈÍ… ÌQWERTYUIOPASDFGHJKL«ZXCVBNMqwertyuiopasdfghjklÁzxcvbnm1234567890 ");
		AG.setTaxaDeCrossover(0.6);
        AG.setTaxaDeMutacao(0.3);
        boolean eltismo = true;
        int tamPop = 100;
        int numMaxGeracoes = 10000;
        
        
		Scanner in = new Scanner(System.in);
		System.out.println("Defina a soluÁ„o: ");
		String solucao = in.nextLine();
		while(solucao.length() < 5) {
			System.out.println("Digite uma soluÁ„o com tamanho maior que 5");
			solucao = in.nextLine();
		}
        AG.setSolucao(solucao);      
        int numGenes = AG.getSolucao().length();
        

        //cria a primeira populaÁ„o aleatÈrioa
        Populacao populacao = new Populacao(numGenes, tamPop);

        boolean temSolucao = false;
        int geracao = 0;

        System.out.println("Iniciando... Fitness da soluÁ„o: "+AG.getSolucao().length());
        
        //loop atÈ o critÈrio de parada
        while (!temSolucao && geracao < numMaxGeracoes) {
            geracao++;

            //cria nova populacao
            populacao = AG.novaGeracao(populacao, eltismo);

            System.out.println("GeraÁ„o " + geracao + " | Fitness: " + populacao.getIndivduo(0).getFitness() + " | Melhor: " + populacao.getIndivduo(0).getGenes());
            
            //verifica se tem a solucao
            temSolucao = populacao.temSolocao(AG.getSolucao());
        }

        if (geracao == numMaxGeracoes) {
            System.out.println("N˙mero Maximo de GeraÁıes | " + populacao.getIndivduo(0).getGenes() + " " + populacao.getIndivduo(0).getFitness());
        }

        if (temSolucao) {
            System.out.println("Encontrado resultado na geraÁ„o " + geracao + " | " + populacao.getIndivduo(0).getGenes() + " (Fitness: " + populacao.getIndivduo(0).getFitness() + ")");
        }
    }
	
		
		
		
	}


