import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	//koristimo tri klijenta
	static ServerNit klijenti[]= new ServerNit[3];
	
	public static ServerSocket serverSoketPodaci;
	public static Socket klijentSoketZaPodatke;
	
	//brojevi portova
	public static void main(String args[]) {
		/*
		int port=1908;
		int port2=4013;
	
	//alternativni broj porta
		if(args.length>0){
			port2=Integer.parseInt(args[0]);
		}
		*/
		
		//deklaracija soketa klijenta koji ce doci na server
		Socket klijentSoket=null;
		Socket klijentSoketZaPodatke=null;
		
		
		
		try {
			//zauzimamo port
			//ServerSocket serverSoket = new ServerSocket(port);
		   // serverSoketPodaci=new ServerSocket(port2);
	 ServerSocket serverSoket = new ServerSocket(1908);
			   serverSoketPodaci=new ServerSocket(4013);
			while (true) {
				//cekamo klijenta ali u pitanju je veza komunikaciju stalno aktivna
				klijentSoket=serverSoket.accept();
				
				//System.out.println("Veza je uspostavljena sa klijentom!");
				
				//proveravamo koje je mesto slobodno i pravi se ya svakog klijenta po jedna nit
				for (int i = 0; i<=2; i++) {
					if(klijenti[i]==null){
						klijenti[i]=new ServerNit(klijentSoket, klijenti);
						klijenti[i].start();
						break;
					
					}
					
				}
			}
		}
			catch (BindException e) {
				System.out.println("Port je zauzet!");
			}
		 catch (IOException e) {
			System.out.println(e);
		}
	}
}

