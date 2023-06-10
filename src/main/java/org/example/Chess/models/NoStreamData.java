package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NoStreamData {
    public static void handle_NoStreamData_validation() throws IOException {
        System.out.println("Validating on Time Series Model");
        Graph dataGraph = loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .getGraph();

        Graph shapesGraph = loadData.initAndLoadModelFromResource("shapes/NoStreamData_shapes/oneBoardShapes.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("shapes/NoStreamData_shapes/NumberPiecesShapes.ttl", Lang.TURTLE))
                .getGraph();

        SHACLValidation.validation(dataGraph, shapesGraph);
    }
}
