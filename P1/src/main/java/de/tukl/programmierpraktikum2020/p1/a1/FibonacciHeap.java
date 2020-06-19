package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.UnaryOperator;


public class FibonacciHeap<E> implements PriorityQueue<E> {
    FibNode<E> maxNode;
    FibNode<E> foundNode;
    FibNode<E> vater;
    int size = 0;
    Comparator<E> comp;
    LinkedList<FibNode<E>> rootlist = new LinkedList<>();

    public FibonacciHeap(Comparator<E> comp) {
        this.comp = comp;
    }

    //---------------insert------------------------------------------
    private void insertNode(FibNode<E> node) {
        if (rootlist.isEmpty()) {
            maxNode = node;
        }
        if (comp.compare(maxNode.key, node.key) < 0) {
            maxNode = node;
        }
        rootlist.add(node);
    }

    @Override
    public void insert(E elem) {
        FibNode<E> node = new FibNode<>(elem);
        if (rootlist.isEmpty()) {
            maxNode = node;
        }
        if (comp.compare(maxNode.key, node.key) < 0) {
            maxNode = node;
        }
        rootlist.add(node);
        size++;
    }
//   maxNode
// ---------------------------------
//|     1   |          |            |
// ---------------------------------
// maxNode
// ---------------------------------
//|     1   |      0    |            |
// ---------------------------------
//                        maxNode
// ---------------------------------
//|    1    |      0    |      5      |
// ---------------------------------

    // --------------------------merge--------------------------------------
    @Override
    public void merge(PriorityQueue<E> otherQueue) {
        int otherQueueSize = ((FibonacciHeap<E>) otherQueue).rootlist.size();
        while (!((FibonacciHeap<E>) otherQueue).rootlist.isEmpty()) {
            insertNode(((FibonacciHeap<E>) otherQueue).rootlist.remove());
        }
        size += otherQueueSize;
    }

//  Queue1/size=7                                         Queue2/size = 9
//   ----------------------------                    -------------------
//  |     5    |   9    |   2   |                   |     8   |    5     |
//   ----/-\-------|-------------                    --/-/-\-------------
//      1   2      3                                  7  5  4
//      |                                           / \  |
//      0                                          6   3 0
//                                                 |
//                                                 2
// Merge Funktion nimmt ELemente von der RootListe des Queues2 und fügt sie in RootList des Queue1 ein
//    und addiert den Size des Queue2 auf den Size von Queue1
//    Queue1.merge(Queue2) /size = 7+9 = 16
//    ------------------------------------------
//   |    5    |   9    |   2   |    8   |   5   |
//    ---/-\-------|---------------/-/-\--------
//      1   2      3              7  5  4
//      |                       / \  |
//      0                      6   3 0
//                             |
//                             2


//-----------------------------------------------------------------------------------------------------------

