### HW3 Feedback

**CSE 331 18sp**

**Name:** Khai T Tran (trank6)

**Graded By:** Leah Perlmutter (cse331-staff@cs.washington.edu)

### Score: 95/100
---

**Problem 3 - HolaWorld:** 5/5

- comment

**Problem 4 - RandomHello:** 9/10

- Avoid magic numbers, as in `nextInt(5)`. Should use `nextInt(greetings.length)` instead. (-1)


**Problem 5 - Testing (Fibonacci) Java Code with JUnit :** 5/5

- comment

**Problem 6 - Answering Questions about the (Fibonacci) Code:** 14/15

- Question 1: 5/5
  - comment
- Question 2: 5/5
  - comment
- Question 3: 4/5
  - You wrote `(n < 0)` but should have written `(n < 2)` (-1)

**Problem 7 - Implementation:** 62/65

- Ball.java: 5/5
  - comment
- BallContainer.java: 25/25
  - comment
- Box.java: 32/35
  - in `getBallsFromSmallest`, did not need `iter2` (-1)
  - in discussion of `getBallsFromSmallest`, TreeSet is an incorrect choice because it does not handle duplicates (it would consider two balls with the same volume to be the same ball). Instead use a different data structure that handles dulicates. (-2)