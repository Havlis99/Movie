package cz.uhk.fim.movies.gui;

import javax.swing.*;

public class MenuItem {
    private String name;
    private String type;
    private String parameter;
    private JMenuItem item;

    public MenuItem(String name, String type, String parameter, JMenuItem item){
        this.name = name;
        this.type = type;
        this.parameter = parameter;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public JMenuItem getItem() {
        return item;
    }

    public void setItem(JMenuItem item) {
        this.item = item;
    }
}
