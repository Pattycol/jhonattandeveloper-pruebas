package com.btg.claro.LBS.vlr;

public interface ClienteVLR{
	
	/**
	 * Proceso que efectúa la ubicación de un movil. En primer lugar
	 * consulta a la base de datos en qué HLR se encuentra el número
	 * ingresado; luego consulta al HLR mencionado, devolviendo éste
	 * el VLR donde se encuentra el número; finalmente se consulta al
	 * VLR seleccionado a fin de determinar la celda asociada al movil
	 * buscado.
	 * 
	 * @param numero el número del movil a ubicar
	 * @return los datos de la ubicación del movil o <code>null</code> si no ha sido encontrado
	 */
	DatosMovil ubicarMovil(String numero);

}
