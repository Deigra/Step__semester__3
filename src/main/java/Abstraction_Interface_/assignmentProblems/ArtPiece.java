package Abstraction_Interface_.assignmentProblems;

public abstract class ArtPiece {
    private static int counter = 0;
    private final int pieceId;

    public ArtPiece() {
        counter++;
        this.pieceId = counter;
    }

    public int getPieceId() {
        return pieceId;
    }

    public abstract String describe();
}