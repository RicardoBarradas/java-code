/*
 *  Extenso.java - Classe para "traduzir" inteiros cardinais para a sua notação por extenso.
 *  --------------------------------------------------------------------------------------------
 *  Ex: para o input String "1212", o output String deverá ser "mil duzentos e doze".
 *
 *  Versão 6 - 02/03/2019 - neste momento recebe inteiros compostos por até 66 algarismos.
 *  Versão 7 - 19/03/2019 - Acrescentado constructor overloaded para receber "varargs";
 *  - recebe um objecto tipo String[] e devolve um objecto do mesmo tipo
 *  - melhorias no código
 *  - comentários ao longo da classe
 *
 */

import java.math.BigInteger;

/*  Apenas para efeitos de teste - comment out no ficheiro principal.
class teste{

    public static void main(String[] arg) {

        System.out.println(Extenso.extenso("323"));
        System.out.println();
        String[] teste = new String[3];
        String[] teste2 = new String[3];
        String[] teste3 = new String[7];
        teste[0] = "333"; teste[1] = "444"; teste[2] = "555";
        teste2 = Extenso.extenso(teste);
        System.out.println(teste2[0]); System.out.println(teste2[1]); System.out.println(teste2[2]);
        System.out.println();
        teste3 = Extenso.extenso("125", "32", "-45", "0", "", "fdf", "343");
        for(int i =0; i < 7; i++ ) System.out.println(teste3[i]);
    }
}
*/

public class Extenso {

    /* Método "de entrada":
     * Por aqui passam todos os inputs, quer directos, quer vindos de outros métodos "overloaded".
     * Este método verifica a informação recebida e encaminha os pedidos para o método "triagemInicial".
     * (ou então rejeita o pedido, devolvendo uma mensagem de alerta...)
     */
    public static String extenso(String inputVal) {

        if(inputVal.isEmpty()) return "Foi passada uma express\u00E3o vazia.";

        boolean isNeg = false;
        if (inputVal.startsWith("-")) {
            isNeg = true;
            inputVal = inputVal.substring(1);
        }

        // Caçar caracteres não permitidos:
        char[] diagS = inputVal.toCharArray();
        for (char algarismo : diagS) {

            if (!Character.isDigit(algarismo)) {
                return "A express\u00E3o cont\u00E9m caracteres n\u00E3o num\u00E9ricos.";
            }
        }
        // Chamada ao método central de triagem da grandeza e seguimento do fluxo:
        return (isNeg ? "menos " : "") + triagemInicial(inputVal);
    }

    /* Método "Overloaded" - permite a passagem de vários argumentos sequenciais,
     * em alternativa a apenas um argumento. Ex:  extenso("3232", "433", "", "-232". "0")
     * Também permite receber directamente um Array do tipo String.
     * Devolve um Array do tipo String com o extenso de cada valor passado como argumento.
     */
    public static String[] extenso(String... inputVal) {

        String[] retVal2 = new String[inputVal.length];
        for(int i = 0; i < inputVal.length; i++ ){
            retVal2[i] = extenso(inputVal[i]);
        }
        return retVal2;
    }

    private static String triagemInicial(String i) {

        String valorFinal;

        BigInteger inputVal = new BigInteger(i);

        // Determinar se é maior ou menor que 1 milhão
        if (inputVal.compareTo(BigInteger.valueOf(1_000_000)) < 0) {

            int inputValNum = Integer.parseInt(i);

            if (inputValNum < 20) {

                valorFinal = deZeroaDezanove(inputValNum);
            } else if (inputValNum <= 100) {

                valorFinal = menorQueCem(inputValNum);
            } else if (inputValNum <= 1000) {

                valorFinal = menorQueMil(inputValNum);
            } else {  // <1_000_000

                valorFinal = menorQueUmMilhao(inputValNum);
            }
        } else {  // >= 1_000_000


            BigInteger milhaoComparador = new BigInteger("1" + "000000");
            BigInteger iterador = inputVal;
            int tamanhoGrandeza = 0;
            String extensoGrandeza = "1";

            while (iterador.compareTo(milhaoComparador) >= 0) {
                tamanhoGrandeza += 1;
                extensoGrandeza += "000000";
                iterador = iterador.divide(milhaoComparador);
            }

            String preExt;

            switch (tamanhoGrandeza) {

                case 1:    // < 1_000_000_000_000, menor do que 1 Bilião
                    preExt = "milh";
                    break;
                case 2:   // < 1_000_000_000_000_000_000, menor do que 1 Trilião
                    preExt = "bili";
                    break;
                case 3:   // < 1_000_000_000_000_000_000, menor do que 1 Quatrilião
                    preExt = "trili";
                    break;
                case 4:   // < 1 Quintilião ....
                    preExt = "quatrili";
                    break;
                case 5:   // < 1 Sextilião
                    preExt = "quintili";
                    break;
                case 6:   // < 1 Setptilião
                    preExt = "sextili";
                    break;
                case 7:   // < 1 Octilião
                    preExt = "septili";
                    break;
                case 8:   // < 1 Nonilião
                    preExt = "octili";
                    break;
                case 9:   // < 1 Decilião
                    preExt = "nonili";
                    break;
                case 10:   // < 1 Undecilião
                    preExt = "decili";
                    break;
            // Acrescentar aqui novas grandezas...
                default:
                    preExt = "nulo";
            }

            valorFinal = (preExt != "nulo"
                    ? metodoMilionario(inputVal.toString(), preExt, extensoGrandeza)
                    : "Inteiro demasiado grande - ainda n\u00E3o suportado.");
        }
        return valorFinal;
    }

