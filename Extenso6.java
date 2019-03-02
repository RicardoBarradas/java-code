/*
* Classe para "traduzir" inteiros cardinais para a sua notação por extenso.
*
*  Ex: para o input 1212, o output deverá ser "mil duzentos e doze"
*
*  Versão 6 - 02/03/2018 - neste momento receber inteiros compostos por até 66 algarismos.
*/

import java.math.BigInteger;

public class Extenso6 {

   public static void main(String[] args) {

      String inputVal = "15456151351346545654546545646546545646545645646545165545131311";

      boolean isNeg = false;
      if( inputVal.startsWith("-")) {
         isNeg = true;
         inputVal = inputVal.substring(1);
      }

      // Caçar caracteres não permitidos:
      char[] diagS = inputVal.toCharArray();

      int contarDigitos = 0;
      for(char algarismo: diagS) {

         if( ! Character.isDigit(algarismo)){
            System.out.println("*** EXITING *** A express\u00E3o contem caracteres n\u00E3o num\u00E9ricos.");
            return;
         }
         contarDigitos++;
      }

      // pseudo-info-stats
      System.out.println();
      BigInteger inteiro = new BigInteger(inputVal);
      String sepMilhares = String.format("%,d", inteiro);
      System.out.println(sepMilhares);
      System.out.println("Express\u00E3o composta por " + contarDigitos + " algarismos.");
      System.out.println();

      // Chamada ao método "nuclear": para triagem da grandeza e seguimento do fluxo
      System.out.println("Extenso: " + ( isNeg ? "menos " : "" ) + triagemInicial(inputVal));
   }

   public static String triagemInicial(String i) {

      String valorFinal;

      BigInteger inputVal = new BigInteger(i);

      if(inputVal.compareTo(BigInteger.valueOf(1_000_000)) < 0) {

         int inputValNum = Integer.parseInt(i);

         if(inputValNum < 20) {

            valorFinal = deZeroaDezanove(inputValNum);
         }
         else if(inputValNum <= 100) {

            valorFinal = menorQueCem(inputValNum);
         }
         else if(inputValNum <= 1000) {

            valorFinal = menorQueMil(inputValNum);
         }
         else {  // <1_000_000

            valorFinal = menorQueUmMilhao(inputValNum);
         }
      }
      else{  // >= 1_000_000

         BigInteger milhaoComparador = new BigInteger("1"+"0".repeat(6));
         BigInteger iterador = inputVal;
         int tamanhoGrandeza = 0;
         String extensoGrandeza = "1";

         while( iterador.compareTo(milhaoComparador) >= 0 ) {
            tamanhoGrandeza += 1;
            extensoGrandeza += "0".repeat(6);
            iterador = iterador.divide(milhaoComparador);
         }

         if(tamanhoGrandeza == 1) { // < 1_000_000_000_000, menor do que 1 Bilião

            valorFinal = metodoMilionario( inputVal.toString(), "milh", extensoGrandeza);

         }
         else if(tamanhoGrandeza == 2) { // < 1_000_000_000_000_000_000, menor do que 1 Trilião

            valorFinal = metodoMilionario( inputVal.toString(), "bili", extensoGrandeza);
         }
         else if(tamanhoGrandeza == 3) { // < 1_000_000_000_000_000_000_000_000, menor do que 1 Quatrilião

            valorFinal = metodoMilionario( inputVal.toString(), "trili", extensoGrandeza);
         }
         else if(tamanhoGrandeza == 4) { // < 1_000_000_000_000_000_000_000_000_000_000

            valorFinal = metodoMilionario( inputVal.toString(), "quatrili", extensoGrandeza);
         }
         else if(tamanhoGrandeza == 5) { // < 1_000_000_000_000_000_000_000_000_000_000_000_000

            valorFinal = metodoMilionario( inputVal.toString(), "quintili", extensoGrandeza);
         }
         else if(tamanhoGrandeza == 6) { // < 1_000_000_000_000_000_000_000_000_000_000_000_000_000_000

            valorFinal = metodoMilionario( inputVal.toString(), "sextili", extensoGrandeza);
         }
         else if(tamanhoGrandeza == 7) { // < 1_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000

            valorFinal = metodoMilionario( inputVal.toString(), "septili", extensoGrandeza);
         }
         else if(tamanhoGrandeza == 8) { // < 1_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000

            valorFinal = metodoMilionario( inputVal.toString(), "octili", extensoGrandeza);
         }
         else if(tamanhoGrandeza == 9) { // < 1_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000

            valorFinal = metodoMilionario( inputVal.toString(), "nonili", extensoGrandeza);
         }
         else if(tamanhoGrandeza == 10) { // < 1_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000_000

            valorFinal = metodoMilionario( inputVal.toString(), "decili", extensoGrandeza);
         }
         else {
            valorFinal = "Inteiro demasiado grande - ainda n\u00E3o suportado.";
         }
      }
      return valorFinal;

   }

