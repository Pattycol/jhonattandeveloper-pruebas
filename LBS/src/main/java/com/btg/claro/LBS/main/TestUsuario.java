package com.btg.claro.LBS.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.btg.claro.LBS.dao.UsuarioDAO;
import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Rol;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.claro.LBS.util.Constantes;

public class TestUsuario{

	/**
	 * @param args
	 */
	public static void main(String[] args){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("/META-INF/spring/usuario.xml");
		UsuarioDAO usuarioDAO=ctx.getBean(UsuarioDAO.class);
		while(true){
			Usuario consultante=usuarioDAO.buscarPorNumero("997101273");
			Usuario consultado=usuarioDAO.buscarPorNumero("997106787");
			validarConsulta(consultante,consultado);
			System.out.println(consultante);
			System.out.println("\t"+consultante.getAreas());
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static int validarConsulta(Usuario origen,Usuario destino){
		if(!verificarJerarquia(origen,destino))
			return Constantes.ERROR_JERARQUIA;
		/*if(!verificarConsultasDisponibles(origen))
			return Constantes.ERROR_CONSULTAS_AGOTADAS;*/
		return Constantes.OK;
	}
	
	private static boolean verificarJerarquia(Usuario origen,Usuario destino){
		// Se verifica que al menos tengan un área en común que pertenezca a la
		// misma empresa
		List<Area> areasOrigen=origen.getAreas();
		List<Area> areasDestino=destino.getAreas();
		boolean mismaEmpresa=false;
		for(Area o : areasOrigen){
			for(Area d : areasDestino){
				if(o.getEmpresa().equals(d.getEmpresa())){
					mismaEmpresa=true;
				}
			}
		}
		if(mismaEmpresa){
			Rol rolUsuarioConsultante=origen.getRol();
			Rol rolUsuarioConsultado=destino.getRol();
			for(Area o : areasOrigen){
				for(Area d : areasDestino){
					// misma área
					if(o.equals(d)){
						if(rolUsuarioConsultante.getCodigo().equals(Constantes.CODIGO_ROL_JEFE)){
							return true;
						}
					}
					else{
						Area padre=d.getPadre();
						boolean esPadre=false;
						while(padre != null){
							if(padre.equals(o)){
								esPadre=true;
								break;
							}
							padre=padre.getPadre();
						}
						if(esPadre){
							if(rolUsuarioConsultante.getCodigo().equals(Constantes.CODIGO_ROL_JEFE)){
								return true;
							}
							if(rolUsuarioConsultado.getCodigo().equals(Constantes.CODIGO_ROL_EMPLEADO)){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

}
