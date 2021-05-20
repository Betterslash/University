
namespace Practical_Ex_seminar
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
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.updateBtn = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.parentDgv)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.childDgv)).BeginInit();
            this.SuspendLayout();
            // 
            // parentDgv
            // 
            this.parentDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parentDgv.Location = new System.Drawing.Point(78, 92);
            this.parentDgv.Name = "parentDgv";
            this.parentDgv.RowHeadersWidth = 51;
            this.parentDgv.RowTemplate.Height = 24;
            this.parentDgv.Size = new System.Drawing.Size(351, 232);
            this.parentDgv.TabIndex = 0;
            // 
            // childDgv
            // 
            this.childDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childDgv.Location = new System.Drawing.Point(562, 92);
            this.childDgv.Name = "childDgv";
            this.childDgv.RowHeadersWidth = 51;
            this.childDgv.RowTemplate.Height = 24;
            this.childDgv.Size = new System.Drawing.Size(337, 232);
            this.childDgv.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(75, 52);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(45, 17);
            this.label1.TabIndex = 2;
            this.label1.Text = "Users";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(559, 52);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(41, 17);
            this.label2.TabIndex = 3;
            this.label2.Text = "Likes";
            // 
            // updateBtn
            // 
            this.updateBtn.Location = new System.Drawing.Point(449, 399);
            this.updateBtn.Name = "updateBtn";
            this.updateBtn.Size = new System.Drawing.Size(120, 66);
            this.updateBtn.TabIndex = 4;
            this.updateBtn.Text = "Update DB";
            this.updateBtn.UseVisualStyleBackColor = true;
            this.updateBtn.Click += new System.EventHandler(this.button1_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(929, 588);
            this.Controls.Add(this.updateBtn);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.childDgv);
            this.Controls.Add(this.parentDgv);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.parentDgv)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.childDgv)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView parentDgv;
        private System.Windows.Forms.DataGridView childDgv;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button updateBtn;
    }
}

