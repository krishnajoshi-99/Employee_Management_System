
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.swing.*;

class UpdateFrame extends JFrame {
	Container c;
	JLabel eidLbl, enameLbl, esalLbl;
	JTextField eidTxt, enameTxt, esalTxt;
	JButton saveBtn, backBtn;

	UpdateFrame() {
		setContentPane(new JLabel(new ImageIcon("UPDATE.jpg")));		
		setBounds(550,180,400,470);
		c = getContentPane();
		c.setLayout(null);

		Font f = new Font("Arial", Font.BOLD, 20);
		Font f1 = new Font("Arial", Font.PLAIN, 20);

		eidLbl = new JLabel("Employee ID:");
		eidLbl.setBounds(120, 20, 200, 30);
		eidLbl.setFont(f);

		eidTxt = new JTextField(10);
		eidTxt.setBounds(40, 50, 308, 30);
		eidTxt.setFont(f1);

		enameLbl = new JLabel("Employee name:");
		enameLbl.setBounds(110, 100, 200, 30);
		enameLbl.setFont(f);

		enameTxt = new JTextField();
		enameTxt.setBounds(40, 130, 308, 30);
		enameTxt.setFont(f1);

		esalLbl = new JLabel("Employee salary:");
		esalLbl.setBounds(105, 180, 250, 30);
		esalLbl.setFont(f);

		esalTxt = new JTextField();
		esalTxt.setBounds(40, 210, 308, 30);
		esalTxt.setFont(f1);

		ImageIcon SaveImage = new ImageIcon(new ImageIcon("save.png").getImage().getScaledInstance(25, 20, Image.SCALE_DEFAULT));
		ImageIcon BackImage = new ImageIcon(new ImageIcon("back.png").getImage().getScaledInstance(25, 20, Image.SCALE_DEFAULT));

		saveBtn = new JButton("SAVE", SaveImage);
		saveBtn.setSize(150, 40);
		saveBtn.setLocation(110, 285);
		saveBtn.setFont(f);

		backBtn = new JButton("BACK", BackImage);
		backBtn.setSize(150, 40);
		backBtn.setLocation(110, 340);
		backBtn.setFont(f);
		
		backBtn.addActionListener(ae -> {MainFrame a=new MainFrame();	dispose();});

		saveBtn.addActionListener(ae -> {
		
			Configuration cfg= new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact = cfg.buildSessionFactory();
			Session session = sfact.openSession();
			Transaction t =null;
			try
			{
				System.out.println("begin");
	                	t= session.beginTransaction();
			        Employee e =new Employee();              
				if(eidTxt.getText().equals("")){
                                	JOptionPane.showMessageDialog(null,"Please Enter Employee id ","Insane warning",JOptionPane.WARNING_MESSAGE);
					eidTxt.requestFocus();
                            		return;							 
				}	
				int value= Integer.parseInt(eidTxt.getText()); 
				if(value<0 ){
					JOptionPane.showMessageDialog(null,"Id should be positive integer","Insane error",JOptionPane.ERROR_MESSAGE);
					eidTxt.setText("");
					eidTxt.requestFocus();
					return;
				}
				Employee s=(Employee)session.get(Employee.class,value);
				if(s!=null){
					String str=enameTxt.getText();
                        		if(str.equals("") || str.length()<2 || !str.matches("[a-zA-Z,]+")){ 
                        			JOptionPane.showMessageDialog(null,"Please Enter a valid name","Insane warning",JOptionPane.WARNING_MESSAGE);
                        			enameTxt.setText("");
                        			enameTxt.requestFocus();
                        			return;
					}
					else
						s.setEname(str);

					if(esalTxt.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Please Enter the salary","Inane warning",JOptionPane.WARNING_MESSAGE);
						esalTxt.requestFocus();
						return;
					}
					if(!esalTxt.getText().matches("[0-9]+")){
                                		JOptionPane.showMessageDialog(null,"Salary  should  only have Digits","Inane error",JOptionPane.ERROR_MESSAGE);
						esalTxt.setText("");
						esalTxt.requestFocus();
                             			return;							 
					}	
					double value1 = Double.parseDouble(esalTxt.getText()); 						 
					if(value1<8000){
						JOptionPane.showMessageDialog(null,"Salary cannot be less than 8000","Inane error",JOptionPane.ERROR_MESSAGE);
						esalTxt.setText("");
						esalTxt.requestFocus();
						return;
					}
					s.setEsal(value1);
					session.save(s);
					t.commit();
					JOptionPane.showMessageDialog(null,"Record Updated","A plain message",JOptionPane.PLAIN_MESSAGE);
            				System.out.println("\n \n \t \t \t \t \t record updated \t \t \t \t \t \n \n");
					System.out.println("end");
				}
				else{
					JOptionPane.showMessageDialog(null,"Record does not exist","Insane Error",JOptionPane.ERROR_MESSAGE);
						eidTxt.setText("");
			            		enameTxt.setText("");
						esalTxt.setText("");
				}						
			}
			catch(NumberFormatException e){
				t.rollback();
			 	JOptionPane.showMessageDialog(null," Employee Id should  only have Digits","Insane error",JOptionPane.ERROR_MESSAGE);
			 	eidTxt.setText("");
				eidTxt.requestFocus();
			}
        		catch(org.hibernate.exception.ConstraintViolationException eb){
				t.rollback();
				JOptionPane.showMessageDialog(null,"Record does not exist","A plain message",JOptionPane.PLAIN_MESSAGE);
				System.out.println("\n \n \t \t \t \t \t Record already exist \t \t \t \t \t \n \n");
				eidTxt.setText("");
				enameTxt.setText("");
				esalTxt.setText("");
			}
			catch(Exception ei){
				t.rollback();
				JOptionPane.showMessageDialog(null,ei);
			}
			finally{
				session.close();
			}
		});		

		c.add(eidLbl);
		c.add(eidTxt);
		c.add(enameLbl);
		c.add(enameTxt);
		c.add(esalLbl);
		c.add(esalTxt);
		c.add(saveBtn);
		c.add(backBtn);
		
		setSize(400, 470);
		setLocation(550, 180);
		setTitle("Update Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
