package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.IOException;

public class Tstile {
    private static Graph setDataGraph() throws IOException {

        return loadData.initAndLoadModelFromResource("Tstile/move_t1.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE))
                .getGraph();
    }
    private static Graph setShapesGraph() throws IOException {
        Graph shapesGraph_everything = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_shapes_everything.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_noStatus = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_shapes_noStatus.ttl", Lang.TURTLE).getGraph();
        return shapesGraph_noStatus;
    }
    public static void handle_TT_validation() throws IOException {
        System.out.println("Validating on Turn Table Model");
        Graph dataGraph = setDataGraph();
        Graph shapesGraph = setShapesGraph();

        SHACLValidation.validation(dataGraph, shapesGraph);
    }
}
