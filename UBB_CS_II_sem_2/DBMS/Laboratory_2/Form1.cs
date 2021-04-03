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
        SqlConnection dbConn;
        SqlDataAdapter daChild, daParent;
        DataSet ds;
        BindingSource bsChild, bsParent;
        SqlCommandBuilder cbChild;
        string childTable = ""; //"genre_table;";
        string parentTable = ""; //"games_table;";
        string selectCommand ="SELECT * FROM ";
        string parentKey = ""; //"id";
        string childKey = ""; //"genreID";
        public Form1()
        {
            InitializeComponent();
        }
        public void GetConfigurationValue()
        {
            parentTable = ConfigurationManager.AppSettings["ParentTable"];
            childTable = ConfigurationManager.AppSettings["ChildTable"];
            parentKey = ConfigurationManager.AppSettings["ParentKey"];
            childKey = ConfigurationManager.AppSettings["ChildKey"];
         }
        private void Form1_Load(object sender, EventArgs e)
        {
            GetConfigurationValue();
            dbConn = new SqlConnection("Data Source = DESKTOP-97FIB3Q\\SQLEXPRESS;" +
               "Initial Catalog = GADB;" +
               "Integrated Security = SSPI");
            ds = new DataSet();
            daParent = new SqlDataAdapter(selectCommand + childTable + ";", dbConn);
            daChild = new SqlDataAdapter(selectCommand + parentTable + ";", dbConn);
            cbChild = new SqlCommandBuilder(daChild);
            daParent.Fill(ds, "Parent");
            daChild.Fill(ds, "Child");

            DataRelation dr = new DataRelation("FK_Parent_Child",
                ds.Tables[parentTable].Columns["id"],
                ds.Tables[childTable].Columns["genreID"]);
            //Unique Constraint, ForeignKey Constraint
            //GetChildRows
            //GetParentRow
            ds.Relations.Add(dr);

            bsParent = new BindingSource();
            bsParent.DataSource = ds;
            bsParent.DataMember = "Parent";

            bsChild = new BindingSource();
            bsChild.DataSource = bsParent;
            bsChild.DataMember = "FK_Parent_Child";

            parentDGV.DataSource = bsParent;
            childDGV.DataSource = bsChild;
        }
    }
}
