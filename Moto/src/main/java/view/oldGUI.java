package view;

import java.util.List;
import javax.swing.DefaultListModel;
//import model.Motorbike;

/**
 * THIS IS OLD VERSION, NOT CURRENTLY USED,
 * The GUI class represents the graphical user interface of the Motorcycle Catalog application.
 * It is responsible for displaying the catalog, handling user interactions, and updating the view.
 * 
 * @version 3.0
 * @author Kamil Kotorc
 */
public class oldGUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public oldGUI() {
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
        scrollPane2 = new javax.swing.JScrollPane();
        modelList = new javax.swing.JList<>();
        titleLabel2 = new javax.swing.JLabel();
        selectedModel = new javax.swing.JLabel();
        selectedPrice = new javax.swing.JLabel();
        selectedDisplacement = new javax.swing.JLabel();
        selectedPower = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputModel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inputPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputDisplacement = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inputPower = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        optionMenu = new javax.swing.JMenu();
        menuClear = new javax.swing.JMenuItem();
        menuRemove = new javax.swing.JMenuItem();
        menuAdd = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MotoCat");
        setMinimumSize(new java.awt.Dimension(640, 360));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        titleLabel.setText("Motorcycle Catalog Application");

        catalogLabel.setText("Catalog:");

        selectedCatalog.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        selectedCatalog.setText("defaultCatalog");

        leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Browse"));
        leftPanel.setToolTipText("Motorcycles");
        leftPanel.setMaximumSize(new java.awt.Dimension(304, 304));
        leftPanel.setMinimumSize(new java.awt.Dimension(300, 300));

        modelList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        modelList.setToolTipText("Select motorcycle");
        modelList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        scrollPane2.setViewportView(modelList);

        titleLabel2.setText("Selected motorcycle details:");

        selectedModel.setText("Model");
        selectedModel.setToolTipText("Motorcycle model name");
        selectedModel.setEnabled(false);

        selectedPrice.setText("Price");
        selectedPrice.setToolTipText("Motorcycle price in PLN");
        selectedPrice.setEnabled(false);

        selectedDisplacement.setText("Displacement");
        selectedDisplacement.setToolTipText("Motorcycle displacement in ccm");
        selectedDisplacement.setEnabled(false);

        selectedPower.setText("Power");
        selectedPower.setToolTipText("Motorcycle power in kW");
        selectedPower.setEnabled(false);

        clearButton.setText("Clear catalog");
        clearButton.setToolTipText("Clear catalog");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        removeButton.setText("Remove");
        removeButton.setToolTipText("Remove motorcycle");
        removeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        editButton.setText("Edit");

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectedModel)
                    .addComponent(titleLabel2)
                    .addComponent(selectedPrice)
                    .addComponent(selectedDisplacement)
                    .addComponent(selectedPower)
                    .addComponent(removeButton)
                    .addComponent(editButton)
                    .addComponent(clearButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addComponent(titleLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedModel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedPrice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedDisplacement)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedPower)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        rightPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add"));

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
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputDisplacement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(11, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        optionMenu.setText("Options");
        optionMenu.setToolTipText("Application options");

        menuClear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuClear.setText("Clear");
        menuClear.setToolTipText("Remove all motorcycles from catalog");
        optionMenu.add(menuClear);

        menuRemove.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuRemove.setText("Remove");
        menuRemove.setToolTipText("Remove selected motorcycle");
        optionMenu.add(menuRemove);

        menuAdd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuAdd.setText("Add");
        menuAdd.setToolTipText("Add motorcycle to catalog");
        optionMenu.add(menuAdd);

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
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // METHODS
    
    public void setSelectedCatalog(String catalogName) {
        selectedCatalog.setText(catalogName);
    }
    
    /*
    public Motorbike getMotorbikeFromInputFields() {
        try {
            String model = inputModel.getText();
            double price = Double.parseDouble(inputPrice.getText());
            int displacement = Integer.parseInt(inputDisplacement.getText());
            int power = Integer.parseInt(inputPower.getText());
            return new Motorbike(model, price, displacement, power);
        } catch (NumberFormatException e) {
            return null;
        }
    }
     */
    
    public void clearInputFields() {
        inputModel.setText("");
        inputPrice.setText("");
        inputDisplacement.setText("");
        inputPower.setText("");
    }
    
    public void updateSelectedMotorbikeDetails(String brand, String model, String price, String displacement, String power) {
        selectedModel.setText(brand + " " + model);
        selectedModel.setEnabled(true);
        selectedPrice.setText(price + " PLN");
        selectedPrice.setEnabled(true);
        selectedDisplacement.setText(displacement + " ccm");
        selectedDisplacement.setEnabled(true);
        selectedPower.setText(power + " kW");
        selectedPower.setEnabled(true);
    }

    public void clearSelectedMotorbikeDetails() {
        selectedModel.setText("Model");
        selectedModel.setEnabled(false);
        selectedPrice.setText("Price");
        selectedPrice.setEnabled(false);
        selectedDisplacement.setText("Displacement");
        selectedDisplacement.setEnabled(false);
        selectedPower.setText("Power");
        selectedPower.setEnabled(false);
    }
    
    public void updateMotorbikeList(List<String> motorbikeNames) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String name : motorbikeNames) {
            listModel.addElement(name);
        }
        modelList.setModel(listModel);
    }

    public int getSelectedMotorbikeIndex() {
        return modelList.getSelectedIndex();
    }
    
    /*
    public void updateMotorbikeTable(List<Motorbike> motorbikes) {
    
        DefaultTableModel tableModel = (DefaultTableModel) modelTable.getModel();

        tableModel.setRowCount(0);
        
        for (Motorbike motorbike : motorbikes) {
            tableModel.addRow(new Object[]{motorbike.model(), motorbike.price()});
        }
    }
    */
    
    /*
    public void setMotorbikeSelectionListener(javax.swing.event.ListSelectionListener listener) {
        modelList.addListSelectionListener(listener);
        modelTable.getSelectionModel().addListSelectionListener(listener);
    }
    */
    
    // LISTENERS
    
    public void setClearButtonActionListener(java.awt.event.ActionListener listener) {
        clearButton.addActionListener(listener);
    }
    
    public void setRemoveButtonListener(java.awt.event.ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    public void setAddButtonListener(java.awt.event.ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void setEditButtonListener(java.awt.event.ActionListener listener) {
        editButton.addActionListener(listener);
    }
    
    public void setMenuClearActionListener(java.awt.event.ActionListener actionListener) {
        menuClear.addActionListener(actionListener);
    }

    public void setMenuRemoveActionListener(java.awt.event.ActionListener actionListener) {
        menuRemove.addActionListener(actionListener);
    }

    public void setMenuAddActionListener(java.awt.event.ActionListener actionListener) {
        menuAdd.addActionListener(actionListener);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel catalogLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField inputDisplacement;
    private javax.swing.JTextField inputModel;
    private javax.swing.JTextField inputPower;
    private javax.swing.JTextField inputPrice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JMenuItem menuAdd;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuClear;
    private javax.swing.JMenuItem menuRemove;
    private javax.swing.JList<String> modelList;
    private javax.swing.JMenu optionMenu;
    private javax.swing.JButton removeButton;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JLabel selectedCatalog;
    private javax.swing.JLabel selectedDisplacement;
    private javax.swing.JLabel selectedModel;
    private javax.swing.JLabel selectedPower;
    private javax.swing.JLabel selectedPrice;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleLabel2;
    // End of variables declaration//GEN-END:variables
}
