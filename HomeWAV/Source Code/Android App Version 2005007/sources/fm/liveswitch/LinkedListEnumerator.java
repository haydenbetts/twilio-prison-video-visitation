package fm.liveswitch;

public class LinkedListEnumerator<T> {
    private LinkedListNode<T> __currentNode;
    private LinkedListNode<T> __root;
    private boolean __started;

    public T getCurrent() {
        return this.__currentNode.getValue();
    }

    public LinkedListNode<T> getCurrentNode() {
        return this.__currentNode;
    }

    public LinkedListEnumerator(LinkedListNode<T> linkedListNode) {
        this.__root = linkedListNode;
    }

    public boolean moveNext() {
        LinkedListNode<T> linkedListNode = this.__currentNode;
        if (linkedListNode != null || this.__started) {
            this.__currentNode = linkedListNode.getNext();
        } else {
            this.__started = true;
            this.__currentNode = this.__root;
        }
        if (this.__currentNode != null) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.__started = false;
        this.__currentNode = null;
    }
}
