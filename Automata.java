package al;

import java.util.HashMap;
import java.util.Map;

public class Automata
{
    private Map<Estado,TipoToken> estadosFinales;

    public Automata(){
        estadosFinales = new HashMap<>();
        estadosFinales.put(Estado.Q1,TipoToken.IDENTIFICADOR);
        estadosFinales.put(Estado.Q3,TipoToken.CADENA);
        estadosFinales.put(Estado.Q4,TipoToken.NUMERO);
    }

    private Estado Transicion(Estado estadoActual, char entrada)
    {
        switch(estadoActual)
        {
            case INICIAL:
            {
                 if ((entrada >= 'A' && entrada <= 'Z') || (entrada >= 'a' && entrada <= 'z'))
                    return Estado.Q1;
                else if (entrada == '"')
                    return Estado.Q2;
                else if (entrada >= '0' && entrada <= '9')
                    return Estado.Q4;
                else if (entrada == '+' || entrada == '-')
                    return Estado.Q5;
                else
                    return Estado.INVALIDO;
            }
            case Q1: {
                return (entrada >= 'A' && entrada <= 'Z')
                        || (entrada >= 'a' && entrada <= 'z')
                        || (entrada >= '0' && entrada <= '9')
                        ? Estado.Q1 : Estado.INVALIDO;
            }

            case Q2: {
                return (entrada == '"') ? Estado.Q3 : Estado.Q2;
            }

            case Q4: {
                if (entrada == '.')
                    return Estado.Q6;
                else if (entrada >= '0' && entrada <= '9')
                    return Estado.Q4;
                else
                    return Estado.INVALIDO;
            }

            case Q5: {
                return (entrada >= '0' && entrada <= '9') ? Estado.Q4 : Estado.INVALIDO;
            }
            case Q6:

            case Q7: {
                return (entrada >= '0' && entrada <= '9') ? Estado.Q7 : Estado.INVALIDO;
            }

            default:
                return Estado.INVALIDO;
        }
    }
    public TipoToken evaluar(String str){
        Estado estado = Estado.INICIAL;
        for(char c : str.toCharArray()){
            estado = Transicion(estado, c);
        }
        return estadosFinales.getOrDefault(estado, TipoToken.INVALIDO);
    }

}