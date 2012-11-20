package com.btg.claro.LBS.aprovisionamiento.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.aprovisionamiento.dao.ConsultaDAO;
import com.btg.claro.LBS.aprovisionamiento.dao.EmpresaDAO;
import com.btg.claro.LBS.aprovisionamiento.dao.UsuarioDAO;
import com.btg.claro.LBS.aprovisionamiento.service.ConsultasEmpresaService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.aprovisionamiento.util.Util;
import com.btg.claro.LBS.domain.Consulta;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.claro.LBS.domain.Empresa;

@Service("ConsultasEmpresaService")
public class ConsultasEmpresa implements ConsultasEmpresaService{
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ConsultaDAO consultaDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;

	public List<Map<String,Object>> buscarUsuario(String query){
		if(!Util.vacio(query)){
			query="%"+query.toLowerCase()+"%";
			List<Usuario> usuarios=usuarioDAO.buscarUsuarios(query);
			if(usuarios.size()>0){
				List<Map<String,Object>> lista=new ArrayList<Map<String,Object>>();
				for(Usuario usuario : usuarios){
					Map<String,Object> datos=new HashMap<String,Object>();
					datos.put("id",usuario.getId());
					datos.put("label",usuario.getNumero()+" - "+usuario.getNombres()+" "+usuario.getApellidos());
					datos.put("value",usuario.getNumero()+" - "+usuario.getNombres()+" "+usuario.getApellidos());
					lista.add(datos);
				}
				return lista;
			}
		}
		return null;
	}
	
	public List<Map<String,Object>> buscarEmpresas(String query, Usuario usuario){
		if(!Util.vacio(query)){
			query="%"+query.toLowerCase()+"%";
			List<Empresa> empresas=empresaDAO.buscarEmpresas(query,usuario);
			if(empresas.size()>0){
				List<Map<String,Object>> lista=new ArrayList<Map<String,Object>>();
				for(Empresa empresa : empresas){
					Map<String,Object> datos=new HashMap<String,Object>();
					datos.put("id",empresa.getId());
					datos.put("label",empresa.getRuc()+" - "+empresa.getRazonSocial());
					datos.put("value",empresa.getRuc()+" - "+empresa.getRazonSocial());
					lista.add(datos);
				}
				return lista;
			}
		}
		return null;
	}
	

	public Map<String,Object> reporteUsuarioConsultado(int usuario,Date fechaInicio,Date fechaFin){
		Calendar fin=Calendar.getInstance();
		fin.setTime(fechaFin);
		fin.set(Calendar.HOUR_OF_DAY,23);
		fin.set(Calendar.MINUTE,59);
		fin.set(Calendar.SECOND,59);
		List<Consulta> consultasTotales=consultaDAO.getReportePorUsuarioConsultado(usuario,fechaInicio,fin.getTime());
		int total=consultasTotales.size();
		Map<String,Object> datos=new HashMap<String,Object>();
		datos.put("consultasTotales",total);
		if(total>0){			
			List<Consulta> sms=new ArrayList<Consulta>();
			List<Consulta> web=new ArrayList<Consulta>();
			int exitoSms=0;
			int exitoWeb=0;
			for(Consulta c : consultasTotales){
				if(c.getTipo().equals(Constantes.TIPO_SMS)){
					sms.add(c);
					if(c.getCodigoError()==Constantes.OK){
						exitoSms++;
					}
				}
				else if(c.getTipo().equals(Constantes.TIPO_WEB)){
					web.add(c);
					if(c.getCodigoError()==Constantes.OK){
						exitoWeb++;
					}
				}
			}
			int totalSms=sms.size();
			if(totalSms>0){
				datos.put("consultasSMS",sms);
				datos.put("totalSMS",totalSms);
				datos.put("porcentajeSMS",(100*(double)totalSms/total));
				datos.put("exitoSMS",exitoSms);
				datos.put("porcentajeExitoSMS",(100*(double)exitoSms/totalSms));
			}
			int totalWeb=web.size();
			if(totalWeb>0){
				datos.put("consultasWEB",web);
				datos.put("totalWEB",totalWeb);
				datos.put("porcentajeWEB",(100*(double)totalWeb/total));
				datos.put("exitoWEB",exitoWeb);
				datos.put("porcentajeExitoWEB",(100*(double)exitoWeb/totalWeb));
			}
		}
		return datos;		
	}

