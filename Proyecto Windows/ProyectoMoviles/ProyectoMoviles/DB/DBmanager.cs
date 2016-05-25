using ProyectoMoviles.Model;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.DB
{
    public static class DBmanager
    {
        public static SQLiteConnection db;

        public static void createDataBase() {
            SQLiteConnection.CreateFile("Messages.sqlite");
             db = new SQLiteConnection("Data Source=Messages.sqlite; Version=3;");

            db.Open();
            var command = db.CreateCommand();

            //table Create
            command.CommandText = "CREATE TABLE IF NOT EXISTS messages(toCol int,fromCol int, message Varchar(100), dateCol Varchar(25))";
            command.ExecuteNonQuery();
            db.Close();
        }

        public static void addNewMessage(Message newMessage) {
            db.Open();
            var command = db.CreateCommand();

            var to = newMessage.to;
            var from = newMessage.from;
            var message = newMessage.text;
            var date = newMessage.date;

            //Inserting data
            command.CommandText = "INSERT INTO messages(toCol, fromCol, message, dateCol) values (" + to + "," + from + ",'" + message + "','" + date + "')";
            command.ExecuteNonQuery();
            db.Close();
        }

        public static void getMessages(List<Message> messages, int from, int to) {
            db.Open();
            var command = db.CreateCommand();

            //Read from table
            command.CommandText = "SELECT * FROM messages WHERE fromCol = " + from + " AND toCol = " + to + " ORDER BY dateCol ASC";
            SQLiteDataReader sqlDataReader = command.ExecuteReader();

            while (sqlDataReader.Read())
            {
                messages.Add(new Message(
                    Convert.ToInt32(sqlDataReader["toCol"]), 
                    Convert.ToInt32(sqlDataReader["fromCol"]), 
                    sqlDataReader.GetString(sqlDataReader.GetOrdinal("message")),
                    sqlDataReader.GetString(sqlDataReader.GetOrdinal("dateCol")) 
                    ));
            }
            sqlDataReader.Close();
            db.Close();
        }
    }
}
