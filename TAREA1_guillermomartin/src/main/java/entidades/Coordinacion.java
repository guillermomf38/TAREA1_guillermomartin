package entidades;

import java.time.LocalDate;

import java.util.HashSet;

import java.util.Set;

public class Coordinacion extends Persona {
	private Long idCoord;
	private boolean senior = false;
	private LocalDate fechasenior = null;
	private Set<Espectaculo> espectaculosdirige = new HashSet<>();

	public Coordinacion() {

	}

	public Coordinacion(Long idCoord, boolean senior, LocalDate fechasenior, Set<Espectaculo> espectaculosdirige) {
		super();
		this.idCoord = idCoord;
		this.senior = senior;
		this.fechasenior = fechasenior;
		this.espectaculosdirige = espectaculosdirige;
	}

	public Long getIdCoord() {
		return idCoord;
	}

	public void setIdCoord(Long idCoord) {
		this.idCoord = idCoord;
	}

	public boolean isSenior() {
		return senior;
	}

	public void setSenior(boolean senior) {
		this.senior = senior;
	}

	public LocalDate getFechasenior() {
		return fechasenior;
	}

	public void setFechasenior(LocalDate fechasenior) {
		this.fechasenior = fechasenior;
	}

	public Set<Espectaculo> getEspectaculosdirige() {
		return espectaculosdirige;
	}

	public void setEspectaculosdirige(Set<Espectaculo> espectaculosdirige) {
		this.espectaculosdirige = espectaculosdirige;
	}

	@Override
	public String toString() {
		return "Coordinacion [idCoord=" + idCoord + ", senior=" + senior + ", fechasenior=" + fechasenior
				+ ", espectaculosdirige=" + espectaculosdirige + "]";
	}

}
