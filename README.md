[![Build Status](https://travis-ci.org/falkoschumann/java-flowdesign.svg?branch=develop)](https://travis-ci.org/falkoschumann/java-flowdesign)
[![Download](https://api.bintray.com/packages/falkoschumann/maven/java-flowdesign/images/download.svg)](https://bintray.com/falkoschumann/maven/java-flowdesign)

Flow Design for Java
====================

This implementation follows the idea of the following blog entries.

*   [Flow-Design Cheat Sheet – Part I, Notation][1]
*   [Flow-Design Cheat Sheet – Part II, Translation][2]

With an implementation of code kata *Bowling* from this blog entry:

*   [Flowing Bowling Game Kata I][3]

The following classes in folder `src/test/java` shows how to implement the flow
API from blog with Java 8:

*   `MethodsCheatSheet` shows how to transfer functional units into simple
    methods.
*   `DelegatesCheatSheet` shows how to transfer functional units into methods
     with delegation to return values.
*   `EventsCheatSheet` shows how to transfer functional units into classes with
    events.
*   `BowlingGame` shows how to implement the bowling kata with flow design.

Also there is a class `Program` shows how to implement a complete program.

For productive use there are following classes in the framework:

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


[1]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/03/19/flow-design-cheat-sheet-ndash-part-i-notation.aspx
[2]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/03/20/flow-design-cheat-sheet-ndash-part-ii-translation.aspx
[3]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/07/05/flowing-bowling-game-kata-i.aspx
