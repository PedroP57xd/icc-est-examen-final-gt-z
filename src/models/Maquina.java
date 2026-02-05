package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Maquina {

    private String nombre;
    private String ip;
    private List<Integer> codigos;

    private int subred;   // campo calculado
    private int riesgo;   // campo calculado

    // =============================
    // CONSTRUCTOR
    // =============================
    public Maquina(String nombre, String ip, List<Integer> codigos) {

        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;

        this.subred = calcularSubred();
        this.riesgo = calcularRiesgo();
    }

    public Maquina() {
    }

    // =============================
    // CÁLCULOS
    // =============================

    // Cuarto octeto
    private int calcularSubred() {

        if (ip == null) {
            throw new IllegalArgumentException("IP nula");
        }

        String[] partes = ip.split("\\.");

        if (partes.length != 4) {
            throw new IllegalArgumentException("IP inválida");
        }

        return Integer.parseInt(partes[3]);
    }

    // Riesgo según README
    private int calcularRiesgo() {

        if (codigos == null || nombre == null) return 0;

        int suma = 0;

        // Sumar divisibles por 3
        for (int c : codigos) {

            if (c % 3 == 0) {
                suma += c;
            }
        }

        // Caracteres únicos sin espacios
        Set<Character> unicos = new HashSet<>();

        for (char ch : nombre.replace(" ", "").toCharArray()) {
            unicos.add(ch);
        }

        int cantidadUnicos = unicos.size();

        return suma * cantidadUnicos;
    }

    // =============================
    // GETTERS
    // =============================

    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        return subred;
    }

    public int getRiesgo() {
        return riesgo;
    }
}
