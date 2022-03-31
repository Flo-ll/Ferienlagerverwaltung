public class Artikel {
    private String artikelbezeichnung;
    private String zustand;
    private int vorhanden;
    private int benoetigt;
    private String beschreibung;
    private double preis;
    private int id;

    /**
     * 
     * @param id
     * @param artikelbezeichnung
     * @param zustand
     * @param vorhanden
     * @param benoetigt
     * @param beschreibung
     * @param preis
     * Objekt, in dem ein Artikel gespeichert wird
     */
    public Artikel(int id, String artikelbezeichnung, String zustand, int vorhanden, int benoetigt, String beschreibung,
            double preis) {
        this.artikelbezeichnung = artikelbezeichnung;
        this.zustand = zustand;
        this.vorhanden = vorhanden;
        this.benoetigt = benoetigt;
        this.beschreibung = beschreibung;
        this.preis = preis;
        this.id = id;
    }

    /**
     * Gibt die id zurück.
     * @return int
     */
    public int gibId() {
        return this.id;
    }

    /**
     * Gibt die Artikelbezeichnung zurück.
     * @return String
     */
    public String gibArtikelbezeichnung() {
        return this.artikelbezeichnung;
    }

    /**
     * Gibt den Zustand zurück.
     * @return String
     */
    public String gibZustand() {
        return this.zustand;
    }

    /**
     * Gibt die Anzahl der vorhandenen Artikel zurück.
     * @return int
     */
    public int gibVorhanden() {
        return this.vorhanden;
    }

    /**
     * Gibt die Anzahl der Benötigten Artikel zurück.
     * @return int
     */
    public int gibBenoetigt() {
        return this.benoetigt;
    }

    /**
     * Gibt die Beschreibung des Artikels zurück.
     * @return String
     */
    public String gibBeschreibung() {
        return this.beschreibung;
    }

    /**
     * Gibt den Preis des Artikels zurück.
     * @return double
     */
    public double gibPreis() {
        return this.preis;
    }
}
