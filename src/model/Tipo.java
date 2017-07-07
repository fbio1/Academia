package model;

public enum Tipo {
	DORSAL, PEITO, BICEPS, TRICEPS, PERNA;
	
	private String tipos;

	public String getTipos() {
		return tipos;
	}

	public void setTipos(String tipos) {
		this.tipos = tipos;
	}
}
