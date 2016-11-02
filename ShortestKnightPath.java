import java.util.*;

/**
 * Created by cheeyim on 2/11/2016.
 */
public class ShortestKnightPath {

    // Reference: http://yanbraslavsky.blogspot.my/2016/01/chess-knight-shortest-path-problem-java.html

    public static void main(String args[]) {

        Integer source = 0, dest = 63;
        final List<Integer> path = new ArrayList<Integer>();
        Map<Integer, List<Integer>> graph = buildGraph();

        HashMap<Integer, Integer> parentToChild = findPathViaBfs(graph, source, dest);
        path.add(dest);
        Integer par = parentToChild.get(dest);
        while (par != null) {
            path.add(par);
            par = parentToChild.get(par);
        }

        Collections.reverse(path);
        System.out.println( "Min move to reach destination: " + (path.size() - 1));
    }

    private static HashMap<Integer, Integer> findPathViaBfs(Map<Integer, List<Integer>> graph,
                                                      int curr, int dest) {

        final HashMap<Integer, Integer> parentToChild = new HashMap<Integer, Integer>();
        Integer currParent = -1;

        List<Integer> visitedNodes = new ArrayList<Integer>();
        Queue q = new LinkedList();
        q.add(new Integer(curr));
        visitedNodes.add(new Integer(curr));

        while (!q.isEmpty()) {
            Integer i = (Integer) q.poll();

            currParent = i;

            for (Integer child : graph.get(new Integer(i))) {
                if (!visitedNodes.contains(child)) {
                    visitedNodes.add(new Integer(child));
                    q.add(child);

                    parentToChild.put(child, currParent);
                    if (child == dest) {
                        q = new LinkedList();
                        break;
                    }
                }
            }
        }

        return parentToChild;
    }

    private static Map<Integer, List<Integer>> buildGraph() {
        Map<Integer, List<Integer>> graph = new HashMap ();

        for (int i =0 ; i<=63; i++) {
            graph.put(new Integer(i), getAllMoves(i));
        }

        return graph;
    }

    private static List<Integer> getAllMoves(int src) {
        int col = src % 8;
        int row = src / 8;
        int destCol = -1, destRow = -1;
        List<Integer> moves = new ArrayList<Integer>();

        //move left - top
        destCol = col - 2;
        destRow = row - 1;
        getValidMove(destRow, destCol, moves);

        //move left - bottom
        destCol = col - 2;
        destRow = row + 1;
        getValidMove(destRow, destCol, moves);

        //move right - top
        destCol = col + 2;
        destRow = row - 1;
        getValidMove(destRow, destCol, moves);

        //move right - bottom
        destCol = col + 2;
        destRow = row + 1;
        getValidMove(destRow, destCol, moves);

        //move top - left
        destCol = col - 1;
        destRow = row - 2;
        getValidMove(destRow, destCol, moves);

        //move top - right
        destCol = col + 1;
        destRow = row - 2;
        getValidMove(destRow, destCol, moves);

        //move bottom - left
        destCol = col - 1;
        destRow = row + 2;
        getValidMove(destRow, destCol, moves);

        //move bottom - right
        destCol = col + 1;
        destRow = row + 2;
        getValidMove(destRow, destCol, moves);

        return moves;
    }

    public static void getValidMove(int row, int col, List<Integer> list) {
        if (row >= 0 && row <= 7 && col >= 0 && col <= 7) {
            int num = (row * 8) + col;

            list.add(new Integer(num));
        }
    }
}
