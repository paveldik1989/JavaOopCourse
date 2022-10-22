package ru.academits.paveldik.tree;

class TreeNode<E> {
    private final E value;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    TreeNode(E value) {
        this.value = value;
    }

    E getValue() {
        return value;
    }

    TreeNode<E> getLeftChild() {
        return leftChild;
    }

    void setLeftChild(TreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    TreeNode<E> getRightChild() {
        return rightChild;
    }

    void setRightChild(TreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }
}