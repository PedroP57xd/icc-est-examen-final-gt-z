package models;

import java.util.List;

public class Maquina {

    private String nombre;
    private String ip;
    private List<Integer> codigos;
    private int subred;
    private int riesgo;

    

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
    }
    
    public Maquina() {
    }

    private int calcularSubred(){
        String[] temp= ip.split(".",0);
        subred= Integer.parseInt(temp[3]);
        return subred;
    }
    private int calcularRiesgo(){
        codigos.add(nombre.length());
        

        return 0;
    }




    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public List<Integer> getCodigos() {
        return codigos;
    }
    public void setCodigos(List<Integer> codigos) {
        this.codigos = codigos;
    }
    public int getSubred() {
        return subred;
    }
    public void setSubred(int subred) {
        this.subred = subred;
    }
    public int getRiesgo() {
        return riesgo;
    }
    public void setRiesgo(int riesgo) {
        this.riesgo = riesgo;
    }

    
}