package unmsm.fisi.tesis.servicio;

public class Quicksort {

	  public static void main(String args[]){
	
		  /*
		  ConocimientoFitness c1= new ConocimientoFitness(321,1);
		  ConocimientoFitness c2= new ConocimientoFitness(123,2);
		  ConocimientoFitness c3= new ConocimientoFitness(213,3);
		  ConocimientoFitness c4= new ConocimientoFitness(234,4);
		  ConocimientoFitness c5= new ConocimientoFitness(1,5);
		  ConocimientoFitness c6= new ConocimientoFitness(4,6);
		  ConocimientoFitness c7= new ConocimientoFitness(5,7);
		  ConocimientoFitness c8= new ConocimientoFitness(6,8);
		  ConocimientoFitness c9= new ConocimientoFitness(7,9);
		  
		  ConocimientoFitness arrayEntrada[]={c1,c2,c3,c4,c5,c6,c7,c8,c9}; //Este es el array de elementos que vamos a ordenar
		  //ConocimientoFitness arrayEntrada[]={321, 123, 213, 234, 1, 4, 5, 6}; //Este es el array de elementos que vamos a ordenar
	       quickSort(arrayEntrada); //llamada al metodo quickSort 
	       for (int i=0;i < arrayEntrada.length;i++){ //Este bucle imprime el contenido del array
	          System.out.print("["+arrayEntrada[i].getValorFitness()+" - "+arrayEntrada[i].getPosicionCromosoma()+"]    ");
	       }//fin del for
	       */
	       
		  int val = 10;
		  int num=val/2;
		  System.out.println("valor de val: "+val+"==>"+num);
		  
		  
		  
	     }//fin del main
	     

	
	
	public static void quickSort(ConocimientoFitness[] a) {
		quickSort(a, 0, a.length - 1);
	}
	
	

	public static void swapReferences(ConocimientoFitness[] a, int index1, int index2) {
		ConocimientoFitness tmp1 = a[index1];
		ConocimientoFitness tmp2 = a[index2];

		a[index1]=tmp2;
		a[index2]=tmp1;
		//tmp=null;
	}

	private static void quickSort(ConocimientoFitness[] a, int low, int high) {
		if (low >= high) 
		{
			return;
		} else 
		{ // ordenamos entre sí el primer elemento, el último y el del
					// centro (mediana de tres)
			int middle = (low + high) / 2;
			
			if (a[middle].getValorFitness() < a[low].getValorFitness())
				swapReferences(a, low, middle);
			
			if (a[high].getValorFitness() < a[low].getValorFitness())
				swapReferences(a, low, high);
			
			if (a[high].getValorFitness() < a[middle].getValorFitness())
				swapReferences(a, middle, high);
			
			double pivot = a[middle].getValorFitness(); // colocamos pivote
			int i, j;
			
			for (i = low, j = high;;) {// recorremos con i de izda a dcha con j
										// de dcha a izda
				while (a[++i].getValorFitness() < pivot)
					;// si están bien colocados seguimos corriendo posiciones
				while (pivot < a[--j].getValorFitness())
					;// si están bien colocados seguimos corriendo posiciones
				if (i < j) // si no están bien colocados los intercambiamos
					swapReferences(a, i, j);
				else
					break;
			}
			quickSort(a, low, i - 1); // ordenamos la mitad izda
			quickSort(a, i + 1, high); // ordenamos la mitad dcha
		}
	}
}// class QuickSort

