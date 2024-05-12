grammar Calculator;

expr: term ((MUL | DIV) term)*         # MulDiv
    ;

term: factor ((ADD | SUB) factor)*     # AddSub
    ;

factor: INT                              # Int
    | '(' expr ')'                       # Parens
    ;

MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';

INT: [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;
