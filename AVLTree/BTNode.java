package AVLTree;

public class BTNode<T extends Comparable<T>> {
    public T data;
    public BTNode<T> left, right;

    public BTNode() {
        left = right = null;
    }

    public BTNode(T data) {
        this(data, null, null);
    }

    public BTNode(T data, BTNode<T> left, BTNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return this.data + "";
    }
}