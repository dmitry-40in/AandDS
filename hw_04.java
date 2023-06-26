public class hw_04 {
    public static void main(String[] args) {
        
    }
}

class Tree {
    Node root;

    class Node{
        int key;
        int value;
        Node left = null;
        Node rigth = null;
        Node parent = null;
        boolean color = BLACK;
    }

    Node nil = new Node();

    public void insert(Tree tree, int key, int value){
        Node currentNode = tree.root;
        Node parent = nil;
        while (nodeExists(currentNode)){
            parent = currentNode;
            if(value < currentNode.value){
                currentNode = currentNode.left;
                }
            else {
                currentNode = currentNode.rigth;
            }
            }
        
        Node newNode = new Node();
        creatNode(newNode, key, value);
        newNode.parent = parent;
        if(parent == nil){
            tree.root = newNode;
        }
        else if (value < parent.value){
            parent.left = newNode;
        }
        else{
            parent.rigth = newNode;
        }
        balanceTree(tree,newNode);
    }

    public void creatNode(Node node, int key, int value){
        node.parent = nil;
        node.left = nil;
        node.rigth = nil;
        node.key = key;
        node.value = value;
        node.color = RED;
    }

    public boolean nodeExists(Node node){
        return node != nil;
    }

    public void balanceTree(Tree tree, Node newNode){
        Node uncle;
        while (newNode.parent.color == RED){
            if(newNode.parent == newNode.parent.parent.left){
                uncle = newNode.parent.parent.rigth;
                if (uncle.color == RED) {
                    newNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                }
                else{
                    if(newNode == newNode.parent.rigth){
                        newNode = newNode.parent;
                        leftRotate(tree, newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    rigthRotate(tree,newNode.parent.parent);
                }
            }
            else{
                uncle = newNode.parent.parent.left;
                if (uncle.color == RED){
                    newNode.parent.color = BLACK;                        
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                }
                else{
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rigthRotate(tree,newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    leftRotate(tree, newNode.parent.parent);
                }
            }
        }
    tree.root.color = BLACK;
    }    
}

