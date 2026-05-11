package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Info:
 * Map: 8 feladat magyarázás:
 * Az ertek++ (poszt-inkrementálás) a növelés előtti értéket adja vissza
 * a put metódusnak, míg a ++ertek (pre-inkrementálás) előbb növeli az értéket,
 * és már a módosított eredményt adja át a Map-nek.
 * Ha meg akarjuk számolni egy kulcsból mennyi van pre-inkrementálással működik.
 */
public class FajlBeolvasas {
 
    private static List<Fuvar> fuvarlista = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        Path path = Path.of("fuvarok.csv");
        beolvas(path);
        
        System.out.println("1: Összes fuvar értéke?");
        System.out.println(feladat1() + " €");
        System.out.println("");
        
        System.out.println("2: Legdrágább fuvar rendszáma?");
        System.out.println("Rendszám: " + feladat2());
        System.out.println("");
        
        System.out.println("3: Legolcsóbb fuvar forintban?");
        System.out.println(feladat3() + " Ft");
        System.out.println("");
        
        System.out.println("4: Hány Kártyás fizetés volt?");
        System.out.println(feladat4() + " db.");
        System.out.println("");
        
        System.out.println("5: Minden fizetési mód meghatározott?");
        String valasz = feladat5() ? "Igen." : "Nem.";
        System.out.println(valasz);
        System.out.println("");
        
        System.out.println("6: Hány db autó van a rendszerben?");
        System.out.println(feladat6() + " db.");
        System.out.println("");
        
        System.out.println("7: Hányféle Fizetési mód van?");
        System.out.println(feladat7() + " féle.");
        System.out.println("");
        
        System.out.println("8: Melyik autó mennyi fuvart teljesített?");
        feladat8();
        System.out.println("");
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
    
    private static FizetesiMod szovegEnumm(String szoveg) {
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

    private static double feladat1() {
        double eur = 0;
        for(Fuvar fuvar : fuvarlista){
            eur += fuvar.getOsszegEuro();
        }
        return eur;
    }
    
    private static String feladat2() {
        int maxIndex = 0;
        for (int i = 1; i < fuvarlista.size(); i++) {
            if (fuvarlista.get(i).getOsszegEuro() > fuvarlista.get(maxIndex).getOsszegEuro()) {
                maxIndex = i;
            }
        }
        return fuvarlista.get(maxIndex).getRendszam();
    }
    
    private static double feladat3() {
        double min = fuvarlista.get(0).getOsszegEuro();
        for (Fuvar fuvar : fuvarlista) {
            if (fuvar.getOsszegEuro() < min) {
                min = fuvar.getOsszegEuro();
            }
        }
        return min * 354; // Példa árfolyam: 354 Ft/EUR (20260510)
    }
    
    private static int feladat4() {
        int db = 0;
        for (Fuvar fuvar : fuvarlista) {
            if (fuvar.getFizetesiMod() == FizetesiMod.KARTYA) {
                db++;
            }
        }
        return db;
    }
    
    private static boolean feladat5() {
        boolean mindegyik = true;
        for (Fuvar fuvar : fuvarlista) {
            if (fuvar.getFizetesiMod() == FizetesiMod.NINCS) {
                mindegyik = false;
                break;
            }
        }
        return mindegyik;
    }
    
    private static int feladat6() {
        Set<String> s = new HashSet<>();
        for(Fuvar fuvar : fuvarlista){
            s.add(fuvar.getRendszam());
        }
        return s.size();
    }
    
    private static int feladat7() {
        Set<FizetesiMod> modok = new HashSet<>();
        for (Fuvar fuvar : fuvarlista) {
            modok.add(fuvar.getFizetesiMod());
        }
        return modok.size();
    }
    
    private static void feladat8() {
        Map<String, Integer> melymenny = new HashMap<>();
        for (Fuvar fuvar : fuvarlista) {
            String kulcs = fuvar.getRendszam();
            if(melymenny.containsKey(kulcs)){
                int ertek = melymenny.get(kulcs);
                melymenny.put(kulcs, ++ertek);
            }else{ 
                melymenny.put(kulcs, 1);
            }
        }
        
        for (Map.Entry<String, Integer> entry : melymenny.entrySet()) {
            String kulcs = entry.getKey();
            Integer ertek = entry.getValue();
            System.out.printf("[%s] = %d\n", kulcs, ertek);
        }
    }
    
}