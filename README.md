# Integer Multiplication Algorithms

Implementation of integer multiplication algorithms in Java, with a focus on the Karatsuba multiplication algorithm.

## Karatsuba Multiplication

Karatsuba multiplication is a divide-and-conquer algorithm for multiplying large integers more efficiently than the traditional multiplication method.

It reduces the number of recursive multiplications by using algebraic decomposition.

## Implementation

* **Language:** Java
* **Algorithm:** Karatsuba Multiplication
* **Number Representation:** `BigInteger`

## Complexity

The Karatsuba algorithm has a time complexity of:

**O(n^log₂3) ≈ O(n^1.585)**

This improves upon the **O(n²)** complexity of the standard multiplication algorithm for sufficiently large integers.

## Purpose

This project was created to practice algorithm implementation, recursion, divide-and-conquer techniques, and large integer multiplication in Java.
