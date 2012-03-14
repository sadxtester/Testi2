public class Laskuri implements AutolaskuriautomaattiPlugin{
	
	int luku = 0;
	int havaintoja = 0;
	int oikeaVastaus = 0;
	int vastaus = 0;
	
	public String getPluginNimi() {
		
		return "Laskuri";
	}

	public void setLuku(int lahtoluku) {
		luku = lahtoluku;
		
	}

	public void setHavaintojenMaara(int havaintojenMaara) {
		havaintoja = havaintojenMaara;
		
	}
	
	public void oikeaVastaus(int vastaus) {
		
		oikeaVastaus = vastaus;
		
	}

	public int getTulos() {
		vastaus = lisays(luku, havaintoja);
		return vastaus;
	}

	public boolean getTarkistus() {
		if(vastaus == oikeaVastaus ){
		
			return true;
		}else{
			
			return false;
		}
		
		
	}

	protected int lisays (int lahto, int havaintojenMaara) {
		
		for(int i = 0; i < havaintojenMaara; i++){
			
			lahto++;
			
		}
		
		
		return lahto;
	}


	

}
