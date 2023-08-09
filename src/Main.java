import java.io.File;
import java.util.Scanner;

public class Main {
    static KeyWordList keyWordList = new KeyWordList();
    static final int paragraphLength = 100;
    //static NextWordList nextWordList = new NextWordList();
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
        try {
            Scanner reader = new Scanner(new File(filename));
            String word1 = filter(reader.next());
            String word2 = filter(reader.next());
            String nextWord, keyWord;
            while (reader.hasNext()) {
                keyWord = word1 + " " + word2;
                nextWord = filter(reader.next());
                if (/*!keyWord.equals("") && */!keyWord.equals(" ")){
                    keyWordList.foundWordSequence(keyWord, nextWord);
                    //nextWordList.foundNextWord(keyWord); //adds to nextWordList
                }
                word1 = word2;
                word2 = nextWord;
            } //while (reader.hasNext());

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
        System.out.print("Please choose a starting word pair: ");
        String startWords = keyboard.nextLine();
        if (startWords.split(" ").length != 2){
            System.out.println("Not a word pair");
            return;
        }
        String nextWord;

        System.out.print(startWords+" ");

        for (int i =0; i<paragraphLength; i++) {
            nextWord = keyWordList.getRandomNextWord(startWords);
            System.out.print(nextWord+" ");
            //startWords = nextWord;
            startWords = startWords.substring(startWords.indexOf(" ")+1,startWords.length());
            startWords = startWords + " " + nextWord;
        }
    }
}