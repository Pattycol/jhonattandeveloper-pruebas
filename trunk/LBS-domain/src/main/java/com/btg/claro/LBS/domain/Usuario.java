package com.btg.claro.LBS.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.btg.claro.LBS.auditoria.Auditable;
import com.btg.dao.entidad.Entidad;

@Entity
public class Usuario implements Entidad{

	@Id
	@SequenceGenerator(name="secuenciaUsuario",sequenceName="USUARIO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaUsuario")
	@Column(name="id_usuario")
	private Integer id;

	@Auditable
	private String numero;

	@Auditable
	private String clave;
	
	@Auditable
	private String correo;

	@Auditable
	private String nombres;

	@Auditable
	private String apellidos;

	@Auditable
	@Column(name="consultas_por_mes")
	private Integer consultasPorMes;

	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	private Character estado;

	@Auditable
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="usuario_por_area",joinColumns={@JoinColumn(name="id_usuario")},inverseJoinColumns={@JoinColumn(name="id_area")})
	private List<Area> areas;

	@Auditable
	@ManyToOne
	@JoinColumn(name="rol")
	private Rol rol;
	
	@Auditable
	@ManyToOne
	@JoinColumn(name="id_tipo_servicio")
	private TipoServicio tipoServicio;
	
    @Column(name = "clave_recuperada")
    private Character    clave_recuperada;
	

	
	public Character getClave_recuperada() {
        return clave_recuperada;
    }

    public void setClave_recuperada(Character clave_recuperada) {
        this.clave_recuperada = clave_recuperada;
    }

    public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Usuario(){}

	public Usuario(Integer id,String numero){
		super();
		this.id=id;
		this.numero=numero;
	}

	public Usuario(Integer id,String numero,String nombres,String apellidos){
		super();
		this.id=id;
		this.numero=numero;
		this.nombres=nombres;
		this.apellidos=apellidos;
	}
	
	public Usuario(Integer id,String numero,String nombres,String apellidos,Integer consultasPorMes){
		super();
		this.id=id;
		this.numero=numero;
		this.nombres=nombres;
		this.apellidos=apellidos;
		this.consultasPorMes=consultasPorMes;
	}

	public Usuario(Integer id,String numero,String nombres,String apellidos,Integer consultasPorMes,Rol rol){
		super();
		this.id=id;
		this.numero=numero;
		this.nombres=nombres;
		this.apellidos=apellidos;
		this.consultasPorMes=consultasPorMes;
		this.rol=rol;
	}

	@Override
    public Integer getId(){
		return id;
	}

	@Override
    public void setId(Integer id){
		this.id=id;
	}

	public String getNumero(){
		return numero;
	}

	public void setNumero(String numero){
		this.numero=numero;
	}

	public String getClave(){
		return clave;
	}

	public void setClave(String clave){
		this.clave=clave;
	}

	public String getCorreo(){
		return correo;
	}

	public void setCorreo(String correo){
		this.correo=correo;
	}

	public String getNombres(){
		return nombres;
	}

	public void setNombres(String nombres){
		this.nombres=nombres;
	}

	public String getApellidos(){
		return apellidos;
	}

	public void setApellidos(String apellidos){
		this.apellidos=apellidos;
	}

	public Integer getConsultasPorMes(){
		return consultasPorMes;
	}

	public void setConsultasPorMes(Integer consultasPorMes){
		this.consultasPorMes=consultasPorMes;
	}

	public Date getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion=fechaCreacion;
	}

	public Date getFechaActualizacion(){
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion){
		this.fechaActualizacion=fechaActualizacion;
	}

	public Character getEstado(){
		return estado;
	}

	public void setEstado(Character estado){
		this.estado=estado;
	}

	public List<Area> getAreas(){
		return areas;
	}

	public void setAreas(List<Area> areas){
		this.areas=areas;
	}

	public Rol getRol(){
		return rol;
	}

	public void setRol(Rol rol){
		this.rol=rol;
	}

	@Override
	public int hashCode(){
		final int prime=31;
		int result=1;
		result=prime * result + ((id == null) ? 0 : id.hashCode());
		result=prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Usuario other=(Usuario) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		if(numero == null){
			if(other.numero != null)
				return false;
		}
		else if(!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Usuario [id=" + id + ", numero=" + numero + ", nombres=" + nombres + ", apellidos=" + apellidos + ", rol=" + rol +"]";
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
