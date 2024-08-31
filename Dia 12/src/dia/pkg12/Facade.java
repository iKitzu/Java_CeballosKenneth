
package dia.pkg12;



// Subsistema complejo
class SistemaInventario {
    void verificarStock(String producto) {
        System.out.println("Verificando stock de " + producto);
    }
}

class SistemaEnvios {
    void procesarEnvio(String producto) {
        System.out.println("Procesando envío de " + producto);
    }
}

class SistemaFacturacion {
    void generarFactura(String producto) {
        System.out.println("Generando factura para " + producto);
    }
}

// Fachada
class GestionPedidos {
    private SistemaInventario inventario = new SistemaInventario();
    private SistemaEnvios envios = new SistemaEnvios();
    private SistemaFacturacion facturacion = new SistemaFacturacion();

    void procesarPedido(String producto) {
        inventario.verificarStock(producto);
        facturacion.generarFactura(producto);
        envios.procesarEnvio(producto);
    }
}

