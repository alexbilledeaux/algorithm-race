# Algorithm Race

This is a Java pathfinding simulation using Branch&amp;Bound and Dijkstra's algorithm. The point of the project is to "race" the two algorithms against eachother to see if a simple Branch & Bound algorithm would ever be sufficient for extremely small scale pathfinding tasks when pitted against more robust pathinding solutions like Dijkstra's algorithm.

## Branch & Bound
The Branch and Bound algorithm is an extremely simple algorithm that explores every single state of a given problem and checks if that state is a solution to the problem. To reduce processing, you can reduce the number of solutions that Branch and Bound checks by checking periodically if it makes sense to continue down a given branch. For example, have we moved further away from the target destination? That probably does not get us closer to our goal for a pathfinding algorithm. We can stop exploring this set of moves as a potential solution. This algorithm has to process so many branches simultaneously that it is generally infeasible for large-scale problems without severe bounding. You can read more about it on [Geeks for Geeks](https://www.geeksforgeeks.org/branch-and-bound-algorithm/).

## Dijkstra's Algorithm
Dijkstra's Algorithm is a purpose-built solution for pathfinding. It finds the shortest path between one source location and every other location on the map (usually referred to as nodes). The main reason that this algorithm processes nodemaps so fast is because it only processes each node once. Branch & Bound may process the same node hundreds or thousands of time as it tries to find an optimum solution. You can read more about Dijkstra's Algorithm on [Wikipedia] (https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm).

## Results
On maps with fewer than 25x25 nodes a heavily-bounded Branch and Bound algorithm performed just as quickly as Dijksta's algorithm. Anything beyond that size rendered Branch and Bound completely infeasable.
