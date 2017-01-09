
public class TTT_Sw_grafik_Spielfeld {

	public int m = 8 ; //length of the matrix 8 as default
	public int[][] matrix = new int[20][20]; //the matrix that represents the board of the game
	public int[] x = new int[20]; 
	public int[] y = new int[20];
	public int s = 1; // player number
	public int z = 0; //position of the game 0 for stopped 1 for playing

	/**
	 * constructor of the class which setting all the matrix values to zero
	 */
	TTT_Sw_grafik_Spielfeld() {
		for (int i = 0; i < m; i++)
			for (int j = 0; j < m; j++)
				matrix[i][j] = 0;
		
	}

	/**
	 * this function initialize the beginning of the game
	 * with 2 black and 2 white circles and it's legal movements 
	 */
	void init(){
		
		//setting up the players disks
		for (int i = 0; i < m; i++)
			for (int j = 0; j < m; j++)
				if(i == m/2 && j == m/2){
					matrix[i-1][j-1] =1;
					matrix[i][j-1] =2;
					matrix[i-1][j] =2;
					matrix[i][j] =1;
				}
		legal_B(s); //check the legal movement of the disks
	}
	
	/**
	 * @param zn : value of x on the matrix
	 * @param sn : value of y on the matrix
	 * this function setting the clicked square on the matrix
	 * to the relevant  player's number ad changes the playing person
	 */
	void zug(int zn, int sn) {
		if (matrix[zn][sn] == 0) {
			matrix[zn][sn] = s;
			if (s == 1)
				s = 2;
			else
				s = 1;
		}
	}
	
	/**
	 * @param p : is the number of the player 1 for black and 2 for white
	 * this function checks all the possible legal actions through multiple functions 
	 */
	void legal_B(int p){
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				if(matrix[i][j]==p){
					l_horizontal(i,j,p);
					l_vertical(i,j,p);
					l_diagonal(i,j,p);
					l_diagonal1(i,j,p);
				}
			}
		}
	}
	

	/**
	 * @param i : x value of the disk
	 * @param j : y value of the disk
	 * @param p is the players number
	 * this function checks the vertical legal movements and setting 
	 * it's value on the matrix of 3
	 */
	private void l_vertical(int i, int j, int p) {
		if(p==1)
			p+=1;
		else
			p-=1;
		int y=j;
			if(y>1 && matrix[i][y-1]==p){
				y=y-1;
				while(y > 0 && matrix[i][y]==p){
					y--;
				}
				if(matrix[i][y] ==0)
					matrix[i][y]=3;
			}
			
			if(j<m && matrix[i][j+1]==p){
				j=j+1;
				while(j <m && matrix[i][j]==p){
					j++;
				}
				if(matrix[i][j] == 0)
					matrix[i][j]=3;		
			}
		
	}

	/**
	 * @param i : x value of the disk
	 * @param j : y value of the disk
	 * @param p is the players number
	 * this function checks the diagonal legal movements from right to left and setting 
	 * it's value on the matrix of 3
	 */
	private void l_diagonal(int i, int j,int p) {
		
		if(p==1)
			p+=1;
		else
			p-=1;
		int x=i;
		int y=j;
		if(x>1 && y>1 && matrix[x-1][y-1]==p){
			x=x-1;
			y=y-1;
			while(x > 0 && y>0 && matrix[x][y]==p){
				x--;
				y--;
			}
			if(matrix[x][y] ==0)
				matrix[x][y]=3;
		}
		
		if(i<m && j<m && matrix[i+1][j+1]==p){
			i=i+1;
			j=j+1;
			while(i <m && j<m && matrix[i][j]==p){
				i++;
				j++;
			}
			if(matrix[i][j] == 0)
				matrix[i][j]=3;		
		}

	}

	/**
	 * @param i : x value of the disk
	 * @param j : y value of the disk
	 * @param p is the players number
	 * this function checks the diagonal legal movements from left to right and setting 
	 * it's value on the matrix of 3
	 */
private void l_diagonal1(int i, int j,int p) {
		
		if(p==1)
			p+=1;
		else
			p-=1;
		int x=i;
		int y=j;
		if(x>=0 && y>1 && matrix[x+1][y-1]==p){
			x=x+1;
			y=y-1;
			while(x < m && y>0 && matrix[x][y]==p){
				x++;
				y--;
			}
			if(matrix[x][y] ==0)
				matrix[x][y]=3;
		}
		
		if(i>1 && j>1 && matrix[i-1][j+1]==p){
			i=i-1;
			j=j+1;
			while(i >1 && j<m && matrix[i][j]==p){
				i--;
				j++;
			}
			if(matrix[i][j] == 0)
				matrix[i][j]=3;		
		}

	}


