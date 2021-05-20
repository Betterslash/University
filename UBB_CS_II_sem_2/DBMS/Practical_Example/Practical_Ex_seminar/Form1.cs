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

namespace Practical_Ex_seminar
{
    public partial class Form1 : Form
    {
        SqlConnection dbConn;
        SqlDataAdapter parentDa, childDa;
        SqlCommandBuilder parentCb, childCb;
        DataRelation dr;
        DataSet ds;
        BindingSource childBs, parentBs;
        string foreignKey = "parent_child_FK";
        string sqlCommand = "SELECT * FROM ";
        string childTable = "posts";
        string parentTable = "users";
        string childKey = "user_id";
        string parentKey = "id";

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            dbConn = new SqlConnection(
                "Data Source = DESKTOP-97FIB3Q\\SQLEXPRESS;" +
                "Initial Catalog = practical_ex_seminar;" +
                "Integrated Security = SSPI;"
                );

            ds = new DataSet();

            string commandParent = sqlCommand + parentTable + ";";
            string commandChild = sqlCommand + childTable + ";";

            parentDa = new SqlDataAdapter(commandParent, dbConn);
            parentCb = new SqlCommandBuilder(parentDa);
            parentDa.Fill(ds, parentTable);

            childDa = new SqlDataAdapter(commandChild, dbConn);
            childCb = new SqlCommandBuilder(childDa);
            childDa.Fill(ds, childTable);

            dr = new DataRelation(foreignKey,
                    ds.Tables[parentTable].Columns[parentKey],
                    ds.Tables[childTable].Columns[childKey]
                   );

            ds.Relations.Add(dr);

            parentBs = new BindingSource();
            parentBs.DataSource = ds;
            parentBs.DataMember = parentTable;

            childBs = new BindingSource();
            childBs.DataSource = parentBs;
            childBs.DataMember = foreignKey;

            parentDgv.DataSource = parentBs;
            childDgv.DataSource = childBs;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            childDa.Update(ds, childTable);
        }
    }
}
