package common;

import java.util.Date;

public class Proyecto {

    int id_proyecto;
    String nombre;
    int horas_estimadas;
    int horas_reales;
    Date fecha_entrega;
    Date fecha_insercion;

    public Proyecto(){}

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras_estimadas() {
        return horas_estimadas;
    }

    public void setHoras_estimadas(int horas_estimadas) {
        this.horas_estimadas = horas_estimadas;
    }

    public int getHoras_reales() {
        return horas_reales;
    }

    public void setHoras_reales(int horas_reales) {
        this.horas_reales = horas_reales;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Date getFecha_insercion() {
        return fecha_insercion;
    }

    public void setFecha_insercion(Date fecha_insercion) {
        this.fecha_insercion = fecha_insercion;
    }
}
