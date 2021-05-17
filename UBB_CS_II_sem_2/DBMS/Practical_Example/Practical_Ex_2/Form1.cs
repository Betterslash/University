using System;
using System.Data;
using System.Data.SqlClient;
using System.Windows.Forms;

namespace Practical_Ex_2
{
    public partial class Form1 : Form
    {
        SqlDataAdapter parentDa, childDa;
        SqlCommandBuilder parentCb, childCb;
        SqlConnection dbConn;
        DataSet ds;
        DataRelation dr;
        BindingSource bsChild, bsParent;

        string parentTable, childTable;
        string parentKey, childKey;
        string sqlCommand;

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void Form1_Load_1(object sender, EventArgs e)
        {
            InitializeParameters();
            InitializeView();
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            childDa.Update(ds, childTable);
        }

        string connection;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            
        }

        private void InitializeParameters() {
            parentTable = "developer";
            childTable = "task";
            parentKey = "id";
            childKey = "developer_id";
            sqlCommand = "SELECT * FROM ";
            connection = "Data Source = DESKTOP-97FIB3Q\\SQLEXPRESS;" +
               "Initial Catalog = practical_ex_2;" +
               "Integrated Security = SSPI";
        }

        private void InitializeView() {

            dbConn = new SqlConnection(connection);
            ds = new DataSet();
            
            string commandChild = sqlCommand + childTable + ";";
            string commandParent = sqlCommand + parentTable + ";";

            parentDa = new SqlDataAdapter(commandParent, dbConn);
            parentCb = new SqlCommandBuilder(parentDa);
            parentDa.Fill(ds, parentTable);

            childDa = new SqlDataAdapter(commandChild, dbConn);
            childCb = new SqlCommandBuilder(childDa);
            childDa.Fill(ds, childTable);

            dr = new DataRelation("developer_task_FK",
                    ds.Tables[parentTable].Columns[parentKey],
                    ds.Tables[childTable].Columns[childKey]
                );

            ds.Relations.Add(dr);

            bsParent = new BindingSource();
            bsParent.DataSource = ds;
            bsParent.DataMember = parentTable;

            bsChild = new BindingSource();
            bsChild.DataSource = bsParent;
            bsChild.DataMember = "developer_task_FK";

            parentDgv.DataSource = bsParent;
            childDgv.DataSource = bsChild;
        }
    }
}
