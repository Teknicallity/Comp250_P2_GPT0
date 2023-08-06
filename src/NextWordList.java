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

    public void foundNextWord(String nextWord){
        Element capsule = new Element(nextWord);
        if (start == null){ //takes care of no elements in list
            start = capsule;
            end = capsule;
            return;
        }
        Element current = start;
        //loops through all elements
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

    public int getSize(){
        return size;
    }
}
