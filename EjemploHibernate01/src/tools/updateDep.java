package tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import modelo.Departamentos;

public class updateDep {

	public static void modificarDepartamento(int id, String nombre, String localidad) {

		if (id != -1 && nombre != "" && localidad != "") {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();

			Departamentos dep = new Departamentos();

			dep.setDptoNo(id);
			dep.setDnombre(nombre);
			dep.setLoc(localidad);

			try {
				sesion.update(dep);
				tx.commit();

				System.out.println("Departamento modificado\n");

			} catch (ConstraintViolationException cve) {
				System.out.println("Problemas al guardar: Registro duplicado\n");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Problemas al guardar: " + e.getMessage() + "\n");
			}

			sesion.close();
		}
	}

}
