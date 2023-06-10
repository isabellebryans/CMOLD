package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TS {
    private static Graph setDataGraph() throws IOException {

        return loadData.initAndLoadModelFromResource("TS/boardStatusT1.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE))
                .getGraph();
    }
    private static Graph setShapesGraph() throws IOException {

        return loadData.initAndLoadModelFromResource("shapes/TS_shapes/TS_shapes_everything.ttl", Lang.TURTLE).getGraph();
    }
    public static void handle_TS_validation() throws IOException {
        System.out.println("Validating on Time Series Model");
        Graph dataGraph = setDataGraph();
        Graph shapesGraph = setShapesGraph();

        SHACLValidation.validation(dataGraph, shapesGraph);
    }
}
