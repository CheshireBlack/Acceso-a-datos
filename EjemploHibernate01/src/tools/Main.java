package tools;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import modelo.Departamentos;

public class Main {
	public static void main(String[] args) {
		
		int id = -1;
		String nombre = "";
		String localidad = "";
		
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		
		while (!salir) {
			System.out.println("1. Ver departamento");
			System.out.println("2. Insertar departamento");
			System.out.println("3. Borrar departamento");
			System.out.println("4. Modificar departamento");
			System.out.println("5. Salir");
			try {
				System.out.println("Escribe una de las opciones");
				opcion = sn.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("Has seleccionado la opcion 1");
					System.out.println("Escribe el id del departamento: ");
					//Funciones.selectDepartamento(sn.nextInt());
					Funciones.selectDepartamentoQuery(sn.nextInt());
					break;
				case 2:
					System.out.println("Has seleccionado la opcion 2");
					
					System.out.println("Escribe el id del departamento: ");
					id = sn.nextInt();
					
					System.out.println("Escribe el nombre del departamento: ");
					sn.nextLine();
					nombre = sn.nextLine();
					
					System.out.println("Escribe la localidad del departamento: ");
					localidad = sn.nextLine();
					
					//Funciones.insertarDepartamento(id, nombre, localidad);
					Funciones.insertarDepartamentoQuery(id, nombre, localidad);
					break;
				case 3:
					System.out.println("Has seleccionado la opcion 3");
					
					System.out.println("Escribe el id del departamento: ");
					//Funciones.deleteDepartamento(sn.nextInt());
					Funciones.deleteDepartamentoQuery(sn.nextInt());
					
					break;
				case 4:
					System.out.println("Has seleccionado la opcion 4");

					System.out.println("Escribe el id del departamento a modificar: ");
					id = sn.nextInt();

					System.out.println("Escribe el nombre del departamento: ");
					sn.nextLine();
					nombre = sn.nextLine();

					System.out.println("Escribe la localidad del departamento: ");
					localidad = sn.nextLine();

					//Funciones.modificarDepartamento(id, nombre, localidad);
					Funciones.modificarDepartamentoQuery(id, nombre, localidad);
					break;
				case 5:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 5");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				sn.next();
			}
		}
		System.exit(0);
	}
}

