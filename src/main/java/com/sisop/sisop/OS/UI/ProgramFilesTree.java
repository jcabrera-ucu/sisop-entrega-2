package com.sisop.sisop.OS.UI;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 */
public class ProgramFilesTree extends javax.swing.JPanel {

    public interface OnOpenProgram {
        void onOpen(File path);
    }

    private OnOpenProgram callback;
    
    private File rootPath;

    /**
     * Creates new form ProgramFilesTree
     */
    public ProgramFilesTree() {
        initComponents();
    }
    
    public void loadProgramFilesTree(File rootPath, OnOpenProgram onOpen) {
        this.callback = onOpen;
        this.rootPath = rootPath;
        
        var rootNode = (DefaultMutableTreeNode) programFilesTree.getModel().getRoot();

        loadProgramFiles(rootNode, new File("programs"), true);

        for (int i = 0; i < programFilesTree.getRowCount(); i++) {
            programFilesTree.expandRow(i);
        }
    }

    private void loadProgramFiles(DefaultMutableTreeNode parentNode, File path, boolean isRoot) {
        if (path.isDirectory()) {
            DefaultMutableTreeNode node;
            
            if (!isRoot) {
                node = new DefaultMutableTreeNode(path.getName());
                parentNode.add(node);
            } else {
                node = parentNode;
            }

            for (var childPath : path.listFiles()) {
                loadProgramFiles(node, childPath, false);
            }
        } else {
            if (path.getPath().endsWith(".uculang")) {
                parentNode.add(new DefaultMutableTreeNode(path.getName()));
            }
        }
    }
    
    private void onMouseClicked(int x, int y) {
        TreePath tp = programFilesTree.getPathForLocation(x, y);
        if (tp != null) {            
            Path filePath = Paths.get(rootPath.getPath());
                    
            for (int i = 1; i < tp.getPathCount(); i++) {
                filePath = Paths.get(filePath.toString(), tp.getPathComponent(i).toString());
            }
            
            if (callback != null) {
                callback.onOpen(new File(filePath.toString()));
            }
        }
    }

    // =====================================================
    // =====================================================

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        programFilesScrollPane = new javax.swing.JScrollPane();
        programFilesTree = new javax.swing.JTree();

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Programas");
        programFilesTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        programFilesTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onMouseClicked(evt);
            }
        });
        programFilesScrollPane.setViewportView(programFilesTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(programFilesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(programFilesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void onMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onMouseClicked
        var point = evt.getPoint();
        onMouseClicked(point.x, point.y);
    }//GEN-LAST:event_onMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane programFilesScrollPane;
    private javax.swing.JTree programFilesTree;
    // End of variables declaration//GEN-END:variables
}
