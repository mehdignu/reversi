
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TTT_Sw_grafik extends JFrame {
	TTT_Sw_grafik_Spielfeld T;
	
	
	/* (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 * paint method changes the look of the components
	 */
	public void paint(Graphics g) {
		super.paintComponents(g);
		char ST;
		int erg;
	
		int r = T.m;
		int z = T.z;
		for(int i =0;i<= r;i++){			
			T.y[i] =138 + i * 30;
		}

		for (int i = 0; i < r; i++)
			for (int j = 0; j < r; j++) {
				if(z != 0){
				if (T.matrix[i][j] == 0) {
					g.setColor(new Color(18, 110, 4));
					
				} else if (T.matrix[i][j] == 1) { // if 1 found in the matrix than a black disk will be drawn 
					g.setColor(Color.black);
					g.fillOval(T.x[i]+3, T.y[j]+3, 25, 25);
	
				} else if (T.matrix[i][j] == 2){ // if 2 found in the matrix than a white disk will be drawn 
					g.setColor(Color.white);
					g.fillOval(T.x[i]+3, T.y[j]+3, 25, 25);
				}else if (T.matrix[i][j] == 3){ // if 3 found in the matrix than a transparent disk will be drawn 
					Color c=new Color(0f,0f,0f,.3f );
					g.setColor(c);
					g.fillOval(T.x[i]+3, T.y[j]+3, 25, 25);
				}
				}
				
				//border of the matrix
				g.setColor(Color.black);
				//draw the matrix
				g.drawRect(60 + j * 30, 138 + i * 30, 30, 30);
				T.x[i] =60 + i * 30;

				
			}
		T.x[r] =60 + r * 30;

		//draw number on the left
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 18));
		//draw the big rectangle
		g.drawRect(51, 129, 32*r+r-4, 33*r-r+3);
		g.setColor(new Color(178, 191, 176));
		
		int p=100;
		for(int i=1;i<=r;i++){
			p+=30;
			g.drawString(String.valueOf(i),30,p+30);
		}
		
		p=10;
		g.setFont(new Font("Serif", Font.BOLD, 18));
		g.setColor(new Color(178, 191, 176));
		for(int i=1;i<=r;i++){
			p+=30;
			g.drawString(Character.toString ((char) (i+64)),p+30,110);
		}	
		//draw the lower rectangle wich contains the status Playing or Stopped
		g.setColor(new Color(198, 185, 185));
		g.fillRect(4, 467, 700, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 19));
		String msg = "Playing";
		if(z==0)
			msg = "Stopped";
		g.drawString(msg, 10, 485);
		
	
		if(z != 0){ // the game is stopped if z equal to zero and can not draw anything on the board
		g.setColor(new Color(32, 159, 6));
		g.fillRect(5, 54, 700, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 19));
		if (T.s == 1)
			g.drawString("black's turn", 52, 70);
		else
			g.drawString("white's turn", 52, 70);
		
		
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 19));
		int d;
		if(T.s==1)
			d=2;
		else
			d=1;
		g.drawString(String.valueOf(T.count(1)), 5, 70);
		g.setColor(Color.white);
		g.drawString(String.valueOf(T.count(2)), 29, 70);
		
		if(T.gewonnen() == 1){
			g.setColor(new Color(32, 159, 6));
			g.fillRect(52, 54, 700, 30);
			if(T.counti() == 1){ //checks the player with the most disks and announce him the official winner ot the game
				g.setColor(Color.black);
				g.drawString("Black has won !", 52, 70);
				JOptionPane.showMessageDialog(new JFrame(), "Black has won !");
				}else{
					g.setColor(Color.white);
					g.drawString("White has won", 52, 70);
					JOptionPane.showMessageDialog(new JFrame(), "White has won !");
				}
		}
			
		
		}
	
		
	}

	/**
	 *  Constructor that set the main setting to the window 
	 */
	TTT_Sw_grafik() {
		
		getContentPane().setBackground( new Color(18,110,4) );
		T = new TTT_Sw_grafik_Spielfeld();
		 int r = T.m;
		// Angaben zum Fenster
		setTitle("Tic Tac Toe");
		createMenuBar();
		setSize(400,500);
		setLocation(200, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		addMouseListener(new CMeinMausAdapter());
		setVisible(true);
		
	}

	/**
	 * @author mehdi
	 * on the mouse click the relevant square in the matrix will have the number of the player
	 * and then be colored after that the legal movements will be checked and also if there is no legal movements more
	 * a player will give his turn to the other player
	 */
	class CMeinMausAdapter extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			
					
			int x, y, zn = 0, sn = 0;
			x = e.getX();
			y = e.getY();
			for(int i=0;i<=T.m;i++ ){
				if(T.x[i]<=x && T.x[i+1]>=x)
					zn = i; 
			}
	
			for(int i=0;i<T.m;i++ ){
				if(T.y[i]<=y && T.y[i+1]>=y)
					sn = i; 
			}
			
			if(T.matrix[zn][sn] == 3){
				for (int i = 0; i < T.m; i++)
					for (int j = 0; j < T.m; j++)
						if(T.matrix[i][j]==3)
							T.matrix[i][j]=0;
			T.zug(zn, sn);
			T.conn(zn, sn, T.s);
			
				
			
			if(T.s == 1)
				T.legal_B(1);
			else
				T.legal_B(2);
			
			if(T.cheh()==false && T.gewonnen() != 1){
				if(T.s==1){
					JOptionPane.showMessageDialog(new JFrame(), "Black should give his turn !");
					T.s=2;
					T.legal_B(2);
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(), "White should give his turn !");
					T.s=1;
					T.legal_B(1);
				}
			}
			
			repaint();
			}
		
	}
		}
	
	
	/**
	 * if the user clicked settings in the menu he will have to choose another size of the board
	 */
	private void popup(){
		 final JPanel panel = new JPanel();
		 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        final JRadioButton button1 = new JRadioButton("6x6");
	        final JRadioButton button2 = new JRadioButton("8x8");
	        final JRadioButton button3 = new JRadioButton("10x10");
	        if(T.m == 8)
	        	button2.setSelected(true);
	        if(T.m == 6)
	        	button1.setSelected(true);
	        if(T.m == 10)
	        	button3.setSelected(true);
	        
	        JLabel headerLabel = new JLabel("", JLabel.CENTER);        
	        headerLabel.setText("choose size:"); 
	        panel.add(headerLabel);
	        panel.add(button1);
	        panel.add(button2);
	        panel.add(button3);
	        
	        
	        button1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	        
	                T.m = 6;
	                button1.setSelected(true);
	                button2.setSelected(false);
	                button3.setSelected(false);
	                repaint();
	            }
	        });
	        button2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                T.m = 8;
	                
	                button1.setSelected(false);
	                button2.setSelected(true);
	                button3.setSelected(false);
	                repaint();
	            }
	        });
	        button3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                T.m = 10;
	                button1.setSelected(false);
	                button2.setSelected(false);
	                button3.setSelected(true);
	                repaint();
	            }
	        });
	        
	          JOptionPane.showConfirmDialog(null, panel, "Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        

	}
	
	/**
	 * the menu of the game which can be used the start a new game, stop the game 
	 * and change the size of the board if the game is stopped 
	 */
	private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        ImageIcon iconNew = new ImageIcon("new.png");
        ImageIcon iconOpen = new ImageIcon("open.png");
        ImageIcon pause = new ImageIcon("pause.png");

        ImageIcon iconExit = new ImageIcon("exit.png");
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMi = new JMenuItem("New", iconNew);
        
        newMi.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        
        JMenuItem StopMi = new JMenuItem("Stop", iconNew);
        
        StopMi.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        
        JMenuItem openMi = new JMenuItem("Settings", iconOpen);
       
        JMenuItem exitMi = new JMenuItem("Exit", iconExit);
        exitMi.setToolTipText("Exit application");
        StopMi.setEnabled(false);
        exitMi.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        
        StopMi.addActionListener((ActionEvent event) -> {
            T.z = 0;
            openMi.setEnabled(true);
            StopMi.setEnabled(false);
            repaint();
        });
        
        openMi.addActionListener((ActionEvent event) -> {
        	
            popup();
        });
        
        newMi.addActionListener((ActionEvent event) -> {
        	int r = T.m;
        	T.z = 1;
    		for (int i = 0; i < r; i++){
    			for (int j = 0; j < r; j++) {
    				T.matrix[i][j] = 0;	
    					}
    				}
    		StopMi.setEnabled(true);
    	        openMi.setEnabled(false);

    	        T.init();
        	  repaint();
        });
        
        fileMenu.add(newMi);
        fileMenu.add(StopMi);
        fileMenu.add(openMi);
        fileMenu.addSeparator();
        fileMenu.add(exitMi);
        menubar.add(fileMenu);
        setJMenuBar(menubar);
    }

	/**
	 * @param args main program
	 */
	public static void main(String args[]) {

		new TTT_Sw_grafik();
	}
}
