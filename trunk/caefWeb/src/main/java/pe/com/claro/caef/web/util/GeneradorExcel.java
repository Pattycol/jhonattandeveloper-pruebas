package pe.com.claro.caef.web.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.avatar.excel.bean.ExcelTemplate;
import pe.com.avatar.excel.generate.ExcelGenerator;
import pe.com.claro.caef.web.beans.ConsultaConsumo;
import pe.com.claro.caef.web.beans.ConsultaRecarga;
import pe.com.claro.caef.web.beans.DetalleEstadoCuenta;
import pe.com.claro.caef.web.beans.LlamadaEntrante;
import pe.com.claro.caef.web.beans.LlamadaFacturada;
import pe.com.claro.caef.web.beans.LlamadaNoFacturada;


public class GeneradorExcel {

	
	public static File generarReporteLlamadasFacturadas(List<LlamadaFacturada> lstLlamadaFacturada, String path) throws Exception
	{		
		File excel = null;
		
		//ClassLoader loader = Thread.currentThread().getContextClassLoader();
		//String path = ClassLoader.getSystemResource("/plantillas/llamadasFacturadas.xml").toURI().getPath();
		//String path =  loader.getResource("//plantillas//llamadasFacturadas.xml").toURI().getPath();
		
		ExcelTemplate excelTemplate = new ExcelTemplate();
		Map context = new HashMap();
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		ReporteLlamadasFacturadas reporte  = new ReporteLlamadasFacturadas();
		reporte.setPlantilla("reporteLlamadasFacturadas.xls");
		reporte.setLstLlamadaFacturada(lstLlamadaFacturada);
		//cargamos el documento 
		model.put("reporte", reporte);
		
		excelTemplate.setParameters(model);
		excelTemplate.setTemplate(new File(path));

		excel = ExcelGenerator.generate(excelTemplate);
		
		return excel;
	}
	
	public static File generarReporteConsultaConsumos(List<ConsultaConsumo> lstConsultaConsumo, String path)
	{
		File excel = null;
		
		try{
//			ClassLoader loader = Thread.currentThread().getContextClassLoader();
//			String path =  loader.getResource("/plantillas/consultaConsumos.xml").toURI().getPath();
//			
			ExcelTemplate excelTemplate = new ExcelTemplate();
			Map context = new HashMap();
			
			ReporteConsultaConsumos reporte  = new ReporteConsultaConsumos();
			reporte.setPlantilla("reporteConsultaConsumos.xls");
			reporte.setLstConsultaConsumo(lstConsultaConsumo);
			//cargamos el documento 
			context.put("reporte", reporte);
			
			excelTemplate.setParameters(context);
			excelTemplate.setTemplate(new File(path));
			
			excel = ExcelGenerator.generate(excelTemplate);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return excel;
	}
	
	public static File generarReporteConsultaRecargas(List<ConsultaRecarga> lstConsultaRecarga, String path)
	{
		File excel = null;
		
		try{
			//ClassLoader loader = Thread.currentThread().getContextClassLoader();
			//String path =  loader.getResource("/plantillas/consultaRecargas.xml").toURI().getPath();
			
			ExcelTemplate excelTemplate = new ExcelTemplate();
			Map context = new HashMap();
			
			ReporteConsultaRecargas reporte  = new ReporteConsultaRecargas();
			reporte.setPlantilla("reporteConsultaRecargas.xls");
			reporte.setLstConsultaRecarga(lstConsultaRecarga);
			//cargamos el documento 
			context.put("reporte", reporte);
			
			excelTemplate.setParameters(context);
			excelTemplate.setTemplate(new File(path));
			
			excel = ExcelGenerator.generate(excelTemplate);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return excel;
	}
	
	public static File generarReporteLlamadasEntrantes(List<LlamadaEntrante> lstLlamadaEntrante, String path)
	{
		File excel = null;
		
		try{
			//ClassLoader loader = Thread.currentThread().getContextClassLoader();
			//String path =  loader.getResource("/plantillas/llamadasEntrantes.xml").toURI().getPath();
			
			ExcelTemplate excelTemplate = new ExcelTemplate();
			Map context = new HashMap();
			
			ReporteLlamadasEntrantes reporte  = new ReporteLlamadasEntrantes();
			reporte.setPlantilla("reporteLlamadasEntrantes.xls");
			reporte.setLstLlamadaEntrante(lstLlamadaEntrante);
			//cargamos el documento 
			context.put("reporte", reporte);
			
			excelTemplate.setParameters(context);
			excelTemplate.setTemplate(new File(path));
			
			excel = ExcelGenerator.generate(excelTemplate);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return excel;
	}
	
	public static File generarReporteLlamadasNoFacturadas(List<LlamadaNoFacturada> lstLlamadaNoFacturada, String path)
	{
		File excel = null;
		
		try{
//			ClassLoader loader = Thread.currentThread().getContextClassLoader();
//			String path =  loader.getResource("/plantillas/llamadasNoFacturadas.xml").toURI().getPath();
//			
			ExcelTemplate excelTemplate = new ExcelTemplate();
			
			Map context = new HashMap();
			
			ReporteLlamadasNoFacturadas reporte  = new ReporteLlamadasNoFacturadas();
			reporte.setPlantilla("reporteLlamadasNoFacturadas.xls");
			reporte.setLstLlamadaNoFacturada(lstLlamadaNoFacturada);
			//cargamos el documento 
			context.put("reporte", reporte);
			
			excelTemplate.setParameters(context);
			excelTemplate.setTemplate(new File(path));
			
			excel = ExcelGenerator.generate(excelTemplate);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return excel;
	}
	
	
	public static File generarReporteEstadoCuenta(List<DetalleEstadoCuenta> lstEstadoCuenta, String path)
	{
		File excel = null;
		
		try{
			
			ExcelTemplate excelTemplate = new ExcelTemplate();
			
			Map context = new HashMap();
			
			ReporteConsultaEstadoCuenta reporte  = new ReporteConsultaEstadoCuenta();
			reporte.setPlantilla("reporteEstadoCuenta.xls");
			reporte.setLstEstadoCuenta(lstEstadoCuenta);
			//cargamos el documento 
			context.put("reporte", reporte);
			
			excelTemplate.setParameters(context);
		
			excelTemplate.setTemplate(new File(path));
			
			excel = ExcelGenerator.generate(excelTemplate);
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return excel;
	}
}
