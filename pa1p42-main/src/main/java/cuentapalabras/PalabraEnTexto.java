package cuentapalabras;

import java.util.Objects;

public class PalabraEnTexto {
    private String palabra;
    private int veces;

    public PalabraEnTexto(String p) {
        palabra = p.toUpperCase();
        veces = 1;
    }

    public void incrementa() {
        veces++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return palabra.equals(((PalabraEnTexto)obj).palabra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(palabra);
    }

    @Override
    public String toString() {
        return palabra + ": " + veces;
    }
}
