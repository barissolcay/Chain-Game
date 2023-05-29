public class SLL {
    public NodeSLL head;


    public SLL(){
        this.head = head;
    }
    public void insert(Object data) {
        NodeSLL newNode = new NodeSLL(data);

        if (head == null) {
            head = newNode;
        } else {
            NodeSLL currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
    }
    public void deleteByCoordinates(int x, int y) {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }

        if (head.data instanceof TailCoordination) {
            TailCoordination headCoordination = (TailCoordination) head.data;
            if (headCoordination.getX() == x && headCoordination.getY() == y) {
                head = head.next;
                return;
            }
        }

        NodeSLL current = head;
        NodeSLL prev = null;
        while (current != null) {
            if (current.data instanceof TailCoordination) {
                TailCoordination tailCoordination = (TailCoordination) current.data;
                if (tailCoordination.getX() == x && tailCoordination.getY() == y) {
                    if (prev != null) {
                        prev.next = current.next;
                    }
                    return;
                }
            }
            prev = current;
            current = current.next;
        }

        System.out.println("TailCoordination with the specified coordinates not found.");
    }
    public void removeDuplicates() {
        if (head == null || head.next == null) {
            return;
        }

        NodeSLL current = head;
        while (current != null) {
            NodeSLL runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    public void deleteNode(Object data) {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }

        if (head.data == data) {
            head = head.next;
            return;
        }

        NodeSLL current = head;
        NodeSLL prev = null;
        while (current != null && current.data != data) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            return;
        }

        prev.next = current.next;
    }
    public int size() {
        int count = 0;
        NodeSLL currentNode = head;

        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }
}
