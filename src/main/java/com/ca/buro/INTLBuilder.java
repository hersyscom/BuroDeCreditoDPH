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
		campos[0].setNombre("Campo-Etiqueta");
		campos[0].setLongitud(4);
		
		campos[1] = new Campo();
		campos[1].setNombre("Campo-Version");
		campos[1].setLongitud(2);
		
		campos[2] = new Campo();
		campos[2].setNombre("Campo-ReferenciaOperador");
		campos[2].setLongitud(25);
		
		campos[3] = new Campo();
		campos[3].setNombre("Campo-ProductoRequerido");
		campos[3].setLongitud(3);
		
		campos[4] = new Campo();
		campos[4].setNombre("Campo-ClavePais");
		campos[4].setLongitud(2);
		
		campos[5] = new Campo();
		campos[5].setNombre("Campo-Reservado");
		campos[5].setLongitud(4);
		
		campos[6] = new Campo();
		campos[6].setNombre("Campo-Usuario");
		campos[6].setLongitud(10);
		
		campos[7] = new Campo();
		campos[7].setNombre("Campo-Contrasena");
		campos[7].setLongitud(8);
		
		campos[8] = new Campo();
		campos[8].setNombre("Campo-TipoResponsabilidad");
		campos[8].setLongitud(1);
		
		campos[9] = new Campo();
		campos[9].setNombre("Campo-TipoContrato");
		campos[9].setLongitud(2);
		
		campos[10] = new Campo();
		campos[10].setNombre("Campo-Moneda");
		campos[10].setLongitud(2);
		
		campos[11] = new Campo();
		campos[11].setNombre("Campo-Importe");
		campos[11].setLongitud(9);
		
		campos[12] = new Campo();
		campos[12].setNombre("Campo-Idioma");
		campos[12].setLongitud(2);
		
		campos[13] = new Campo();
		campos[13].setNombre("Campo-TipoSalida");
		campos[13].setLongitud(2);
		
		campos[14] = new Campo();
		campos[14].setNombre("Campo-TamanoDelBloque");
		campos[14].setLongitud(1);
		
		campos[15] = new Campo();
		campos[15].setNombre("Campo-IdentificacionImpresora");
		campos[15].setLongitud(4);
		
		campos[16] = new Campo();
		campos[16].setNombre("Campo-ReservadoFuturo");
		campos[16].setLongitud(7);
		segmento.setCampos(campos);
		
		return segmento;
	}
	
	private static Segmento buildSegmentoPN() {
		Segmento segmento = new Segmento();
		segmento.setIncluyeEtiquetas(true);
		segmento.setIdentificador("SeccionPN");
		Campo[] campos = new Campo[4];
		
		campos[0] = new Campo();
		campos[0].setNombre("Campo-PN");
		campos[0].setLongitud(26);
		
		campos[1] = new Campo();
		campos[1].setNombre("Campo-00");
		campos[1].setLongitud(26);
		
		campos[2] = new Campo();
		campos[2].setNombre("Campo-01");
		campos[2].setLongitud(26);
		
		campos[3] = new Campo();
		campos[3].setNombre("Campo-02");
		campos[3].setLongitud(26);
		
		segmento.setCampos(campos);
		return segmento;
	}
}
