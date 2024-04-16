import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class Grid extends JFrame {
    public JPanel[][] BackgroundGrid;
    public Case[][] PiecesGrid;
    public JLayeredPane LayeredPane;
    public boolean Turn = true;
    public Grid() {
        BackgroundGrid = new JPanel[8][8];
        PiecesGrid = new Case[8][8];
        boolean RowColor = true;
        boolean Turn = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        LayeredPane = new JLayeredPane();

        for(int i = 0; i < 8; i++) {
            RowColor = !RowColor;
            for(int j = 0; j < 8; j++) {
                RowColor = !RowColor;
                BackgroundGrid[i][j] = new JPanel();
                if (RowColor) {
                    BackgroundGrid[i][j].setBackground(new Color(238,238,210));
                } else {
                    BackgroundGrid[i][j].setBackground(new Color(118,130,86));
                }
                BackgroundGrid[i][j].setBounds(j * 130, i * 130, 130, 130);
                LayeredPane.add(BackgroundGrid[i][j], JLayeredPane.DEFAULT_LAYER);
            }
        }
        this.SetUpGrid();

        add(LayeredPane, BorderLayout.CENTER);
        pack();
        setSize(1080,1080);
        setVisible(true);
    }

    private void SetUpGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                PiecesGrid[i][j] = new Case();
                PiecesGrid[i][j].Panel = new JButton();
                PiecesGrid[i][j].Panel.setOpaque(false);
                PiecesGrid[i][j].Panel.setBounds(j * 130, i * 130, 130, 130);
                PiecesGrid[i][j].Panel.setBorderPainted(false);
                PiecesGrid[i][j].Panel.setFocusPainted(false);
                PiecesGrid[i][j].Panel.setContentAreaFilled(false);
                PiecesGrid[i][j].Panel.setOpaque(false);
                PiecesGrid[i][j].Panel.addActionListener(new ShowMovement(i, j,this));
                LayeredPane.add(PiecesGrid[i][j].Panel, JLayeredPane.PALETTE_LAYER);
            }
        }
        setPieceImage("Image/BlackPieces/Rook.svg.png", PiecesGrid[0][0].Panel);
        PiecesGrid[0][0].SetPiece("Rook");
        setPieceImage("Image/BlackPieces/Knight.svg.png", PiecesGrid[0][1].Panel);
        PiecesGrid[0][1].SetPiece("Knight");
        setPieceImage("Image/BlackPieces/Bishop.svg.png", PiecesGrid[0][2].Panel);
        PiecesGrid[0][2].SetPiece("Bishop");
        setPieceImage("Image/BlackPieces/Queen.svg.png", PiecesGrid[0][3].Panel);
        PiecesGrid[0][3].SetPiece("Queen");
        setPieceImage("Image/BlackPieces/King.svg.png", PiecesGrid[0][4].Panel);
        PiecesGrid[0][4].SetPiece("King");
        setPieceImage("Image/BlackPieces/Bishop.svg.png", PiecesGrid[0][5].Panel);
        PiecesGrid[0][5].SetPiece("Bishop");
        setPieceImage("Image/BlackPieces/Knight.svg.png", PiecesGrid[0][6].Panel);
        PiecesGrid[0][6].SetPiece("Knight");
        setPieceImage("Image/BlackPieces/Rook.svg.png", PiecesGrid[0][7].Panel);
        PiecesGrid[0][7].SetPiece("Rook");
        for (int i = 0 ; i < 8;i++){
            setPieceImage("Image/BlackPieces/Pawn.svg.png", PiecesGrid[1][i].Panel);
            PiecesGrid[1][i].SetPiece("Pawn");
            PiecesGrid[1][i].SetColor("Black");
        }
        for (int i = 0 ; i < 8;i++){
            PiecesGrid[0][i].SetColor("Black");
        }
        setPieceImage("Image/WhitePieces/Rook.svg.png", PiecesGrid[7][0].Panel);
        PiecesGrid[7][0].SetPiece("Rook");
        setPieceImage("Image/WhitePieces/Knight.svg.png", PiecesGrid[7][1].Panel);
        PiecesGrid[7][1].SetPiece("Knight");
        setPieceImage("Image/WhitePieces/Bishop.svg.png", PiecesGrid[7][2].Panel);
        PiecesGrid[7][2].SetPiece("Bishop");
        setPieceImage("Image/WhitePieces/Queen.svg.png", PiecesGrid[7][3].Panel);
        PiecesGrid[7][3].SetPiece("Queen");
        setPieceImage("Image/WhitePieces/King.svg.png", PiecesGrid[7][4].Panel);
        PiecesGrid[7][4].SetPiece("King");
        setPieceImage("Image/WhitePieces/Bishop.svg.png", PiecesGrid[7][5].Panel);
        PiecesGrid[7][5].SetPiece("Bishop");
        setPieceImage("Image/WhitePieces/Knight.svg.png", PiecesGrid[7][6].Panel);
        PiecesGrid[7][6].SetPiece("Knight");
        setPieceImage("Image/WhitePieces/Rook.svg.png", PiecesGrid[7][7].Panel);
        PiecesGrid[7][7].SetPiece("Rook");
        for (int i = 0 ; i < 8;i++){
            setPieceImage("Image/WhitePieces/Pawn.svg.png", PiecesGrid[6][i].Panel);
            PiecesGrid[6][i].SetPiece("Pawn");
            PiecesGrid[6][i].SetColor("White");
        }
        for (int i = 0 ; i < 8;i++){
            PiecesGrid[7][i].SetColor("White");
        }
        
    }


    //Thx for ChatGPT , 1H for this !!
    public void setPieceImage(String imagePath, JButton targetButton) {
        ImageIcon icon = new ImageIcon(imagePath);
    
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
    
            JLabel label = new JLabel(icon) {
                @Override
                public void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    
                    double scaleX = (double) getWidth() / icon.getIconWidth();
                    double scaleY = (double) getHeight() / icon.getIconHeight();
    
                    AffineTransform at = AffineTransform.getScaleInstance(scaleX, scaleY);
                    g2d.drawImage(icon.getImage(), at, this);
    
                    g2d.dispose();
                }
            };
            targetButton.setLayout(new BorderLayout());
            targetButton.add(label, BorderLayout.CENTER);
    
            targetButton.revalidate();
            targetButton.repaint();
        } else {
            System.out.println("Image loading failed!");
        }
    }
    public void removePieceImage(JButton button) {
        button.removeAll();
        button.revalidate();
        button.repaint();
    }
    
    
}
