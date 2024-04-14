import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class DoMovement implements ActionListener{
    private int x;
    private int y;
    private int newx;
    private int newy;
    private Grid Grid;
    public DoMovement(int x,int y ,int newx,int newy,Grid BackgroundGrid) {
        this.x = x;
        this.y = y;
        this.newx = newx;
        this.newy = newy;
        this.Grid = BackgroundGrid;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("move"+x+y+"to"+newx+newy+"by "+this.Grid.PiecesGrid[x][y].GetPiece());
        this.Grid.PiecesGrid[newx][newy].SetMoved(true);
        this.Grid.removePieceImage(Grid.PiecesGrid[x][y].Panel);
    
        if (this.Grid.PiecesGrid[x][y].GetColor() == "White"){
            this.Grid.setPieceImage("Image/WhitePieces/"+this.Grid.PiecesGrid[x][y].GetPiece()+".svg.png", this.Grid.PiecesGrid[newx][newy].Panel);
        }else {
            this.Grid.setPieceImage("Image/BlackPieces/"+this.Grid.PiecesGrid[x][y].GetPiece()+".svg.png", this.Grid.PiecesGrid[newx][newy].Panel);
        }
        this.Grid.PiecesGrid[newx][newy].SetPiece(this.Grid.PiecesGrid[x][y].GetPiece());
        this.Grid.PiecesGrid[newx][newy].SetColor(this.Grid.PiecesGrid[x][y].GetColor());
        this.Grid.PiecesGrid[x][y].SetPiece("");
        this.Grid.PiecesGrid[x][y].SetColor("");
        
        boolean RowColor = true;
        for(int i = 0; i < 8; i++) {
            RowColor = !RowColor;
            for(int j = 0; j < 8; j++) {
                RowColor = !RowColor;
                if (RowColor) {
                    Grid.BackgroundGrid[i][j].setBackground(new Color(238,238,210));
                } else {
                    Grid.BackgroundGrid[i][j].setBackground(new Color(118,130,86));
                }
                Grid.BackgroundGrid[i][j].setBounds(j * 130, i * 130, 130, 130);
                for(ActionListener al : this.Grid.PiecesGrid[i][j].Panel.getActionListeners()) {
                    this.Grid.PiecesGrid[i][j].Panel.removeActionListener(al);
                }
                Grid.PiecesGrid[i][j].Panel.addActionListener(new ShowMovement(i,j,Grid));
                // Grid.LayeredPane.add(Grid.BackgroundGrid[i][j], JLayeredPane.DEFAULT_LAYER);
            }
        }
    }
}
