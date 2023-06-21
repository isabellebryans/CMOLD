package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.IOException;

public class StaticBoard {
    public static void handle_StaticBoard_validation() throws IOException {
        System.out.println("Validating on Time Series Model");

        Graph w3_dataGraph = //loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE)
                //loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE)
                loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("w2_boardStatus.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("w3_boardStatus.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("w2_piecesInfo.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("w3_piecesInfo.ttl", Lang.TURTLE))
                .getGraph();

        Graph dataGraph = loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE)
                //.union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .getGraph();

        Graph shapesGraph = loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/StaticBoard_BS_PI_status.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/NumberPiecesShapes.ttl", Lang.TURTLE))
                .getGraph();

        Graph shapesGraph_noBS = loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/StaticBoard_status_PI.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/NumberPiecesShapes.ttl", Lang.TURTLE))
                .getGraph();

        Graph shapesGraph_PI = loadData.initAndLoadModelFromResource("1W_shapes/StaticBoard_shapes/StaticBoard_PI.ttl", Lang.TURTLE).getGraph();

        Graph w3_shapesGraph_BS_PI_status = loadData.initAndLoadModelFromResource("3W_shapes/StaticBoard_shapes/StaticBoard_BS_PI_status.ttl", Lang.TURTLE).getGraph();
        Graph w3_shapesGraph_BS_PI = loadData.initAndLoadModelFromResource("3W_shapes/StaticBoard_shapes/StaticBoard_BS_PI.ttl", Lang.TURTLE).getGraph();
        Graph w3_shapesGraph_BS_status = loadData.initAndLoadModelFromResource("3W_shapes/StaticBoard_shapes/StaticBoard_BS_status.ttl", Lang.TURTLE).getGraph();
        Graph w3_shapesGraph_status = loadData.initAndLoadModelFromResource("3W_shapes/StaticBoard_shapes/StaticBoard_status.ttl", Lang.TURTLE).getGraph();

        SHACLValidation.validation(w3_dataGraph, w3_shapesGraph_status);
    }
}
