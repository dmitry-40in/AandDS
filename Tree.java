public class Tree {
    private Node root;

        private class Node{
            private int value;
            private Node left;
            private Node right;
            private boolean colorRed;
        }


        public void insert(int value){
            if (root == null){
                root = new Node();
                root.value = value;
                root.colorRed = false;
            }else{
                insert(root, value);
                root = balanceTree(root);
                root.colorRed = false;
            }
        }

        
        private void insert(Node node, int value){
            if (node.value != value){
                if (node.value < value){
                    if (node.right == null){
                        node.right = new Node();
                        node.right.value = value;
                        node.right.colorRed = true;
                    }else{
                        insert(node.right, value);
                        node.right = balanceTree(node.right);
                    }
                }else{
                    if (node.left == null){
                        node.left = new Node();
                        node.left.value = value;
                        node.left.colorRed = true;
                    }else{
                        insert(node.left, value);
                        node.left = balanceTree(node.left);
                    }
                }
            }else{
                System.out.println("Нода с таким значением уже существует");
            }
        }


        private Node balanceTree(Node node){
            Node result = node;
            boolean flag;
            do {
                flag = false;
                if (result.right != null && result.right.colorRed == true &&
                    (result.left == null || result.left.colorRed == false)){
                        flag = true;
                        result = rightSwap(result);
                    }
                if (result.left != null && result.left.colorRed == true &&
                    result.left.left != null && result.left.left.colorRed == true){
                        flag = true;
                        result = leftSwap(result);
                    }
                if (result.left != null && result.left.colorRed == true &&
                    result.right != null && result.right.colorRed == true) {
                        flag = true;
                        colorSwap(result);
                    }
            }
            while (flag);
            return result;   
        }


        private void colorSwap(Node node){
            node.colorRed = true;
            node.left.colorRed = false;
            node.right.colorRed = false;
        }


        private Node leftSwap(Node node){
            Node leftChild = node.left;
            Node betweenChild = leftChild.right;
            leftChild.right = node;
            node.left = betweenChild;
            leftChild.colorRed = node.colorRed;
            node.colorRed = true;
            return leftChild;
        }


        private Node rightSwap(Node node){
            Node rightChild = node.right;
            Node betweenChild = rightChild.left;
            rightChild.left = node;
            node.right = betweenChild;
            rightChild.colorRed = node.colorRed;
            node.colorRed = true;
            return rightChild;
        }




}
