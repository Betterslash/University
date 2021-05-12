using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Example3
{
    class Program
    {
        static void Main(string[] args)
        {
            SqlConnection con = new SqlConnection("Data Source=.\\SQLEXPRESS;Initial Catalog=trains_stations;Integrated Security=SSPI");
            SqlCommand cmd = new SqlCommand("Select * from TRAINS", con);

            con.Open();

            

            con.Close(); 
            
        }
    }
}
