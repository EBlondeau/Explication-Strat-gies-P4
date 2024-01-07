package puissance4.model.strategy;

import java.util.Scanner;

import puissance4.model.State;

public class UserInputStrategy implements IStrategy {

    @Override
    public int playStrategy(State state) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Type move: ");
        int move= sc.nextInt();
        return move;

    }
    
}
