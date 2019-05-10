
public class BinaryTreeNode {

	private int val;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private BinaryTreeNode parent;
	
	public BinaryTreeNode(int val) {
		super();
		this.val = val;
		left = null;
		right = null;
		parent = null;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	public BinaryTreeNode getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode parent) {
		this.parent = parent;
	}
}
