public class MaterialGateway {
    private DatabaseConnector db;

    /**
     * Konstruktor der Klasse MaterialGateway
     */
    public MaterialGateway() {
        db = null;
    }

    /**
     * Diese Methode setzt die CREATE-Funktion um.
     * 
     * @param artikelbezeichnung
     * @param zustand
     * @param vorhanden
     * @param benoetigt
     * @param beschreibung
     * @param preis
     * 
     */
    public void hinzufuegen(String artikelbezeichnung, String zustand, int vorhanden, int benoetigt,
            String beschreibung, Double preis) {
        verbinde();
        db.executeStatement(
                "INSERT INTO materialien (artikelbezeichnung, zustand, vorhanden, benoetigt, beschreibung, preis) VALUES('"
                        + artikelbezeichnung + "','" + zustand + "','" + vorhanden + "','" + benoetigt + "','"
                        + beschreibung + "','" + preis + "')");
        beende();
    }

    /**
     * Diese Methode setzt die READ-Funktion um und gibt alle Artikel in der
     * Datenbak zurück.
     * 
     * @return
     */
    public List<Artikel> holeAlle() {
        verbinde();
        List<Artikel> aList = new List();
        db.executeStatement(
                "Select id, artikelbezeichnung, zustand, vorhanden, benoetigt, beschreibung, preis FROM materialien");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if (ergebnis != null) {
            for (int i = 0; i < ergebnis.getRowCount(); i++) {
                aList.append(new Artikel(Integer.parseInt(ergebnis.getData()[i][0]), ergebnis.getData()[i][1],
                        ergebnis.getData()[i][2],
                        Integer.parseInt(ergebnis.getData()[i][3]), Integer.parseInt(ergebnis.getData()[i][4]),
                        ergebnis.getData()[i][5], Double.parseDouble(ergebnis.getData()[i][6])));
            }
        }
        beende();
        return aList;
    }

    /**
     * Diese Methode setzt Die READ-Funktion um und gibt alle Artikel mit dem
     * übergebenen Status zurück.
     * 
     * @param status
     * @return
     */
    public List<Artikel> holeMitStatus(String status) {
        verbinde();
        List<Artikel> aList = new List();
        db.executeStatement(
                "Select id, artikelbezeichnung, zustand, vorhanden, benoetigt, beschreibung, preis FROM materialien WHERE zustand ='"
                        + status + "'");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if (ergebnis != null) {
            for (int i = 0; i < ergebnis.getRowCount(); i++) {
                aList.append(new Artikel(Integer.parseInt(ergebnis.getData()[i][0]), ergebnis.getData()[i][1],
                        ergebnis.getData()[i][2],
                        Integer.parseInt(ergebnis.getData()[i][3]), Integer.parseInt(ergebnis.getData()[i][4]),
                        ergebnis.getData()[i][5], Double.parseDouble(ergebnis.getData()[i][6])));
            }
        }
        beende();
        return aList;
    }

    /**
     * Diese Methode setzt die READ-Funktion um und gibt den ersten Artikel mit der
     * übergebenen Artikelbezeichnung zurück.
     * 
     * @param artikelbezeichnung
     * @return
     */
    public Artikel hole(String artikelbezeichnung) {
        verbinde();
        Artikel a = null;
        db.executeStatement(
                "SELECT id, artikelbezeichnung, zustand, vorhanden, benoetigt, beschreibung, preis FROM materialien WHERE artikelbezeichnung ='"
                        + artikelbezeichnung + "'");
        QueryResult ergebnis = db.getCurrentQueryResult();
        try {
            if (ergebnis != null) {
                a = new Artikel(Integer.parseInt(ergebnis.getData()[0][0]), ergebnis.getData()[0][1],
                        ergebnis.getData()[0][2],
                        Integer.parseInt(ergebnis.getData()[0][3]), Integer.parseInt(ergebnis.getData()[0][4]),
                        ergebnis.getData()[0][5], Double.parseDouble(ergebnis.getData()[0][6]));
            }
        } catch (Exception e) {
            return null;
        }
        beende();
        return a;
    }

    /**
     * Diese Methode setzt die UPDATE-Funktion um
     * 
     * @param id
     * @param artikelbezeichnung
     * @param zustand
     * @param vorhanden
     * @param benoetigt
     * @param beschreibung
     * @param preis
     */
    public void aktualisiere(int id, String artikelbezeichnung, String zustand, int vorhanden, int benoetigt,
            String beschreibung, Double preis) {
        verbinde();
        db.executeStatement(
                "UPDATE materialien SET artikelbezeichnung = '" + artikelbezeichnung + "', zustand = '" + zustand
                        + "', vorhanden = " + vorhanden + ",benoetigt = '" + benoetigt + "', beschreibung = '"
                        + beschreibung + "',preis = "
                        + preis + "WHERE ID =" + id);
        beende();
    }

    /**
     * Diese Funktion setzt die DELETE-Funktion um.
     * 
     * @param id
     */
    public void loesche(int id) {
        verbinde();
        db.executeStatement("DELETE FROM materialien WHERE id =" + id);
        beende();
    }

    /**
     * Diese Funktion erzeugt die Tabelle materialien, Wenn sie noch nicht
     * existiert.
     */
    public void erzeugeTabelle() {
        verbinde();
        db.executeStatement(
                "Create table if not exists materialien (id INTEGER PRIMARY KEY AUTOINCREMENT, artikelbezeichnung TEXT , zustand TEXT, vorhanden INTEGER, benoetigt INTEGER, beschreibung TEXT, preis DOUBLE)");
        beende();
    }

    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde() {
        if (db == null) {
            db = new DatabaseConnector("", 0, "materialien.db", "", "");
        }
    }

    /**
     * Diese Methode beendet die Verbindung zur Datenbank.
     */
    private void beende() {
        if (db != null) {
            db.close();
            db = null;
        }
    }
}
