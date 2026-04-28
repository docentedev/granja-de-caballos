package com.granja.caballos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "caballos")
public class Caballo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String color;

    private Integer edad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_id", nullable = false)
    private TipoCaballo tipo;

    public Caballo() {}

    public Caballo(String nombre, String color, Integer edad, TipoCaballo tipo) {
        this.nombre = nombre;
        this.color = color;
        this.edad = edad;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public TipoCaballo getTipo() {
        return tipo;
    }

    public void setTipo(TipoCaballo tipo) {
        this.tipo = tipo;
    }
}
