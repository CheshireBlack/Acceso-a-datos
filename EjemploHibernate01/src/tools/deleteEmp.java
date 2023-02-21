package tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Empleados;

public class deleteEmp {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session sesion = sessionFactory.openSession();

		Transaction tx = sesion.beginTransaction();

		Empleados emp = (Empleados) sesion.get(Empleados.class, (int) 10);

		if (emp != null) {
			sesion.delete(emp);
			System.out.println("Empleado borrado");
		} else {
			System.out.println("Error en el borrado");
		}
		tx.commit();

		sesion.close();
		System.exit(0);
	}

}
