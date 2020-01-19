package algorithm;

import entites.CGraph;
import entites.CNode;
import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class CDijkstra {

    /**
     * Trouve le chemin le plus court et met à jour le graph.
     *
     * @param graph
     */
    public void getChemin(CGraph graph) {
        graph.setNodeActuel(graph.getNodeFin());
        boolean unSurDeux = false;

        while (graph.getNodeActuel() != graph.getNodeDebut()) {
            CNode VoisinsMoinsDePoid = null;
            ArrayList<CNode> listeVoisins = graph.getVoisins();
            for (int i = 0; i < listeVoisins.size(); i++) {
                CNode Voisins = listeVoisins.get(i);
                if (VoisinsMoinsDePoid == null || Voisins.getPoids() < VoisinsMoinsDePoid.getPoids()) {
                    VoisinsMoinsDePoid = Voisins;
                }
            }
            if (unSurDeux) {
                VoisinsMoinsDePoid.setStatus("retour");
                graph.setNodeActuel(VoisinsMoinsDePoid);
            } else {
                int y = VoisinsMoinsDePoid.getX();
                int x = VoisinsMoinsDePoid.getY();
                ArrayList<CNode> listeNode = graph.getListeNode();
                for (int j = 0; j < listeNode.size(); j++) {
                    if (listeNode.get(j).getX() == x && listeNode.get(j).getY() == y) {
                        listeNode.get(j).setStatus("retour");
                        graph.setNodeActuel(listeNode.get(j));
                    }
                }
            }
            System.out.println(VoisinsMoinsDePoid.toString());
        }
        graph.setListeNode(graph.getListeNode());
    }

    /**
     * Avance d'une étape et met à jour le graph.
     *
     * @return La liste des nodes voisins pour la prochaine étape.
     * @param graph
     */
    public ArrayList<CNode> prochaineEtape(CGraph graph) {
        CNode currentNode = graph.getNodeActuel();
        ArrayList<CNode> listNodeVoisins = graph.getVoisins();
        ArrayList<CNode> listeNodeVoisinsFinal = new ArrayList<>();
        int poids = currentNode.getPoids() + 1;

        for (int i = 0; i < listNodeVoisins.size(); i++) {
            if ("undefined".equals(listNodeVoisins.get(i).getStatus())) {
                listNodeVoisins.get(i).setPoids(poids);
                listNodeVoisins.get(i).setStatus("visited");
            } else if ("end".equals(listNodeVoisins.get(i).getStatus())) {
                listNodeVoisins.get(i).setPoids(poids);
                System.out.println("FIN !");
                System.out.println("poid: " + poids);
                graph.setNodeActuel(graph.getNodeFin());
            }
            listeNodeVoisinsFinal.add(listNodeVoisins.get(i));
        }

        ArrayList<CNode> listeNodeApresUneEtape = graph.getListeNode();
        graph.setListeNode(listeNodeApresUneEtape);

        return listeNodeVoisinsFinal;

    }

    /**
     * Avance d'une étape et met à jour le graph.
     *
     * @return La liste des nodes voisins pour la prochaine étape.
     * @param graph
     */
    public ArrayList<CNode> prochaineEtape(CGraph graph, ArrayList<CNode> listeNodeATester) {
        CNode currentNode = graph.getNodeActuel();
        ArrayList<CNode> listeNodeVoisinsFinal = new ArrayList<>();
        int poids = currentNode.getPoids() + 1;

        //Pour chaque node à tester
        for (int j = 0; j < listeNodeATester.size(); j++) {
            graph.setNodeActuel(listeNodeATester.get(j));
            ArrayList<CNode> listNodeVoisins = graph.getVoisins();
            //On regarde les voisins
            for (int i = 0; i < listNodeVoisins.size(); i++) {
                if ("undefined".equals(listNodeVoisins.get(i).getStatus())) {
                    listNodeVoisins.get(i).setPoids(poids);
                    listNodeVoisins.get(i).setStatus("visited");
                    listeNodeVoisinsFinal.add(listNodeVoisins.get(i));
                } else if ("end".equals(listNodeVoisins.get(i).getStatus())) {
                    listNodeVoisins.get(i).setPoids(poids);
                    System.out.println("FIN !");
                    System.out.println("poid: " + poids);
                    graph.setNodeActuel(graph.getNodeFin());
                    listeNodeVoisinsFinal.add(listNodeVoisins.get(i));
                }
                //On l'ajoute à la liste finale

            }
        }
        ArrayList<CNode> listeNodeApresUneEtape = graph.getListeNode();
        System.out.println("listeNodeVoisinsFinal:" + listeNodeVoisinsFinal.size());
        graph.setListeNode(listeNodeApresUneEtape);

        return listeNodeVoisinsFinal;

    }

    /**
     * Utilise Djikstra et met à jour le graph.
     *
     * @param graph
     */
    public void utiliser(CGraph graph) {
        CNode currentNode = graph.getNodeActuel();
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
                for (int k = 0; k < listNodeVoisinsTemp.size(); k++) {
                    if (!listNodeVoisins.contains(listNodeVoisinsTemp.get(k))) {
                        listNodeVoisins.add(listNodeVoisinsTemp.get(k));
                    }
                }
                for (int i = 0; i < listNodeVoisinsTemp.size() && fin == false; i++) {
                    if ("undefined".equals(listNodeVoisinsTemp.get(i).getStatus())) {
                        listNodeVoisinsTemp.get(i).setPoids(poids);
                        listNodeVoisinsTemp.get(i).setStatus("visited");
                    } else if ("end".equals(listNodeVoisinsTemp.get(i).getStatus())) {
                        listNodeVoisinsTemp.get(i).setPoids(poids);
                        System.out.println("FIN !");
                        System.out.println("poid: " + poids);
                        graph.setNodeActuel(graph.getNodeFin());
                        fin = true;
                    }
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
        CGraph graph = new CGraph(4, 1, 1, 4, 4);
        graph.genererNodes();
        CDijkstra djikstra = new CDijkstra();

        //djikstra.utiliser(graph);
        ArrayList<CNode> listeNodeATester = djikstra.prochaineEtape(graph);
        while (graph.getNodeFin().getPoids() == Integer.MAX_VALUE) {
            listeNodeATester = djikstra.prochaineEtape(graph, listeNodeATester);
        }
    }
}
