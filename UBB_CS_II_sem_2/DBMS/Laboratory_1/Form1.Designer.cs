
namespace Laboratory_1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.gADBDataSet = new Laboratory_1.GADBDataSet();
            this.genre_tableBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.genre_tableTableAdapter = new Laboratory_1.GADBDataSetTableAdapters.genre_tableTableAdapter();
            this.tableAdapterManager = new Laboratory_1.GADBDataSetTableAdapters.TableAdapterManager();
            this.games_tableTableAdapter = new Laboratory_1.GADBDataSetTableAdapters.games_tableTableAdapter();
            this.genre_tableBindingNavigator = new System.Windows.Forms.BindingNavigator(this.components);
            this.bindingNavigatorAddNewItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorCountItem = new System.Windows.Forms.ToolStripLabel();
            this.bindingNavigatorDeleteItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorMoveFirstItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorMovePreviousItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorSeparator = new System.Windows.Forms.ToolStripSeparator();
            this.bindingNavigatorPositionItem = new System.Windows.Forms.ToolStripTextBox();
            this.bindingNavigatorSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.bindingNavigatorMoveNextItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorMoveLastItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.genre_tableBindingNavigatorSaveItem = new System.Windows.Forms.ToolStripButton();
            this.geDgv = new System.Windows.Forms.DataGridView();
            this.dataGridViewTextBoxColumn1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn2 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn3 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.games_tableBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gaDgv = new System.Windows.Forms.DataGridView();
            this.dataGridViewTextBoxColumn4 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn5 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn6 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataGridViewTextBoxColumn7 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.updateBtn = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.gADBDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.genre_tableBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.genre_tableBindingNavigator)).BeginInit();
            this.genre_tableBindingNavigator.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.geDgv)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.games_tableBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gaDgv)).BeginInit();
            this.SuspendLayout();
            // 
            // gADBDataSet
            // 
            this.gADBDataSet.DataSetName = "GADBDataSet";
            this.gADBDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // genre_tableBindingSource
            // 
            this.genre_tableBindingSource.DataMember = "genre_table";
            this.genre_tableBindingSource.DataSource = this.gADBDataSet;
            // 
            // genre_tableTableAdapter
            // 
            this.genre_tableTableAdapter.ClearBeforeFill = true;
            // 
            // tableAdapterManager
            // 
            this.tableAdapterManager.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager.games_tableTableAdapter = this.games_tableTableAdapter;
            this.tableAdapterManager.genre_tableTableAdapter = this.genre_tableTableAdapter;
            this.tableAdapterManager.UpdateOrder = Laboratory_1.GADBDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // games_tableTableAdapter
            // 
            this.games_tableTableAdapter.ClearBeforeFill = true;
            // 
            // genre_tableBindingNavigator
            // 
            this.genre_tableBindingNavigator.AddNewItem = this.bindingNavigatorAddNewItem;
            this.genre_tableBindingNavigator.BindingSource = this.genre_tableBindingSource;
            this.genre_tableBindingNavigator.CountItem = this.bindingNavigatorCountItem;
            this.genre_tableBindingNavigator.DeleteItem = this.bindingNavigatorDeleteItem;
            this.genre_tableBindingNavigator.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.genre_tableBindingNavigator.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.bindingNavigatorMoveFirstItem,
            this.bindingNavigatorMovePreviousItem,
            this.bindingNavigatorSeparator,
            this.bindingNavigatorPositionItem,
            this.bindingNavigatorCountItem,
            this.bindingNavigatorSeparator1,
            this.bindingNavigatorMoveNextItem,
            this.bindingNavigatorMoveLastItem,
            this.bindingNavigatorSeparator2,
            this.bindingNavigatorAddNewItem,
            this.bindingNavigatorDeleteItem,
            this.genre_tableBindingNavigatorSaveItem});
            this.genre_tableBindingNavigator.Location = new System.Drawing.Point(0, 0);
            this.genre_tableBindingNavigator.MoveFirstItem = this.bindingNavigatorMoveFirstItem;
            this.genre_tableBindingNavigator.MoveLastItem = this.bindingNavigatorMoveLastItem;
            this.genre_tableBindingNavigator.MoveNextItem = this.bindingNavigatorMoveNextItem;
            this.genre_tableBindingNavigator.MovePreviousItem = this.bindingNavigatorMovePreviousItem;
            this.genre_tableBindingNavigator.Name = "genre_tableBindingNavigator";
            this.genre_tableBindingNavigator.PositionItem = this.bindingNavigatorPositionItem;
            this.genre_tableBindingNavigator.Size = new System.Drawing.Size(1093, 27);
            this.genre_tableBindingNavigator.TabIndex = 0;
            this.genre_tableBindingNavigator.Text = "bindingNavigator1";
            // 
            // bindingNavigatorAddNewItem
            // 
            this.bindingNavigatorAddNewItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorAddNewItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorAddNewItem.Image")));
            this.bindingNavigatorAddNewItem.Name = "bindingNavigatorAddNewItem";
            this.bindingNavigatorAddNewItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorAddNewItem.Size = new System.Drawing.Size(29, 24);
            this.bindingNavigatorAddNewItem.Text = "Add new";
            // 
            // bindingNavigatorCountItem
            // 
            this.bindingNavigatorCountItem.Name = "bindingNavigatorCountItem";
            this.bindingNavigatorCountItem.Size = new System.Drawing.Size(45, 24);
            this.bindingNavigatorCountItem.Text = "of {0}";
            this.bindingNavigatorCountItem.ToolTipText = "Total number of items";
            // 
            // bindingNavigatorDeleteItem
            // 
            this.bindingNavigatorDeleteItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorDeleteItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorDeleteItem.Image")));
            this.bindingNavigatorDeleteItem.Name = "bindingNavigatorDeleteItem";
            this.bindingNavigatorDeleteItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorDeleteItem.Size = new System.Drawing.Size(29, 24);
            this.bindingNavigatorDeleteItem.Text = "Delete";
            // 
            // bindingNavigatorMoveFirstItem
            // 
            this.bindingNavigatorMoveFirstItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorMoveFirstItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorMoveFirstItem.Image")));
            this.bindingNavigatorMoveFirstItem.Name = "bindingNavigatorMoveFirstItem";
            this.bindingNavigatorMoveFirstItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorMoveFirstItem.Size = new System.Drawing.Size(29, 24);
            this.bindingNavigatorMoveFirstItem.Text = "Move first";
            // 
            // bindingNavigatorMovePreviousItem
            // 
            this.bindingNavigatorMovePreviousItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorMovePreviousItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorMovePreviousItem.Image")));
            this.bindingNavigatorMovePreviousItem.Name = "bindingNavigatorMovePreviousItem";
            this.bindingNavigatorMovePreviousItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorMovePreviousItem.Size = new System.Drawing.Size(29, 24);
            this.bindingNavigatorMovePreviousItem.Text = "Move previous";
            // 
            // bindingNavigatorSeparator
            // 
            this.bindingNavigatorSeparator.Name = "bindingNavigatorSeparator";
            this.bindingNavigatorSeparator.Size = new System.Drawing.Size(6, 27);
            // 
            // bindingNavigatorPositionItem
            // 
            this.bindingNavigatorPositionItem.AccessibleName = "Position";
            this.bindingNavigatorPositionItem.AutoSize = false;
            this.bindingNavigatorPositionItem.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.bindingNavigatorPositionItem.Name = "bindingNavigatorPositionItem";
            this.bindingNavigatorPositionItem.Size = new System.Drawing.Size(50, 27);
            this.bindingNavigatorPositionItem.Text = "0";
            this.bindingNavigatorPositionItem.ToolTipText = "Current position";
            // 
            // bindingNavigatorSeparator1
            // 
            this.bindingNavigatorSeparator1.Name = "bindingNavigatorSeparator1";
            this.bindingNavigatorSeparator1.Size = new System.Drawing.Size(6, 27);
            // 
            // bindingNavigatorMoveNextItem
            // 
            this.bindingNavigatorMoveNextItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorMoveNextItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorMoveNextItem.Image")));
            this.bindingNavigatorMoveNextItem.Name = "bindingNavigatorMoveNextItem";
            this.bindingNavigatorMoveNextItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorMoveNextItem.Size = new System.Drawing.Size(29, 24);
            this.bindingNavigatorMoveNextItem.Text = "Move next";
            // 
            // bindingNavigatorMoveLastItem
            // 
            this.bindingNavigatorMoveLastItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorMoveLastItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorMoveLastItem.Image")));
            this.bindingNavigatorMoveLastItem.Name = "bindingNavigatorMoveLastItem";
            this.bindingNavigatorMoveLastItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorMoveLastItem.Size = new System.Drawing.Size(29, 24);
            this.bindingNavigatorMoveLastItem.Text = "Move last";
            // 
            // bindingNavigatorSeparator2
            // 
            this.bindingNavigatorSeparator2.Name = "bindingNavigatorSeparator2";
            this.bindingNavigatorSeparator2.Size = new System.Drawing.Size(6, 27);
            // 
            // genre_tableBindingNavigatorSaveItem
            // 
            this.genre_tableBindingNavigatorSaveItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.genre_tableBindingNavigatorSaveItem.Image = ((System.Drawing.Image)(resources.GetObject("genre_tableBindingNavigatorSaveItem.Image")));
            this.genre_tableBindingNavigatorSaveItem.Name = "genre_tableBindingNavigatorSaveItem";
            this.genre_tableBindingNavigatorSaveItem.Size = new System.Drawing.Size(29, 24);
            this.genre_tableBindingNavigatorSaveItem.Text = "Save Data";
            this.genre_tableBindingNavigatorSaveItem.Click += new System.EventHandler(this.genre_tableBindingNavigatorSaveItem_Click);
            // 
            // geDgv
            // 
            this.geDgv.AutoGenerateColumns = false;
            this.geDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.geDgv.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dataGridViewTextBoxColumn1,
            this.dataGridViewTextBoxColumn2,
            this.dataGridViewTextBoxColumn3});
            this.geDgv.DataSource = this.genre_tableBindingSource;
            this.geDgv.Location = new System.Drawing.Point(12, 30);
            this.geDgv.Name = "geDgv";
            this.geDgv.RowHeadersWidth = 51;
            this.geDgv.RowTemplate.Height = 24;
            this.geDgv.Size = new System.Drawing.Size(429, 220);
            this.geDgv.TabIndex = 1;
            // 
            // dataGridViewTextBoxColumn1
            // 
            this.dataGridViewTextBoxColumn1.DataPropertyName = "id";
            this.dataGridViewTextBoxColumn1.HeaderText = "id";
            this.dataGridViewTextBoxColumn1.MinimumWidth = 6;
            this.dataGridViewTextBoxColumn1.Name = "dataGridViewTextBoxColumn1";
            this.dataGridViewTextBoxColumn1.Width = 125;
            // 
            // dataGridViewTextBoxColumn2
            // 
            this.dataGridViewTextBoxColumn2.DataPropertyName = "genreName";
            this.dataGridViewTextBoxColumn2.HeaderText = "genreName";
            this.dataGridViewTextBoxColumn2.MinimumWidth = 6;
            this.dataGridViewTextBoxColumn2.Name = "dataGridViewTextBoxColumn2";
            this.dataGridViewTextBoxColumn2.Width = 125;
            // 
            // dataGridViewTextBoxColumn3
            // 
            this.dataGridViewTextBoxColumn3.DataPropertyName = "allowenceAge";
            this.dataGridViewTextBoxColumn3.HeaderText = "allowenceAge";
            this.dataGridViewTextBoxColumn3.MinimumWidth = 6;
            this.dataGridViewTextBoxColumn3.Name = "dataGridViewTextBoxColumn3";
            this.dataGridViewTextBoxColumn3.Width = 125;
            // 
            // games_tableBindingSource
            // 
            this.games_tableBindingSource.DataMember = "FK__games_tab__genre__412EB0B6";
            this.games_tableBindingSource.DataSource = this.genre_tableBindingSource;
            // 
            // gaDgv
            // 
            this.gaDgv.AutoGenerateColumns = false;
            this.gaDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.gaDgv.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dataGridViewTextBoxColumn4,
            this.dataGridViewTextBoxColumn5,
            this.dataGridViewTextBoxColumn6,
            this.dataGridViewTextBoxColumn7});
            this.gaDgv.DataSource = this.games_tableBindingSource;
            this.gaDgv.Location = new System.Drawing.Point(12, 298);
            this.gaDgv.Name = "gaDgv";
            this.gaDgv.RowHeadersWidth = 51;
            this.gaDgv.RowTemplate.Height = 24;
            this.gaDgv.Size = new System.Drawing.Size(554, 220);
            this.gaDgv.TabIndex = 2;
            this.gaDgv.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.genreDgv_CellContentClick);
            // 
            // dataGridViewTextBoxColumn4
            // 
            this.dataGridViewTextBoxColumn4.DataPropertyName = "code";
            this.dataGridViewTextBoxColumn4.HeaderText = "code";
            this.dataGridViewTextBoxColumn4.MinimumWidth = 6;
            this.dataGridViewTextBoxColumn4.Name = "dataGridViewTextBoxColumn4";
            this.dataGridViewTextBoxColumn4.Width = 125;
            // 
            // dataGridViewTextBoxColumn5
            // 
            this.dataGridViewTextBoxColumn5.DataPropertyName = "gameName";
            this.dataGridViewTextBoxColumn5.HeaderText = "gameName";
            this.dataGridViewTextBoxColumn5.MinimumWidth = 6;
            this.dataGridViewTextBoxColumn5.Name = "dataGridViewTextBoxColumn5";
            this.dataGridViewTextBoxColumn5.Width = 125;
            // 
            // dataGridViewTextBoxColumn6
            // 
            this.dataGridViewTextBoxColumn6.DataPropertyName = "genreID";
            this.dataGridViewTextBoxColumn6.HeaderText = "genreID";
            this.dataGridViewTextBoxColumn6.MinimumWidth = 6;
            this.dataGridViewTextBoxColumn6.Name = "dataGridViewTextBoxColumn6";
            this.dataGridViewTextBoxColumn6.Width = 125;
            // 
            // dataGridViewTextBoxColumn7
            // 
            this.dataGridViewTextBoxColumn7.DataPropertyName = "price";
            this.dataGridViewTextBoxColumn7.HeaderText = "price";
            this.dataGridViewTextBoxColumn7.MinimumWidth = 6;
            this.dataGridViewTextBoxColumn7.Name = "dataGridViewTextBoxColumn7";
            this.dataGridViewTextBoxColumn7.Width = 125;
            // 
            // updateBtn
            // 
            this.updateBtn.Location = new System.Drawing.Point(763, 242);
            this.updateBtn.Name = "updateBtn";
            this.updateBtn.Size = new System.Drawing.Size(229, 61);
            this.updateBtn.TabIndex = 3;
            this.updateBtn.Text = "Update DB";
            this.updateBtn.UseVisualStyleBackColor = true;
            this.updateBtn.Click += new System.EventHandler(this.button1_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1093, 623);
            this.Controls.Add(this.updateBtn);
            this.Controls.Add(this.gaDgv);
            this.Controls.Add(this.geDgv);
            this.Controls.Add(this.genre_tableBindingNavigator);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.gADBDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.genre_tableBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.genre_tableBindingNavigator)).EndInit();
            this.genre_tableBindingNavigator.ResumeLayout(false);
            this.genre_tableBindingNavigator.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.geDgv)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.games_tableBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gaDgv)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private GADBDataSet gADBDataSet;
        private System.Windows.Forms.BindingSource genre_tableBindingSource;
        private GADBDataSetTableAdapters.genre_tableTableAdapter genre_tableTableAdapter;
        private GADBDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private System.Windows.Forms.BindingNavigator genre_tableBindingNavigator;
        private System.Windows.Forms.ToolStripButton bindingNavigatorAddNewItem;
        private System.Windows.Forms.ToolStripLabel bindingNavigatorCountItem;
        private System.Windows.Forms.ToolStripButton bindingNavigatorDeleteItem;
        private System.Windows.Forms.ToolStripButton bindingNavigatorMoveFirstItem;
        private System.Windows.Forms.ToolStripButton bindingNavigatorMovePreviousItem;
        private System.Windows.Forms.ToolStripSeparator bindingNavigatorSeparator;
        private System.Windows.Forms.ToolStripTextBox bindingNavigatorPositionItem;
        private System.Windows.Forms.ToolStripSeparator bindingNavigatorSeparator1;
        private System.Windows.Forms.ToolStripButton bindingNavigatorMoveNextItem;
        private System.Windows.Forms.ToolStripButton bindingNavigatorMoveLastItem;
        private System.Windows.Forms.ToolStripSeparator bindingNavigatorSeparator2;
        private System.Windows.Forms.ToolStripButton genre_tableBindingNavigatorSaveItem;
        private System.Windows.Forms.DataGridView geDgv;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn1;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn2;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn3;
        private GADBDataSetTableAdapters.games_tableTableAdapter games_tableTableAdapter;
        private System.Windows.Forms.BindingSource games_tableBindingSource;
        private System.Windows.Forms.DataGridView gaDgv;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn4;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn5;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn6;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn7;
        private System.Windows.Forms.Button updateBtn;
    }
}

