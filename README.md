The java program is titled as "expressionconverter", run it and see how it running. you input your expression and the program sorts it into a prefix and postfix and displays the the expression in postfix and prefix
The workflow is lablled and "expression converter workflow". Check it to see how the the logic was used to design the java program.

Here is the pseudocode

ALGORITHM: Expression_Converter
INPUT: Infix String
OUTPUT: Postfix and Prefix Strings

-------------------------------------------------------
PART 1: THE CORE LOGIC (Infix to Postfix)
-------------------------------------------------------
1. INITIALIZE empty stack 'S' and empty string 'Result'
2. FOR each character 'C' in the expression:
    
    IF 'C' is an Operand (Letter or Number):
        - APPEND 'C' to 'Result'
        
    ELSE IF 'C' is an Opening Parenthesis '(':
        - PUSH 'C' onto stack 'S'
        
    ELSE IF 'C' is a Closing Parenthesis ')':
        - WHILE top of 'S' is not '(':
            - POP from 'S' and APPEND to 'Result'
        - POP and discard '(' from 'S'
        
    ELSE IF 'C' is an Operator (+, -, *, /):
        - WHILE 'S' is not empty AND Priority(S.top) >= Priority('C'):
            - POP from 'S' and APPEND to 'Result'
        - PUSH 'C' onto stack 'S'

3. WHILE stack 'S' is not empty:
    - POP remaining operators and APPEND to 'Result'

4. RETURN 'Result'

-------------------------------------------------------
PART 2: THE TRANSFORMATION (For Prefix)
-------------------------------------------------------
1. REVERSE the input Infix string
2. SWAP all '(' with ')' and vice-versa
3. RUN Part 1 (Infix to Postfix) on this modified string
4. REVERSE the final result to obtain the Prefix expression
