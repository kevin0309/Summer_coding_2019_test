import java.util.LinkedList;

public class BinaryTree {

	private BinaryTreeNode root;
	private LinkedList<BinaryTreeNode> q;

	public BinaryTree() {
		super();
		root = null;
		q = new LinkedList<>();
	}

	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}
	
	public void addNode(BinaryTreeNode node) {
		if (root == null) {
			setRoot(node);
			q.add(node);
		}
		else {
			BinaryTreeNode temp = q.peek();
			if (temp.getLeft() == null)
				temp.setLeft(node);
			else {
				temp.setRight(node);
				q.poll();
			}
			q.add(node);
			node.setParent(temp);
		}
	}
	
	public void inorder() {
		System.out.print("inorder : ");
		inorderRecur(root);
		System.out.println();
	}
	
	private void inorderRecur(BinaryTreeNode node) {
		if (node.getLeft() != null)
			inorderRecur(node.getLeft());
		System.out.print(node.getVal() + " ");
		if (node.getRight() != null)
			inorderRecur(node.getRight());
	}
}
