import java.io.File;

/**
 * 
 * @author Mikko
 * 
 * T�m� osio on kokonaan kopioitu esimerkist�.
 *
 */

//Turvallisuudesta huolehtiva luokka, sek� s��nn�t
public class AutolaskuriTurva extends SecurityManager{
	private String pluginDir = null;

	//Konstruktori
	AutolaskuriTurva (String dir) {
		pluginDir = dir;
	}
	
	
	protected void trusted() {
		if (inClassLoader()) throw new SecurityException();
	}

	
	
	//T�ss� kaikki tarkistukset joita t�m� luokka tekee
	public void checkCreateClassLoader() { trusted(); }
	public void checkAccess (Thread g) { trusted(); }
	public void checkAccess (ThreadGroup g) { trusted(); }
	public void checkExit (int status) { trusted(); }
	public void checkExec (String cmd) { trusted(); }
	public void checkLink (String lib) { trusted(); }
	public void checkRead (java.io.FileDescriptor fd) { trusted(); }
	public void checkRead (String file) {

			trusted();
	}
	public void checkRead (String file, Object context) { trusted(); }
	public void checkWrite (java.io.FileDescriptor fd) { trusted(); }
	public void checkWrite (String file) { trusted(); }
	public void checkDelete (String file) { trusted(); }
	public void checkConnect (String host, int port) { trusted(); }
	public void checkConnect (String host,int port,Object context) {trusted();}
	public void checkListen (int port) { trusted(); }
	public void checkAccept (String host, int port) { trusted(); }
	public void checkMulticast (java.net.InetAddress maddr) { trusted(); }
	public void checkMulticast (java.net.InetAddress maddr, byte ttl) { trusted(); }
	public void checkPropertiesAccess() { trusted(); }
	public void checkPropertyAccess (String key) {

			trusted();
	}
	public void checkPrintJobAccess() { trusted(); }
	public void checkSystemClipboardAccess() { trusted(); }
	public void checkAwtEventQueueAccess() { trusted(); }
	public void checkSetFactory() { trusted(); }
	public void checkMemberAccess (Class clazz, int which) { trusted(); }
	public void checkSecurityAccess (String provider) { trusted(); }


	public void checkPackageAccess (String pkg) { 
		if (inClassLoader() && !pkg.startsWith("java.") && !pkg.startsWith("javax."))
			throw new SecurityException();
	}


	public void checkPackageDefinition (String pkg) { 
		if (inClassLoader() && ((pkg.startsWith("java.") || pkg.startsWith("javax.") || pkg.startsWith("sun."))))
			throw new SecurityException();
	}


	public boolean checkTopLevelWindow (Object window) { 
		trusted();
		return true; 
	}

}
