using DBMS_CRUD.Model;
using System;
using System.Collections.Generic;
using System.Text;

namespace DBMS_CRUD.NewFolder
{
    public class Train: DBObject
    {
        private readonly int id;
        private readonly string type;
        private readonly string color;
        private readonly DateTime dateTime;

        public Train( string type, string color, DateTime dateTime)
        {
            
            this.type = type;
            this.color = color;
            this.dateTime = dateTime;
        }

        public string Color => color;

        public string Type => type;

        public DateTime DateTime => dateTime;

        public int getId() => id;

        public override string ToString()
        {
            String returnString = "";
            returnString += id + " " + type + " " + color + " " + dateTime.ToString();
            return returnString;
        }
    }
   
}
