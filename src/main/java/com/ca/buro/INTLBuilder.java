package com.ca.buro;

public class INTLBuilder {

	public static Transaccion buildTransaccionINTL11SinAutenticacion() {
		Transaccion transaccion = new Transaccion();
		transaccion.setSegmentos(buildSegmentosINTL11SinAutenticacion());
		//transaccion.setSegmentos(buildTest());
		transaccion.setNombre("INTL11SinAutenticacion");
		return transaccion;
	}
	
	private static Segmento[] buildTest() {
		Segmento[] segmentos = new Segmento[1];
		segmentos[0] = buildSegmentoTest();
		return segmentos;
	}
	
	private static Segmento buildSegmentoTest() {
		Segmento segmento = new Segmento();
		segmento.setIdentificador("seccionINTL");
		Campo[] campos = new Campo[2];
		
		campos[0] = new Campo();
		campos[0].setNombre("Etiqueta");
		campos[0].setLongitud(4);
		
		campos[1] = new Campo();
		campos[1].setNombre("Version");
		campos[1].setLongitud(2);
		segmento.setCampos(campos);
		
		return segmento;
	}
	
	private static Segmento[] buildSegmentosINTL11SinAutenticacion() {
		Segmento[] segmentos = new Segmento[2];
		segmentos[0] = buildSegmentoINTL();
		segmentos[1] = buildSegmentoPN();
		return segmentos;
	}
	
	
	private static Segmento buildSegmentoINTL() {
		Segmento segmento = new Segmento();
		segmento.setIdentificador("SeccionINTL");
		Campo[] campos = new Campo[17];
		
		campos[0] = new Campo();
		campos[0].setNombre("CampoEtiqueta");
		campos[0].setLongitud(4);
		
		campos[1] = new Campo();
		campos[1].setNombre("CampoVersion");
		campos[1].setLongitud(2);
		
		campos[2] = new Campo();
		campos[2].setNombre("CampoReferenciaOperador");
		campos[2].setLongitud(25);
		
		campos[3] = new Campo();
		campos[3].setNombre("CampoProductoRequerido");
		campos[3].setLongitud(3);
		
		campos[4] = new Campo();
		campos[4].setNombre("CampoClavePais");
		campos[4].setLongitud(2);
		
		campos[5] = new Campo();
		campos[5].setNombre("CampoReservado");
		campos[5].setLongitud(4);
		
		campos[6] = new Campo();
		campos[6].setNombre("CampoUsuario");
		campos[6].setLongitud(10);
		
		campos[7] = new Campo();
		campos[7].setNombre("CampoContrasena");
		campos[7].setLongitud(8);
		
		campos[8] = new Campo();
		campos[8].setNombre("CampoTipoResponsabilidad");
		campos[8].setLongitud(1);
		
		campos[9] = new Campo();
		campos[9].setNombre("CampoTipoContrato");
		campos[9].setLongitud(2);
		
		campos[10] = new Campo();
		campos[10].setNombre("CampoMoneda");
		campos[10].setLongitud(2);
		
		campos[11] = new Campo();
		campos[11].setNombre("CampoImporte");
		campos[11].setLongitud(9);
		
		campos[12] = new Campo();
		campos[12].setNombre("CampoIdioma");
		campos[12].setLongitud(2);
		
		campos[13] = new Campo();
		campos[13].setNombre("CampoTipoSalida");
		campos[13].setLongitud(2);
		
		campos[14] = new Campo();
		campos[14].setNombre("CampoTamanoDelBloque");
		campos[14].setLongitud(1);
		
		campos[15] = new Campo();
		campos[15].setNombre("CampoIdentificacionImpresora");
		campos[15].setLongitud(4);
		
		campos[16] = new Campo();
		campos[16].setNombre("CampoReservadoFuturo");
		campos[16].setLongitud(7);
		segmento.setCampos(campos);
		
		return segmento;
	}
	
	private static Segmento buildSegmentoPN() {
		Segmento segmento = new Segmento();
		segmento.setIncluyeEtiquetas(true);
		segmento.setIdentificador("SeccionPN");
		Campo[] campos = new Campo[2];
		
		campos[0] = new Campo();
		campos[0].setNombre("CampoPN");
		campos[0].setLongitud(26);
		
		campos[1] = new Campo();
		campos[1].setNombre("Campo00");
		campos[1].setLongitud(26);
		
//		campos[2] = new Campo();
//		campos[2].setNombre("Campo01");
//		campos[2].setLongitud(26);
//		
//		campos[3] = new Campo();
//		campos[3].setNombre("Campo02");
//		campos[3].setLongitud(26);
		
		segmento.setCampos(campos);
		return segmento;
	}
}
