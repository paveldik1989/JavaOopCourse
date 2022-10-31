package ru.academits.paveldik.tree;

class TreeNode<E> {
    private final E value;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    public TreeNode(E value) {
        this.value = value;
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