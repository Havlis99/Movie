package cz.uhk.fim.movies.gui;

import cz.uhk.fim.movies.model.Movie;
import cz.uhk.fim.movies.model.MovieType;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class MovieTableModel extends AbstractTableModel {

    private List<Movie> movieList;

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movieList.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie item = movieList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getTitle();
            case 1:
                return item.getYear();
            case 2:
                return item.getReleaseDate();
            case 3:
                return item.getRuntime();
            case 4:
                return item.getType();
            case 5:
                return item.getRatings().size() > 0 ? item.getRatings().get(0).getValue() : "-";
            case 6:
                return item.getRatings().size() > 1 ? item.getRatings().get(1).getValue() : "-";
            case 7:
                return item.getRatings().size() > 2 ? item.getRatings().get(2).getValue() : "-";
            default:
                return "?";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Název";
            case 1:
                return "Rok";
            case 2:
                return "Datum vydání";
            case 3:
                return "Doba trvání";
            case 4:
                return "Typ";
            case 5:
                return "Hodnocení 1";
            case 6:
                return "Hodnocení 2";
            case 7:
                return "Hodnocení 3";
            default:
                return "?";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 5:
            case 6:
            case 7:
                return String.class;
            case 2:
                return Date.class;
            case 3:
                return Integer.class;
            case 4:
                return MovieType.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
