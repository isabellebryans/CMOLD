package org.example.SHACLoptions.StreamLevel;
// Implement shacl at stream level

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.Shapes;
import org.apache.jena.shacl.ValidationReport;
import org.apache.jena.shacl.lib.ShLib;
import org.example.SHACLoptions.loadData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamLevel {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Hello world!");
        Graph dataGraph = loadData.initAndLoadModelFromFolder("src/main/java/org/example/SHACLoptions/StreamLevel/data_item.ttl", Lang.TURTLE).getGraph();
        Graph shapesGraph = loadData.initAndLoadModelFromFolder("src/main/java/org/example/SHACLoptions/StreamLevel/StreamLevelShape.ttl", Lang.TURTLE).getGraph();
        Shapes shapes = Shapes.parse(shapesGraph);

        ValidationReport report = ShaclValidator.get().validate(shapes, dataGraph);
        ShLib.printReport(report);
        System.out.println();
        RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);
        RDFDataMgr.write(new FileOutputStream("report.ttl"), report.getModel(), Lang.TURTLE);
        //RDFDataMgr.write(System.out, report.getModel(), Lang.TTL);

    }
}