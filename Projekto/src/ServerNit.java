import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;



public class ServerNit extends Thread{
	
	 BufferedReader ulazniTokOdKlijenta=null;
	 PrintStream izlazniTokKaKlijentu=null;
	 
	 BufferedReader TokOdKlijentaZaPodatke=null;
	 PrintStream TokKaKlijentuZaPodatke=null;
	 
	 Socket soketZaKomunikaciju=null;
	 Socket soketZaPodatke=null;
	
	// Socket klijentSoketZaPodatke=null;
	// Socket klijentSoket=null;
	 
	ServerNit[] klijenti;
	/*
	public ServerNit(Socket  soketZaKomunikaciju,Socket soketZaPodatke,ServerNit[] klijent) {
	
		this.soketZaKomunikaciju=soketZaKomunikaciju;
		this.soketZaPodatke=soketZaPodatke;
		this.klijenti=klijent;
	}*/
	public ServerNit(Socket  soketZaKomunikaciju,ServerNit[] klijent) {
		
		this.soketZaKomunikaciju=soketZaKomunikaciju;
		this.klijenti=klijent;
	}
	
	 



	//svaka nit ima svoju run metodu i sluzi za osluskivanje

	@Override
	public void run() {
		String imeKorisnika;
		String linija;
		String operacija;
		double prvi;
		double drugi;
	    double proizvod=1;
        double drugiSabirak=0;
        double del;
        	 
		
		try {
			ulazniTokOdKlijenta=new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			izlazniTokKaKlijentu=new PrintStream(soketZaKomunikaciju.getOutputStream());
			
			
			izlazniTokKaKlijentu.println("Unesite ime:");
			imeKorisnika=ulazniTokOdKlijenta.readLine();
			
			while(true){
			
			operacija=ulazniTokOdKlijenta.readLine();
			
			
			izlazniTokKaKlijentu.println("Unesi brojeve koje želiš u izrazu: ");
			
			String brojevi;
			soketZaPodatke = Server.serverSoketPodaci.accept();
			TokOdKlijentaZaPodatke=new BufferedReader(new InputStreamReader(soketZaPodatke.getInputStream()));
			TokKaKlijentuZaPodatke=new PrintStream(soketZaPodatke.getOutputStream());
			
			brojevi=TokOdKlijentaZaPodatke.readLine();
			String[] nizbrojeva= brojevi.split(" ");
			
			
			

				
				
		        
		      
		        	double resenje=0;
		        	if(operacija.equals("+")||operacija.startsWith("sa")) {
		        		
		        				for (int i = 0; i < nizbrojeva.length; i++) {
		        				resenje+=Double.parseDouble(nizbrojeva[i]); 
		        				
		        			}}
		        			
		        	if(operacija.equals("-")||operacija.startsWith("od")){
		        		prvi=Double.parseDouble(nizbrojeva[0]);
			        		for (int i = 0; i < nizbrojeva.length-1; i++) {
			        			 
			        			drugiSabirak-=Double.parseDouble(nizbrojeva[i+1]); resenje=prvi+drugiSabirak;	
							}
			        		
			        	}
					
					if(operacija.equals("*")||operacija.startsWith("mn")) {
						for (int j = 0; j < nizbrojeva.length; j++) {
							proizvod *=Integer.parseInt(nizbrojeva[j]); 
							resenje=proizvod;
						}}
					 
		        
					if(operacija.equals("/")||operacija.equals("de")){
						del=Double.parseDouble(nizbrojeva[0]);
						for (int j = 0; j < nizbrojeva.length-1; j++) {
							 
							del/=Double.parseDouble(nizbrojeva[j+1]);
							resenje=del;
						}
					}
					
		        	
		            TokKaKlijentuZaPodatke.println(resenje);
					
		      
			
				
				//soketZaPodatke.close();
			
			}
			//soketZaKomunikaciju.close();
		}catch (IOException e) {
		System.out.println(e);
	}
		
		//oslobadja mesto za novog klijenta
		for (int i = 0; i < klijenti.length-1; i++) {
			if(klijenti[i]==this){
				klijenti[i]=null;
				System.out.println("Klijent br."+i);
			}
			
			
		}


}
	}
			
	