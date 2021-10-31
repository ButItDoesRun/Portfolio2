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
        //To make a spannning tree you need to know the distances between to vertices, and where a node came from
        //Because of the adj-datastructure my elements need to be accessed through a iteration of the list
        for (int vertex = 0; vertex < vertices.size(); vertex++) {
            //all vertex dist are set to Max_value (infinity) - basically says its infinitely far from the other nodes
            vertices.get(vertex).setDistance(Integer.MAX_VALUE); //the heap key
            //since we don't start out with a predecessor before we iterate we set them all to null
            vertices.get(vertex).setPrevious(null); //can put -1 instead //the heap parent
            Q.insert(vertices.get(vertex)); //creates a queue for all vertices in the graph
        }

        //Only if the root isn't empty can we set a distance
        if (vertices.size() > 0) {
            int root = 0;

            //the key of a root is zero - here we set a/any note as root
            vertices.get(root).setDistance(0); //if not empty distance to the roo is 0 - meaning we are starting at 0.
            int position = Q.getPosition(vertices.get(root)); //getting the position of the starting vertex

            Q.decreaseKey(position); //Fixing minheap when root value changes
            int MST = 0;

            while (!Q.isEmpty()) {
                //U contains the list of vertices that have been visited
                Vertex u = Q.extractMin(); //Edge consists of (u,v). Extract minimum (the smallest u vertex).
                for (int vIndex = 0; vIndex < u.getOutEdges().size(); vIndex++) {
                    //V-U is the list of vertices that haven't been visited
                    Edge v = u.getOutEdges().get(vIndex); //update the distance - done by getting the new outEdge to u

                    //if the weight (key/dist) of edge v is < than the weight of u (the edges new pair/vertex)
                    //getToV is the opponent of the v which in this case is "from"
                    //it is of course smaller as long as it hasn't been visited, because then its set as null
                    if (v.getWeight() < v.getToV().distance) {
                        v.getToV().setDistance(v.getWeight()); //sets the opponents weight/dist as the weight of parent which is v
                        v.getToV().setPrevious(u); //set the u nodes vertex to be v - thereby v is removed from queue
                        int pos = Q.getPosition(v.getToV()); //get position
                        Q.decreaseKey(pos); //Fixing minheap when position changes

                    }

                }

                MST += u.distance;

                //print only if parent != null (det er den kun ved root)
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


