package activitat1;
import activitat1.Multiplicacion.Multiplicacio;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class Servidor {
    
    private final ThreadPoolExecutor executor;
    
    public Servidor(){
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
    }
    
    public Future<Integer> ejecutaTarea(Multiplicacio multiplicacion){
        Future<Integer> future = executor.submit(multiplicacion);
        return future;
    }
    
    public void terminaServidor(){
        executor.shutdown();
    }
    
}
