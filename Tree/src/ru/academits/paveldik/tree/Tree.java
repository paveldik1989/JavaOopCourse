package ru.academits.paveldik.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<E> comparator;

    public Tree() {
        comparator = null;
    }

    public Tree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public Tree(Collection<E> collection) {
        comparator = null;

        for (E e : collection) {
            add(e);
        }
    }

    public Tree(Collection<E> collection, Comparator<E> comparator) {
        this.comparator = comparator;

        for (E e : collection) {
            add(e);
        }
    }

    private int compare(E value1, E value2) {
        if (comparator != null) {
            return comparator.compare(value1, value2);
        }

        if (value1 == null && value2 == null) {
            return 0;
        }

        if (value1 == null) {
            return -1;
        }

        if (value2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<E>) value1).compareTo(value2);
    }

    public int size() {
        return size;
    }

    public void add(E value) {
        if (root == null) {
            root = new TreeNode<>(value);
            size++;
            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            if (compare(value, currentNode.getValue()) < 0) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.setLeftChild(new TreeNode<>(value));
                    break;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(new TreeNode<>(value));
                    break;
                }
            }
        }

        size++;
    }

    public boolean contains(E value) {
        if (root == null) {
            return false;
        }

        if (compare(value, root.getValue()) == 0) {
            return true;
        }

        return getNodeParent(value) != null;
    }

    private TreeNode<E> getNodeParent(E value) {
        if (root == null) {
            return null;
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> currentNodeParent = null;

        while (true) {
            int valuesComparisonResult = compare(value, currentNode.getValue());

            if (valuesComparisonResult == 0) {
                return currentNodeParent;
            }

            if (valuesComparisonResult < 0) {
                if (currentNode.getLeftChild() == null) {
                    return null;
                }

                currentNodeParent = currentNode;
                currentNode = currentNode.getLeftChild();
            } else {
                if (currentNode.getRightChild() == null) {
                    return null;
                }

                currentNodeParent = currentNode;
                currentNode = currentNode.getRightChild();
            }
        }
    }

    public void traverseBreadthFirst(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> treeNode = queue.remove();
            action.accept(treeNode.getValue());

            if (treeNode.getLeftChild() != null) {
                queue.add(treeNode.getLeftChild());
            }

            if (treeNode.getRightChild() != null) {
                queue.add(treeNode.getRightChild());
            }
        }
    }

    public void traverseDepthFirstByRecursion(Consumer<E> action) {
        visit(root, action);
    }

    private void visit(TreeNode<E> treeNode, Consumer<E> action) {
        if (treeNode != null) {
            action.accept(treeNode.getValue());
            visit(treeNode.getLeftChild(), action);
            visit(treeNode.getRightChild(), action);
        }
    }

    public void traverseDepthFirst(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> treeNode = stack.pop();
            action.accept(treeNode.getValue());

            if (treeNode.getRightChild() != null) {
                stack.push(treeNode.getRightChild());
            }

            if (treeNode.getLeftChild() != null) {
                stack.push(treeNode.getLeftChild());
            }
        }
    }

    public boolean remove(E value) {
        if (root == null) {
            return false;
        }

        TreeNode<E> nodeToDeleteParent = getNodeParent(value);
        TreeNode<E> nodeToDelete;
        boolean isLeftChild = false;

        if (nodeToDeleteParent == null) {
            if (compare(value, root.getValue()) != 0) {
                return false;
            }

            nodeToDelete = root;
        } else {
            if (compare(value, nodeToDeleteParent.getValue()) < 0) {
                nodeToDelete = nodeToDeleteParent.getLeftChild();
                isLeftChild = true;
            } else {
                nodeToDelete = nodeToDeleteParent.getRightChild();
            }
        }

        // Случай 1 - нет детей
        if (nodeToDelete.getRightChild() == null && nodeToDelete.getLeftChild() == null) {
            linkNodeToParent(nodeToDeleteParent, null, isLeftChild);
            size--;
            return true;
        }

        TreeNode<E> replacementNode;

        // Случай 2 - есть один ребенок
        if (nodeToDelete.getLeftChild() == null || nodeToDelete.getRightChild() == null) {
            if (nodeToDelete.getLeftChild() == null) {
                replacementNode = nodeToDelete.getRightChild();
            } else {
                replacementNode = nodeToDelete.getLeftChild();
            }

            linkNodeToParent(nodeToDeleteParent, replacementNode, isLeftChild);
            size--;
            return true;
        }

        // Случай 3 - есть два ребенка
        replacementNode = nodeToDelete.getRightChild();
        TreeNode<E> replacementNodeParent = null;

        while (replacementNode.getLeftChild() != null) {
            replacementNodeParent = replacementNode;
            replacementNode = replacementNodeParent.getLeftChild();
        }

        if (replacementNodeParent != null) {
            replacementNodeParent.setLeftChild(replacementNode.getRightChild());
            replacementNode.setRightChild(nodeToDelete.getRightChild());
        }

        replacementNode.setLeftChild(nodeToDelete.getLeftChild());
        linkNodeToParent(nodeToDeleteParent, replacementNode, isLeftChild);
        size--;
        return true;
    }

    private void linkNodeToParent(TreeNode<E> nodeToDeleteParent, TreeNode<E> replacementNode, boolean hasRemovedLeftChild) {
        if (nodeToDeleteParent == null) {
            root = replacementNode;
        } else if (hasRemovedLeftChild) {
            nodeToDeleteParent.setLeftChild(replacementNode);
        } else {
            nodeToDeleteParent.setRightChild(replacementNode);
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        traverseBreadthFirst(e -> stringBuilder.append(e).append(", "));
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length())
                .append(']');

        return stringBuilder.toString();
    }
}