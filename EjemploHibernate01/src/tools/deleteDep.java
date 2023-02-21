package tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Departamentos;

public class deleteDep {

	public static void deleteDepartamento(int id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session sesion = sessionFactory.openSession();

		Transaction tx = sesion.beginTransaction();

		Departamentos dep = (Departamentos) sesion.get(Departamentos.class, id);

		if (dep != null) {
			sesion.delete(dep);
			System.out.println("Departamento borrado\n");
		} else {
			System.out.println("Error en el borrado\n");
		}
		tx.commit();

		sesion.close();
	}

}
