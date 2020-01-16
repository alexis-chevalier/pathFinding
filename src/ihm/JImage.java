package ihm;

import algorithm.CDjikstra;
import entites.CGraph;
import entites.CNode;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Alexis
 */
public class JImage extends JComponent {

    private int taille;
    private CGraph graph;

    private void setTaille(int taille) {
        this.taille = taille;
    }

    private CGraph getGraph() {
        return this.graph;
    }

    public JImage(int taille, CGraph graph) {
        setTaille(taille);
        setGraph(graph);
    }

    private void setGraph(CGraph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        int taille = r.nextInt((50 - 3) + 1) + 3;
        int debutX = r.nextInt((taille - 3) + 1) + 3;
        int debutY = debutX;
        int finX = r.nextInt((taille - 3) + 1) + 3;
        int finY = r.nextInt((taille - 3) + 1) + 3;
        
        CGraph graph = new CGraph(taille, debutX, debutY, finX, finY);
        JImage image = new JImage(graph.getTaille(), graph);
        image.faireDessin();
    }

    public void faireDessin() throws InterruptedException {
        JFrame frame = new JFrame("Djikstra");
        CGraph graphTemp = this.getGraph();
        graphTemp.genererNodes();
        CDjikstra djikstra = new CDjikstra();
        ArrayList<CNode> listeVoisinsATester = djikstra.prochaineEtape(graphTemp);
        frame.add(new JImage(graphTemp.getTaille(), graphTemp));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(JImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (graphTemp.getNodeFin().getPoids() == Integer.MAX_VALUE) {
            listeVoisinsATester = djikstra.prochaineEtape(graphTemp, listeVoisinsATester);
            frame.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException ex) {
                Logger.getLogger(JImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        djikstra.getChemin(graphTemp);
        frame.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.black);
        float m = (1f / this.taille);
        for (float i = 0; i < this.taille; i++) {
            int resultat = (int) (800 * m * i);
            g.drawLine(resultat, 0, resultat, 600);
        }

        for (int i = 0; i < this.taille; i++) {
            int resultat = (int) (600 * m * i);
            g.drawLine(0, resultat, 800, resultat);
        }

        ArrayList<CNode> listeNode = graph.getListeNode();

        float coteLargeur = 1f / this.taille * 600 - 1;
        float coteLongeur = 1f / this.taille * 800 - 1;

        for (int i = 0; i < listeNode.size(); i++) {
            //Choix couleur
            g.setColor(Color.white);
            if (null != listeNode.get(i).getStatus()) {
                switch (listeNode.get(i).getStatus()) {
                    case "visited":
                        g.setColor(Color.blue);
                        break;
                    case "start":
                        g.setColor(Color.green);
                        break;
                    case "end":
                        g.setColor(Color.red);
                        break;
                    case "retour":
                        g.setColor(Color.green);
                        break;
                    default:
                        break;
                }
            }

            float nodeX = (1f / this.taille * 800) * (listeNode.get(i).getX() - 1) + 1;
            float nodeY = (1f / this.taille * 600) * Math.abs(this.taille - listeNode.get(i).getY()) + 1;

            g.fillRect((int) nodeX, (int) nodeY, (int) coteLongeur, (int) coteLargeur);
        }
    }
}
