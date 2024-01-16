package com.ceica.Modelos;

public class Proveedor {
    private int id;
    private String name;
    private String cif;
    private String direccion;
    private String localidad;
    private String provincia;

    public Proveedor() {
    }

    public Proveedor(String name, String cif) {
        this.name = name;
        this.cif = cif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "\n"+
                "id=" + id + "\n"+
                ", name='" + name + '\'' + "\n"+
                ", cif='" + cif + '\'' + "\n"+
                ", direccion='" + direccion + '\'' + "\n"+
                ", localidad='" + localidad + '\'' + "\n"+
                ", provincia='" + provincia + '\'' + "\n"+
                '}';
    }
}
