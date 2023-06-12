package org.example.Chess;

import org.example.Chess.models.CR;
import org.example.Chess.models.StaticBoard;
import org.example.Chess.models.TS;
import org.example.Chess.models.TT;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Run validation on only static data, no stream data
        // BS + PI + status
        //StaticBoard.handle_StaticBoard_validation();

        // Run validation on Time Series model
        TS.handle_TS_validation();

        // Run validation on Cash Registry model
        //CR.handle_CR_validation();

        // Run validation on Turn Table model
        //TT.handle_TT_validation();


    }
}
