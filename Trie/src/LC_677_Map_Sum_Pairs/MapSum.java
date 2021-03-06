package LC_677_Map_Sum_Pairs;

import java.util.TreeMap;

public class MapSum {

    private class Node {

        public int value;
        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node(0));
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {

        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    // 加上node以及遍历node对应的所有子树，他们的权重全部加起来返回
    private int sum(Node node) {
        int res = node.value;
        for (char c : node.next.keySet())    // <- 循环遍历终止条件包含了递归终止条件：如果node.next.size()没有了，就是到了叶子结点的时候，返回当前node的权重就好了
            res += sum(node.next.get(c));
        return res;
    }
}
