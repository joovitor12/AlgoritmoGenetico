import java.util.Random;

public class Individuo {

    private String genes = "";
    private int fitness = 0;

    //gera um indiv�duo aleat�rio
    public Individuo(int numGenes) {
        genes = "";
        Random r = new Random();
        
        String caracteres = AG.getCaracteres();
        
        for (int i = 0; i < numGenes; i++) {
            genes += caracteres.charAt(r.nextInt(caracteres.length()));
        }
        
        geraFitness();        
    }

    //cria um indiv�duo com os genes definidos
    public Individuo(String genes) {    
        this.genes = genes;
        
        Random r = new Random();
        //se for mutar, cria um gene aleat�rio, aqui ocorre a mutação
        if (r.nextDouble() <= AG.getTaxaDeMutacao()) {
            String caracteres = AG.getCaracteres();
            String geneNovo="";
            int posAleatoria = r.nextInt(genes.length());
            for (int i = 0; i < genes.length(); i++) {
                if (i==posAleatoria){
                    geneNovo += caracteres.charAt(r.nextInt(caracteres.length()));
                }else{
                    geneNovo += genes.charAt(i);
                }
                
            }
            this.genes = geneNovo;   
        }
        geraFitness();
    }

    //gera o valor de aptid�o, ser� calculada pelo n�mero de bits do gene iguais ao da solu��o
    private void geraFitness() {
        String solucao = AG.getSolucao();
        for (int i = 0; i < solucao.length(); i++) {
            if (solucao.charAt(i) == genes.charAt(i)) {
                fitness++;
            }
        }
    }

    public int getFitness() {
        return fitness;
    }
   
    public void setGenes(String genes) {
		this.genes = genes;
	}

	public String getGenes() {
        return genes;
    }
	
	
	
}
