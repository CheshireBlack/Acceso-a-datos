package tools;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

import javassist.tools.framedump;
import modelo.Departamentos;
import modelo.Empleados;

public class Funciones {
	
	// Sin querys
	
	public static void selectDepartamento(int id) {

		SessionFactory sesFactory = HibernateUtil.getSessionFactory();

		Session sesion = sesFactory.openSession();

		Departamentos depart = null;
		
		String resul = "";

		depart = (Departamentos) sesion.get(Departamentos.class, id);
		if (depart != null) {
			System.out.println("Nombre del dpto: " + depart.getDnombre());
			System.out.println("Localización: " + depart.getLoc());
			System.out.println();
		} else {
			System.out.println("El departamento indicado no existe\n");
		}
		 TreeSet<Empleados>empleados=(TreeSet<Empleados>) depart.getEmpleadoses();
         Iterator<Empleados>it=empleados.iterator();
		
		 while(it.hasNext()) {
             Empleados emp = it.next();
             System.out.print("Apellido: "+emp.getApellido());
             System.out.println("\tSalario: "+emp.getSalario());
             resul=resul+"\n"+"Apellido: "+emp.getApellido()+"\t"+"Salario: "+emp.getSalario();
             
         }

		sesion.close();
	}

	public static void insertarDepartamento(int id, String nombre, String localidad) {

		if (id != -1 && nombre != "" && localidad != "") {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();

			Departamentos dep = new Departamentos();

			dep.setDptoNo(id);
			dep.setDnombre(nombre);
			dep.setLoc(localidad);

			try {
				sesion.save(dep);
				tx.commit();

				System.out.println("Departamento insertado\n");

			} catch (ConstraintViolationException cve) {
				System.out.println("Problemas al guardar: Registro duplicado\n");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Problemas al guardar: " + e.getMessage() + "\n");
			}

			sesion.close();
		}
	}
	
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
	
	
	// Querys
	
	public static void selectDepartamentoQuery(int id) {

		SessionFactory sesFactory = HibernateUtil.getSessionFactory();

		Session sesion = sesFactory.openSession();

		Query hqlSelect = sesion.createQuery("from Departamentos where dptoNo=:dpto_NO");
		hqlSelect.setInteger("dpto_NO", id);
		
		Departamentos dep = (Departamentos) hqlSelect.uniqueResult();
		if (dep != null) {
			System.out.println(dep.toString());
		} else {
			System.out.println("El departamento indicado no existe\n");
		}

		sesion.close();
	}
	
	public static void insertarDepartamentoQuery(int id, String nombre, String localidad) {

		if (id != -1 && nombre != "" && localidad != "") {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();
			Query hqlInsert = sesion.createQuery("insert into Departamentos (dptoNo, dnombre, loc) SELECT :dptoNo, :dnombre, :loc");
			hqlInsert.setInteger("dptoNo", id);
			hqlInsert.setString("dnombre", nombre);
			hqlInsert.setString("loc", localidad);
			hqlInsert.executeUpdate();

			tx.commit();

			System.out.println("Departamento insertado\n");

			sesion.close();
		}
	}
	
	public static void deleteDepartamentoQuery(int id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session sesion = sessionFactory.openSession();

		Transaction tx = sesion.beginTransaction();
		
		Query hqlDelete = sesion.createQuery("DELETE FROM Departamentos WHERE dptoNo = :dptoNo");
		hqlDelete.setInteger("dptoNo", id);

		hqlDelete.executeUpdate();

		tx.commit();
		
		System.out.println("Departamento eliminado");

		sesion.close();
	}
	
	public static void modificarDepartamentoQuery(int id, String nombre, String localidad) {

		if (id != -1 && nombre != "" && localidad != "") {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();
			
			Query hqlUpdate = sesion.createQuery("UPDATE Departamentos SET dnombre =:dnombre, loc =:loc WHERE dptoNo =:dptoNo");
			hqlUpdate.setInteger("dptoNo", id);
			hqlUpdate.setString("dnombre", nombre);
			hqlUpdate.setString("loc", localidad);

			hqlUpdate.executeUpdate();
			
			tx.commit();

			System.out.println("Departamento modificado\n");

			sesion.close();
		}
	}
	
	// Hibernate
	public static Departamentos selectDepartamentoHibernate(int id) {

		SessionFactory sesFactory = HibernateUtil.getSessionFactory();

		Session sesion = sesFactory.openSession();

		Departamentos depart = null;

		depart = (Departamentos) sesion.get(Departamentos.class, id);
		if (depart != null) {
			return depart;
		} else {
			return null;
		}
	}
	
	public static String insertarDepartamentoHibernate(int id, String nombre, String localidad) {

		if (id != -1 && nombre != "" && localidad != "") {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();

			Departamentos dep = new Departamentos();

			dep.setDptoNo(id);
			dep.setDnombre(nombre);
			dep.setLoc(localidad);

			try {
				sesion.save(dep);
				tx.commit();

			} catch (ConstraintViolationException cve) {
				return "Problemas al guardar: Registro duplicado\n";
			} catch (Exception e) {
				e.printStackTrace();
				return ("Problemas al guardar: " + e.getMessage() + "\n");
			}
		}
		return "Departamento Insertado";
	}
	
	public static String insertarEmpleadoHibernate(int dptoNo, int empNo, String ape, String oficio, float salario, int dir, String fecha) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session sesion = sessionFactory.openSession();

		Transaction tx = sesion.beginTransaction();

		Departamentos dep = new Departamentos();
		dep.setDptoNo(dptoNo);

		Empleados emp = new Empleados();
		emp.setDepartamentos(dep);
		emp.setEmpNo(empNo);
		emp.setApellido(ape);
		emp.setOficio(oficio);
		emp.setSalario(salario);
		emp.setDir((short) dir);
		emp.setFechaAlta(Date.valueOf(fecha));

		try {
			sesion.save(emp);
			tx.commit();

			return "Empleado insertado correctamente.";

		} catch (ConstraintViolationException cve) {
			return "Problemas al guardar: Registro duplicado";
		} catch (TransientPropertyValueException tpve) {
			return "Problemas al guardar: El departamento indicado no existe";
		} catch (Exception e) {
			e.printStackTrace();
			return "Problemas al guardar: " + e.getMessage();
		}

	}
	
	public static Empleados mostrarEmpleadoHibernate(int empNo) {
		SessionFactory sesFactory = HibernateUtil.getSessionFactory();

		Session sesion = sesFactory.openSession();

		Empleados emp = null;

		emp = (Empleados) sesion.get(Empleados.class, empNo);
		if (emp != null) {
			return emp;
		} else {
			return null;
		}
	}


}
