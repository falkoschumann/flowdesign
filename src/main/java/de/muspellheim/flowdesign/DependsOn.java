/*
 * Copyright (c) 2016 Falko Schumann
 * Released under the terms of the MIT License (MIT).
 */

package de.muspellheim.flowdesign;

/**
 * Marks an functional unit need a dependency.
 *
 * @param <T> the dependencies type.
 * @author Falko Schumann
 * @since 3.0
 */
public interface DependsOn<T> {

    /**
     * Inject the dependency in the functional unit.
     *
     * @param dependency the dependency.
     */
    void inject(T dependency);

}
