import java.util.Random;

public class AG {
	private static String solucao;
	private static double taxaDeCrossover;
	private static double taxaDeMutacao;
	private static String caracteres;

	public static Populacao novaGeracao(Populacao populacao, boolean elitismo) {
		// nova popula��o do mesmo tamanho da antiga
		Populacao novaPopulacao = new Populacao(populacao.getTamPopulacao());

		// se tiver elitismo, mant�m o melhor indiv�duo da gera��o atual
		if (elitismo) {
			novaPopulacao.setIndividuo(populacao.getIndivduo(0));
		}

		// insere novos indiv�duos na nova popula��o, at� atingir o tamanho m�ximo
		while (novaPopulacao.getNumIndividuos() < novaPopulacao.getTamPopulacao()) {
			// seleciona os 2 pais por torneio
			Individuo[] pais = selecaoTorneio(populacao);

			Individuo[] filhos = new Individuo[2];

			// verifica a taxa de crossover, se sim realiza o crossover, se n�o, mant�m os
			// pais selecionados para a pr�xima gera��o
			// if (r.nextDouble() <= taxaDeCrossover) {
			filhos = crossover(pais[1], pais[0]);
			// } else {
			// filhos[0] = new Individuo(pais[0].getGenes());
			// filhos[1] = new Individuo(pais[1].getGenes());
			// }

			// adiciona os filhos na nova gera��o
			novaPopulacao.setIndividuo(filhos[0]);
			novaPopulacao.setIndividuo(filhos[1]);
		}

		// ordena a nova popula��o
		novaPopulacao.ordenaPopulacao();
		return novaPopulacao;
	}

	public static Individuo[] crossover(Individuo individuo1, Individuo individuo2) {
		Random r = new Random();

		// sorteia o ponto de corte
		int pontoCorte1 = r.nextInt((individuo1.getGenes().length() / 2) - 2) + 1;
		int pontoCorte2 = r.nextInt((individuo1.getGenes().length() / 2) - 2) + individuo1.getGenes().length() / 2;

		Individuo[] filhos = new Individuo[2];

		// pega os genes dos pais
		String genePai1 = individuo1.getGenes();
		String genePai2 = individuo2.getGenes();

		String geneFilho1;
		String geneFilho2;

		// realiza o corte,
		geneFilho1 = genePai1.substring(0, pontoCorte1);
		geneFilho1 += genePai2.substring(pontoCorte1, pontoCorte2);
		geneFilho1 += genePai1.substring(pontoCorte2, genePai1.length());

		geneFilho2 = genePai2.substring(0, pontoCorte1);
		geneFilho2 += genePai1.substring(pontoCorte1, pontoCorte2);
		geneFilho2 += genePai2.substring(pontoCorte2, genePai2.length());

		// cria o novo indiv�duo com os genes dos pais
		filhos[0] = new Individuo(geneFilho1);
		filhos[1] = new Individuo(geneFilho2);

		return filhos;
	}

	public static Individuo[] selecaoTorneio(Populacao populacao) {
		Random r = new Random();
		Populacao populacaoIntermediaria = new Populacao(3);
		Individuo[] pais = new Individuo[2];

		for (int i = 0; i < 2; i++) {
			populacaoIntermediaria.setIndividuo(populacao.getIndivduo(r.nextInt(populacao.getTamPopulacao())));
			populacaoIntermediaria.setIndividuo(populacao.getIndivduo(r.nextInt(populacao.getTamPopulacao())));
			populacaoIntermediaria.setIndividuo(populacao.getIndivduo(r.nextInt(populacao.getTamPopulacao())));
			populacaoIntermediaria.ordenaPopulacao();
			// seleciona o melhor individuo da melhor popula��o
			pais[i] = populacaoIntermediaria.getIndivduo(0);
		}

		return pais;
	}

	public static String getSolucao() {
		return solucao;
	}

	public static void setSolucao(String solucao) {
		AG.solucao = solucao;
	}

	public static double getTaxaDeCrossover() {
		return taxaDeCrossover;
	}

	public static void setTaxaDeCrossover(double taxaDeCrossover) {
		AG.taxaDeCrossover = taxaDeCrossover;
	}

	public static double getTaxaDeMutacao() {
		return taxaDeMutacao;
	}

	public static void setTaxaDeMutacao(double taxaDeMutacao) {
		AG.taxaDeMutacao = taxaDeMutacao;
	}

	public static String getCaracteres() {
		return caracteres;
	}

	public static void setCaracteres(String caracteres) {
		AG.caracteres = caracteres;
	}

}
