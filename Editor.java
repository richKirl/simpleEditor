import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

class Editor extends JFrame implements ActionListener {

    // Text component
    private JTextPane t;                                     //code

    private JTextPane t1;                                    //lineNumbers

    private JTextPane t2;                                    //mini cli

    private JTextPane t3;                                    //top linesearch

    private JTextPane t4;                                    //bottom

    private JTextPane t5;                                    //top Jumper

    // Frame
    private JFrame f;                                        //frame-likewWindow

    private String prompter=" \n % ";                        //prompt

    String commander;                                        //command after prompt

    private int LineNN=1;                                    //number of lines on doc

    //Style
    private Color tTextWC;                                   //new Color(0,0,0,255);BackGround

    private Color tTextWCF;                                  //new Color(200,200,200,255);ForeGround

    private Color tBlue;                                     //new Color(23,159,241);

    private Color tBody;                                     //new Color(206,145,120);

    private Color tMagenta;                                  //new Color(218,112,214);

    private Color tLBlue;                                    //new Color(156,220,254);

    private Color tYellow;                                   //new Color(255,201,20);

    private Color tGreen;                                    //new Color(25,201,20);

    private Color tConsoleTextError;                         //new Color(255,101,100);

    private String Path;                                     //Path for widget Pather

    private String FilePathw;                                //nameFile with res

    private String FileExt;                                  //nameFile

    private String ExtFile;                                  //.{}

    private JTextPane Pather;                                //buffer for pathViewer

    private CmrViewer testls;                                //bufferViewerDirectory


    private String[] langSupport = {"C","C++","Java"};

    private static final int TAB_SIZE = 2;


    JMenuBar mbConsole;

    private int mbConsoleSizeH = 200;

    private int VisualConsole = 0;


    JMenuBar mbViewer;

    private int VisualViewer = 0;

    private int mbViewerSizeW = 200;


    JScrollPane panelScrollText;


    private short CountFORFREE = 0;


    JMenuBar mbSearcher;

    private int VisualSearcher = 0;

    JMenuBar mbJumper;

    private int VisualJumper = 0;

    JMenuBar mbInfoBar;

    private int VisualInfoBar = 0;

    int CaretPosSave;

    private List<Integer> tempSearch;

    private int StartTempSearch=0;

    JPanel panel1;

