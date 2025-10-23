package entidades;

public class Sesion {
	private String usuarioActual;
	private Perfiles perfil;

	public Sesion() {
	}

	public Sesion(String usuarioActual, Perfiles perfil) {
		super();
		this.usuarioActual = usuarioActual;
		this.perfil = perfil;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public Perfiles getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfiles perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Sesion [usuarioActual=" + usuarioActual + ", perfil=" + perfil
				+ "]";
	}
	

}
