package com.ca.buro;

public class Segmento {
	private String identificador;
	private boolean incluyeEtiquetas;
	private Campo[] campos;

	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public boolean isIncluyeEtiquetas() {
		return incluyeEtiquetas;
	}
	public void setIncluyeEtiquetas(boolean incluyeEtiquetas) {
		this.incluyeEtiquetas = incluyeEtiquetas;
	}
	public void setCampos(Campo[] campos) {
		this.campos = campos;
	}
	public Campo[] getCampos() {
		return campos;
	}
}