//LeetCode #114
public class BinaryTreeList {
    public void flatten(TreeNode root) {
        if(root == null) { return; }

        flattenHelper(root);
    }

    public TreeNode flattenHelper(TreeNode root) {
        TreeNode bottomLeft = root;
        TreeNode bottomRight = root;
        if(root.left != null) {
            bottomLeft = flattenHelper(root.left);
        }

        if(root.right != null) {
            bottomRight = flattenHelper(root.right);
        }

        if(root.left != null) {
            if(root.right == null) {
                root.right = root.left;
                root.left = null;
            } else {
                TreeNode temp = root.right;
                root.right = root.left;
                root.left = null;
                if (bottomLeft != root) {
                    bottomLeft.right = temp;
                }
            }
        }

        if(bottomRight == root) {
            return bottomLeft;
        }
        return bottomRight;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}


