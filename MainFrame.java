
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
	Container c;
	JButton AddBtn,DeleteBtn,UpdateBtn,ViewBtn,SearchBtn;

	MainFrame(){
		c=getContentPane();
		getContentPane().setBackground(Color.LIGHT_GRAY);
		c.setLayout(null);

		Font f=new Font("Arial",Font.BOLD,20);

		AddBtn=new JButton("ADD");
		AddBtn.setSize(200,40);
		AddBtn.setLocation(135,35);
		AddBtn.setFont(f);

		ViewBtn=new JButton("VIEW");
		ViewBtn.setSize(200,40);
		ViewBtn.setLocation(135,100);
		ViewBtn.setFont(f);
		
		UpdateBtn=new JButton("UPDATE");
		UpdateBtn.setSize(200,40);
		UpdateBtn.setLocation(135,165);
		UpdateBtn.setFont(f);

		SearchBtn=new JButton("SEARCH");
		SearchBtn.setSize(200,40);
		SearchBtn.setLocation(135,230);
		SearchBtn.setFont(f);
		
		DeleteBtn=new JButton("DELETE");
		DeleteBtn.setSize(200,40);
		DeleteBtn.setLocation(135,295);
		DeleteBtn.setFont(f);

		c.add(AddBtn);
		c.add(ViewBtn);
		c.add(UpdateBtn);
		c.add(SearchBtn);
		c.add(DeleteBtn);		

		AddBtn.addActionListener   (ae -> {AddFrame a=new AddFrame();       	dispose();});
		ViewBtn.addActionListener  (ae -> {ViewFrame a=new ViewFrame();     	dispose();});
		UpdateBtn.addActionListener(ae -> {UpdateFrame a=new UpdateFrame(); 	dispose();});
		DeleteBtn.addActionListener(ae -> {DeleteFrame a=new DeleteFrame(); 	dispose();});
		SearchBtn.addActionListener(ae -> {SearchFrame a=new SearchFrame(); 	dispose();});
		
		setSize(500,420);
		setLocation(550,180);
		setTitle("Employee Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String args[]){
		MainFrame mf=new MainFrame();		
	}
}
