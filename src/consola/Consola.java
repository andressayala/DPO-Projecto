package consola;

import java.util.Scanner;
import modelo.DulcesNDados;

public abstract class Consola {
	
	protected DulcesNDados sistema;
	protected Scanner scanner;
	
	public Consola(DulcesNDados sistema){
		this.sistema = sistema;
		this.scanner = new Scanner(System.in);
	}
	
	public abstract void mostrarMenu();
	
	public abstract void ejecutarOpcion(int opcion);
	
	public int leerEntero(String mensaje){
		int valor = -1;
		boolean valido = false;
		
		while(!valido){
			try{
				System.out.println(mensaje);
				valor = Integer.parseInt(scanner.nextLine());
				valido = true;
			}
			catch(NumberFormatException e){
				System.out.println("Debe ingresar un numero entero");
			}
		}
		
		return valor;
	}
	
	public String leerTexto(String mensaje){
		System.out.println(mensaje);
		return scanner.nextLine();
	}
}