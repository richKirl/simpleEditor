/*
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
* IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
* THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
* PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
* LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*/

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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.awt.event.ActionListener;

class Editor extends JFrame implements ActionListener  {

  // Text component//code
  private JTextPane t;

  //lineNumbers
  private JTextPane t1;

  //mini cli
  private JTextPane t2;

  //top linesearch
  private JTextPane t3;

  //bottom
  private JTextPane t4;

  //top Jumper
  private JTextPane t5;

  // Frame//frame-likewWindow
  private JFrame f;

  //prompt
  private String prompter=" \n % ";

  //command after prompt
  String commander;

  //number of lines on doc
  private int LineNN=1;

  //Style//new Color(0,0,0,255);BackGround
  private Color tTextWC;

  //new Color(200,200,200,255);ForeGround
  private Color tTextWCF;

  //new Color(23,159,241);
  private Color tBlue;

  //new Color(206,145,120);
  private Color tBody;

  //new Color(218,112,214);
  private Color tMagenta;

  //new Color(156,220,254);
  private Color tLBlue;

  //new Color(255,201,20);
  private Color tYellow;

  //new Color(25,201,20);
  private Color tGreen;

  //new Color(255,101,100);
  private Color tConsoleTextError;

  //Path for widget Pather
  private String Path;

  //nameFile with res
  private String FilePathw;

  //nameFile
  private String FileExt;

  //.{}
  private String ExtFile;

  //buffer for pathViewer
  private JTextPane Pather;

  private int PatherVisualFlag=1;

  //bufferViewerDirectory
  private CmrViewer testls;

  //supportcolorschemaforlangs
  private String[] langSupport={"C","C++","Java"};

  //sizetabinspaces
  private static final int TAB_SIZE = 2;




  JMenuBar mbConsole;

  private int mbConsoleSizeH = 200;

  private int VisualConsole = 0;


  JMenuBar mbViewer;

  private int VisualViewer = 0;

  private int mbViewerSizeW = 200;




  private short CountFORFREE = 0;





  JMenuBar mbSearcher;

  private int VisualSearcher = 0;


  JMenuBar mbJumper;

  private int VisualJumper = 0;


  JMenuBar mbInfoBar;

  private int VisualInfoBar = 0;


  //possave
  int CaretPosSave;

  //listforsearch
  private List<Integer> tempSearch;

  //startposinsave
  private int StartTempSearch=0;




  JPanel panel1;

  JScrollPane panelScrollText;




  private int FlagForComment = 0;




  private int FlagBraces = 0;



  //history-ring
  private List<String> HListCommands;

  int CurrHListCom=0;



  int posFileS;

  int posFileE;



  private List<kvList> itt;

  private int NumberOfl=0;

  public class kvList {

    private int k,v;

    public kvList(int ka,int ve) {

      k=ka;

      v=ve;

    }
    int getKa() {

      return k;

    }
    int getVe() {

      return v;

    }

  }




