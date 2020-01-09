package entites;

import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class CGraph {
    
    private int taille;
    private ArrayList<CNode> listNode = new ArrayList<>();
    private int startNodeXY[];
    private int endNodeXY[];
    private CNode currentNode;
    private CNode endNode;
    private CNode startNode;
    private ArrayList<CNode> listNodePreviousSteps = new ArrayList<>();

    public CGraph(int taille, int startNodeX, int startNodeY, int endNodeX,int endNodeY) {
        this.setTaille(taille);
        this.setStartNodeXY(startNodeX, startNodeY);
        this.setEndNodeXY(endNodeX, endNodeY);
    }
    
    public CGraph(int taille) {
        this.setTaille(taille);
        this.setStartNodeXY(1, 1);
        this.setEndNodeXY(3,1);
    }
    
    public ArrayList<CNode> getListNode() {
        return listNode;
    }

    public void setStartNodeXY(int x, int y){
        int startNode[] = {x,y};
        this.startNodeXY = startNode;
    }
    
    public int[] getStartNodeXY(){
        return this.startNodeXY;
    }
    
    public void setEndNodeXY(int x, int y){
        int endNode[] = {x,y};
        this.endNodeXY = endNode;
    }
    
    public int[] getEndNodeXY(){
        return this.endNodeXY;
    }
    
    public void setListNode(ArrayList<CNode> listNode) {
        this.listNode = listNode;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        if (taille > 1){
            this.taille = taille;
        }
        else{
            System.out.println("Taille trop petite !");
        }
    }
    
    public void setStartNode(CNode startNode) {
        this.startNode = startNode;
    }
    
    public CNode getStartNode(){
        return this.startNode;
    }
    
    public void setCurrentNode(CNode currentNode) {
        this.currentNode = currentNode;
    }
    
    public CNode getCurrentNode(){
        return this.currentNode;
    }
    
    public void setEndNode(CNode endNode) {
        this.endNode = endNode;
    }
    
    public CNode getEndNode(){
        return this.endNode;
    }
    
    public void generateNodes(){
        ArrayList<CNode> listNode = new ArrayList<CNode>();
        for (int j = 1; j <= this.getTaille(); j++) {
            for (int i = 1; i <= this.getTaille(); i++) {
                String status = "undefined";
                CNode node = new CNode(i,j,status);
                listNode.add(node);
            }
        }
        
        int idStartNode = 0;
        if (this.getStartNodeXY()[0] == 0){
            idStartNode = this.getStartNodeXY()[1]-1;
        }
        else{
            idStartNode = ((this.getStartNodeXY()[0]-1)*this.getTaille() + this.getStartNodeXY()[1])-1;
        }
        listNode.get(idStartNode).setStatus("start");
        this.setStartNode(listNode.get(idStartNode));
        this.getStartNode().setPoids(0);
        this.setCurrentNode(listNode.get(idStartNode));
        
        int idEndNode = 1;
        if (this.getEndNodeXY()[0] == 0){
            idEndNode = this.getEndNodeXY()[1]-1;
        }
        else{
            idEndNode = ((this.getEndNodeXY()[0]-1)*this.getTaille() + this.getEndNodeXY()[1])-1;
        }
        listNode.get(idEndNode).setStatus("end");
        this.setEndNode(listNode.get(idEndNode));
        this.setListNode(listNode);
    }
    
    public ArrayList<CNode> getVoisins(){
        ArrayList<CNode> listNodeVoisins = new ArrayList<>();
        int nb = 0;
        CNode currentNode = this.getCurrentNode();
        if (currentNode.getX() > 0){
            nb = ((currentNode.getX()-1)*this.getTaille() + currentNode.getY())-2;
        }
        else{
            nb = currentNode.getY()-2;
        }
        CNode nodeHaut = this.getListNode().get(nb);
        listNodeVoisins.add(nodeHaut);
        
        if (currentNode.getX() > 0){
            nb = ((currentNode.getX()-1)*this.getTaille() + currentNode.getY());
        }
        else{
            nb = currentNode.getY();
        }
        CNode nodeBas = this.getListNode().get(nb);
        listNodeVoisins.add(nodeBas);
        
        if (currentNode.getX() > 0){
            nb = ((currentNode.getX()-1)*this.getTaille() + currentNode.getY())-1-this.getTaille();
        }
        else{
            nb = currentNode.getY()-this.getTaille();
        }
        CNode nodeGauche = this.getListNode().get(nb);
        listNodeVoisins.add(nodeGauche);
        
        if (currentNode.getX() > 0){
            nb = ((currentNode.getX()-1)*this.getTaille() + currentNode.getY())-1+this.getTaille();
        }
        else{
            nb = currentNode.getY()+this.getTaille();
        }
        CNode nodeDroite = this.getListNode().get(nb);
        listNodeVoisins.add(nodeDroite);
        this.getListNodePreviousSteps().add(nodeBas);
        this.getListNodePreviousSteps().add(nodeHaut);
        this.getListNodePreviousSteps().add(nodeGauche);
        this.getListNodePreviousSteps().add(nodeDroite);
        
        return listNodeVoisins;
    }

    public ArrayList<CNode> getListNodePreviousSteps() {
        return listNodePreviousSteps;
    }

    public void setListNodePreviousSteps(ArrayList<CNode> listNodePreviousSteps) {
        this.listNodePreviousSteps = listNodePreviousSteps;
    }    
    
    public static void main(String[] args) {
        CGraph graph = new CGraph(5,3,3,3,4);
        graph.generateNodes();
        System.out.println("Current node: "+graph.getCurrentNode().toString());
        ArrayList<CNode> ListNodePreviousSteps = graph.getListNodePreviousSteps();
        ArrayList<CNode> listNodeVoisins = graph.getVoisins();
        /*int poids = 1;
        for (int j = 0; j < ListNodePreviousSteps.size(); j++) {
            listNodeVoisins = graph.getVoisins();
            for (int i = 0; i < listNodeVoisins.size(); i++) {
                listNodeVoisins.get(i).setPoids(poids);
                System.out.println(listNodeVoisins.get(i).toString());
            }
            poids++;
        }*/
        //Presque
        for (int i = 0; i < listNodeVoisins.size(); i++) {
            if (listNodeVoisins.get(i).getStatus() == "undefined"){
                listNodeVoisins.get(i).setPoids(1);
                listNodeVoisins.get(i).setStatus("visited");
            }
            else if (listNodeVoisins.get(i).getStatus() == "end"){
                System.out.println("c'est fini !");
            }
            System.out.println(listNodeVoisins.get(i).toString());
        }
        
    }
    
}
