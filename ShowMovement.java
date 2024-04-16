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
        if(Grid.Turn && this.Grid.PiecesGrid[x][y].GetColor() == "Black"){
            return;
        }else if (!Grid.Turn && this.Grid.PiecesGrid[x][y].GetColor() == "White"){
            return;
        }
        switch (this.Grid.PiecesGrid[x][y].GetPiece()) {
            case "Rook":
                ShowRook(x, y);
                this.Grid.Turn = !this.Grid.Turn;
                break;
            case "Knight":
                ShowKnight(x, y);
                this.Grid.Turn = !this.Grid.Turn;
                break;
            case "Bishop":
                ShowBishop(x, y);
                this.Grid.Turn = !this.Grid.Turn;
                break;
            case "Queen":
                ShowQueen(x, y);
                this.Grid.Turn = !this.Grid.Turn;
                break;
            case "King":
                ShowKing(x, y);
                this.Grid.Turn = !this.Grid.Turn;
                break;
            case "Pawn":
                ShowPawn(x, y);
                this.Grid.Turn = !this.Grid.Turn;
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
        // if (this.Grid.PiecesGrid[newX][newY].GetPiece() =="" ){
            this.Grid.BackgroundGrid[newX][newY].setBackground(new Color(255, 100, 100));

            for (ActionListener al : this.Grid.PiecesGrid[newX][newY].Panel.getActionListeners()) {
                this.Grid.PiecesGrid[newX][newY].Panel.removeActionListener(al);
            }
            this.Grid.PiecesGrid[newX][newY].Panel.addActionListener(new DoMovement(x, y, newX, newY, Grid));
        // }
    }
    private void ShowPawn(int x, int y) {
        System.out.println("move Pawn");
        FillCancelButton();
    
        int direction = (this.Grid.PiecesGrid[x][y].GetColor() == "Black") ? 1 : -1;
    
        int newx = x + direction;
        int newy = y;
        if (newx >= 0 && newx < 8 && newy >= 0 && newy < 8 && this.Grid.PiecesGrid[newx][newy].GetPiece().equals("")) {
            addMovementListener(x, y, newx, newy);
        }
    
        newx = x + direction;
        newy = y + 1;
        if (newx >= 0 && newx < 8 && newy >= 0 && newy < 8 && !this.Grid.PiecesGrid[newx][newy].GetPiece().equals("") && this.Grid.PiecesGrid[newx][newy].GetColor() != this.Grid.PiecesGrid[x][y].GetColor()) {
            addMovementListener(x, y, newx, newy);
        }
    
        newy = y - 1;
        if (newx >= 0 && newx < 8 && newy >= 0 && newy < 8 && !this.Grid.PiecesGrid[newx][newy].GetPiece().equals("") && this.Grid.PiecesGrid[newx][newy].GetColor() != this.Grid.PiecesGrid[x][y].GetColor()) {
            addMovementListener(x, y, newx, newy);
        }
        if (!this.Grid.PiecesGrid[x][y].GetMoved()) {
            newx = x + 2 * direction;
            newy = y;
            if (newx >= 0 && newx < 8 && newy >= 0 && newy < 8 && this.Grid.PiecesGrid[newx][newy].GetPiece().equals("")) {
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
                if (this.Grid.PiecesGrid[x][y].GetColor() == "Black" && this.Grid.PiecesGrid[newX][newY].GetColor() == "Black"){
                    continue;
                }else if (this.Grid.PiecesGrid[x][y].GetColor() == "White" && this.Grid.PiecesGrid[newX][newY].GetColor() == "White"){
                    continue;
                }
                addMovementListener(x, y, newX, newY);
            }
        }
    }

    private void ShowQueen(int x, int y) {
        System.out.println("move Queen");
        FillCancelButton();
    
        for (int i = x + 1; i < 8; i++) {
            if (!this.Grid.PiecesGrid[i][y].GetPiece().equals("")) {
                if (!this.Grid.PiecesGrid[i][y].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                    addMovementListener(x, y, i, y);
                }
                break;
            }
            addMovementListener(x, y, i, y);
        }
    
        for (int i = x - 1; i >= 0; i--) {
            if (!this.Grid.PiecesGrid[i][y].GetPiece().equals("")) {
                if (!this.Grid.PiecesGrid[i][y].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                    addMovementListener(x, y, i, y);
                }
                break;
            }
            addMovementListener(x, y, i, y);
        }
    
        for (int j = y + 1; j < 8; j++) {
            if (!this.Grid.PiecesGrid[x][j].GetPiece().equals("")) {
                if (!this.Grid.PiecesGrid[x][j].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                    addMovementListener(x, y, x, j);
                }
                break;
            }
            addMovementListener(x, y, x, j);
        }
    
        for (int j = y - 1; j >= 0; j--) {
            if (!this.Grid.PiecesGrid[x][j].GetPiece().equals("")) {
                if (!this.Grid.PiecesGrid[x][j].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                    addMovementListener(x, y, x, j);
                }
                break;
            }
            addMovementListener(x, y, x, j);
        }
    
        for (int i = 1; i < 8; i++) {
            if (x - i >= 0 && y - i >= 0) {
                if (!this.Grid.PiecesGrid[x - i][y - i].GetPiece().equals("")) {
                    if (!this.Grid.PiecesGrid[x - i][y - i].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                        addMovementListener(x, y, x - i, y - i);
                    }
                    break;
                }
                addMovementListener(x, y, x - i, y - i);
            } else {
                break;
            }
        }
    
        for (int i = 1; i < 8; i++) {
            if (x + i < 8 && y + i < 8) {
                if (!this.Grid.PiecesGrid[x + i][y + i].GetPiece().equals("")) {
                    if (!this.Grid.PiecesGrid[x + i][y + i].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                        addMovementListener(x, y, x + i, y + i);
                    }
                    break;
                }
                addMovementListener(x, y, x + i, y + i);
            } else {
                break;
            }
        }
    
        for (int i = 1; i < 8; i++) {
            if (x - i >= 0 && y + i < 8) {
                if (!this.Grid.PiecesGrid[x - i][y + i].GetPiece().equals("")) {
                    if (!this.Grid.PiecesGrid[x - i][y + i].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                        addMovementListener(x, y, x - i, y + i);
                    }
                    break;
                }
                addMovementListener(x, y, x - i, y + i);
            } else {
                break;
            }
        }
    
        for (int i = 1; i < 8; i++) {
            if (x + i < 8 && y - i >= 0) {
                if (!this.Grid.PiecesGrid[x + i][y - i].GetPiece().equals("")) {
                    if (!this.Grid.PiecesGrid[x + i][y - i].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                        addMovementListener(x, y, x + i, y - i);
                    }
                    break;
                }
                addMovementListener(x, y, x + i, y - i);
            } else {
                break;
            }
        }
    }
    
    
    

    private void ShowBishop(int x, int y) {
        System.out.println("move Bishop");
        FillCancelButton();
    
        for (int i = 1; i < 8; i++) {
            if (x - i >= 0 && y - i >= 0) {
                if (!this.Grid.PiecesGrid[x - i][y - i].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                    addMovementListener(x, y, x - i, y - i);
                    if (!this.Grid.PiecesGrid[x - i][y - i].GetPiece().equals("")) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    
        for (int i = 1; i < 8; i++) {
            if (x + i < 8 && y + i < 8) {
                if (!this.Grid.PiecesGrid[x + i][y + i].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                    addMovementListener(x, y, x + i, y + i);
                    if (!this.Grid.PiecesGrid[x + i][y + i].GetPiece().equals("")) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    
        for (int i = 1; i < 8; i++) {
            if (x - i >= 0 && y + i < 8) {
                if (!this.Grid.PiecesGrid[x - i][y + i].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                    addMovementListener(x, y, x - i, y + i);
                    if (!this.Grid.PiecesGrid[x - i][y + i].GetPiece().equals("")) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    
        for (int i = 1; i < 8; i++) {
            if (x + i < 8 && y - i >= 0) {
                if (!this.Grid.PiecesGrid[x + i][y - i].GetColor().equals(this.Grid.PiecesGrid[x][y].GetColor())) {
                    addMovementListener(x, y, x + i, y - i);
                    if (!this.Grid.PiecesGrid[x + i][y - i].GetPiece().equals("")) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }
    
    

    private void ShowRook(int x, int y) {
        System.out.println("move Rook");
        FillCancelButton();
        for (int i = x + 1; i < 8; i++) {
            if (!this.Grid.PiecesGrid[i][y].GetPiece().equals("")) {
                if (this.Grid.PiecesGrid[x][y].GetColor() == "Black" && this.Grid.PiecesGrid[i][y].GetColor() == "White"){
                    addMovementListener(x, y, i, y);
                }else if (this.Grid.PiecesGrid[x][y].GetColor() == "White" && this.Grid.PiecesGrid[i][y].GetColor() == "Black"){
                    addMovementListener(x, y, i, y);
                }
                break;
            }
            addMovementListener(x, y, i, y);
        }
    
        for (int i = x - 1; i >= 0; i--) {
            if (!this.Grid.PiecesGrid[i][y].GetPiece().equals("")) {
                if (this.Grid.PiecesGrid[x][y].GetColor() == "Black" && this.Grid.PiecesGrid[i][y].GetColor() == "White"){
                    addMovementListener(x, y, i, y);
                }else if (this.Grid.PiecesGrid[x][y].GetColor() == "White" && this.Grid.PiecesGrid[i][y].GetColor() == "Black"){
                    addMovementListener(x, y, i, y);
                }
                break;
            }
            addMovementListener(x, y, i, y);
        }
        for (int j = y + 1; j < 8; j++) {
            if (!this.Grid.PiecesGrid[x][j].GetPiece().equals("")) {
                if (this.Grid.PiecesGrid[x][y].GetColor() == "Black" && this.Grid.PiecesGrid[x][j].GetColor() == "White"){
                    addMovementListener(x, y, x, j);
                }else if (this.Grid.PiecesGrid[x][y].GetColor() == "White" && this.Grid.PiecesGrid[x][j].GetColor() == "Black"){
                    addMovementListener(x, y, x, j);
                }
                break;
            }
            addMovementListener(x, y, x, j);
        }
    
        for (int j = y - 1; j >= 0; j--) {
            if (!this.Grid.PiecesGrid[x][j].GetPiece().equals("")) {
                if (this.Grid.PiecesGrid[x][y].GetColor() == "Black" && this.Grid.PiecesGrid[x][j].GetColor() == "White"){
                    addMovementListener(x, y, x, j);
                }else if (this.Grid.PiecesGrid[x][y].GetColor() == "White" && this.Grid.PiecesGrid[x][j].GetColor() == "Black"){
                    addMovementListener(x, y, x, j);
                }
                break;
            }
            addMovementListener(x, y, x, j);
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
                if (this.Grid.PiecesGrid[x][y].GetColor() == "Black" && this.Grid.PiecesGrid[newX][newY].GetColor() == "Black"){
                    continue;
                }else if (this.Grid.PiecesGrid[x][y].GetColor() == "White" && this.Grid.PiecesGrid[newX][newY].GetColor() == "White"){
                    continue;
                }
                addMovementListener(x, y, newX, newY);
            }

        }
    }
}