    private FibNode<E> Binomial() {
        HashMap<Integer, FibNode<E>> degRoots = new HashMap<>();
        if (rootlist.isEmpty()) {
            return null;
        }
        FibNode<E> max = rootlist.getFirst();
        int i = 0;
        while (rootlist.size() > i) {
            if (comp.compare(max.key, rootlist.get(i).key) < 0) {
                max = rootlist.get(i);
            }
            if (!degRoots.containsKey(rootlist.get(i).degree)) {
                degRoots.put(rootlist.get(i).degree, rootlist.get(i));
                i++;
            } else {
                int sameDegree = rootlist.get(i).degree;
                FibNode<E> sameTreeDegree = degRoots.remove(sameDegree);
                FibNode<E> newTree = mergTowtrees(rootlist.get(i), sameTreeDegree);

                rootlist.set(i, newTree);
                i =  i - 1;
                rootlist.remove(sameTreeDegree);
            }
        }
        return max;
    }
// max -> ist der Knoten der den großten schlüssel hat .
// degRoots -> ist ein HashMap , enhält an einer Position einen Knoten(Root) und seinen  Degree <degree , Root> .
// sameDegree -> ist ein Root in der rootListe , der einen Degree gleich einen eines Root in der degRoots hat
// Nach dem Löschen von dem Knoten der das größte Schlüssel hat , könnte neue Knoten(child)in die rootListe eingefügt werden ,
//  Das heißt könnte Roots mit selben Grad stattfinden , genau bei mergFunktion .wir wissen schon dass die Fibonacciheap bessteht aus binomiale Bäume mit verschieden Grad .
//  Um die Struktur von Fibonacciheap zu halten , müssen alle binomiale Bäume die gleichen Grad haben , zusammengefast.
//
//
//
//   ---0--------1-------0---
//  |   5   |    5   |   4   |
//   ------------|-----------
//               3
//


//    max = Node(5)   ... rootlist.size()= 3
//   i = 0
//    rootlist.get(0) = node(5)  ist nicht in der hashMap<degree , Node()>
//     wir fügen <0, Node(5)> in degRoots(hashMap) ein .
//   -------------
//  |<0 , Node(5)>|
//   -------------

//    i =1
//     rootlist.get(1) = node(5)  ist nicht in der hashMap<degree , Node()>
//       wir fügen <1 , Node(5)> in degRoots(hashMap) ein .

//   -------------------------------
//  |<0 , Node(5)> | <1 , Node(5)>  |
//   -------------------------------

//  i = 2
//  rootlist.get(2) = node(4)  es gibt ein Knoten in hashMap , der gleichen degree hat . <0 , Node(5)>
//       wir fügen <0 , Node(4)> in degRoots(hashMap) ((((nicht)))) ein .
//  sondern wird folgendes gemacht
//  sameDegree = 0;
//                         ---
//  sameTreeDegree = Node(5)  |
//   -----------------        |
//  |  <1 , Node(5)>  |       |
//   -----------------        |
//                         ---
//    newTree = mergTowtrees(Node(4) , Node(5))  --> Node(5) mit degree 1
//    wir ersetzen newTree mit dem Knoten an position i =2
//   ---0--------1-------1---
//  |   5   |    5   |   5   |
//   ------------|-------|--
//               3       4
//  aktulalisieren der Index i = rootlist.indexOf(sameTreeDegree) > i ? i : i - 1; wir sichern dass i wird nicht mehr als rootlist size
//   i = 2>2 ? 2 : 1
//    i = 1;
//   ---1-------1---
//   |   5   |   5   |
//    ---|-------|---
//       3       4
//   hier wird die obene Operation wiederhold weil 3> 1 und  bei i = 1 gibt es wieder ein zwei Roots mit gleichem Degree.
//
//  am Ende bekommen wir diese rooltlist

//      ---2---
//     |   5   |
//      --/-\---
//       5   3
//       |
//       4
//
// maxNode = Node(5)


    //------------------ mergTowtrees------------------------------
    // Hilfsfunktion bei Binomial Funktion
    private FibNode<E> mergTowtrees(FibNode<E> FiboNode1, FibNode<E> FiboNode2) {
        if (comp.compare(FiboNode1.key, FiboNode2.key) > 0) {
            FiboNode1.child.add(FiboNode2);
            FiboNode2.parent = FiboNode1;
            FiboNode1.degree++;
            return FiboNode1;
            // FiboNode1                          FiboNode2
//          -----------                          ---------
//         |     5     |                        |    8    |
//          ----/-\----                          ---/-\----
//             1   2                               5  4
//             |                                   |
//             0                                   0
//
//                      FiboNode2
//                     --------
//                    |    8   |
//                     --/-/-\--
//                     5  5  4
//                   / \  |
//                  1   2 0
//                  |
//                  0

        } else {
            FiboNode2.child.add(FiboNode1);
            FiboNode1.parent = FiboNode2;
            FiboNode2.degree++;
            return FiboNode2;

            // FiboNode1                          FiboNode2
//          -----------                          ---------
//         |     10     |                        |    8    |
//          ----/-\----                          ---/-\----
//             1   2                               5  4
//             |                                   |
//             0                                   0
//
//                     FiboNode1
//                     --------
//                    |    10   |
//                     --/-/-\--
//                     8  1  2
//                   / \  |
//                  5   4 0
//                  |
//                  0


        }
    }

    @Override
    public E deleteMax() {
        if (rootlist.isEmpty()) {
            return null;
        } else {

            while (!maxNode.child.isEmpty()) {
                cut(maxNode.child.removeFirst(), maxNode);
            }
            FibNode<E> m = maxNode;
            rootlist.remove(maxNode);
            maxNode = Binomial();
            size--;
            return m.key;
        }
    }

//      maxNode
//    ----2-------0--
//   |    8   |   5   |
//    ---/-\----------
//      5  4
//      |
//                                     ----
//      maxNode                            |
//    ----2-------0--                      |
//   |    8   |   5   |                    |
//    ----------------                     |
//      5  4                               |
//      |                                  |
//      3                                  |   cut funktion
//                                         |
//  maxNode                                |
//   ---0-------0------1------0---         |
//  |   8   |   5   |  5   |   4  |        |
//   ------------------|-----------        |
//                     3                   |
//                                      ---
//                             ---
//   ---0--------1-------0---     |
//  |   5   |    5   |   4   |    |  remove(maxNode);
//   ------------|-----------     |
//               3             ---
//


