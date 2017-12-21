package invoice;

import java.util.Calendar;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
       
        // En otra clase (la que contiene el método main) se deberá crear un cliente con los siguientes datos:
        Scanner sc=new Scanner(System.in);
        int opcion=0;
        Calendar cal = Calendar.getInstance();
        int dia=cal.get(Calendar.DAY_OF_MONTH);
        int mes=cal.get(Calendar.MONTH)+1;
        int anyo=cal.get(Calendar.YEAR);
         
        Fecha hoy = new Fecha(dia,mes,anyo);
        
        String nif="B-21212121";
        String nombre= "Informatica manises s.l.";
        String direccion="Avda. Blasco Ibañez nº 4";
        String telefono="963 21 45 78";
        
        String proCodigo1="25UM58-P";
        String proNombre1="LG 25UM58-P";
        String proDescripcion1="Monitor UltraWide de 64 cm";
        
        String proCodigo2="sdskssd240";
        String proNombre2="Sandisk ssd 240";
        String proDescripcion2="Disco duro ssd de 240 Gb";
        
        String proCodigo3="sdskpd32";
        String proNombre3="Pendrive 32 GB";
        String proDescripcion3="Pendrive ScanDisk - 32 GB - USB 3.0";
        
        /* 
        do{
        System.out.print("\tNombre del cliente: ");
        nombre=sc.next();
        }while((nombre.equals(null)));
        
        do{
        System.out.print("\tNIF: ");
        nif=sc.next();
        }while((nif.equals(null)));
        
        do{
        System.out.print("\tDirección: ");
        direccion=sc.next();
        }while((direccion.equals(null)));
      */  
       Cliente cliente = new Cliente(nif,nombre,direccion,telefono);
    /*   
       do{
       System.out.println("Qué produdcto quiere comprar: ");
       System.out.println("1. "+proDescripcion1);
       System.out.println("2. "+proDescripcion2);
       System.out.println("3. "+proDescripcion3);
       System.out.println("4. Terminar");
       
       opcion=sc.nextInt();
       
       
       
       }while (opcion>1 || opcion<=4);*/
       Factura factura = new Factura(1,cliente,hoy);
        
        Producto pro[] =new Producto[10];
        LineaFactura linea[]=new LineaFactura[10];
        
        Producto producto1 = new Producto(proCodigo1,proNombre1,proDescripcion1,200);
        LineaFactura linea1 = new LineaFactura(producto1,2,1);
        factura.nuevaLinea(linea1);
                
        Producto producto2 = new Producto(proCodigo2,proNombre2,proDescripcion2,100);
        LineaFactura linea2 = new LineaFactura(producto2,10,-2);
        factura.nuevaLinea(linea2);
                
        Producto producto3 = new Producto(proCodigo3,proNombre3,proDescripcion3,10);
        LineaFactura linea3 = new LineaFactura(producto3,10,10);
        factura.nuevaLinea(linea3);
         //LineaFactura linea4 = new LineaFactura(producto3,37,1);
        //factura.nuevaLinea(linea4);
        
        
        System.out.print(factura.imprimir());
        //factura.borrarLineaNumero(0);
        //factura.borrarLineaNombre("Sandisk ssd 240");
        
        //System.out.print(factura.imprimir());
        
        
        //System.out.println("\tlinea de pendrive= "+factura.buscarProducto("Pendrive 32 GB")+"");
       
        /*
        MEJORAS:
        
        - si el descuento se especifica debe ser mayor de cero
        - al agregar un articulo que ya existe en la factura el descuento establecido establece el descuento definitivo
        - desfragmentación del vector LineaFactura cuando se borra una linea y tiene espacios en blanco: ordenVector()
        - se muestra por pantalla:
            precio unitario
            precio articulo x cantidad
            descuento en %
            descuento en €
            subtotal = precio art x cantidad - descuento
        -
        
        
        */
        
    }
    
}
