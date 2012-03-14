import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 
 * @author Mikko
 * 
 * Olen tehnyt omaa toteutusta seuraten esimerkki sovellusta
 * ja koittanut ymm‰rt‰‰ mit‰ kukin asia tekee. Osa toteutuksesta
 * ei ole t‰ysin omaani.
 *
 */



public class AutolaskuriClassloader extends ClassLoader{
	
//Luokka josta ladataan
	File directory;



	//Konstruktori
	public AutolaskuriClassloader(File dir){
	
		this.directory = dir;
		
		
	}
	
	public Class loadClass (String name) throws ClassNotFoundException { 
	      return loadClass(name, true); 
	    }


	
	//Turvallisuus syist‰ t‰m‰ Classloader abstrakti metodi tarkistaa, ett‰ aliluokat 
	//on m‰‰ritelty.
    public Class loadClass (String classname, boolean resolve) throws ClassNotFoundException {
      try {
         
    	  //Tarkistetaan v‰limuistista jo sinne ladatut luokat
        Class c = findLoadedClass(classname);


        //Metodi lataa luokat. Se lataa myˆs superluokat (superclass)
        if (c == null) {
          try { c = findSystemClass(classname); }
          catch (Exception ex) {}
        }

        
        //Jos edellinen metodi ei lˆyd‰ luokkia, t‰m‰ yritt‰‰ ladata ne tiedostosta
        if (c == null) {
        	
          // Selvitt‰‰ tiedostonimen
          String filename = classname.replace('.',File.separatorChar)+".class";

          // Luodaan File objekti
          File f = new File(directory, filename);

          // Luetaan luokka
          int length = (int) f.length();
          byte[] classbytes = new byte[length];
          DataInputStream in = new DataInputStream(new FileInputStream(f));
          in.readFully(classbytes);
          in.close();

          // K‰‰nnet‰‰n classbytes luokaksi
          c = defineClass(classname, classbytes, 0, length);
        }

     
        if (resolve) resolveClass(c);

        // Luokka on ladattu
        return c;
      }
      // Virheen tarkastaja
      catch (Exception ex) { throw new ClassNotFoundException(ex.toString()); }
    }

}