    private static String deZeroaDezanove(int i) {


        String valorFinal;

        switch (i) {
            case 0:
                valorFinal = "zero";
                break;
            case 1:
                valorFinal = "um";
                break;
            case 2:
                valorFinal = "dois";
                break;
            case 3:
                valorFinal = "tr\u00EAs";
                break;
            case 4:
                valorFinal = "quatro";
                break;
            case 5:
                valorFinal = "cinco";
                break;
            case 6:
                valorFinal = "seis";
                break;
            case 7:
                valorFinal = "sete";
                break;
            case 8:
                valorFinal = "oito";
                break;
            case 9:
                valorFinal = "nove";
                break;
            case 10:
                valorFinal = "dez";
                break;
            case 11:
                valorFinal = "onze";
                break;
            case 12:
                valorFinal = "doze";
                break;
            case 13:
                valorFinal = "treze";
                break;
            case 14:
                valorFinal = "catorze";
                break;
            case 15:
                valorFinal = "quinze";
                break;
            case 16:
                valorFinal = "dezasseis";
                break;
            case 17:
                valorFinal = "dezassete";
                break;
            case 18:
                valorFinal = "dezoito";
                break;
            case 19:
                valorFinal = "dezanove";
                break;
            default:
                valorFinal = "BUG eating method deZeroaVinte";
        }
        return valorFinal;
    }

    private static String menorQueCem(int i) {

        String valorFinal;

        if (i % 10 == 0) {
            valorFinal = deVinteaNoventaeNove(i);
        } else {
            valorFinal = deVinteaNoventaeNove(i - (i % 10)) + " e "
                    + deZeroaDezanove(i % 10);
        }

        return valorFinal;
    }

    private static String deVinteaNoventaeNove(int i) {


        String valorFinal;

        switch (i) {
            case 20:
                valorFinal = "vinte";
                break;
            case 30:
                valorFinal = "trinta";
                break;
            case 40:
                valorFinal = "quarenta";
                break;
            case 50:
                valorFinal = "cinquenta";
                break;
            case 60:
                valorFinal = "sessenta";
                break;
            case 70:
                valorFinal = "setenta";
                break;
            case 80:
                valorFinal = "oitenta";
                break;
            case 90:
                valorFinal = "noventa";
                break;
            case 100:
                valorFinal = "cem";
                break;
            default:
                valorFinal = "A BUG is chewing method deVinteaNoventaeNove";
        }
        return valorFinal;
    }

    private static String menorQueMil(int i) {

        String valorFinal;

        if (i % 100 == 0)
            valorFinal = deCemaNoveNoveNove(i);
        else if (i < 200)
            valorFinal = "cento e " + triagemInicial(String.valueOf(i % 100));
        else
            valorFinal = deCemaNoveNoveNove(i - i % 100) + " e " + triagemInicial(String.valueOf(i % 100));

        return valorFinal;
    }

    private static String deCemaNoveNoveNove(int i) {


        String valorFinal;

        switch (i) {

            case 200:
                valorFinal = "duzentos";
                break;
            case 300:
                valorFinal = "trezentos";
                break;
            case 400:
                valorFinal = "quatrocentos";
                break;
            case 500:
                valorFinal = "quinhentos";
                break;
            case 600:
                valorFinal = "seiscentos";
                break;
            case 700:
                valorFinal = "setecentos";
                break;
            case 800:
                valorFinal = "oitocentos";
                break;
            case 900:
                valorFinal = "novecentos";
                break;
            case 1000:
                valorFinal = "mil";
                break;
            default:
                valorFinal = "A BUG is chewing method deCemaNoveNoveNove";
        }
        return valorFinal;
    }

