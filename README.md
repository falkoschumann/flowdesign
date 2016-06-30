[![Build Status](https://travis-ci.org/falkoschumann/java-flowdesign.svg?branch=develop)](https://travis-ci.org/falkoschumann/java-flowdesign)
[![Download](https://api.bintray.com/packages/falkoschumann/maven/java-flowdesign/images/download.svg)](https://bintray.com/falkoschumann/maven/java-flowdesign)


Flow-Design for Java
====================

Dieses Projekt enthält eine Java-Implementation der Flow-API die in den
folgenden beiden Blogeinträgen vorgestellt wird:

  - [Flow-Design Cheat Sheet – Part I, Notation][1]
  - [Flow-Design Cheat Sheet – Part II, Translation][2]

Mit einer Implementierung der Code Kata *Bowling* aus diesem Blogeintrag:

  - [Flowing Bowling Game Kata I][3] (Fehlt leider noch)

Die folgenden Klassen im Ordner `src/test/java` zeigen wie die im Blog genannte
Flow-API mit Java 8 realsiert werden kann:

  - `MethodsCheatSheet` zeigt die Überführung von Functional-Units in Methoden.
  - `DelegatesCheatSheet` zeigt die Überführung von Functional-Units in Methoden
    mit Delegation für Rückgabewerten.
  - `EventsCheatSheet` zeigt die Überführung von Functional-Units in Ereignisse.

Ebenfalls im Ordner `src/test/java` befindet sich Klasse `Program` mit einem
kompletten Programm.

Für den produktiven Einsatz sind die Klassen im Ordner `src/main/java`.

  - `FunctionalUnit` ist eine Basisklasse für eine Functional-Unit mit genau
    einem Input-Pin und einem Output-Pin.
  - Die Klasse `OutputPin` dient als Output-Pin mit einem beliebige Namen.
  - Das Functional-Interface `InputPut` definiert einen Input-Pin beliebigen
    Namens.
  - Die Klasse `Flow` dient als Basis für die Initialisierung und das Starten
    eines Flows.

Für weitere Klassen und Details verweise ich auf die Dokumentation im JavaDoc.


[1]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/03/19/flow-design-cheat-sheet-ndash-part-i-notation.aspx
[2]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/03/20/flow-design-cheat-sheet-ndash-part-ii-translation.aspx
[3]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/07/05/flowing-bowling-game-kata-i.aspx
