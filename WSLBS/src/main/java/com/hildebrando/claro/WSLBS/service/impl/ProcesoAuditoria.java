package com.hildebrando.claro.WSLBS.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.auditoria.Auditable;
import com.btg.claro.LBS.domain.Auditoria;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.entidad.Entidad;
import com.hildebrando.claro.WSLBS.dao.AuditoriaDAO;
import com.hildebrando.claro.WSLBS.service.ProcesoAuditoriaService;
import com.hildebrando.claro.WSLBS.util.Constantes;

@Service("ProcesoAuditoriaService")
public class ProcesoAuditoria implements ProcesoAuditoriaService{
	
	@Autowired
	private AuditoriaDAO auditoriaDAO;

	@Transactional
	public void registrarNuevoObjeto(Entidad objeto,Usuario usuario){
		Auditoria auditoria=new Auditoria();
		auditoria.setUsuario(usuario);
		auditoria.setAccion(Constantes.ACCION_CREAR);
		auditoria.setEntidad(objeto.getClass().getSimpleName());
		auditoria.setIdentificador(objeto.getId());
		auditoria.setFechaCreacion(new Date());
		auditoriaDAO.guardar(auditoria);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void registrarModificacionObjeto(Entidad antiguo,Entidad nuevo,Usuario usuario){
		try{
			Class<?> clase=Class.forName(antiguo.getClass().getCanonicalName());
			Field[] campos=clase.getDeclaredFields();
			for(Field campo : campos){
				Annotation a=campo.getAnnotation(Auditable.class);
				if(a!=null){
					String nombre=campo.getName();
					String getter="get"+nombre.substring(0,1).toUpperCase()+nombre.substring(1);
					try{
						Object valorAntiguo=clase.getMethod(getter,new Class<?>[]{}).invoke(antiguo,new Object[]{});
						Object valorNuevo=clase.getMethod(getter,new Class<?>[]{}).invoke(nuevo,new Object[]{});
						//sólo si ha habido un cambio
						if((valorAntiguo==null && valorNuevo!=null) || (valorAntiguo!=null && valorNuevo==null) || (valorAntiguo!=null && valorNuevo!=null && !valorAntiguo.equals(valorNuevo))){
							Auditoria auditoria=new Auditoria();
							auditoria.setUsuario(usuario);
							auditoria.setAccion(Constantes.ACCION_MODIFICAR);
							auditoria.setEntidad(antiguo.getClass().getSimpleName());
							auditoria.setIdentificador(nuevo.getId());
							auditoria.setFechaCreacion(new Date());
							auditoria.setColumna(campo.getName());
							if((valorAntiguo instanceof Entidad) && (valorNuevo instanceof Entidad)){
								auditoria.setValorAntiguo((((Entidad) valorAntiguo).getId()).toString());
								auditoria.setValorNuevo((((Entidad) valorNuevo).getId()).toString());
							}
							else if((valorAntiguo instanceof List) && (valorNuevo instanceof List)){
								String old="[";
								List<Entidad> listaAntigua=(List<Entidad>) valorAntiguo;
								boolean primero=true;
								for(Entidad e : listaAntigua){
									if(!primero){
										old+=","+e.getId();
									}
									else{
										old+=e.getId();
										primero=false;
									}										
								}
								old+="]";
								auditoria.setValorAntiguo(old);
								String nueva="[";
								List<Entidad> listaNueva=(List<Entidad>) valorNuevo;
								primero=true;
								for(Entidad e : listaNueva){
									if(!primero){
										nueva+=","+e.getId();										
									}
									else{
										nueva+=e.getId();
										primero=false;
									}
								}
								nueva+="]";
								auditoria.setValorNuevo(nueva);
							}
							else{
								if(valorAntiguo!=null)
									auditoria.setValorAntiguo(valorAntiguo.toString());
								if(valorNuevo!=null)
								auditoria.setValorNuevo(valorNuevo.toString());
							}
							auditoriaDAO.guardar(auditoria);
						}
					}
					catch(IllegalArgumentException e){
						e.printStackTrace();
					}
					catch(IllegalAccessException e){
						e.printStackTrace();
					}
					catch(InvocationTargetException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch(NoSuchMethodException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		catch(SecurityException e1){
		}
		catch(ClassNotFoundException e1){
		}
	}

	@Transactional
	public void registrarEliminacionObjeto(Entidad objeto,Usuario usuario){
		Auditoria auditoria=new Auditoria();
		auditoria.setUsuario(usuario);
		auditoria.setAccion(Constantes.ACCION_ELIMINAR);
		auditoria.setEntidad(objeto.getClass().getSimpleName());
		auditoria.setIdentificador(objeto.getId());
		auditoria.setFechaCreacion(new Date());
		auditoriaDAO.guardar(auditoria);
	}

}
