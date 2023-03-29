package al;

public enum TipoToken {
    // Crear un tipoToken por palabra reservada
    // Crear un tipoToken: identificador, una cadena y numero
    // Crear un tipoToken por cada "Signo del lenguaje" (ver clase Scanner)
    // Palabras clave:
    //Y, CLASE,
    // Final de cadena
    //EOF
    IDENTIFICADOR,CADENA,NUMERO,CLASE,EOF,Y,MAS,MENOS,DIV,MULT,
    FOR,WHILE,PAR_IZQ,PAR_DER,LLAVE_IZQ,LLAVE_DER,COMA,PUNTO,
    PUNTOCOMA,NEGACION,DIFERENTE,ASIGNAR,IGUAL,MENOR,MENORIGUAL,
    MAYOR,MAYORIGUAL,IF,TRUE,FALSE,RETURN,NULL,PRINT,FUNCTION,
    O,THIS,SUPER,VAR,THEN,INVALIDO
}
