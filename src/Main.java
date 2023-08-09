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
            String keyWord = filter(reader.next());
            String word;
            while (reader.hasNext()) {
                word = filter(reader.next());
                if (!keyWord.equals("") && !keyWord.equals(" ")){
                    keyWordList.foundWordSequence(keyWord, word);
                    //nextWordList.foundNextWord(keyWord); //adds to nextWordList
                }
                keyWord = word;
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


    public static void askUserInputOld(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please choose a starting word: ");
        String startWord = keyboard.nextLine();
        String nextWord;

        System.out.print(startWord+" ");

        for (int i =0; i<paragraphLength; i++) {
            nextWord = keyWordList.getRandomNextWord(startWord);
            System.out.print(nextWord+" ");
            startWord = nextWord;
        }
    }

    public static void askUserInput(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please choose a starting word: ");
        String word1 = keyboard.nextLine();
        String word2 = keyWordList.getRandomNextWord(word1);
        String keyWord, nextWord;

        for (int i =0; i<paragraphLength; i++) {
            keyWord = word1+" "+word2;
            nextWord = keyWordList.getRandomNextWord(word2);
            keyWordList.foundWordSequence(keyWord, nextWord);
            System.out.print(word1+" ");
            word1 = word2;
            word2 = nextWord;
        }

    }
}