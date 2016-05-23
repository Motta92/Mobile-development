using ProyectoMoviles.DB;
using ProyectoMoviles.Model;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace ProyectoMoviles.Views
{
    /// <summary>
    /// Interaction logic for Messages.xaml
    /// </summary>
    public partial class Messages : Window
    {
        public int contactId { get; set; }
        List<Message> messages;

        public Messages(int indexOfContact)
        {
            this.contactId = indexOfContact;
            messages = new List<Message>();
            
            InitializeComponent();

            // DB test data
            //DBmanager.addNewMessage(new Message(1, 3, "testing like a baws", "22-04-2016"));
            
            var client = new RestClient("http://localhost:8090");
            var request = new RestRequest("rest/messages/" + contactId + "/3", Method.GET);
            var result = client.Execute<List<Message>>(request).Data;

            foreach (Message m in result)
            {
                //messages.Add(m);
                Console.WriteLine("m.message: " + m.text);
                DBmanager.addNewMessage(m);
            }

            DBmanager.getMessages(messages);

            MessagesListView.ItemsSource = messages;
            
        }
    }
}
