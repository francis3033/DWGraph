package dwGraph;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


/* This class represents a directed weighted Graph. It's implemented by using 
 * a adjacency map. The only private field it has is a Map that would keep 
 * track of all of the data in the Graph. There are a total of 10 methods that 
 * support this class. Also there's a constructor that was added to initialize
 * the adjacency map need to store data(vertices and edges). 
 */
public class DWGraph<V extends Comparable<V>> {
   private Map<V, Map<V, Integer>> adjacencyList;
   
   //This is a simple constructor that initializes the adjacency map to a 
   //TreeMap.
   public DWGraph() {
      adjacencyList = new TreeMap<V, Map<V, Integer>>();
   }

   /* This method is supposed to create a new vertex if the parameter isn't 
    * null and returns true if it adds it, and false otherwise.
    */
  public boolean createVertex(V dataForVertex) {
    if (dataForVertex == null) {
       throw new IllegalArgumentException("Invalid Parameter");
    } else {
       boolean ans;
       if (adjacencyList.containsKey(dataForVertex)) {
          ans = false;
       } else {
          adjacencyList.put(dataForVertex, new TreeMap<V, Integer>());
          ans = true;
       }
       return ans;
    }
  }

  /* This method checks if the Graph contains a certain vertex in the graph.
   */
  public boolean isVertex(V dataForVertex) {
    if (dataForVertex == null) {
       throw new IllegalArgumentException("Invalid Parameter");
    } else {
       return adjacencyList.containsKey(dataForVertex);
    }
  }

  /* This method returns a collection of the vertices in the graph.
   */
  public Collection<V> getVertices() {
   return adjacencyList.keySet();
  }

  /* This method creates a new edge with the parameters given. First it checks
   * if the weight is negative, if it is negative, then it checks if the 
   * initial vertex and final vertex given are in the graph. If either of them 
   * are not in the graph, then it will add them to the graph and return true,
   * otherwise it will return false. If the weight of the edge given, is 
   * greater than or equal to 0, then the method will add the edge to the 
   * appropriate initial and final vertices and return true.
   */
  public boolean createEdge(V initialVertex, V finalVertex, int weight) {
    if (initialVertex == null || finalVertex == null) {
       throw new IllegalArgumentException("Invalid Parameter");
    } else {
       boolean ans = false;
     
       if (weight < 0) {
          ans = false;
          if (!adjacencyList.containsKey(initialVertex)) {
             createVertex(initialVertex);
             ans = true;
             if (!adjacencyList.containsKey(finalVertex)) {
                createVertex(finalVertex);
                ans = true;
             } 
          }  
       } else {
          for (V i : adjacencyList.keySet()) {
             if (initialVertex.equals(i)) {
                adjacencyList.get(initialVertex).put(finalVertex, weight);
                ans = true;
             }
          }
       }
       return ans;
    }
  }

  /* This method is supposed to return the weight of an edge with the given 
   * initial and final vertices. First, the method will check if the initial
   *  and final vertices are in the graph, and if they're in the graph, then 
   *  then weight of the edge will be returned. Otherwise, -1 will be returned 
   *  in any other circumstance.
   * 
   */
  public int edgeCost(V initialVertex, V finalVertex) {
    if (initialVertex == null || finalVertex == null) {
       throw new IllegalArgumentException("Invalid Parameter");
    } else {
       int ans = -1;
       
       if (adjacencyList.containsKey(initialVertex) 
             && adjacencyList.get(initialVertex).containsKey(finalVertex)) {
          ans = adjacencyList.get(initialVertex).get(finalVertex);
       }
       return ans;
    }
  }

  /* This method is supposed to remove an edge from the graph. First method 
   * will check if the initial and final vertices are a part of the graph. If 
   * they are then the method will proceed to delete the edge and return true.
   * Otherwise, the method will return false.
   */
  public boolean removeEdge(V initialVertex, V finalVertex) {
     if (initialVertex == null || finalVertex == null) {
        throw new IllegalArgumentException("Invalid Parameter");
     } else {
        boolean ans = false;
        
        if (adjacencyList.containsKey(initialVertex) 
              && adjacencyList.get(initialVertex).containsKey(finalVertex)) {
           int  i = adjacencyList.get(initialVertex).get(finalVertex);
           adjacencyList.get(initialVertex).remove(finalVertex, i);
           ans = true;
        }
        return ans;
     }
  }

