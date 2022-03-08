import java.awt.event.ActionEvent;
import java.awt.event.ActionListener ;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.*; 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

 
public class Gui extends JFrame implements ActionListener {
	
	JButton button = new JButton("Choose File") ; 
	JPanel panel = new JPanel() ; 
	JLabel label = new JLabel("The selected image") ; 
	JButton buttonx = new JButton("Compress") ;
	JPanel panelx = new JPanel() ; 
	JLabel labelx = new JLabel("The compressed image") ;
	
	JPanel dimensions = new JPanel () ; 
	
	JLabel size = new JLabel("Code Book size") ; 
	JLabel vectorDimensions = new JLabel ("vecotr dimensions ") ; 
	JLabel LabelHeight = new JLabel ("block height") ; 
	JTextField text1 = new JTextField () ; 
	JTextField text2 = new JTextField () ; 
	//JTextField text3 = new JTextField() ;
	BufferedImage image = null ; 
	public String source ; 
	int numberOfBlocks = 32 ; 
	int blockWidth = 2 ; 
	
	Gui ()
	{
		this.setVisible(true) ; 
		this.setSize(1280,720) ; 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (0xe6e6e6)) ;
		this.setTitle("Vector Gui") ; 
		button.addActionListener(this);
		this.setLayout(null) ; 
		this.setResizable(false) ; 
		this.add(button) ; 
		
		button.setBounds(250 , 520 , 200 , 100) ; 
		this.add(panel) ; 
		panel.setBounds(150,100,200*2,200*2) ; 
		panel.setBackground(new Color(0xcccccc));
		panel.add(label) ; 
		
		this.add(buttonx) ; 
		buttonx.setBounds(850 , 520 , 200 , 100 ) ;
		buttonx.addActionListener(this);
		this.add(panelx) ; 
		panelx.setBounds(750,100,200*2,200*2) ; 
		panelx.setBackground(new Color(0xcccccc));
		panelx.add(labelx) ;
		
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		
		labelx.setHorizontalTextPosition(JLabel.CENTER); //this
		labelx.setVerticalTextPosition(JLabel.TOP);
		this.add(dimensions) ; 
		dimensions.setBounds(600 , 500 , 150, 200) ; 
		dimensions.setBackground(new Color (0xe6e6e6));
		dimensions.add(size) ; 
		
		//text1.setBounds(120,50,100,20) ; 
		dimensions.add(text1) ; 
		text1.setPreferredSize(new Dimension(100,20));
		dimensions.add(vectorDimensions) ; 
		
		text2.setPreferredSize(new Dimension (100, 20 ));
		
		dimensions.add(text2) ; 
		
		
//		text3.setBounds(480,50,100,20) ; 
//		this.add(text3) ;  
		} //this
		
		
		
		

		
		
		
		
		
	
	
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource() == button)
	{
		//System.out.println("run");
		//panel.getGraphics().clearRect(0,0 , panel.getWidth(), panel.getHeight()) ; 
		JFileChooser chooser = new JFileChooser() ; //this 
		int response = chooser.showOpenDialog(null) ;  //this
		if (response == 0 )
		{
			File file = new File (chooser.getSelectedFile().getAbsolutePath()) ; 
			String s = file.getAbsolutePath()  ;
			source = s ; 
			
			try
			{
				image = ImageIO.read(new File (s)) ; 
				Image image1 = image.getScaledInstance(panel.getWidth() , panel.getHeight() , Image.SCALE_SMOOTH); 
				ImageIcon format = new ImageIcon(image1) ; 
				label.setIcon(format);
			}
			catch(Exception r ) 
			{
				
			}
	
		}
	}
	if (e.getSource() == buttonx)
	{
		try {
		String str ; 
		str = text1.getText();
		numberOfBlocks = Integer.valueOf(str) ; 
		str = text2.getText(); 
		blockWidth = Integer.valueOf(str) ; 
//		str = text3.getText() ; 
//		Integer blockHeight = Integer.valueOf(str) ;
		}
		catch (Exception r ) 
		{
			System.out.println("it was set by default !! ");
		}
		vector_quantization vecq = new vector_quantization(blockWidth, numberOfBlocks);
        vecq.compress(source, ".\\output.txt");
        vecq.img.toimage("gray.jpg");
        vecq.decompress(".\\output.txt", "result.png");
        ImageIcon icon = new ImageIcon ("result.png") ; 
        try
		{
			image = ImageIO.read(new File ("result.png")) ; 
			Image image1 = image.getScaledInstance(panelx.getWidth() , panelx.getHeight() , Image.SCALE_SMOOTH); 
			ImageIcon format = new ImageIcon(image1) ; 
			labelx.setIcon(format);
		}
		catch(Exception r ) 
		{
			
		}
        
	}
}}
	
