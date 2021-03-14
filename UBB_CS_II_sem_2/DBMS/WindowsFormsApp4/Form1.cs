using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace WindowsFormsApp4
{
    public partial class Form1 : Form
    {
        SqlConnection dbConn;
        SqlDataAdapter daShips, daPirates;
        DataSet ds;
        BindingSource bsShips, bsPirates;
        SqlCommandBuilder cbPirates;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            dbConn = new SqlConnection("Data Source = DESKTOP-97FIB3Q\\SQLEXPRESS;" +
                "Initial Catalog = Northwind;" +
                "Integrated Security = SSPI");
            ds = new DataSet();
            daShips = new SqlDataAdapter("SELECT * FROM Ships;", dbConn);
            daPirates = new SqlDataAdapter("SELECT * FROM Pirates;", dbConn);
            cbPirates = new SqlCommandBuilder(daPirates);
            daShips.Fill(ds, "Ships");
            daPirates.Fill(ds, "Pirates");

            DataRelation dr = new DataRelation("FK_Pirates_Ships",
                ds.Tables["Ships"].Columns["ShipID"],
                ds.Tables["Pirates"].Columns["ShipID"]);
            //Unique Constraint, ForeignKey Constraint
            //GetChildRows
            //GetParentRow
            ds.Relations.Add(dr);

            bsShips = new BindingSource();
            bsShips.DataSource = ds;
            bsShips.DataMember = "Ships";

            bsPirates = new BindingSource();
            bsPirates.DataSource = bsShips;
            bsPirates.DataMember = "FK_Pirates_Ships";

            dgvShips.DataSource = bsShips;
            dgvPirates.DataSource = bsPirates;
        }
        //SqlException add
        private void label1_Click(object sender, EventArgs e)
        {
           
        }

        private void button1_Click(object sender, EventArgs e)
        {
            daPirates.Update(ds, "Pirates");
        }
    }
}
