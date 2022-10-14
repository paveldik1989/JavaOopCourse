package ru.academits.paveldik.tree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Consumer;

public class Tree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;

    public Tree() {
    }

    public Tree(Collection<E> collection) {
        for (E e : collection) {
            this.add(e);
        }
    }

    public int size() {
        return size;
    }

    public void add(E value) {
        if (root == null) {
            root = new TreeNode<>(value, null, null);
            size++;
            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            if (value.compareTo(currentNode.getValue()) < 0) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.setLeftChild(new TreeNode<>(value, null, null));
                    break;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(new TreeNode<>(value, null, null));
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

        if (value.compareTo(root.getValue()) == 0) {
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
            if (value.compareTo(currentNode.getValue()) == 0) {
                return currentNodeParent;
            }

            if (value.compareTo(currentNode.getValue()) < 0) {
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

        LinkedList<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> treeNode = queue.removeFirst();
            action.accept(treeNode.getValue());
            if (treeNode.getLeftChild() != null) {
                queue.addLast(treeNode.getLeftChild());
            }

            if (treeNode.getRightChild() != null) {
                queue.addLast(treeNode.getRightChild());
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
        Stack<TreeNode<E>> stack = new Stack<>();
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

    public void remove(E value) { // Добавить изменение размера
        if (root == null) {
            return;
        }

        TreeNode<E> nodeToDeleteParent = getNodeParent(value);
        TreeNode<E> nodeToDelete;

        if (nodeToDeleteParent == null) {
            if (value.compareTo(root.getValue()) != 0) {
                return;
            }

            nodeToDelete = root;
        } else {
            if (value.compareTo(nodeToDeleteParent.getValue()) < 0) {
                nodeToDelete = nodeToDeleteParent.getLeftChild();
            } else {
                nodeToDelete = nodeToDeleteParent.getRightChild();
            }
        }

        // Случай 1 - нет детей
        if (nodeToDelete.getRightChild() == null && nodeToDelete.getLeftChild() == null) {
            if (nodeToDeleteParent == null) {
                root = null;
                size--;
                return;
            }

            if (nodeToDelete.getValue().compareTo(nodeToDeleteParent.getValue()) < 0) {
                nodeToDeleteParent.setLeftChild(null);
            } else {
                nodeToDeleteParent.setRightChild(null);
            }

            size--;
        }

        // Случай 2 - есть один ребенок
        if (nodeToDelete.getLeftChild() != null && nodeToDelete.getRightChild() == null) {
            if (nodeToDeleteParent == null) {
                root = nodeToDelete.getLeftChild();
                size--;
                return;
            }

            if (nodeToDelete.getValue().compareTo(nodeToDeleteParent.getValue()) < 0) {
                nodeToDeleteParent.setLeftChild(nodeToDelete.getLeftChild());
            } else {
                nodeToDeleteParent.setRightChild(nodeToDelete.getLeftChild());
            }

            size--;
        }

        if (nodeToDelete.getLeftChild() == null && nodeToDelete.getRightChild() != null) {
            if (nodeToDeleteParent == null) {
                root = nodeToDelete.getRightChild();
                size--;
                return;
            }

            if (nodeToDelete.getValue().compareTo(nodeToDeleteParent.getValue()) < 0) {
                nodeToDeleteParent.setLeftChild(nodeToDelete.getRightChild());
            } else {
                nodeToDeleteParent.setRightChild(nodeToDelete.getRightChild());
            }

            size--;
        }

        // Случай 3 - есть два ребенка
        if (nodeToDelete.getLeftChild() != null && nodeToDelete.getRightChild() != null) {
            if (nodeToDelete.getRightChild().getLeftChild() == null) {
                if (nodeToDeleteParent == null) {
                    nodeToDelete.getRightChild().setLeftChild(nodeToDelete.getLeftChild());
                    root = nodeToDelete.getRightChild();
                    size--;
                    return;
                }

                if (nodeToDelete.getValue().compareTo(nodeToDeleteParent.getValue()) < 0) {
                    nodeToDeleteParent.setLeftChild(nodeToDelete.getRightChild());
                    nodeToDeleteParent.getLeftChild().setLeftChild(nodeToDelete.getLeftChild());
                } else {
                    nodeToDeleteParent.setRightChild(nodeToDelete.getRightChild());
                    nodeToDeleteParent.getRightChild().setLeftChild(nodeToDelete.getLeftChild());
                }

                size--;
                return;
            }

            TreeNode<E> lastLeftChild = nodeToDelete.getRightChild().getLeftChild();
            TreeNode<E> lastLeftChildParent = nodeToDelete.getRightChild();

            while (lastLeftChild.getLeftChild() != null) {
                lastLeftChildParent = lastLeftChild;
                lastLeftChild = lastLeftChild.getLeftChild();
            }

            lastLeftChildParent.setLeftChild(lastLeftChild.getRightChild());

            if (nodeToDeleteParent == null) {
                lastLeftChild.setLeftChild(nodeToDelete.getLeftChild());
                lastLeftChild.setRightChild(nodeToDelete.getRightChild());
                root = lastLeftChild;
                size--;
                return;
            }

            if (nodeToDelete.getValue().compareTo(nodeToDeleteParent.getValue()) < 0) {
                nodeToDeleteParent.setLeftChild(lastLeftChild);
            } else {
                nodeToDeleteParent.setRightChild(lastLeftChild);
            }

            lastLeftChild.setLeftChild(nodeToDelete.getLeftChild());
            lastLeftChild.setRightChild(nodeToDelete.getRightChild());
            size--;
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