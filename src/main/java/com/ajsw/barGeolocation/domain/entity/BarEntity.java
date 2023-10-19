package com.ajsw.barGeolocation.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bar", schema = "geobar", catalog = "")
public class BarEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "logo")
    private String logo;
    @Basic
    @Column(name = "latitud")
    private Double latitud;
    @Basic
    @Column(name = "longitud")
    private Double longitud;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BarEntity barEntity = (BarEntity) o;

        if (id != barEntity.id) return false;
        if (name != null ? !name.equals(barEntity.name) : barEntity.name != null) return false;
        if (description != null ? !description.equals(barEntity.description) : barEntity.description != null)
            return false;
        if (logo != null ? !logo.equals(barEntity.logo) : barEntity.logo != null) return false;
        if (latitud != null ? !latitud.equals(barEntity.latitud) : barEntity.latitud != null) return false;
        if (longitud != null ? !longitud.equals(barEntity.longitud) : barEntity.longitud != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (latitud != null ? latitud.hashCode() : 0);
        result = 31 * result + (longitud != null ? longitud.hashCode() : 0);
        return result;
    }
}
