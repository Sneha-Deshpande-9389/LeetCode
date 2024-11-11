package LeetCode;

class Node {
    int val;
    Node left;
    Node right;
}
public class BST {
    public static void main(String[] args) {
        /*
        Input: root = [1,0,2], low = 1, high = 2
Output: [1,null,2]

Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]
                3
             0      4
               2
             1
         */
        Node node = new Node();
        node.val = 3;
        node.left = new Node();
    }

    public Node delete(Node root, int low, int high) {
        //base case - TODO
        if(root == null) {
            return null;
        }

        // 1. root.left < low
        //             c. root.left = root.left.right
        if(root.left!= null && root.left.val < low) {
            root.left = root.left.right;
        }


        //        1a root.left > low - go root.left
        if(root.val > low) {
            delete(root.left, low, high);
        }
        //
        //        2.  root.right > high
        //            a. root.right =  root.right.left
        if(root.right!=null && root.right.val > high) {
            root.right = root.right.left;
        }
        //        2a    root.right < high - go root.high
        if(root.right!=null && root.right.val < high) {
            delete(root.right, low, high);
        }

        //
        //        3. root.val == low
        //             c. root.left = null
        if(root.val == low) {
            root.left = null;
        }
        //         4. root.val == high
        //                    root.right = null;
        if(root.val == high) {
            root.right = null;
        }
        return root;
    }
    /*
    Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.

Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.

Input: root = [1,0,2], low = 1, high = 2
Output: [1,null,2]

Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]


            1
     0-X              2
   1.left = null
                3
             0      4
               2
             1

        1. root.left < low
             c. root.left = root.left.right
        1a root.left > low - go root.left

        2.  root.right > high
            a. root.right =  root.right.left
        2a    root.right > high - go root.high

        3. root.val == low
             c. root.left = null
         4. root.val == high
                    root.right = null;

     */
}
