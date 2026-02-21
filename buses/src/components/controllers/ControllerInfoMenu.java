/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components.controllers;

import ClassDAO.ViajeDAO;
import Tables.TablaProximosViajes;
import components.InfoMenu;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author alxyg
 */
public class ControllerInfoMenu {
    
    private InfoMenu vista;
    private final TablaProximosViajes tablaProximosViajes = new TablaProximosViajes();
    private final ViajeDAO modeloViaje = new ViajeDAO();
    
    public ControllerInfoMenu(InfoMenu vista) {
        this.vista = vista;
    }
    public void iniciar(){
        vista.setVisible(true);
        cargarTabla();
    }

    private void cargarTabla() {
        tablaProximosViajes.cargarTabla(vista.tablaProximosViajes, modeloViaje.listarProximosViajes());
    }

    

}
