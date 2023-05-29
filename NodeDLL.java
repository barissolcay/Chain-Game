public class NodeDLL {
    public Object data;
    public NodeDLL prev;
    public NodeDLL next;

    public NodeDLL(Object dataToAdd){
        data=dataToAdd;
        prev=null;
        next=null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public NodeDLL getPrev() {
        return prev;
    }

    public void setPrev(NodeDLL prev) {
        this.prev = prev;
    }

    public NodeDLL getNext() {
        return next;
    }

    public void setNext(NodeDLL next) {
        this.next = next;
    }
}
