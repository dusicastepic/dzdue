
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class Klijent {
	
    static Socket soketZaKomunikaciju=null;	
	static Socket soketZaPodatke=null;
	
	static PrintStream izlazniTokKaServeru=null;
	static BufferedReader ulazniTokOdServera=null;
	
	static PrintStream izlazniTokPodakaKaServeru=null;
	static BufferedReader ulazniTokPodatakaOdServera=null;
	
	static BufferedReader ulazKonzola=null;
	static boolean kraj=false;
	
	public static void main(String[] args) {
		String imeKorisnika;
		String linija;
		String operacija;
		try {
			//port za kontrolnu vezu
			int port=1908;
			
			//povezujemo se na host	
			soketZaKomunikaciju=new Socket("localhost", port);
		

			//inicijalizacija ulaza sa konzole
			ulazKonzola=new BufferedReader(new InputStreamReader(System.in));
			
			izlazniTokKaServeru=new PrintStream(soketZaKomunikaciju.getOutputStream());
			ulazniTokOdServera=new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			
		
			
			System.out.println(ulazniTokOdServera.readLine());
			imeKorisnika=ulazKonzola.readLine();
			//saljemo serveru
			izlazniTokKaServeru.println(imeKorisnika);
			char pretposlednjeSlovo=imeKorisnika.charAt(imeKorisnika.length()-2);
			switch (pretposlednjeSlovo) {
			case 'c':
				System.out.println("*ZDRAVO* "+imeKorisnika.substring(0, imeKorisnika.length()-1)+"e"+" Ovo je interaktivni digitron duca4.0, ako ne zelis da nastavis dalje napisi DOSTA");
				break;
			case 'a':
				System.out.println("*ZDRAVO* "+imeKorisnika+"e"+" Ovo je interaktivni digitron duca4.0, ako ne zelis da nastavis dalje napisi DOSTA");
				break;

			default:System.out.println("*ZDRAVO* "+imeKorisnika+" Ovo je interaktivni digitron duca4.0, ako ne zelis da nastavis dalje napisi DOSTA");
				break;
			}
			
			
			linija=ulazKonzola.readLine();
        		if(linija.startsWith("DOSTA")||linija.startsWith("dosta")){
        			System.out.println("Dovidjenja hvala Vam iako niste raèunali sa nama!");
        			return;}
			
        		int y = 1;

    			for (int x=0; y<10; x++){
    				System.out.println("Izaberi jednu operaciju sabiranje(+),oduzimanje(-),množenje(*),deljenje(/)");
    			   
    			    
    			
    				while(true){
    					operacija=ulazKonzola.readLine();
    					if((operacija.equals("+"))||(operacija.equals("-"))||(operacija.equals("*"))||(operacija.equals("/"))
    							||(operacija.startsWith("s"))||(operacija.startsWith("o"))||(operacija.startsWith("m"))||(operacija.startsWith("d"))){
    					
    					break;}
    					System.out.println("Pogrešno ste ukucali operaciju. pokušajte ponovo!");
    					
    				
    				}
    				izlazniTokKaServeru.println(operacija);
    				//port za podatke
    				int port2=4013;
    				
    				if(args.length>0){
    					port2=Integer.parseInt(args[0]);
    				}
    				soketZaPodatke=new Socket("localhost",4013);
    				izlazniTokPodakaKaServeru=new PrintStream(soketZaPodatke.getOutputStream());
    				ulazniTokPodatakaOdServera=new BufferedReader(new InputStreamReader(soketZaPodatke.getInputStream()));
    				
    			
    				System.out.println(ulazniTokOdServera.readLine());
    				
    				String brojevi=ulazKonzola.readLine();
    				
    				
    				
    				
    				izlazniTokPodakaKaServeru.println(brojevi);
    				double resenje=0;
    				resenje = Double.parseDouble(ulazniTokPodatakaOdServera.readLine());
    				System.out.println("Resenje je "+resenje);
    				
    				soketZaPodatke.close();
    				
    				System.out.println("Ako jos zelite da racunate sa nama recite DA,a ako ne recite DOSTA");
					
    				
   		         while (true) {
   		        		linija=ulazKonzola.readLine();
   		        		if(linija.startsWith("DOSTA")||linija.startsWith("dosta")){
   		        			System.out.println("Dovidjenja hvala Vam što ste raèunali sa nama!");
   		        			y=10;
   		        			break;
   		        			}
   		        		break;
   		        	}
   		         }
    				
        		
        		    
        		    
        		    
        		
        		
        		
        		
        		
        		
        		
    			
			while (!kraj) {
				izlazniTokKaServeru.println(ulazKonzola.readLine());
				
			}
			soketZaKomunikaciju.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}