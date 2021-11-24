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
        //makes the graph undirected
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

    //implementation of prims algorithm
    public void MSTPrims() {
        MinHeap<Vertex> Q = new MinHeap<>();

        for (int vertex = 0; vertex < vertices.size(); vertex++) {
            //all vertex dist are set to Max_value (infinity)
            vertices.get(vertex).setDistance(Integer.MAX_VALUE);
            //since we don't start out with a predecessor before we iterate we set them all to null
            vertices.get(vertex).setPrevious(null);
            Q.insert(vertices.get(vertex)); //creates a queue for all vertices in the graph

        }

        if (vertices.size() > 0) {
            int root = 0;

            vertices.get(root).setDistance(0);
            int position = Q.getPosition(vertices.get(root));
            Q.decreaseKey(position);
            int MST = 0;


            while (!Q.isEmpty()) {
                //U contains the list of vertices that have been visited
                Vertex u = Q.extractMin();

                for (int vIndex = 0; vIndex < u.getOutEdges().size(); vIndex++) { //O(2E), because while a V to a V one path
                    //V-U is the list of vertices that haven't been visited
                    Edge v = u.getOutEdges().get(vIndex);

                    if (v.getWeight() < v.getToV().distance) {
                        v.getToV().setDistance(v.getWeight()); //sets the v's weight/dist as the weight of the parent which is v
                        v.getToV().setPrevious(u); //set the u nodes vertex to be v - thereby v is removed from queue
                        int pos = Q.getPosition(v.getToV());
                        Q.decreaseKey(pos);
                    }
                }

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

    //function for adding existing edges to graph
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

    //Getters and Setters for class, because variables is private
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
    private Integer weight;

    public Edge(Vertex from, Vertex to, Integer cost) {
        fromV = from;
        toV = to;
        weight = cost;
        from.addOutEdges(this);
    }


    //Getter and setter for class
    public Vertex getToV() {
        return toV;
    }

    public Integer getWeight() {
        return weight;
    }

  }


