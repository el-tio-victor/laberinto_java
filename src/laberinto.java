

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;



public class laberinto extends JFrame
{
	
	static final String DATA_PATH = "Data2.txt";
	private static char matriz[][];                             //argumentos de metodo almacenarx
	private static String matriz2[][]=new String[20][20];
	private static String matrizAuxiliar[][]=new String[20][20];
	
	
	private static String finals="    ";
	
	
	private JPanel ventana;            //ventana principal
	private JPanel ventanaJuego;        //ventana dentro de ventana
    private JPanel botonesPanel;
    
    
    
    private JButton cargar,iniciar;
    
    private static boolean a=true; 
    private boolean validacion=false;
    private static int cord=18,cord2=1;
	private static int proxPasX=0,proxPasY=0;
	private static int ciclo=0;
    
    
    
    
    //metodo pasar a string el archivo*********************************************************************
    private  static void almacenar(){
		Scanner fileReader=null;
	try{
		fileReader=new Scanner (new  File(DATA_PATH));
		System.out.println("buscando...");
	}
	catch(FileNotFoundException e){
		System.out.println("The file "+DATA_PATH+" was not found!");
		System.out.println("The program terminates now.");
	}
	
	
	Scanner lineScanner;
	String content;

	int p,k;
	
	while(fileReader.hasNextLine())
	{
		int dato;
		char dato2;
		
		content = fileReader.nextLine();
		lineScanner = new Scanner(content);
		dato= lineScanner.nextInt();
		p=dato;
		k=dato;
		
		
	    
		 matriz = new char[p][k];
		for (int x=0;x<20;x++)
		{
			 int z=0;
			content = fileReader.nextLine();
			
			lineScanner = new Scanner(content);	
			for (int y=0;y<20;y++)
			{
				char a=content.charAt(z);
				dato2 = a;
				matriz[x][y] = dato2;
				z++;
			}
		}
		break;
	}
	
	
	}

    
    public static void copiar()
	{
		
		for (int x=0;x<20;x++)
		{
			 
			for (int y=0;y<20;y++)
			{
				char opcion=matriz[x][y];
				switch (opcion)
			     {
			         case '*':
			            matriz2[x][y]="-1";
			             break;
			         case ' ':
				            matriz2[x][y]="0";
				             break;
			         case 'S':
				            matriz2[x][y]="-2";
				             break;
			         case 'G':
				            matriz2[x][y]="-3";
				             break;
				     default:
				    	 matriz2[x][y]="-5";
				    	 System.out.println("Caracter no valido "+matriz[x][y]);
			              
			                 
			     }
				
			}
			
		}
		for (int x=0;x<20;x++)
		{
			if(x<10){
				String raizS=String.valueOf(x);
				finals=finals+raizS+"   ";}
			else{
				String raizS=String.valueOf(x);
				finals=finals+raizS+"  ";
			}
			if (x==19)
			{
				finals=finals+"\n"+" ";
			}
		}
		int b=0;
		for (int s=0;s<20;s++)
		{
			
			String raizS2=String.valueOf(b);
			finals=""+finals+raizS2;
			for (int t=0;t<20;t++)
			{
				
				String opcion2=matriz2[s][t];
				switch (opcion2)
			     {
			         case "-1":
			            finals=finals+"++++";
			             break;
			         case "-2":
			        	 finals=finals+"++S+";
				             break;
			         case "-3":
			        	 finals=finals+"++G+";
				             break;
			         case "0":
			        	 finals=finals+"    ";
				             break;
				     
			              
			}
                     //************************************Matriz Auxiliar**************************************************
				for(int i=0;i<matriz2.length;i++)
				{
					for(int j=0;j<matriz2.length;j++)
					{
						matrizAuxiliar[i][j]=matriz2[i][j];
					}
				}
			
		}
			b++;
			if(b<10){
				finals=finals+"\n"+" ";}
			else
			{
				finals=finals+"\n";
			}
	}
		}
    //metodo constructor
	public laberinto()
	{
	
	this.setTitle("Laberinto");                  //contenedor Principal
	ventana = (JPanel)this.getContentPane();
	Dimension frameSize = new Dimension(500,500);
	ventana.setLayout(new BorderLayout(2,2));
	
	
	
    cargar= new JButton("Cargar archivo");	
    cargar.setSize(200,200);
    
    
    iniciar=new JButton("Iniciar");
    iniciar.setSize(400,400);
    
    
	
	ventanaJuego=new JPanel(); 
	ventanaJuego.setBorder(BorderFactory.createTitledBorder("salida"));
	
	botonesPanel= new JPanel();
	botonesPanel.setLayout(new FlowLayout());    //contenedor de botones
	
	
	
	
	botonesPanel.add(cargar);                    //agregar elementos al panel de botones
	botonesPanel.add(iniciar);
	
	
	                  //contenedores secundarios
	ventana.add(botonesPanel,BorderLayout.SOUTH);
	ventana.add(ventanaJuego,BorderLayout.CENTER);     //agregar contenedores a la ventana principal
	
	
	
	
	//manejador de eventos para boton cargar************************************************************************
	cargar.addActionListener(new ActionListener()
	{
		
		public void actionPerformed(ActionEvent e)
		{
			laberinto.almacenar();
			laberinto.copiar();
			//matrizAuxiliar=matriz2.clone();
			int ejeX;
			int ejeY=50;
			int medida=18;
			for(int i=0;i<20;i++)
			{
				ejeX=80;      //inicializacion de coordenada x
				
				for(int j=0;j<20;j++)
				{
					
					if(matriz2[i][j]=="-2")
					{
						for(int c=0;c<4;c++)
						{laberinto.lineaRoja(ventanaJuego.getGraphics(), ejeX+5, ejeY+c, ejeX+medida-5,ejeY+c );} 
					}
					if(matriz2[i][j]=="-3")
					{
						int auxX=ejeX;
						int auxY=ejeY-5;
						for(int c=0;c<15;c++)
						{laberinto.lineaVerde(ventanaJuego.getGraphics(), ejeX, ejeY+c-5, ejeX+medida,ejeY+c-5 );} 
						for(int c=0;c<20;c++)                                                                                 //imagen de salida de laberinto
						{laberinto.lineaVerde(ventanaJuego.getGraphics(), auxX-10+c, auxY-c, auxX+medida+10-c,auxY-c );} 
					}
					
					if(matriz2[i][j]=="-1")
					{
						if(j==0)
						{
							for (int c=0;c<20;c++)
							{laberinto.dibujar(ventanaJuego.getGraphics(), 80-c, ejeY, 80-c,ejeY+medida );}  //columna 0
							
						}
						else if(j==19)
						{
							for(int c=0;c<20;c++)
								{laberinto.dibujar(ventanaJuego.getGraphics(), 404+c, ejeY, 404+c, ejeY+medida);} //columna 19
							}
						
						else
						{
								for(int c=0;c<20;c++)
									{laberinto.dibujar(ventanaJuego.getGraphics(), ejeX, ejeY+c, ejeX+medida,ejeY+c );} //resto de los renglones
								
							}
						
						if(j==19)
						{
							ejeY=ejeY+medida;
						}
						
					}
					if(j!=0)
					{ejeX=ejeX+medida;}
				}
					
			}
			
			
			 
		}
	}
			);
	//manejador de eventos para boton iniciar***********************************************************************
	iniciar.addActionListener(new ActionListener()
	{
		
	public void actionPerformed(ActionEvent e)
	{
	  validacion=true;                            //variable validacion =true para que entre al metodo paint
	  repaint();
	  
	}}		);
	
	//****************************************************************************************************************
	
	
	this.setSize(frameSize);
	this.setLocation(300, 20);              //Uvicacion de ventana
	this.setVisible(true);
	}
	
//Metodo paint ****************************************************___________________________________________��

