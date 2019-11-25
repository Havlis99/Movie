package cz.uhk.fim.movies.gui;

import cz.uhk.fim.movies.model.*;
import cz.uhk.fim.movies.util.FileUtils;
import cz.uhk.fim.movies.util.MovieParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private List<Movie> movies;
    private JLabel lblPName = new JLabel("Název:");
    private JLabel lblPYear = new JLabel("Rok:");
    private JLabel lblPType = new JLabel("Typ:");
    private JLabel lblPoster = new JLabel();

    public MainFrame() {
        initUI();
        initFrame();
//        initTestData();
    }

    private void initFrame() {
        setTitle("Movies Search");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screenSize.width * 0.75), (int) (screenSize.height * 0.75));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void initUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panel.setPreferredSize(new Dimension(200, 150));
        panel.setBorder(BorderFactory.createTitledBorder("Hlavní panel"));

        JLabel lblName = new JLabel("Název:");
        panel.add(lblName);
        lblName.setFont(new Font("font", Font.BOLD, 25));

        JTextField txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(250, 35));
        panel.add(txtName);
        txtName.setFont(new Font("font", Font.PLAIN, 25));

        JLabel lblSearchYear = new JLabel("Rok:");
        lblSearchYear.setFont(new Font("font", Font.BOLD, 25));
        panel.add(lblSearchYear);
        lblSearchYear.setVisible(false);

        JTextField txtSearchYear = new JTextField();
        txtSearchYear.setPreferredSize(new Dimension(150, 35));
        txtSearchYear.setFont(new Font("font", Font.PLAIN, 25));
        panel.add(txtSearchYear);
        txtSearchYear.setVisible(false);

        JButton btnSearch = new JButton("Vyhledat");
        panel.add(btnSearch);
        btnSearch.setPreferredSize(new Dimension(150, 35));
        btnSearch.setFont(new Font("font", Font.BOLD, 20));


        JCheckBox chBYear = new JCheckBox();
        panel.add(chBYear);
        JLabel lblYear = new JLabel("Vyhledat podle roku");
        lblYear.setFont(new Font("font", Font.PLAIN, 15));
        panel.add(lblYear);


        chBYear.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    lblSearchYear.setVisible(true);
                    txtSearchYear.setVisible(true);
                } else {
                    lblSearchYear.setVisible(false);
                    txtSearchYear.setVisible(false);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setPreferredSize(new Dimension((int) (screenSize.getWidth() * 0.75) / 2, 250));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Button panel"));

        JButton btnAdd = new JButton("Přidat do seznamu");
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 170)));
        buttonPanel.add(btnAdd);
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.setMaximumSize(new Dimension(250, 40));
        btnAdd.setFont(new Font("font", Font.BOLD, 15));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JButton btnNext = new JButton("Přeskočit");
        buttonPanel.add(btnNext);
        btnNext.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNext.setMaximumSize(new Dimension(250, 40));
        btnNext.setFont(new Font("font", Font.BOLD, 15));

        JPanel printPanel = new JPanel();
        printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.PAGE_AXIS));
        printPanel.setPreferredSize(new Dimension((int) (screenSize.getWidth() * 0.75) / 2, 250));
        printPanel.setBorder(BorderFactory.createTitledBorder("Print panel"));

        lblPName.setFont(new Font("font", Font.BOLD, 18));
        lblPYear.setFont(new Font("font", Font.PLAIN, 15));
        lblPType.setFont(new Font("font", Font.ITALIC, 15));


        printPanel.add(lblPName);
        printPanel.add(lblPYear);
        printPanel.add(lblPType);
        printPanel.add(lblPoster);

        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);
        add(printPanel, BorderLayout.WEST);

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                for (Movie m : MovieParser.parseMovieSearch(txtName.getText().trim())) {
//                    BufferedImage image = (BufferedImage) m.getPoster();
//                    lblPName.setText(m.getTitle());
//                    lblPYear.setText(m.getYear());
//                    lblPoster.setIcon(new ImageIcon(image));
//                }
                if (!txtName.getText().isEmpty()) {
                    movies = MovieParser.parseMovieSearch(txtName.getText().trim());
                    setDataToUi();
                }
            }
        });

        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!movies.isEmpty()){
                    addMovie(movies.get(0));
                }
                setDataToUi();
            }
        });

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!movies.isEmpty()){
                    nextMovie(movies.get(0));
                }
                setDataToUi();
            }
        });


    }

    private void setDataToUi() {
        if (!movies.isEmpty()) {
            Movie m = movies.get(0);
            lblPName.setText(m.getTitle());
            lblPYear.setText(m.getYear());
            lblPType.setText(m.getType().getType());
            lblPoster.setIcon(new ImageIcon(m.getPoster()));
        } else {
            lblPName.setVisible(false);
            lblPYear.setVisible(false);
            lblPType.setVisible(false);
            lblPoster.setVisible(false);
        }
    }

    private void addMovie(Movie m) {
        System.out.println(m);
        try {
            FileUtils.saveStringToFile(m.getMovieId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        movies.remove(m);
    }

    private void nextMovie(Movie m){
        System.out.println(m);
        movies.remove(m);
    }


    private void initTestData() {

        //MovieParser.parseMovieSearch("Iron man");
        List<Genre> genres = new ArrayList<>(4);
        genres.add(new Genre("Sci-fi"));
        genres.add(new Genre("Action"));
        genres.add(new Genre("Adveture"));
        genres.add(new Genre("Fantasy"));

        List<Actor> actors = new ArrayList<>(3);
        actors.add(new Actor("Mark Hammil"));
        actors.add(new Actor("Harrison Ford"));
        actors.add(new Actor("Carrie Fisher"));

        List<Rating> ratings = new ArrayList<>(3);
        ratings.add(new Rating("Internet Movie Database", "8.6/10"));
        ratings.add(new Rating("Rotten Tomatoes", "93%"));
        ratings.add(new Rating("Metacritic", "90/100"));


//        Movie m1 = new Movie("Star Wars IV","1997", DataHandler.getDateFromString("25 MAY 1997"),121,genres,"George Lucas","George Lucas",actors,"...","USA",
//                "ENG", ImageHandler.getImageFromUrl("https://lumiere-a.akamaihd.net/v1/images/star-wars-the-rise-of-skywalker-theatrical-poster-600_889ecbb6.jpeg?region=0%2C0%2C600%2C889)"),
//                MovieType.MOVIE);

        //System.out.println(m1);
    }
}
