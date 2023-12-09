package puissance4.model;

public class DemoSerializedData {
    public static void main(String[] args) {
        Game game= new Game("112031 5 4 2");
        game.getCurrentState().printStateFull();
    }
}
