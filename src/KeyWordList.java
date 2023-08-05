public class KeyWordList {
    private Element start, end;
    private int size;
    //nested class that handles a single string  element
    private class Element
    {
        public Element(String s)
        {
            value=s;
            next=null;
        }
        String value;
        Element next;
    }

    public KeyWordList() {
        start=null;
        end=null;
        size = 0;
    }

    public void add(String s) {
        //adds s to the list
        Element capsule = new Element(s);
        if (end==null) {
            start=capsule;
        }
        else {
            //go to last element, point it to me
            end.next=capsule;
            //redefine last to my capsule
        }
        end=capsule;
        size++;
    }

    public String get(int index){
    //returns the string at index index
        Element current = start;
        for (int i = 0; i<index; i++){
            current = current.next;
        }
        return current.value;
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
        }
        return -1;
    }

    public void print(){
        //prints out the entire list to the console
        Element e=start;
        while(e!=null)
        {
            System.out.println(e.value);
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

}
