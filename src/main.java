import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class main {

    //metode ord til tal
    public static int [] numberCypherEncoder (String alfabet, String incryptOrDecryptMessage){

            Scanner scanner = new Scanner(System.in);
            System.out.println(incryptOrDecryptMessage);

            String input = scanner.nextLine().toLowerCase(Locale.ROOT);

        int[] charatersToIndex = new int[input.length()];

        char[] inputsToCharacter = input.toCharArray();

            boolean isInputVallid = alfabet.contains(input);
           // if (isInputVallid == false){
             //   System.out.println("fejl");
           // } else if (isInputVallid == true) {

                //int[] charatersToIndex = new int[input.length()];

                //char[] inputsToCharacter = input.toCharArray();

                for (int i = 0; i < inputsToCharacter.length; i++) {

                    charatersToIndex[i] = alfabet.indexOf(inputsToCharacter[i]) + 1;
               // }
            }


        return charatersToIndex;
    }

    //metode tal til ord
    public static String numberCypherDecoder (String alfabet, String input){
        input = input;

        int toIndex = 0;
        int fromIndex = 0;
        char separatingChar = ',';
        int numberCounts =0;

        //finder antalet af kommaer og dermed antallet af indtastet ciffer (derfor der er indsat komma til sidst i inoputtet)
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == separatingChar){
                numberCounts++;
            }
        }

        int [] numbersOfNumberFromInput= new int[numberCounts];

        //omsætter inputStringen til et int array
        for (int j = 0; j < numberCounts; j++) {
            //finder første komma
            toIndex = input.indexOf(',');
            //tager fra index 0 til det første komma, passer den somm int ind i arrayet
            numbersOfNumberFromInput[j] = Integer.parseInt(input.substring(fromIndex, toIndex));
            //fjerner første ciffer og komma
            input = input.substring(toIndex + 1, input.length());
        }

        String decodedMessage = "";
        //tager værdien i arrayet og bruger til at peje på bogstavet i alfabetet
        //ved at bruge værdien som index, derefter tilføjes bogstavet til decodeMessage
        for (int i = 0; i < numbersOfNumberFromInput.length; i++) {
            decodedMessage += String.valueOf(alfabet.charAt(numbersOfNumberFromInput[i] - 1));
        }

        System.out.println(decodedMessage);

        return decodedMessage;
    }

    public static String ceaserEncoder(String alfabet){
        String encodeMessage = "Type in the message to encode (only characters from the alfabet and space)";
        int [] cypherEncoded = numberCypherEncoder(alfabet, encodeMessage);

        String cypherEncodedString = "";

        for (int i = 0; i < cypherEncoded.length; i++) {

            if (cypherEncoded[i] == 27){
                cypherEncoded[i] = 1;
            } else if (cypherEncoded[i] == 28) {
                cypherEncoded[i] = 2;
            } else if (cypherEncoded[i] == 29) {
                cypherEncoded[i] = 3;
            } else if (cypherEncoded[i] == 30) {
                cypherEncoded[i] = 30;
            } else {
                cypherEncoded [i] += +3;
            }
        }
        for (int i = 0; i < cypherEncoded.length; i++) {
            cypherEncodedString += cypherEncoded[i] + ",";
        }

        String ceaserEncodedString = numberCypherDecoder(alfabet,cypherEncodedString);

        return ceaserEncodedString;
    }

    public static String ceaserDecoder(String alfabet){
        String decodeMessage = "Type in the message to decode (only characters from the alfabet and space)";
        int [] cypherDencoded = numberCypherEncoder(alfabet, decodeMessage);
        String cypherDecodedString = "";

        for (int i = 0; i < cypherDencoded.length; i++) {

            if (cypherDencoded[i] == 1){
                cypherDencoded[i] = 27;
            } else if (cypherDencoded[i] == 2) {
                cypherDencoded[i] = 28;
            } else if (cypherDencoded[i] == 3) {
                cypherDencoded[i] = 29;
            } else if (cypherDencoded[i] == 30) {
                cypherDencoded[i] = 30;
            } else {
                cypherDencoded [i] += -3;
            }
        }
        for (int i = 0; i < cypherDencoded.length; i++) {
            cypherDecodedString += cypherDencoded[i] + ",";
        }

        String ceaserDecodedString = numberCypherDecoder(alfabet,cypherDecodedString);

        return ceaserDecodedString;
    }

    public static void main(String[] args) {
        String alfabetString = "abcdefghijklmnopqrstuvwxyzæøå ";
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {

        System.out.println("press 1 for encode, 2 decode or q for quit");
        String decodeOrEncode = scanner.nextLine().toLowerCase(Locale.ROOT);

            if (decodeOrEncode.equals("1")) {
                ceaserEncoder(alfabetString);
            } else if (decodeOrEncode.equals("2")) {
                ceaserDecoder(alfabetString);
            } else if (decodeOrEncode.equals("q")){
                done = true;
            } else {
                System.out.println("Invallid input, You have to choose between 1 and 2!!");
            }
        }
    }
}
