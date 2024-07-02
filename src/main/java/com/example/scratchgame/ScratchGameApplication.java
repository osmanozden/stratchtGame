package com.example.scratchgame;

import com.example.scratchgame.model.Config;
import com.example.scratchgame.model.Symbol;
import com.example.scratchgame.service.GameService;
import com.example.scratchgame.service.MatrixGenerator;
import com.example.scratchgame.util.JsonUtil;

import java.io.IOException;
import java.util.Arrays;

public class ScratchGameApplication {
    private static final String CONFIG_PATH = "src/main/resources/config.json";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java -jar <your-jar-file> --betting-amount <amount>");
            System.exit(1);
        }

        double betAmount = 0;
        try {
            betAmount = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid betting amount. It must be a number.");
            System.exit(1);
        }

        if (betAmount <= 0) {
            System.err.println("Invalid arguments. Usage: java -jar <your-jar-file> --betting-amount <amount>");
            System.exit(1);
        }

        try {
            Config config = JsonUtil.fromJsonFile(CONFIG_PATH, Config.class);
            System.out.println("Loaded Config: " + config);

            MatrixGenerator matrixGenerator = new MatrixGenerator(config);
            Symbol[][] matrix = matrixGenerator.generateMatrix(config.getRows(), config.getColumns());

            GameService gameService = new GameService(config);
            double reward = gameService.calculateReward(matrix, betAmount);

            System.out.println("Matrix: " + Arrays.deepToString(matrix));
            System.out.println("Reward: " + reward);
        } catch (IOException e) {
            System.err.println("Error loading config file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