    @Override
    public E max() {
        return maxNode.key;
    }

    @Override
    public boolean isEmpty() {
        return rootlist.isEmpty();
    }


    //------------------------------------update-----------------------------------------------------
    //------------------------------------UPDATE-----------------------------------------------------
    //-----------HilfsFunktionBeimSuchen
    //                                                                                                  |
    private void findeNode(FibNode<E> node, E elem) {
        if (comp.compare(node.key, elem) == 0) {
            foundNode = node;
            vater = node.parent;
            //                                                                                                     |
        } else if (comp.compare(node.key, elem) > 0) {
            for (int i = 0; i < node.degree; i++) {
                findeNode(node.child.get(i), elem);
            }
        }
    }

    //-------------- SearchNachElement----------------------                                             |
    private void updateMax() {
        for (FibNode<E> i : rootlist) {
            if (comp.compare(i.key, maxNode.key) > 0) {
                maxNode = i;
            }
        }
    }

    //                                                                                                  ( find )    |
    private FibNode<E> find(E elem) {
        foundNode = null;
        vater = null;
        for (FibNode<E> eFibNode : rootlist) {
            if (foundNode != null) {
                break;
            }
            findeNode(eFibNode, elem);
        }
        return foundNode;
    }

    //                                                                                                ( updateElem )  |
    private boolean moveChild(FibNode<E> node) {
        boolean check = false;
        for (FibNode<E> i : node.child) {
            if (comp.compare(i.key, node.key) > 0) {
                check = true;
                break;
            }
        }
        return check;
    }

    private void updateElem(FibNode<E> node, E updateElem) {
        node.key = updateElem;
        if (vater == null) {
            if (!node.child.isEmpty() && moveChild(node)) {
                while (!node.child.isEmpty()) {
                    cut(node.child.removeFirst(), node);
                }
            }
            updateMax();
        } else {
            if (comp.compare(node.key, vater.key) > 0) {
                int i = vater.child.indexOf(node);
                cut(vater.child.remove(i), vater);
                fix_after_cut(vater);
            } else if ((moveChild(node))) {
                while (!node.child.isEmpty()) {
                    cut(node.child.removeFirst(), node);
                }
            }
        }
    }

    private void cut(FibNode<E> child, FibNode<E> Vater) {
        Vater.degree--;
        child.parent = null;
        child.marked = false;
        insertNode(child);
    }

    private void fix_after_cut(FibNode<E> y) {
        FibNode<E> z = y.parent;
        if (z != null) {
            if (!y.marked) {
                y.marked = true;
            } else {
                cut(y, z);
                fix_after_cut(z);
            }
        }
    }

    //-------------------------------------------------------------------------------------
    @Override
    public boolean update(E elem, E updatedElem) {
        FibNode<E> x = find(elem);
        if (x != null) {
            updateElem(x, updatedElem);
            return true;
        }
        return false;
    }
//---------------------map------------------------------------------

    public void map(UnaryOperator<E> f) {
        LinkedList<FibNode<E>> temp = new LinkedList<>();
        FibNode<E> node;
        while (!rootlist.isEmpty()) {
            node = new FibNode<>(f.apply(deleteMax()));
            temp.add(node);
        }
        rootlist = temp;
        maxNode = rootlist.getFirst();
    }


 /*private void print(FibNode<E> node) {
        System.out.println(node.key);
        for (int i = 0; i < node.degree; i++) {
            print(node.child.get(i));
        }
    }

    public void printRoot() {
        for (FibNode<E> eFibNode : rootlist) {
            System.out.println("--");
            print(eFibNode);
        }

    }

    public static void main(String[] args) {
        FibonacciHeap<Integer> queue = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());
        queue.insert( 2 );
        queue.insert( 93 );
        queue.insert( 91 );
        queue.insert( 90 );
        queue.insert( 88 );


        //Tests für Update von ELemente die in der Queue nicht vorhand
        queue.deleteMax();

        queue.printRoot();
    }*/
}