
namespace Laboratory_2
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.parentDGV = new System.Windows.Forms.DataGridView();
            this.childDGV = new System.Windows.Forms.DataGridView();
            this.refreshBTN = new System.Windows.Forms.Button();
            this.updateBTN = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.parentDGV)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.childDGV)).BeginInit();
            this.SuspendLayout();
            // 
            // parentDGV
            // 
            this.parentDGV.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parentDGV.Location = new System.Drawing.Point(12, 115);
            this.parentDGV.Name = "parentDGV";
            this.parentDGV.RowHeadersWidth = 51;
            this.parentDGV.RowTemplate.Height = 29;
            this.parentDGV.Size = new System.Drawing.Size(530, 323);
            this.parentDGV.TabIndex = 0;
            // 
            // childDGV
            // 
            this.childDGV.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childDGV.Location = new System.Drawing.Point(617, 115);
            this.childDGV.Name = "childDGV";
            this.childDGV.RowHeadersWidth = 51;
            this.childDGV.RowTemplate.Height = 29;
            this.childDGV.Size = new System.Drawing.Size(530, 323);
            this.childDGV.TabIndex = 1;
            // 
            // refreshBTN
            // 
            this.refreshBTN.Location = new System.Drawing.Point(617, 463);
            this.refreshBTN.Name = "refreshBTN";
            this.refreshBTN.Size = new System.Drawing.Size(232, 68);
            this.refreshBTN.TabIndex = 2;
            this.refreshBTN.Text = "Refresh";
            this.refreshBTN.UseVisualStyleBackColor = true;
            this.refreshBTN.Click += new System.EventHandler(this.refreshBTN_Click);
            // 
            // updateBTN
            // 
            this.updateBTN.Location = new System.Drawing.Point(322, 463);
            this.updateBTN.Name = "updateBTN";
            this.updateBTN.Size = new System.Drawing.Size(219, 68);
            this.updateBTN.TabIndex = 3;
            this.updateBTN.Text = "Update";
            this.updateBTN.UseVisualStyleBackColor = true;
            this.updateBTN.Click += new System.EventHandler(this.updateBTN_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(617, 49);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(132, 31);
            this.label1.TabIndex = 4;
            this.label1.Text = "Child Table";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label2.Location = new System.Drawing.Point(12, 49);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(145, 31);
            this.label2.TabIndex = 5;
            this.label2.Text = "Parent Table";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1159, 571);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.updateBTN);
            this.Controls.Add(this.refreshBTN);
            this.Controls.Add(this.childDGV);
            this.Controls.Add(this.parentDGV);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.parentDGV)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.childDGV)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView parentDGV;
        private System.Windows.Forms.DataGridView childDGV;
        private System.Windows.Forms.Button refreshBTN;
        private System.Windows.Forms.Button updateBTN;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
    }
}

