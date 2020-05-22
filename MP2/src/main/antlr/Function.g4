grammar Function;
@header {package de.tukl.programmierpraktikum2020.mp2.antlr;}

expr:
      op=(EXP | LOG | SQRT | ABS) expr                      #expExpr
    | op=(SIN | COS | ACOS | ASIN | ATAN | TAN ) expr      #trigExp
    | lexpr=expr POW rexpr=expr                #powExpr
    | lexpr=expr op=(MULT | DIV) rexpr=expr    #multExpr
    | lexpr=expr op=(PLUS | MINUS) rexpr=expr  #addExpr
    | LPAREN expr RPAREN                       #parExpr

    | sgn=(PLUS | MINUS)? var                  #sgnValExpr
    ;

var: CONST  #constVar
   | ID     #idVar
   ;




CONST   : [0-9]+ ('.' [0-9]+)?;
ID      : 'x';
POW     : '^';
PLUS    : '+';
MINUS   : '-';
MULT    : '*';
DIV     : '/';
EXP     : 'exp';
LOG     : 'log';
SIN     : 'sin';
SQRT    : 'sqrt';
TAN     : 'tan';
ATAN    : 'atan';
ACOT    : 'acot';
ACOS    : 'acos';
ASIN    : 'asin';
ABS     : 'abs';
COS     : 'cos';
LPAREN  : '(';
RPAREN  : ')';
