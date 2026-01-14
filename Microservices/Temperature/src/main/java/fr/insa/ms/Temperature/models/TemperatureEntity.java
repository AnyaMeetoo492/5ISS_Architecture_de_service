package fr.insa.ms.Temperature.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperature")
public class TemperatureEntity {

    @Id
    @Column(name = "Date")
    private LocalDateTime date;

    @Column(name = "valeur")
    private int valeur;

    public TemperatureEntity() {}

    public TemperatureEntity(int valeur, LocalDateTime date) {
        this.date = date;
        this.valeur = valeur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
