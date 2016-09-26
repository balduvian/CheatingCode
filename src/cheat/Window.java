package cheat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Window extends JFrame{
	
	Canvas canvas;
	String text;
	JTextField inp = new JTextField();
	Image[] glyphs = new Image[5];
	/*
		0: ^
		1: >
		2: V
		3: <
		4: -
	*/
	int[][] codes = {{},{0},{1,1},{3},{1},{3,3},{4,3},{0,4},{4,4},{4},{4,1},{3,4},{4},{0,0},{0},{0,2},{1,4},{0,4},{1,4},{3,1},{2,4},{2},{2},{2,2},{2,0},{2,4},{1,3}};
	String chrs = " abcdefghijklmnopqrstuvwxyz";
	
	public static void main(String[] args) {
		Window w = new Window();
		while(true){
			w.text = w.inp.getText();
			//w.text = w.text.toLowerCase(); //GIVES ME ERRORS
			w.canvas.repaint();
		}
	}
	
	public class Canvas extends JPanel{
		public void paint(Graphics g){
			super.paint(g);
			setBackground(Color.WHITE);
			int vs = 32;
			int hs = 32;
			int w = getWidth()/hs;
			for(int x=0;x<text.length();x++){
				int c = posIn(text.charAt(x),chrs);
				for(int i=0;i<codes[c].length;i++){
					g.drawImage(glyphs[codes[c][i]],(x%w)*hs,i*vs+Math.round(x/w)*(int)(vs*2),this);
				}
			}
		}
	}
	
	public int posIn(Character c,String s){
		int v=0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)==c){
				v=i;
				break;
			}
		}
		return v;
	}
	
	public Window(){
		canvas = new Canvas();
		try {glyphs[0] = ImageIO.read(getClass().getResource("g0.png"));} catch (IOException e) {}
		try {glyphs[1] = ImageIO.read(getClass().getResource("g1.png"));} catch (IOException e) {}
		try {glyphs[2] = ImageIO.read(getClass().getResource("g2.png"));} catch (IOException e) {}
		try {glyphs[3] = ImageIO.read(getClass().getResource("g3.png"));} catch (IOException e) {}
		try {glyphs[4] = ImageIO.read(getClass().getResource("g4.png"));} catch (IOException e) {}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640,480);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(inp,BorderLayout.NORTH);
		cp.add(canvas,BorderLayout.CENTER);
		setVisible(true);
	}
	
}
