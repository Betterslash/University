using DBMS_CRUD.NewFolder;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;

namespace DBMS_CRUD.Services
{
    class TrainService<T> : DBOService<T> where T : Train
    {

        public void ExcuteDelete(int id)
        {
        }

        public IList<T> ExcuteRead()
        {
            throw new NotImplementedException();
        }

        public void ExecuteCreate(T dBObject)
        {
            SqlConnection con = new SqlConnection("Data Source=.\\SQLEXPRESS;Initial Catalog=trains_stations;Integrated Security=SSPI");
            string insertCommand = "INSERT INTO Trains(train_type, train_color, fabrication_date) VALUES";
            insertCommand += "('" + dBObject.Type + "', '" + dBObject.Color + "', '" + dBObject.DateTime.ToString() + "')";
            SqlCommand command = new SqlCommand(insertCommand, con);
            con.Open();
            command.ExecuteNonQuery();
            con.Close();
        }

        public void ExecuteUpdate(T dBObject)
        {
            throw new NotImplementedException();
        }
    }
}
