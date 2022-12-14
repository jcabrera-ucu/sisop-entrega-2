package desktop;

import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 */
public class ProcessesPanel extends javax.swing.JPanel {

    private final List<ProgramWindow> programWindows = new LinkedList<>();

    /**
     * Creates new form ProcessesPanel
     */
    public ProcessesPanel() {
        initComponents();
    }
    
    public void addProgramWindow(ProgramWindow window) {
        var container = new JPanel();
        
        container.setLayout(new FlowLayout());
        container.setBackground(processesPanel.getBackground());
        container.add(window);
        
        processesPanel.add(container);
        programWindows.add(window);

        window.setOnCloseCallback(() -> {
            processesPanel.remove(container);
            processesPanel.revalidate();
            processesPanel.repaint();
            window.killProcess();
        });

        revalidate();
        repaint();
    }
    
    public void updatePrograms() {
        for (var program : programWindows) {
            program.update();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        processesPanel = new javax.swing.JPanel();

        processesPanel.setBackground(new java.awt.Color(102, 102, 102));
        processesPanel.setLayout(new java.awt.GridLayout(0, 3, 3, 3));
        jScrollPane1.setViewportView(processesPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel processesPanel;
    // End of variables declaration//GEN-END:variables
}
