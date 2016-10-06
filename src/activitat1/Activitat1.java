package activitat1;

import activitat1.Multiplicacion.Multiplicacio;
import java.util.*;
import java.util.concurrent.*;

public class Activitat1 {

    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        //Afegir el gestor
        Servidor servidor = new Servidor();
        //Llista amb multiplicacions aleatories
        List<Multiplicacio> llistaTasques = new ArrayList<Multiplicacio>();
        for (int i = 0; i < 10; i++) {
            Multiplicacio calcula = new Multiplicacio((int) (Math.random() * 10), (int) (Math.random() * 10));
            llistaTasques.add(calcula);
        }
        
        //Calcul de les multiplicacions amb fils
        List<Future<Integer>> llistaResultats = new ArrayList<>();
        Future<Integer> future = null;
        
        for (int i = 0; i < llistaTasques.size() ; i++) {
            future = servidor.ejecutaTarea(llistaTasques.get(i));
            llistaResultats.add(future);

        }
        while (!future.isDone()) {
            System.out.println("Esperando...");
            Thread.sleep(1000);
        }
        //Mostrem el resultat de la llista  
        for (int i = 0; i < llistaResultats.size(); i++) {
            Future<Integer> resultat = llistaResultats.get(i);
            try {
                System.out.println("Resultat tasca " + i + " és:" + resultat.get());
            } catch (InterruptedException | ExecutionException e) {
            }
        }
        //Terminar servidor
        servidor.terminaServidor();
    }
}
