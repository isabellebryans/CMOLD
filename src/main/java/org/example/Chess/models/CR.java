package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.IOException;

public class CR {
    private static Graph setDataGraph() throws IOException {

         Graph oneWindow_dataGraph = loadData.initAndLoadModelFromResource("CR/t1.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE)).getGraph();

        // 1st window
        Model w1_dataGraph = loadData.initAndLoadModelFromResource("CR/t1.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE));

        // 2nd window
        Model w2_dataGraph = loadData.initAndLoadModelFromResource("CR/t2.ttl", Lang.TURTLE)
        .union(loadData.initAndLoadModelFromResource("w2_boardStatus.ttl", Lang.TURTLE))
        .union(loadData.initAndLoadModelFromResource("w2_piecesInfo.ttl", Lang.TURTLE));

        // 3rd window
        Model w3_dataGraph = loadData.initAndLoadModelFromResource("CR/t3.ttl", Lang.TURTLE)
        .union(loadData.initAndLoadModelFromResource("w3_boardStatus.ttl", Lang.TURTLE))
        .union(loadData.initAndLoadModelFromResource("w3_piecesInfo.ttl", Lang.TURTLE));


        Graph threeWindows_dataGraph = w1_dataGraph.union(w2_dataGraph).union(w3_dataGraph).getGraph();

        return oneWindow_dataGraph;
    }
    private static Graph setShapesGraph() throws IOException {
        // one data item at a time
        Graph shapesGraph_everything = loadData.initAndLoadModelFromResource("1W_shapes/CR_shapes/CR_BS_PI_status.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_BS_PI = loadData.initAndLoadModelFromResource("1W_shapes/CR_shapes/CR_BS_PI.ttl", Lang.TURTLE).getGraph();;
        Graph shapesGraph_BS_status = loadData.initAndLoadModelFromResource("1W_shapes/CR_shapes/CR_BS_status.ttl", Lang.TURTLE).getGraph();;
        Graph shapesGraph_status_PI = loadData.initAndLoadModelFromResource("1W_shapes/CR_shapes/CR_status_PI.ttl", Lang.TURTLE).getGraph();;
        Graph shapesGraph_PI = loadData.initAndLoadModelFromResource("1W_shapes/CR_shapes/CR_PI.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/StaticBoard_PI.ttl", Lang.TURTLE)).getGraph();;
        Graph shapesGraph_BS = loadData.initAndLoadModelFromResource("1W_shapes/CR_shapes/CR_BS.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/StaticBoard_BS.ttl", Lang.TURTLE)).getGraph();;
        Graph shapesGraph_status = loadData.initAndLoadModelFromResource("1W_shapes/CR_shapes/CR_status.ttl", Lang.TURTLE).getGraph();

        // Window = 3 data items
        Graph W_shapesGraph_everything = loadData.initAndLoadModelFromResource("3W_shapes/CashReg_shapes/CashReg_BS_PI_status.ttl", Lang.TURTLE).getGraph();
        Graph W_shapesGraph_BS_PI = loadData.initAndLoadModelFromResource("3W_shapes/CashReg_shapes/CashReg_BS_PI.ttl", Lang.TURTLE).getGraph();
        Graph W_shapesGraph_BS_status = loadData.initAndLoadModelFromResource("3W_shapes/CashReg_shapes/CashReg_BS_status.ttl", Lang.TURTLE).getGraph();
        Graph W_shapesGraph_nothing = loadData.initAndLoadModelFromResource("3W_shapes/CashReg_shapes/CashReg_nothing.ttl", Lang.TURTLE).getGraph();
        Graph W_shapesGraph_PI = loadData.initAndLoadModelFromResource("3W_shapes/CashReg_shapes/CashReg_PI.ttl", Lang.TURTLE).getGraph();
        Graph W_shapesGraph_status = loadData.initAndLoadModelFromResource("3W_shapes/CashReg_shapes/CashReg_status.ttl", Lang.TURTLE).getGraph();


        return shapesGraph_everything;
    }
    public static void handle_CR_validation() throws IOException {
        System.out.println("Validating on Cash Registry Model");
        Graph dataGraph = setDataGraph();
        Graph shapesGraph = setShapesGraph();

        SHACLValidation.validation(dataGraph, shapesGraph);
    }
}
