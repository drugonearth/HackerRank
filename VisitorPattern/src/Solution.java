import java.sql.SQLOutput;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//https://www.hackerrank.com/challenges/java-vistor-pattern/problem

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);
}

class SumInLeavesVisitor extends TreeVis {
    int count = 0;

    public int getResult() {
        //implement this
        return count;
    }

    public void visitNode(TreeNode node) {
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        count += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    long product = 1;

    public int getResult() {
        return (int) product;
    }

    public void visitNode(TreeNode node) {
        if(node.getColor()==Color.RED)
        {
            product = (product*node.getValue())%1000000007;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor()==Color.RED)
        {
            product = (product*leaf.getValue())%1000000007;
        }
    }
}

class FancyVisitor extends TreeVis {
    int notLeafEven = 0;
    int greenLeaf = 0;

    public int getResult() {

        return Math.abs(notLeafEven - greenLeaf);
    }

    public void visitNode(TreeNode node) {


        if(node.getDepth()%2==0) notLeafEven += node.getValue();
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor()==Color.GREEN && leaf.getValue()!=0) greenLeaf += leaf.getValue();
    }
}

public class Solution {

    public static Tree solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] values = new int[n];
        Color[] colors = new Color[n];
        List<Integer>[] edges = new LinkedList[n];



        for(int i = 0; i < n; i++)
        {
            values[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++)
        {
            int c = sc.nextInt();
            if(c == 0) colors[i] = Color.RED;
            else colors[i] = Color.GREEN;
        }

        for(int i = 0; i < n; i++)
        {
            edges[i] = new LinkedList<>();
        }

        for(int i = 0; i < n-1; i++)
        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            edges[a-1].add(b);
            edges[b-1].add(a);
        }

        int redCount = 0;

        Tree root = createEdge(edges, values, colors, 0, 1);

        return root;
    }

    private static Tree createEdge(List<Integer>[] edges ,int[] values, Color[] colors, int dept, int number){
        if(edges[number-1].size() != 0) {
            TreeNode node = new TreeNode(values[number - 1], colors[number - 1], dept);
            for (int i : edges[number-1] ) {
                edges[i-1].remove((Integer)number);
                node.addChild(createEdge(edges ,values, colors, dept + 1, i));
            }
            return node;
        }
        else return new TreeLeaf(values[number-1], colors[number-1], dept);
    }

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
