package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        AdjacencyListGraph adjGraph = insertGraphData();
        //AdjacencyListGraph adjGraph2 = insertGraphData();

        //adjGraph.printGraph();

        //System.out.println("Dividing line--------------------------------------------------------");

        //adjGraph2.reverseAdjGraph();

        //adjGraph2.printGraph();


        //MinHeap<Vertex> heap = adjGraph.addAdjGraphToHeap();

        //Iterator<Vertex> itrHeap = heap.minheap.iterator();


        /*
        while(itrHeap.hasNext()){
            Vertex itH = itrHeap.next();
            System.out.println(itH.getName() + " , " + itH.getOutEdges());
        }

         */

        //test printing
       // while(!heap.isEmpty()){
            //System.out.println(heap.extractMin().getName());


        //}


        adjGraph.printGraph();
        adjGraph.MSTPrims();






        ///System.out.println(heap.printHeap());

        //System.out.println(heap.printHeap());







    }



    //Creating the Adjacency vertex graph with data from the assigment datatable
    public static AdjacencyListGraph insertGraphData(){
        //Making an arraylist of vertices
        AdjacencyListGraph adjG = new AdjacencyListGraph();

        //adding all vertices
        //The vertex is added outside to it can be accessed when using edges
        Vertex Esk = new Vertex("Eskildstrup");
        adjG.addVertex(Esk);

        Vertex Has = new Vertex("Haslev");
        adjG.addVertex(Has);

        Vertex Hol = new Vertex("Holbæk");
        adjG.addVertex(Hol);

        Vertex Jaer = new Vertex("Jærgerspris");
        adjG.addVertex(Jaer);

        Vertex Kal = new Vertex("Kalundborg");
        adjG.addVertex(Kal);

        Vertex Kor = new Vertex("Korsør");
        adjG.addVertex(Kor);

        Vertex Kge = new Vertex("Køge");
        adjG.addVertex(Kge);

        Vertex Mar = new Vertex("Maribo");
        adjG.addVertex(Mar);

        Vertex Naes = new Vertex("Næstved");
        adjG.addVertex(Naes);

        Vertex Rin = new Vertex("Ringsted");
        adjG.addVertex(Rin);

        Vertex Sla = new Vertex("Slagelse");
        adjG.addVertex(Sla);

        Vertex Nyk = new Vertex("Nykøbing F");
        adjG.addVertex(Nyk);

        Vertex Vor = new Vertex("Vordingborg");
        adjG.addVertex(Vor);

        Vertex Ros = new Vertex("Roskilde");
        adjG.addVertex(Ros);

        Vertex Sor = new Vertex("Sorø");
        adjG.addVertex(Sor);

        Vertex Nak = new Vertex("Nakskov");
        adjG.addVertex(Nak);


        //Adding the Edges
        //A.addOutEdges(new Edge(A, B, 1));
        adjG.newEdge(Esk, Mar, 28);
        adjG.newEdge(Esk, Nyk, 13);
        adjG.newEdge(Esk, Vor, 25);
        adjG.newEdge(Has, Kor, 60);
        adjG.newEdge(Has, Kge, 24);
        adjG.newEdge(Has, Naes, 25);
        adjG.newEdge(Has, Rin, 19);
        adjG.newEdge(Has, Ros, 47);
        adjG.newEdge(Has, Sla, 48);
        adjG.newEdge(Has, Sor, 34);
        adjG.newEdge(Has, Vor, 40);
        adjG.newEdge(Hol, Jaer, 34);
        adjG.newEdge(Hol, Kal, 44);
        adjG.newEdge(Hol, Kor, 66);
        adjG.newEdge(Hol, Rin, 36);
        adjG.newEdge(Hol, Ros, 32);
        adjG.newEdge(Hol, Sla, 46);
        adjG.newEdge(Hol, Sor, 34);
        adjG.newEdge(Jaer, Kor, 94);
        adjG.newEdge(Jaer, Kge, 58);
        adjG.newEdge(Jaer, Rin, 56);
        adjG.newEdge(Jaer, Ros, 33);
        adjG.newEdge(Jaer, Sla, 74);
        adjG.newEdge(Jaer, Sor, 63);
        adjG.newEdge(Kal, Rin, 62);
        adjG.newEdge(Kal, Ros, 70);
        adjG.newEdge(Kal, Sla, 39);
        adjG.newEdge(Kal, Sor, 51);
        adjG.newEdge(Kor, Naes, 45);
        adjG.newEdge(Kor, Sla, 20);
        adjG.newEdge(Kge, Naes, 45);
        adjG.newEdge(Kge, Rin, 28);
        adjG.newEdge(Kge, Ros, 25);
        adjG.newEdge(Kge, Vor, 60);
        adjG.newEdge(Mar, Nak, 27);
        adjG.newEdge(Mar, Nyk, 26);
        adjG.newEdge(Naes, Ros, 57);
        adjG.newEdge(Naes, Rin, 26);
        adjG.newEdge(Naes, Sla, 37);
        adjG.newEdge(Naes, Sor, 32);
        adjG.newEdge(Naes, Vor, 28);
        adjG.newEdge(Rin, Ros, 31);
        adjG.newEdge(Rin, Sor, 15);
        adjG.newEdge(Rin, Vor, 58);
        adjG.newEdge(Sla, Sor, 14);


        return adjG;

    }
}
