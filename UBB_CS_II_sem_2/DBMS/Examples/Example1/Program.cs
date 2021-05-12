using System;
using System.Data.SqlClient;

namespace Example1
{
    class Program
	{
		static void Main(string[] args) 
		{
			SqlConnection sqlConnection = new SqlConnection();
			SqlCommand sqlCommand = new SqlCommand();
			Console.WriteLine("Hello World!");
		}
	}
}
