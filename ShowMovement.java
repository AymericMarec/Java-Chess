import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMovement implements ActionListener {
    private int x;
    private int y;
    private Grid Grid;

    public ShowMovement(int x, int y, Grid BackgroundGrid) {
        this.x = x;
        this.y = y;
        this.Grid = BackgroundGrid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.Grid.PiecesGrid[x][y].GetColor() + " " + this.Grid.PiecesGrid[x][y].GetPiece());
        switch (this.Grid.PiecesGrid[x][y].GetPiece()) {
            case "Rook":
                ShowRook(x, y);
                break;
            case "Knight":
                ShowKnight(x, y);
                break;
            case "Bishop":
                ShowBishop(x, y);
                break;
            case "Queen":
                ShowQueen(x, y);
                break;
            case "King":
                ShowKing(x, y);
                break;
            case "Pawn":
                ShowPawn(x, y);
                break;
            default:
                System.out.println("empty case selected");
                break;
        }
    }

    private void FillCancelButton() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (ActionListener al : this.Grid.PiecesGrid[i][j].Panel.getActionListeners()) {
                    this.Grid.PiecesGrid[i][j].Panel.removeActionListener(al);
                }
                this.Grid.PiecesGrid[i][j].Panel.addActionListener(new CancelMovement(i, j, Grid));
            }
        }
    }

    private void addMovementListener(int x, int y, int newX, int newY) {
        if (this.Grid.PiecesGrid[newX][newY].GetPiece() =="" ){
            this.Grid.BackgroundGrid[newX][newY].setBackground(new Color(255, 100, 100));

            for (ActionListener al : this.Grid.PiecesGrid[newX][newY].Panel.getActionListeners()) {
                this.Grid.PiecesGrid[newX][newY].Panel.removeActionListener(al);
            }
            this.Grid.PiecesGrid[newX][newY].Panel.addActionListener(new DoMovement(x, y, newX, newY, Grid));
        }
    }

    private void ShowPawn(int x, int y) {
        System.out.println("move Pawn");
        FillCancelButton();
        if (this.Grid.PiecesGrid[x][y].GetColor() == "Black") {
            if (!this.Grid.PiecesGrid[x][y].GetMoved()) {
                int newx = x + 2;
                int newy = y;
                addMovementListener(x, y, newx, newy);
            }
            if (x + 1 < 8) {
                int newx = x + 1;
                int newy = y;
                addMovementListener(x, y, newx, newy);
            }
        } else {
            if (!this.Grid.PiecesGrid[x][y].GetMoved()) {
                int newx = x - 2;
                int newy = y;
                addMovementListener(x, y, newx, newy);
            }
            if (x - 1 >= 0) {
                int newx = x - 1;
                int newy = y;
                addMovementListener(x, y, newx, newy);
            }
        }
    }

    private void ShowKing(int x, int y) {
        FillCancelButton();
        int[][] moves = {
                {x + 1, y},
                {x, y + 1},
                {x - 1, y},
                {x, y - 1},
                {x + 1, y + 1},
                {x - 1, y + 1},
                {x + 1, y - 1},
                {x - 1, y - 1}
        };

        for (int[] move : moves) {
            int newX = move[0];
            int newY = move[1];

            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                addMovementListener(x, y, newX, newY);
            }
        }
    }

    private void ShowQueen(int x, int y) {
        FillCancelButton();
        for (int i = 0; i < 8; i++) {
            addMovementListener(x, y, x, i);
            addMovementListener(x, y, i, y);
            if (x - i >= 0 && y - i >= 0) {
                addMovementListener(x, y, x - i, y - i);
            }
            if (x + i < 8 && y + i < 8) {
                addMovementListener(x, y, x + i, y + i);
            }
            if (x - i >= 0 && y + i < 8) {
                addMovementListener(x, y, x - i, y + i);
            }
            if (x + i < 8 && y - i >= 0) {
                addMovementListener(x, y, x + i, y - i);
            }
        }
    }
    

    private void ShowBishop(int x, int y) {
        System.out.println("move Bishop");
        FillCancelButton();
        for (int i = 1; i < 8; i++) {
            if (x - i >= 0 && y - i >= 0) {
                addMovementListener(x, y, x - i, y - i);
            }
            if (x + i < 8 && y + i < 8) {
                addMovementListener(x, y, x + i, y + i);
            }
            if (x - i >= 0 && y + i < 8) {
                addMovementListener(x, y, x - i, y + i);
            }
            if (x + i < 8 && y - i >= 0) {
                addMovementListener(x, y, x + i, y - i);
            }
        }
    }
    

    private void ShowRook(int x, int y) {
        System.out.println("move Rook");
        FillCancelButton();
    
        // Vérification des mouvements horizontaux
        for (int i = x + 1; i < 8; i++) {
            if (!this.Grid.PiecesGrid[i][y].GetPiece().equals("")) {
                break;
            }
            addMovementListener(x, y, i, y);
            if (!this.Grid.PiecesGrid[i][y].GetPiece().equals("")) {
                break;
            }
        }
    
        for (int i = x - 1; i >= 0; i--) {
            if (!this.Grid.PiecesGrid[i][y].GetPiece().equals("")) {
                break;
            }
            addMovementListener(x, y, i, y);
            if (!this.Grid.PiecesGrid[i][y].GetPiece().equals("")) {
                break;
            }
        }
    
        // Vérification des mouvements verticaux
        for (int j = y + 1; j < 8; j++) {
            if (!this.Grid.PiecesGrid[x][j].GetPiece().equals("")) {
                break;
            }
            addMovementListener(x, y, x, j);
            if (!this.Grid.PiecesGrid[x][j].GetPiece().equals("")) {
                break;
            }
        }
    
        for (int j = y - 1; j >= 0; j--) {
            if (!this.Grid.PiecesGrid[x][j].GetPiece().equals("")) {
                break;
            }
            addMovementListener(x, y, x, j);
            if (!this.Grid.PiecesGrid[x][j].GetPiece().equals("")) {
                break;
            }
        }
    }
    
    

    private void ShowKnight(int x, int y) {
        System.out.println("move Knight");
        FillCancelButton();

        int[][] moves = {
                {x + 2, y + 1},
                {x + 2, y - 1},
                {x - 2, y + 1},
                {x - 2, y - 1},
                {x + 1, y + 2},
                {x + 1, y - 2},
                {x - 1, y + 2},
                {x - 1, y - 2}
        };

        for (int[] move : moves) {
            int newX = move[0];
            int newY = move[1];

            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                addMovementListener(x, y, newX, newY);
            }
        }
    }
}
