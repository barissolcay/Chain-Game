public class DLL {
    private NodeDLL head;
    private NodeDLL tail;
    public DLL(){
        head = null;
        tail = null;
    }
    public void insertSorted(Object data)
    {
        // create a new node with the given data
        NodeDLL newNode = new NodeDLL(data);
        // check if the list is empty or the new node's data is less than or equal to the head node's data
        if (head == null || compare((String)head.getData(), (String) data) >= 0) {
            // insert the new node at the beginning of the list and update the head pointer
            newNode.setNext(head);
            if (head != null) {
                head.setPrev(newNode);
            }
            head = newNode;
            // check if the list has only one node and update the tail pointer
            if (head.getNext() == null) {
                tail = head;
            }
        } else {
            // start from the head node and loop until you find a node whose data is greater than the new node's data or you reach the end of the list
            NodeDLL current = head;
            while (current.getNext() != null && compare((String)current.getNext().getData(), (String) data) < 0) {
                current = current.getNext();
            }
            // insert the new node before that node and update the previous and next pointers accordingly
            newNode.setNext(current.getNext());
            newNode.setPrev(current);
            if (current.getNext() != null) {
                current.getNext().setPrev(newNode);
            }
            current.setNext(newNode);
            // check if the new node is the last node in the list and update the tail pointer
            if (newNode.getNext() == null) {
                tail = newNode;
            }
        }
    }

    // a method to compare two strings based on their scores
    public int compare(String a, String b) {
        // split the strings by comma and get the second element as the score
        int scoreA = Integer.parseInt(a.split(",")[1].trim());
        int scoreB = Integer.parseInt(b.split(",")[1].trim());
        // return the difference of the scores
        return scoreA - scoreB;
    }
    public String display(){
        String highscoreTable ="";
        if(head ==null){
        }else {
            NodeDLL temp = tail;
            while (temp != null){
                String formatted;
                String name = temp.getData().toString().trim().split(",")[0];
                String score = temp.getData().toString().trim().split(",")[1];
                formatted = String.format("%-15s %-15s", name, score);
                temp = temp.getPrev();
                highscoreTable += formatted +"\n";
            }
        }
        return highscoreTable;
    }
}
