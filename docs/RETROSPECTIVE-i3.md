
## Project Velocity
The following chart illustrates our project velocity for Iterations 1 and 2.

![Velocity Chart](velocity-chart.png)

## What has not been successful and how can we improve?
After discussion about the previous iterations, our team has come to the conclusion that certain implementations and practices of code seemed to consistently violate SOLID principles. This includes seeing overlap of layers, such as the sign-up activity class (UI layer) having a method that would check what role the user had selected (logic layer method); the validator classes being put under DSOs instead of the logic layer (SRP Violation) and finding code that was hard-coded (symptoms, age, date, etc.). The team discussed that team members would be more responsible with communicating the code that was made to ensure proper review of the code would take place to avoid any SOLID violation. Another aspect that was not that succesful in iteration two specifically was communication. What was seen throughout iteration two was a lack of good communication, which resulted in a lot of the work for iteration two being made a couple days before the iteration due date, which ultimately resulted in a generation of code that had smells to it. This could have easily been avoided by setting due dates or coding things early and making sure we were communicating the work finished/needed to be done through communication online or through meetings. A third problem was seen to be with documentation as there had been inconsistencies found in the time logged/time spent compared to iteration one. This can easily be improved upon by properly logging the time spent on a certain dev task.

## Determine concrete and realistic ways of improvement and decide how its success will be evaluated at the end of the iteration
