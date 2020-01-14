/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entites.CGraph;
import entites.CNode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexis
 */
public class JUnitTest {
    
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCNode(){
        CNode node = new CNode(1,2,"undefined");
        assertEquals(1, node.getX());
        assertEquals(2, node.getY());
        assertEquals("undefined", node.getStatus());
        int[] XY = {1,2};
        assertEquals(XY[0], node.getX());
        assertEquals(XY[1], node.getY());
        node.setPoids(123);
        assertEquals(123, node.getPoids());
        node.setStatus("visited");
        assertEquals("visited", node.getStatus());
    }
    
    @Test
    public void testCGraph(){
        CGraph graph = new CGraph(5,2,1,5,4);
        assertEquals(5, graph.getTaille());
        graph.genererNodes();
        assertEquals((graph.getTaille()*graph.getTaille()), graph.getListeNode().size());
        assertEquals(2,graph.getNodeDebut().getX());
        assertEquals(1,graph.getNodeDebut().getY());
        assertEquals(5,graph.getNodeFin().getX());
        assertEquals(4,graph.getNodeFin().getY());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
