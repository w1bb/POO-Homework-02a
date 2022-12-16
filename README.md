# POO TV - Part 1 (POO-Homework-02a)

This repository is intended to store a complex user-webpage interaction simulation. It is part of a computer
science assignment (2nd year, 1st semester).

Full completion date: 16 Dec. 2022

Deadline: 17 Dec. 2022

This project will be available <a href="https://github.com/w1bb/POO-Homework-02a">on GitHub</a> once the deadline passes.
The original homework is available on <a href="https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1">our OCW</a>, but a
copy of the text will be provided in the repo.
## License

Once the deadline passes, this project will be available under the MIT license. For more info about the author of the
code, please check out <a href="https://v-vintila.com">my personal website</a>.

## Coding style choices

There are a few design choices I want to address:
* The methods and variables will follow
  <a href="https://www.oracle.com/java/technologies/javase/codeconventions-fileorganization.html">the Oracle</a> file
  organization convention, so:
    1) Class (static) variables: First the public class variables, then the protected, and then the private.
    2) Instance variables:First public, then protected, and then private.
    3) Constructors
    4) Methods: These methods should be grouped by functionality rather than by scope or accessibility. For example, a
       private class method can be in between two public instance methods. The goal is to make reading and understanding the
       code easier.

## Design patterns

Since there are many design patterns used throughout the project, this section shall keep track of them.

Every time the interpreter is asked to follow a request, a PageRequest is generated and sent over to the appropriate sub-interpreter, and then it is sent to the appropriate page.
This makes use of the **STRATEGY** design pattern. 

Pages are static and independent of the logged-in user, meaning the **SINGLETON** design pattern is correctly used.

A certain page can be generated (since singleton is used, it will simply be returned) using its name by making use of the **FACTORY** design pattern.

Page responses are also created after any action using the **BUILDER** design pattern.

## Documentation

In short, the control is given to an interpreter that "understands" the commands and translates them to actual user-webpage interaction.
A request is sent to the page that should resolve it (that page is determined using the "page" filed in the json files, and the conversion string-class is made using the PageFactory class).

In case of special pages, such as the "see details" page, there exists a special method called "afterEnter" that should be called when such a context change happens.
This method might redirect users to other webpages (see the "logout" page) or might output different information based on other fields (e.g. "see details").

Page responses are generated after any interaction with the webpages and they represent the means of communication with the interpreter / user.

This makes the flow of the program very easy to read and understand. For more info, please take a quick look at the
code, as it is **very** well documented.