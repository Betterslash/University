using DBMS_CRUD.NewFolder;
using DBMS_CRUD.Services;
using System;

namespace DBMS_CRUD
{
    class Program
    {
        static void Main(string[] args)
        {
            
            string type = "Mocanita";
            string color = "Brown";
            DateTime dateTime = new DateTime(1900, 9, 11);
            Train train = new Train(type, color, dateTime);
            DBOService<Train> dBOService = new TrainService<Train>();
            dBOService.ExecuteCreate(train);
            Console.WriteLine(train);
        }
    } 
}
