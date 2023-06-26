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
        boolean color = false;
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
        node.color = true;
    }

    public boolean nodeExists(Node node){
        return node != nil;
    }
    
    public void swap(Node a, Node b){
        int a_key = a.key;
        a.key = b.key;
        b.key = a_key;
        int a_value = a.value;
        a.value = b.value;
        b.value = a_value;
    }

    public void rigthRotate(Node node){
        swap(node, node.left);
        Node buffer = node.rigth;
        node.rigth = node.left;
        node.left = node.rigth.left;
        node.rigth.left = node.rigth.rigth;
        node.rigth.rigth = buffer;
    }

    public void leftRotate(Node node){
        swap(node, node.rigth);
        Node buffer = node.left;
        node.left = node.rigth;
        node.rigth = node.left.rigth;
        node.left.rigth = node.left.left;
        node.left.left = buffer;
    }   


    public void balanceTree(Tree tree, Node newNode){
        Node uncle;
        while (newNode.parent.color == true){
            if(newNode.parent == newNode.parent.parent.left){
                uncle = newNode.parent.parent.rigth;
                if (uncle.color == true) {
                    newNode.parent.color = false;
                    uncle.color = false;
                    newNode.parent.parent.color = true;
                    newNode = newNode.parent.parent;
                }
                else{
                    if(newNode == newNode.parent.rigth){
                        newNode = newNode.parent;
                        leftRotate(newNode);
                    }
                    newNode.parent.color = false;
                    newNode.parent.parent.color = true;
                    rigthRotate(newNode.parent.parent);
                }
            }
            else{
                uncle = newNode.parent.parent.left;
                if (uncle.color == true){
                    newNode.parent.color = false;                        
                    uncle.color = false;
                    newNode.parent.parent.color = true;
                    newNode = newNode.parent.parent;
                }
                else{
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rigthRotate(newNode);
                    }
                    newNode.parent.color = false;
                    newNode.parent.parent.color = true;
                    leftRotate(newNode.parent.parent);
                }
            }
        }
    tree.root.color = false;
    }
}

