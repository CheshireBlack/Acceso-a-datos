package modelo;
// Generated 8 feb 2023 19:51:55 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Equipo generated by hbm2java
 */
public class Equipo implements java.io.Serializable {

	private String nombre;
	private String ciudad;
	private String conferencia;
	private String division;
	private Set<Jugador> jugadors = new HashSet<Jugador>(0);
	private Set<Partidos> partidosesForEquipoVisitante = new HashSet<Partidos>(0);
	private Set<Partidos> partidosesForEquipoLocal = new HashSet<Partidos>(0);

	public Equipo() {
	}

	public Equipo(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
	}

	public Equipo(String nombre, String ciudad, String conferencia, String division, Set<Jugador> jugadors,
			Set<Partidos> partidosesForEquipoVisitante, Set<Partidos> partidosesForEquipoLocal) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.conferencia = conferencia;
		this.division = division;
		this.jugadors = jugadors;
		this.partidosesForEquipoVisitante = partidosesForEquipoVisitante;
		this.partidosesForEquipoLocal = partidosesForEquipoLocal;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getConferencia() {
		return this.conferencia;
	}

	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}

	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Set<Jugador> getJugadors() {
		return this.jugadors;
	}

	public void setJugadors(Set<Jugador> jugadors) {
		this.jugadors = jugadors;
	}

	public Set<Partidos> getPartidosesForEquipoVisitante() {
		return this.partidosesForEquipoVisitante;
	}

	public void setPartidosesForEquipoVisitante(Set<Partidos> partidosesForEquipoVisitante) {
		this.partidosesForEquipoVisitante = partidosesForEquipoVisitante;
	}

	public Set<Partidos> getPartidosesForEquipoLocal() {
		return this.partidosesForEquipoLocal;
	}

	public void setPartidosesForEquipoLocal(Set<Partidos> partidosesForEquipoLocal) {
		this.partidosesForEquipoLocal = partidosesForEquipoLocal;
	}

	@Override
	public String toString() {
		return "\nNombre: " + nombre + "\nCiudad: " + ciudad + "\nConferencia: " + 
				conferencia + "\nDivision: " + division + "\nJugadores: ";
	}

}
