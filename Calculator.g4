grammar Calculator;

expr: term (('*' | '/') term)*         # MulDiv
    | term (('+' | '-') term)*         # AddSub
    ;

term: INT                               # Int
    | '(' expr ')'                      # Parens
    ;

INT: [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;
