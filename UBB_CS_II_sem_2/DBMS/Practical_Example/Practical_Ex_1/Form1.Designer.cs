
namespace Practical_Ex_1
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
            this.childDgv = new System.Windows.Forms.DataGridView();
            this.parentDgv = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.updateBtn = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.childDgv)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.parentDgv)).BeginInit();
            this.SuspendLayout();
            // 
            // childDgv
            // 
            this.childDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childDgv.Location = new System.Drawing.Point(797, 59);
            this.childDgv.Name = "childDgv";
            this.childDgv.RowHeadersWidth = 51;
            this.childDgv.RowTemplate.Height = 24;
            this.childDgv.Size = new System.Drawing.Size(555, 312);
            this.childDgv.TabIndex = 0;
            // 
            // parentDgv
            // 
            this.parentDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parentDgv.Location = new System.Drawing.Point(140, 59);
            this.parentDgv.Name = "parentDgv";
            this.parentDgv.RowHeadersWidth = 51;
            this.parentDgv.RowTemplate.Height = 24;
            this.parentDgv.Size = new System.Drawing.Size(524, 312);
            this.parentDgv.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(146, 29);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(84, 21);
            this.label1.TabIndex = 2;
            this.label1.Text = "Publisher";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(813, 33);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(59, 21);
            this.label2.TabIndex = 3;
            this.label2.Text = "Books";
            // 
            // updateBtn
            // 
            this.updateBtn.Location = new System.Drawing.Point(215, 499);
            this.updateBtn.Name = "updateBtn";
            this.updateBtn.Size = new System.Drawing.Size(107, 43);
            this.updateBtn.TabIndex = 4;
            this.updateBtn.Text = "Update DB";
            this.updateBtn.UseVisualStyleBackColor = true;
            this.updateBtn.Click += new System.EventHandler(this.updateBtn_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1429, 711);
            this.Controls.Add(this.updateBtn);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.parentDgv);
            this.Controls.Add(this.childDgv);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.childDgv)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.parentDgv)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView childDgv;
        private System.Windows.Forms.DataGridView parentDgv;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button updateBtn;
    }
}

