package fm.liveswitch;

public class LinkedListNode<T> {
    private LinkedListNode<T> _next;
    private LinkedListNode<T> _previous;
    private T _value;

    public LinkedListNode<T> getNext() {
        return this._next;
    }

    public LinkedListNode<T> getPrevious() {
        return this._previous;
    }

    public T getValue() {
        return this._value;
    }

    public LinkedListNode(T t) {
        setValue(t);
    }

    /* access modifiers changed from: package-private */
    public void setNext(LinkedListNode<T> linkedListNode) {
        this._next = linkedListNode;
    }

    /* access modifiers changed from: package-private */
    public void setPrevious(LinkedListNode<T> linkedListNode) {
        this._previous = linkedListNode;
    }

    private void setValue(T t) {
        this._value = t;
    }
}
