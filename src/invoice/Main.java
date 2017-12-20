package invoice;

import java.util.Calendar;


public class Main {

    public static void main(String[] args) {
       
        // En otra clase (la que contiene el método main) se deberá crear un cliente con los siguientes datos:
        
        Calendar cal = Calendar.getInstance();
        int dia=cal.get(Calendar.DAY_OF_MONTH);
        int mes=cal.get(Calendar.MONTH)+1;
        int anyo=cal.get(Calendar.YEAR);
         
        Fecha hoy = new Fecha(dia,mes,anyo);
        
        String nif="B-21212121";
        String nombre= "Informatica manises s.l.";
        String direccion="Avda. Blasco Ibañez nº 4";
        String telefono="963 21 45 78";
        
        Cliente cliente = new Cliente(nif,nombre,direccion,telefono);
        
        Factura factura = new Factura(1,cliente,hoy);
        
        Producto producto1 = new Producto("25UM58-P","LG 25UM58-P","Monitor UltraWide de 64 cm",200);
        LineaFactura linea1 = new LineaFactura(producto1,2,1);
        factura.nuevaLinea(linea1);
                
        Producto producto2 = new Producto("sdskssd240","Sandisk ssd 240","Disco duro ssd de 240 Gb",100);
        LineaFactura linea2 = new LineaFactura(producto2,10,2);
        factura.nuevaLinea(linea2);
                
        Producto producto3 = new Producto("sdskpd32","Pendrive 32 GB","Pendrive ScanDisk - 32 GB - USB 3.0",10);
        LineaFactura linea3 = new LineaFactura(producto3,7,3);
        LineaFactura linea4 = new LineaFactura(producto3,37,1);
        factura.nuevaLinea(linea3);
        factura.nuevaLinea(linea4);
        
        
        System.out.print(factura.imprimir());
        factura.borrarLineaNumero(0);
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
