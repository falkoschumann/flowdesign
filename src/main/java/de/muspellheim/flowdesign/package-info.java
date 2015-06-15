/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

/**
 * API und Implementierung für Flow-Design mit Java.
 *
 * <p>Ein Flow-Applikation wird mit den folgenden Schritten initialisiert und gestartet.</p>
 * <ol>
 * <li><em>Build:</em> Erzeuge alle Functional-Units.</li>
 * <li><em>Bind:</em> Verbinde die Functional-Units miteinander.</li>
 * <li><em>Inject:</em> Injiziere explizite Abhängigkeiten in die Functional-Units, die {@link de.muspellheim.flowdesign.DependsOn} implentieren.</li>
 * <li><em>Configure:</em> Konfiguriere die Functional-Units, die {@link de.muspellheim.flowdesign.Configurable} implementieren.</li>
 * <li><em>Run:</em> Starte den Fluss durch den Aufruf von {@link de.muspellheim.flowdesign.EntryPoint#run(java.lang.String[])} am Startpunkt.</li>
 * </ol>
 */
package de.muspellheim.flowdesign;
