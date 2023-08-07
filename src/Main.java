import java.io.File;
import java.util.Scanner;

public class Main {
    static KeyWordList keyWordList = new KeyWordList();
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

        //nextWordList.print();
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
}