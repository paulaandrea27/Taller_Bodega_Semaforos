package modelo;

import java.util.concurrent.Semaphore;

public class Bodega {
	
	public final static boolean TIPO_UNO = true;
	public final static boolean TIPO_DOS = false;
	
	int espacio_disponible;
	private Semaphore capacidad;
	int cant_art1;
	private Semaphore art1;
	int cant_art2;
	private Semaphore art2;
	private Semaphore mutex;
	
	public Bodega()
	{
		espacio_disponible = 200;
		cant_art1 = 0;
		cant_art2 = 0;
		capacidad = new Semaphore(200);
		art1 = new Semaphore(0);
		art2 = new Semaphore(0);
		mutex = new Semaphore(1);
	}
	
	public void colocarArticulo(boolean tipo) throws InterruptedException
	{
		int espacio = 0;
		if(tipo == TIPO_UNO)
		{
			espacio = 10;
			capacidad.acquire(espacio);
			mutex.acquire();
				espacio_disponible = espacio_disponible - espacio;
				cant_art1 = cant_art1 + 1;
			mutex.release();
			art1.release(1);
		} else
		{
			espacio = 15;
			capacidad.acquire(espacio);
			mutex.acquire();
				espacio_disponible = espacio_disponible - espacio;
				cant_art2 = cant_art2 + 1;
				System.out.println("----------------------------------");
				System.out.println("Espacio Disponible: "+espacio_disponible);
				System.out.println("Artículos Tipo 1: "+cant_art1);
				System.out.println("Artículos Tipo 2: "+cant_art2);
				System.out.println("----------------------------------");
			mutex.release();
			art2.release(1);
		}
		   
	}
	
	public void formarPaquete() throws InterruptedException
	{
		art1.acquire(3);
		art2.acquire(4);
		mutex.acquire();
			cant_art1 = cant_art1 - 3;
			cant_art2 = cant_art2 - 4;
			int espacio_nuevo = (3*10)+(4*15);
			espacio_disponible = espacio_disponible + espacio_nuevo;
			System.out.println("Se formó el paquete correctamente");
			System.out.println("----------------------------------");
			System.out.println("Espacio Disponible: "+espacio_disponible);
			System.out.println("Artículos Tipo 1: "+cant_art1);
			System.out.println("Artículos Tipo 2: "+cant_art2);
			System.out.println("----------------------------------");
		mutex.release();
		capacidad.release(espacio_nuevo);
	}

	public int getEspacio_disponible() {
		return espacio_disponible;
	}

	public void setEspacio_disponible(int espacio_disponible) {
		this.espacio_disponible = espacio_disponible;
	}

	public int getCant_art1() {
		return cant_art1;
	}

	public void setCant_art1(int cant_art1) {
		this.cant_art1 = cant_art1;
	}

	public int getCant_art2() {
		return cant_art2;
	}

	public void setCant_art2(int cant_art2) {
		this.cant_art2 = cant_art2;
	}
	
	

}
