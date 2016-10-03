package modelo;

public class Empacador extends Thread {
	
	private Bodega bodega;

	public Empacador(Bodega b) {
		// TODO Auto-generated constructor stub
		bodega = b;
	}
	
	public void run()
	{
		int repetir = 0;
		while(repetir < 100)
		{
			try {
				bodega.formarPaquete();
				
				repetir++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				int capacidad = bodega.getEspacio_disponible();
				int art1 = bodega.getCant_art1();
				int art2 = bodega.getCant_art2();
				System.out.println("El empacador no pudo formar paquete cuando"
						+ " la capacidad era: "+capacidad+", habían "+art1+
						" artículos tipo 1 y "+art2+" artículos tipos 2.");
				System.out.println("----------------------------------");
				repetir++;
			}
			
		}
		
	}
	
}
