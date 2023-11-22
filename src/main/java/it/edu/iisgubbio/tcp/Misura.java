package it.edu.iisgubbio.tcp;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(indexes = { @Index(name="indice_ts", columnList = "ts") })
public class Misura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @CreationTimestamp
    LocalDateTime ts;
    String fornitore;
	double tensione;
    double corrente;
    double potenza;
    double rpm;
    
    public Misura() {}

	public Misura(String fornitore, double tensione, double corrente, double potenza, double rpm) {
		super();
		this.fornitore = fornitore;
		this.tensione = tensione;
		this.corrente = corrente;
		this.potenza = potenza;
		this.rpm = rpm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getTs() {
		return ts;
	}

	public void setTs(LocalDateTime ts) {
		this.ts = ts;
	}
	
	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}

	public double getTensione() {
		return tensione;
	}

	public void setTensione(double tensione) {
		this.tensione = tensione;
	}

	public double getCorrente() {
		return corrente;
	}

	public void setCorrente(double corrente) {
		this.corrente = corrente;
	}

	public double getPotenza() {
		return potenza;
	}

	public void setPotenza(double potenza) {
		this.potenza = potenza;
	}
	
	public double getRpm() {
		return rpm;
	}

	public void setRpm(double rpm) {
		this.rpm = rpm;
	}
    
}