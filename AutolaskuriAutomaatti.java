import java.io.File;
import java.util.*;

public class AutolaskuriAutomaatti {
	
	//Kansio jossa kaikki plugarit
	String pluginsKansio;
	
	//Lista yll� pit�� tiedon plugareista
	List plugins;
	
	
	public static void main (String args[]) {
		AutolaskuriAutomaatti laskuri = new AutolaskuriAutomaatti(args);
		laskuri.getPlugins();
		laskuri.runPlugins();
	}

	private void runPlugins() {
		// TODO Auto-generated method stub
		int aloitusLuku = 0;
		int havaintoja = 1;
		int vastaus = 1;
		
		Iterator iter = plugins.iterator();
		while (iter.hasNext()) {
			AutolaskuriautomaattiPlugin alp = (AutolaskuriautomaattiPlugin) iter.next();
			try {
				alp.setLuku(aloitusLuku);
				alp.setHavaintojenMaara(havaintoja);
				alp.oikeaVastaus(vastaus);
				System.out.print(alp.getPluginNimi());
				System.out.print(": Aloitus luku = " + aloitusLuku + " havaintojen maara on =" 
				+havaintoja);
				
				
				int tulos = alp.getTulos();
				System.out.println(" ja vastaus =" +tulos);
					
				boolean olikoOikein = alp.getTarkistus();
				if(olikoOikein)	{
					
					System.out.println("Lasku ja vastaus oikein!");
					
				}else{
					
					System.out.println("Lasku ja vastaus v��rin!");
					
				}
					
			
			
			} catch (SecurityException secEx) {
				System.err.println("plugari '"+alp.getClass().getName()+"' Yritti suorittaa laittomuuden");
			}
		}
	
		
	}

	private void getPlugins() {
		// TODO Auto-generated method stub
		
		File kansio = new File(System.getProperty("user.dir") + File.separator + pluginsKansio);
		ClassLoader cl = new AutolaskuriClassloader(kansio);
		
		if (kansio.exists() && kansio.isDirectory()) {
			
			//Ladataan kansion sis�lt�, mutta ei esim alikansioita
			String[] files = kansio.list();
			for (int i=0; i<files.length; i++) {
				try {
					// Otetaan tiedostot jotka p��ttyv�t ".class"
					if (! files[i].endsWith(".class"))
						continue;

					Class c = cl.loadClass(files[i].substring(0, files[i].indexOf(".")));
					Class[] intf = c.getInterfaces();
					for (int j=0; j<intf.length; j++) {
						if (intf[j].getName().equals("AutolaskuriautomaattiPlugin")) {
							
							//Plugin api
							AutolaskuriautomaattiPlugin alp = (AutolaskuriautomaattiPlugin) c.newInstance();
							plugins.add(alp);
							continue;
						}
					}
				} catch (Exception ex) {
					System.err.println("Tiedosto " + files[i] + " Ei sis�ll� oikeanlaisia plugin luokkia");
				}
			}
		}
		
	}

	AutolaskuriAutomaatti (String args[]) {
		if (args.length > 0)
			pluginsKansio = args[0];
		else
			//Kansio josta plugit l�ytyy
			pluginsKansio = "plugins";

		//Listaan kaikki plugarit
		plugins = new ArrayList();

		//Plugins kansio turvatarkastukseen
		System.setSecurityManager(new AutolaskuriTurva(pluginsKansio));
	}
	
	
	
	
	
	
	
	
	
	
	

}
