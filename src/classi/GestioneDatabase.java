package classi;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestioneDatabase {
	
	private static File f; 
	private ObjectInputStream oin;
	private ObjectOutputStream oos;
	private AppendingObjectOutputStream aoos;
	
	public GestioneDatabase(){
		
	}
	
	private boolean giaRegistrato(Utente u, String tipo) throws ClassNotFoundException { //controlla prima della registrazione se l'username è già in uso
		boolean check=false;
		
		try {
			FileInputStream fi;
			
			if(tipo.equals("admin"))
				fi = new FileInputStream("src/credenzialiAdmin.dat");
			else
				fi = new FileInputStream("src/credenzialiUser.dat");
			
			oin = new ObjectInputStream(fi);
			
			Utente tmp;
			
			while(true) {
				try {
					tmp = (Utente) oin.readObject();
					if(tmp.getUsername().equals(u.getUsername())) { //se gli username coincidono allora l'utente si è già registrato
						check=true;
						break;
					}
				}
				catch(EOFException e) {
					break;
				}
			}
			fi.close();
			oin.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public boolean registraUtente(Utente u, String tipo) throws ClassNotFoundException { //registra l'utente
		
		boolean check=false;
		boolean isGiaRegistrato = giaRegistrato(u, tipo);
		
		if(!isGiaRegistrato) {
			try {
				FileOutputStream fo;
				
				if(tipo.equals("admin")) {
					fo = new FileOutputStream("src/credenzialiAdmin.dat", true);
					f = new File("src/credenzialiAdmin.dat");
				}
					
				else {
					fo = new FileOutputStream("src/credenzialiUser.dat", true);
					f = new File("src/credenzialiUser.dat");
				}
				
					
				 if(f.length() == 0) {
	                    oos = new ObjectOutputStream(fo);
	                    oos.writeObject(u);
	                    oos.close();
	                    check=true;
	             }
	 
				 // There is content in file to be write on
	             else {
                    aoos = null;
                    aoos = new AppendingObjectOutputStream(fo);
                    aoos.writeObject(u);	 
                    // Closing the FileOutputStream object
                    // to release memory resources
	                aoos.close();
	                check=true;
	             }
				 
			}
				
			catch (IOException e) {
				check=false;
				e.printStackTrace();
			}
		}
		return check;
	}
	
	public Utente login(String tipo, String username, String password) {
		SHA256 sha256 = new SHA256();
		password = sha256.encrypt(password);
		
		Utente tmp=null;
		boolean check=false;
		
		try {
			FileInputStream fi;
			
			if(tipo.equals("admin"))
				fi = new FileInputStream("src/credenzialiAdmin.dat");
			else
				fi = new FileInputStream("src/credenzialiUser.dat");
			
			oin = new ObjectInputStream(fi);
			
			while(true) {
				try {
					tmp = (Utente) oin.readObject();
					if(tmp.getUsername().equals(username) && tmp.getPassword().equals(password)) {
						check=true;
						break;
					}
				}
				catch(EOFException | ClassNotFoundException e) {
					break;
				}
			}fi.close();
			oin.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		if(check)
			return tmp;
		else
			return null;
	}
	
	public void salvaQuestionarioSuFile(Questionario q) {
		try {
			FileOutputStream fos = new FileOutputStream("src/000.sur");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(q);
			
			fos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Questionario getQuestionario() {
		Questionario tmp = null;
		
		try {
			FileInputStream fis = new FileInputStream("src/000.sur");
			oin = new ObjectInputStream(fis);
			
			try{
				tmp = (Questionario) oin.readObject();
			}
			catch(EOFException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			fis.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return tmp;
	}
	
	public void salvaRispostaSuFile(QuestionarioSvolto q) {
		try {
			FileOutputStream fos = new FileOutputStream("src/000risp.sur");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(q);
			
			fos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public QuestionarioSvolto getRisposta() {
		QuestionarioSvolto tmp = null;
		
		try {
			FileInputStream fis = new FileInputStream("src/000risp.sur");
			oin = new ObjectInputStream(fis);
			
			try{
				tmp = (QuestionarioSvolto) oin.readObject();
			}
			catch(EOFException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			fis.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return tmp;
	}
}
