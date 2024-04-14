import javax.swing.JButton;
import javax.swing.JPanel;

public class Case {
    public JButton Panel;
    private String Piece;
    private String Color;
    private boolean Moved;
    public Case(){
        this.Panel = new JButton() ;
        this.Piece = "";
        this.Color = "";
    }
    public String GetPiece(){
        return this.Piece;
    }
    public boolean GetMoved(){
        return this.Moved;
    }
    public JButton GetPanel(){
        return this.Panel;
    }
    public String GetColor(){
        return this.Color;
    }
    public void SetPiece(String piece){
        this.Piece = piece;
    }
    public void SetMoved(boolean moved){
        this.Moved = moved;
    }
    public void SetJPanel(JButton panel){
        this.Panel = panel;
    }
    public void SetColor(String color){
        this.Color = color;
    }
}
