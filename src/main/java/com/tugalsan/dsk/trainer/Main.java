/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugalsan.dsk.trainer;

import com.tugalsan.api.cast.client.*;
import com.tugalsan.api.file.txt.server.*;
import com.tugalsan.api.input.server.*;
import com.tugalsan.api.log.server.*;
import com.tugalsan.api.shape.client.*;
import com.tugalsan.api.string.server.*;
import com.tugalsan.api.thread.server.async.TS_ThreadAsync;
import com.tugalsan.api.thread.server.TS_ThreadWait;
import com.tugalsan.api.thread.server.sync.TS_ThreadSyncTrigger;
import com.tugalsan.api.unsafe.client.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;

/**
 *
 * @author me
 */
public class Main extends javax.swing.JFrame {
    //cd C:\me\codes\com.tugalsan\trm\com.tugalsan.trm.trainer
    //java --enable-preview --add-modules jdk.incubator.vector -jar target/com.tugalsan.trm.trainer-1.0-SNAPSHOT-jar-with-dependencies.jar

    final private static TS_Log d = TS_Log.of(Main.class);
    final private static TS_ThreadSyncTrigger killTrigger = TS_ThreadSyncTrigger.of();

    /**
     * Creates new form NewJFrame
     */
    public Main() {
        initComponents();
        taCode.setText(testCode());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taCode = new javax.swing.JTextArea();
        btnRun = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        cbRepeat = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tuğalsan Karabacak's ASW Trainer");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStatus.setText("XY");
        getContentPane().add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 310, -1));

        jLabel3.setText("Status ->");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, -1));

        taCode.setColumns(20);
        taCode.setRows(5);
        taCode.setText("WAIT 5\nTYPE AKSAN\nCLICK_LEFT 100 100");
        jScrollPane1.setViewportView(taCode);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 260, 240));

        btnRun.setText("RUN");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });
        getContentPane().add(btnRun, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 100, -1));

        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 100, -1));

        btnLoad.setText("LOAD");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        getContentPane().add(btnLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 100, -1));

        cbRepeat.setText("RepeatAfter5");
        getContentPane().add(cbRepeat, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        setSize(new java.awt.Dimension(411, 326));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        TS_FileTxtUtils.toFile(taCode.getText(), Path.of("Trainer.macro"), false);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        taCode.setText(TS_FileTxtUtils.toString(Path.of("Trainer.macro")));
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        // TODO add your handling code here:
        TS_ThreadAsync.now(killTrigger, rt -> {
            var codeAll = taCode.getText();
            var codeLines = TS_StringUtils.toList(codeAll, "\n");
            codeLines.stream().forEachOrdered(codeLine -> execute(codeLine));
            while (cbRepeat.isSelected()) {
                TS_ThreadWait.seconds(d.className, killTrigger, 5);
                codeLines.stream().forEachOrdered(codeLine -> execute(codeLine));
            }
        });
    }//GEN-LAST:event_btnRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        Arrays.stream(javax.swing.UIManager.getInstalledLookAndFeels())
                .filter(info -> "Nimbus".equals(info.getName()))
                .forEach(info -> TGS_UnSafe.run(() -> javax.swing.UIManager.setLookAndFeel(info.getClassName()), e -> d.ct("main", e)));
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                var w = new Main();
                w.setVisible(true);
                TS_ThreadAsync.now(killTrigger, kt -> {
                    TS_ThreadWait.seconds(d.className, killTrigger, 1);
                    w.lblStatus.setText(TS_InputMouseUtils.getLocation().toString());
                });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnRun;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbRepeat;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea taCode;
    // End of variables declaration//GEN-END:variables

    private void execute(String codeLine) {
        if (codeLine.isEmpty()) {
            return;
        }
        if (codeLine.startsWith(CODE_WAIT)) {
            var tags = TS_StringUtils.toList_spc(codeLine);
            if (tags.size() != 2) {
                JOptionPane.showMessageDialog(null, "Code should have 2 tokens->[" + codeLine + "]");
                return;
            }
            var secStr = tags.get(1);
            Integer sec = TGS_CastUtils.toInteger(secStr);
            if (sec == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse sec to Integer->[" + secStr + "]");
                return;
            }
            TS_ThreadWait.seconds(d.className, killTrigger, sec);
        } else if (codeLine.startsWith(CODE_TYPE)) {
            var text = codeLine.substring(CODE_TYPE.length() + 1);
            TS_InputKeyboardUtils.typeString(text);
        } else if (codeLine.startsWith(CODE_MOVE)) {
            var tags = TS_StringUtils.toList_spc(codeLine);
            if (tags.size() != 3) {
                JOptionPane.showMessageDialog(null, "Code should have 3 tokens->[" + codeLine + "]");
                return;
            }
            Integer x = TGS_CastUtils.toInteger(tags.get(1));
            if (x == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse x to Integer->[" + tags.get(1) + "]");
                return;
            }
            Integer y = TGS_CastUtils.toInteger(tags.get(2));
            if (y == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse y to Integer->[" + tags.get(2) + "]");
                return;
            }
            TS_InputMouseUtils.mouseMove(new TGS_ShapeLocation(x, y));
        } else if (codeLine.startsWith(CODE_PRESS_LEFT)) {
            var tags = TS_StringUtils.toList_spc(codeLine);
            if (tags.size() != 3) {
                JOptionPane.showMessageDialog(null, "Code should have 3 tokens->[" + codeLine + "]");
                return;
            }
            Integer x = TGS_CastUtils.toInteger(tags.get(1));
            if (x == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse x to Integer->[" + tags.get(1) + "]");
                return;
            }
            Integer y = TGS_CastUtils.toInteger(tags.get(2));
            if (y == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse y to Integer->[" + tags.get(2) + "]");
                return;
            }
            TS_InputMouseUtils.mousePressLeft();
        } else if (codeLine.startsWith(CODE_RELEASE_LEFT)) {
            var tags = TS_StringUtils.toList_spc(codeLine);
            if (tags.size() != 3) {
                JOptionPane.showMessageDialog(null, "Code should have 3 tokens->[" + codeLine + "]");
                return;
            }
            Integer x = TGS_CastUtils.toInteger(tags.get(1));
            if (x == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse x to Integer->[" + tags.get(1) + "]");
                return;
            }
            Integer y = TGS_CastUtils.toInteger(tags.get(2));
            if (y == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse y to Integer->[" + tags.get(2) + "]");
                return;
            }
            TS_InputMouseUtils.mouseClickLeft(new TGS_ShapeLocation(x, y));
        } else if (codeLine.startsWith(CODE_CLICK_LEFT)) {
            var tags = TS_StringUtils.toList_spc(codeLine);
            if (tags.size() != 3) {
                JOptionPane.showMessageDialog(null, "Code should have 3 tokens->[" + codeLine + "]");
                return;
            }
            Integer x = TGS_CastUtils.toInteger(tags.get(1));
            if (x == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse x to Integer->[" + tags.get(1) + "]");
                return;
            }
            Integer y = TGS_CastUtils.toInteger(tags.get(2));
            if (y == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse y to Integer->[" + tags.get(2) + "]");
                return;
            }
            TS_InputMouseUtils.mouseClickLeft(new TGS_ShapeLocation(x, y));
        } else if (codeLine.startsWith(CODE_CLICK_RIGHT)) {
            var tags = TS_StringUtils.toList_spc(codeLine);
            if (tags.size() != 3) {
                JOptionPane.showMessageDialog(null, "Code should have 3 tokens->[" + codeLine + "]");
                return;
            }
            Integer x = TGS_CastUtils.toInteger(tags.get(1));
            if (x == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse x to Integer->[" + tags.get(1) + "]");
                return;
            }
            Integer y = TGS_CastUtils.toInteger(tags.get(2));
            if (y == null) {
                JOptionPane.showMessageDialog(null, "Cannot parse y to Integer->[" + tags.get(2) + "]");
                return;
            }
            TS_InputMouseUtils.mouseClickRight(new TGS_ShapeLocation(x, y));
        }
    }
    public static String CODE_TYPE = "TYPE";
    public static String CODE_WAIT = "WAIT";
    public static String CODE_PRESS_LEFT = "PRESS_LEFT";
    public static String CODE_RELEASE_LEFT = "RELEASE_LEFT";
    public static String CODE_CLICK_LEFT = "CLICK_LEFT";
    public static String CODE_CLICK_RIGHT = "CLICK_RIGHT";
    public static String CODE_MOVE = "MOVE";

    public static String testCode(int i) {
        return """
               CLICK_LEFT -310 100
               WAIT 1
               CLICK_LEFT -310 160
               WAIT 2
               
               CLICK_LEFT -700 215
               CLICK_LEFT -580 250
               WAIT 1
               TYPE fai
               CLICK_LEFT -580 290
               CLICK_LEFT -580 290
               WAIT 1
               TYPE BOGUS
               CLICK_LEFT -580 290
               CLICK_LEFT -580 290
               WAIT 1
               TYPE %d
               CLICK_LEFT -580 350
               TYPE %d
               
               CLICK_LEFT -370 390
               WAIT 1
               CLICK_LEFT -220 480
               WAIT 1
               CLICK_LEFT -450 460
               WAIT 1
               CLICK_LEFT -450 520
               WAIT 1
               
               
               
               
               """.formatted(i, i);
    }

    public static String testCode() {
        return IntStream.range(165, 180)
                .mapToObj(i -> testCode(i))
                .collect(Collectors.joining(""));
    }
}
