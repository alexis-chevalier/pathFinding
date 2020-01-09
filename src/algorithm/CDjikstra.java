package algorithm;

import entites.CGraph;
import entites.CNode;
import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class CDjikstra {
    
    private CGraph graph;

    public CGraph getGraph() {
        return graph;
    }

    public void setGraph(CGraph graph) {
        this.graph = graph;
    }

    /**
     * Renvoie la prochain currentNode.
     * @param graph
     * @return currentNode
     */
    public CNode use(CGraph graph){
        ArrayList<CNode> listNode = graph.getListNode();
        CNode currentNode = graph.getCurrentNode();
        CNode endNode = graph.getEndNode();
        
        
        
        return currentNode;
    }
}
