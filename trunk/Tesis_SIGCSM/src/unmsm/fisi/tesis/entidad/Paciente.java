/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unmsm.fisi.tesis.entidad;

/**
 *
 * @author Jhon
 */
public class Paciente {

    private String codigo;
    private String nombres;
    private String apellidos;
    private int diagnostico;
    private int[] experiencias;

    public Paciente() {
    }

    public Paciente(String codigo, String nombres, String apellidos, int diagnostico, int[] experiencias) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.diagnostico = diagnostico;
        this.experiencias = experiencias;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(int diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int[] getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(int[] experiencias) {
        this.experiencias = experiencias;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }



}
