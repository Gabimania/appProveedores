package com.ceica.Modelos;

public class Pieza {
    private int id;
    private String name;
    private String color;
    private Categoria categoría;
    private Double precio;

    public Pieza(String name, String color, Double precio) {
        this.name = name;
        this.color = color;
        this.precio = precio;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Categoria getCategoría() {
        return categoría;
    }

    public void setCategoría(Categoria categoría) {
        this.categoría = categoría;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
