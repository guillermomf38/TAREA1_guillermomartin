package entidades;

public  class Persona {
	protected Long id;
	protected String email;
	protected String nombre;
	protected String nacionalidad;
	protected Long idCredencial;

	public Persona() {

	}

	public Persona(Long id, String email, String nombre, String nacionalidad, Long idCredencial) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.idCredencial = idCredencial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Long getIdCredencial() {
		return idCredencial;
	}

	public void setIdCredencial(Long idCredencial) {
		this.idCredencial = idCredencial;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", email=" + email + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad
				+ ", idCredencial=" + idCredencial + "]";
	}

}
