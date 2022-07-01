package notepadEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class Nodepad implements ActionListener {

  JFrame jf,  replaceFram, font_frame;
  JMenuBar menuBar;
  JMenu file, edit, format, view, help;
  JMenuItem New, open, save, saveas, exit, pageSetup, print, cut, copy, paste, delete;
  JMenuItem replace, timeDate, Setfont, fontcolor, textareacolor, zoom, viewhelp, sendfeed, aboutnot;
  JTextArea textArea;
  String title = "Untitled-Notepad";
  JFileChooser fileChooser;
  JCheckBoxMenuItem checkBoxMenuItem;

  File file1;
  JTextField jt1, jt2;
  JButton jb1, ok;
  JComboBox cb_font_style, cb_font_family, cb_font_size;

  Nodepad() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.out.println(e);
    }
    jf = new JFrame(title);
    jf.setSize(500, 500);
    Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\LENOVO\\Desktop\\IdeaProjects\\Notpad\\src\\notepadEditor\\notepad_icon.png");
    jf.setIconImage(icon);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setLocationRelativeTo(null);

    menuBar = new JMenuBar();

    file = new JMenu("File");

    New = new JMenuItem("New");
    New.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
    New.addActionListener(this);
    file.add(New);

    open = new JMenuItem("Open...");
    open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
    open.addActionListener(this);
    file.add(open);

    save = new JMenuItem("Save");
    save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
    save.addActionListener(this);
    file.add(save);

    saveas = new JMenuItem("Save As...");
    saveas.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.SHIFT_DOWN_MASK));
    saveas.addActionListener(this);
    file.add(saveas);

    file.addSeparator();

    pageSetup = new JMenuItem("Page Setup");
    pageSetup.addActionListener(this);
    file.add(pageSetup);

    print = new JMenuItem("Print");
    print.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
    print.addActionListener(this);
    file.add(print);

    file.addSeparator();
    exit = new JMenuItem("Exit");
    exit.addActionListener(this);
    file.add(exit);

    menuBar.add(file);

    edit = new JMenu("Edit");

    cut = new JMenuItem("Cut");
    cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));
    cut.addActionListener(this);
    edit.add(cut);

    copy = new JMenuItem("Copy");
    copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
    copy.addActionListener(this);
    edit.add(copy);

    paste = new JMenuItem("Paste");
    paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK));
    paste.addActionListener(this);
    edit.add(paste);

    delete = new JMenuItem("Delete");
    delete.addActionListener(this);
    edit.add(delete);

    edit.addSeparator();

    replace = new JMenuItem("Replace..");
    replace.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_DOWN_MASK));
    replace.addActionListener(this);
    edit.add(replace);


    timeDate = new JMenuItem("Time/Date");
    timeDate.addActionListener(this);
    edit.add(timeDate);

    menuBar.add(edit);

    format = new JMenu("Format");

    Setfont = new JMenuItem("Font");
    Setfont.addActionListener(this);
    format.add(Setfont);

    fontcolor = new JMenuItem("Font Color");
    fontcolor.addActionListener(this);
    format.add(fontcolor);

    textareacolor = new JMenuItem("Text Area Color");
    textareacolor.addActionListener(this);
    format.add(textareacolor);

    format.addSeparator();

    checkBoxMenuItem = new JCheckBoxMenuItem("Word Wrap");
    checkBoxMenuItem.addActionListener(this);
    format.add(checkBoxMenuItem);

    menuBar.add(format);

    view = new JMenu("View");

    zoom = new JMenuItem("Zoom");
    zoom.addActionListener(this);
    view.add(zoom);

    menuBar.add(view);

    help = new JMenu("Help");

    viewhelp = new JMenuItem("View Help");
    viewhelp.addActionListener(this);
    help.add(viewhelp);

    sendfeed = new JMenuItem("Send Feedback");
    sendfeed.addActionListener(this);
    help.add(sendfeed);

    help.addSeparator();

    aboutnot = new JMenuItem("About Notepad");
    aboutnot.addActionListener(this);
    help.add(aboutnot);

    menuBar.add(help);


    jf.setJMenuBar(menuBar);

    textArea = new JTextArea();

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jf.add(scrollPane);

    jf.setVisible(true);
  }

  public static void main(String[] args) {

    new Nodepad();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    fileChooser = new JFileChooser();
    if (e.getSource() == New) {
      newNotepad();
    }
    if (e.getSource() == exit) {
      System.exit(0);
    }
    if (e.getSource() == save)
    {
      savee();
    }
    if (e.getSource() == saveas) {
      saveAs();
    }
    if(e.getSource() == open)
    {
      openn();
    }
    if (e.getSource() == pageSetup)
    {
      pageSetup();
    }
    if (e.getSource() == print)
    {
      printPage();
    }
    if (e.getSource() == cut)
    {
      textArea.cut();
    }
    if (e.getSource() == copy)
    {
      textArea.copy();
    }
    if (e.getSource() == paste)
    {
      textArea.paste();
    }
    if (e.getSource() == replace)
    {
      replaceFrame();
    }
    if (e.getSource() == jb1)
    {
      replace();
    }
    if (e.getSource() == timeDate)
    {
      dateTime();
    }
    if (e.getSource() == fontcolor)
    {
      setFontColor();
    }
    if (e.getSource() == textareacolor)
    {
      setTextAreaColor();
    }
    if (e.getSource() == Setfont)
    {
      openFontFrame();
    }
    if (e.getSource() == ok)
    {
      setFontToNotepad();
    }
    if (e.getSource() == checkBoxMenuItem)
    {
     boolean b = checkBoxMenuItem.getState();
      textArea.setLineWrap(b);
    }
  }

  public void newNotepad()
  {
    String str= textArea.getText();
    if (!str.equals(""))
    {
        int i = JOptionPane.showConfirmDialog(jf, "Do you want to save this file ?");
        if (i == 0)
        {
          saveAs();
          if (!jf.getTitle().equals(title))
          {
            savetitlel(title);
            textArea.setText("");
          }

        }
        else if (i == 1)
        {
            textArea.setText("");
        }
    }
  }

  void savee()
  {
    if (jf.getTitle().equals(title))
    {
      saveAs();
    }
    else
    {
      FileOutputStream fos = null;
      try
      {
        String str = textArea.getText();
        byte[] b = str.getBytes();
        fos = new FileOutputStream(file1);
        fos.write(b);
      }
      catch (Exception e)
      {
        System.out.println(e);
      }
    }
  }

  public void saveAs() {

    int i = fileChooser.showSaveDialog(jf);
    if (i == 0)
    {
      try
      {
        String text = textArea.getText();
        byte[] arr = text.getBytes();
        file1 = fileChooser.getSelectedFile();
        FileOutputStream fos = new FileOutputStream(file1);
        fos.write(arr);
        fos.close();

        savetitlel(file1.getName());
      }
      catch (Exception e)
      {
        System.out.println(e);
      }

    }

  }

   void openn()
  {
    FileInputStream fis = null;
    try
    {
       int i = fileChooser.showOpenDialog(jf);
       if (i == 0)
       {
         textArea.setText("");
          fis = new FileInputStream(fileChooser.getSelectedFile());
         int r;
         while ((r = fis.read()) != -1)
         {
            textArea.append(String.valueOf((char) r));
         }
         savetitlel(fileChooser.getSelectedFile().getName());
         fis.close();
       }

    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    finally
    {
      try
      {
        fis.close();
      }
      catch (Exception e)
      {

      }
    }
  }

  public void pageSetup()
  {
    PrinterJob pj = PrinterJob.getPrinterJob();
    PageFormat pf = pj.pageDialog(pj.defaultPage());
  }

  public void printPage()
  {
    PrinterJob pj = PrinterJob.getPrinterJob();
    if (pj.printDialog())
    {
      try
      {
        pj.print();
      }
      catch (PrinterException e)
      {
        System.out.println(e);
      }
    }
  }

  public void replaceFrame()
  {

     replaceFram = new JFrame("Replace");
    replaceFram.setSize(500, 300);
    replaceFram.setLayout(null);
    replaceFram.setLocationRelativeTo(null);

    JLabel jl1 = new JLabel("Find What : ");
    jl1.setBounds(50, 50, 80, 40);
    replaceFram.add(jl1);

     jt1 = new JTextField();
     jt1.setBounds(150, 50, 200, 40);
     replaceFram.add(jt1);

     JLabel jl2 = new JLabel("Replace With : ");
     jl2.setBounds(50, 100, 100, 40);
     replaceFram.add(jl2);

     jt2 = new JTextField();
     jt2.setBounds(150, 100, 200, 40);
     replaceFram.add(jt2);

      jb1 = new JButton("Replace");
      jb1.addActionListener(this);
     jb1.setBounds(200, 150, 100, 40);
     replaceFram.add(jb1);
    replaceFram.setVisible(true);
  }
  public void replace()
  {
    String repace_what = jt1.getText();
    String repace_with = jt2.getText();
    String text = textArea.getText();
    String newText = text.replace(repace_what, repace_with);
    textArea.setText(newText);

    replaceFram.setVisible(false);

  }

  public void dateTime()
  {
    LocalDateTime ldt = LocalDateTime.now();
    String str = ldt.format(DateTimeFormatter.ISO_DATE_TIME);
    textArea.append(str);

  }

  public void setFontColor()
  {
    Color cl = JColorChooser.showDialog(jf, "Select font Color..", Color.BLACK);
    textArea.setForeground(cl);

  }

  public void setTextAreaColor()
  {
    Color cl = JColorChooser.showDialog(jf, "Select TextArea color..", Color.white);
    textArea.setBackground(cl);
  }

  public void openFontFrame()
  {
    font_frame=new JFrame("Fonts...");
    font_frame.setSize(650,300);
    font_frame.setLocationRelativeTo(null);
    font_frame.setLayout(null);

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    String[] fontFamilies = ge.getAvailableFontFamilyNames();
    cb_font_family=new JComboBox(fontFamilies);
    cb_font_family.setBounds(50, 50, 200, 40);
    font_frame.add(cb_font_family);

    String[] font_style={"Plain", "Bold", "Italic"};
    cb_font_style=new JComboBox(font_style);
    cb_font_style.setBounds(300, 50, 100, 40);
    font_frame.add(cb_font_style);

    Integer[] font_size={10,16,20,25,30,35,40,50,60};
    cb_font_size=new JComboBox(font_size);
    cb_font_size.setBounds(450, 50, 80, 40);
    font_frame.add(cb_font_size);

    ok=new JButton("OK");
    ok.setBounds(250, 150, 80, 50);
    ok.addActionListener(this);
    font_frame.add(ok);

    font_frame.setVisible(true);
  }

  public void setFontToNotepad()
  {
    String font_family=(String) cb_font_family.getSelectedItem();
    String font_style=(String) cb_font_style.getSelectedItem();
    Integer font_size=(Integer) cb_font_size.getSelectedItem();

    int font_stylee=0;
    if(font_style.equals("Plain"))
    {
      font_stylee=Font.PLAIN;
    }
    else if(font_style.equals("Bold"))
    {
      font_stylee=Font.BOLD;
    }
    else if(font_style.equals("Italic"))
    {
      font_stylee=Font.ITALIC;
    }

    Font f=new Font(font_family, font_stylee, font_size);
    textArea.setFont(f);

    font_frame.setVisible(false);
  }

  public void savetitlel(String title)
  {
    jf.setTitle(title);
  }
}
