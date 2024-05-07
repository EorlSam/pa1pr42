package cuentapalabras;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ContadorPalabras {
    private List<PalabraEnTexto> palabras;

    public ContadorPalabras() {
        palabras = new ArrayList<>();
    }

    private int esta(String pal) {
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.get(i).equals(new PalabraEnTexto(pal))) {
                return i;
            }
        }
        return -1;
    }

    public PalabraEnTexto encuentra(String pal) {
        int index = esta(pal);
        if (index == -1) {
            throw new NoSuchElementException("No existe la palabra " + pal);
        }
        return palabras.get(index);
    }

    protected void incluye(String pal) {
        if (pal!=null) {
            int index = esta(pal);
            if (index != -1) {
                palabras.get(index).incrementa();
            } else {
                palabras.add(new PalabraEnTexto(pal));
            }
        }
    }

    private void incluyeTodas(String linea, String del) {
        for (String pal : linea.split(del)) {
            incluye(pal);
        }
    }

    public void incluyeTodas(String[] texto, String del) {
        for (String linea : texto) {
            incluyeTodas(linea, del);
        }
    }

    /*
    public void incluyeTodasFichero(String nomFich, String del) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nomFich))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                incluyeTodas(linea, del);
            }
        }
    }
    */

    public void incluyeTodasFichero(String nomFich, String del) throws IOException {
        try {
            String[] texto = Files.readAllLines(Paths.get(nomFich)).toArray(new String[0]);
            incluyeTodas(texto, del);
        } catch (IOException e) {
            System.err.println("Error reading file: " + nomFich);
        }
    }

    public void presentaPalabras(String fichero) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(fichero)) {
            presentaPalabras(pw);
        }
    }

    public void presentaPalabras(PrintWriter pw) {
        for (PalabraEnTexto palabra : palabras) {
            pw.println(palabra.toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < palabras.size(); i++) {
            if (i > 0) {
                sb.append(" - ");
            }
            sb.append(palabras.get(i).toString());
        }
        sb.append(']');
        return sb.toString();
    }
}
