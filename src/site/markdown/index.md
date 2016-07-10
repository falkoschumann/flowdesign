Flow Design
-----------

With flow design objects are independent functional units. This functional units
communicate only by messages. A message is a data structure that flow from a
sender to a receiver. The sender obtain no return value synchronously. An answer
flow also as message from a sender to receiver.

This implementation follows the idea of the following blog entries.

*   [Flow-Design Cheat Sheet – Part I, Notation][1]
*   [Flow-Design Cheat Sheet – Part II, Translation][2]

With an implementation of code kata *Bowling* from this blog entry:

*   [Flowing Bowling Game Kata I][3]


### Usage

General instructions on how to use flow design can be found on the
[usage page](usage.html).

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


[1]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/03/19/flow-design-cheat-sheet-ndash-part-i-notation.aspx
[2]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/03/20/flow-design-cheat-sheet-ndash-part-ii-translation.aspx
[3]: http://geekswithblogs.net/theArchitectsNapkin/archive/2011/07/05/flowing-bowling-game-kata-i.aspx
