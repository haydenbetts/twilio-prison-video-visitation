package fm.liveswitch;

import java.util.ArrayList;

public class LinkedList<T> {
    private int __count;
    private LinkedListNode<T> _first;
    private LinkedListNode<T> _last;

    public LinkedListNode<T> addAfterNode(LinkedListNode<T> linkedListNode, T t) {
        if (linkedListNode != null) {
            LinkedListNode<T> linkedListNode2 = new LinkedListNode<>(t);
            linkedListNode2.setPrevious(linkedListNode);
            linkedListNode2.setNext(linkedListNode.getNext());
            if (linkedListNode.getNext() != null) {
                linkedListNode.getNext().setPrevious(linkedListNode2);
            }
            linkedListNode.setNext(linkedListNode2);
            if (linkedListNode2.getNext() == null) {
                setLast(linkedListNode2);
            }
            this.__count++;
            return linkedListNode2;
        }
        throw new RuntimeException(new Exception("Node cannot be null."));
    }

    public LinkedListNode<T> addBeforeNode(LinkedListNode<T> linkedListNode, T t) {
        if (linkedListNode != null) {
            LinkedListNode<T> linkedListNode2 = new LinkedListNode<>(t);
            linkedListNode2.setPrevious(linkedListNode.getPrevious());
            linkedListNode2.setNext(linkedListNode);
            if (linkedListNode.getPrevious() != null) {
                linkedListNode.getPrevious().setNext(linkedListNode2);
            }
            linkedListNode.setPrevious(linkedListNode2);
            if (linkedListNode2.getPrevious() == null) {
                setFirst(linkedListNode2);
            }
            this.__count++;
            return linkedListNode2;
        }
        throw new RuntimeException(new Exception("Node cannot be null."));
    }

    public LinkedListNode<T> addFirst(T t) {
        if (getFirst() != null) {
            return addBeforeNode(getFirst(), t);
        }
        setFirst(new LinkedListNode(t));
        setLast(getFirst());
        this.__count = 1;
        return getFirst();
    }

    public LinkedListNode<T> addLast(T t) {
        if (getLast() != null) {
            return addAfterNode(getLast(), t);
        }
        setLast(new LinkedListNode(t));
        setFirst(getLast());
        this.__count = 1;
        return getLast();
    }

    public void clear() {
        while (getFirst() != null) {
            removeFirst();
        }
        this.__count = 0;
    }

    public int getCount() {
        return this.__count;
    }

    public LinkedListEnumerator<T> getEnumerator() {
        return new LinkedListEnumerator<>(getFirst());
    }

    public LinkedListNode<T> getFirst() {
        return this._first;
    }

    public LinkedListNode<T> getLast() {
        return this._last;
    }

    public boolean remove(T t) {
        LinkedListEnumerator enumerator = getEnumerator();
        while (enumerator.moveNext()) {
            if (enumerator.getCurrent() == t) {
                return removeNode(enumerator.getCurrentNode());
            }
        }
        return false;
    }

    public boolean removeFirst() {
        return removeNode(getFirst());
    }

    public boolean removeLast() {
        return removeNode(getLast());
    }

    public boolean removeNode(LinkedListNode<T> linkedListNode) {
        if (linkedListNode == null) {
            return false;
        }
        LinkedListNode<T> next = linkedListNode.getNext();
        LinkedListNode<T> previous = linkedListNode.getPrevious();
        if (next == null && previous == null && !Global.equals(getFirst(), linkedListNode) && !Global.equals(getLast(), linkedListNode)) {
            return false;
        }
        if (Global.equals(getFirst(), linkedListNode)) {
            setFirst(next);
        }
        if (Global.equals(getLast(), linkedListNode)) {
            setLast(previous);
        }
        if (next != null) {
            next.setPrevious(previous);
        }
        if (previous != null) {
            previous.setNext(next);
        }
        linkedListNode.setNext((LinkedListNode<T>) null);
        linkedListNode.setPrevious((LinkedListNode<T>) null);
        this.__count--;
        return true;
    }

    private void setFirst(LinkedListNode<T> linkedListNode) {
        this._first = linkedListNode;
    }

    private void setLast(LinkedListNode<T> linkedListNode) {
        this._last = linkedListNode;
    }

    public ArrayList<T> toList() {
        ArrayList<T> arrayList = new ArrayList<>();
        LinkedListEnumerator enumerator = getEnumerator();
        while (enumerator.moveNext()) {
            arrayList.add(enumerator.getCurrent());
        }
        return arrayList;
    }
}
