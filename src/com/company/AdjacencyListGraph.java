package com.company;
import java.util.ArrayList;


//Use an adjacency vertex graph to make a graph with the data provided on the next pages.
// (Hint have a Vertex prev=null and Integer dist= Integer.MAX_VALUE on the vertex)
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

        Edge newE = new Edge(from, to, dist);

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


    //method for inserting vertices into a heap
    public MinHeap<Vertex> addAdjGraphToHeap(){
        MinHeap<Vertex> newHeap = new MinHeap<>();

        for(int i = 0; i < vertices.size(); i++){
            newHeap.insert(vertices.get(i));
        }

        return newHeap;
    }


    /*
    public void reverseAdjGraph(){
        Vertex currentFrom;
        Vertex newFrom;
        AdjacencyListGraph reverseGraph = new AdjacencyListGraph();
        ArrayList<Edge> currentFromOutEdgesList;
        ArrayList<Vertex> adjList = vertices;


        for(int i = 0; i < adjList.size(); i++){
            currentFrom = adjList.get(i);
            currentFromOutEdgesList = currentFrom.getOutEdges();

            for(int j = 0; j < currentFromOutEdgesList.size(); j++){
                newFrom = adjList.get(j);


                //add reverse vertex
                reverseGraph.newEdge(newFrom, currentFrom, 1);

            }
        }
    }

     */




}


class Vertex implements Comparable<Vertex>{
    private String name;
    private ArrayList<Edge> outEdges;
    Integer distance = Integer.MAX_VALUE;

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

    //All getters and setters for class (generated) --> right-click, generate, all options clicked.
    //(this is needed because variable this.name is private
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Edge> getOutEdges() {
        return outEdges;
    }

    public void setOutEdges(ArrayList<Edge> outEdges) {
        this.outEdges = outEdges;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}




class Edge{
    private Vertex fromV;
    private Vertex toV;
    private Integer weight; // change to Integer to Double if too big

    public Edge(Vertex from, Vertex to, Integer cost){ //cost is weight
        fromV = from;
        toV = to;
        weight = cost;
        from.addOutEdges(this); //method to make it easier to add edges (less text)
    }


    //Getter and setter methods (generated)
    public Vertex getFromV() {
        return fromV;
    }

    public void setFromV(Vertex fromV) {
        this.fromV = fromV;
    }

    public Vertex getToV() {
        return toV;
    }

    public void setToV(Vertex toV) {
        this.toV = toV;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}