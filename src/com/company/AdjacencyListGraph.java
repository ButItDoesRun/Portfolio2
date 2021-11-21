package com.company;
import java.util.ArrayList;

public class AdjacencyListGraph {
    private ArrayList<Vertex> vertices; //list of vertices


    public AdjacencyListGraph(){
        vertices = new ArrayList<Vertex>();
    }


    //Function for making a vertex
    public void addVertex(Vertex v){
        vertices.add(v);
    }

    //Function for making a edge
    public void newEdge(Vertex from, Vertex to, Integer dist){
        if(!vertices.contains(from) && vertices.contains(to)){
            System.out.println("Vertex not found");
            return;

        }
        //this does that two edges are added pr. newEdge() call to make the graph undirected
        Edge edgeFromTo = new Edge(from, to, dist);
        Edge edgeToFrom = new Edge(to, from, dist);

    }

    //A function that prints the graph
    public void printGraph(){
        Vertex currentV;
        for(int i = 0; i < vertices.size(); i++){
            currentV = vertices.get(i);
            System.out.println("Edges from Vertex: " + currentV.getName());
            for(int j = 0; j < currentV.getOutEdges().size(); j++){
                Edge currentE = currentV.getOutEdges().get(j);
                System.out.println("To " + currentE.getToV().getName() + " weight: " + currentE.getWeight());
            }
            System.out.println(" ");

        }
    }


    public void MSTPrims() {
        MinHeap<Vertex> Q = new MinHeap<>();

        for (int vertex = 0; vertex < vertices.size(); vertex++) { //O(V)
            //all vertex dist are set to Max_value (infinity)
            vertices.get(vertex).setDistance(Integer.MAX_VALUE); //the heap key O(1)
            //since we don't start out with a predecessor before we iterate we set them all to null
            vertices.get(vertex).setPrevious(null); //the heap parent O(1)
            Q.insert(vertices.get(vertex)); //creates a queue for all vertices in the graph
            //To insert into a heap is O(log 2^V)  --> gælder for binear fordi der er 2 børn
        }
        //TC = O(V * log2^V)

        if (vertices.size() > 0) { //O(1) for all if
            int root = 0;

            vertices.get(root).setDistance(0); //if not empty distance to the roo is 0 - meaning we are starting at 0. //O(1)
            int position = Q.getPosition(vertices.get(root)); //getting the position of the starting vertex //O(1) fordi jeg bruger Hashmap

            Q.decreaseKey(position); //Fixing minheap when root value changes  //O(log 2^V) = O(log V) (same TC as extractMin)
            int MST = 0;

            //O(V^2 log V) if you multiply the time from whole and for (V*V = V^2) this would be if I had a dense graph
            //(It would be dense if 10 cites = 100 edges, because the height of a tree is log V therefore 10^2)
            //Our graph is sparse, because our V isn't connected to all possible E
            while (!Q.isEmpty()) { //O(V) -- skal ganges på de andre tider
                //U contains the list of vertices that have been visited
                Vertex u = Q.extractMin(); //Edge consists of (u,v). Extract minimum (the smallest u vertex). //O(V * log V)
                //O(2E), because while a V to a V is one path. An E goes to the 2 V's because it connects both
                //Therefore instead of making the Time Complexity for the loop a V, we write it as 2E
                for (int vIndex = 0; vIndex < u.getOutEdges().size(); vIndex++) { //O(2E), because while a V to a V one path
                    //V-U is the list of vertices that haven't been visited
                    Edge v = u.getOutEdges().get(vIndex); //update the distance - done by getting the new outEdge to u

                    if (v.getWeight() < v.getToV().distance) {
                        v.getToV().setDistance(v.getWeight()); //sets the v's weight/dist as the weight of the parent which is v
                        v.getToV().setPrevious(u); //set the u nodes vertex to be v - thereby v is removed from queue
                        int pos = Q.getPosition(v.getToV()); //get position
                        Q.decreaseKey(pos); //Fixing minheap when position changes O(log V)
                    }
                }

                /*So if we add it up :
                TC from the upper for-loop is  = O(V * log2^V)
                TC from the decreaseKey (heap) is = O(log V) //we ignore the 2 constant for the heap
                TC from the While/for is = O(2E log V) (log V comes from the decreaseKey() in the if-statement)
                Ignoring the constants. TC therefore is = O(V log V + log V + E log V)
                However unlike a * notation where you have to add. A + notation in-between means that we can
                just take the worst case time.
                Total TC for my Prims is therefore: O(E log V) or O((V+E) log V) (depends on how you write it

                 */

                MST += u.distance;

                if (u.previous != null) {
                    //Printing out the edges in the MST
                    System.out.println("Distance from " + u.previous.getName() + " to " + u.getName() + " is " +
                            u.getDistance() + " km.");
                }
            }

            System.out.println("Minimum Spanning Tree Total:");
            //Printing the total distance of MST
            System.out.println("MST distance is: " + MST + " Km");
            //Printing the total price of the spanning tree
            System.out.println("MST price is: " + MST * 100000 + " DKK");

        }else{
            System.out.println("ERROR: no elements in arraylist this.vertices");
        }
    }
}


class Vertex implements Comparable<Vertex>{
    private String name;
    private ArrayList<Edge> outEdges;
    Integer distance = Integer.MAX_VALUE;
    Vertex previous = null;

    public Vertex(String id){
        this.name = id;
        outEdges = new ArrayList<>();
    }

    //function for adding existing edges (the ones made with newEdge()) to graph
    public void addOutEdges(Edge outEdge){
        outEdges.add(outEdge);
    }

    @Override
    public int compareTo(Vertex o) {
        if(this.distance < o.distance){
            return -1;
        }

        if(this.distance > o.distance){
            return 1;
        }

        return 0;

    }

    //Getters and Setters for class, because variables is rivate
    public String getName() {
        return name;
    }


    public ArrayList<Edge> getOutEdges() {
        return outEdges;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

}




class Edge {
    private Vertex fromV;
    private Vertex toV;
    private Integer weight; // change to Integer to Double if too big

    public Edge(Vertex from, Vertex to, Integer cost) { //cost is weight
        fromV = from;
        toV = to;
        weight = cost;
        from.addOutEdges(this); //method to make it easier to add edges (less text)
    }


    //Getter and setter for class
    public Vertex getToV() {
        return toV;
    }

    public Integer getWeight() {
        return weight;
    }

  }


