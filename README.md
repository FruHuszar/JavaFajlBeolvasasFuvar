# Java Halmazok és fájlbeolvasás OOP + Saját osztállyal

```java
/**
 * Info:
 * Map: 8 feladat magyarázás:
 * Az ertek++ (poszt-inkrementálás) a növelés előtti értéket adja vissza
 * a put metódusnak, míg a ++ertek (pre-inkrementálás) előbb növeli az értéket,
 * és már a módosított eredményt adja át a Map-nek.
 * Ha meg akarjuk számolni egy kulcsból mennyi van pre-inkrementálással működik.
 */
if(ezEgyMap.containsKey(kulcs)){
                int ertek = ezEgyMap.get(kulcs);
                ezEgyMap.put(kulcs, ++ertek); /*ertek++ nem működik*/
}                

 //Teljes feladat:
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
 ```
