import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class CancelMovement implements ActionListener{
    private int x;
    private int y;
    private Grid Grid;
    public CancelMovement(int x,int y,Grid BackgroundGrid) {
        this.x = x;
        this.y = y;
        this.Grid = BackgroundGrid;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("reset");
        boolean RowColor = true;
        this.Grid.Turn = !this.Grid.Turn;
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