  // Constructor
  public Editor() {

    // Create a frame
    JFrame.setDefaultLookAndFeelDecorated( true );

    JDialog.setDefaultLookAndFeelDecorated( true );


    f = new JFrame("editor");




    tempSearch = new ArrayList<Integer>();

    HListCommands = new ArrayList<String>();

    try {

      // Set metal look and feel
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

      // Set theme to ocean//set theme read in doc
      MetalLookAndFeel.setCurrentTheme(new OceanTheme());

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

    //set cursor position in doc
    t.setCaretPosition(t.getDocument().getLength());

    //connect listen keyboard
    t.addKeyListener(new CounterLines());


    // Create a menubar//menubar top
    JMenuBar mb = new JMenuBar();

    // Create amenu for menu//menu for file
    JMenu m1 = new JMenu("File");

    // Create menu items//item for file
    JMenuItem mi1 = new JMenuItem("New");

    JMenuItem mi2 = new JMenuItem("Open");

    JMenuItem mi3 = new JMenuItem("Save");

    JMenuItem mi9 = new JMenuItem("Print");

    // Add action listener//connect event
    mi1.addActionListener(this);

    mi2.addActionListener(this);

    mi3.addActionListener(this);

    mi9.addActionListener(this);

    m1.add(mi1);

    m1.add(mi2);

    m1.add(mi3);

    m1.add(mi9);
    //EndCreateMenuBar



    // Create amenu for menu
    JMenu m2 = new JMenu("Edit");//menu for Edit

    // Create menu items
    JMenuItem mi4 = new JMenuItem("cut");

    JMenuItem mi5 = new JMenuItem("copy");

    JMenuItem mi6 = new JMenuItem("paste");

    // Add action listener//connect event
    mi4.addActionListener(this);

    mi5.addActionListener(this);

    mi6.addActionListener(this);

    m2.add(mi4);

    m2.add(mi5);

    m2.add(mi6);


    //menu button for close instant
    JMenu mc = new JMenu("Close");

    //menu button for close instant
    JMenu col = new JMenu("ColorText");

    //menu button for close instant
    JMenu formatText = new JMenu("formatText");

    //connect event
    mc.addMouseListener(new ExitAction());

    col.addMouseListener(new ColorStyle());

    formatText.addMouseListener(new FormatStyle());

    formatText.setToolTipText("Click for formatText.");

    mb.add(m1);

    mb.add(m2);

    mb.add(mc);

    mb.add(col);

    mb.add(formatText);


    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    //create menubar-bottom for cli
    mbConsole = new JMenuBar();

    //create jtextarea with a1 a2 a3
    t2 = new JTextPane();

    t2.setLocation(120,0);

    t2.setSize(380,200);

    t2.setFont(new Font("monospaced", Font.PLAIN, 16));

    t2.setPreferredSize(new Dimension(380,200));

    //connect keyevent
    t2.addKeyListener(new CmrPrompter());



    t3 = new JTextPane();

    t3.setPreferredSize(new Dimension(600,30));

    t4 = new JTextPane();

    t4.setPreferredSize(new Dimension(600,30));

    t5 = new JTextPane();

    t5.setPreferredSize(new Dimension(600,30));

    //connect jtextarea-cli to bottom menubar
    mbConsole.add(new JScrollPane(t2));

    mbConsole.setLocation(0,200);

    mbConsole.setPreferredSize(new Dimension(500,mbConsoleSizeH));

    mbConsole.setSize(500,mbConsoleSizeH);



    mbViewer = new JMenuBar();

    Pather = new JTextPane();

    Pather.setLocation(0,0);

    Pather.setSize(100,300);

    Pather.setFont(new Font("monospaced",Font.PLAIN,16));

    Pather.setPreferredSize(new Dimension(200,300));

    Pather.addKeyListener(new CmrViewer());

    mbViewer.add(new JScrollPane(Pather));

    mbViewer.setLocation(0,0);

    mbViewer.setPreferredSize(new Dimension(200,300));


    //collors for all jtextareas t=code t1=numbers t2=cli
    tTextWC = new Color(0,0,0,255);

    tTextWCF = new Color(200,200,200,255);

    t.setBackground(tTextWC);

    t.setForeground(tTextWCF);


    //menubar top
    JMenuBar mFunctionality = new JMenuBar();


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

    //connect keyevent
    t3.addKeyListener(new SearchPrompter());



    mbJumper = new JMenuBar();

    t5.setFont(new Font("monospaced", Font.PLAIN, 16));

    t5.setBackground(c2);

    t5.setForeground(cf2);

    t5.setText("SearchJ: ");

    mbJumper.add(t5);

    //connect keyevent
    t5.addKeyListener(new jumpPrompter());



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

    //create panel
    JPanel panel = new JPanel();

    //create template for complians
    panel.setLayout(new BorderLayout());


    panel1 = new JPanel();

    JPanel panel2 = new JPanel();

    panel1.setLayout(new BorderLayout());

    panel2.setLayout(new BorderLayout());

    //left because numbers
    panel1.add(mbSearcher, BorderLayout.NORTH);

    //left because numbers
    panel2.add(mbJumper, BorderLayout.NORTH);

    //left because numbers
    panel1.add(mbInfoBar, BorderLayout.SOUTH);

    //left because numbers
    panel.add(t1, BorderLayout.WEST);

    //right extend to center
    panel.add(t, BorderLayout.CENTER);

    panelScrollText = new JScrollPane(panel);

    panelScrollText.setRowHeaderView(t1);

    panelScrollText.getVerticalScrollBar().setUnitIncrement(30);

    panel1.add(panelScrollText,BorderLayout.CENTER);

    panel2.add(panel1,BorderLayout.CENTER);



    //connect all panel to frame
    f.add(panel2);

    //menubar to up
    f.add(mb,BorderLayout.NORTH);

    f.add(mbViewer,BorderLayout.WEST);

    //set scroll to cli and set bottom position
    f.add(mbConsole,BorderLayout.SOUTH);

    //set size wxh
    f.setSize(1200, 900);

    //set center position
    centreWindow(f);


    f.setVisible(true);

    //fix some situation with current pos and doc
    t.requestFocus();

    //fix some situation with cursor
    t.setCaretPosition(t.getText().length());

    t.setCaretColor(Color.WHITE);

    t2.setCaretColor(Color.WHITE);

    appendToPane(t1,""+LineNN+"\n",tTextWCF);

    appendToPane(t2,"\n"+" % ",tTextWCF);

  }

  // If a button is pressed//actions for items
  public void actionPerformed(ActionEvent e) {

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

    repaint();

    revalidate();

  }


  public void SaveFunction() {

    if(FilePathw!=null) {

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
    else if(FilePathw==null) {

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
    else JOptionPane.showMessageDialog(f, "the user cancelled the operation");


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

        ExtFile = FileExt.substring(FileExt.indexOf(".")+1,FileExt.length());



        String tt=new String();

        for(int i=1;i<partsDirs.length-1;i++) {

          tt+="/"+partsDirs[i];

        }

        tt+="/";

        Path=tt;

        viewDir(Path);

        //FileExt;

        posFileS=Pather.getText().lastIndexOf(FileExt+"\n");

        int spos =Pather.getText().lastIndexOf("\n",posFileS);

        int epos =Pather.getText().indexOf("\n",posFileS);


        Pather.setSelectionStart(spos);

        Pather.setSelectionEnd(epos);

        posFileS=spos;

        posFileE=epos;

        //Pather.select(spos,epos);
        replaceToPane(Pather,Pather.getText().substring(spos,epos),tGreen,spos,epos);

        //System.out.println(spos+" "+epos);

        // Initialize sl
        sl = br.readLine();

        // Take the input from the file
        while ((s1 = br.readLine()) != null) {

          sl = sl + "\n" + s1;

          //set linenumbers while if line
          LineNN+=1;

          appendToPane(t1,""+LineNN+"\n",tTextWCF);

        }

        // Set the text
        t.setText(sl);

        if(ExtFile.equals("java")){

          ExtFile=langSupport[2];

          cmrColorTexte();

        }
        else if(ExtFile.equals("cpp")){

          ExtFile=langSupport[1];

          cmrColorTexte();

        }
        else if(ExtFile.equals("c")){

          ExtFile=langSupport[0];

          cmrColorTexte();

        }

        System.gc();

      }
      catch (Exception evt) {

        JOptionPane.showMessageDialog(f, evt.getMessage());

      }

    }

    // If the user cancelled the operation
    else JOptionPane.showMessageDialog(f, "the user cancelled the operation");

  }

  public void NewFunction() {

    String tempS=new String();

    tempS=t.getText();

    if(tempS!=null){

      //SaveFunction();

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

    //System.out.println(t.getText().split("/n").length);

  }

  //exit after clicked on Close Menu!=item
  class FormatStyle extends MouseInputAdapter {

    public void mouseClicked(MouseEvent mouseEvent) {

      cmrFormatTexte();cmrColorTexte();

    }

  }

  //exit after clicked on Close Menu!=item
  class ColorStyle extends MouseInputAdapter {

    public void mouseClicked(MouseEvent mouseEvent) {

      cmrColorTexte();

    }

  }

  public void cmrColorTexte() {

    int currPosC=t.getCaretPosition();

    if(ExtFile.equals("C++")) {

      String s1 = t.getText();

      //(?<=\s)(?=\S)|(?<=\S)(?=\s)|(?<=\w)(?=\W)|(?<=\W)(?=\w)
      t.setText("");

      //"((?= )|(?=\t)|(?<=\n))"(?=\\w)
      String parts[] = s1.split("(?U)(?<=\\s)(?=\\S)|(?<=\\S)(?=\\s)|(?<=\\w)(?=\\W)|(?<=\\W)");

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

        else if(current.equals("<")&&next.equals("<")){

          appendToPane(t,current,tBody);

          appendToPane(t,next,tBody);

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;

        }

        else if(previous.equals("<")&&current.equals("std")==false){   appendToPane(t,current,tBody);}

        else if(current.equals(">")){                                  appendToPane(t,current,tBody);}

        else if(current.equals("return")){                             appendToPane(t,current,tMagenta);}

        else if(current.equals("int")){                                appendToPane(t,current,tBlue);}

        else if(current.equals("bool")){                               appendToPane(t,current,tBlue);}

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

        else if(current.equals("switch")){                             appendToPane(t,current,tMagenta);}

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

            if(next.equals("\n")){ appendToPane(t,current,tGreen); break; }

            else appendToPane(t,current,tGreen);

          }

        }
        else if(current.equals("/")&&next.equals("*")){

          appendToPane(t,current,tGreen);

          appendToPane(t,next,tGreen);

          for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

            previous = current;

            current = next;

            next = it.hasNext() ? it.next() : null;

            if(current.equals("*")&&next.equals("/")){ appendToPane(t,current,tGreen); appendToPane(t,next,tGreen);previous = current;current = next;next = it.hasNext() ? it.next() : null; break; }
            else appendToPane(t,current,tGreen);

          }

        }
        else if(current.equals("(")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals(")")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals("[")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals("]")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals("{")){                                  appendToPane(t,current,tMagenta);}

        else if(current.equals("}")){                                  appendToPane(t,current,tMagenta);}

        else if(current.equals("}")&&next.equals(";")){                appendToPane(t,current,tMagenta);}

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

              if(current.equals("\"")) {

                if(next.equals("\"")) {

                  appendToPane(t,current,tBody);

                  previous = current;

                  current = next;

                  next = it.hasNext() ? it.next() : null;

                  appendToPane(t,current,tBody);

                }
                else {

                  appendToPane(t,current,tBody);

                }

                break;

              }
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

    else if(ExtFile.equals("C")) {

      String s1 = t.getText();

      //(?<=\s)(?=\S)|(?<=\S)(?=\s)|(?<=\w)(?=\W)|(?<=\W)(?=\w)
      t.setText("");

      //"((?= )|(?=\t)|(?<=\n))"(?=\\w)
      String parts[] = s1.split("(?U)(?<=\\s)(?=\\S)|(?<=\\S)(?=\\s)|(?<=\\w)(?=\\W)|(?<=\\W)");

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

        else if(current.equals(">")){                                  appendToPane(t,current,tBody);}

        else if(current.equals("return"))                              appendToPane(t,current,tMagenta);

        else if(current.equals("int")){                                appendToPane(t,current,tBlue);}

        else if(current.equals("char")){                               appendToPane(t,current,tBlue);}

        else if(current.equals("bool")){                               appendToPane(t,current,tBlue);}

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

        else if(current.equals("struct")){                             appendToPane(t,current,tBlue);}

        else if(current.equals("union")){                              appendToPane(t,current,tBlue);}

        else if(current.equals("import")){                             appendToPane(t,current,tBlue);}

        else if(current.equals("static")){                             appendToPane(t,current,tBlue);}

        else if(current.equals("constexpr")){                          appendToPane(t,current,tBlue);}

        else if(current.equals("const")){                              appendToPane(t,current,tBlue);}

        else if(current.equals("void")){                               appendToPane(t,current,tBlue);}

        else if(current.equals("do")){                                 appendToPane(t,current,tMagenta);}

        else if(current.equals("for")){                                appendToPane(t,current,tBlue);}

        else if(current.equals("while")){                              appendToPane(t,current,tMagenta);}

        else if(current.equals("if")){                                 appendToPane(t,current,tMagenta);}

        else if(current.equals("else")){                               appendToPane(t,current,tMagenta);}

        else if(current.equals("switch")){                             appendToPane(t,current,tMagenta);}

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
        else if(current.equals("/")&&next.equals("*")){

          appendToPane(t,current,tGreen);

          appendToPane(t,next,tGreen);

          for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

            previous = current;

            current = next;

            next = it.hasNext() ? it.next() : null;

            if(current.equals("*")&&next.equals("/")){ appendToPane(t,current,tGreen); appendToPane(t,next,tGreen);previous = current;current = next;next = it.hasNext() ? it.next() : null; break; }
            else appendToPane(t,current,tGreen);

          }

        }
        else if(current.equals("(")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals(")")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals("[")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals("]")){                                  appendToPane(t,current,tBlue);}

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

              if(current.equals("\"")) {

                if(next.equals("\"")) {

                  appendToPane(t,current,tBody);

                  previous = current;

                  current = next;

                  next = it.hasNext() ? it.next() : null;

                  appendToPane(t,current,tBody);

                }
                else {

                  appendToPane(t,current,tBody);

                }

                break;

              }
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

      String s1 = t.getText();

      //(?<=\s)(?=\S)|(?<=\S)(?=\s)|(?<=\w)(?=\W)|(?<=\W)(?=\w)
      t.setText("");

      //"((?= )|(?=\t)|(?<=\n))"(?=\\w)
      String parts[] = s1.split("(?U)(?<=\\s)(?=\\S)|(?<=\\S)(?=\\s)|(?<=\\w)(?=\\W)|(?<=\\W)");

      List<String> list = new ArrayList<String>();

      for(String r: parts)list.add(r);

      final Iterator<String> it = list.iterator();

      for(String next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

        String previous = current;

        current = next;

        next = it.hasNext() ? it.next() : null;

        if(current.equals("import")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals("return")){                             appendToPane(t,current,tMagenta);}

        else if(current.equals("int")){                                appendToPane(t,current,tBlue);}

        else if(current.equals("char")){                               appendToPane(t,current,tBlue);}

        else if(current.equals("double")){                             appendToPane(t,current,tBlue);}

        else if(current.equals("boolean")){                             appendToPane(t,current,tBlue);}

        else if(current.equals("unsigned")){                           appendToPane(t,current,tBlue);}

        else if(current.equals("long")){                               appendToPane(t,current,tBlue);}

        else if(current.equals("class")){                              appendToPane(t,current,tBlue);}

        else if(current.equals("static")){                             appendToPane(t,current,tBlue);}

        else if(current.equals("protected")){                          appendToPane(t,current,tBlue);}

        else if(current.equals("throws")){                             appendToPane(t,current,tBlue);}

        else if(current.equals("final")){                              appendToPane(t,current,tBlue);}

        else if(current.equals("public")){                             appendToPane(t,current,tBlue);}

        else if(current.equals("private")){                            appendToPane(t,current,tBlue);}

        else if(current.equals("void")){                               appendToPane(t,current,tBlue);}

        else if(current.equals("new")){                                appendToPane(t,current,tBlue);}

        else if(current.equals("break")){                              appendToPane(t,current,tBlue);}

        else if(current.equals("continue")){                           appendToPane(t,current,tMagenta);}

        else if(current.equals("do")){                                 appendToPane(t,current,tMagenta);}

        else if(current.equals("for")){                                appendToPane(t,current,tBlue);}

        else if(current.equals("while")){                              appendToPane(t,current,tMagenta);}

        else if(current.equals("if")){                                 appendToPane(t,current,tMagenta);}

        else if(current.equals("else")){                               appendToPane(t,current,tMagenta);}

        else if(current.equals("switch")){                             appendToPane(t,current,tMagenta);}

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
        else if(current.equals("/")&&next.equals("*")){

          appendToPane(t,current,tGreen);

          appendToPane(t,next,tGreen);

          for(next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

            previous = current;

            current = next;

            next = it.hasNext() ? it.next() : null;

            if(current.equals("*")&&next.equals("/")){ appendToPane(t,current,tGreen); appendToPane(t,next,tGreen);previous = current;current = next;next = it.hasNext() ? it.next() : null; break; }
            else appendToPane(t,current,tGreen);

          }

        }

        else if(current.equals("(")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals(")")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals("[")){                                  appendToPane(t,current,tBlue);}

        else if(current.equals("]")){                                  appendToPane(t,current,tBlue);}

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

              if(current.equals("\"")) {

                if(next.equals("\"")) {

                  appendToPane(t,current,tBody);

                  previous = current;

                  current = next;

                  next = it.hasNext() ? it.next() : null;

                  appendToPane(t,current,tBody);

                }
                else {

                  appendToPane(t,current,tBody);

                }

                break;

              }
              else appendToPane(t,current,tBody);

            }

          }

        }
        else appendToPane(t,current,tTextWCF);

      }

      list.clear();

    }

    t.setCaretPosition(currPosC);

    System.gc();

  }

  //test
  public void cmrFormatTexte() {

    int ccc=t.getCaretPosition();

    String s1 = t.getText();

    t.setText("");

    String s2[] = s1.split("\n");

    String ttt = new String();

    int countCCCC=0;int first=0;

    List<String> list = new ArrayList<String>();

    for(String r:s2) {

      r=r.replaceFirst("^\\s*", "");

      r=r.replaceFirst("\\s*$", "");

      if(r.length()==1&&r.contains("}")){countCCCC--;}
      else if(r.length()==2&&r.contains("};")){countCCCC--;}
      for(int i=0;i<countCCCC;i++){if(r.equals("")){}else{ttt+="  ";}}

      if(r.length()==1&&r.contains("}")){countCCCC++;}
      else if(r.length()==2&&r.contains("};")){countCCCC++;}

      ttt+=r;ttt+="\n";

      String parts[] = r.split("(?U)(?<=\\s)(?=\\S)|(?<=\\S)(?=\\s)|(?<=\\w)(?=\\W)|(?<=\\W)");



      for(String r1: parts)list.add(r1);

      final Iterator<String> it = list.iterator();

      for(String next = (it.hasNext() ? it.next() : null), current = null; next != null;) {

        String previous = current;

        current = next;

        next = it.hasNext() ? it.next() : null;


        if(current.equals("{")) {

          countCCCC++;

        }
        else if(current.equals("{")&&next.equals("}")) {

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;

        }
        else if(current.equals("=")&&next.equals("{")) {

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;


        }
        else if(current.equals("\"")&&next.equals("{")) {

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;


        }
        else if(current.equals("\"")&&next.equals("}")) {

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;

          previous = current;

          current = next;

          next = it.hasNext() ? it.next() : null;

        }
        else if(current.equals("}")) {

          countCCCC-=1;

        }

      }

      list.clear();

    }

    t.setText(ttt);

    t.setCaretPosition(ccc);

    System.gc();

  }

  private void appendToPaneCurrPos(JTextPane tp, String msg, Color c,int pos) {

    StyleContext sc = StyleContext.getDefaultStyleContext();

    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

    aset = sc.addAttribute(aset, StyleConstants.FontFamily, "monospaced");

    aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);

    tp.setCaretPosition(pos);

    tp.setCharacterAttributes(aset,false);

    tp.replaceSelection(msg);

  }

  private void appendToPaneCurr(JTextPane tp, String msg, Color c) {

    StyleContext sc = StyleContext.getDefaultStyleContext();

    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

    aset = sc.addAttribute(aset, StyleConstants.FontFamily, "monospaced");

    aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);


    int len = tp.getCaretPosition();

    tp.setCaretPosition(len);

    tp.setCharacterAttributes(aset,false);

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

  private void replaceToPane(JTextPane tp, String msg, Color c,int start,int end) {

    StyleContext sc = StyleContext.getDefaultStyleContext();

    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

    aset = sc.addAttribute(aset, StyleConstants.FontFamily, "monospaced");

    aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);



    tp.setCharacterAttributes(aset,false);

    tp.setSelectionStart(start);

    tp.setSelectionEnd(end);

    tp.replaceSelection(msg);

  }

  private String getFromPane(JTextPane tp, int start,int end) {

    StyleContext sc = StyleContext.getDefaultStyleContext();

    int len = tp.getDocument().getLength();

    tp.setCaretPosition(start);

    tp.setSelectionStart(start);

    tp.setSelectionEnd(end);

    return tp.getSelectedText();

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

  //listen keyboard event for enter if enter add \n +i to numberLines
  class CounterLines extends KeyAdapter {

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

        cmrColorTexte();

      }
      else if(e.getKeyCode() == KeyEvent.VK_P && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        printFile();

      }
      else if(e.getKeyCode() == KeyEvent.VK_W && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        cmrCommenterLineTemp();

      }
      else if(e.getKeyCode() == KeyEvent.VK_R && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        cmrSelectLineTemp();

      }
      else if((e.getModifiersEx() == (KeyEvent.SHIFT_DOWN_MASK))) {

        checkShiftKeys(e);

      }

      else if(e.getKeyCode() == KeyEvent.VK_D && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        cmrDeleterLineTemp();

      }
      else if(e.getKeyCode() == KeyEvent.VK_V &&  (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        e.consume();

        int tempPrevNLines=0;

        tempPrevNLines = LineNN;

        t.paste();

        int tempCurrNLines=0;

        tempCurrNLines = t.getText().split("\n").length-tempPrevNLines;

        for(int i=0;i<tempCurrNLines;i++) {

          LineNN+=1;

          appendToPane(t1,""+LineNN+"\n",tTextWCF);


        }


      }
      else if(e.getKeyCode() == KeyEvent.VK_TAB) {

        e.consume();

        appendToPaneTabs(t,"  ",tTextWCF);

      }
      else if(e.getKeyCode() == KeyEvent.VK_M &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        toggleConsole();

      }
      else if(e.getKeyCode() == KeyEvent.VK_V &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        toggleViewer();

      }
      else if(e.getKeyCode() == KeyEvent.VK_UP &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        sizeConsoleUPH();

      }
      else if(e.getKeyCode() == KeyEvent.VK_DOWN &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        sizeConsoleDownH();

      }
      else if(e.getKeyCode() == KeyEvent.VK_RIGHT &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        sizeViewerRightW();

      }
      else if(e.getKeyCode() == KeyEvent.VK_LEFT &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        sizeViewerLeftW();

      }
      else if(e.getKeyCode() == KeyEvent.VK_Q &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        CaretPosSave=t.getCaretPosition();

        t2.requestFocus();

        t2.setCaretPosition(t2.getText().length());

      }
      else if(e.getKeyCode() == KeyEvent.VK_W &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        if(PatherVisualFlag==1){

          CaretPosSave=t.getCaretPosition();

          Pather.requestFocus();

          Pather.setCaretPosition(Pather.getText().length());

        }

      }
      else if(e.getKeyCode() == KeyEvent.VK_F && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        toggleSearcher();

      }
      else if(e.getKeyCode() == KeyEvent.VK_DOWN && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        if(StartTempSearch==tempSearch.size())StartTempSearch=0;

        StartTempSearch++;

        t.requestFocus();

        t.setCaretPosition(tempSearch.get(StartTempSearch));

      }
      else if(e.getKeyCode() == KeyEvent.VK_Q && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        tempSearch.clear();

        StartTempSearch=0;

        System.gc();

      }
      else if(e.getKeyCode() == KeyEvent.VK_UP && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        if(StartTempSearch==0)StartTempSearch=tempSearch.size();

        StartTempSearch--;

        t.requestFocus();

        t.setCaretPosition(tempSearch.get(StartTempSearch));


      }
      else if(e.getKeyCode() == KeyEvent.VK_J && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {

        toggleJumper();

      }
      else if(!(e.getModifiersEx() == (KeyEvent.SHIFT_DOWN_MASK))&&!(e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))&&!(e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))){

        checkKeys(e);

      }
      t4.setText(t.getText().toString().length()+" chars"+" "+(t.getText().split("\n").length)+" lines"+"   File: "+FileExt+" Language: "+ExtFile);

      if(CountFORFREE==30) {

        CountFORFREE=0;System.gc();

      }

      CountFORFREE++;

    }

    public void keyTyped(KeyEvent e) {

      if(( (e.getModifiersEx() == (KeyEvent.SHIFT_DOWN_MASK))) ) {

        e.consume();

        if(e.getKeyCode() == KeyEvent.VK_9){


        }

      }
      else {

        e.consume();



      }

    }

  }

  public void checkKeys(KeyEvent e) {

    if(e.getKeyCode() == KeyEvent.VK_Q) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"q",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_W) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"w",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_E) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"e",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_R) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"r",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_T) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"t",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_Y) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"y",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_U) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"u",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_I) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"i",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_O) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"o",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_P) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"p",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_A) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"a",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_S) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"s",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_D) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"d",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_F) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"f",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_G) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"g",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_H) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"h",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_J) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"j",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_K) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"k",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_L) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"l",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_Z) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"z",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_X) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"x",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_C) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"c",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_V) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"v",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_B) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"b",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_N) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"n",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_M) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"m",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_COMMA) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,",",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_PERIOD) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,".",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_SLASH) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"/",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_SEMICOLON) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,";",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_QUOTE) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"'",tBody,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"\\",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"[",tBlue,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"]",tBlue,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_CLOSE_SPACE) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t," ",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }

  }

  public void checkShiftKeys(KeyEvent e) {

    if(e.getKeyCode() == KeyEvent.VK_BACK_QUOTE  ) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"~",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_1) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"!",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_2) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"@",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_3) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"#",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_4) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"$",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_5) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"%",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_6) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"^",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_7) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"&",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_8) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"*",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_9) {

      CaretPosSave=t.getCaretPosition();

      cmrBraces();

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_0) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,")",tBlue,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_MINUS) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"_",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_EQUALS) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"+",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_Q) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"Q",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_W) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"W",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_E) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"E",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_R) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"R",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_T) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"T",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_Y) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"Y",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_U) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"U",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_I) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"I",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_O) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"O",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_P) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"P",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_A) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"A",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_S) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"S",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_D) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"D",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_F) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"F",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_G) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"G",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_H) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"H",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_J) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"J",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_K) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"K",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_L) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"L",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_Z) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"Z",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_X) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"X",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_C) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"C",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_V) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"V",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_B) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"B",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_N) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"N",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_M) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"M",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_COMMA) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"<",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_PERIOD) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,">",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_SLASH) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"?",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_SEMICOLON) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,":",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_QUOTE) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"\"",tBody,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"|",tTextWCF,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"{",tMagenta,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {

      CaretPosSave=t.getCaretPosition();

      appendToPaneCurrPos(t,"}",tMagenta,CaretPosSave);

      t.setCaretPosition(CaretPosSave+1);

    }
    else if(e.getKeyCode() == KeyEvent.VK_INSERT) {

      e.consume();

      int tempPrevNLines=0;

      tempPrevNLines = LineNN;

      t.paste();

      int tempCurrNLines=0;

      tempCurrNLines = t.getText().split("\n").length-tempPrevNLines;

      for(int i=0;i<tempCurrNLines;i++) {

        LineNN+=1;

        appendToPane(t1,""+LineNN+"\n",tTextWCF);

      }

    }

  }

  public void sizeViewerLeftW() {


    mbViewerSizeW-=100;

    mbViewer.setSize(mbViewerSizeW,300);

    mbViewer.setPreferredSize(new Dimension(mbViewerSizeW,300));

    mbViewer.setVisible(false);

    mbViewer.setVisible(true);

  }

  public void sizeViewerRightW() {


    mbViewerSizeW+=100;

    mbViewer.setSize(mbViewerSizeW,300);

    mbViewer.setPreferredSize(new Dimension(mbViewerSizeW,300));

    mbViewer.setVisible(false);

    mbViewer.setVisible(true);

  }

  public void sizeConsoleDownH() {


    mbConsoleSizeH-=100;

    mbConsole.setSize(500,mbConsoleSizeH);

    mbConsole.setPreferredSize(new Dimension(500,mbConsoleSizeH));

    mbConsole.setVisible(false);

    mbConsole.setVisible(true);

  }

  public void sizeConsoleUPH() {


    mbConsoleSizeH+=100;

    mbConsole.setSize(500,mbConsoleSizeH);

    mbConsole.setPreferredSize(new Dimension(500,mbConsoleSizeH));

    mbConsole.setVisible(false);

    mbConsole.setVisible(true);

  }


  public void toggleConsole() {

    VisualConsole+=1;

    if(VisualConsole==2){

      mbConsole.setVisible(false);

      VisualConsole=0;

    }
    else {

      mbConsole.setVisible(true);

    }

  }

  public void toggleViewer() {

    VisualViewer+=1;

    if(VisualViewer==2) {

      mbViewer.setVisible(false);

      VisualViewer=0;

      PatherVisualFlag=0;

    }
    else {

      mbViewer.setVisible(true);

      PatherVisualFlag=1;

    }

  }

  public void toggleSearcher() {

    VisualSearcher+=1;

    if(VisualSearcher==2) {

      mbSearcher.setVisible(false);

      VisualSearcher=0;

    }
    else {

      mbSearcher.setVisible(true);

    }

  }

  public void toggleJumper() {

    VisualJumper+=1;

    if(VisualJumper==2) {

      mbJumper.setVisible(false);

      VisualJumper=0;

    }
    else {

      mbJumper.setVisible(true);

    }

  }

  public void cmrBraces() {

    int currentPosC = t.getCaretPosition();

    Highlighter.Highlight[] h = t.getHighlighter().getHighlights();

    int hi=-1;

    int hl=-2;

    for(int i = 0; i < h.length; i++) {

      hi=h[i].getStartOffset();

      hl=h[i].getEndOffset();

    }

    if(hi==-1&&hl==-2||hi==hl){

      int currentPos=t.getCaretPosition();

      appendToPaneCurrPos(t,"(",tBlue,currentPos);

    }
    else {

      FlagBraces=1;

      int currentPos = hi;

      appendToPaneCurrPos(t,"(",tBlue,hi);

      appendToPaneCurrPos(t,")",tBlue,hl+1);

      t.setCaretPosition(currentPosC);

      System.gc();

    }

  }

  public void cmrCommenterLineTemp() {

    int getStart=t.getSelectionStart();

    int getEnd=t.getSelectionEnd();

    int currentPos = t.getText().lastIndexOf("\n",t.getCaretPosition())+1;

    if(getStart==getEnd) {

      replaceToPane(t,"//"+t.getText().substring(t.getText().lastIndexOf("\n",t.getCaretPosition())+1,t.getText().indexOf("\n",t.getCaretPosition())),tGreen,t.getText().lastIndexOf("\n",t.getCaretPosition())+1,t.getText().indexOf("\n",t.getCaretPosition()));

    }
    else {

      String tttt=(t.getText().substring(getStart,getEnd)).replaceAll("\n","\n//");

      replaceToPane(t,"",tGreen,getStart,getEnd);

      appendToPaneCurrPos(t,"//"+tttt,tGreen,getStart);



    }

    t.setCaretPosition(currentPos);

    System.gc();

  }

  public void cmrSelectLineTemp() {

    int spos=t.getText().lastIndexOf("\n",t.getCaretPosition())+1;

    int epos=t.getText().indexOf("\n",t.getCaretPosition());


    t.setSelectionStart(spos);

    t.setSelectionEnd(epos);

    t.select(spos,epos);

    System.gc();

  }

  public void cmrDeleterLineTemp() {

    int currentPos = t.getText().lastIndexOf("\n",t.getCaretPosition())+1;

    replaceToPane(t,"",tTextWCF,t.getText().lastIndexOf("\n",t.getCaretPosition())+1,t.getText().indexOf("\n",t.getCaretPosition())+1);

    t.setCaretPosition(currentPos);

    System.gc();

  }

  //set center Window
  public static void centreWindow(Window frame) {

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    int x = (int) ((dimension.getWidth() - frame.getWidth()) * 0.5);

    int y = (int) ((dimension.getHeight() - frame.getHeight()) * 0.5);

    frame.setLocation(x, y);

  }


  class SearchPrompter extends KeyAdapter {

    public void keyPressed(KeyEvent e) {

      if(e.getKeyCode() == KeyEvent.VK_ENTER) {

        e.consume();

        String jLine = getFromPane(t3,t3.getText().indexOf(" ",8)+1,t3.getText().length());

        String parts3[] = t.getText().split("\n");

        t.requestFocus();

        t3.setText("");

        t3.setText("SearchL: ");

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

        e.consume();

        int jLine = Integer.parseInt(getFromPane(t5,t5.getText().indexOf(" ",8)+1,t5.getText().length()));

        t.requestFocus();

        t5.setText("");

        t5.setText("SearchJ: ");


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

  //cmd\n - \n emulate enter
  class CmrPrompter extends KeyAdapter {

    public void keyPressed(KeyEvent e) {

      if(e.getKeyCode() == KeyEvent.VK_ENTER) {

        e.consume();

        commander = t2.getText().substring(t2.getText().lastIndexOf("% ")+2,t2.getText().length());

        String ttttt=new String();

        if(commander.equals("clear")==true){

          t2.setText("");

        }
        else if(commander.equals(" ")||commander.equals("")){

          appendToPane(t2,"\n % ",tTextWCF);

        }
        else {

          if(commander.contains("*")) {

            String Temparts[] = commander.split(" ");

            File dir=new File(".");

            File[] files1 = dir.listFiles();

            for(String r:Temparts) {

              if(r.contains("*")) {

                String trTemp=new String();

                trTemp = r.replaceAll("\\W","");



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
          if(!commander.equals(" ")||!commander.equals("")){

            try {

              //call program
              Process pR = Runtime.getRuntime().exec(commander);


              int exitCode = pR.waitFor();

              PrintWriter cmdLineIn = new PrintWriter(pR.getOutputStream());

              BufferedReader cmdLineOut = new BufferedReader(new InputStreamReader(pR.getInputStream()));

              BufferedReader cmdLineErr = new BufferedReader(new InputStreamReader(pR.getErrorStream()));

              String s = null;

              if(exitCode==0||exitCode==2) {

                //read line by line
                while((s=cmdLineOut.readLine())!=null){

                  appendToPane(t2,"\n"+s,tTextWCF);

                }

              }

              if(exitCode!=0) {

                //read line by line
                while((s=cmdLineErr.readLine())!=null){

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

            HListCommands.add(commander);

            commander= "";

            appendToPane(t2,"\n % ",tTextWCF);

          }

        }

        System.gc();

      }
      else if(e.getKeyCode() == KeyEvent.VK_Q &&  (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))){

        t.requestFocus();t.setCaretPosition(CaretPosSave);

      }
      else if(e.getKeyCode() == KeyEvent.VK_UP){

        e.consume();

        if(CurrHListCom<0) {

          if(HListCommands.size()==1)CurrHListCom=0;

          else if(HListCommands.size()==0){}

          else CurrHListCom=CurrHListCom=HListCommands.size()-1;

        }
        if(HListCommands.size()>0) commander=HListCommands.get(CurrHListCom);

        else commander="";

        replaceToPane(t2,"\n % "+commander,tTextWCF,t2.getText().lastIndexOf("\n %"),t2.getText().length());

        commander="";

        CurrHListCom--;

        System.gc();

      }

    }

  }


  //cmd\n - \n emulate enter
  class CmrViewer extends KeyAdapter {


    public void keyPressed(KeyEvent e) {

      if(e.getKeyCode() == KeyEvent.VK_ENTER) {

        e.consume();

        t.setText("");

        t1.setText("");

        LineNN=0;

        try {

          // String
          String s1 = "", sl = "",sf = "";

          // File reader
          FileReader fr = new FileReader(Path+(Pather.getText().substring(posFileS+1,posFileE)));

          // Buffered reader
          BufferedReader br = new BufferedReader(fr);

          FilePathw=Path+(Pather.getText().substring(posFileS+1,posFileE));



          FileExt = Pather.getText().substring(posFileS+1,posFileE);

          String partsDim[] = FileExt.split("\\W");

          int sz2 = partsDim.length;

          ExtFile = partsDim[sz2-1];


          Path=Path;

          viewDir(Path);

          //FileExt;

          posFileS=Pather.getText().lastIndexOf(FileExt+"\n");

          int spos =Pather.getText().lastIndexOf("\n",posFileS);

          int epos =Pather.getText().indexOf("\n",posFileS);


          Pather.setSelectionStart(spos);

          Pather.setSelectionEnd(epos);

          posFileS=spos;

          posFileE=epos;

          //Pather.select(spos,epos);
          replaceToPane(Pather,Pather.getText().substring(spos,epos),tGreen,spos,epos);



          // Initialize sl
          sl = br.readLine();

          // Take the input from the file
          while ((s1 = br.readLine()) != null) {

            sl = sl + "\n" + s1;

            //set linenumbers while if line
            LineNN+=1;

            appendToPane(t1,""+LineNN+"\n",tTextWCF);

          }
          //set linenumbers while if line
          LineNN+=1;

          appendToPane(t1,""+LineNN+"\n",tTextWCF);
          // Set the text
          t.setText(sl);

          if(ExtFile.equals("java")){

            ExtFile=langSupport[2];

            cmrColorTexte();

          }
          else if(ExtFile.equals("cpp")){

            ExtFile=langSupport[1];

            cmrColorTexte();

          }
          else if(ExtFile.equals("c")){

            ExtFile=langSupport[0];

            cmrColorTexte();

          }

          System.gc();

        }
        catch (Exception evt) {

          JOptionPane.showMessageDialog(f, evt.getMessage());

        }


      }
      else if(e.getKeyCode() == KeyEvent.VK_DOWN && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {


        Pather.setSelectionStart(posFileS);

        Pather.setSelectionEnd(posFileE);



        replaceToPane(Pather,Pather.getText().substring(posFileS,posFileE),tTextWCF,posFileS,posFileE);


        posFileE+=2;

        posFileS=Pather.getText().lastIndexOf("\n",posFileE);

        posFileE= Pather.getText().indexOf("\n",posFileE);


        Pather.setSelectionStart(posFileS);

        Pather.setSelectionEnd(posFileE);



        replaceToPane(Pather,Pather.getText().substring(posFileS,posFileE),tGreen,posFileS,posFileE);

        Pather.setCaretPosition(posFileS+1);



      }
      else if(e.getKeyCode() == KeyEvent.VK_UP && (e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK))) {


        Pather.setSelectionStart(posFileS);

        Pather.setSelectionEnd(posFileE);



        replaceToPane(Pather,Pather.getText().substring(posFileS,posFileE),tTextWCF,posFileS,posFileE);



        posFileE= Pather.getText().indexOf("\n",posFileS-2);

        posFileS=Pather.getText().lastIndexOf("\n",posFileS-2);



        Pather.setSelectionStart(posFileS);

        Pather.setSelectionEnd(posFileE);



        replaceToPane(Pather,Pather.getText().substring(posFileS,posFileE),tGreen,posFileS,posFileE);

        Pather.setCaretPosition(posFileS+1);



      }
      else if(e.getKeyCode() == KeyEvent.VK_W && (e.getModifiersEx() == (KeyEvent.ALT_DOWN_MASK))) {

        t.requestFocus();t.setCaretPosition(CaretPosSave);

      }

    }

  }

  public void viewDir(String stringDir) {

    Pather.setText("");

    String commander1;

    try {

      commander1="ls "+stringDir+"\n";

      //call program
      Process pR = Runtime.getRuntime().exec(commander1);

      int exitCode = pR.waitFor();

      PrintWriter cmdLineIn = new PrintWriter(pR.getOutputStream());

      BufferedReader cmdLineOut = new BufferedReader(new InputStreamReader(pR.getInputStream()));

      BufferedReader cmdLineErr = new BufferedReader(new InputStreamReader(pR.getErrorStream()));

      String s = null;

      if(exitCode==0||exitCode==2) {

        //read line by line
        while((s=cmdLineOut.readLine())!=null) {

          appendToPane(Pather,"\n"+s,tTextWCF);

        }

      }

      if(exitCode!=0) {

        //read line by line
        while((s=cmdLineErr.readLine())!=null) {

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

    commander1= "";

    System.gc();

  }

  // Main class
  public static void main(String[] args) {

    //jump to editor
    Editor e = new Editor();

  }

}
