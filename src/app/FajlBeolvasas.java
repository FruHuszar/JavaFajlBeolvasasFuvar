package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FajlBeolvasas {
    
    
    private static List<Fuvar> fuvarlista = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        
        Path path = Path.of("adatok.csv");
        beolvas(path);
        
        osszesKiir();
        
        System.out.println("\n\n");
        feladatok();
    }

    private static void beolvas(Path path) throws NumberFormatException, IOException {
        List<String> sorok = Files.readAllLines(path);
        for (String sor : sorok){
            String[] s = sor.split(";");
            
            String rendszam = s[0];
            int idoMp = Integer.parseInt(s[1]);
            double osszegEuro = Double.parseDouble(s[2]);
            FizetesiMod fizetesiMod = szovegEnumm(s[3]);
            
            Fuvar egyFuvar = new Fuvar(rendszam, idoMp, osszegEuro, fizetesiMod);
            fuvarlista.add(egyFuvar);
        }
    }
    
        public static FizetesiMod szovegEnumm(String szoveg) {
        return switch (szoveg) {
            case "kártya" -> FizetesiMod.KARTYA;
            case "készpénz" -> FizetesiMod.KESZPENZ;
            case "utalás" -> FizetesiMod.UTALAS;
            case "csekk" -> FizetesiMod.CSEKK;
            case "-" -> FizetesiMod.NINCS;
            default -> FizetesiMod.NINCS;
        };
    }
    
    private static void osszesKiir() {
        for (Fuvar fuvar : fuvarlista){
            System.out.println(fuvar);
        }
    }
    
    public static void feladatok() {
        int i = 0;
        int N = fuvarlista.size();
        while(i < N && true){
            i++;
        }
        
        String valasz = i<=N ? "Igen" : "Nem";
        System.out.println("Végig tudott futni/Összesre igaz? " + valasz);
    }
}
