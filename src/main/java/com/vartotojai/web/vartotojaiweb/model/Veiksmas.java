package com.vartotojai.web.vartotojaiweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veiksmas implements Comparable<Veiksmas> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String reiksme;
    private int vartotojoID;
    private String date;

    public Veiksmas() {}

    public Veiksmas(String reiksme, int vartotojoID, String date) {
        this.reiksme = reiksme;
        this.vartotojoID = vartotojoID;
        this.date = date;
    }

    public Veiksmas(int id, String reiksme, int vartotojoID, String date) {
        this.id = id;
        this.reiksme = reiksme;
        this.vartotojoID = vartotojoID;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Veiksmas [ID=" + id + ", veiksmas=" + reiksme + ", vartotojoID=" + vartotojoID + ", date=" + date +"]";
    }

    @Override
    public int compareTo(Veiksmas p) {
        if(this.vartotojoID > p.vartotojoID)
            return 1;
        else if(this.vartotojoID < p.vartotojoID)
            return -1;
        else {
            if(this.id > p.id) return 1;
            else if(this.id < p.id) return -1;
            else return this.date.compareTo(p.date);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + id;
        result = prime * result + vartotojoID;
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
        Veiksmas other = (Veiksmas) obj;

        if (vartotojoID == other.vartotojoID) {
            if (id == other.id) {
                if (date == null || other.date == null)
                    return false;
                if (date.equals(other.date))
                    return true;
            }
        }
        return false;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getReiksme() {
        return reiksme;
    }

    public void setReiksme(String reiksme) {
        this.reiksme = reiksme;
    }

    public int getVartotojoID() {
        return vartotojoID;
    }

    public void setVartotojoID(int vartotojoID) {
        this.vartotojoID = vartotojoID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}