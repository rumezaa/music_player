import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Main {

    //initiating our arrays
    public static ArrayList<String> songs = new ArrayList<String>();
    public static ArrayList<String> artists = new ArrayList<String>();

    //instantiating classes


    public static Scanner input = new Scanner(System.in);

    public static JTextField tf = new JTextField();
    public static JTextArea ta = new JTextArea();

    //for adding songs
    public static JDialog add = new JDialog();
    public static JTextField add_s = new JTextField(30);


    //for removing songs
    public static JDialog remove = new JDialog();
    public static JTextField rem_s = new JTextField();

    public static JScrollPane scroll;






    public static void main(String[] args){
        menu();
    }


    //deassembles the input and puts it in the according array
    public static void addSong(String song){
        artists.add(song.split(" ")[0]);
        songs.add(song.split(" ")[2]);
    }

    //deassembles input and searches
    public static void removeSong(String song){
        String sng = song.split(" ")[2];
        String artst = song.split(" ")[0];

        for(int i = 0; i<artists.size(); i++){
            for(int m = 0; m<songs.size(); m++){
                if((""+artists.get(i)+" - "+songs.get(m)+"").equals(song)){
                    artists.remove(i);
                    songs.remove(m);
                }
            }
        }

//        songs.removeIf(i -> i.equals(sng));
//        artists.removeIf(i -> i.equals(artst));
    }


    //for showing the song
    public static void printSong(){
        int counter = 1;
        for(int i = 0; i<songs.size(); i++){
            counter += 1;
            ta.append(""+(counter)+") "+artists.get(i)+" - "+songs.get(i)+"\n");
        }
    }


    //initiating out gui
    public static void menu(){
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);




        //text input panel
        JPanel panel = new JPanel();


        JMenuBar mebr = new JMenuBar();


        //create help button
        JMenuItem h = new JMenuItem("Help");
        h.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog help = new JDialog();
                JTextArea cmd = new JTextArea("include 'print' or 'show' in your input to see playlist\nInclude 'remove' to remove songs\ninclude 'add' to add songs");
                help.add(BorderLayout.CENTER,cmd);
                help.setSize(350,100);
                help.setVisible(true);
            }
        });

        mebr.add(h);








        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10){
                    String content = tf.getText();
                    for(String i: content.split(" ")){
                        i=i.toLowerCase();
                        if(i.equals("add")){
                            ta.append("ENTERED ADD MODE\n\n");
                            add.add(BorderLayout.NORTH,new JLabel("format: yourArtist - SongName"));
                            JButton b = new JButton ("OK");
                            b.addActionListener ( new ActionListener()
                            {
                                public void actionPerformed( ActionEvent e )
                                {
                                    add.setVisible(false);
                                    addSong(add_s.getText());
                                }
                            });


                            add.add(BorderLayout.SOUTH,b);
                            add.add(BorderLayout.CENTER,add_s);
                            add.setSize(300,200);
                            add.setVisible(true);
                        }


                        else if(i.equals("remove")){

                            ta.append("ENTERED DELETE MODE\n\n");
                            remove.add(BorderLayout.NORTH,new JLabel("format: yourArtist - SongName"));



                            remove.add(rem_s);
                            JButton b = new JButton ("OK");
                            b.addActionListener ( new ActionListener()
                            {
                                public void actionPerformed( ActionEvent e )
                                {
                                    remove.setVisible(false);
                                    removeSong(rem_s.getText());

                                }
                            });


                            remove.add(BorderLayout.SOUTH,b);
                            remove.add(BorderLayout.CENTER,rem_s);
                            remove.setSize(300,200);
                            remove.setVisible(true);

                        }
                        else if(i.equals("show") || i.equals("print")){
                            ta.append("PRINT MODE:\n");
                            printSong();
                        }

                        else if(i.equals("help")){
                            JDialog help = new JDialog();
                            JTextArea cmd = new JTextArea("include 'print' or 'show' in your input to see playlist\nInclude 'remove' to remove songs\ninclude 'add' to add songs");
                            help.add(BorderLayout.CENTER,cmd);
                            help.setSize(350,100);
                            help.setVisible(true);


                        }
                    }
                }
            };



        });



        panel.add(tf);





        // Text Area at the Center

        scroll = new JScrollPane(ta);
        //Adding Components to the frame

        frame.getContentPane().add(BorderLayout.NORTH,mebr);
        frame.getContentPane().add(BorderLayout.CENTER, scroll);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);

        
        frame.setVisible(true);




    }

}

