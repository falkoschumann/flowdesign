Changelog for Flow-Design
=========================

Version 3.1.1-SNAPSHOT - [...]
---------------------------------

*   Placeholder for next release.


Version 3.1.0 - Deploy to Bintray
---------------------------------

*   The JAR is also an OSGi bundle.
*   Deploy to Bintray with optional profile.


Version 3.0.0 - Neustart des Flow-Frameworks
--------------------------------------------

*   Input-Pins, Output-Pins einschließlich deren Verknüpfung (`InputPin`
    `OutputPin`).
*   Basisklasse für eine Functional-Unit mit genau einem Input-Pin und
    Output-Pin (`FunctionalUnit`).
*   Basisklasse für die Initialisierung das Starten eines Flows (`Flow`).
*   Schnittstelle für die Phasen der Flow-Initalisierung (`DependsOn`,
    `Configurable`, `EntryPoint`).
*   `Tuple`, `Map`, `Join`, `Split`.


Version 2.0.0 - Replace interfaces with annotations
---------------------------------------------------

*   Interfaces DependsOn and Configurable removed.
*   Replaced interface DependsOn with annotation @Inject.
*   Replaced interface Configurable with annotation @Configure.
*   Naming connect and disconnect methods of functional units more clean.


Version 1.0.1 - Improve generics
--------------------------------

*   Improve using generics in FunctionalUnitSupport. Now methods accept
    collections as wire type also.


Version 1.0.0 - First release
-----------------------------

*   Contains Flow Design API and helper class for implementing functional units.
