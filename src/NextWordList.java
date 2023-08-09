import java.util.Random;

public class NextWordList {
    private Element start, end;
    private int size;
    //nested class that handles a single string  element
    private static class Element {
        public Element (String s) {
            word = s;
            count = 1;
            next = null;
        }
        String word;
        int count;
        Element next;
    }

    public NextWordList(){
        start = null;
        end = null;
        size = 0;
    }

    public void foundNextWord(String nextWord){ //finds element and increases count or adds it to list
        Element capsule = new Element(nextWord);
        if (start == null){ //takes care of no elements in list
            start = capsule;
            end = capsule;
            return;
        }
        Element current = start;
        //loops through all elements, if nextWord is found, increment
        while (current != null) {
            if (current.word.equals(nextWord)) {
                current.count++;
                return;
            }
            current = current.next;
        }

        //if not found, add to list
        end.next = capsule; //set the current end's next to capsule
        end = capsule; //set the current end element to be capsule
        size++;
    }

    public void print(){
        //prints out the entire list to the console
        Element current = start;
        while(current != null)
        {
            System.out.println(current.word+" "+current.count);
            current = current.next;
        }
    }

    public String toString(){
        Element current = start;
        StringBuilder strBuild = new StringBuilder();
        while(current != null) {
            strBuild.append("\t")
                    .append(current.word)
                    .append(" ")
                    .append(current.count)
                    .append("\n");
            current = current.next;
        }
        return strBuild.toString();
    }



    public int getSize(){
        return size;
    }

    public String getRandomWord(){
        Random rand = new Random();
        Element current = start;
        int choice = 0;

        if (size != 0)
            choice = rand.nextInt(size); //random number up to size
        int runningCount = 0;
        while (runningCount < choice){
            current = current.next;
            runningCount++;
        }
        return current.word;
    }
}
