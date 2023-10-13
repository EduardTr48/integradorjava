import java.util.Arrays;

public class Pedido {
    private static int numeracion = 0;
    private int numero_pedido = 0;
    private String[] orden;
    private int numero_mesa;
    private EstadoPedido estado;
    public Pedido(int numero_mesa, String []platos){
        this.estado = EstadoPedido.ESPERA;
        this.numero_pedido = ++numeracion;
        this.numero_mesa = numero_mesa;
        this.orden = new String[4];
        mostrarPlatos(platos);
    }

    public void mostrarPlatos(String[] platos){
        int indice = 0;
        System.out.println("Pedido "+this.numero_pedido);
        while(indice<4){
            for (int i = 0; i < platos.length; i++) {
                System.out.println((i+1)+ ". " + platos[i]);
            }
            int seleccion = Helper.getInteger("Elija un plato conforme a su numeración: ")-1;
            if (seleccion >= 0 && seleccion < platos.length) {
                orden[indice] = platos[seleccion];
                indice++;
            } else {
                System.out.println("Selección no válida. Intente de nuevo.");
            }
        }
        System.out.println("Pedido tomado correctamente");
    }

    public void modificarEstado(EstadoPedido estado){
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numero_pedido=" + numero_pedido +
                ", orden=" + Arrays.toString(orden) +
                ", numero_mesa=" + numero_mesa +
                ", estado=" + estado +
                '}';
    }

}
