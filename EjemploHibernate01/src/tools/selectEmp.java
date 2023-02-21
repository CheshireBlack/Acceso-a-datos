package tools;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import modelo.Departamentos;
import modelo.Empleados;

public class selectEmp {

	public static void main(String[] args) {

		SessionFactory sesFactory = HibernateUtil.getSessionFactory();

		Session sesion = sesFactory.openSession();

		Departamentos depart = null;

		depart = (Departamentos) sesion.get(Departamentos.class, (int) 12);
		
		if (depart != null) {
			System.out.println("Nombre del dpto: " + depart.getDnombre());
			System.out.println("Localización: " + depart.getLoc());
			
			Set<Empleados> empleados = depart.getEmpleadoses();
			Iterator<Empleados> it = empleados.iterator();
			System.out.println("Nº de Empleados: " + empleados.size());
			
			while(it.hasNext()) {
				Empleados emp = it.next();
				System.out.println("Apellido: " + emp.getApellido());
				System.out.println("  \tSalario: "+ emp.getSalario());
			}
		} else {
			System.out.println("El departamento indicado no existe");
		}

		sesion.close();
		System.exit(0);
	}

}
