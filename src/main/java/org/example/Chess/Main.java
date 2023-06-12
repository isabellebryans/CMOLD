package org.example.Chess;

import org.example.Chess.models.Tstile;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Run validation on only static data, no stream data
        // BS + PI + status
        //StaticBoard.handle_StaticBoard_validation();

        // Run validation on Time Series model
        //TS.handle_TS_validation();

        // Run validation on Cash Registry model
        //CR.handle_CR_validation();

        // Run validation on Turn Table model
        Tstile.handle_TT_validation();


    }
}
