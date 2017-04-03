package common;

import java.util.Date;

public class Delegacion {

    int id_del;
    String nombre;
    int id_jefe;
    String localizacion;

    public Delegacion(){}

    public int getId_del() {
        return id_del;
    }

    public void setId_del(int id_del) {
        this.id_del = id_del;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_jefe() {
        return id_jefe;
    }

    public void setId_jefe(int id_jefe) {
        this.id_jefe = id_jefe;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
