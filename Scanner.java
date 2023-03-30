package al;

import java.util.*;

public class Scanner {

    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    private int linea = 1;

    private static final Map<String, TipoToken> palabrasReservadas;
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("y", TipoToken.Y);
        palabrasReservadas.put("clase", TipoToken.CLASE);
        palabrasReservadas.put("ademas", TipoToken.THEN);
        palabrasReservadas.put("falso", TipoToken.FALSE);
        palabrasReservadas.put("para", TipoToken.FOR);
        palabrasReservadas.put("fun", TipoToken.FUNCTION); //definir funciones
        palabrasReservadas.put("si", TipoToken.IF);
        palabrasReservadas.put("nulo", TipoToken.NULL);
        palabrasReservadas.put("o", TipoToken.O);
        palabrasReservadas.put("imprimir", TipoToken.PRINT);
        palabrasReservadas.put("retornar", TipoToken.RETURN);
        palabrasReservadas.put("super", TipoToken.SUPER);
        palabrasReservadas.put("este", TipoToken.THIS);
        palabrasReservadas.put("verdadero", TipoToken.TRUE);
        palabrasReservadas.put("var", TipoToken.VAR); //definir variables
        palabrasReservadas.put("mientras", TipoToken.WHILE);
    }

    Scanner(String source){
        this.source = source;
    }

    public List<Token> scanTokens(Map<Integer,String> lines){
        lines.forEach((nlinea,linea)->{
            Map<String,TipoToken> tokline = analizar(linea.replaceAll(" ", ""));
            tokline.forEach((valor,token)->tokens.add(new Token(token,valor,null,nlinea)));
        });
        tokens.add(new Token(TipoToken.EOF, "", null, linea));

        return tokens;
    }
    private Map<String, TipoToken> analizar(String line) {

        Map<String, TipoToken> lineaTokens = new HashMap<>();
        Automata automata = new Automata();

        for (String str : line.split(" ")) {
            if (palabrasReservadas.containsKey(str.toLowerCase()))
                lineaTokens.put(str, palabrasReservadas.get(str.toLowerCase()));
            else
                lineaTokens.put(str, automata.evaluar(str));
        }

        return lineaTokens;
    }
}

/*
Signos o símbolos del lenguaje:
(
)
{
}
,
.
;
-
+
*
/
!
!=
=
==
<
<=
>
>=
// -> comentarios (no se genera token)
/* ... * / -> comentarios (no se genera token)
Identificador,
Cadena
Numero
Cada palabra reservada tiene su nombre de token

 */