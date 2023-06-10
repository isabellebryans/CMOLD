package org.example.Chess;

import org.example.Chess.models.NoStreamData;
import org.example.Chess.models.TS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.TextStyle;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Run validation on only static data, no stream data
        // BS + PI + status
        //NoStreamData.handle_NoStreamData_validation();

        // Run validation on Time Series model
        TS.handle_TS_validation();

    }
}
