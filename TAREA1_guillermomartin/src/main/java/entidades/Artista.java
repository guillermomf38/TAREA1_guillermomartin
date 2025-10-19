package entidades;

import java.util.ArrayList;
import java.util.List;

public class Artista extends Persona {

	private Long idArt;
	private String apodo = null;
	private List<Especialidad> especialidades = new ArrayList<>();
	private List<Numero> numParticipa;

	public Artista() {

	}

	public Artista(Long idArt, String apodo, List<Especialidad> especialidades, List<Numero> numParticipa) {
		super();
		this.idArt = idArt;
		this.apodo = apodo;
		this.especialidades = especialidades;
		this.numParticipa = numParticipa;
	}

	public Long getIdArt() {
		return idArt;
	}

	public void setIdArt(Long idArt) {
		this.idArt = idArt;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Numero> getNumParticipa() {
		return numParticipa;
	}

	public void setNumParticipa(List<Numero> numParticipa) {
		this.numParticipa = numParticipa;
	}

	@Override
	public String toString() {
		return "Artista [idArt=" + idArt + ", apodo=" + apodo + ", especialidades=" + especialidades + ", numParticipa="
				+ numParticipa + "]";
	}

}
