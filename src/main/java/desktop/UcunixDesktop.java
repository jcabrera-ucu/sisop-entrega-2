package desktop;

import java.io.IOException;

import ucunix.Ucunix;
import uculang.CompilationError;

import java.io.File;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.SwingUtilities;
import ucunix.RoundRobinScheduler;

/**
 *
 */
public class UcunixDesktop extends javax.swing.JFrame {

    private final Ucunix ucunix;
    private int maxInstructionCountTimeout = 10;
    
    /**
     * Creates new form UcunixDesktop
     * 
     */
    public UcunixDesktop() {
        ucunix = new Ucunix(new RoundRobinScheduler());
        
        initComponents();        
        doLoop();
        
        programFilesTree.loadProgramFilesTree(new File("programs"), (File path) -> {
            loadProgram(path.getPath());
        });
    }
    
    private void doLoop() {
        Thread newThread = new Thread(() -> {
            while (true) {
                try {
                    SwingUtilities.invokeAndWait(() -> {
                        ucunix.update();
                        processesPanel.updatePrograms();
                    });
                } catch (InvocationTargetException | InterruptedException e) { 
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        });
        newThread.start();
    }

    
    private void loadProgram(String programFile) {
        try {
            var process = ucunix.createProcess(
                programFile, 
                Files.readString(Path.of(programFile)),
                null
            );
            
            var programWindow = new ProgramWindow(process.getPid(), ucunix.getScheduler());
            
            process.setConsole(programWindow.getConsoleWidget());
            
            processesPanel.addProgramWindow(programWindow);
        } catch (IOException e) { 
            // --
        } catch (CompilationError e) {
            var dialog = new CompilationErrorDialog(this, true, e);
            dialog.setVisible(true);
        }
    }
    
    private void onSemaphoresButtonClicked() {
        var frame = new SemaphoresViewerFrame();
        frame.setVisible(true);
    }
    
    
    private void onSharedVariablesButtonClicked() {
        var frame = new SharedVariablesViewerFrame();
        frame.setVisible(true);
    }
    
    private void updateMaxInstructionsCountTimeout(int value) {
        maxInstructionCountTimeout = value;
    }
    
    // ==========================================================
    // ==========================================================

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainHorizontalSplit = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        programFilesTree = new desktop.ProgramFilesTree();
        jPanel1 = new javax.swing.JPanel();
        semaphoresButton = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        sharedVariablesButton = new javax.swing.JButton();
        processesPanel = new desktop.ProcessesPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ucunix");

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setTopComponent(programFilesTree);

        semaphoresButton.setText("Semáforos");
        semaphoresButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSemaphoresButtonClicked(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(10, 1, null, 1));
        jSpinner1.setToolTipText("Cantidad máxima de instrucciones a ejecutar en cada proceso (timeout)");
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onMaxInstructionsStateChanged(evt);
            }
        });

        jLabel1.setText("Max Instrucciones:");

        sharedVariablesButton.setText("Variables Compartidas");
        sharedVariablesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sharedVariablesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(semaphoresButton, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(sharedVariablesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(semaphoresButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sharedVariablesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel1);

        mainHorizontalSplit.setLeftComponent(jSplitPane1);
        mainHorizontalSplit.setRightComponent(processesPanel);

        getContentPane().add(mainHorizontalSplit, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onSemaphoresButtonClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSemaphoresButtonClicked
        onSemaphoresButtonClicked();
    }//GEN-LAST:event_onSemaphoresButtonClicked

    private void onMaxInstructionsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onMaxInstructionsStateChanged
        // TODO add your handling code here:
        updateMaxInstructionsCountTimeout((Integer) jSpinner1.getModel().getValue());
    }//GEN-LAST:event_onMaxInstructionsStateChanged

    private void sharedVariablesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sharedVariablesButtonActionPerformed
        onSharedVariablesButtonClicked();
    }//GEN-LAST:event_sharedVariablesButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane mainHorizontalSplit;
    private desktop.ProcessesPanel processesPanel;
    private desktop.ProgramFilesTree programFilesTree;
    private javax.swing.JButton semaphoresButton;
    private javax.swing.JButton sharedVariablesButton;
    // End of variables declaration//GEN-END:variables
}