    // Constructor
    public Editor() {

	// Create a frame
	JFrame.setDefaultLookAndFeelDecorated( true );

	JDialog.setDefaultLookAndFeelDecorated( true );


	f = new JFrame("editor");

       tempSearch = new ArrayList<Integer>();

	try {

	    // Set metal look and feel
	    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

	    // Set theme to ocean
	    MetalLookAndFeel.setCurrentTheme(new OceanTheme());//set theme read in doc

	}
	catch (Exception e) {
	}


	//config jtextarea for numberLines

	t1 = new JTextPane();

	t1.setFont(new Font("monospaced", Font.PLAIN, 16));

	t1.setLocation(100,0);

	t1.setSize(20,300);

	t = new JTextPane();


	t.setFont(new Font("monospaced", Font.PLAIN, 16));

	t.setCaretPosition(t.getDocument().getLength());//set cursor position in doc

	t.addKeyListener(new CounterLines());//connect listen keyboard


       // Create a menubar
       JMenuBar mb = new JMenuBar();//menubar top

       // Create amenu for menu
       JMenu m1 = new JMenu("File");//menu for file

       // Create menu items
       JMenuItem mi1 = new JMenuItem("New");//item for file

       JMenuItem mi2 = new JMenuItem("Open");//item for file

       JMenuItem mi3 = new JMenuItem("Save");//item for file

       JMenuItem mi9 = new JMenuItem("Print");//item for file

       // Add action listener
       mi1.addActionListener(this);//connect event

       mi2.addActionListener(this);

       mi3.addActionListener(this);

       mi9.addActionListener(this);

       m1.add(mi1);//connect to File

       m1.add(mi2);

       m1.add(mi3);

       m1.add(mi9);
       //EndCreateMenuBar



	// Create amenu for menu
	JMenu m2 = new JMenu("Edit");//menu for Edit

	// Create menu items
	JMenuItem mi4 = new JMenuItem("cut");//item for edit

	JMenuItem mi5 = new JMenuItem("copy");//item for edit

	JMenuItem mi6 = new JMenuItem("paste");//item for edit

	// Add action listener
	mi4.addActionListener(this);//connect event

	mi5.addActionListener(this);

	mi6.addActionListener(this);

	m2.add(mi4);//connect to Edit

	m2.add(mi5);

       	m2.add(mi6);



	JMenu mc = new JMenu("Close");//menu button for close instant

	JMenu col = new JMenu("ColorText");//menu button for close instant

	mc.addMouseListener(new ExitAction());//connect event

	col.addMouseListener(new ColorStyle());

	mb.add(m1);//connect all menu to menubar-top

	mb.add(m2);

	mb.add(mc);

	mb.add(col);


	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit on button close X




	mbConsole = new JMenuBar();//create menubar-bottom for cli

	//create jtextarea with a1 a2 a3
	t2 = new JTextPane();

	t2.setLocation(120,0);

	t2.setSize(380,200);

	t2.setFont(new Font("monospaced", Font.PLAIN, 16));

	t2.setPreferredSize(new Dimension(380,200));

	t2.addKeyListener(new CmrPrompter());//connect keyevent



       t3 = new JTextPane(); 

       t3.setPreferredSize(new Dimension(600,30));

       t4 = new JTextPane(); 

       t4.setPreferredSize(new Dimension(600,30));	

       t5 = new JTextPane(); 

       t5.setPreferredSize(new Dimension(600,30));	


	mbConsole.add(new JScrollPane(t2));//connect jtextarea-cli to bottom menubar

	mbConsole.setLocation(0,200);

	mbConsole.setPreferredSize(new Dimension(500,mbConsoleSizeH));

	mbConsole.setSize(500,mbConsoleSizeH);



	mbViewer = new JMenuBar();

	Pather = new JTextPane();

	Pather.setLocation(0,0);

	Pather.setSize(100,300);

	Pather.setFont(new Font("monospaced",Font.PLAIN,16));

	Pather.setPreferredSize(new Dimension(200,300));

	mbViewer.add(new JScrollPane(Pather));

	mbViewer.setLocation(0,0);

	mbViewer.setPreferredSize(new Dimension(200,300));


	//collors for all jtextareas t=code t1=numbers t2=cli
	tTextWC = new Color(0,0,0,255);

	tTextWCF = new Color(200,200,200,255);

	t.setBackground(tTextWC);

	t.setForeground(tTextWCF);



	JMenuBar mFunctionality = new JMenuBar();//menubar top


	Color c1 = new Color(50,50,50,255);

	Color cf1 = new Color(200,200,200,255);

	t1.setBackground(c1);

	t1.setForeground(cf1);




	Color c2 = new Color(0,0,0,255);

	Color cf2 = new Color(200,200,200,255);

	t2.setBackground(c2);

	t2.setForeground(cf2);


       mbSearcher = new JMenuBar();

       t3.setFont(new Font("monospaced", Font.PLAIN, 16));

       t3.setBackground(c2);

       t3.setForeground(cf2);

       t3.setText("SearchL: ");

       mbSearcher.add(t3);

       t3.addKeyListener(new SearchPrompter());//connect keyevent



       mbJumper = new JMenuBar();

       t5.setFont(new Font("monospaced", Font.PLAIN, 16));

       t5.setBackground(c2);

       t5.setForeground(cf2);

       t5.setText("SearchJ: ");

       mbJumper.add(t5);

       t5.addKeyListener(new jumpPrompter());//connect keyevent



       mbInfoBar = new JMenuBar();

       t4.setFont(new Font("monospaced", Font.PLAIN, 16));

       t4.setBackground(c2);

       t4.setForeground(cf2);

       t4.setText("Info: ");

       mbInfoBar.add(t4);


       Pather.setBackground(new Color(0,0,0,255));

	Pather.setForeground(new Color(200,200,200,255));

	//ColorINIT
	tBlue=new Color(23,159,241);

	tBody=new Color(206,145,120);

	tMagenta=new Color(218,112,214);

	tLBlue=new Color(156,220,254);

	tYellow=new Color(255,201,20);

	tGreen = new Color(25,201,20);

	tConsoleTextError= new Color(255,101,100);
	//COLORENDINIT



	JPanel panel = new JPanel();//create panel

	panel.setLayout(new BorderLayout());//create template for complians


       panel1 = new JPanel();

       JPanel panel2 = new JPanel();

       panel1.setLayout(new BorderLayout());

       panel2.setLayout(new BorderLayout());

       //left because numbers
       panel1.add(mbSearcher, BorderLayout.NORTH);

       panel2.add(mbJumper, BorderLayout.NORTH);//left because numbers

       panel1.add(mbInfoBar, BorderLayout.SOUTH);//left because numbers

	panel.add(t1, BorderLayout.WEST);//left because numbers

	panel.add(t, BorderLayout.CENTER);//right extend to center


	panelScrollText = new JScrollPane(panel);

	panelScrollText.setRowHeaderView(t1);

       panelScrollText.getVerticalScrollBar().setUnitIncrement(30);

       panel1.add(panelScrollText,BorderLayout.CENTER);

       panel2.add(panel1,BorderLayout.CENTER);


       f.add(mb,BorderLayout.NORTH);//menubar to up

	f.add(mbViewer,BorderLayout.WEST);

	f.add(mbConsole,BorderLayout.SOUTH);//set scroll to cli and set bottom position

	f.add(panel2);//connect all panel to frame



	f.setSize(1200, 900);//set size wxh

	centreWindow(f);//set center position

	f.setVisible(true);//show()

	t.requestFocus();//fix some situation with current pos and doc

	t.setCaretPosition(t.getText().length());//fix some situation with cursor

	t.setCaretColor(Color.WHITE);

	t2.setCaretColor(Color.WHITE);

	appendToPane(t1,""+LineNN+"\n",tTextWCF);

	appendToPane(t2,"\n"+" % ",tTextWCF);

	testls = new CmrViewer();//connect keyevent

    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {//actions for items

        String s = e.getActionCommand();

        if (s.equals("cut")) {

            cutFunction();

        }
        else if (s.equals("copy")) {

            copyFunction();

        }
        else if (s.equals("paste")) {

            pasteFunction();

	    setCounterL(t.getText());

        }
        else if (s.equals("Save")) {

	    SaveFunction();

        }

        else if (s.equals("Print")) {

	    printFile();

        }
        else if (s.equals("Open")) {

	    OpenFile();

        }
        else if (s.equals("New")) {

          NewFunction();

        }
        else if (s.equals("Close")) {

        }

	repaint();//

	revalidate();

    }


    public void SaveFunction() {

	if(FilePathw!=null){

	    try {

		// Create a file writer
		FileWriter wr = new FileWriter(FilePathw, false);

		// Create buffered writer to write
		BufferedWriter w = new BufferedWriter(wr);

		// Write
		w.write(t.getText());

		w.flush();

		w.close();

	    }
	    catch (Exception evt) {

		JOptionPane.showMessageDialog(f, evt.getMessage());

	    }


	}
	else if(FilePathw==null){

	    // Create an object of JFileChooser class
	    JFileChooser j = new JFileChooser("f:");

	    // Invoke the showsSaveDialog function to show the save dialog
	    int r = j.showSaveDialog(null);

	    if (r == JFileChooser.APPROVE_OPTION) {


		// Set the label to the path of the selected directory
		File fi = new File(j.getSelectedFile().getAbsolutePath());

		try {

		    // Create a file writer
		    FileWriter wr = new FileWriter(fi, false);

		    // Create buffered writer to write
		    BufferedWriter w = new BufferedWriter(wr);

		    // Write
		    w.write(t.getText());

		    w.flush();

		    w.close();

		}
		catch (Exception evt) {

		    JOptionPane.showMessageDialog(f, evt.getMessage());

		}
	    }
	}
	// If the user cancelled the operation
	else
	    JOptionPane.showMessageDialog(f, "the user cancelled the operation");


	System.gc();

    }


    public void OpenFile() {

	// Create an object of JFileChooser class
	JFileChooser j = new JFileChooser("f:");

	// Invoke the showsOpenDialog function to show the save dialog
	int r = j.showOpenDialog(null);

	// If the user selects a file
	if (r == JFileChooser.APPROVE_OPTION) {

	    // Set the label to the path of the selected directory
	    File fi = new File(j.getSelectedFile().getAbsolutePath());


	    try {

		// String
		String s1 = "", sl = "";

		// File reader
		FileReader fr = new FileReader(fi);

		// Buffered reader
		BufferedReader br = new BufferedReader(fr);

		FilePathw=fi.toString();

		String partsDirs[] = FilePathw.split("/");

		int sz1 = partsDirs.length;

		FileExt = partsDirs[sz1-1];

              String partsDim[] = FileExt.split("\\W");

              int sz2 = partsDim.length;

              ExtFile = partsDim[sz2-1];

              if(ExtFile.equals("java"))ExtFile=langSupport[2];

              else if(ExtFile.equals("cpp"))ExtFile=langSupport[1];

              else if(ExtFile.equals("c"))ExtFile=langSupport[0];

		String tt=new String();

		for(int i=1;i<partsDirs.length-1;i++) {

		    tt+="/"+partsDirs[i];

		}

		tt+="/";

		Path=tt;

		testls.viewDir(Path);

		// Initialize sl
		sl = br.readLine();

		// Take the input from the file
		while ((s1 = br.readLine()) != null) {

		    sl = sl + "\n" + s1;

		    LineNN+=1;//set linenumbers while if line

		    appendToPane(t1,""+LineNN+"\n",tTextWCF);

		}

		// Set the text
		t.setText(sl);

		System.gc();

	    }
	    catch (Exception evt) {

		JOptionPane.showMessageDialog(f, evt.getMessage());

	    }

	}

	// If the user cancelled the operation
	else
	    JOptionPane.showMessageDialog(f, "the user cancelled the operation");

    }


      public void NewFunction() {

	    String tempS=new String();

	    tempS=t.getText();

	    if(tempS!=null){

		SaveFunction();

		Pather.setText("");

		t.setText("");

		t1.setText("");

		LineNN=1;

		appendToPane(t1,""+LineNN+"\n",tTextWCF);

		System.gc();

	    }
	    else {

		t.setText("");

	    }

           System.gc();

      }



    public void printFile() {

	try {

	    // print the file
	    t.print();

	}
	catch (Exception evt) {

	    JOptionPane.showMessageDialog(f, evt.getMessage());
	}

    }


    public void cutFunction() {

	t.cut();

    }

    public void copyFunction() {

	t.copy();

    }

    public void pasteFunction() {

	t.paste();

    }

    //exit after clicked on Close Menu!=item
    class ColorStyle extends MouseInputAdapter {

	public void mouseClicked(MouseEvent mouseEvent) {

         if(ExtFile.equals("cpp")) {

	    String s1 = t.getText();//

	    t.setText("");//(?<=\s)(?=\S)|(?<=\S)(?=\s)|(?<=\w)(?=\W)|(?<=\W)(?=\w)

	    String parts[] = s1.split("(?U)(?<=\\s)(?=\\S)|(?<=\\S)(?=\\s)|(?<=\\w)(?=\\W)|(?<=\\W)");//"((?= )|(?=\t)|(?<=\n))"(?=\\w)

	    List<String> list = new ArrayList<String>();

	    for(String r: parts)list.add(r);

	    final Iterator<String> it = list.iterator();

	    for(String next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

		String previous = current;

		current = next;

		next = it.hasNext() ? it.next() : null;

		if(current.equals("#")&&next.equals("include")){               appendToPane(t,current,tMagenta);}

		else if(current.equals("include")&&previous.equals("#")){      appendToPane(t,current,tMagenta);}

		else if(current.equals("<")){                                  appendToPane(t,current,tBody);}

		else if(previous.equals("<")&&current.equals("std")==false){   appendToPane(t,current,tBody);}

		else if(current.equals(">")){                                  appendToPane(t,current,tBody);}

		else if(current.equals("return"))                              appendToPane(t,current,tMagenta);

		else if(current.equals("int")){                                appendToPane(t,current,tBlue);}

		else if(current.equals("char")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("wchar")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("double")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("unsigned")){                           appendToPane(t,current,tBlue);}

		else if(current.equals("long")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("uint8_t")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("uint16_t")){                           appendToPane(t,current,tBlue);}

		else if(current.equals("uint32_t")){                           appendToPane(t,current,tBlue);}

		else if(current.equals("int8_t")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("int16_t")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("int32_t")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("class")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("struct")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("union")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("using")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("import")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("namespace")){                          appendToPane(t,current,tBlue);}

		else if(current.equals("static")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("constexpr")){                          appendToPane(t,current,tBlue);}

		else if(current.equals("const")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("protected")){                          appendToPane(t,current,tBlue);}

		else if(current.equals("public")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("private")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("void")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("std")&&next.equals(":")){              appendToPane(t,current,tMagenta);}

		else if(current.equals("new")){                                appendToPane(t,current,tBlue);}

		else if(current.equals("delete")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("do")){                                 appendToPane(t,current,tMagenta);}

		else if(current.equals("for")){                                appendToPane(t,current,tBlue);}

		else if(current.equals("while")){                              appendToPane(t,current,tMagenta);}

		else if(current.equals("if")){                                 appendToPane(t,current,tMagenta);}

		else if(current.equals("else")){                               appendToPane(t,current,tMagenta);}

		else if(current.equals("try")){                                appendToPane(t,current,tMagenta);}

		else if(current.equals("catch")){                              appendToPane(t,current,tMagenta);}

		else if(previous.equals(":")&&!current.equals(":")){           appendToPane(t,current,tBlue);}

		else if(current.equals("/")&&next.equals("/")){

                    appendToPane(t,current,tGreen);

                    appendToPane(t,next,tGreen);

                    for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

                      previous = current;

                      current = next;

                      next = it.hasNext() ? it.next() : null;

                      if(next.equals("\n")){ appendToPane(t,current,tGreen);break; }

                      else appendToPane(t,current,tGreen);

                    }

              }

		else if(current.equals("(")){                                  appendToPane(t,current,tBlue);}

		else if(current.equals(")")){                                  appendToPane(t,current,tBlue);}

		else if(current.equals("{")){                                  appendToPane(t,current,tMagenta);}

		else if(current.equals("}")){                                  appendToPane(t,current,tMagenta);}

		else if(current.equals("main")){                               appendToPane(t,current,tBlue);}

              	else if(current.equals("\"")){

                    if(current.equals("\"")&&next.equals("\"")){

                      appendToPane(t,current,tBody);

                        previous = current;

                        current = next;

                        next = it.hasNext() ? it.next() : null;

                      appendToPane(t,current,tBody);

                    }
                    else {

                      appendToPane(t,current,tBody);

                      appendToPane(t,next,tBody);

                      for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

                        previous = current;

                        current = next;

                        next = it.hasNext() ? it.next() : null;

                        if(current.equals("\"")){ if(next.equals("\"")){ appendToPane(t,current,tBody); previous = current;current = next;next = it.hasNext() ? it.next() : null;appendToPane(t,current,tBody); } else { appendToPane(t,current,tBody); } break; }

                        else appendToPane(t,current,tBody);

                      }

                    }

              }

		else appendToPane(t,current,tTextWCF);


		// Do something using 'current', 'previous' and 'next'.
		// NB: 'previous' and/or 'next' are null when 'current' is
		// the first and/or last element respectively
	    }

            list.clear();

	    }

           else if(ExtFile.equals("c")) {

	    String s1 = t.getText();//

	    t.setText("");//(?<=\s)(?=\S)|(?<=\S)(?=\s)|(?<=\w)(?=\W)|(?<=\W)(?=\w)

	    String parts[] = s1.split("(?U)(?<=\\s)(?=\\S)|(?<=\\S)(?=\\s)|(?<=\\w)(?=\\W)|(?<=\\W)");//"((?= )|(?=\t)|(?<=\n))"(?=\\w)

	    List<String> list = new ArrayList<String>();

	    for(String r: parts)list.add(r);

	    final Iterator<String> it = list.iterator();

	    for(String next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

		String previous = current;

		current = next;

		next = it.hasNext() ? it.next() : null;

		if(current.equals("#")&&next.equals("include")){               appendToPane(t,current,tMagenta);}

		else if(current.equals("include")&&previous.equals("#")){      appendToPane(t,current,tMagenta);}

		else if(current.equals("<")){                                  appendToPane(t,current,tBody);}

		else if(previous.equals("<")&&current.equals("std")==false){   appendToPane(t,current,tBody);}

		else if(current.equals(">")){                                  appendToPane(t,current,tBody);}

		else if(current.equals("return"))                              appendToPane(t,current,tMagenta);

		else if(current.equals("int")){                                appendToPane(t,current,tBlue);}

		else if(current.equals("char")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("wchar")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("double")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("unsigned")){                           appendToPane(t,current,tBlue);}

		else if(current.equals("long")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("uint8_t")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("uint16_t")){                           appendToPane(t,current,tBlue);}

		else if(current.equals("uint32_t")){                           appendToPane(t,current,tBlue);}

		else if(current.equals("int8_t")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("int16_t")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("int32_t")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("class")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("struct")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("union")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("using")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("import")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("static")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("constexpr")){                          appendToPane(t,current,tBlue);}

		else if(current.equals("const")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("protected")){                          appendToPane(t,current,tBlue);}

		else if(current.equals("public")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("private")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("void")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("do")){                                 appendToPane(t,current,tMagenta);}

		else if(current.equals("for")){                                appendToPane(t,current,tBlue);}

		else if(current.equals("while")){                              appendToPane(t,current,tMagenta);}

		else if(current.equals("if")){                                 appendToPane(t,current,tMagenta);}

		else if(current.equals("else")){                               appendToPane(t,current,tMagenta);}

		else if(current.equals("try")){                                appendToPane(t,current,tMagenta);}

		else if(current.equals("catch")){                              appendToPane(t,current,tMagenta);}

		else if(current.equals("/")&&next.equals("/")){

                    appendToPane(t,current,tGreen);

                    appendToPane(t,next,tGreen);

                    for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

                      previous = current;

                      current = next;

                      next = it.hasNext() ? it.next() : null;

                      if(next.equals("\n")){ appendToPane(t,current,tGreen); break; }

                      else appendToPane(t,current,tGreen);

                    }

              }

		else if(current.equals("(")){                                  appendToPane(t,current,tBlue);}

		else if(current.equals(")")){                                  appendToPane(t,current,tBlue);}

		else if(current.equals("{")){                                  appendToPane(t,current,tMagenta);}

		else if(current.equals("}")){                                  appendToPane(t,current,tMagenta);}

		else if(current.equals("main")){                               appendToPane(t,current,tBlue);}

              	else if(current.equals("\"")){

                    if(current.equals("\"")&&next.equals("\"")){

                      appendToPane(t,current,tBody);

                        previous = current;

                        current = next;

                        next = it.hasNext() ? it.next() : null;

                      appendToPane(t,current,tBody);

                    }
                    else {

                      appendToPane(t,current,tBody);

                      appendToPane(t,next,tBody);

                      for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

                        previous = current;

                        current = next;

                        next = it.hasNext() ? it.next() : null;

                        if(current.equals("\"")){ if(next.equals("\"")) { appendToPane(t,current,tBody); previous = current; current = next; next = it.hasNext() ? it.next() : null; appendToPane(t,current,tBody); } else { appendToPane(t,current,tBody); } break; }

                        else appendToPane(t,current,tBody);

                      }

                    }

              }

		else appendToPane(t,current,tTextWCF);


		// Do something using 'current', 'previous' and 'next'.
		// NB: 'previous' and/or 'next' are null when 'current' is
		// the first and/or last element respectively
	    }

