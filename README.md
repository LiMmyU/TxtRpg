# Dokumentation TxtRpg

## Epics

- UI Erstellen & designen
- Spielmechaniken
- Zufallsprinzipien (Trefferchance, Schadensbereicht)

## Epic 1: UI

Das UI soll simpel gestaltet werden und die Interaktion mit dem Spiel wird hauptsächlich via Buttons und TextAreas erfolgen.
Sprich: Logs und Button um Aktionen wie "Angriff" oder "Statuserhöhungen" zu erhalten.
Animationen werden simpel gehalten.

## Epic 2: Spielmechaniken

### Charakterwachstum


Der Charakter sammelt während des Kampfes Erfahrungspunkte welche bei Erreichen eines höheren Levels in Skillpunkte umgewandelt werden.

Die Menge an Skillpunkten die erhalten werden, wird anhand eines Würfelsystems entschieden.

z.B: 5 5 2 2 2 = Full House (120 Skillpunkte)

Es gibt folgende Charakterstatistiken welche via Skillpunkte erhöht werden könen:

- Leben
- Schaden
- Trefferchance
- Kritische-Trefferchance
- Kritischer-Schaden

### Währungen

Einige Währungen werden im Spiel implementiert:

- Gold
- Skillpunkte

Gold wird in einem Shop ausgegeben um Ausrüstung zu kaufen.
Die Ausrüstung bringt diverse Statistik-Erhöhungen vom Spielcharakter.

Die Skillpunkte können direkt in Charakter-Statistiken investiert werden.

## Epic 3: Zufallsmechaniken

Zufallsmechaniken sind im gesamten Spiel präsent.
Trefferchancen werden per "Trefferchance" Statistik berechnet.
Das beinhaltet kritische treffer und Schaden.

Schaden wird in einem Bereich berechnet. z.B Der Charakter hat einen Schadensbereich von 5-13 und der Schaden wird
anhand dieses Bereichs berechnet.

Geld und Erfahrung werden anhand der Gegnerstärke berechnet und dem Spieler gutgeschrieben.

Skillpunkte werden anhand eines "Würfel-Casinos" vergeben.