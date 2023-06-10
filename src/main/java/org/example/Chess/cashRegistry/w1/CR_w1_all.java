package org.example.Chess.cashRegistry.w1;
import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.Shapes;
import org.apache.jena.shacl.ValidationReport;
import org.apache.jena.shacl.lib.ShLib;
import org.example.Chess.utils.loadData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class CR_w1_all {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Hello world!");

        Graph dataGraph = loadData.initAndLoadModelFromResource("chessBoardStructure.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromResource("ontology.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("unused/t1PositionsFull.ttl", Lang.TURTLE))
                .union(loadData.initAndLoadModelFromResource("CR/t1.ttl", Lang.TURTLE)).getGraph();
        //Graph shapesGraph = loadData.initAndLoadModelFromResource("tempExShape.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph = loadData.initAndLoadModelFromFolder("src/main/java/org/example/Chess/cashRegistry/w1/CR_w1_allShapes.ttl", Lang.TURTLE)
                .union(loadData.initAndLoadModelFromFolder("src/main/java/org/example/Chess/timeSeries/oneBoardShapesOld.ttl", Lang.TURTLE)).getGraph();
        Shapes shapes = Shapes.parse(shapesGraph);

        ValidationReport report = ShaclValidator.get().validate(shapes, dataGraph);
        ShLib.printReport(report);
        System.out.println();
        RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);
        RDFDataMgr.write(new FileOutputStream("report.ttl"), report.getModel(), Lang.TURTLE);
        //RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);

    }
}
