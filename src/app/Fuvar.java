package app;

import java.util.Objects;

public class Fuvar {
    private String rendszam;
    private int idoMp;
    private double osszegEuro;
    private String fizetesiMod;

    public Fuvar(String rendszam, int idoMp, double osszegEuro, String fizetesiMod) {
        this.rendszam = rendszam;
        this.idoMp = idoMp;
        this.osszegEuro = osszegEuro;
        this.fizetesiMod = fizetesiMod;
    }

    public String getRendszam() { return rendszam; }

    public int getIdoMp() {  return idoMp; }

    public double getOsszegEuro() { return osszegEuro; }

    public String getFizetesiMod() { return fizetesiMod; }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.rendszam);
        hash = 89 * hash + this.idoMp;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.osszegEuro) ^ (Double.doubleToLongBits(this.osszegEuro) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.fizetesiMod);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fuvar other = (Fuvar) obj;
        if (this.idoMp != other.idoMp) {
            return false;
        }
        if (Double.doubleToLongBits(this.osszegEuro) != Double.doubleToLongBits(other.osszegEuro)) {
            return false;
        }
        if (!Objects.equals(this.rendszam, other.rendszam)) {
            return false;
        }
        return Objects.equals(this.fizetesiMod, other.fizetesiMod);
    }

    @Override
    public String toString() {
        return "Fuvar{" + "rendszam=" + rendszam + ", idoMp=" + idoMp + ", osszegEuro=" + osszegEuro + ", fizetesiMod=" + fizetesiMod + '}';
    } 
}
