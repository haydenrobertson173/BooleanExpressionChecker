# BooleanExpressionChecker

A terminal-based Java program that validates and evaluates boolean expressions.  
The program prompts the user for the number of inputs and a boolean expression, then evaluates the expression across all possible input combinations (or user-provided inputs) to determine whether each case produces `true` or `false`.

---

## How It Works

- The user is prompted for the number of inputs in the expression
- The user is prompted for a boolean expression, which is validated before evaluation  
  - If the expression is invalid, the user is prompted again
- Accepted syntax:
  - `'` for NOT  
  - `+` for OR  
  - Concatenation for AND  
  - `(` `)` for grouping
- Variables must correspond to the number of inputs  
  - Example: number of inputs = 3 → valid variables are `A`, `B`, `C`
- The user selects an evaluation mode:
  - Auto mode: generates all `2^n` input combinations and evaluates each
  - Manual mode: allows the user to input `0` or `1` for each variable and evaluates the result
- After evaluation, the user can continue with a new expression or quit the program

---

## Purpose of the Program

This program was originally designed to verify results for a class project in a computer engineering course.  
It can also be used to generate a truth table for a given boolean expression.

---

## Possible Improvements

- Add a GUI or web-based interface
- Improve error handling and input validation
- Support additional operators (e.g., XOR, NAND, NOR)
- Refactor evaluation logic using postfix notation

---

## How to Run

-Compile the program:
  - javac BooleanExpressionCheck.java HelperFunctions.java

-Run the Program:
  - java BooleanExpressionCheck

---

## Author

Hayden Robertson 
Computer Science student  
GitHub: https://github.com/haydenrobertson173
