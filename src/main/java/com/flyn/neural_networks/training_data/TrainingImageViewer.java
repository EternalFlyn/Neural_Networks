package com.flyn.neural_networks.training_data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrainingImageViewer extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 886635393882701079L;
	private int imageNumber = 0;
	private JFrame frame = new JFrame("Training Image");
	private TrainingImage image;
	
	public TrainingImageViewer(TrainingImage image) {
		this.image = image;
		frame.addKeyListener(this);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setContentPane(this);
	}
	
	public void show() {
		frame.setVisible(true);
		frame.setSize(image.rows * 20 + frame.getInsets().left + frame.getInsets().right, image.columns * 20 + frame.getInsets().top + frame.getInsets().bottom);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		BufferedImage img = image.data[imageNumber];
		g.drawImage(img, 0, 0, image.rows * 20, image.columns * 20, null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_LEFT:
			if(imageNumber > 0) {
				imageNumber--;
				repaint();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(imageNumber < image.itemAmount) {
				imageNumber++;
				repaint();
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
