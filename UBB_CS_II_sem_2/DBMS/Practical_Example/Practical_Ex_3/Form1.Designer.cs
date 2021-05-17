
namespace Practical_Ex_3
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
            this.parenOneDgv = new System.Windows.Forms.DataGridView();
            this.childDgv = new System.Windows.Forms.DataGridView();
            this.parentTwoDgv = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.updateBtn = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.parenOneDgv)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.childDgv)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.parentTwoDgv)).BeginInit();
            this.SuspendLayout();
            // 
            // parenOneDgv
            // 
            this.parenOneDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parenOneDgv.Location = new System.Drawing.Point(62, 125);
            this.parenOneDgv.Name = "parenOneDgv";
            this.parenOneDgv.RowHeadersWidth = 51;
            this.parenOneDgv.RowTemplate.Height = 24;
            this.parenOneDgv.Size = new System.Drawing.Size(365, 284);
            this.parenOneDgv.TabIndex = 0;
            // 
            // childDgv
            // 
            this.childDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childDgv.Location = new System.Drawing.Point(894, 125);
            this.childDgv.Name = "childDgv";
            this.childDgv.RowHeadersWidth = 51;
            this.childDgv.RowTemplate.Height = 24;
            this.childDgv.Size = new System.Drawing.Size(418, 284);
            this.childDgv.TabIndex = 1;
            // 
            // parentTwoDgv
            // 
            this.parentTwoDgv.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parentTwoDgv.Location = new System.Drawing.Point(466, 125);
            this.parentTwoDgv.Name = "parentTwoDgv";
            this.parentTwoDgv.RowHeadersWidth = 51;
            this.parentTwoDgv.RowTemplate.Height = 24;
            this.parentTwoDgv.Size = new System.Drawing.Size(389, 284);
            this.parentTwoDgv.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(59, 91);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(73, 21);
            this.label1.TabIndex = 3;
            this.label1.Text = "Lobbies";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(481, 91);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(71, 21);
            this.label2.TabIndex = 4;
            this.label2.Text = "Servant";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(891, 91);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(56, 21);
            this.label3.TabIndex = 5;
            this.label3.Text = "Order";
            // 
            // updateBtn
            // 
            this.updateBtn.Location = new System.Drawing.Point(551, 560);
            this.updateBtn.Name = "updateBtn";
            this.updateBtn.Size = new System.Drawing.Size(212, 81);
            this.updateBtn.TabIndex = 6;
            this.updateBtn.Text = "UPDATE DB";
            this.updateBtn.UseVisualStyleBackColor = true;
            this.updateBtn.Click += new System.EventHandler(this.updateBtn_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1350, 773);
            this.Controls.Add(this.updateBtn);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.parentTwoDgv);
            this.Controls.Add(this.childDgv);
            this.Controls.Add(this.parenOneDgv);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.parenOneDgv)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.childDgv)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.parentTwoDgv)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView parenOneDgv;
        private System.Windows.Forms.DataGridView childDgv;
        private System.Windows.Forms.DataGridView parentTwoDgv;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button updateBtn;
    }
}

