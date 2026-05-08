package view;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI class represents the graphical user interface of the Motorcycle Catalog application.
 * It is responsible for displaying the catalog, handling user interactions, and updating the view.
 * 
 * @version 3.1
 * @author Kamil Kotorc
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        catalogLabel = new javax.swing.JLabel();
        selectedCatalog = new javax.swing.JLabel();
        leftPanel = new javax.swing.JPanel();
        scrollPane1 = new javax.swing.JScrollPane();
        motoTable = new javax.swing.JTable();
        buttonLabel1 = new javax.swing.JLabel();
        removeButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        buttonLabel2 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputModel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inputPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputDisplacement = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inputPower = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        inputTypeBox = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        optionMenu = new javax.swing.JMenu();
        clearMenu = new javax.swing.JMenuItem();
        removeMenu = new javax.swing.JMenuItem();
        addMenu = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MotoCat");
        setMinimumSize(new java.awt.Dimension(860, 410));
        setPreferredSize(new java.awt.Dimension(870, 420));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        titleLabel.setText("Motorcycle Catalog Application");

        catalogLabel.setText("Catalog:");

        selectedCatalog.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        selectedCatalog.setText("defaultCatalog");

        leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Browse"));
        leftPanel.setToolTipText("Motorcycles");
        leftPanel.setMaximumSize(new java.awt.Dimension(500, 500));
        leftPanel.setMinimumSize(new java.awt.Dimension(200, 200));

        scrollPane1.setPreferredSize(new java.awt.Dimension(250, 250));

        motoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Model", "Price", "Displacement", "Power", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        motoTable.setToolTipText("Motorcycle table");
        motoTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        motoTable.setShowGrid(false);
        scrollPane1.setViewportView(motoTable);

        buttonLabel1.setText("Motorcycle options:");
        buttonLabel1.setToolTipText("");

        removeButton.setText("Remove");
        removeButton.setToolTipText("Remove motorcycle");
        removeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        editButton.setText("Edit");

        buttonLabel2.setText("Catalog options:");

        clearButton.setText("Clear catalog");
        clearButton.setToolTipText("Clear catalog");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editButton)
                    .addComponent(buttonLabel1)
                    .addComponent(removeButton)
                    .addComponent(buttonLabel2)
                    .addComponent(clearButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(buttonLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addGap(18, 18, 18)
                .addComponent(buttonLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        rightPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add"));
        rightPanel.setMaximumSize(new java.awt.Dimension(500, 500));
        rightPanel.setMinimumSize(new java.awt.Dimension(100, 100));

        jLabel1.setText("Model name:");

        inputModel.setToolTipText("Enter motorcycle model name");
        inputModel.setMinimumSize(new java.awt.Dimension(100, 22));
        inputModel.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel2.setText("Price in PLN:");

        inputPrice.setToolTipText("Enter motorcycle price");
        inputPrice.setMinimumSize(new java.awt.Dimension(100, 22));
        inputPrice.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel3.setText("Displacement in ccm:");

        inputDisplacement.setToolTipText("Enter motorcycle displacement");
        inputDisplacement.setMinimumSize(new java.awt.Dimension(100, 22));
        inputDisplacement.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel4.setText("Power in kW:");

        inputPower.setToolTipText("Enter motorcycle power");
        inputPower.setMinimumSize(new java.awt.Dimension(100, 22));
        inputPower.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel5.setText("Motocycle type:");

        inputTypeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2" }));

        addButton.setText("Add");
        addButton.setToolTipText("Add motorcycle");
        addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputTypeBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputDisplacement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputPower, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputDisplacement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(12, 12, 12)
                .addComponent(addButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        optionMenu.setText("Options");
        optionMenu.setToolTipText("Application options");

        clearMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        clearMenu.setText("Clear");
        clearMenu.setToolTipText("Remove all motorcycles from catalog");
        optionMenu.add(clearMenu);

        removeMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        removeMenu.setText("Remove");
        removeMenu.setToolTipText("Remove selected motorcycle");
        optionMenu.add(removeMenu);

        addMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addMenu.setText("Add");
        addMenu.setToolTipText("Add motorcycle to catalog");
        optionMenu.add(addMenu);

        editMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        editMenu.setText("Edit");
        editMenu.setToolTipText("Edit selected motorcycle");
        optionMenu.add(editMenu);

        menuBar.add(optionMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(catalogLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedCatalog))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedCatalog)
                    .addComponent(catalogLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // DIALOGS
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    public boolean showConfirmation(String message, String title) {
        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
        return option == JOptionPane.OK_OPTION;
    }
    
    public Map<String, String> showEditMessage(String title, String model, double price, int displacement, int power) {
        JTextField modelField = new JTextField(model);
        JTextField priceField = new JTextField(String.valueOf(price));
        JTextField displacementField = new JTextField(String.valueOf(displacement));
        JTextField powerField = new JTextField(String.valueOf(power));

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Model:"));
        panel.add(modelField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Displacement:"));
        panel.add(displacementField);
        panel.add(new JLabel("Power:"));
        panel.add(powerField);
        
        int option = JOptionPane.showConfirmDialog(
                this,
                panel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
        
        if(option == JOptionPane.OK_OPTION){ 
            Map<String, String> fieldValues = new HashMap<>();
            
            fieldValues.put("model", modelField.getText());
            fieldValues.put("price", priceField.getText());
            fieldValues.put("displacement", displacementField.getText());
            fieldValues.put("power", powerField.getText());   
            
            return fieldValues;
        }
        
        return null;
    }
    
    // METHODS
    
    /**
     * Sets the name of the selected catalog in the GUI.
     *
     * @param catalogName the name of the catalog to be displayed.
     */
    public void setSelectedCatalog(String catalogName) {
        selectedCatalog.setText(catalogName);
    }
    
    public void setTypeBox(Vector<String> list) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(list);
        inputTypeBox.setModel(comboBoxModel);
    }

    public Map<String, String> getInputValues() {
        Map<String, String> inputValues = new HashMap<>();

        inputValues.put("model", inputModel.getText());
        inputValues.put("price", inputPrice.getText());
        inputValues.put("displacement", inputDisplacement.getText());
        inputValues.put("power", inputPower.getText());
        inputValues.put("type", inputTypeBox.getSelectedItem().toString());

        return inputValues;
    }

    public void clearInputFields() {
        inputModel.setText("");
        inputPrice.setText("");
        inputDisplacement.setText("");
        inputPower.setText("");
        inputTypeBox.setSelectedIndex(0);
    }
 
    public int getSelectedTableRow() {
        return motoTable.getSelectedRow();
    }
    
    public void updateMotoTable(List<String[]> motorbikes) {
        DefaultTableModel tableModel = (DefaultTableModel) motoTable.getModel();
        tableModel.setRowCount(0);
        
        for (String[] motorbike : motorbikes) {
            tableModel.addRow(motorbike);
        }
    }
    
    // LISTENERS
    
    /**
     * Sets the action listener for the clear button. This listener will be triggered when the button is clicked.
     *
     * @param listener the action listener to be set for the clear button.
     */
    public void setClearButtonListener(java.awt.event.ActionListener listener) {
        clearButton.addActionListener(listener);
    }
    
    /**
     * Sets the action listener for the remove button. This listener will be triggered when the remove button is clicked.
     *
     * @param listener the action listener to be set for the remove button.
     */
    public void setRemoveButtonListener(java.awt.event.ActionListener listener) {
        removeButton.addActionListener(listener);
    }
    
    /**
     * Sets the action listener for the add button. This listener will be triggered when the add button is clicked.
     *
     * @param listener the action listener to be set for the add button.
     */
    public void setAddButtonListener(java.awt.event.ActionListener listener) {
        addButton.addActionListener(listener);
    }
    
    /**
     * Sets the action listener for the edit button. This listener will be triggered when the add button is clicked.
     *
     * @param listener the action listener to be set for the edit button.
     */
    public void setEditButtonListener(java.awt.event.ActionListener listener) {
        editButton.addActionListener(listener);
    }
    
    /**
     * Sets the action listener for the "Clear" menu item. This listener will be triggered when the "Clear" option is selected from the menu.
     *
     * @param listener the action listener to be set for the "Clear" menu item.
     */
    public void setClearMenuListener(java.awt.event.ActionListener listener) {
        clearMenu.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Remove" menu item. This listener will be triggered when the "Remove" option is selected from the menu.
     *
     * @param listener the action listener to be set for the "Remove" menu item.
     */
    public void setRemoveMenuListener(java.awt.event.ActionListener listener) {
        removeMenu.addActionListener(listener);
    }

    /**
     * Sets the action listener for the "Add" menu item. This listener will be triggered when the "Add" option is selected from the menu.
     *
     * @param listener the action listener to be set for the "Add" menu item.
     */
    public void setAddMenuListener(java.awt.event.ActionListener listener) {
        addMenu.addActionListener(listener);
    }
    
    /**
     * Sets the action listener for the "Edit" menu item. This listener will be triggered when the "Edit" option is selected from the menu.
     *
     * @param listener the action listener to be set for the "Edit" menu item.
     */
    public void setEditMenuListener(java.awt.event.ActionListener listener) {
        editMenu.addActionListener(listener);
    }
    
    /**
     * Sets the list selection listener for the motorbike table. This listener will be triggered when a selection in the table is made.
     *
     * @param listener the list selection listener to be set for the motorbike table.
     */
    public void setMotoTableSelectionListener(javax.swing.event.ListSelectionListener listener) {
        motoTable.getSelectionModel().addListSelectionListener(listener);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JMenuItem addMenu;
    private javax.swing.JLabel buttonLabel1;
    private javax.swing.JLabel buttonLabel2;
    private javax.swing.JLabel catalogLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JMenuItem clearMenu;
    private javax.swing.JButton editButton;
    private javax.swing.JMenuItem editMenu;
    private javax.swing.JTextField inputDisplacement;
    private javax.swing.JTextField inputModel;
    private javax.swing.JTextField inputPower;
    private javax.swing.JTextField inputPrice;
    private javax.swing.JComboBox<String> inputTypeBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTable motoTable;
    private javax.swing.JMenu optionMenu;
    private javax.swing.JButton removeButton;
    private javax.swing.JMenuItem removeMenu;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JLabel selectedCatalog;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
