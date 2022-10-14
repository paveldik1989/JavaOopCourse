package ru.academits.paveldik.tree;

public class TreeNode<E extends Comparable<E>> {
    private final E value;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    public TreeNode(E value, TreeNode<E> leftChild, TreeNode<E> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public E getValue() {
        return value;
    }

    public TreeNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }
}
