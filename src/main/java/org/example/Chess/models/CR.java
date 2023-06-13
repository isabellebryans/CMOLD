package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.IOException;

public class CR {
    private static Graph setDataGraph() throws IOException {

        Graph dataGraph = loadData.initAndLoadModelFromResource("CR/t1.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE))
                .getGraph();

        Graph W_dataGraph = loadData.initAndLoadModelFromResource("CR/3w_moves.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE))
                .getGraph();

        return W_dataGraph;
    }
    private static Graph setShapesGraph() throws IOException {
        // one data item at a time
        Graph shapesGraph_everything = loadData.initAndLoadModelFromResource("shapes/CR_shapes/CR_BS_PI_status.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_BS_PI = loadData.initAndLoadModelFromResource("shapes/CR_shapes/CR_BS_PI.ttl", Lang.TURTLE).getGraph();;
        Graph shapesGraph_BS_status = loadData.initAndLoadModelFromResource("shapes/CR_shapes/CR_BS_status.ttl", Lang.TURTLE).getGraph();;
        Graph shapesGraph_status_PI = loadData.initAndLoadModelFromResource("shapes/CR_shapes/CR_status_PI.ttl", Lang.TURTLE).getGraph();;
        Graph shapesGraph_PI = loadData.initAndLoadModelFromResource("shapes/CR_shapes/CR_PI.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_PI.ttl", Lang.TURTLE)).getGraph();;
        Graph shapesGraph_BS = loadData.initAndLoadModelFromResource("shapes/CR_shapes/CR_BS.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_BS.ttl", Lang.TURTLE)).getGraph();;
        Graph shapesGraph_status = loadData.initAndLoadModelFromResource("shapes/CR_shapes/CR_status.ttl", Lang.TURTLE).getGraph();

        // Window = 3 data items
        Graph W_shapesGraph_everything = loadData.initAndLoadModelFromResource("W_shapes/CashReg_shapes/CashReg_BS_PI_status.ttl", Lang.TURTLE).getGraph();


        return W_shapesGraph_everything;
    }
    public static void handle_CR_validation() throws IOException {
        System.out.println("Validating on Cash Registry Model");
        Graph dataGraph = setDataGraph();
        Graph shapesGraph = setShapesGraph();

        SHACLValidation.validation(dataGraph, shapesGraph);
    }
}