/**
 * @param i : x value of the disk
 * @param j : y value of the disk
 * @param p is the players number
 * this function checks the horizontal legal movements and setting 
 * it's value on the matrix of 3
 */
	private void l_horizontal(int i, int j,int p) {
	if(p==1)
		p+=1;
	else
		p-=1;
	int x=i;
		if(x>1 && matrix[x-1][j]==p){
			x=x-1;
			while(x > 0 && matrix[x][j]==p){
				x--;
			}
			if(matrix[x][j] ==0)
				matrix[x][j]=3;
		}
		
		if(i<m && matrix[i+1][j]==p){
			i=i+1;
			while(i <m && matrix[i][j]==p){
				i++;
			}
			if(matrix[i][j] == 0)
				matrix[i][j]=3;		
		}
	}
	
	
	/**
	 * @param i : x value of the clicked 
	 * @param j
	 * @param p
	 * if a player clicked on a legal square he will make the relevant disks flip 
	 * to it's color using the two functions d_one and d_two which basically they check for every possible disk flip 
	 * and change the matrix
	 */
	void conn(int i, int j, int p){
		d_one(i,j,p);
		d_two(i,j,p);
	}

	private void d_one(int i, int j, int p) {
		int x,r;
		if(p==1)
			x=p+1;
		else
			x=p-1;
		boolean b;
			if(i>2 && matrix[i-1][j]==p){
				b=true;
				r=i-1;
				while(matrix[r][j]==p && b==true){
					if(r-1<1 || matrix[r-1][j] == 0)
						b=false;
					r--;
				}
				if(b==true)
					for(int e=i;e>r;e--)
						matrix[e][j]=x;
			}
			
			if(i<m-2 && matrix[i+1][j]==p){
				b=true;
				r=i+1;
				while(matrix[r][j]==p && b==true){
					if( matrix[r+1][j] == 0 )
						b=false;
					r++;
				}
				if(b==true)
					for(int e=i;e<r;e++)
						matrix[e][j]=x;
			}
			
			if(j<=m-2 && matrix[i][j+1]==p){
				b=true;
				r=j+1;
				while(matrix[i][r]==p && b==true){
					if(r-1<m && matrix[i][r+1] == 0 )
						b=false;
					r++;
				}
				if(b==true)
					for(int e=j;e<r;e++)
						matrix[i][e]=x;
			}
			
			if(j>2 && matrix[i][j-1]==p){
				b=true;
				r=j-1;
				
				while(matrix[i][r]==p && b==true){
					if(r-1<1 || matrix[i][r-1] == 0)
						b=false;
					r--;
				}
				if(b==true)
					for(int e=j;e>r;e--)
						matrix[i][e]=x;
			}
	}
	
	private void d_two(int i, int j, int p){
		int x,r,d;
		if(p==1)
			x=p+1;
		else
			x=p-1;
		boolean b;
		if(j>=2 && i>=2 && matrix[i-1][j-1]==p){
			b=true;
			r = j-1;
			d = i-1;
			while(matrix[d][r]==p && b==true){
				if(d-1==0 || r-1==0 || matrix[d-1][r-1] == 0)
					b=false;
				r--;
				d--;
			}
			if(b==true)
				for(int e=j, o=i;e>r && o>d;e--,o--)
					matrix[o][e]=x;
		}
		
		if(j<=m-2 && i<=m-2 && matrix[i+1][j+1]==p){
			b=true;
			r = j+1;
			d = i+1;
			while(matrix[d][r]==p && b==true){
				if(d+1<=m && r+1<=m && matrix[d+1][r+1] == 0)
					b=false;
				r++;
				d++;
			}
			if(b==true)
				for(int e=j, o=i;e<r && o<d;e++,o++)
					matrix[o][e]=x;
		}
		
		if(j<m-2 && i>2 && matrix[i-1][j+1]==p){
			b=true;
			r = j+1;
			d = i-1;
			while(matrix[d][r]==p && b==true){
				if(d-1<1 || r+1>m || matrix[d-1][r+1] == 0)
					b=false;
				r++;
				d--;
			}
			if(b==true)
				for(int e=j, o=i;e<r && o>d;e++,o--)
					matrix[o][e]=x;
		}
		
		if(j>=2 && i<=m-2 && matrix[i+1][j-1]==p){
			b=true;
			r = j-1;
			d = i+1;
			while(matrix[d][r]==p && b==true){
				if(d+1==m || r-1 ==0 || matrix[d+1][r-1] == 0)
					b=false;
				r--;
				d++;
			}
			if(b==true)
				for(int e=j, o=i;e>r && o<d;e--,o++)
					matrix[o][e]=x;
		}
		
		
		
	}
	
	/**
	 * @return false if there is no more legal movements to switch the players
	 */
	public boolean cheh(){
		boolean b=false;
		for(int i=0;i<m;i++)
			for(int j=0;j<m;j++)
				if(matrix[i][j]==3)
					b=true;
		return b;
	}
	
	public int count(int p){
		int s=0;
		for(int i=0;i<m;i++)
			for(int j=0;j<m;j++)
				if(matrix[i][j]==p)
					s++;
		return s;
	}
	
	/**
	 * @return the player who won the game by comparing the disks of both players
	 */
	public int counti(){
		int s=0,s1=0;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < m; j++)
				if(matrix[i][j]==1)
					s++;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < m; j++)
				if(matrix[i][j]==2)
					s1++;
		
		if(Math.max(s1,s) == s1)
			return 2;
		else
			return 1;
	}

	/**
	 * @return 1 if the game is over and 0 if there is possibility to play
	 */
	int gewonnen() {
		int w = 1;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < m; j++)
				if (matrix[i][j] == 0 || matrix[i][j] == 3)
					w = 0;
		
		return w;
	}
	
		
		
	
}