   public static String deZeroaDezanove(int i){

      String valorFinal;

      switch(i) {
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

   public static String menorQueCem(int i) {

      String valorFinal;

      if(i%10 == 0) {
         valorFinal = deVinteaNoventaeNove(i);
      }
      else {
         valorFinal = deVinteaNoventaeNove(i - (i%10)) + " e "
               + deZeroaDezanove(i%10);
      }

      return valorFinal;
   }

   public static String deVinteaNoventaeNove(int i) {

      String valorFinal;

      switch(i) {
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

   public static String menorQueMil(int i) {

      String valorFinal;

      if(i%100 == 0)
         valorFinal = deCemaNoveNoveNove(i);
      else if(i<200)
         valorFinal = "cento e " + triagemInicial(String.valueOf(i%100));
      else
         valorFinal = deCemaNoveNoveNove(i - i%100) + " e " + triagemInicial(String.valueOf(i%100));

      return valorFinal;
   }

   public static String deCemaNoveNoveNove(int i) {

      String valorFinal;

      switch(i) {

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

   public static String menorQueUmMilhao(int i) {

      String valorFinal ;

      if(i%1000 == 0) {
         valorFinal = triagemInicial(String.valueOf(i/1000)) + " mil" ;
      }
      else if(i%1000 <= 100 || ((i%1000)%100) == 0){
         if(i/1000 == 1 ) {
            valorFinal = "mil e " + triagemInicial(String.valueOf(i%1000));
         }
         else {
            valorFinal = triagemInicial(String.valueOf(i/1000)) + " mil e " + triagemInicial(String.valueOf(i%1000));
         }
      }
      else {
         if(i/1000 == 1 ){
            valorFinal = "mil " + triagemInicial(String.valueOf(i%1000));
         }
         else {
            valorFinal = triagemInicial(String.valueOf(i/1000)) + " mil " + triagemInicial(String.valueOf(i%1000));
         }
      }
      return valorFinal;
   }

   public static boolean levaE(String grandeza) {
      // Método para verificar a necessidade de
      //  utilização de um "e" logo após a grandeza maior do inteiro.
      // Exs:
      // 1.000.001 = "um milhão e um" (verifica as condições) - devolve TRUE
      // 1.000.200 = "um milhão e duzentos" (verifica as condições) - devolve TRUE
      // 1.200.000 = "um milhão e duzentos mil" (verifica as condições) - devolve TRUE
      // 1.000.101 = "um milhão, cento e um" (NÃO verifica as condições - logo a seguir a milhão, leva uma vírgula) - devolve FALSE

      boolean comE = false;

      BigInteger grandezaA = new BigInteger(grandeza);
      BigInteger grandezaB = new BigInteger(grandeza);

      int powerVal = 0;
      while( grandezaB.compareTo(BigInteger.valueOf(1_000_000)) >= 0) {
         grandezaB = grandezaB.divide(BigInteger.valueOf(1_000_000));
         powerVal++;
      }

      BigInteger resto = new BigInteger("1");
      BigInteger comparador = new BigInteger("1000000"); // Um milhão
      comparador = comparador.pow(powerVal);
      resto = grandezaA.mod(comparador);

      // Teste Condição 1:  Resto da grandeza a dividir pelo comparador é menor ou igual a 100 (ex input: 1.0000.100)

      if(resto.compareTo(BigInteger.valueOf(100)) <= 0) {
         comE = true;
         return comE;
      }
      else {
         comE = false;
      }

      //  Condição 2

      BigInteger resto2 = new BigInteger("1");
      BigInteger comparador2 = new BigInteger("1");
      comparador2 = comparador.divide(BigInteger.valueOf(10));

      for( BigInteger z = new BigInteger("100000"),  x = new BigInteger("1000") ;
           z.compareTo(comparador2) <= 0 ;
           z = z.multiply(BigInteger.valueOf(1_000)), x = x.multiply(BigInteger.valueOf(10)) ) {

         resto2 = resto.mod(x);

         if( resto.compareTo(z) <= 0 && resto2.compareTo(BigInteger.valueOf(0)) == 0 ){
            comE = true;
            return comE;
         }
         else {
            comE = false;
         }
      }
      //  Condição 3

      BigInteger resto3 = new BigInteger("1");

      for( BigInteger w = new BigInteger("1000"),  y = new BigInteger("100") ;
           w.compareTo(comparador) <= 0 ;
           w = w.multiply(BigInteger.valueOf(1_000)), y = y.multiply(BigInteger.valueOf(1000)) ) {

         resto3 = resto.mod(y);

         if( resto.compareTo(w) <= 0 && resto3.compareTo(BigInteger.valueOf(0)) == 0 ){
            comE = true;
            return comE;
         }
         else {
            comE = false;
         }
      }
      return comE;
   }

   public static String metodoMilionario(String i, String preExt, String grand) {

      BigInteger grandeza = new BigInteger(grand);
      String singular = preExt + "\u00E3o", plural = preExt + "\u00F5es" ;

      String valorFinal;
      BigInteger inputVal = new BigInteger(i);

      BigInteger i2 = inputVal.divide(grandeza);
      BigInteger i3 = inputVal.mod(grandeza);

      if(i3.compareTo(BigInteger.valueOf(0)) == 0 ) {
         if(inputVal.compareTo(grandeza) == 0) {
            valorFinal = "um "+ singular;
         }
         else {
            inputVal = inputVal.divide(grandeza);
            valorFinal = triagemInicial(inputVal.toString()) + " " + plural;
         }
      }
      else if(levaE(i)) {
         if(i2.compareTo(BigInteger.valueOf(1)) == 0) {
            valorFinal = "um " + singular + " e " + triagemInicial(i3.toString());
         }
         else{
            valorFinal = triagemInicial(i2.toString()) + " " + plural + " e " + triagemInicial(i3.toString());
         }
      }
      else {
         if (i2.compareTo(BigInteger.valueOf(1)) ==0 ) {
            valorFinal = "um "+ singular + ", " + triagemInicial(i3.toString());
         }
         else{
            valorFinal = triagemInicial(i2.toString()) + " " + plural + ", " + triagemInicial(i3.toString());
         }
      }
      return valorFinal;
   }
}
