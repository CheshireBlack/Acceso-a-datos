package tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import modelo.Departamentos;

public class selectDep {

	public static void selectDepartamento(int id) {

		SessionFactory sesFactory = HibernateUtil.getSessionFactory();

		Session sesion = sesFactory.openSession();

		Departamentos depart = null;

		depart = (Departamentos) sesion.get(Departamentos.class, id);
		if (depart != null) {
			System.out.println("Nombre del dpto: " + depart.getDnombre());
			System.out.println("Localización: " + depart.getLoc());
			System.out.println();
		} else {
			System.out.println("El departamento indicado no existe\n");
		}

		sesion.close();
	}

}
