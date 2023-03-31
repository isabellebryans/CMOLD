package org.example.SHACLoptions;
// Implement shacl at window level

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.Shapes;
import org.apache.jena.shacl.ValidationReport;
import org.apache.jena.shacl.lib.ShLib;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


// on the Observation stream
// Issue because person in more than one room at once because after R2S operator, time data is lost

public class WindowLevel {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");
        Graph dataGraph = loadData.initAndLoadModelFromResource("windoGraph.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph = loadData.initAndLoadModelFromResource("WindowShape.ttl", Lang.TURTLE).getGraph();
        Shapes shapes = Shapes.parse(shapesGraph);

        ValidationReport report = ShaclValidator.get().validate(shapes, dataGraph);
        ShLib.printReport(report);
        System.out.println();
        RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);
        RDFDataMgr.write(new FileOutputStream("report.ttl"), report.getModel(), Lang.TURTLE);
        //RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);

    }
}