  /* This method will remove a vertex from the graph. First the method will 
   * check if the parameter given is part of the graph, and if it is, then the 
   * method will get rid of the vertex, and any edges that pointed to, or were 
   * coming out of the vertex. You can't have an edge without at least 2 
   * vertices in this graph. After successfully removing the vertex, the method 
   * will return true, but false otherwise. 
   */
  public boolean removeVertex(V dataForVertex) {
     if (dataForVertex == null) {
        throw new IllegalArgumentException("Invalid Parameter");
     } else {
        boolean ans = false;
        
        if (adjacencyList.containsKey(dataForVertex)) {
           adjacencyList.remove(dataForVertex, adjacencyList.get(dataForVertex));
           
           for (V i : adjacencyList.keySet()) {
              for (V v : adjacencyList.get(i).keySet()) {
                 if (dataForVertex.equals(v)) {
                    int n = adjacencyList.get(i).get(dataForVertex);
                    adjacencyList.get(i).remove(dataForVertex, n);
                    break;
                 }
              }
           }
           ans = true;
        }
        return ans;
     }
  }

  /* This method is supposed to return a collection of the vertices that are 
   * adjacent to the given vertex. First it will make a new Set, if the vertex
   * isn't in the graph, then null will be return. If the vertex has no 
   * adjacencies then an empty collection will be returned. If everything is 
   * successful,  then a collection of the adjacent vertices will be returned 
   * in a collection.
   */
  public Collection<V> adjacentVertices(V dataForVertex) {
     if (dataForVertex == null) {
        throw new IllegalArgumentException("Invalid Parameter");
     } else {
        TreeSet<V> ans = new TreeSet<V>();
        if (!adjacencyList.containsKey(dataForVertex)) {
           return null;
        } else {
           for (V i : adjacencyList.get(dataForVertex).keySet()) {
              if (dataForVertex.equals(i)) {
                 ans.add(i);
              }
           }
           ans.addAll(adjacencyList.get(dataForVertex).keySet());
        }
        return ans;
     }
  }

  /* This method will do the exact same thing the method above does, the only 
   * difference is that this method will return a collection of vertices that
   * are the predecessors of the vertex given in the parameter.
   */
  public Collection<V> predecessorsOfVertex(V destVertex) {
     if (destVertex == null) {
        throw new IllegalArgumentException("Invalid Parameter");
     } else {
        TreeSet<V> ans = new TreeSet<V>();
        if (!adjacencyList.containsKey(destVertex)) {
           return null;
        } else {
           for (V i : adjacencyList.get(destVertex).keySet()) {
              if (destVertex.equals(i)) {
                 ans.add(i);
              }
           }
           for (V x : adjacencyList.keySet()) {
              for (V y : adjacencyList.get(x).keySet()) {
                 if (destVertex.equals(y)) {
                    ans.add(x);
                 }
              }
           }
        }
        return ans;
     }
  }

  public DWGraph<V> divideGraph(Collection<V> verticesForNewGraph) {
    if (verticesForNewGraph == null) {
       throw new IllegalArgumentException("Invalid Parameter");
    } else {
       DWGraph<V> newGraph = new DWGraph<V>();
       
       TreeSet<V> newVertices = new TreeSet<V>(); 
       for (V v : verticesForNewGraph) {
          if (adjacencyList.containsKey(v)) {
             newVertices.add(v);
          }
       }
       for (V source : adjacencyList.keySet()) {
          for (V dest : adjacencyList.get(source).keySet()) {
             
             if (newVertices.contains(source) 
                   && newVertices.contains(dest)) {
                newGraph.createVertex(source);
                newGraph.createVertex(dest);
                newGraph.createEdge(source, dest, edgeCost(source, dest));
                
                removeVertex(source);
                removeVertex(dest);
             } 
             else if (!newVertices.contains(source) 
                   && !newVertices.contains(dest)) {
                break;
             } else if (!newVertices.contains(source) 
                   || !newVertices.contains(dest)) {
                removeVertex(source);
                removeVertex(dest);
             }
          }
       }
       return newGraph;
    }
  }

}
