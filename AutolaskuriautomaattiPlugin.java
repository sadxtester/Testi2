

public interface AutolaskuriautomaattiPlugin {
	
	//Plugarin nimi
	public String getPluginNimi();
	
	// Antaa alkuluvun
	public void setLuku(int lahtoluku);
	
	//Montako havaintoa tehdaan
	public void setHavaintojenMaara(int havaintojenMaara);
	
	//Mikä on oikea vastaus
	public void oikeaVastaus(int vastaus);
	
	// plauttaa vastauksen
	public int getTulos();
	
	
	//Plauttaa pitiko paikkansa
	public boolean getTarkistus();
	


}
