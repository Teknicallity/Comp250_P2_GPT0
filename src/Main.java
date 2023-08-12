import java.io.File;
import java.util.Scanner;

public class Main {
    static KeyWordList keyWordList = new KeyWordList();
    static final int paragraphLength = 100;
    static int nOrder = 0;

    public static void main(String[] args) {
        if (args.length > 0){
            intake(args[0]);
        } else {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter filename: ");
            String file = keyboard.nextLine();
            intake(file);
        }

        keyWordList.print();

        askUserInput(); //asks and prints
    }

    public static void intake(String filename){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("How many linked words should the text be based on?: ");
        nOrder = keyboard.nextInt();
        keyboard.nextLine();

        StringBuilder builder = new StringBuilder();
        try {
            Scanner reader = new Scanner(new File(filename));
            String tmp;
            for (int i = 0; i<nOrder; i++) {
                builder.append(filter(reader.next()));
                if (i<nOrder - 1){
                    builder.append(" ");
                }
            }
            String nextWord, keyWord;
            keyWord = builder.toString();
            while (reader.hasNext()) {

                nextWord = filter(reader.next());
                if (!keyWord.equals(" ")) {
                    keyWordList.foundWordSequence(keyWord, nextWord);
                    //nextWordList.foundNextWord(keyWord); //adds to nextWordList
                }
                tmp = keyWord;
                keyWord = keyWord.substring(keyWord.indexOf(" ")+1);
                if (tmp.equals(keyWord)) {
                    keyWord = nextWord;
                } else {
                    keyWord = keyWord + " " + nextWord;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String filter(String word){
        StringBuilder modifiedStr = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.toLowerCase().charAt(i);
            if (c >= 'a' && c <= 'z' )
                modifiedStr.append(c);
        }
        return modifiedStr.toString();
    }

    public static void askUserInput(){
        Scanner keyboard = new Scanner(System.in);
        if (nOrder>1){
            System.out.print("Please choose "+nOrder+" words to start: ");
        } else {
            System.out.print("Please choose one word to start: ");
        }
        String startWords = keyboard.nextLine();
        if (startWords.split(" ").length <1){
            System.out.println("Not a word pair");
            return;
        }
        String nextWord;

        System.out.print(startWords+" ");

        String tmp;
        int printCount = 0;
        for (int i = 0; i < paragraphLength; i++) {
            if (printCount>20){
                System.out.println();
                printCount = 0;
            }
            nextWord = keyWordList.getRandomNextWord(startWords);
            System.out.print(nextWord + " ");
            printCount++;

            tmp = startWords;
            startWords = startWords.substring(startWords.indexOf(" ")+1);
            if (tmp.equals(startWords)) {
                startWords = nextWord;
            } else {
                startWords = startWords + " " + nextWord;
            }
        }
        System.out.println();
    }
}