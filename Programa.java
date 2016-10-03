package modelo;

public class Programa {
	
	private Bodega bodega;
	private Empacador empacador;
	private Operario operario;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Programa programa = new Programa();
		programa.empacador.start();
		programa.operario.start();
		
	}
	
	public Programa()
	{
		bodega = new Bodega();
		empacador = new Empacador(bodega);
		operario = new Operario(bodega);
		
	}

}
