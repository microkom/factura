package invoice;

import static java.lang.Math.floor;
import static java.lang.Math.round;


public class Factura {
    private int numero;
    private Cliente cliente;
    private Fecha fecha;
    private LineaFactura[] lineas = new LineaFactura[10];
    
    public Factura(int numero, Cliente cliente, Fecha fecha){
        this.numero=numero;
        this.cliente=cliente;
        this.fecha=fecha;
    }
    
    public int getNumero(){return this.numero;}
    public Cliente getCliente(){return this.cliente;}
    public Fecha getFecha(){return this.fecha;}
    public LineaFactura[] getLineas(){return this.lineas;}
    
    public void setCliente(Cliente cliente){this.cliente=cliente;}
    public void setFecha(Fecha fecha){this.fecha=fecha;}
    public void setLineaFactura(LineaFactura[] lineas){this.lineas=lineas;}
    
    /*
    nuevaLinea(LineaFactura lf): Añadir una línea a la factura. En caso
    que lo haya podido añadir debe devolver el número de la linea en la
    que la ha insertado. En caso contrario (vector lleno) debe devolver -
    1. 
    */
    public int nuevaLinea(LineaFactura lf){
        int vector=-1;
        int i=0,j;
        boolean insertado=false;
        j=buscarProducto(lf.getProducto().getNombre());
        if (j!=-1){
            this.lineas[j].setCantidad(lineas[j].getCantidad()+lf.getCantidad());
        }else{
        while (i<this.lineas.length && insertado==false){
            if(this.lineas[i]==null){
                lineas[i]=lf;
                insertado=true;
                vector=i;
            }
            i++;
        }
        }
        return vector;
    }

    
    //borrarLineaNumero(int numLinea) : Elimina la linea con el número indicado
    public void borrarLineaNumero(int numLinea){
        //borrado del vector recibiendo la posición exacta 
        this.lineas[numLinea]=null;
        ordenVector();
    }
    
    //Agrupación del vector al inicio cuando tiene espacios blancos
    public void ordenVector(){
        for(int i=0;i<lineas.length;i++){
            //buscar lineas que no están vacías
            if(lineas[i]!=null){
                boolean found=false;

                //búsqueda del primer null en el array y reemplazo con el último not null
                for(int j=0;j<lineas.length && found==false;j++){
                    if(lineas[j]==null){
                        lineas[j]=lineas[i];
                        lineas[i]=null;
                        found=true;
                    }
                }
            }
        }
    }
    
    /*
    borrarLineaNombre(String nombre): Elimina la linea con el producto indicado 
    (buscar por nombre de producto).                                        */
    public void borrarLineaNombre(String nombre){
        int i=0;
        boolean encontrado=false;
        while(i<this.lineas.length && encontrado==false){
             //saltarse las campos del vector vacio
            if(lineas[i]!=null){
                //busqueda del nombre recibido por parametro con el vector actual en i
                if (this.lineas[i].getProducto().getNombre().equals(nombre)){
                    //borrado del contenido de una casilla del vector
                    this.lineas[i]=null;
                    encontrado=true;
                }
            }    
            i++;
        }
        ordenVector();
    }
    
    //importeTotal(): Calcular el importe total.
//importeTotal(): Calcular el importe total.
    public double importeTotal(){
        double total=0;int i;
        for (i=0;i<this.lineas.length;i++){
            //saltarse las campos del vector vacio
            double desc=0;
            if(this.lineas[i]!=null){
                //sumar los precios de todos los productos
                //desc=this.lineas[i].getProducto().getPrecio()*this.lineas[i].getCantidad()*this.lineas[i].getDescuento();
                total+=(this.lineas[i].getProducto().getPrecio()*this.lineas[i].getCantidad())-desc;
            }
        }                
        return total;
    }
    public double descuentoTotal(){
        double desc=0;int i;
        for (i=0;i<this.lineas.length;i++){
            //saltarse las campos del vector vacio
            if(this.lineas[i]!=null){
                //sumar los descuentos de todos los productos
                desc+=this.lineas[i].getProducto().getPrecio()*this.lineas[i].getCantidad()*this.lineas[i].getDescuento();
                
            }
        }                
        return desc;
    }
    
