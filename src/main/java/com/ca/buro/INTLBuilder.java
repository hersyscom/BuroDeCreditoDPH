package com.ca.buro;

import com.ca.buro.layout.Field;
import com.ca.buro.layout.Section;
import com.ca.buro.layout.Template;

public class INTLBuilder {

	public static Template buildRequestINTL11SinAutenticacion() {
		Template template = new Template();
		template.setSections(buildRequestSegmentosINTL11SinAutenticacion());
		template.setName("INTL11SinAutenticacion");
		return template;
	}
	
	public static Template buildResponseINTL11SinAutenticacion() {
		Template template = new Template();
		template.setSections(buildResponseSegmentosINTL11SinAutenticacion());
		template.setName("INTL11SinAutenticacion");
		return template;
	}
	
	private static Section[] buildResponseSegmentosINTL11SinAutenticacion() {
		Section[] sections = new Section[1];
		sections[0] = buildSegmentoTest();
		return sections;
	}

	
	private static Section buildSegmentoTest() {
		Section section = new Section();
		section.setId("seccionINTL");
		Field[] fields = new Field[2];
		
		fields[0] = new Field();
		fields[0].setName("Etiqueta");
		fields[0].setLength(4);
		
		fields[1] = new Field();
		fields[1].setName("Version");
		fields[1].setLength(2);
		section.setFields(fields);
		
		return section;
	}
	
	private static Section[] buildRequestSegmentosINTL11SinAutenticacion() {
		Section[] sections = new Section[2];
		sections[0] = buildRequestSegmentoINTL();
		sections[1] = buildRequestSegmentoPN();
		return sections;
	}
	
	
	private static Section buildRequestSegmentoINTL() {
		Section section = new Section();
		section.setId("SeccionINTL");
		Field[] fields = new Field[17];
		
		fields[0] = new Field();
		fields[0].setName("Campo-Etiqueta");
		fields[0].setLength(4);
		
		fields[1] = new Field();
		fields[1].setName("Campo-Version");
		fields[1].setLength(2);
		
		fields[2] = new Field();
		fields[2].setName("Campo-ReferenciaOperador");
		fields[2].setLength(25);
		
		fields[3] = new Field();
		fields[3].setName("Campo-ProductoRequerido");
		fields[3].setLength(3);
		
		fields[4] = new Field();
		fields[4].setName("Campo-ClavePais");
		fields[4].setLength(2);
		
		fields[5] = new Field();
		fields[5].setName("Campo-Reservado");
		fields[5].setLength(4);
		
		fields[6] = new Field();
		fields[6].setName("Campo-Usuario");
		fields[6].setLength(10);
		
		fields[7] = new Field();
		fields[7].setName("Campo-Contrasena");
		fields[7].setLength(8);
		
		fields[8] = new Field();
		fields[8].setName("Campo-TipoResponsabilidad");
		fields[8].setLength(1);
		
		fields[9] = new Field();
		fields[9].setName("Campo-TipoContrato");
		fields[9].setLength(2);
		
		fields[10] = new Field();
		fields[10].setName("Campo-Moneda");
		fields[10].setLength(2);
		
		fields[11] = new Field();
		fields[11].setName("Campo-Importe");
		fields[11].setLength(9);
		
		fields[12] = new Field();
		fields[12].setName("Campo-Idioma");
		fields[12].setLength(2);
		
		fields[13] = new Field();
		fields[13].setName("Campo-TipoSalida");
		fields[13].setLength(2);
		
		fields[14] = new Field();
		fields[14].setName("Campo-TamanoDelBloque");
		fields[14].setLength(1);
		
		fields[15] = new Field();
		fields[15].setName("Campo-IdentificacionImpresora");
		fields[15].setLength(4);
		
		fields[16] = new Field();
		fields[16].setName("Campo-ReservadoFuturo");
		fields[16].setLength(7);
		section.setFields(fields);
		
		return section;
	}
	
	private static Section buildRequestSegmentoPN() {
		Section section = new Section();
		section.setLabelIncluded(true);
		section.setId("SeccionPN");
		Field[] fields = new Field[4];
		
		fields[0] = new Field();
		fields[0].setName("Campo-PN");
		fields[0].setLength(26);
		
		fields[1] = new Field();
		fields[1].setName("Campo-00");
		fields[1].setLength(26);
		
		fields[2] = new Field();
		fields[2].setName("Campo-01");
		fields[2].setLength(26);
		
		fields[3] = new Field();
		fields[3].setName("Campo-02");
		fields[3].setLength(26);
		
		section.setFields(fields);
		return section;
	}
}
