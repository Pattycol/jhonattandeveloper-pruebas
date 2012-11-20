package com.hildebrando.claro.WSLBS.service;

import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.entidad.Entidad;

public interface ProcesoAuditoriaService{
	
	/**
	 * Método para registrar la creación de una nueva entidad en la base de datos.
	 * Este método debe ser llamado luego de haber registrado el objeto
	 * 
	 * @param objeto el objeto que está siendo creado
	 * @param usuario el usuario que crea el objeto
	 */
	void registrarNuevoObjeto(Entidad objeto,Usuario usuario);
	
	/**
	 * Método que registra la modificación de un objeto. Se almacena el cambio de valor
	 * de las columnas correspondientes marcadas como <code>@Auditable</code>.
	 * Este método debe ser llamado antes de guardar el objeto.
	 * 
	 * @param antiguo el objeto que está siendo modificado
	 * @param el nuevo objeto
	 * @param usuario el usuario que modifica el objeto
	 */
	void registrarModificacionObjeto(Entidad antiguo,Entidad nuevo,Usuario usuario);
	
	/**
	 * Método que registra la eliminación de un objeto en la base de datos. La eliminación
	 * consiste en el cambio de estado a ELIMINADO. 
	 * Este método debe ser llamado después de eliminar el objeto
	 * 
	 * @param objeto el objeto a eliminar
	 * @param usuario el usuario que elimina el objeto
	 */
	void registrarEliminacionObjeto(Entidad objeto,Usuario usuario);
}
