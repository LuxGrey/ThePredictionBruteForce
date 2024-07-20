# ThePredictionBruteForce

## Table of contents
* [What is this project?](#what-is-this-project)
* [What is 'The Prediction'?](#what-is-the-prediction)
* [So what is being proven or disproven here and why?](#so-what-is-being-proven-or-disproven-here-and-why)
* [How the test plays out](#how-the-test-plays-out)
* [How this program works](#how-this-program-works)
* [Verifying 'The Prediction'](#verifying-the-prediction)
* [Conclusion](#conclusion)

## What is this project?
This is just a small program that I decided to write on a whim to prove or disprove
the fact that 'The Prediction' holds up under any possible circumstances.

## What is 'The Prediction'?
'The Prediction' is a video that was uploaded to YouTube in the year 2007 by the channel
Quirkology (which has some nice content, might be worth checking out).
The video in question can be found [here](https://www.youtube.com/watch?v=pv9nleiFogc).
A more recent and higher-quality version of the same recording can be found [here](https://www.youtube.com/watch?v=DaWcL3oOd-E).

In the video, the viewer is presented with a sort of test that they can actively participate in.
They can see a 3x3 grid of post-it notes that have been stuck to a transparent surface.
Each of these post-its has a different symbol drawn on them, but that seems to be of no greater relevance
to the game.
The narrator of the video then explains the rules of the test:
* the viewer always needs to have their finger on the screen, pointing at one of the visible notes
(which shall from here on out be referred to as 'fields')
* they have to start on the field in the upper right corner that reads 'start'
* during the game, when prompted to move by the narrator, the viewer can move their finger
to any neighboring field that is directly above, below, right or left of the currently selected field
* diagonal movement is not allowed
* it is allowed to move to fields that have already been visited before

At this point the test starts.
The viewer is told to make a single move, placing them on one of two possible fields.
The narrator then removes the starting field, so that it is no longer a selectable option.
The game then continues for several more cycles.
During each cycle, the viewer is prompted to move their finger a specific number of times,
after which one of the fields that are still available is removed.
This will eventually end with only 1 field being left.

The claim made by the narrator here is that, no matter which choices the viewer makes, as long as
they play by the established rules, he can reliably predict which fields are **not** selected
at the end of each cycle when he decides to remove one of them.
The end result is expected to be that the viewer should end up on that last remaining field.

## So what is being proven or disproven here and why?
I was motivated to write this because I checked the comments on the newer of the two videos,
where I was surprised to find users who were claiming that, even though they had played by the rules,
they at some point ended up on a field that was then removed by the narrator.
Since I lack the mathematical skills (or patience, despite the irony of me creating an entire project
for this one specific problem) to prove or disprove 'The Prediction' with maths,
I decided to instead write an algorithm that would brute-force every possible sequence of moves
that could be made under the directions of the narrator and see if there is even a single instance
where 'The Prediction' is factually incorrect.

## How the test plays out
```
[] [] []
[] [] []
[] [] []
```
**1 move**

Stage 2:
```
[] []
[] [] []
[] [] []
```
**7 moves**

Stage 3:
```
[] []
   [] []
[] [] []
```
**3 moves**

Stage 4:
```
   []
   [] []
[] [] []
```
**7 moves**

Stage 5:
```

   [] []
[] [] []
```
**5 moves**

Stage 6:
```

   [] []
   [] []
```
**9 moves**

Stage 7:
```

   []
   [] []
```
**3 moves**

Stage 8:
```


   [] []
```
**1 move**

Stage 9 (final):
```


      []
```

## How this program works
Let's assume the following numbering for the 3x3 grid where the test is executed:
```
1 2 3
4 5 6
7 8 9
```

The program expects 3 command-line arguments:
* **available fields** a 9-digit sequence of 0s and 1s, where the nth digit corresponds to the field numbered with n;
0 means 'this field is not available for selection' and 1 means 'this field is available for selection'
* **starting fields** same principle as above, but this time 0 means 'this is not a possible starting field'
and 1 means 'this is a possible starting field'
* **number of moves** a positive integer that represents the number of moves that have to be made from
any starting field

The program will then simulate moving sequences, using the specified parameters and exploring all possible combination of starting conditions and choices.
At the end, it will print out a 3x3 grid, which expresses which fields can or cannot be the last occupied field at the end of a moving sequence.
An execution of the program can only simulate one stage of the game (stages are separated by the removal of fields).
To simulate all stages in succession, using the results of the prior for the next stage, one has to interpret the output at the end of a stage and turn it into the appropriate input for the next program execution that will then simulate the next stage.

**Example:**

Input: ``111011010 100000010 1``

Output:
```
OXO
_XO
_O_
```

Symbol explanation:
```
_ unavailable field
X field that can be occupied at the end of the sequence
O field that can never be occupied at the end of the sequence
```

## Verifying 'The Prediction'
Stage 1:

Input: ``111111111 001000000 1``

Output:
```
OXO
OOX
OOO
```

Stage 2:

Input: ``110111111 010001000 7``

Output:
```
XO_
OXO
XOX
```

Stage 3:

Input: ``110011111 100010101 3``

Output:
```
OX_
_OX
OXO
```

Stage 4:

Input: ``010011111 010001010 7``

Output:
```
_O_
_XO
XOX
```

Stage 5:

Input: ``000011111 000010101 5``

Output:
```
___
_OX
OXO
```

Stage 6:

Input: ``000011011 000001010 9``

Output:
```
___
_XO
_OX
```

Stage 7:

Input: ``000010011 000010001 3``

Output:
```
___
_O_
_XO
```

Stage 8:

Input: ``000000011 000000010 1``

Output:
```
___
___
_OX
```

## Conclusion
'The Prediction' is indeed correct in all possible cases and anyone who got a different result
while following the video apparently either made a mistake or was dishonest.
