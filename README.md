1. Title - Calculator

2. Project Overview

	a) About the team:

		GEI Students:
		- Miquel Garcia Martinez
		- Gerard Pascual Fontanilles
		- Nil Pinyol Mateu


	b) The language:

		We decided to develop a grammar that capable of using variables for creating a calculator.
		This language grammar describes a simple calculator language that can evaluate arithmetic expressions and handle variable assignments. 
		The grammar is written in ANTLR (ANother Tool for Language Recognition) format, which is used to generate parsers for processing 
		structured text data.

		The language defined by this grammar allows for:
		- Evaluating arithmetic expressions with the standard operators (+, -, *, /).
		- Assigning the result of expressions to variables.
		- Handling variable references within expressions.

		This project utilizes ANTLR version 4.13.1.

	c) The interpreter:

		This project uses an interpreter to process and execute code written in a specific language. The interpreter consists of several key 
		components and rules defined below:

		· Token Literal Names:
			These are the actual symbols or characters used in the language.
				- null: Represents undefined or non-applicable tokens.
				- '(', ')': Parentheses for grouping expressions.
				- '*', '/', '+', '-': Arithmetic operators for multiplication, division, addition, and subtraction.
				- '=': Assignment operator.

		· Token Symbolic Names:
			These are the symbolic names assigned to tokens in the language.
				- null: Undefined or non-applicable tokens.
				- MUL: Multiplication operator (*).
				- DIV: Division operator (/).
				- ADD: Addition operator (+).
				- SUB: Subtraction operator (-).
				- ASSIGN: Assignment operator (=).
				- ID: Identifier (variable names).
				- INT: Integer literals.
				- NEWLINE: Newline character.
				- WS: Whitespace.

		· Rule Names:
			These define the grammar rules for parsing the language.
				- program: The starting point of the code.
				- statement: A single statement in the program.
				- assign: An assignment operation.
				- expr: An expression involving operators.
				- term: A term within an expression (e.g., part of a multiplication or division).
				- factor: The smallest unit of an expression (e.g., a number or a variable).


	d) The solution:

		Firstly, we started with a grammar that allowed simple calculations (addition, substraction, multiplication and division)
		with numbers and tested the implementation with the tool "ANTLR Preview" (available with the ANTLR plugin in IntellIJ) that
		allowed us to see if the code worked properly.

		When we had that, we implemented the use of variables and a "main" class that allowed us to do tests.

		Finally, we implemented a simple GUI that uses the code made before to make it more user friendly.

		
		We need to point out that our solution can only ensure a correct result when brackets ("(" and ")") are used.
		For example, if we want to do "3+5*8" and get a result of 43, we need to write "3+(5*8)) even though it's not mandatory in most 
		calculators.

3. Contribution

	Overall, every member contributed in all tasks, but had more implications on some roles:

	- Miquel Garcia Martinez: Grammar and testing
	- Gerard Pascual Fontanilles: GUI and testing
	- Nil Pinyol Mateu: Correct use of the code and calculator variables


4. How to install and run the tool

	1) Install IntellIJ Community Version (if not installed yet)

		Available download at:
			https://www.jetbrains.com/es-es/idea/download/?section=windows
	
	2) Install the ANTLR plugin

		a) Click on Settings
		b) Click on Plugins
		c) Search for "ANTLR v4"
		d) Click on install

	3) Install the ANTLR dependencies on the project

		a) Click on Settings
		b) Click on Project Structure
		c) In the "Modules" tab, click on "Dependencies"
		d) Check that JUnit4 and JUnit5.8.1 are installed (used for testing)
		e) Remove the existing "antlr-4.13.1-complete.jar" if is detected
		f) Click on the "+" sign and then "1| Jar or Directories"
		g) Load the "antlr-4.13.1-complete.jar" delivered with the project		

5. How to use


