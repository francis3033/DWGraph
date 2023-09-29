package tests;

import org.junit.*;

import dwGraph.DWGraph;

import static org.junit.Assert.*;

public class StudentTests {

   @Test
   public void testCreateVertex01() {
      DWGraph<Integer> graph = new DWGraph<Integer>(); 
      
      assertTrue(graph.createVertex(1));
      assertTrue(graph.createVertex(100));
      
      assertTrue(graph.isVertex(100));
      
      assertFalse(graph.isVertex(27));
      
      assertFalse(graph.createVertex(1));
   }
   @Test
   public void testRemoveVertices01() {
      DWGraph<Integer> graph = new DWGraph<Integer>(); 
      
      assertTrue(graph.createVertex(1));
      assertTrue(graph.createVertex(100));
      
      assertTrue(graph.removeVertex(100));
      
      assertFalse(graph.isVertex(100));
      
   }
   
   @Test
   public void testAddRemoveEdge01() {
      DWGraph<Integer> graph = new DWGraph<Integer>(); 
      assertTrue(graph.createVertex(1));
      assertTrue(graph.createVertex(100));
      
      assertTrue(graph.createEdge(1, 100, 25));
      
      assertTrue(graph.createVertex(25));
      assertTrue(graph.createVertex(18));
      
      assertFalse(graph.createEdge(1, 100, -5));
      
      assertTrue(graph.createEdge(18, 18, 2));
   }
   
   @Test
   public void testAddRemoveEdge02() {
      DWGraph<Integer> graph = new DWGraph<Integer>();
      int[] arr = {1, 100};
      
      assertTrue(graph.createVertex(1));
      assertTrue(graph.createVertex(100));
      
      assertTrue(graph.createEdge(1, 100, 25));
      
      assertTrue(graph.removeEdge(1, 100));
      
      assertTrue(graph.isVertex(1));
   }
   
}
