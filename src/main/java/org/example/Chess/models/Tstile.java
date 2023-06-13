package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.IOException;

public class Tstile {
    private static Graph setDataGraph() throws IOException {

        return loadData.initAndLoadModelFromResource("Tstile/move_t1.ttl", Lang.TURTLE)
                //.union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE))
                .getGraph();
    }
    private static Graph setShapesGraph() throws IOException {
        Graph shapesGraph_everything = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_BS_PI_status.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_BS_PI = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_BS_PI.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_BS_status = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_BS_status.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_BS_status.ttl", Lang.TURTLE)).getGraph();
        Graph shapesGraph_status_PI = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_status_PI.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_status_PI.ttl", Lang.TURTLE)).getGraph();
        Graph shapesGraph_PI = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_PI.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_PI.ttl", Lang.TURTLE)).getGraph();
        Graph shapesGraph_BS = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_BS.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_BS.ttl", Lang.TURTLE)).getGraph();
        Graph shapesGraph_status = loadData.initAndLoadModelFromResource("shapes/Tstile_shapes/Tstile_status.ttl", Lang.TURTLE).getGraph();

        return shapesGraph_status;
    }
    public static void handle_TT_validation() throws IOException {
        System.out.println("Validating on Turn Table Model");
        Graph dataGraph = setDataGraph();
        Graph shapesGraph = setShapesGraph();

        SHACLValidation.validation(dataGraph, shapesGraph);
    }
}
