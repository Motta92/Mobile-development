# Inheritance, overloading and overcharge. The Geometric library

In this exercise I created a java package that contains an abstract class called GeometricFigure, and 3 objects that inherit from it, which are Circle, Rectangle and Square. The following bullets ilustrates each step taken to complete this final Question of lab 1.

- Create a GeometricFigure, Circle, Rectangular and Square
classes. All figures have a name and a description so use
Inheritance to avoid code repetition.


- Circle contains the properties ratio and pi value,
rectangule contains the properties height and width and Square
has the property edge for each side.

- The GeometricFigure class should be abstract and contain
abstract methods caclulateArea and calculatePerimeter that each
class needs to override and implement.

- Overload circle caclulateArea method to receive a given
pi value to calculate the area instead of using an internal
field of the class.