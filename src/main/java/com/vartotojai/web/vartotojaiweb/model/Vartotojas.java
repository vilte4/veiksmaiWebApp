package com.vartotojai.web.vartotojaiweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vartotojas implements Comparable<Vartotojas> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    private String vardas;
    private String telNr;
    
    public Vartotojas() {}
    
	public Vartotojas(String vardas, String telNr) {
		this.vardas = vardas;
		this.telNr = telNr;
	}

	public Vartotojas(int id, String vardas, String telNr) {
		this.id = id;
		this.vardas = vardas;
		this.telNr = telNr;
	}


	@Override
	public String toString() {
		return "Vartotojas [ID=" + id + ", vardas=" + vardas + ", telefono numeris=" + telNr + "]";
	}

	@Override
	public int compareTo(Vartotojas o) {
		return Integer.compare(this.id, o.id);
	}    
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vartotojas other = (Vartotojas) obj;
		if (this.id != other.id)
			return false;
		return true;
	}

	public int getID() {
		return id;
	}
	public void setID(int ID) {
		if(ID < 0) {
            System.out.println("ERROR Vartotojas.setID() trying to set wrong value for ID=" + ID + ", value must be >=0");
            throw new NullPointerException();
        }
		this.id = ID;
	}
	
	public String getVardas() {
		return vardas;
	}
	
	public void setVardas(String vardas) {
		if(vardas == null) {
            System.out.println("ERROR Vartotojas.setVardas() trying to set wrong value for vardas=" + vardas + ", value must be not null");
            throw new NullPointerException();
        }
		this.vardas = vardas;
	}

	public String getTelNr() {
		return telNr;
	}

	public void setTelNr(String telNr) {
		this.telNr = telNr;
	}
}
