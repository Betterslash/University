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

namespace Practical_Ex_3
{
    public partial class Form1 : Form
    {
        SqlConnection dbConn;
        SqlDataAdapter daChild, daParentOne, daParentTwo;
        DataSet ds;
        BindingSource bsChild, bsParentOne, bsParentTwo;
        SqlCommandBuilder cbChild, cbParentOne, cbParentTwo;
        DataRelation dr, drTwo;
        String childTable = "lobby";
        String parentOneTable = "orders";
        String parentTwoTable = "servant";
        String selectCommand = "SELECT * FROM ";
        String parentOneKey = "id"; //"id";
        String parentTwoKey = "order_id";
        String childKey = "servant_id"; //"genreID";

        public Form1()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void updateBtn_Click(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            dbConn = new SqlConnection("Data Source = DESKTOP-97FIB3Q\\SQLEXPRESS;" +
               "Initial Catalog = practical_ex_3;" +
               "Integrated Security = SSPI");
            InitializeData();
        }

        private void InitializeData()
        {
            // We get an in memory cache of data
            ds = new DataSet();
            // Building the querry commands for retrieving data from DB
            String commandChild = selectCommand + childTable + ";";
            String commandParentOne = selectCommand + parentOneTable + ";";
            String commandParentTwo = selectCommand + parentTwoTable + ";";
            // We build a set of commands and a connection to be able to fill the data set with tables
            daParentOne = new SqlDataAdapter(commandParentOne, dbConn);
            cbParentOne = new SqlCommandBuilder(daParentOne);
            // Open the connection and execute the predefined command such that in our data set at the 
            // string parentTable we will have the results of the predefined query
            daParentOne.Fill(ds, parentOneTable);

            daParentTwo = new SqlDataAdapter(commandParentTwo, dbConn);
            cbParentTwo = new SqlCommandBuilder(daParentTwo);
            daParentTwo.Fill(ds, parentTwoTable);

            daChild = new SqlDataAdapter(commandChild, dbConn);
            cbChild = new SqlCommandBuilder(daChild);
            daChild.Fill(ds, childTable);
            // We use a DataRelation object such that we will recreate the one to many relation in 
            // our data set object
            dr = new DataRelation("FK_ParentOne_ParentTwo",
                ds.Tables[parentOneTable].Columns[parentOneKey],
                ds.Tables[parentTwoTable].Columns[parentTwoKey]);

            drTwo = new DataRelation("FK_ParenTwo_Child",
                ds.Tables[parentTwoTable].Columns[parentTwoKey],
                ds.Tables[childTable].Columns[childKey]);
            //Unique Constraint, ForeignKey Constraint
            //GetChildRows
            //GetParentRow
            ds.Relations.Add(dr);
            ds.Relations.Add(drTwo);
            // We encapsulate the data in binding sources to be able to transfer it to the view
            bsParentOne = new BindingSource();
            bsParentOne.DataSource = ds;
            bsParentOne.DataMember = parentOneTable;

            bsParentTwo = new BindingSource();
            bsParentTwo.DataSource = bsParentOne;
            bsParentTwo.DataMember = "FK_ParentOne_ParentTwo";

            bsChild = new BindingSource();
            bsChild.DataSource = bsParentTwo;
            bsChild.DataMember = "FK_ParenTwo_Child";

            // Transferring data to the view
            parenOneDgv.DataSource = bsParentOne;
            parentTwoDgv.DataSource = bsParentTwo;
            childDgv.DataSource = bsChild;
        }
    }
}
