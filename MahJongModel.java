/*
 * Romela Azizyan
 * CS 3230 - MahJong
*/
import java.awt.*;

public class MahJongModel implements LayoutManager
{

	private static int counter = 0;
	private static int tileNumber  = 0;
	private static int offset = 20;
	

	@Override
	public void layoutContainer(Container parent)
	{
		int nComps = parent.getComponentCount();
		int previousWidth = 0;
		int previousHeight = 0;
		int x = parent.getWidth()/2-7*Tile.getWIDTH()-3*Tile.getDEPTH();
		int y = offset + parent.getHeight()/2-Tile.getDEPTH();

		for (int i = 0; i < nComps; i++)
		{
			Component c = parent.getComponent(i);
			
			Dimension d = c.getPreferredSize();

				if(tileNumber == 144 || tileNumber == 145)		//Don't add anything to additional items added
					break;
				//outlayers
				if(i ==0)	// tile is dar left tile
				{
					MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[100]);
					MahJongBoard.getTILES()[tileNumber].setToR2(MahJongBoard.getTILES()[88]);
				}
				//layer 1
				if(i == 1)	//Tippytop tile
				{	
					x = parent.getWidth()/2-Tile.getWIDTH()/2+Tile.getDEPTH(); 
					y = 3*offset + parent.getHeight()/2-2*Tile.getHEIGHT()/2-Tile.getDEPTH();
				}
				
				// layer 2
				if (i > 1 && i <6) 
				{
					MahJongBoard.getTILES()[tileNumber].setToB(MahJongBoard.getTILES()[1]);
					if(i == 2)
					{
						MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
						counter = 0;
						x = parent.getWidth()/2-Tile.getWIDTH();
						y = 3*offset + parent.getHeight()/2-Tile.getHEIGHT()/2-Tile.getDEPTH()/2;
					} else {
						
						x += previousWidth - Tile.getDEPTH();
						if(i == 4)
						{
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 2*previousWidth - 2*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
						}
						else {
							MahJongBoard.getTILES()[tileNumber].setToL(MahJongBoard.getTILES()[tileNumber -1]);
						}
					}
				}
				//layer 3
				if (i >= 6 && i <= 22)
				{
					if(i == 6)
					{
						MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
						x = parent.getWidth()/2-2*Tile.getWIDTH()-Tile.getDEPTH();
						y = offset + parent.getHeight()/2+Tile.getHEIGHT()+Tile.getDEPTH();
						counter = 0;
					} else {
						
						if(counter == 4)
						{
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 3*previousWidth - 3*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter = 0;
						}else {
							MahJongBoard.getTILES()[tileNumber].setToL(MahJongBoard.getTILES()[tileNumber -1]);
							if(counter +1 != 4)
								MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x += previousWidth - Tile.getDEPTH();
						}
					}
					//set up tile in front links
					if(tileNumber == 11 || tileNumber == 12)
							((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -9]);
					if(tileNumber == 15 || tileNumber == 16)
						((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -11]);
					counter++;
				}
				//layer 4
				if (i >= 22 && i <= 57)
				{
					if(i ==22)
					{
						MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
						x = parent.getWidth()/2-3*Tile.getWIDTH()-2*Tile.getDEPTH();
						y = offset + parent.getHeight()/2+2*Tile.getHEIGHT()+2*Tile.getDEPTH();
						counter = 0;
					} else {
						
						if(counter == 5)
						{
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 5*previousWidth - 5*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter = 0;
						} else {
							MahJongBoard.getTILES()[tileNumber].setToL(MahJongBoard.getTILES()[tileNumber -1]);
							if(counter +1 != 5)
								MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x += previousWidth - Tile.getDEPTH();
							counter++;
						}
						//set up tile in front links
					if(tileNumber == 29 || tileNumber == 30 || tileNumber == 31 || tileNumber == 32  )
							((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -23]);
					if(tileNumber == 35 || tileNumber == 36 || tileNumber == 37 || tileNumber == 38  )
						((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -25]);
					if(tileNumber == 41 || tileNumber == 42 || tileNumber == 43 || tileNumber == 44  )
						((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -27]);
					if(tileNumber == 47 || tileNumber == 48 || tileNumber == 49 || tileNumber == 50  )
						((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -29]);
					}
					
				}
				//layer 5
				if (i >= 58)
				{
					if(i == 58)
					{
						MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
						x = parent.getWidth()/2-6*Tile.getWIDTH()-3*Tile.getDEPTH();
						y = offset + parent.getHeight()/2+3*Tile.getHEIGHT()+3*Tile.getDEPTH();
						counter = 0;
					} else {
						switch(counter)
						{
						case 11:
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 9*previousWidth - 9*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter++;
							break;
						case 19:
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 8*previousWidth - 8*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter++;
							break;
						case 29:
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 10*previousWidth - 10*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter++;
							break;
						case 41:
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 11*previousWidth - 11*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter++;
							break;
						case 53:
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 10*previousWidth - 10*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter++;
							break;
						case 63:
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 8*previousWidth - 8*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter++;
							break;
						case 71:
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x -= 9*previousWidth - 9*Tile.getDEPTH();
							y -= previousHeight-Tile.getDEPTH();
							counter++;
							break;
						case 83:
							MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							MahJongBoard.getTILES()[tileNumber].setToL(MahJongBoard.getTILES()[99]);
							MahJongBoard.getTILES()[tileNumber].setToL2(MahJongBoard.getTILES()[111]);
							x = parent.getWidth()/2+5*Tile.getWIDTH()-2*Tile.getDEPTH();
							y = offset + parent.getHeight()/2-Tile.getDEPTH();
							counter++;
						default: 
							if(tileNumber != 142)
								MahJongBoard.getTILES()[tileNumber].setToL(MahJongBoard.getTILES()[tileNumber -1]);
							
							if(tileNumber  != 143 &&
									counter != 10 &&
									counter != 18 &&
									counter != 28 &&
									tileNumber != 121 &&
									tileNumber != 129 &&
									tileNumber != 141
									)
								MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[tileNumber +1]);
							x += previousWidth - Tile.getDEPTH();
							counter++;
						}
						
						//set up tile in front links
					if(tileNumber == 71 || tileNumber == 72 || tileNumber == 73 || tileNumber == 74 ||
							tileNumber == 75 || tileNumber == 76)
							((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -49]);
					if(tileNumber == 80 || tileNumber == 81 || tileNumber == 82 || tileNumber == 83 ||
							tileNumber == 84 || tileNumber == 85)
							((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -52]);
					if(tileNumber == 91 || tileNumber == 92 || tileNumber == 93 || tileNumber == 94 ||
							tileNumber == 95 || tileNumber == 96)
							((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -57]);
					if(tileNumber == 103 || tileNumber == 104 || tileNumber ==105 || tileNumber == 106 ||
							tileNumber == 107 || tileNumber == 108)
							((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -63]);
					if(tileNumber == 114 || tileNumber == 115 || tileNumber ==116 || tileNumber == 117 ||
							tileNumber == 118 || tileNumber == 119)
							((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -68]);
					if(tileNumber == 123 || tileNumber == 124 || tileNumber ==125 || tileNumber == 126 ||
							tileNumber == 127 || tileNumber == 128)
							((Tile)c).setToB(MahJongBoard.getTILES()[tileNumber -71]);
					}
					//set up double blocked set special case
					if(tileNumber == 100 || tileNumber == 88)
					{
						MahJongBoard.getTILES()[tileNumber].setToL(MahJongBoard.getTILES()[0]);
						
					}
					if(tileNumber == 99 || tileNumber == 111)
						MahJongBoard.getTILES()[tileNumber].setToR(MahJongBoard.getTILES()[142]);
				}
				
				
				// Set the component's size and position.
				c.setBounds(x, y, d.width, d.height);

				previousWidth = d.width;
				previousHeight = d.height;
				tileNumber++;
		}

	}
	
	public static int getCounter() {return counter;}
	public static void setCounter(int counter) {MahJongModel.counter = counter;}
	public static int getTileNumber() {return tileNumber;}
	public static void setTileNumber(int tileNumber) {MahJongModel.tileNumber = tileNumber;}

	
	
	@Override
	public void addLayoutComponent(String name, Component comp){}
	@Override
	public void removeLayoutComponent(Component comp){}
	@Override
	public Dimension preferredLayoutSize(Container parent){return null;}
	@Override
	public Dimension minimumLayoutSize(Container parent){return null;}

}
