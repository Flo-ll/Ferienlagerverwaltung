import java.util.Scanner;

public class Ferianlagerverwaltung {
    private MaterialGateway material;

    /**
     * Konstruktor der Klass Ferienlagerverwaltung
     */
    public Ferianlagerverwaltung() {
        material = new MaterialGateway();
        material.erzeugeTabelle();
    }

    /**
     * Diese Methode fügt neue Materialien hinzu.
     * 
     * @param artikelbezeichnung
     * @param zustand
     * @param vorhanden
     * @param benoetigt
     * @param beschreibung
     * @param preis
     */
    public void materialHinzufuegen(String artikelbezeichnung, String zustand, int vorhanden, int benoetigt,
            String beschreibung,
            Double preis) {
        material.hinzufuegen(artikelbezeichnung, zustand, vorhanden, benoetigt,
                beschreibung, preis);
    }

    /**
     * Diese Methode aktualisiert Materialien mit der übergebenen id.
     * 
     * @param id
     * @param artikelbezeichnung
     * @param zustand
     * @param vorhanden
     * @param benoetigt
     * @param beschreibung
     * @param preis
     */
    public void aktualisieren(int id, String artikelbezeichnung, String zustand, int vorhanden, int benoetigt,
            String beschreibung, Double preis) {
        material.aktualisiere(id, artikelbezeichnung, zustand, vorhanden, benoetigt, beschreibung, preis);
    }

    /**
     * Diese Methode entfernt Materialen mit der übergebenen id.
     * 
     * @param id
     */
    public void materialEntfernen(int id) {
        material.loesche(id);
    }

    /**
     * Diese Methode gibt alle Materialien aus.
     */
    public void materialAusgeben() {
        List<Artikel> materialListe = material.holeAlle();
        materialListe.toFirst();
        while (materialListe.hasAccess()) {
            System.out.println("id|Bezeichnung|Zustand|Vorhanden|Benötigt|Beschreibung|Preis");
            ArtikelAusgeben(materialListe.getContent());
            materialListe.next();
        }
    }

    /**
     * Diese Methode gibt Materialien mit der übergeben artikelbezeichnung zurück.
     * 
     * @param artikelbezeichnung
     */
    public void materialSuchen(String artikelbezeichnung) {
        Artikel a = material.hole(artikelbezeichnung);
        System.out.println("id|Bezeichnung|Zustand|Vorhanden|Benötigt|Beschreibung|Preis");
        if (a != null) {
            ArtikelAusgeben(a);
        }

    }

    /**
     * Diese Methode gibt Materialien mit dem übergebenen Status zurück.
     * 
     * @param status
     */
    public void materialMitStatus(String status) {
        List<Artikel> materialListe = material.holeMitStatus(status);
        materialListe.toFirst();
        System.out.println("Artikel mit Status: " + status);
        Double gesamtpreis = 0.0;
        System.out.println("id|Bezeichnung|Zustand|Vorhanden|Benötigt|Beschreibung|Preis");
        while (materialListe.hasAccess()) {
            Artikel a = materialListe.getContent();
            ArtikelAusgeben(a);
            gesamtpreis += a.gibPreis() * a.gibBenoetigt();
            materialListe.next();
        }

        System.out.println("Die fehlenden Artikel kosten " + gesamtpreis + " Euro.");

    }

    /**
     * Diese Methode überprüft, ob die eingegebenen Artikel vorhanden sind und
     * druckt eine Liste mit fehlenden Artikeln.
     */
    public void materialFuerSpielVorhanden() {
        List<String> benoetigteMaterialien = new List<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Geben sie eine Artikelbeschreibung ein. Geben sie 'q' ein, um die Eingabe zu beenden.");
            String eingabe = sc.nextLine();
            if (eingabe.equals("q")) {
                break;
            } else {
                benoetigteMaterialien.append(eingabe.trim());
                System.out.println(eingabe);
            }
        }
        benoetigteMaterialien.toFirst();
        System.out.println("Folgende Materialien fehlen:\n");

        while (benoetigteMaterialien.hasAccess()) {
            if (material.hole(benoetigteMaterialien.getContent()) == null) {
                System.out.println(benoetigteMaterialien.getContent());
            }
            benoetigteMaterialien.next();
        }

    }

    /**
     * Diese Methode druckt die Attribute des übergebenen Artikels in der Konsole aus.
     * @param a
     */
    private void ArtikelAusgeben(Artikel a) {
        System.out.println(a.gibId() + "|" + a.gibArtikelbezeichnung() + "|" + a.gibZustand() + "|" + a.gibVorhanden()
                + "|" + a.gibBenoetigt()
                + "|" + a.gibBeschreibung() + "|" + a.gibPreis());
    }
}
