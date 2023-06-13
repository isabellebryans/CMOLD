package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.IOException;

public class StaticBoard {
    public static void handle_StaticBoard_validation() throws IOException {
        System.out.println("Validating on Time Series Model");
        Graph dataGraph = loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE)
                //.union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .getGraph();

        Graph shapesGraph = loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/oneBoardShapes.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/NumberPiecesShapes.ttl", Lang.TURTLE))
                .getGraph();

        Graph shapesGraph_noBS = loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_noBS.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/NumberPiecesShapes.ttl", Lang.TURTLE))
                .getGraph();

        SHACLValidation.validation(dataGraph, shapesGraph_noBS);
    }
}
