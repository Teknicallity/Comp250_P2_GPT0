public class KeyWordList {
    private Element start, end;
    private int size;
    //nested class that handles a single string  element
    private static class Element {
        public Element(String s) {
            value = s;
            nextWordList = null;
            next = null;

        }
        String value;
        NextWordList nextWordList;
        Element next;
    }

    public KeyWordList() {
        start = null;
        end = null;
        size = 0;
    }

    public void add(String s) {
        //adds s to the list
        Element capsule = new Element(s);
        capsule.nextWordList = new NextWordList();
        if (end == null) { //handles first in list
            start = capsule;
        }
        else { //handles adding to last point
            //go to last element, point it to me
            end.next=capsule;
            //redefine last to my capsule
        }
        end=capsule;
        size++;
    }

    public int addUnique(String s){
        int index = find(s);
        //if String s is not found, add it and return its index
        if (index == -1){
            add(s);
        }
        return index;
    }

    public void foundWordSequence(String keyword, String nextWord){
        int index = addUnique(keyword); //adds word if it is unique (-1), or return at index if found
        if (index == -1){
            index = size-1;
        }
        Element current = getElement(index);
        current.nextWordList.foundNextWord(nextWord);
    }

    public String get(int index){
    //returns the string at index, index
        Element current = start;
        for (int i = 0; i<index; i++){
            current = current.next;
        }
        return current.value;
    }

    private Element getElement(int index){
        Element current = start;
        for (int i = 0; i<index; i++){
            current = current.next;
        }
        return current;
    }

    public Element getElementWithString(String s){
        //returns the Element of the first instance with s
        Element current = start;
        while(current!=null){
            if (current.value.equals(s)){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int length(){
        //returns the number of elements in the list
        return size;
    }

    public int find(String s){
        //returns the index of the first instance of s
        int index = 0;
        Element current = start;
        while(current!=null){
            if (current.value.equals(s)){
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    public void print(){
        //prints out the entire list to the console
        Element e=start;
        while(e!=null)
        {
            System.out.println(e.value+":");
            System.out.println(e.nextWordList);
            e=e.next;
        }
    }

    public void set(int index, String value){
        Element current = start;
        for (int i = 0; i<index; i++){
            current = current.next;
        }
        current.value = value;
    }

    String getRandomNextWord(String keyword){
        Element current = getElementWithString(keyword);
        return current.nextWordList.getRandomWord();
    }

}
