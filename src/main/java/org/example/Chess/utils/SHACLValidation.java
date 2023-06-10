package org.example.Chess.utils;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.Shapes;
import org.apache.jena.shacl.ValidationReport;
import org.apache.jena.shacl.lib.ShLib;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SHACLValidation {
    public static void validation(Graph dataGraph, Graph shapesGraph) throws FileNotFoundException, IOException {
        System.out.println("Validating SHACL...");

       // Graph dataGraph = loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE).union(loadData.initAndLoadModelFromResource("fullInitialPositions.ttl", Lang.TURTLE)).getGraph();
        // Graph shapesGraph = loadData.initAndLoadModelFromResource("tempExShape.ttl", Lang.TURTLE).getGraph();
        // Graph shapesGraph = loadData.initAndLoadModelFromFolder("src/main/java/org/example/Chess/type1/oneBoardShapesOld.ttl", Lang.TURTLE).getGraph();
        Shapes shapes = Shapes.parse(shapesGraph);

        ValidationReport report = ShaclValidator.get().validate(shapes, dataGraph);
        ShLib.printReport(report);
        System.out.println();
        RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);
        RDFDataMgr.write(new FileOutputStream("report.ttl"), report.getModel(), Lang.TURTLE);
        //RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);

    }
}