	public Map<String,Object> reporteUsuarioConsultante(int usuario,Date fechaInicio,Date fechaFin){
		Calendar fin=Calendar.getInstance();
		fin.setTime(fechaFin);
		fin.set(Calendar.HOUR_OF_DAY,23);
		fin.set(Calendar.MINUTE,59);
		fin.set(Calendar.SECOND,59);
		List<Consulta> consultasTotales=consultaDAO.getReportePorUsuarioConsultante(usuario,fechaInicio,fin.getTime());
		int total=consultasTotales.size();
		Map<String,Object> datos=new HashMap<String,Object>();
		datos.put("consultasTotales",total);
		if(total>0){			
			List<Consulta> sms=new ArrayList<Consulta>();
			List<Consulta> web=new ArrayList<Consulta>();
			int exitoSms=0;
			int exitoWeb=0;
			for(Consulta c : consultasTotales){
				if(c.getTipo().equals(Constantes.TIPO_SMS)){
					sms.add(c);
					if(c.getCodigoError()==Constantes.OK){
						exitoSms++;
					}
				}
				else if(c.getTipo().equals(Constantes.TIPO_WEB)){
					web.add(c);
					if(c.getCodigoError()==Constantes.OK){
						exitoWeb++;
					}
				}
			}
			int totalSms=sms.size();
			if(totalSms>0){
				datos.put("consultasSMS",sms);
				datos.put("totalSMS",totalSms);
				datos.put("porcentajeSMS",(100*(double)totalSms/total));
				datos.put("exitoSMS",exitoSms);
				datos.put("porcentajeExitoSMS",(100*(double)exitoSms/totalSms));
			}
			int totalWeb=web.size();
			if(totalWeb>0){
				datos.put("consultasWEB",web);
				datos.put("totalWEB",totalWeb);
				datos.put("porcentajeWEB",(100*(double)totalWeb/total));
				datos.put("exitoWEB",exitoWeb);
				datos.put("porcentajeExitoWEB",(100*(double)exitoWeb/totalWeb));
			}
		}
		return datos;
	}

	public Map<String, Object> reporteFechaConsultada(Date fechaInicio,
			Date fechaFin) {
		
		Calendar fin=Calendar.getInstance();
		fin.setTime(fechaFin);
		fin.set(Calendar.HOUR_OF_DAY,23);
		fin.set(Calendar.MINUTE,59);
		fin.set(Calendar.SECOND,59);
		List<Consulta> consultasTotales=consultaDAO.getReportePorFechaConsultada(fechaInicio, fin.getTime());
		int total=consultasTotales.size();
		Map<String,Object> datos=new HashMap<String,Object>();
		datos.put("consultasTotales",total);
		if(total>0){			
			List<Consulta> sms=new ArrayList<Consulta>();
			List<Consulta> web=new ArrayList<Consulta>();
			int exitoSms=0;
			int exitoWeb=0;
			for(Consulta c : consultasTotales){
				if(c.getTipo().equals(Constantes.TIPO_SMS)){
					sms.add(c);
					if(c.getCodigoError()==Constantes.OK){
						exitoSms++;
					}
				}
				else if(c.getTipo().equals(Constantes.TIPO_WEB)){
					web.add(c);
					if(c.getCodigoError()==Constantes.OK){
						exitoWeb++;
					}
				}
			}
			int totalSms=sms.size();
			if(totalSms>0){
				datos.put("consultasSMS",sms);
				datos.put("totalSMS",totalSms);
				datos.put("porcentajeSMS",(100*(double)totalSms/total));
				datos.put("exitoSMS",exitoSms);
				datos.put("porcentajeExitoSMS",(100*(double)exitoSms/totalSms));
			}
			int totalWeb=web.size();
			if(totalWeb>0){
				datos.put("consultasWEB",web);
				datos.put("totalWEB",totalWeb);
				datos.put("porcentajeWEB",(100*(double)totalWeb/total));
				datos.put("exitoWEB",exitoWeb);
				datos.put("porcentajeExitoWEB",(100*(double)exitoWeb/totalWeb));
			}
		}
		return datos;

	}

}
