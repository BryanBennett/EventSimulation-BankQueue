import java.util.ArrayList;

public class Queue<T> {

    //Field Variables
    private ArrayList<T> array = new ArrayList<T>();

    //Constructor
    public Queue(){}

    //Methods (including get and set methods)
    public void push(T n){array.add(n);}
    public void deleteFront(){array.remove(0);}

    //This takes the front object off the queue and deletes it
    public T pop() {
        T result = array.get(0);
        array.remove(0);
        return result;
    }
    public T peek(){return array.get(0);}
    public boolean isEmpty(){return array.isEmpty();}
    public int lengthOfQueue(){return array.size();}
}