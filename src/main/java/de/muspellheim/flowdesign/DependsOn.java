package de.muspellheim.flowdesign;

public interface DependsOn<T> {

    void inject(T object);

}
