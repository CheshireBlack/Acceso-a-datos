package tools;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import modelo.Equipo;
import modelo.Jugador;


public class Functions {
	public static void main(String[] args) {
		//System.out.println(deleteEquipo("Bulls"));
		//System.out.println(insertEquipo("Bulls", "Chicago", "East","Atlántico"));
		//modificarEquipo("Bulls", "Chicago", "West", "Atlántico");
		System.out.println(selectEquipo("Bulls"));
		System.exit(0);
	}
	
	public static Equipo selectEquipo(String nombre) {

		SessionFactory sesFactory = HibernateUtils.getSessionFactory();

		Session sesion = sesFactory.openSession();

		Query hqlSelect = sesion.createQuery("from Equipo where nombre=:nombre");
		hqlSelect.setString("nombre", nombre);
		
		Equipo e =  (Equipo) hqlSelect.uniqueResult();
		
		sesion.close();
		return e;
	}
	
	public static boolean insertEquipo(String nombre, String ciudad, String conferencia, String division){

		if (nombre != "" && ciudad != "" && conferencia != "" && division != "") {
			SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();

			Equipo e = new Equipo();

			e.setNombre(nombre);
			e.setCiudad(ciudad);
			e.setConferencia(conferencia);
			e.setDivision(division);

			try {
				sesion.save(e);
				tx.commit();

				return true;

			} catch (ConstraintViolationException cve) {
				System.out.println("Problemas al guardar: Registro duplicado\n");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Problemas al guardar: " + ex.getMessage() + "\n");
			}

			sesion.close();
		}
		return false;
	}
	
	public static boolean modificarEquipo(String nombre, String ciudad, String conferencia, String division) {

		if (nombre != "" && ciudad != "" && conferencia != "" && division != "") {
			SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();
			
			Query hqlUpdate = sesion.createQuery("UPDATE Equipo SET nombre =:nombre, ciudad =:ciudad, conferencia =:conferencia"
					+ ", division =:division WHERE nombre =:nombre");
			hqlUpdate.setString("nombre", nombre);
			hqlUpdate.setString("ciudad", ciudad);
			hqlUpdate.setString("conferencia", conferencia);
			hqlUpdate.setString("division", division);

			hqlUpdate.executeUpdate();
			
			tx.commit();
			
			sesion.close();
			return true;
		}
		return false;
	}
	
	public static boolean deleteEquipo(String nombre) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		Session sesion = sessionFactory.openSession();
		
		Equipo e = (Equipo) sesion.get(Equipo.class, nombre);

		if (e != null) {
			Transaction tx = sesion.beginTransaction();

			sesion.delete(e);
			
			tx.commit();
			sesion.close();
			
			return true;
		}
		sesion.close();
		return false;
	}
	
	public static Jugador selectJugador(int id) {
		SessionFactory sesFactory = HibernateUtils.getSessionFactory();

		Session sesion = sesFactory.openSession();

		Jugador j = null;

		j = (Jugador) sesion.get(Jugador.class, id);
		
		return j;
	}
	
	public static boolean insertJugador(String nombre, String procedencia, String altura, int peso, String posicion, String equipo){

		if (nombre != "" && procedencia != "" && altura != "" && posicion != "" && equipo != "") {
			SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();

			Jugador j = new Jugador();
			
			Equipo e = selectEquipo(nombre);

			j.setNombre(nombre);
			j.setProcedencia(procedencia);
			j.setAltura(altura);
			j.setPeso(peso);
			j.setPosicion(posicion);
			j.setEquipo(e);

			try {
				sesion.save(e);
				tx.commit();

				return true;

			} catch (ConstraintViolationException cve) {
				System.out.println("Problemas al guardar: Registro duplicado\n");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Problemas al guardar: " + ex.getMessage() + "\n");
			}

			sesion.close();
		}
		return false;
	}
	
	public static boolean modificarJugador(int id, String nombre, String procedencia, String altura, int peso, String posicion, Equipo equipo) {

		Equipo e = selectEquipo(nombre);
		
		if (id != -1 && nombre != "" && procedencia != "" && altura != "" && peso != -1 && posicion != "" && e != null ) {
			SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

			Session sesion = sessionFactory.openSession();

			Transaction tx = sesion.beginTransaction();
			
			Query hqlUpdate = sesion.createQuery("UPDATE Jugador SET nombre =:nombre, procedencia =:procedencia, altura =:altura, peso =:peso, posicion :=posicion, equipo :=equipo WHERE id =:id");
			hqlUpdate.setString("nombre", nombre);
			hqlUpdate.setString("procedencia", procedencia);
			hqlUpdate.setString("altura", altura);
			hqlUpdate.setInteger("peso", peso);
			hqlUpdate.setString("posicion", posicion);
			//hqlUpdate.setEquipo(peso, e);
			

			hqlUpdate.executeUpdate();
			
			tx.commit();
			
			sesion.close();
			return true;
		}
		return false;
	}
	
	public static boolean deleteJugador(int id) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		Session sesion = sessionFactory.openSession();
		Jugador j = selectJugador(id);

		if (j != null) {
			Transaction tx = sesion.beginTransaction();

			Query hqlDelete = sesion.createQuery("DELETE FROM Jugador WHERE id = :id");
			hqlDelete.setInteger("id", id);

			hqlDelete.executeUpdate();

			tx.commit();
			sesion.close();
			
			return true;
		}
		sesion.close();
		return false;
	}
}
