package unmsm.fisi.tesis.servicio;

public class OrganizacionFuente {

	
	private int OMS[]={1,2,	5,6,	9,	10,	13,	15	,16,	23,	24,	25,	26,	28,	30,	31	,33};
	private int OMS_Prioritarios[];
	
	private int GESMM[]={1,2	,3	,4	,9	,10	,14	,15	,16	,17	,18	,19	,20	,27	,28	,32	,34	,34	,
					35	,39	,40	,41	,42	,43	,44	,45	,46	,46	,50	,52	,53};
	private int GESMM_Prioritarios[];
	
	private int AAC[]={22,23	,36	,3	,4	,33	,28	,37	,38};
	private int AAC_Prioritarios[];
	
	private int PNE[]={3,4	,6	,11	,12	,19	,20	,25	,30	,31};
	private int PNE_Prioritarios[];
	
	private int FDI[]={3,4	,5	,6	,11	,12	,15	,18	,30	,31	,53};
	private int FDI_Prioritarios[];
	
	private int CNE[]={3,4	,6	,11	,12	,14	,19	,20	,25	,28	,39	,44	,45	,47	,48	,49	,50};
	private int CNE_Prioritarios[];
	
	private int GEIR[]={1,2	,5	,7	,8	,21	,22	,29	,34};
	private int GEIR_Prioritarios[];
	
	
	public OrganizacionFuente(){
		boolean prueba = false;
		
		if(false){
			 int _OMS_Prioritarios[]={25,26,28,30,31,33};
			 this.setOMS_Prioritarios(_OMS_Prioritarios);
			 
			int _GESMM_Prioritarios[]={27,28,50};
			this.setGESMM_Prioritarios(_GESMM_Prioritarios);
			
			int _AAC_Prioritarios[]={22,23	,36	,3	,4	,33	,28	,37	,38};
			this.setAAC_Prioritarios(_AAC_Prioritarios);
			
			int _PNE_Prioritarios[]={3,4	,6	,11	,12	,19	,20	,25	,30	,31};//se ve de otra manera, revisar.
			this.setPNE_Prioritarios(_PNE_Prioritarios);
			
			int _FDI_Prioritarios[]={15,18};
			this.setFDI_Prioritarios(_FDI_Prioritarios);
			
			int _CNE_Prioritarios[]={3,4,6,11,12,25};
			this.setCNE_Prioritarios(_CNE_Prioritarios);
			
			int _GEIR_Prioritarios[]={34};
			this.setGEIR_Prioritarios(_GEIR_Prioritarios);
				
		}else{
			int _OMS_Prioritarios[]={2,6,9};//de prueba
			this.setOMS_Prioritarios(_OMS_Prioritarios);
			
			int _GESMM_Prioritarios[]={1,2,4};//de prueba
			this.setGESMM_Prioritarios(_GESMM_Prioritarios);
			
			int _AAC_Prioritarios[]={3	,4};//prueba
			this.setAAC_Prioritarios(_AAC_Prioritarios);
			
			int _PNE_Prioritarios[]={3,6,11};//prueba
			this.setPNE_Prioritarios(_PNE_Prioritarios);
			
			int _FDI_Prioritarios[]={5,12};//prueba
			this.setFDI_Prioritarios(_FDI_Prioritarios);
			
			int _CNE_Prioritarios[]={6,11,12};//prueba
			this.setCNE_Prioritarios(_CNE_Prioritarios);
			
			int _GEIR_Prioritarios[]={8};//prueba
			this.setGEIR_Prioritarios(_GEIR_Prioritarios);
		}
		
	}
	
	public int[] getOMS() {
		return OMS;
	}
	public void setOMS(int[] oMS) {
		OMS = oMS;
	}
	public int[] getOMS_Prioritarios() {
		return OMS_Prioritarios;
	}
	public void setOMS_Prioritarios(int[] oMS_Prioritarios) {
		OMS_Prioritarios = oMS_Prioritarios;
	}
	public int[] getGESMM() {
		return GESMM;
	}
	public void setGESMM(int[] gESMM) {
		GESMM = gESMM;
	}
	public int[] getGESMM_Prioritarios() {
		return GESMM_Prioritarios;
	}
	public void setGESMM_Prioritarios(int[] gESMM_Prioritarios) {
		GESMM_Prioritarios = gESMM_Prioritarios;
	}
	public int[] getAAC() {
		return AAC;
	}
	public void setAAC(int[] aAC) {
		AAC = aAC;
	}
	public int[] getAAC_Prioritarios() {
		return AAC_Prioritarios;
	}
	public void setAAC_Prioritarios(int[] aAC_Prioritarios) {
		AAC_Prioritarios = aAC_Prioritarios;
	}
	public int[] getPNE() {
		return PNE;
	}
	public void setPNE(int[] pNE) {
		PNE = pNE;
	}
	public int[] getPNE_Prioritarios() {
		return PNE_Prioritarios;
	}
	public void setPNE_Prioritarios(int[] pNE_Prioritarios) {
		PNE_Prioritarios = pNE_Prioritarios;
	}
	public int[] getFDI() {
		return FDI;
	}
	public void setFDI(int[] fDI) {
		FDI = fDI;
	}
	public int[] getFDI_Prioritarios() {
		return FDI_Prioritarios;
	}
	public void setFDI_Prioritarios(int[] fDI_Prioritarios) {
		FDI_Prioritarios = fDI_Prioritarios;
	}
	public int[] getCNE() {
		return CNE;
	}
	public void setCNE(int[] cNE) {
		CNE = cNE;
	}
	public int[] getCNE_Prioritarios() {
		return CNE_Prioritarios;
	}
	public void setCNE_Prioritarios(int[] cNE_Prioritarios) {
		CNE_Prioritarios = cNE_Prioritarios;
	}
	public int[] getGEIR() {
		return GEIR;
	}
	public void setGEIR(int[] gEIR) {
		GEIR = gEIR;
	}
	public int[] getGEIR_Prioritarios() {
		return GEIR_Prioritarios;
	}
	public void setGEIR_Prioritarios(int[] gEIR_Prioritarios) {
		GEIR_Prioritarios = gEIR_Prioritarios;
	}
	
	

	
}
