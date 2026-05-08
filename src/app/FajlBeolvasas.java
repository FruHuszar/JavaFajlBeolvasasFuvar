package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FajlBeolvasas {
 
    private static List<Fuvar> fuvarlista = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        Path path = Path.of("fuvarok.csv");
        beolvas(path);
        
        feladat1(); //kész
        System.out.println("");
        
        feladat2();
        System.out.println("");
        
        feladat3();
        System.out.println("");
        
        feladat4();
        System.out.println("");
        
        feladat5();
        System.out.println("");
        
        feladat6(); //kész
        System.out.println("");
        
        feladat7();
        System.out.println("");
        
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

    private static void feladat1() {
        System.out.println("1: Összes fuvar értéke?");
        double eur = 0;
        for(Fuvar fuvar : fuvarlista){
            eur += fuvar.getOsszegEuro();
        }
        System.out.println("Összes fuvar értéke: " + eur);
    }
    
    private static void feladat2() {
        System.out.println("2: Legdrágább fuvar rendszáma?");
        
        String valasz = true ? "Igen" : "Nem";
        System.out.println("Végig tudott futni/Összesre igaz? " + valasz);

    }
    
    private static void feladat3() {
        System.out.println("3: Legolcsóbb fuvar forintban?");
        
        String valasz = true ? "Igen" : "Nem";
        System.out.println("Végig tudott futni/Összesre igaz? " + valasz);

    }
    
    private static void feladat4() {
        System.out.println("4: Hány Kártáys fizetés volt?");
        
        String valasz = true ? "Igen" : "Nem";
        System.out.println("Végig tudott futni/Összesre igaz? " + valasz);

    }
    
    private static void feladat5() {
        System.out.println("5: Minden fzetési mód meghatározot?");
        
        String valasz = true ? "Igen" : "Nem";
        System.out.println("Végig tudott futni/Összesre igaz? " + valasz);

    }
    
    private static void feladat6() {
        System.out.println("6: Hány db autó van a rendszerben?");
        
        Set<String> s = new HashSet<>();
        
        
        for(Fuvar fuvar : fuvarlista){
            s.add(fuvar.getRendszam());
        }
        
        System.out.println("Autók a rendszerben: " + s.size() + " db.");

    }
    
    private static void feladat7() {
        System.out.println("7: Hányféle Fizetési mód van?");
        
        String valasz = true ? "Igen" : "Nem";
        System.out.println("Végig tudott futni/Összesre igaz? " + valasz);

    }
    
    private static void feladat8() {
        System.out.println("8: Melyk autó mennyi fuvart teljesített?");
        
        String valasz = true ? "Igen" : "Nem";
        System.out.println("Végig tudott futni/Összesre igaz? " + valasz);

    }
    
}