    private static String menorQueUmMilhao(int i) {

        String valorFinal;

        if (i % 1000 == 0) {
            valorFinal = triagemInicial(String.valueOf(i / 1000)) + " mil";
        } else if (i % 1000 <= 100 || ((i % 1000) % 100) == 0) {
            if (i / 1000 == 1) {
                valorFinal = "mil e " + triagemInicial(String.valueOf(i % 1000));
            } else {
                valorFinal = triagemInicial(String.valueOf(i / 1000)) + " mil e " + triagemInicial(String.valueOf(i % 1000));
            }
        } else {
            if (i / 1000 == 1) {
                valorFinal = "mil " + triagemInicial(String.valueOf(i % 1000));
            } else {
                valorFinal = triagemInicial(String.valueOf(i / 1000)) + " mil " + triagemInicial(String.valueOf(i % 1000));
            }
        }
        return valorFinal;
    }

    private static String metodoMilionario(String i, String preExt, String grand) {

        BigInteger grandeza = new BigInteger(grand);
        String singular = preExt + "\u00E3o", plural = preExt + "\u00F5es";

        String valorFinal;
        BigInteger inputVal = new BigInteger(i);

        BigInteger i2 = inputVal.divide(grandeza);
        BigInteger i3 = inputVal.mod(grandeza);

        if (i3.compareTo(BigInteger.valueOf(0)) == 0) {
            if (inputVal.compareTo(grandeza) == 0) {
                valorFinal = "um " + singular;
            } else {
                inputVal = inputVal.divide(grandeza);
                valorFinal = triagemInicial(inputVal.toString()) + " " + plural;
            }
        } else if (levaE(i)) {
            if (i2.compareTo(BigInteger.valueOf(1)) == 0) {
                valorFinal = "um " + singular + " e " + triagemInicial(i3.toString());
            } else {
                valorFinal = triagemInicial(i2.toString()) + " " + plural + " e " + triagemInicial(i3.toString());
            }
        } else {
            if (i2.compareTo(BigInteger.valueOf(1)) == 0) {
                valorFinal = "um " + singular + ", " + triagemInicial(i3.toString());
            } else {
                valorFinal = triagemInicial(i2.toString()) + " " + plural + ", " + triagemInicial(i3.toString());
            }
        }
        return valorFinal;
    }

    private static boolean levaE(String grandeza) {
        /* Método para verificar a necessidade de
         *  utilização de um "e" logo após a grandeza maior do inteiro.
         *  Este método é chamado unicamente a partir do método "metodoMilionario".
         * Exs:
         *  1.000.001 = "um milhão e um" (verifica as condições) - devolve TRUE
         *  1.000.200 = "um milhão e duzentos" (verifica as condições) - devolve TRUE
         *  1.200.000 = "um milhão e duzentos mil" (verifica as condições) - devolve TRUE
         *  1.000.101 = "um milhão, cento e um" (NÃO verifica as condições - logo a seguir a milhão, leva uma vírgula) - devolve FALSE
         */
        boolean comE;

        BigInteger grandezaA = new BigInteger(grandeza);
        BigInteger grandezaB = new BigInteger(grandeza);

        int powerVal = 0;
        while (grandezaB.compareTo(BigInteger.valueOf(1_000_000)) >= 0) {
            grandezaB = grandezaB.divide(BigInteger.valueOf(1_000_000));
            powerVal++;
        }

        BigInteger resto;
        BigInteger comparador = new BigInteger("1000000"); // Um milhão
        comparador = comparador.pow(powerVal);
        resto = grandezaA.mod(comparador);

        // Teste Condição 1:  Resto da grandeza a dividir pelo comparador é menor ou igual a 100 (ex input: 1.0000.100)

        if (resto.compareTo(BigInteger.valueOf(100)) <= 0) {
            comE = true;
            return comE;
        } else {
            comE = false;
        }

        //  Condição 2

        BigInteger resto2;
        BigInteger comparador2;
        comparador2 = comparador.divide(BigInteger.valueOf(10));

        for (BigInteger z = new BigInteger("100000"), x = new BigInteger("1000");
             z.compareTo(comparador2) <= 0;
             z = z.multiply(BigInteger.valueOf(1_000)), x = x.multiply(BigInteger.valueOf(10))) {

            resto2 = resto.mod(x);

            if (resto.compareTo(z) <= 0 && resto2.compareTo(BigInteger.valueOf(0)) == 0) {
                comE = true;
                return comE;
            } else {
                comE = false;
            }
        }
        //  Condição 3

        BigInteger resto3;

        for (BigInteger w = new BigInteger("1000"), y = new BigInteger("100");
             w.compareTo(comparador) <= 0;
             w = w.multiply(BigInteger.valueOf(1_000)), y = y.multiply(BigInteger.valueOf(1000))) {

            resto3 = resto.mod(y);

            if (resto.compareTo(w) <= 0 && resto3.compareTo(BigInteger.valueOf(0)) == 0) {
                comE = true;
                return comE;
            } else {
                comE = false;
            }
        }
        return comE;
    }

}