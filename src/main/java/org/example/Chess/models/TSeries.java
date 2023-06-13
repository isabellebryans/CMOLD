package org.example.Chess.models;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.example.Chess.utils.SHACLValidation;
import org.example.Chess.utils.loadData;

import java.io.IOException;

public class TSeries {
    private static Graph setDataGraph() throws IOException {

        return loadData.initAndLoadModelFromResource("TSeries/boardStatusT1.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("boardStatus.ttl", Lang.TURTLE))
                //.union(loadData.initAndLoadModelFromResource("piecesInfo.ttl", Lang.TURTLE))
                .getGraph();
    }
    private static Graph setShapesGraph() throws IOException {
        Graph shapesGraph_everything = loadData.initAndLoadModelFromResource("shapes/TSeries_shapes/TSeries_shapes_everything.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_noStatus = loadData.initAndLoadModelFromResource("shapes/TSeries_shapes/TSeries_shapes_noStatus.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_noPI = loadData.initAndLoadModelFromResource("shapes/TSeries_shapes/TSeries_shapes_PI.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_noBS = loadData.initAndLoadModelFromResource("shapes/TSeries_shapes/TSeries_shapes_noBS.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph_PI = loadData.initAndLoadModelFromResource("shapes/TSeries_shapes/TSeries_shapes_PI.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_PI.ttl", Lang.TURTLE)).getGraph();
        Graph shapesGraph_BS = loadData.initAndLoadModelFromResource("shapes/TSeries_shapes/TSeries_shapes_BS.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("shapes/StaticBoard_shapes/StaticBoard_BS.ttl", Lang.TURTLE)).getGraph();


        return shapesGraph_BS;
    }
    public static void handle_TS_validation() throws IOException {
        System.out.println("Validating on Time Series Model");
        Graph dataGraph = setDataGraph();
        Graph shapesGraph = setShapesGraph();

        SHACLValidation.validation(dataGraph, shapesGraph);
    }
}
