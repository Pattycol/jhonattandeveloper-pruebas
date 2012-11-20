package com.btg.claro.LBS.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;

import com.btg.claro.LBS.auditoria.Auditable;
import com.btg.dao.entidad.Entidad;


@Entity
@SqlResultSetMapping(name = "rslt_buscarPorRUC", entities = @EntityResult(entityClass = Empresa.class, fields = {
        @FieldResult(name = "id", column = "id_empresa"),
        @FieldResult(name = "ruc", column = "ruc"),
        @FieldResult(name = "razonSocial", column = "razon_social"),
        @FieldResult(name = "consultasPorMes", column = "consultas_por_mes"),
        @FieldResult(name = "fechaCreacion", column = "fecha_creacion"),
        @FieldResult(name = "estado", column = "estado"),
        @FieldResult(name = "codigoCliente", column = "codigo_cliente"),
        @FieldResult(name = "vista", column = "vista"),
        @FieldResult(name = "consultas_web_realizadas", column = "consultas_web_realizadas"),
        @FieldResult(name = "consultas_sms_realizadas", column = "consultas_sms_realizadas"),
        @FieldResult(name = "consultas_adicionales", column = "consultas_adicionales") }))
@NamedNativeQuery(name = "funct_buscarPorRUC", query = "{? = call pq_mant_lbs.LBSSS_buscarPorRUC(:ruc) }", resultSetMapping = "rslt_buscarPorRUC")
public class Empresa implements Entidad{
	
	@Id
	@SequenceGenerator(name="secuenciaEmpresa",sequenceName="EMPRESA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaEmpresa")
	@Column(name="id_empresa")
	private Integer id;
	
	@Auditable
	private String ruc;
	
	@Auditable
	@Column(name="razon_social")
	private String razonSocial;
	
	@Auditable
	@Column(name="consultas_por_mes")
	private Integer consultasPorMes;
	
	@Auditable
	@Column(name="codigo_cliente")
	private String codigoCliente;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Auditable
	@Column(name="estado")
	private Character estado;

	@Auditable
	@Column(name="vista")
	private Boolean vista;// true = 1  y false = 0 en la Base de datos
	
	
	@Auditable
	@ManyToOne
	@JoinColumn(name="id_tipo_servicio")
	private TipoServicio tipoServicio;
	
	@Column(name="consultas_web_realizadas")
	private Integer consultas_web_realizadas;
	
	@Column(name="consultas_sms_realizadas")
	private Integer consultas_sms_realizadas;
	
	@Column(name="consultas_adicionales")
	private Integer consultas_adicionales;
	
    @Column(name = "pivote")
    private Integer pivote;

	public Integer getConsultas_web_realizadas() {
		return consultas_web_realizadas;
	}

	public Integer getConsultas_sms_realizadas() {
		return consultas_sms_realizadas;
	}

	public Integer getConsultas_adicionales() {
		return consultas_adicionales;
	}

	public void setConsultas_web_realizadas(Integer consultas_web_realizadas) {
		this.consultas_web_realizadas = consultas_web_realizadas;
	}

	public void setConsultas_sms_realizadas(Integer consultas_sms_realizadas) {
		this.consultas_sms_realizadas = consultas_sms_realizadas;
	}

	public void setConsultas_adicionales(Integer consultas_adicionales) {
		this.consultas_adicionales = consultas_adicionales;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	

	public Empresa(){}

	public Empresa(Integer id,String ruc,String razonSocial,boolean vista){
		super();
		this.id=id;
		this.ruc=ruc;
		this.razonSocial=razonSocial;
		this.vista=vista;
	}
	
	public Empresa(Integer id,String ruc,String razonSocial){
		super();
		this.id=id;
		this.ruc=ruc;
		this.razonSocial=razonSocial;
	}
	
	public Empresa(Integer id,String ruc,String razonSocial,Character estado,boolean vista){
		super();
		this.id=id;
		this.ruc=ruc;
		this.razonSocial=razonSocial;
		this.vista=vista;
		this.estado=estado;
	}
	
	public Empresa(Integer id,String ruc,String razonSocial,Character estado){
		super();
		this.id=id;
		this.ruc=ruc;
		this.razonSocial=razonSocial;
		this.estado=estado;
	}
	
	@Override
    public Integer getId(){
		return id;
	}


	@Override
    public void setId(Integer id){
		this.id=id;
	}

	public String getRuc(){
		return ruc;
	}

	public void setRuc(String ruc){
		this.ruc=ruc;
	}

	public String getRazonSocial(){
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial){
		this.razonSocial=razonSocial;
	}

	public Integer getConsultasPorMes(){
		return consultasPorMes;
	}

	public void setConsultasPorMes(Integer consultasPorMes){
		this.consultasPorMes=consultasPorMes;
	}

	public String getCodigoCliente(){
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente){
		this.codigoCliente=codigoCliente;
	}

	public Date getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion=fechaCreacion;
	}

	public Character getEstado(){
		return estado;
	}

	public void setEstado(Character estado){
		this.estado=estado;
	}

	public Boolean getVista() {
		return vista;
	}

	public void setVista(Boolean vista) {
		this.vista = vista;
	}

	@Override
	public int hashCode(){
		final int prime=31;
		int result=1;
		result=prime * result + ((id == null) ? 0 : id.hashCode());
		result=prime * result + ((razonSocial == null) ? 0 : razonSocial.hashCode());
		result=prime * result + ((ruc == null) ? 0 : ruc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Empresa other=(Empresa) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		if(razonSocial == null){
			if(other.razonSocial != null)
				return false;
		}
		else if(!razonSocial.equals(other.razonSocial))
			return false;
		if(ruc == null){
			if(other.ruc != null)
				return false;
		}
		else if(!ruc.equals(other.ruc))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Empresa [id=" + id + ", ruc=" + ruc + ", razonSocial=" + razonSocial + ", estado: "+ estado + ", vista : "+ vista +", ESTADO : "+ estado + "]";
	//	return "Empresa [id=" + id + ", ruc=" + ruc + ", razonSocial=" + razonSocial + ", estado: "+ estado + "]";
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

    public Integer getPivote() {
        return pivote;
    }

    public void setPivote(Integer pivote) {
        this.pivote = pivote;
    }

}
