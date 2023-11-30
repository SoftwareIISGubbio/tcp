public class SimulaGiornata {
	
    private static double gaussiana(double x, double mu, double sigma, double maxY) {
        double A = maxY / (sigma * Math.sqrt(2 * Math.PI));
        return A * Math.exp(-(Math.pow(x - mu, 2) / (2 * Math.pow(sigma, 2))));
    }
	
	
	public static void main(String[] args) {
        double mu = 12;
        double sigma = 5;
        double base =  gaussiana(6, mu, sigma, 4);
        double max = gaussiana(12, mu, sigma, 4)-base;
        double finto = 3.78;

        for (int ora = 0; ora <= 23; ora += 1) {
        	double y=0;
            double baseGiorno = gaussiana(ora, mu, sigma, 4)-base;
            baseGiorno = baseGiorno<0?0 : baseGiorno/max*finto;
            double perturbazioneOraria = Math.random()*0.4-0.2;
            if(baseGiorno>0) {
            	y=baseGiorno+perturbazioneOraria;
            }
            for(int min=0;min<60;min+=2) {
            	double perturbazioneMinuto = Math.random()*0.1-0.05;
            	// insert into misura(tensione,corrente,potenza,rpm,fornitore,ts) values(1,2,3,4,'io','2023-11-08 08:8:00')
            	y = (y+perturbazioneMinuto);
            	if(y<0 || baseGiorno<=0) {
            		y = 0;
            	}
            	String q = "insert into misura(tensione,corrente,potenza,rpm,fornitore,ts) VALUES(0,%s,0,0,'5','2023-11-09 %d:%d:00');".formatted(y,ora,min);
                
            	System.out.println(q);
            }
        }
	}
}
