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

namespace Laboratory_1
{
    public partial class Form1 : Form
    {
        SqlConnection dbConn;
        SqlDataAdapter daGames, daGenre;
        DataSet ds;
        BindingSource bsGames, bsGenre;
        SqlCommandBuilder cbGames;
        public Form1()
        {
            InitializeComponent();
        }

        private void genre_tableBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.genre_tableBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.gADBDataSet);

        }

        private void genreDgv_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            dbConn = new SqlConnection("Data Source = DESKTOP-97FIB3Q\\SQLEXPRESS;" +
                "Initial Catalog = GADB;" +
                "Integrated Security = SSPI");
            ds = new DataSet();
            daGenre = new SqlDataAdapter("SELECT * FROM genre_table;", dbConn);
            daGames = new SqlDataAdapter("SELECT * FROM games_table;", dbConn);
            cbGames = new SqlCommandBuilder(daGames);
            daGenre.Fill(ds, "Genres");
            daGames.Fill(ds, "Games");

            DataRelation dr = new DataRelation("FK_Genres_Games",
                ds.Tables["Genres"].Columns["id"],
                ds.Tables["Games"].Columns["genreID"]);
            //Unique Constraint, ForeignKey Constraint
            //GetChildRows
            //GetParentRow
            ds.Relations.Add(dr);

            bsGenre = new BindingSource();
            bsGenre.DataSource = ds;
            bsGenre.DataMember = "Genres";

            bsGames = new BindingSource();
            bsGames.DataSource = bsGenre;
            bsGames.DataMember = "FK_Genres_Games";

            geDgv.DataSource = bsGenre;
            gaDgv.DataSource = bsGames;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            daGames.Update(ds, "Games"); //RowState property
        }


    }
}
