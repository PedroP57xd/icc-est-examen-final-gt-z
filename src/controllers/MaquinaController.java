package controllers;

import java.util.*;

import models.Maquina;

public class MaquinaController {

    // =====================================
    // MÉTODO A
    // Filtra subred < umbral
    // Mantiene orden
    // =====================================
    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {

        Stack<Maquina> pila = new Stack<>();

        if (maquinas == null) return pila;

        for (Maquina m : maquinas) {

            if (m.getSubred() < umbral) {
                pila.push(m);
            }
        }

        return pila;
    }

    // =====================================
    // MÉTODO B
    // Ordenar por:
    // subred ASC
    // nombre ASC
    // =====================================
    public Set<Maquina> ordenarPorSubred(Stack<Maquina> pila) {

        TreeSet<Maquina> set = new TreeSet<>(

            (m1, m2) -> {

                // Primero subred
                int comp = Integer.compare(
                        m1.getSubred(),
                        m2.getSubred()
                );

                if (comp != 0) return comp;

                // Luego nombre
                return m1.getNombre()
                        .compareTo(m2.getNombre());
            }
        );

        if (pila == null) return set;

        while (!pila.isEmpty()) {
            set.add(pila.pop());
        }

        return set;
    }

    // =====================================
    // MÉTODO C
    // Agrupar por riesgo
    // TreeMap + Queue
    // =====================================
    public Map<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {

        TreeMap<Integer, Queue<Maquina>> mapa = new TreeMap<>();

        if (maquinas == null) return mapa;

        for (Maquina m : maquinas) {

            int riesgo = m.getRiesgo();

            // Crear cola si no existe
            mapa.putIfAbsent(riesgo, new LinkedList<>());

            // Agregar
            mapa.get(riesgo).offer(m);
        }

        return mapa;
    }

    // =====================================
    // MÉTODO D
    // Explotar grupo más grande
    // Empate → mayor riesgo
    // =====================================
    public Stack<Maquina> explotarGrupo(
            Map<Integer, Queue<Maquina>> mapa) {

        Stack<Maquina> pila = new Stack<>();

        if (mapa == null || mapa.isEmpty())
            return pila;

        int maxCantidad = -1;
        int mejorRiesgo = -1;

        Queue<Maquina> mejorGrupo = null;

        // Buscar grupo ganador
        for (Map.Entry<Integer, Queue<Maquina>> entry
                : mapa.entrySet()) {

            int riesgo = entry.getKey();
            Queue<Maquina> cola = entry.getValue();

            int tamaño = cola.size();

            if (tamaño > maxCantidad ||

                (tamaño == maxCantidad &&
                riesgo > mejorRiesgo)) {

                maxCantidad = tamaño;
                mejorRiesgo = riesgo;
                mejorGrupo = cola;
            }
        }

        // Pasar a Stack (LIFO)
        while (!mejorGrupo.isEmpty()) {
            pila.push(mejorGrupo.poll());
        }

        return pila;
    }
}
