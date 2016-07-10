Usage
-----

The key classes are

*   `FunctionalUnit` is a base class or template for a functional unit with one
    input pin and one output pin.
*   Use class `OutputPin` as public final member or private member with getter
    for output pins of a functional pins.
*   Use functional interface `InputPut` as public method for input pins of a
    functional pins.
*   Use class `Flow` as template to design a flow application. This class
    initialize and start the flow of the application


### Creating Functional Units

    public abstract class MyFunctionalUnit<I, O> {

        public final OutputPin<O> result = new OutputPin<>();

        public void process(I input) {
            // process input and publish output
            O output = ...
            result.publish(output);
        }

    }


### Creating Wires

With one value:

    MyFunctionalUnit<String, Integer> fu1 = new MyFunctionalUnit<>();
    MyFunctionalUnit<Integer, String> fu2 = new MyFunctionalUnit<>();
    fu1.result.connect(fu2::process());
    fu1.result.publish("Foo");

Without value:

    MyFunctionalUnit<String, Void> fu1 = new MyFunctionalUnit<>();
    MyFunctionalUnit<Void, String> fu2 = new MyFunctionalUnit<>();
    fu1.result.connect(fu2::process());
    fu1.result.publish(null);
