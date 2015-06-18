/*
 * Flow-Design for Java
 *
 * Copyright (c) 2015 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */
package de.muspellheim.flowdesign;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Markiert eine Methode zur Konfiguration einer Functional-Unit.
 *
 * <p>Die Annonation wird an einer Methode einer Functional-Unit notiert. Die Parameter dieser Methode sind
 * Konfigurationsobjekte. Diese Nethode wird während der Configure-Phase aufgerufen, um die Functional-Unit zu
 * konfigurieren.</p>
 */
@Target(value = {METHOD, CONSTRUCTOR, FIELD})
@Retention(value = RUNTIME)
@Documented
public @interface Configure {}
