grammar Calculator;

program: statement* EOF;

statement: expr NEWLINE                  # ExprStatement
    | assign NEWLINE                    # AssignmentStatement
    ;

assign: ID '=' expr                    # Assignment
    ;

expr: term ((MUL | DIV) term)*         # MulDiv
    ;

term: factor ((ADD | SUB) factor)*     # AddSub
    ;

factor: INT                              # Int
    | ID                                  # Variable
    | '(' expr ')'                       # Parens
    ;

MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
ASSIGN: '=';
ID: [a-zA-Z][a-zA-Z0-9]*;
INT: [0-9]+ ;
NEWLINE: '\r'? '\n' ;
WS : [ \t]+ -> skip ;
