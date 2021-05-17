
namespace Practical_Ex_2
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
            this.parentDgv = new System.Windows.Forms.DataGridView();
            this.childDgv = new System.Windows.Forms.DataGridView();
            this.btnUpdate = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.parentDgv)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.childDgv)).BeginInit();
            this.SuspendLayout();
            // 
            // parentDgv
            // 
            this.parentDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parentDgv.Location = new System.Drawing.Point(51, 146);
            this.parentDgv.Name = "parentDgv";
            this.parentDgv.RowHeadersWidth = 51;
            this.parentDgv.RowTemplate.Height = 24;
            this.parentDgv.Size = new System.Drawing.Size(550, 370);
            this.parentDgv.TabIndex = 0;
            // 
            // childDgv
            // 
            this.childDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childDgv.Location = new System.Drawing.Point(700, 146);
            this.childDgv.Name = "childDgv";
            this.childDgv.RowHeadersWidth = 51;
            this.childDgv.RowTemplate.Height = 24;
            this.childDgv.Size = new System.Drawing.Size(485, 370);
            this.childDgv.TabIndex = 1;
            this.childDgv.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView2_CellContentClick);
            // 
            // btnUpdate
            // 
            this.btnUpdate.Location = new System.Drawing.Point(176, 556);
            this.btnUpdate.Name = "btnUpdate";
            this.btnUpdate.Size = new System.Drawing.Size(123, 75);
            this.btnUpdate.TabIndex = 2;
            this.btnUpdate.Text = "UpdateDb";
            this.btnUpdate.UseVisualStyleBackColor = true;
            this.btnUpdate.Click += new System.EventHandler(this.btnUpdate_Click);
            // 
            // Form1
            // 
            this.ClientSize = new System.Drawing.Size(1249, 879);
            this.Controls.Add(this.btnUpdate);
            this.Controls.Add(this.childDgv);
            this.Controls.Add(this.parentDgv);
            this.Name = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load_1);
            ((System.ComponentModel.ISupportInitialize)(this.parentDgv)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.childDgv)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dgvParent;
        private System.Windows.Forms.DataGridView dgvChild;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button updateBtn;
        private System.Windows.Forms.Button refreshBtn;
        private System.Windows.Forms.DataGridView parentDgv;
        private System.Windows.Forms.DataGridView childDgv;
        private System.Windows.Forms.Button btnUpdate;
    }
}