	public  void paint(Graphics g)
	{
		super.paint(g);
		if(validacion==true)
		{	
		//laberinto.salida();
				int ejeX;
				int ejeY=50;
				int medida=18;
				
				while (a==true)
				{
					if(ciclo==0)
					{	
						for(int i=0;i<matriz2.length;i++)
						{
							for(int j=0;j<matriz2.length;j++)
							{
								if (matriz2[i][j]=="-2")
								{
									cord=i-1;
									cord2=j;
								}
							}
						}
					}
					else
					{
						cord=proxPasX;
						cord2=proxPasY;
					}
						if(matriz2[cord][cord2]=="0"||matriz2[cord][cord2]=="*")
						{
							matriz2[cord][cord2]="1";             //verificacion de campo vacio e insercion de caracter "1"
							if(matriz2[cord][cord2-1]=="0"||matriz2[cord][cord2-1]=="-3")
							{
								if(matriz2[cord][cord2-1]=="0")
									{proxPasX=cord;
									proxPasY=cord2-1;
									matriz2[proxPasX][proxPasY]="*";}
								if(matriz2[cord][cord2-1]=="-3")
								{
									a=false;
									
								}
							}
							 if(matriz2[cord+1][cord2]=="0"||matriz2[cord+1][cord2]=="-3")
							{
								if(matriz2[cord+1][cord2]=="0")
								 	{proxPasX=cord+1;
									proxPasY=cord2;
									matriz2[proxPasX][proxPasY]="*";}
								if(matriz2[cord+1][cord2]=="-3")
								{
									a=false;
									
								}
							}
						    if(matriz2[cord][cord2+1]=="0"||matriz2[cord][cord2+1]=="-3")
							{
								if(matriz2[cord][cord2+1]=="0")
						    	{proxPasX=cord;
									proxPasY=cord2+1;
									matriz2[proxPasX][proxPasY]="*";}
								if(matriz2[cord][cord2+1]=="-3")
								{
									a=false;
								
								}
							}
							if(matriz2[cord-1][cord2]=="0"||matriz2[cord-1][cord2]=="-3")
							{
								if(matriz2[cord-1][cord2]=="0")
									{proxPasX=cord-1;
									proxPasY=cord2;
									matriz2[proxPasX][proxPasY]="*";}
								if(matriz2[cord-1][cord2]=="-3")
								{
									a=false;
									
								}
							}
							
							ciclo++;
							
						}
						else
						{
							for(int i=0;i<20;i++)
							{
								for (int j=0;j<20;j++)
								{
									if(matriz2[i][j]=="*") //caso para cuando llega a una pared y no  tiene oreo camino
									{
										proxPasX=i;
										proxPasY=j;
										break;
									}
								}
							}
							
						}
						ejeY=80;
						for(int i=0;i<20;i++)
						{
							ejeX=88;      //inicializacion de coordenada x
							
							for(int j=0;j<20;j++)
							{
								if(matriz2[i][j]=="-1"||matriz2[i][j]=="1"||matriz2[i][j]=="*"||matriz2[i][j]=="-3")
								{
									if(matriz2[i][j]=="-1")	                  
									{	for(int c=0;c<20;c++)
											{
												//laberinto.lineaRoja(ventanaJuego.getGraphics(), ejeX-5, ejeY+c+8, ejeX-medida+5,ejeY+c+8);
												//laberinto.this.ventanaJuego.paint(null);
												//objeto.paint(getGraphics(),ejeX-5, ejeY+c+8, ejeX-medida+5,ejeY+c+8);
										        g.setColor(Color.black);
												g.drawLine(ejeX, ejeY+c, ejeX-medida,ejeY+c);
											} 
									}
									else if(matriz2[i][j]=="1")
									{
										for(int c=0;c<4;c++)
										{
									        g.setColor(Color.red);
											g.drawLine( ejeX-5, ejeY+c+8, ejeX-medida+5,ejeY+c+8);
										} 
										
									}
									else if(matriz2[i][j]=="*")
									{
										for(int c=0;c<4;c++)
										{
									        g.setColor(Color.blue);
											g.drawLine( ejeX-5, ejeY+c+8, ejeX-medida+5,ejeY+c+8);
										} 
									}
									else if(matriz2[i][j]=="-3")
									{
										int auxX=ejeX-18;
										int auxY=ejeY-5;
										for(int c=0;c<15;c++)
										{   g.setColor(Color.GREEN);
											g.drawLine( ejeX, ejeY+c-5, ejeX-medida,ejeY+c-5 );} 
										for(int c=0;c<20;c++)                                                    //imagen de salida de laberinto
										{
											g.setColor(Color.green);
											g.drawLine( auxX-10+c, auxY-c, auxX+medida+10-c,auxY-c );} 
									}
									
								}
								
								ejeX=ejeX+medida;
								if(j==19)
								{
									ejeY=ejeY+medida;
								}
							}
								
						}
						
						for (int in=0;in<1000000000;in++)
						{}/*Comentario*/	
						
						
						
			}		
					
					
					
				
	}			
	}
	public static void dibujar(Graphics g,int x, int y, int x1, int y1)
	{
		g.drawLine(x, y, x1, y1);
		                                                                   //linea de paredes del laberinto
	}
	public static void lineaRoja(Graphics g,int x, int y, int x1, int y1)
	{
		g.setColor(Color.red);
		g.drawLine(x, y, x1, y1);
		                                                                       //linea de inicio y pasdos del laberinto
	}
	public static void lineaVerde(Graphics g,int x, int y, int x1, int y1)
	{
		g.setColor(Color.green);
		g.drawLine(x, y, x1, y1);                                              //linea de salida de laberinto
		
	}
	
	public static void main(String[]args)
	{
		new laberinto();
		
		
	}
	
/*********************************Metodo que busca la salida del laberinto ********************************************************/
	
}
