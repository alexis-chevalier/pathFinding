package entites;

import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class CGraph {

    private int taille;
    private ArrayList<CNode> listeNode = new ArrayList<>();
    private int[] nodeDebutXY;
    private int[] nodeFinXY;
    private CNode nodeActuel;
    private CNode nodeFin;
    private CNode nodeDebut;

    public CGraph(int taille, int startNodeX, int startNodeY, int endNodeX, int endNodeY) {
        this.setTaille(taille);
        this.setNodeDebutXY(startNodeX, startNodeY);
        this.setNodeFinXY(endNodeX, endNodeY);
    }

    public CGraph(int taille) {
        this.setTaille(taille);
        this.setNodeDebutXY(1, 1);
        this.setNodeFinXY(3, 1);
    }

    public ArrayList<CNode> getListeNode() {
        return listeNode;
    }

    public void setNodeDebutXY(int x, int y) {
        int startNode[] = {y, x};
        this.nodeDebutXY = startNode;
    }

    public int[] getNodeDebutXY() {
        return this.nodeDebutXY;
    }

    public void setNodeFinXY(int x, int y) {
        int endNode[] = {y, x};
        this.nodeFinXY = endNode;
    }

    public int[] getNodeFinXY() {
        return this.nodeFinXY;
    }

    public void setListeNode(ArrayList<CNode> listeNode) {
        this.listeNode = listeNode;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        if (taille > 1) {
            this.taille = taille;
        } else {
            System.out.println("Taille trop petite !");
        }
    }

    public void setNodeDebut(CNode nodeDebut) {
        this.nodeDebut = nodeDebut;
    }

    public CNode getNodeDebut() {
        return this.nodeDebut;
    }

    public void setNodeActuel(CNode nodeActuel) {
        this.nodeActuel = nodeActuel;
    }

    public CNode getNodeActuel() {
        return this.nodeActuel;
    }

    public void setNodeFin(CNode nodeFin) {
        this.nodeFin = nodeFin;
    }

    public CNode getNodeFin() {
        return this.nodeFin;
    }

    public void genererNodes() {
        ArrayList<CNode> listNode = new ArrayList<CNode>();
        for (int j = 1; j <= this.getTaille(); j++) {
            for (int i = 1; i <= this.getTaille(); i++) {
                String status = "undefined";
                CNode node = new CNode(i, j, status);
                listNode.add(node);
            }
        }

        int idStartNode = 0;
        if (this.getNodeDebutXY()[0] == 1) {
            idStartNode = this.getNodeDebutXY()[1] - 1;
        } else {
            idStartNode = ((this.getNodeDebutXY()[0] - 1) * this.getTaille() + this.getNodeDebutXY()[1]) - 1;
        }
        listNode.get(idStartNode).setStatus("start");
        this.setNodeDebut(listNode.get(idStartNode));
        this.getNodeDebut().setPoids(0);
        this.setNodeActuel(listNode.get(idStartNode));

        int idEndNode = 1;
        if (this.getNodeFinXY()[0] == 1) {
            idEndNode = this.getNodeFinXY()[1] - 1;
        } else {
            idEndNode = ((this.getNodeFinXY()[0] - 1) * this.getTaille() + this.getNodeFinXY()[1]) - 1;
        }
        listNode.get(idEndNode).setStatus("end");
        this.setNodeFin(listNode.get(idEndNode));
        this.setListeNode(listNode);
    }

    public ArrayList<CNode> getVoisins() {
        ArrayList<CNode> listNodeVoisins = new ArrayList<>();
        int nb = 0;
        int tailleMax = this.getTaille() * this.getTaille();
        CNode currentNodeTemp = this.getNodeActuel();
        int nbMin = (currentNodeTemp.getX() * this.getTaille()) - this.getTaille();
        int nbMax = currentNodeTemp.getX() * this.getTaille() - 1;

        if (currentNodeTemp.getX() > 0) {
            nb = ((currentNodeTemp.getX() - 1) * this.getTaille() + currentNodeTemp.getY()) - 2;
        } else {
            nb = currentNodeTemp.getY() - 2;
        }
        if (nb >= nbMin && nb <= nbMax) {
            CNode nodeHaut = this.getListeNode().get(nb);
            listNodeVoisins.add(nodeHaut);

        }

        if (currentNodeTemp.getX() > 0) {
            nb = ((currentNodeTemp.getX() - 1) * this.getTaille() + currentNodeTemp.getY());
        } else {
            nb = currentNodeTemp.getY();
        }
        if (nb >= nbMin && nb <= nbMax) {
            CNode nodeBas = this.getListeNode().get(nb);
            listNodeVoisins.add(nodeBas);

        }

        if (currentNodeTemp.getX() > 0) {
            nb = ((currentNodeTemp.getX() - 1) * this.getTaille() + currentNodeTemp.getY()) - 1 - this.getTaille();
        } else {
            nb = currentNodeTemp.getY() - this.getTaille();
        }
        if (nb >= 0 && nb < tailleMax) {
            CNode nodeGauche = this.getListeNode().get(nb);
            listNodeVoisins.add(nodeGauche);

        }

        if (currentNodeTemp.getX() > 0) {
            nb = ((currentNodeTemp.getX() - 1) * this.getTaille() + currentNodeTemp.getY()) - 1 + this.getTaille();
        } else {
            nb = currentNodeTemp.getY() + this.getTaille();
        }
        if (nb >= 0 && nb < tailleMax) {
            CNode nodeDroite = this.getListeNode().get(nb);
            listNodeVoisins.add(nodeDroite);

        }

        return listNodeVoisins;
    }

}
