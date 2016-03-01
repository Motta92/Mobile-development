# Lab 1 - Basic Java Programming

This lab covers the basics of Java programming, including open questions regarding java common knowledge, as well as implementation and tests. The questions are the following:

1. Explain the difference between a Map, List and Set on Java. Give examples(3) for the use of each one. 

2. Explain what is an Interface, an Abstract class and a static field. Provides code examples for each one. 

3. Performance test. Create unit test comparison and attach report output for 100, 1000, 100000 operations. Explain briefly each data structure implementation characteristics. a. Evaluate Insert, Remove and get element by index operations i. ArrayList ii. LinkedList iii. Vector b. Evaluate get, put and remove operations for: i. HashMap ii. LinkedHashMap 

4. Implementing the Strategy patterns. The requirement presented is to do an application which needs to order a list of Students based on differences rules, for example alphabetically by name, by age and order alphabetically by career. a. Create the Student class (create attributes) b. Create an Interface called IComparisonStretegy c. Create 3 comparison Strategies that you design. d. Create a class sorter that receives a Strategy and then filters the list based on the given rule. e. Provide unit test example for each designed strategy. 

5. Inheritance, overloading and overcharge. The Geometric library: a. Create a GeometricFigure, Circle, Rectangular and Square classes. All figures have a name and a description so use Inheritance to avoid code repetition. b. Circle contains the properties ratio and pi value, rectangule contains the properties height and width and Square has the property edge for each side. c. The GeometricFigure class should be abstract and contain abstract methods caclulateArea and calculatePerimeter that each class needs to override and implement. d. Overload circle caclulateArea method to receive a given pi value to calculate the area instead of using an internal field of the class.