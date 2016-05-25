using ProyectoMoviles.DB;
using ProyectoMoviles.Model;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
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

            DBmanager.getMessages(messages, contactId, 3);

            MessagesListView.ItemsSource = messages;
            
        }

        private void Send_message(object sender, RoutedEventArgs e)
        {
            string text = textToSend.Text;
            string json = "{ " + '"' + "from" + '"'+ ":" + 3 + "," + '"'+"to"+ '"'+ ":" + contactId + "," + '"' + "text" + '"' + ":"+'"'+ text + '"' + "}";
            Console.WriteLine(json);

            var client = new RestClient("http://localhost:8090");
            var request = new RestRequest("rest/messages", Method.POST);

            request.AddParameter("application/json", json, ParameterType.RequestBody);

            // execute the request
            //calling server with restClient
            var result = client.ExecuteAsync(request, (response) =>
            {

                if (response.StatusCode == HttpStatusCode.OK)
                {
                    //upload successfull
                    DateTime localDate = DateTime.Now;
                    Message newMessage = new Message(3, contactId, text, localDate.ToString());
                    messages.Add(newMessage);
                    DBmanager.addNewMessage(newMessage);
                }
                else
                {
                    //error ocured during upload
                    MessageBox.Show("There was a problem sending the message, please try again");
                }
            });

        }
    }
}
