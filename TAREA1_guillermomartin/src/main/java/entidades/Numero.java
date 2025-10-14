package entidades;

public class Numero {
	private Long id;
	private int orden;
	private String nombre;
	private double duracion;
	private Long idEspectaculo;

	public Numero() {

	}

	public Numero(Long id, int orden, String nombre, double duracion, Long idEspectaculo) {
		super();
		this.id = id;
		this.orden = orden;
		this.nombre = nombre;
		this.duracion = duracion;
		this.idEspectaculo = idEspectaculo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public Long getIdEspectaculo() {
		return idEspectaculo;
	}

	public void setIdEspectaculo(Long idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}

	@Override
	public String toString() {
		return "Numero [id=" + id + ", orden=" + orden + ", nombre=" + nombre + ", duracion=" + duracion
				+ ", idEspectaculo=" + idEspectaculo + "]";
	}

}