    //ImporteTotalImpuestos(): Devuelve el importe total con el iva(21%).
    public double importeTotalImpuestos(){
        //calculo del importe total más el IVA
        return round((importeTotal()-descuentoTotal())*1.21);
    }

    

    /*
    buscarProducto(String nombre): Buscar si una factura tiene un
    determinado el producto indicado en una de sus lineas. Debe
    devolver el número de linea en caso de encontrarlo o -1 en caso de
    que no.
    */
    public int buscarProducto(String nombre){
        int numLinea=-1,i=0;
        boolean found=false;
        while(i<lineas.length && found==false){
            //saltarse las campos del vector vacio
            if(lineas[i]!=null){
                //comparar los nombres del producto con el que han pasado por parametro
                if (lineas[i].getProducto().getNombre().equalsIgnoreCase(nombre)){
                    //asignar el valor de la linea donde se encontró el nombre a la variable
                    numLinea=i;
                    //variable de control para salir del bucle
                    found=true;
                }
            }
            //incremento del recorrido
            i++;
        }
        return numLinea;
    }
    
    /* 
    imprimir(): Método para imprimir los datos de la factura Debe incluir número de factura, 
    fecha en formato corto, datos del cliente, listado de productos e importe antes y 
    después de impuestos         */
    public String imprimir(){
        String prod="",hyp="";int i=0;
        for (i=0;i<this.lineas.length;i++){
            //saltarse las campos del vector vacio
            if(this.lineas[i]!=null){
                //listado de productos
                double desc=round(this.lineas[i].getDescuento()*this.lineas[i].getProducto().getPrecio());
                double descAll=0;
                descAll+=desc;
                double descAplic=round(this.lineas[i].getProducto().getPrecio()-desc);
                prod+="\t "+(i+1)+"\t"+this.lineas[i].getCantidad()+"\t  "+
                        this.lineas[i].getProducto().getPrecio()+"\t      "+ //precio por unidad
                        this.lineas[i].getProducto().getPrecio()*this.lineas[i].getCantidad()+"\t"+ //precio 
                        this.lineas[i].getDescuento()*100+"\t"+             //descuento en porcentaje
                        desc*this.lineas[i].getCantidad()+"\t"+             //descuento en euros
                        descAplic*this.lineas[i].getCantidad()+"\t"+        //subtotal cuenta por unidad
                        this.lineas[i].getProducto().getDescripcion()+"\n";
            }
        }
        for (i=0;i<102;i++)hyp+=("-");
        return "\n\n\n\n\n\n\n\n"
                + "\t"+hyp+"\n\tFACTURA DE VENTA.\n\t"+hyp+"\n"+
                "\tNº "+this.numero+"\t"+
                "\tFecha: "+fecha.corta()+
                "\tNIF: "+cliente.getNif()+"\n"+
                "\tNombre: "+cliente.getNombre()+"\t"+
                "Dirección: "+cliente.getDireccion()+"     "+
                "Teléfono: "+cliente.getTelefono()+"\n\t"+hyp+"\n"+
                "\tPRODUCTOS\n\t"+hyp+"\n"+
                "\tItem.  Cant.     Precio U.    Precio   Dto-%\tDto-€\tSubt    Descripción:\n"+prod+"\n\t"+
                hyp+
                "\n\t\tSutotal: \t"+round(importeTotal())+" €\n"+
                "\t\tDesc: \t\t"+round(descuentoTotal())+" €\n"+
                "\t\tSutotal: \t"+round(importeTotal()-descuentoTotal())+" €\n"+
                "\t\tIVA: \t\t"+round((importeTotal()-descuentoTotal())*0.21)+" €\n"+
                "\t\tTotal: \t\t"+round(importeTotalImpuestos())+" €\n\n";
    }
}
