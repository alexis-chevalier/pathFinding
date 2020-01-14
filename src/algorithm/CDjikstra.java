package algorithm;

import entites.CGraph;
import entites.CNode;
import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class CDjikstra {

    /**
     * Utilise Djikstra et met à jour le graph.
     *
     * @param graph
     * @return currentNode
     */
    public void utiliser(CGraph graph) {
        CNode currentNode = graph.getNodeActuel();
        //System.out.println(graph.getEndNode().toString());
        ArrayList<CNode> listNodePreviousStep = new ArrayList<>();
        listNodePreviousStep.add(currentNode);
        ArrayList<CNode> listNodeVoisins = new ArrayList<>();
        int poids = 1;
        boolean fin = false;

        while (!fin) {
            for (int j = 0; j < listNodePreviousStep.size() && fin == false; j++) {
                currentNode = listNodePreviousStep.get(j);
                graph.setNodeActuel(currentNode);
                ArrayList<CNode> listNodeVoisinsTemp = graph.getVoisins();
                //Ajout des nouveaux voisins dans listNodeVoisins.
                for (int k = 0; k < listNodeVoisinsTemp.size(); k++) {
                    if (!listNodeVoisins.contains(listNodeVoisinsTemp.get(k))) {
                        listNodeVoisins.add(listNodeVoisinsTemp.get(k));
                    }
                }
                //System.out.println("    Liste des voisins :");
                for (int i = 0; i < listNodeVoisinsTemp.size() && fin == false; i++) {
                    if ("undefined".equals(listNodeVoisinsTemp.get(i).getStatus())) {
                        listNodeVoisinsTemp.get(i).setPoids(poids);
                        listNodeVoisinsTemp.get(i).setStatus("visited");
                    } else if ("end".equals(listNodeVoisinsTemp.get(i).getStatus())) {
                        listNodeVoisinsTemp.get(i).setPoids(poids);
                        System.out.println("FIN !");
                        System.out.println("poid: "+poids);
                        graph.setNodeActuel(graph.getNodeFin());
                        fin = true;
                    }
                    //System.out.println("    " + listNodeVoisins.get(i).toString());
                }
            }
            if (!fin) {
                listNodePreviousStep = new ArrayList<>(listNodeVoisins);
                listNodeVoisins.clear();
                poids++;
            }
        }

        ArrayList<CNode> listNodeApresDjikstra = graph.getListeNode();
        graph.setListeNode(listNodeApresDjikstra);
    }

    public static void main(String[] args) {
        CGraph graph = new CGraph(50, 1, 1, 50, 1);
        graph.genererNodes();
        CDjikstra djikstra = new CDjikstra();

        djikstra.utiliser(graph);
    }
}
