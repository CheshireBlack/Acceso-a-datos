package tools;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

import modelo.Departamentos;
import modelo.Empleados;

public class insertarEmpleado {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session sesion = sessionFactory.openSession();
		
		Transaction tx = sesion.beginTransaction();
		System.out.println("Vamos a insertar un registro Empleados");
		
		Departamentos dep = new Departamentos();
		dep.setDptoNo(35);
		
		Empleados emp = new Empleados();
		emp.setDepartamentos(dep);
		emp.setEmpNo(21);
		emp.setApellido("Rubio");
		emp.setDir((short) 1);
		emp.setFechaAlta(Date.valueOf(LocalDate.now()));
		
		try {
			sesion.save(emp);
			tx.commit();
			
			System.out.println("Empleado insertado correctamente.");
			
		} catch(ConstraintViolationException cve) {
			System.out.println("Problemas al guardar: Registro duplicado");
		} catch(TransientPropertyValueException tpve) {
			System.out.println("Problemas al guardar: El departamento indicado no existe");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Problemas al guardar: Otros");
		}
		
		

	}

}
