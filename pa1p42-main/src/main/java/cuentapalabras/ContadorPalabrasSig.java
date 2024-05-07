package cuentapalabras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContadorPalabrasSig extends ContadorPalabras {
    private List<String> noSignificativas;
    public ContadorPalabrasSig() {
        noSignificativas = new ArrayList<>();
    }

    public void leeArrayNoSig(String[] palsNS) {
        noSignificativas.clear();
        for (String palabra : palsNS) {
            if (!palabra.isEmpty()) {
                noSignificativas.add(palabra.toUpperCase());
            }
        }
    }

    public void leeFicheroNoSig(String filNoSig, String sep) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filNoSig))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                anyadePalabrasNoSignificativas(linea, sep);
            }
        }
    }

    private void anyadePalabrasNoSignificativas(String linea, String sep) {
        for (String pal : linea.split(sep)) {
            noSignificativas.add(pal.toUpperCase());
        }
    }

    @Override
    protected void incluye(String pal) {
        if (noSignificativas.contains(pal.toUpperCase())) {
            return;
        }
        super.incluye(pal);
    }
}
