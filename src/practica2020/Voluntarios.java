package practica2020;
// Generated 10-dic-2020 12:37:10 by Hibernate Tools 5.4.21.Final

/**
 * Voluntarios generated by hbm2java
 */
public class Voluntarios implements java.io.Serializable {

	private VoluntariosId id;
	private Vacuna vacuna;
	private String nya;
	private Byte edad;
	private String grupo;
	private String pais;

	public Voluntarios() {
	}

	public Voluntarios(VoluntariosId id, Vacuna vacuna) {
		this.id = id;
		this.vacuna = vacuna;
	}

	public Voluntarios(VoluntariosId id, Vacuna vacuna, String nya, Byte edad, String grupo, String pais) {
		this.id = id;
		this.vacuna = vacuna;
		this.nya = nya;
		this.edad = edad;
		this.grupo = grupo;
		this.pais = pais;
	}

	public VoluntariosId getId() {
		return this.id;
	}

	public void setId(VoluntariosId id) {
		this.id = id;
	}

	public Vacuna getVacuna() {
		return this.vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

	public String getNya() {
		return this.nya;
	}

	public void setNya(String nya) {
		this.nya = nya;
	}

	public Byte getEdad() {
		return this.edad;
	}

	public void setEdad(Byte edad) {
		this.edad = edad;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
