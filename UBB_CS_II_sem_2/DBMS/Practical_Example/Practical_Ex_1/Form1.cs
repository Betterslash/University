using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Practical_Ex_1
{
    public partial class Form1 : Form
    {
        SqlConnection dbConn;
        SqlDataAdapter daChild, daParent;
        DataSet ds;
        BindingSource bsChild, bsParent;
        SqlCommandBuilder cbChild, cbParent;
        DataRelation dr;
        String childTable = "";
        String parentTable = "";
        String selectCommand = "SELECT * FROM ";
        String parentKey = "";
        String childKey = "";

        private void label1_Click(object sender, EventArgs e)
        {

        }

        public Form1()
        {
            InitializeComponent();
        }

        private void updateBtn_Click(object sender, EventArgs e)
        {
            daChild.Update(ds, childTable);
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            dbConn = new SqlConnection("Data Source = DESKTOP-97FIB3Q\\SQLEXPRESS;" +
               "Initial Catalog = practical_ex_1;" +
               "Integrated Security = SSPI");

            setParameters();

            initializeData();
        }

        private void initializeData()
        {
            ds = new DataSet();
            String commandChild = selectCommand + childTable + ";";
            String parentCommand = selectCommand + parentTable + ";";

            daParent = new SqlDataAdapter(parentCommand, dbConn);
            cbParent = new SqlCommandBuilder(daParent);

            daParent.Fill(ds, parentTable);
            daChild = new SqlDataAdapter(commandChild, dbConn);
            cbChild = new SqlCommandBuilder(daChild);
            daChild.Fill(ds, childTable);

            dr = new DataRelation("publisher_book_FK",
                    ds.Tables[parentTable].Columns[parentKey],
                    ds.Tables[childTable].Columns[childKey]);

            ds.Relations.Add(dr);
            
            bsParent = new BindingSource();
            bsParent.DataSource = ds;
            bsParent.DataMember = parentTable;

            bsChild = new BindingSource();
            bsChild.DataSource = bsParent;
            bsChild.DataMember = "publisher_book_FK";

            parentDgv.DataSource = bsParent;
            childDgv.DataSource = bsChild;
        }

        public void setParameters() {
            parentTable = "publisher";
            childTable = "book";
            parentKey = "id";
            childKey = "publisher_id";
        }
    }
}
