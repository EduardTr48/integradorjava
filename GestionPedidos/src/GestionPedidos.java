import java.util.ArrayList;


public class GestionPedidos {
    private final Queue<Pedido> colaPedidos;
    private final Queue<Pedido> colaPreparando;
    private final ArrayList<Pedido> arrayServidos;
    public GestionPedidos(int[]mesas, String[]platos){
        colaPedidos = new Queue<>(mesas.length);
        colaPreparando = new Queue<>(mesas.length);
        arrayServidos= new ArrayList<>();
        mostrarMenu(mesas,platos);
    }
    public void mostrarMenu(int[]num_mesas,String[]platos){
        boolean salir = false;
        int opcion;

        while(!salir){
            System.out.println("Gestion de Pedidos: ");
            System.out.println("1-Alta de Pedido: ");
            System.out.println("2-Preparacion de pedidos(5 por vez)");
            System.out.println("3-Entrega de pedidos");
            System.out.println("4-Salir");
            opcion = Helper.getInteger("Elija una opcion:");
            switch (opcion) {
                case 1 -> {
                    AltaPedidos(num_mesas, platos);
//  Para verificar si se estan agregando bien los pedidos a la cola
                    System.out.println(colaPedidos);
                }
                case 2 -> {
                    PreparacionPedidos();
//  Para verificar si se estan agregando bien los pedidos a la cola
                    System.out.println(colaPreparando);
                }
                case 3 -> {
                    EntregaPedidos();
//  Para verificar si se estan agregando bien los pedidos al array
                    for (Pedido pedido : arrayServidos) {
                        System.out.println(pedido.toString());
                    }
                }
                case 4 -> salir = true;
                default -> System.out.println("Opcion no valida. Elija otra");
            }
        }
    }
    public void AltaPedidos(int[] mesas, String[] platos){
//        Validacion
        if(mesas.length==0){
            System.out.println("No hay mesas para tomar pedidos");
            return;
        }else if(!colaPedidos.isEmpty()){
            System.out.println("La cola ya tiene pedidos pendientes");
            return;
        }

        for(int numero_mesa: mesas){
            Pedido pedido = new Pedido(numero_mesa,platos);
            colaPedidos.add(pedido);
        }
        System.out.println("Pedidos tomados correctamente");
    }

    public void PreparacionPedidos() {
        if(colaPedidos.isEmpty()){
            System.out.println("No hay pedidos para preparar");
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (!colaPedidos.isEmpty()) {
                Pedido pedidoPreparando = colaPedidos.remove();
                pedidoPreparando.modificarEstado(EstadoPedido.PREPARANDO);
                colaPreparando.add(pedidoPreparando);
            } else {
                System.out.println("Ya no se pueden preparar mas pedidos");
                break;
            }
        }
        System.out.println("Pedidos preparandose....");
    }
    public void EntregaPedidos(){
        int cantidad = Helper.getInteger("Cuantos pedidos desea entregar: ");
        if(cantidad>colaPreparando.size()){
            System.out.println("No se puede entregar esa cantidad de pedidos");
            return;
        }
        for (int i = 0; i<cantidad; i++){
            Pedido pedido = colaPreparando.remove();
            pedido.modificarEstado(EstadoPedido.SERVIDO);
            arrayServidos.add(pedido);
//            Faltaria cambiar el estado de la mesa a servida, necesito la instancia de esa mesa para poder                      modificar el estado
        }
        System.out.println("Pedidos entregados correctamente");
    }

    public static void main(String[] args) {
        /*Ejemplos de platos*/
        String[] platos = {"guiso", "estofado", "empanadas", "matambre"};

        /*Ejemplo de numeros de las mesas*/
        int[] mesas = new int[2];
        for (int i = 0; i < 2; i++) {
            mesas[i] = i+1;
        }

        GestionPedidos gestionPedidos = new GestionPedidos(mesas,platos);

    }
}
