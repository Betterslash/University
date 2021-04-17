using System;
using System.Collections;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Windows.Forms;

namespace Laboratory_2
{
    public partial class Form1 : Form
    {
        // Initializing variables for the future connections
        SqlConnection dbConn;
        SqlDataAdapter daChild, daParent;
        DataSet ds;
        BindingSource bsChild, bsParent;
        SqlCommandBuilder cbChild, cbParent;
        DataRelation dr;
        String childTable = "";
        String parentTable = "";
        String selectCommand ="SELECT * FROM ";
        String parentKey = ""; //"id";
        String childKey = ""; //"genreID";

        private void updateBTN_Click(object sender, EventArgs e)
        {
            daChild.Update(ds, childTable); //RowState property
        }

        public Form1()
        {
            InitializeComponent();
        }

        private void refreshBTN_Click(object sender, EventArgs e)
        {
            InitializeData();
        }

        public void GetConfigurationValue()
        {
            parentTable = ConfigurationManager.AppSettings.Get("ParentTable");
            childTable = ConfigurationManager.AppSettings.Get("ChildTable");
            parentKey = ConfigurationManager.AppSettings.Get("ParentKey");
            childKey = ConfigurationManager.AppSettings.Get("ChildKey");
         }
        private void Form1_Load(object sender, EventArgs e)
        {
            dbConn = new SqlConnection("Data Source = DESKTOP-97FIB3Q\\SQLEXPRESS;" +
               "Initial Catalog = GADB;" +
               "Integrated Security = SSPI");
            //Parse config file and store values in class fields
            GetConfigurationValue();
            //Start initializing the views with Data
            InitializeData();
            
        }
        private void InitializeData() {
            // We get an in memory cache of data
            ds = new DataSet();
            // Building the querry commands for retrieving data from DB
            String commandChild = selectCommand + childTable + ";";
            String commandParent = selectCommand + parentTable + ";";
            // We build a set of commands and a connection to be able to fill the data set with tables
            daParent = new SqlDataAdapter(commandParent, dbConn);
            cbParent = new SqlCommandBuilder(daParent);
            // Open the connection and execute the predefined command such that in our data set at the 
            // string parentTable we will have the results of the predefined query
            daParent.Fill(ds, parentTable);
            daChild = new SqlDataAdapter(commandChild, dbConn);
            cbChild = new SqlCommandBuilder(daChild);
            daChild.Fill(ds, childTable);
            // We use a DataRelation object such that we will recreate the one to many relation in 
            // our data set object
            dr = new DataRelation("FK_Parent_Child",
                ds.Tables[parentTable].Columns[parentKey],
                ds.Tables[childTable].Columns[childKey]);
            //Unique Constraint, ForeignKey Constraint
            //GetChildRows
            //GetParentRow
            ds.Relations.Add(dr);
            // We encapsulate the data in binding sources to be able to transfer it to the view
            bsParent = new BindingSource();
            bsParent.DataSource = ds;
            bsParent.DataMember = parentTable;

            bsChild = new BindingSource();
            bsChild.DataSource = bsParent;
            bsChild.DataMember = "FK_Parent_Child";

            // Transferring data to the view
            parentDGV.DataSource = bsParent;
            childDGV.DataSource = bsChild;
        }
    }
}
