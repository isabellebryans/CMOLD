package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.IOException;

public class TSeries {
    private static Graph setDataGraph() throws IOException {

        // 1st window
        Model w1_dataGraph = loadData.initAndLoadModelFromResource("TSeries/boardStatusT1.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE));
                //.union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE));
                //.union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE));

        // 2nd window
        Model w2_dataGraph = loadData.initAndLoadModelFromResource("TSeries/boardStatusT2.ttl", Lang.TURTLE);
                //.union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("w2_boardStatus.ttl", Lang.TURTLE));
                //.union(loadData.initAndLoadModelFromResource("2ndWindow/w2_piecesInfo.ttl", Lang.TURTLE));

        // 3rd window
        Model w3_dataGraph = loadData.initAndLoadModelFromResource("TSeries/boardStatusT3.ttl", Lang.TURTLE);
                //.union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("w3_boardStatus.ttl", Lang.TURTLE));
                //.union(loadData.initAndLoadModelFromResource("3rdWindow/w3_piecesInfo.ttl", Lang.TURTLE));

        Graph oneWindow_dataGraph = w1_dataGraph.getGraph();

        Graph threeWindows_dataGraph = w1_dataGraph.union(w2_dataGraph).union(w3_dataGraph).getGraph();

        return threeWindows_dataGraph;
    }
    private static Graph setShapesGraph() throws IOException {
        // Shapes for single data item input
        Graph shapesGraph_everything = loadData.initAndLoadModelFromResource("1W_shapes/TSeries_shapes/TSeries_BS_PI_status.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_BS_PI = loadData.initAndLoadModelFromResource("1W_shapes/TSeries_shapes/TSeries_BS_PI.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_BS_status = loadData.initAndLoadModelFromResource("1W_shapes/TSeries_shapes/TSeries_BS_status.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_status_PI = loadData.initAndLoadModelFromResource("1W_shapes/TSeries_shapes/TSeries_status_PI.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_PI = loadData.initAndLoadModelFromResource("1W_shapes/TSeries_shapes/TSeries_BS_status.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/StaticBoard_PI.ttl", Lang.TURTLE)).getGraph();
        Graph shapesGraph_BS = loadData.initAndLoadModelFromResource("1W_shapes/TSeries_shapes/TSeries_BS.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/StaticBoard_BS.ttl", Lang.TURTLE)).getGraph();
        Graph shapesGraph_status = loadData.initAndLoadModelFromResource("1W_shapes/TSeries_shapes/TSeries_status.ttl", Lang.TURTLE).getGraph();

        // Shapes for window
        Graph W_shapesGraph_BS_PI_status = loadData.initAndLoadModelFromResource("3W_shapes/TSeries_shapes/TSeries_BS_PI_status.ttl", Lang.TURTLE).getGraph();


        Graph W_shapesGraph_status = loadData.initAndLoadModelFromResource("3W_shapes/TSeries_shapes/TSeries_status.ttl", Lang.TURTLE).getGraph();
        Graph W_shapesGraph_BS = loadData.initAndLoadModelFromResource("3W_shapes/TSeries_shapes/TSeries_BS.ttl", Lang.TURTLE).getGraph();

        return W_shapesGraph_BS;
    }
    public static void handle_TS_validation() throws IOException {
        System.out.println("Validating on Time Series Model");
        Graph dataGraph = setDataGraph();
        Graph shapesGraph = setShapesGraph();

        SHACLValidation.validation(dataGraph, shapesGraph);
    }
}