            list.clear();


           }
           else if(ExtFile.equals("Java")) {

	    String s1 = t.getText();//

	    t.setText("");//(?<=\s)(?=\S)|(?<=\S)(?=\s)|(?<=\w)(?=\W)|(?<=\W)(?=\w)

	    String parts[] = s1.split("(?U)(?<=\\s)(?=\\S)|(?<=\\S)(?=\\s)|(?<=\\w)(?=\\W)|(?<=\\W)");//"((?= )|(?=\t)|(?<=\n))"(?=\\w)

	    List<String> list = new ArrayList<String>();

	    for(String r: parts)list.add(r);

	    final Iterator<String> it = list.iterator();

	    for(String next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

		String previous = current;

		current = next;

		next = it.hasNext() ? it.next() : null;

		if(current.equals("import")){                                  appendToPane(t,current,tBlue);}

		else if(current.equals("return"))                              appendToPane(t,current,tMagenta);

		else if(current.equals("int")){                                appendToPane(t,current,tBlue);}

		else if(current.equals("char")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("double")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("unsigned")){                           appendToPane(t,current,tBlue);}

		else if(current.equals("long")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("class")){                              appendToPane(t,current,tBlue);}

		else if(current.equals("static")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("protected")){                          appendToPane(t,current,tBlue);}

		else if(current.equals("public")){                             appendToPane(t,current,tBlue);}

		else if(current.equals("private")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("void")){                               appendToPane(t,current,tBlue);}

		else if(current.equals("new")){                                appendToPane(t,current,tBlue);}

              else if(current.equals("break")){                               appendToPane(t,current,tBlue);}

              else if(current.equals("continue")){                            appendToPane(t,current,tMagenta);}

		else if(current.equals("do")){                                 appendToPane(t,current,tMagenta);}

		else if(current.equals("for")){                                appendToPane(t,current,tBlue);}

		else if(current.equals("while")){                              appendToPane(t,current,tMagenta);}

		else if(current.equals("if")){                                 appendToPane(t,current,tMagenta);}

		else if(current.equals("else")){                               appendToPane(t,current,tMagenta);}

		else if(current.equals("try")){                                appendToPane(t,current,tMagenta);}

		else if(current.equals("catch")){                              appendToPane(t,current,tMagenta);}

		else if(current.equals("extends")){                            appendToPane(t,current,tBlue);}

		else if(current.equals("implements")){                         appendToPane(t,current,tBlue);}

		else if(current.equals("/")&&next.equals("/")){

                    appendToPane(t,current,tGreen);

                    appendToPane(t,next,tGreen);

                    for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

                      previous = current;

                      current = next;

                      next = it.hasNext() ? it.next() : null;

                      if(next.equals("\n")){ appendToPane(t,current,tGreen); break; }

                      else appendToPane(t,current,tGreen);

                    }

              }


		else if(current.equals("(")){                                  appendToPane(t,current,tBlue);}

		else if(current.equals(")")){                                  appendToPane(t,current,tBlue);}

		else if(current.equals("{")){                                  appendToPane(t,current,tMagenta);}

		else if(current.equals("}")){                                  appendToPane(t,current,tMagenta);}

		else if(current.equals("main")){                               appendToPane(t,current,tBlue);}

              	else if(current.equals("\"")){

                    if(current.equals("\"")&&next.equals("\"")){

                      appendToPane(t,current,tBody);

                        previous = current;

                        current = next;

                        next = it.hasNext() ? it.next() : null;

                      appendToPane(t,current,tBody);

                    }
                    else {

                      appendToPane(t,current,tBody);

                      appendToPane(t,next,tBody);

                      for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

                        previous = current;

                        current = next;

                        next = it.hasNext() ? it.next() : null;

                        if(current.equals("\"")) { if(next.equals("\"")) { appendToPane(t,current,tBody); previous = current; current = next; next = it.hasNext() ? it.next() : null; appendToPane(t,current,tBody); } else { appendToPane(t,current,tBody); } break; }

                        else appendToPane(t,current,tBody);

                      }

                    }

              }



		else appendToPane(t,current,tTextWCF);

		}

              list.clear();

           }

	    System.gc();

	}

     }

    private void appendToConsolePane(JTextPane tp, String msg, Color c) {

      StyleContext sc = StyleContext.getDefaultStyleContext();

      AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

      aset = sc.addAttribute(aset, StyleConstants.FontFamily, "monospaced");

      aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);


      int len = tp.getDocument().getLength();

      tp.setCaretPosition(len);

      tp.setCharacterAttributes(aset, false);

      tp.replaceSelection(msg);

    }


    private void appendToPane(JTextPane tp, String msg, Color c) {

      StyleContext sc = StyleContext.getDefaultStyleContext();

      AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

      aset = sc.addAttribute(aset, StyleConstants.FontFamily, "monospaced");

      aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);


      int len = tp.getDocument().getLength();

      tp.setCaretPosition(len);

      tp.setCharacterAttributes(aset,false);

      tp.replaceSelection(msg);

    }


    private void appendToPaneTabs(JTextPane tp, String msg, Color c) {


      StyleContext sc = StyleContext.getDefaultStyleContext();

      AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

      aset = sc.addAttribute(aset, StyleConstants.FontFamily, "monospaced");

      aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);


      int len = tp.getCaretPosition();

      tp.setCaretPosition(len);

      tp.setCharacterAttributes(aset,false);

      tp.replaceSelection(msg);

    }
    
    //AddTabs to current position
    private void appendToPaneNumbers(JTextPane tp, String msg, Color c) {


      StyleContext sc = StyleContext.getDefaultStyleContext();

      AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

      aset = sc.addAttribute(aset, StyleConstants.FontFamily, "monospaced");

      aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);

      int len = tp.getCaretPosition();

      tp.setCaretPosition(len);

      tp.setCharacterAttributes(aset,true);

      tp.replaceSelection(msg);

    }

    //exit after clicked on Close Menu!=item
    class ExitAction extends MouseInputAdapter {

	public void mouseClicked(MouseEvent mouseEvent) {

	    System.exit(0);

	}
    }


    public void setCounterL(String s) {

	String parts10[] = s.split("\n");

	int sz = parts10.length;

	for(String sL:parts10) {

	    LineNN+=1;

	    appendToPane(t1,""+LineNN+" \n",tTextWCF);

	}

	System.gc();

    }


    class CounterLines extends KeyAdapter {//listen keyboard event for enter if enter add \n +i to numberLines

	public void keyPressed(KeyEvent e) {

	    if(e.getKeyCode() == KeyEvent.VK_ENTER) {

		LineNN+=1;

		appendToPane(t1,""+LineNN+" \n",tTextWCF);

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_S && (e.getModifiersEx() ==( KeyEvent.CTRL_DOWN_MASK))) {

		SaveFunction();

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_O && (e.getModifiersEx() ==( KeyEvent.CTRL_DOWN_MASK))) {

		OpenFile();

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_P && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

		printFile();

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_X && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

		//cutFunction();

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_C && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

		//copyFunction();

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_V && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_TAB){

		e.consume();

		appendToPaneTabs(t,"  ",tTextWCF);

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_M &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

		VisualConsole+=1;

		if(VisualConsole==2){

		    mbConsole.setVisible(false);

		    VisualConsole=0;

		}
		else { mbConsole.setVisible(true); }

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_V &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

		VisualViewer+=1;

		if(VisualViewer==2) {

		    mbViewer.setVisible(false);

		    VisualViewer=0;

		}
		else { mbViewer.setVisible(true); }

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_UP &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

		mbConsoleSizeH+=100;

		mbConsole.setSize(500,mbConsoleSizeH);

	       mbConsole.setPreferredSize(new Dimension(500,mbConsoleSizeH));

		mbConsole.setVisible(false);

		mbConsole.setVisible(true);

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_DOWN &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))){

		mbConsoleSizeH-=100;

		mbConsole.setSize(500,mbConsoleSizeH);

	       mbConsole.setPreferredSize(new Dimension(500,mbConsoleSizeH));

		mbConsole.setVisible(false);

		mbConsole.setVisible(true);

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_RIGHT &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))){

	     	mbViewerSizeW+=100;

	     	mbViewer.setSize(mbViewerSizeW,300);

	       mbViewer.setPreferredSize(new Dimension(mbViewerSizeW,300));

	     	mbViewer.setVisible(false);

	     	mbViewer.setVisible(true);

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_LEFT &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))){

	     	mbViewerSizeW-=100;

	     	mbViewer.setSize(mbViewerSizeW,300);

	       mbViewer.setPreferredSize(new Dimension(mbViewerSizeW,300));

	     	mbViewer.setVisible(false);

	     	mbViewer.setVisible(true);

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_Q &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))){

              CaretPosSave=t.getCaretPosition();t2.requestFocus();t2.setCaretPosition(t2.getText().length());//}

	    }
	    else if(e.getKeyCode() == KeyEvent.VK_F && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))){

		VisualSearcher+=1;

		if(VisualSearcher==2) {

                  mbSearcher.setVisible(false);

		    VisualSearcher=0;

		}
		else { mbSearcher.setVisible(true); }

	    }
           else if(e.getKeyCode() == KeyEvent.VK_DOWN && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))){

              if(StartTempSearch==tempSearch.size())StartTempSearch=0;

              StartTempSearch++;

              t.requestFocus();

              t.setCaretPosition(tempSearch.get(StartTempSearch));

           }
           else if(e.getKeyCode() == KeyEvent.VK_Q && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))){

              tempSearch.clear();

              StartTempSearch=0;

              System.gc();

           }
           else if(e.getKeyCode() == KeyEvent.VK_UP && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))){

              if(StartTempSearch==0)StartTempSearch=tempSearch.size();

              StartTempSearch--;

              t.requestFocus();

              t.setCaretPosition(tempSearch.get(StartTempSearch));


           }
           else if(e.getKeyCode() == KeyEvent.VK_J && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))){

		VisualJumper+=1;

		if(VisualJumper==2) {

                  mbJumper.setVisible(false);

		    VisualJumper=0;

		}
		else { mbJumper.setVisible(true); }

           }

           t4.setText(t.getText().toString().length()+" chars"+" "+(t.getText().split("\n").length)+" lines"+"   File: "+FileExt+" Language: "+ExtFile);

           if(CountFORFREE==30){CountFORFREE=0;System.gc();}

           CountFORFREE++;

	    // 
	}
    }

    public static void centreWindow(Window frame) {//set center Window

	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();//get size

	int x = (int) ((dimension.getWidth() - frame.getWidth()) * 0.5);//

	int y = (int) ((dimension.getHeight() - frame.getHeight()) * 0.5);

	frame.setLocation(x, y);

    }


    class SearchPrompter extends KeyAdapter {

      public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {

          String s1 = t3.getText();

          String parts[] = s1.split("\n");

          int sz = parts.length;

          String part1 = parts[sz-1];

          String parts2[] = part1.split("SearchL: ");

          int sz1 = parts2.length;

          String jLine = parts2[sz1-1];

          String parts3[] = t.getText().split("\n");

          t.requestFocus();

          int tpos=0;

          for(String r:parts3) {

            if(r.indexOf(jLine)>=0) tempSearch.add(r.indexOf(jLine)+tpos);

            tpos+=r.length()+1;

          }

          System.gc();

        }

      }

    }


    class  jumpPrompter extends KeyAdapter {

      public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {

          String s1 = t5.getText();

          String parts[] = s1.split("\n");

          int sz = parts.length;

          String part1 = parts[sz-1];

          String parts2[] = part1.split("SearchJ: ");

          int sz1 = parts2.length;

          int jLine = Integer.parseInt(parts2[sz1-1]);

          t.requestFocus();

          String parts3[] = t.getText().split("\n");

          int tpos=0;

          int countLN=1;

          for(String r:parts3) {

              if(countLN==jLine)break;

              tpos+=r.length()+1;

              countLN++;

          }

          t.setCaretPosition(tpos);

          System.gc();

        }

      }      

    }


    class CmrPrompter extends KeyAdapter { //cmd\n - \n emulate enter

	public void keyPressed(KeyEvent e) {

	    if(e.getKeyCode() == KeyEvent.VK_ENTER) {

		//get token from doc https://godbolt.org/z/9e5W3zf81
		String s1 = t2.getText();

		String parts[] = s1.split("\n");

		int sz = parts.length;

		String part1 = parts[sz-1];

		String parts2[] = part1.split("% ");

		int sz1 = parts2.length;

		commander=parts2[sz1-1];//set command token

              String ttttt=new String();

		if(commander.equals("clear")==true){t2.setText("");}

		else {

                  if(commander.contains("*")) {

                    String Temparts[] = commander.split(" ");

                      for(String r:Temparts) {

                        if(r.contains("*")) {

                            String trTemp=new String();

                            trTemp = r.replaceAll("\\W","");

                            File dir=new File(".");

                            File[] files1 = dir.listFiles();

                            for(File ffff:files1) {

                              if(ffff.toString().contains(trTemp))

                                ttttt+=ffff.toString()+" ";

                            }

                        }

                        if(!r.contains("*")) ttttt+=r+" ";

                      }

                      commander="";

                      commander+=ttttt;

                      System.gc();

                  }
		    try {//try

			Process pR = Runtime.getRuntime().exec(commander);//call program

			int exitCode = pR.waitFor();

			PrintWriter cmdLineIn = new PrintWriter(pR.getOutputStream());

			BufferedReader cmdLineOut = new BufferedReader(new InputStreamReader(pR.getInputStream()));

			BufferedReader cmdLineErr = new BufferedReader(new InputStreamReader(pR.getErrorStream()));

			String s = null;

			if(exitCode==0||exitCode==2) {

			    while((s=cmdLineOut.readLine())!=null){//read line by line

				appendToPane(t2,"\n"+s,tTextWCF);

			    }
			}

			if(exitCode!=0) {

			    while((s=cmdLineErr.readLine())!=null){//read line by line

				appendToPane(t2,"\n"+s,tConsoleTextError);

			    }
			}

			pR.destroy();

		    }
		    catch (IOException e1) {

			e1.printStackTrace();

		    }
		    catch (InterruptedException e1) {

			e1.printStackTrace();

		    }
		}

		commander= "";//free command string

		appendToPane(t2,"\n"+" % ",tTextWCF);


		t2.setText(t2.getText().substring(0,t2.getText().lastIndexOf("\r\n")));//set cursor after prompt

		System.gc();//just

	    }
            else if(e.getKeyCode() == KeyEvent.VK_Q &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))){


              t.requestFocus();t.setCaretPosition(CaretPosSave);

	  }
	}
    }



    class CmrViewer {//cmd\n - \n emulate enter

	public void viewDir(String stringDir) {

	    Pather.setText("");

	    String commander1;//set command token

	    try {//try

		commander1="ls "+stringDir+"\n";

		Process pR = Runtime.getRuntime().exec(commander1);//call program

		int exitCode = pR.waitFor();

		PrintWriter cmdLineIn = new PrintWriter(pR.getOutputStream());

		BufferedReader cmdLineOut = new BufferedReader(new InputStreamReader(pR.getInputStream()));

		BufferedReader cmdLineErr = new BufferedReader(new InputStreamReader(pR.getErrorStream()));

		String s = null;

		if(exitCode==0||exitCode==2) {

		    while((s=cmdLineOut.readLine())!=null){//read line by line

			appendToPane(Pather,"\n"+s,tTextWCF);

		    }
		}

		if(exitCode!=0) {

		    while((s=cmdLineErr.readLine())!=null){//read line by line

			appendToPane(Pather,"\n"+s,tConsoleTextError);

		    }
		}

		pR.destroy();

	    }
           catch (IOException e1) {

		e1.printStackTrace();

          }
          catch (InterruptedException e1) {

		e1.printStackTrace();

          }

	    commander1= "";//free command string

	    System.gc();//just

	}
    }



    // Main class
    public static void main(String[] args) {

	Editor e = new Editor();//jump to editor

    }
}
