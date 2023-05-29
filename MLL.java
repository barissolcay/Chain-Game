import enigma.console.TextAttributes;
public class MLL {
    NodeMLL head;

    public MLL() {
        this.head = null;
    }

    public void insert(int data) {
        NodeMLL newNode = new NodeMLL(data);
        if (head == null) {
            head = newNode;
        } else {
            NodeMLL current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void insertDown(int parentData, int childData) {
        NodeMLL parentNode = findNode(parentData);
        if (parentNode != null) {
            NodeMLL childNode = new NodeMLL(childData);
            if (parentNode.down == null) {
                parentNode.down = childNode;
            } else {
                NodeMLL current = parentNode.down;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = childNode;
            }
        }
    }

    private NodeMLL findNode(int data) {
        NodeMLL current = head;
        while (current != null) {
            if (current.data == data) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void display() {
        NodeMLL current = head;
        while (current != null) {
            NodeMLL downNode = current.down;
            while (downNode.next != null) {
                System.out.print(downNode.data + " + ");
                downNode = downNode.next;
            }
            System.out.print(downNode.data);
            System.out.println();
            current = current.next;
        }
    }

    public void copySingleToList(SLL singleLinkedList) {
        NodeMLL current = head;
        while (current.next != null) {
            current = current.next;
        }

        NodeSLL singleNode = singleLinkedList.head;
        while (singleNode != null) {
            NodeMLL newNode = new NodeMLL((int)singleNode.data);
            if (current.down == null) {
                current.down = newNode;
            } else {
                NodeMLL downNode = current.down;
                while (downNode.next != null) {
                    downNode = downNode.next;
                }
                downNode.next = newNode;
            }
            singleNode = singleNode.next;
        }
    }
}