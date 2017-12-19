package invoice;


public class Main {

    public static void main(String[] args) {
       
        // En otra clase (la que contiene el método main) se deberá crear un cliente con los siguientes datos:
        
        String nif="B-21212121";
        String nombre= "Informatica manises s.l.";
        String direccion="Avda. Blasco Ibañez nº 4";
        String telefono="963 21 45 78";
        
        Cliente cliente = new Cliente(nif,nombre,direccion,telefono);
        
        Producto producto1 = new Producto("25UM58-P","LG 25UM58-P","Monitor UltraWide de 64 cm",200);
        Producto producto2 = new Producto("sdskssd240","Sandisk ssd 240","Disco duro ssd de 240 Gb",100);
        Producto producto3 = new Producto("sdskpd32","Pendrive 32 GB","Pendrive ScanDisk - 32 GB - USB 3.0",10);
        
        Fecha fecha = new Fecha(15,12,2017);
        
        Factura factura = new Factura(1,cliente,fecha);
        LineaFactura linea1 = new LineaFactura(producto1,2);
        LineaFactura linea2 = new LineaFactura(producto2,10,5);
        LineaFactura linea3 = new LineaFactura(producto3,7,30);
        LineaFactura linea4 = new LineaFactura(producto3,37,10);
        
       
        factura.nuevaLinea(linea1);
        factura.nuevaLinea(linea2);
        factura.nuevaLinea(linea3);
        factura.nuevaLinea(linea4);
        
        System.out.print(factura.imprimir());
        factura.borrarLineaNumero(0);
        //factura.borrarLineaNombre("Sandisk ssd 240");
        
        //System.out.print(factura.imprimir());
        
        
        //System.out.println("\tlinea de pendrive= "+factura.buscarProducto("Pendrive 32 GB")+"");
       
    }
    
}
