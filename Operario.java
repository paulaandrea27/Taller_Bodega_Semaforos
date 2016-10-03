package modelo;

public class Operario extends Thread {

	private Bodega bodega;

	public Operario(Bodega b) {
		// TODO Auto-generated constructor stub
		bodega = b;
	}
	
	public void run()
	{
		int repetir = 0;
		while(repetir < 100)
		{
			try {
				boolean tipo = aleatorio();
				if(tipo == Bodega.TIPO_UNO)
					System.out.println("Se va a agregar un artículo tipo uno");
				else
					System.out.println("Se va a agregar un artículo tipo dos");
				bodega.colocarArticulo(tipo);
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
	
	private boolean aleatorio()
	{
		int num = (int)(Math.random()+0.5);
		if(num == 0)
			return Bodega.TIPO_UNO;
		else
			return Bodega.TIPO_DOS;
	}
}
